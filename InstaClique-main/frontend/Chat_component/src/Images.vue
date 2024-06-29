<template>
    <div>
      <div v-for="image in images" :key="image._id">
        <img :src="getImageUrl(image)" alt="Image">
      </div>
    </div>
  </template>
  
  <script>
  import axios from 'axios';
  
  export default {
    data() {
      return {
        images: []
      };
    },
    mounted() {
      // Make an HTTP GET request to your backend API endpoint
      axios.get('http://localhost:8080/api/images')
        .then(response => {
          // Set the received images to the component's data
          this.images = response.data;
        })
        .catch(error => {
          console.error('Error fetching images:', error);
        });
    },
    methods: {
      getImageUrl(image) {
        // Assuming the image data contains a field 'url' that represents the URL of the image
        // return image.url;
        // return "https://tse1.mm.bing.net/th?id=OIP.xKiE_jUn8tPueoLWXsyCPgHaEo&pid=Api&P=0&h=180";
        return "https://tse1.mm.bing.net/th?id=OIP.xKiE_jUn8tPueoLWXsyCPgHaEo&pid=Api&P=0&h=180";
      }
    }
  };
  </script>
  