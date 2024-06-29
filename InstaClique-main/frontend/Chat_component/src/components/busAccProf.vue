<template>
  <div v-if="userProfile" class="user-profile">
    <h1>{{ userProfile.name }}</h1>
    <p><strong>User ID:</strong> {{ userProfile.UserId }}</p>
    <p><strong>Company:</strong> {{ userProfile.company }}</p>
    <p><strong>Bill:</strong> {{ userProfile.bill }}</p>
    <p><strong>Followers:</strong> {{ userProfile.followers }}</p>

    <!-- Clickable tile for Pay Bill feature -->
    <div class="pay-bill-tile" @click="handlePayBillClick" v-if="userProfile.bill !== null">
      <p><strong>Pay Bill:</strong> Enabled</p>
    </div>

    <!-- Clickable tile for Add Post feature -->
    <div class="pay-bill-tile" @click="handleAddPost">
      <p><strong>Add Post:</strong> Enabled</p>
    </div>

    <!-- Render fetched image posts -->
    <div class="image-container" v-if="fetchedImagePosts.length > 0">
      <div v-for="post in fetchedImagePosts" :key="post.id" class="image-post">
        <h2>{{ post.title }}</h2>
        <img :src="getPostImageUrl(post)" alt="Post Image">
        <!-- <p>Author: {{ post.author }}</p> -->
        <!-- <p>Created At: {{ post.createdAt }}</p> -->
      </div>
    </div>

    <!-- Section for displaying posts -->
    <section v-if="userProfile.posts && userProfile.posts.length > 0">
      <h2>Recent Posts</h2>
      <div class="post" v-for="post in userProfile.posts" :key="post.id">
        <PostVue :post="post" />
      </div>
    </section>
   
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
      userProfile: null,
      userId: "1",
      list: [],
      fetchedImagePosts: [] // Array to store fetched image posts
    };
  },

  mounted() {
    // Make an HTTP GET request to fetch user profile data from the backend
    axios.get(`http://localhost:8080/api/bus`)
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
        this.list = response.data;
        console.log("post list");
        console.log(response.data);

        // Fetch and process each image post
        this.list.forEach(item => {
          let postId = item; // Extract post ID from item if needed

          axios.get(`http://localhost:8080/api/post/post?postId=${postId}`, {
           // Ensure response type is set to arraybuffer for binary data
          })
            .then(response => {
              console.log("Fetched post data:", response.data);

              if (response.data.data) {
                // Convert Base64 data to Blob
                const blob = this.base64ToBlob(response.data.data);
                console.log(response.data.data);
                // this.binarycontent[postId]=response.data.data;
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
            .catch(error => {
              console.error("Error fetching post:", error);
            });
        });
      });
  },

  methods: {
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

      try {
        axios.post('http://localhost:8080/api/post/create', {
          
        });
        console.log('Post added successfully:', response.data);
        // Redirect to home or post list page after successful addition
        this.$router.push('/');
      } catch (error) {
        console.error('Error adding post:', error);
        // Handle error (e.g., show error message to user)
      }



      this.$router.push('/uploadimage');
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
