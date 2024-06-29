<template>
  <div class="feed">
    <div v-for="post in fetchedImagePosts" :key="post.id" class="post">
      <!-- Display post content based on type -->
      <div>
        <img :src="post.imageUrl" class="post-image" alt="Post Image"  @click="toggleComment(post.id)" />
      </div>

      <div v-if="post.id === selectedPostId">
        <div class="like-section">
          <span>Like: {{ likesData[post.id] || 0 }}</span>
        </div>
        <div class="comment-section">
          <input type="text" v-model="comment" placeholder="Enter Comment..." />
          <button @click="sendComment(post.id)">Send Comment</button>
          <button @click="sendLike(post.id)" class="like">&#x2665;</button>
          <button @click="viewAllComments(post.id)">View All Comments</button>
          <div v-if="commentsData && commentsData[post.id]">
            <div v-for="(comment, userId) in commentsData[post.id]" :key="userId" class="comment">
              <b>{{ userId }}:</b> {{ comment }}
              <button @click="deleteComment(post.id, userId)">&#x1F5D1;</button>
            </div>
          </div>
        </div>
        <button class="share-button" @click="fetchSharedList(post.id)">Share</button>
        <div v-if="sharedData && sharedData">
          <h3>Shared Data</h3>
          <ul>
            <li v-for="(value,key) in sharedData" :key="key" class="shared-item" @click="share(image, post.id, key, value)">
              {{ value }}
            </li>
          </ul>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      userId2: "4",
      fetchedImagePosts: [],
      commentsData: {},
      sharedData: [],
      comment: '',
      imageData: null,
      selectedPostId: null,
      likesData: {}, 
      binarycontent:{}
     
    };
  },
  mounted() {
    axios
      .get("http://localhost:8080/api/post/list")
      .then((response) => {
        const postList = response.data;

        // Iterate through each post and fetch associated image
        postList.forEach((postId) => {
          axios
            .get(`http://localhost:8080/api/post/post?postId=${postId}`)
            .then((response) => {
              if (response.data.data) {
                // Convert Base64 data to Blob
                const blob = this.base64ToBlob(response.data.data);
                console.log(response.data.data);
                this.binarycontent[postId]=response.data.data;
                // Create a URL for the Blob
                const imageUrl = URL.createObjectURL(blob);

                this.fetchedImagePosts.push({
                  id: postId,
                  author: 'Unknown',
                  imageUrl: imageUrl,
                  type:"image/jpeg",
                });
              } else {
                console.warn(`Post with ID ${postId} does not contain valid image data.`);
              }
            })
            .catch((error) => {
              console.error("Error fetching post:", error);
            });
        });
      })
      .catch((error) => {
        console.error("Error fetching post list:", error);
      });
  },
  methods: {
    fetchLikes(postId) {
      fetch("http://localhost:8080/getLikes/"+postId)
        .then(response => {
          if (!response.ok) {
            throw new Error('Network response was not ok');
          }
          return response.json();
        })
        .then(data => {
          this.likesData[postId]= data[postId];
        })
        .catch(error => console.error('Error:', error));
    },
    toggleComment(postId) {
      if (this.selectedPostId === postId) {
        this.selectedPostId = null;
      } else {
        this.selectedPostId = postId;
      }
    },
    sendComment(postId) {
      if (!this.comment || !this.selectedPostId) {
        console.error('All fields are required');
        return;
      }

      let formData = new FormData();
      formData.append('postId', postId);
      formData.append('commentData', this.comment);

      fetch('http://localhost:8080/makeComment', {
        method: 'POST',
        body: formData
      })
        .then(response => response.text())
        .then(data => {
          console.log(data);
          this.comment = '';
        })
        .catch(error => console.error('Error:', error));
    },
    sendLike(postid) {
      if (!this.selectedPostId) {
        console.error('All fields are required');
        return;
      }

      let formData = new FormData();
      formData.append('postId', postid);

      fetch('http://localhost:8080/Like', {
        method: 'POST',
        body: formData
      })
        .then(response => response.text())
        .then(data => {
          console.log(data);
          this.fetchLikes(postid);
        })
        .catch(error => console.error('Error:', error));
    },
    viewAllComments(postId) {
      fetch("http://localhost:8080/getComments/"+postId)
        .then(response => {
          if (!response.ok) {
            throw new Error('Network response was not ok');
          }
          return response.json();
        })
        .then(data => {
          this.commentsData[postId]=data;
        })
        .catch(error => console.error('Error:', error));
    },
    fetchSharedList(postId) {
      fetch("http://localhost:8080/network/followers/" +this.userId2)
        .then(response => {
          if (!response.ok) {
            throw new Error('Network response was not ok');
          }
          return response.json();
        })
        .then(data => {
          this.sharedData=data;
        })
        .catch(error => console.error('Error:', error));
    },
    share(image, postId, key, value) {
      const formData = new FormData();
      formData.append('userId2', this.userId2);
      const imageData = this.fetchedImagePosts[postId];
      console.log(imageData);
      // const base64String = imageData.data.split(',')[1];
      formData.append('file',this.binarycontent[postId]);
      formData.append('contentType', "image");
      formData.append('value', value);

      fetch("http://localhost:8080/api/share/post", {
        method: 'POST',
        body: formData
      })
        .then(response => {
          if (!response.ok) {
            throw new Error('Network response was not ok');
          }
          return response.text();
        })
        .then(data => {
          console.log(data);
        })
        .catch(error => console.error('Error:', error));
    },
    deleteComment(postId, userId) {
      let formData = new FormData();
      formData.append('postId', postId);
      formData.append('userId', userId);

      fetch("http://localhost:8080/deleteComment", {
        method: 'POST',
        body: formData
      })
        .then(response => response.text())
        .then(data => {
          console.log(data);
          this.viewAllComments(postId);
        })
        .catch(error => console.error('Error:', error));
    },
    base64ToBlob(base64Data) {
      const byteCharacters = atob(base64Data);
      const byteNumbers = new Array(byteCharacters.length);
      for (let i = 0; i < byteCharacters.length; i++) {
        byteNumbers[i] = byteCharacters.charCodeAt(i);
      }
      const byteArray = new Uint8Array(byteNumbers);
      return new Blob([byteArray], { type: 'image/jpeg' });
    },
  }
};
</script>

<style scoped>
.like{
  
  font-size: x-large;
}
/* Your CSS styles */
</style>
