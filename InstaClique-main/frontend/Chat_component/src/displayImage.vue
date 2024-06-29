<template>
   <div id="app">
    <input type="text" v-model="userId" placeholder="Enter User ID...">
    <button @click="displayImages">Display Images</button>
    <div v-if="imageData">
        <div v-for="(image, postId) in imageData" :key="postId">
            <template v-if="image.type === 'image'">
                <img :src="image.data" alt="Post Image" @click="toggleComment(postId)">
            </template>
            <template v-else-if="image.type === 'video'">
                <video :src="image.data" controls @click="toggleComment(postId)"></video>
            </template>
            <div v-if="postId === selectedPostId">
                <div class="like-section">
                    <span>Like: {{ likesData[postId] || 0 }}</span>
                </div>
                <div class="comment-section">
                    <input type="text" v-model="userId2" placeholder="Enter User ID 2...">
                    <input type="text" v-model="comment" placeholder="Enter Comment...">
                    <button @click="sendComment">Send Comment</button>
                    <button @click="sendLike">Like</button>
                    <button @click="viewAllComments(postId)">View All Comments</button>
                    <div v-if="commentsData && commentsData[postId]">
                        <div v-for="(comment, userId) in commentsData[postId]" :key="userId" class="comment">
                            <b>{{ userId }}:</b> {{ comment }}
                            <button @click="deleteComment(postId, userId)">Delete</button>
                        </div>
                    </div>
                </div>
                <button class="share-button" @click="fetchSharedList(postId)">Share</button>
                <div v-if="sharedData && sharedData">
                    <h3>Shared Data</h3>
                    <ul>
                      <li v-for="(value,key) in sharedData" :key="key" class="shared-item" @click="share(image, postId, key, value)">
                        {{ value }}
                      </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>
  </template>
  

  <script>
  export default {
    data() {
      return {
      
        userId2: "4",
        comment: '',
        imageData: null,
        selectedPostId: null,
        commentsData: {}, // Object to store comments for each postId
        likesData: {}, // Object to store likes count for each postId
        sharedData: [] // Object to store shared data for each postId
      };
    },
    methods: {
      displayImages() {
                fetch("http://localhost:8080/getPhoto")
                    .then(response => {
                        if (!response.ok) {
                            throw new Error('Network response was not ok');
                        }
                        return response.json();
                    })
                    .then(data => {
                        this.imageData = {};
                        for (let postId in data) {
                            let mediaType = data[postId].type;
                            this.imageData[postId] = {
                                type: mediaType,
                                data: `data:${mediaType === 'image' ? 'image/jpeg' : 'video/mp4'};base64,${data[postId].data}`
                            };
                            this.fetchLikes(postId);
                        }
                    })
                    .catch(error => console.error('Error:', error));
            },
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
                      console.log(this.likesData);
                        // this.$set(this.likesData, postId, data[postId]);
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
            sendComment() {
                if (!this.comment || !this.selectedPostId) {
                    console.error('All fields are required');
                    return;
                }

                let formData = new FormData();
                formData.append('postId', this.selectedPostId);
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
            sendLike() {
                if (!this.selectedPostId) {
                    console.error('All fields are required');
                    return;
                }

                let formData = new FormData();
                formData.append('postId', this.selectedPostId);

                fetch('http://localhost:8080/Like', {
                    method: 'POST',
                    body: formData
                })
                    .then(response => response.text())
                    .then(data => {
                        console.log(data);
                        this.fetchLikes(this.selectedPostId);
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
                      console.log(this.commentsData);
                        // this.$set(this.commentsData, postId, data);
                    })
                    .catch(error => console.error('Error:', error));
            },
            fetchSharedList(postId) {
                console.log('Fetching shared list...');
                fetch("http://localhost:8080/network/followers/" +this.userId2)
                    .then(response => {
                        if (!response.ok) {
                            throw new Error('Network response was not ok');
                        }
                        return response.json();
                    })
                    .then(data => {
                        console.log('Received shared data:', data);
                        this.sharedData=data;
                        // this.$set(this.sharedData, postId, data);
                    })
                    .catch(error => console.error('Error:', error));
            },
      share(image, postId, key, value) {
        const formData = new FormData();
        formData.append('userId2', this.userId2);
        const imageData = this.imageData[postId];
const base64String = imageData.data.split(',')[1];
// console.log(base64String);

        formData.append('file',base64String );
        formData.append('contentType', this.imageData[postId].type);
        formData.append('value', value);
  
        fetch("http://localhost:8080/api/share/post", {
            method: 'POST',
            body: formData
          })
          .then(response => {
            if (!response.ok) {
                console.log(this.imageData[postId]);
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
            }


    }
  };
  </script>
  
  <style>
  img,
  video {
    max-width: 400px;
    /* Set the maximum width of the image */
    height: auto;
    /* Maintain aspect ratio */
    margin-bottom: 10px;
    /* Add margin bottom between images */
    display: block;
    /* Display images as block elements */
  }
  
  .comment-section {
    margin-top: 10px;
    /* Add margin top for the comment section */
  }
  
  .share-button {
    margin-top: 10px;
    /* Add margin top for the share button */
  }
  
  .shared-item {
    cursor: pointer;
    /* Change cursor to pointer to indicate clickable element */
  }
  </style>
  