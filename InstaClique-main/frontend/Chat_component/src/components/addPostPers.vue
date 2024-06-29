<template>
  <div class="add-post">
    <h1>Add New Post</h1>

    <!-- Post Form -->
    <form @submit.prevent="submitNewPost">
      <!-- Post Title Input -->
      <div class="form-group">
        <label for="postTitle">Post Title:</label>
        <input type="text" id="postTitle" v-model="newPost.title" required>
      </div>

      <!-- File Upload Input for Image -->
      <div class="form-group">
        <label for="imageFile">Upload Image:</label>
        <input type="file" id="imageFile" @change="handleImageChange" accept="image/*">
      </div>

      <!-- File Upload Input for Video -->
      <div class="form-group">
        <label for="videoFile">Upload Video:</label>
        <input type="file" id="videoFile" @change="handleVideoChange" accept="video/*">
      </div>

      <!-- Submit Button -->
      <button type="submit">Add Post</button>
    </form>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      newPost: {
        image: null,
        video: null
      }
    };
  },
  methods: {
    handleImageChange(event) {
      this.newPost.image = event.target.files[0];
    },
    handleVideoChange(event) {
      this.newPost.video = event.target.files[0];
    },
    async submitNewPost() {
      let formData = new FormData();
      if (this.newPost.image) {
        formData.append('image', this.newPost.image);
      }
      if (this.newPost.video) {
        formData.append('video', this.newPost.video);
      }

      try {
        const response = await axios.post('http://localhost:8080/api/post/add', formData, {
          headers: {
            'Content-Type': 'multipart/form-data'
          }
        });
        console.log('Post added successfully:', response.data);
        // Redirect to home or post list page after successful addition
        this.$router.push('/');
      } catch (error) {
        console.error('Error adding post:', error);
        // Handle error (e.g., show error message to user)
      }
    }
  }
};
</script>


<style scoped>
.add-post {
  max-width: 600px;
  margin: 0 auto;
  padding: 20px;
  border: 1px solid #ccc;
  border-radius: 5px;
  background-color: #f9f9f9;
}

.form-group {
  margin-bottom: 15px;
}

label {
  font-weight: bold;
}

input[type="text"],
input[type="file"] {
  width: 100%;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 5px;
  box-sizing: border-box;
}

button {
  padding: 10px 20px;
  background-color: #007bff;
  color: #fff;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}
</style>
