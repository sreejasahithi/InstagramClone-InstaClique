<template>
  <div>
   
    <input type="text" v-model="searchQuery" placeholder="Search followers..." @input="searchFollowers">

    <h2>Following count: {{ followers.length }}</h2>
    <ul>
      <li v-for="follower in filteredFollowers" :key="follower">
        <span>{{ follower}}</span>
        <button @click="unfollow(follower)">Unfollow</button>
        <button @click="block(follower)">Block</button>
      </li>
    </ul>
  </div>
</template>

  <script>
  import axios from "axios";
  export default {
    data() {
      return {
        followers: [
         
          // Add more followers here...
        ],
        searchQuery: '',
        user_id:"2",
      };
    },
    computed: {
      filteredFollowers() {
        if (!this.searchQuery) {
          return this.followers;
        } else {
          return this.followers.filter(follower =>
            follower.name.toLowerCase().includes(this.searchQuery.toLowerCase())
          );
        }
      }
    },
    methods: {
      searchFollowers() {
        // Triggered when the search input changes
        // Filters followers based on the search query
      },
      unfollow(follower) {
        // Method to unfollow a follower
        // let user_id="1";
        axios.get('http://localhost:8080/following/remove/'+follower+'/'+this.user_id)
      .then(response => {
        // Update the followers array with the data received from the backend
        // this.followers =response.data;
        window.location.reload();
        console.log(response.data);
      })
      .catch(error => {
        console.error('Error fetching followers:', error);
      });
        
      },
      block(follower) {
        // Method to block a follower
        axios.get('http://localhost:8080/network/block/'+follower+'/'+this.user_id)
      .then(response => {
        // Update the followers array with the data received from the backend
        this.followers =response.data;
        console.log(response.data);
      })
      .catch(error => {
        console.error('Error fetching followers:', error);
      });

      }
    },
    mounted() {
      this.user_id = this.$route.params.FROM;
      console.log(this.user_id);
    // Make an HTTP GET request to fetch followers data from the backend
    axios.get('http://localhost:8080/following/'+this.user_id)
      .then(response => {
        // Update the followers array with the data received from the backend
        this.followers =response.data;
        console.log(response.data);
      })
      .catch(error => {
        console.error('Error fetching followers:', error);
      });
  }
  };
  
  </script>
  
  <style>
  /* CSS styles for the component */
  ul {
    list-style: none;
    padding: 0;
  }
  
  li {
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 10px;
    margin-bottom: 5px;
    background-color: #f9f9f9;
    border: 1px solid #eee;
    border-radius: 5px;
  }
  
  button {
    padding: 5px 10px;
    margin-left: 10px;
    border: none;
    border-radius: 5px;
    background-color: #007bff;
    color: white;
    cursor: pointer;
  }
  
  button:hover {
    background-color: #0056b3;
  }
  </style>
  