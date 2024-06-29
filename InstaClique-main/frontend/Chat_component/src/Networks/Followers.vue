<template>
    <div>
     
      <input type="text" v-model="searchQuery" placeholder="Search followers..." @input="searchFollowers">
  
      <h2>Followers count: {{ followers.length }}</h2>
      <ul>
        <li v-for="follower in filteredFollowers" :key="follower">
          <span>{{ follower}}</span>
          <button @click="unfollow(follower)">Remove Follower</button>
          <button @click="block(follower)">Block</button>
        </li>
      </ul>
    </div>
  </template>
  
  <script>
  import axios from 'axios';
  export default {
   
    data() {
      return {
        
        followers: [
        ],
        searchQuery: '',
        my_userid:"4"
      };
    },
    computed: {
      filteredFollowers() {
        if (!this.searchQuery) {
          return this.followers;
        } else {
          return this.followers.filter(follower =>
            follower.toLowerCase().includes(this.searchQuery.toLowerCase())
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
        // let user_id="2";
        axios.get('http://localhost:8080/network/followers/remove/'+follower+'/'+this.my_userid)
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
        axios.get('http://localhost:8080/network/block/'+follower+'/'+this.my_userid)
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
      this.my_userid = this.$route.params.FROM;
      console.log(this.my_userid);
    // Make an HTTP GET request to fetch followers data from the backend
    axios.get('http://localhost:8080/network/followers/'+this.my_userid)
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
  
  

   

<!-- 
    <template>
      <div>
        <!-- Search input -->
        <input type="text" v-model="searchQuery" placeholder="Search followers..." @input="searchFollowers">
    
        <!-- List of followers -->
        <h2>Followers count: {{ visibleFollowers.length }}</h2>
        <ul>
          <li v-for="follower in visibleFollowers" :key="follower.id" v-if="follower.display">
            <span>{{ follower.name }}</span>
            <button @click="unfollow(follower)">Remove Follower</button>
            <button @click="block(follower)">Block</button>
          </li>
        </ul>
      </div>
    </template>
    
    <script>
    import axios from 'axios';
    export default {
      data() {
        return {
          followers: [],
          searchQuery: ''
        };
      },
      computed: {
        visibleFollowers() {
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
          // Assuming you have a method to make an HTTP DELETE request to the backend to unfollow the follower
          axios.delete(`http://localhost:8080/network/unfollow/${follower.id}`)
            .then(response => {
              // If the unfollow request is successful, update the display property of the follower to false
              follower.display = false;
            })
            .catch(error => {
              console.error('Error unfollowing follower:', error);
            });
        },
        block(follower) {
          // Method to block a follower
        }
      },
      mounted() {
        // Make an HTTP GET request to fetch followers data from the backend
        axios.get('http://localhost:8080/network/followers/2')
          .then(response => {
            // Update the followers array with the data received from the backend
            this.followers = response.data.map(follower => ({ ...follower, display: true }));
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
     -->





     <style>
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