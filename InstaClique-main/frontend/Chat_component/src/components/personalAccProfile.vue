<template>
  <div v-if="userProfile" class="user-profile">
    <h1>{{ userProfile.name }}</h1>
    <p><strong>User ID:</strong> {{ userProfile.UserId }}</p>
    <p><strong>Company:</strong> {{ userProfile.company }}</p>
    <p><strong>Bill:</strong> {{ userProfile.bill }}</p>
    <p><strong>Followers:</strong> {{ userProfile.followers }}</p>

    <!-- Clickable tile for Pay Bill feature -->
    <!-- <div class="pay-bill-tile" @click="handlePayBillClick" v-if="userProfile.bill !== null">
      <p><strong>Pay Bill:</strong> Enabled</p>
    </div> -->

    <!-- Clickable tile for Add Post feature -->
    <div class="pay-bill-tile" @click="handleAddPost">
      <p><strong>Add Post:</strong> Enabled</p>
    </div>

    <!-- Render fetched image posts -->
    <div class="image-container" v-if="fetchedImagePosts.length > 0">
      <div v-for="post in fetchedImagePosts" :key="post.id" class="image-post">
        <h2>{{ post.title }}</h2>
        <img :src="getPostImageUrl(post)" alt="Post Image">
        <p>Author: {{ post.author }}</p>
        <p>Created At: {{ post.createdAt }}</p>
      </div>
    </div>

    <!-- Section for displaying posts -->
    <section v-if="userProfile.posts && userProfile.posts.length > 0">
      <h2>Recent Posts</h2>
      <div class="post" v-for="post in userProfile.posts" :key="post.id">
        <PostVue :post="post" />
      </div>
    </section>
    <div v-else>
      <p>No posts available.</p>
    </div>
  </div>
  <div v-else>
    <p>Loading...</p>
  </div>
</template>

<script>
import axios from 'axios';
import PostVue from './PostVue.vue'; // Import the PostVue component

export default {
  components: {
    PostVue // Register the PostVue component
  },
  data() {
    return {
      userProfile: {},
      userId: "1",
      list: [],
      fetchedImagePosts: [] // Array to store fetched image posts
    };
  },

  mounted() {
    // Make an HTTP GET request to fetch user profile data from the backend
    axios.get(`http://localhost:8080/api/accounts/pers`)
      .then(response => {
        // Update the userProfile object with the data received from the backend
        this.userProfile = response.data;
        console.log(response.data);
      })
      .catch(error => {
        console.error('Error fetching user profile:', error);
      });

    axios.get('http://localhost:8080/api/post/postlist_get')
      .then(response => {
        // this.list = response.data;
        // console.log("post list");
        // console.log(response.data);
        console.log(response.data);
        this.list=response.data;
        // Fetch and process each image post
        this.list.forEach(item => {
          let postId = item; // Extract post ID from item if needed

        



          axios.get(`http://localhost:8080/api/post/post?postId=${postId}`, {
            responseType: 'arraybuffer' // Ensure response type is set to arraybuffer for binary data
            
          })
            .then(response => {
              console.log("Fetched post data:", response);

              // if (response.data && response.data.byteLength > 0) {
              //   // Convert binary data to Blob
              //   const blob = new Blob([response.data], { type: 'image/png' });

              //   // Create object URL from Blob
              //   const imageUrl = URL.createObjectURL(blob);

              //   // Add fetched post with image URL to fetchedImagePosts array
              //   this.fetchedImagePosts.push({
              //     id: postId,
              //     title: `Post ${postId}`,
              //     author: 'Unknown', // Set author name accordingly
              //     createdAt: new Date().toISOString(), // Set creation timestamp accordingly
              //     imageUrl: imageUrl // Assign object URL as image URL
              //   });
              // }
              //  else {
              //   console.warn(`Post with ID ${postId} does not contain valid image data.`);
              // }
            })
            .catch(error => {
              console.error("Error fetching post:", error);
            });
        });
      });
  },

  methods: {
    decodeBinaryToString(binaryData) {
      const decoder = new TextDecoder();
      const decodedString = decoder.decode(
        new Uint8Array(
          atob(binaryData)
            .split("")
            .map((char) => char.charCodeAt(0))
        )
      );
      return decodedString;
    },
    createMultimediaUrl(binaryData, format) {
      const blob = new Blob([this.base64ToArrayBuffer(binaryData)], {
        type: format,
      });
      return URL.createObjectURL(blob);
    },
    base64ToArrayBuffer(base64) {
      const binaryString = atob(base64);
      const bytes = new Uint8Array(binaryString.length);
      for (let i = 0; i < binaryString.length; i++) {
        bytes[i] = binaryString.charCodeAt(i);
      }
      return bytes.buffer;
    },
    handlePayBillClick() {
      if (this.userProfile && this.userProfile.bill !== null) {
        console.log('Redirecting to payment page...');
        this.$router.push('/pay');
      } else {
        console.warn('User profile or bill amount is not available.');
      }
    },

    handleAddPost() {
      console.log('Redirecting to add post page...');
      this.$router.push('/persPost');
    },

    getPostImageUrl(post) {
      return post.imageUrl; // Return image URL for the given post
    }
  }
};
</script>

<style scoped>
.user-profile {
  max-width: 600px;
  margin: 0 auto;
  padding: 20px;
  border: 1px solid #ccc;
  border-radius: 5px;
  background-color: #f9f9f9;
}

.post {
  margin-bottom: 20px;
  padding: 10px;
  border: 1px solid #ddd;
  border-radius: 5px;
  background-color: #fff;
}

.pay-bill-tile {
  cursor: pointer;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 5px;
  background-color: #e8f0fe;
  margin-bottom: 10px;
}

.image-post {
  margin-bottom: 20px;
}

.image-post img {
  max-width: 100%;
  height: auto;
}
</style>
