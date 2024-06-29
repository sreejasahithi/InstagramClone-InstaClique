<template>
    <div id="app">
      <!-- Icon for friend requests -->
      <div class="friend-icon" @click="toggleFriendRequests">
        <img src="https://tse1.mm.bing.net/th?id=OIP.O9Av1Fqp-RGlUgf67hycqAHaHa&pid=Api&P=0&h=180" alt="Friend Request" width="60px" height="60px">
        <!-- Display the number of friend requests -->
        <span v-if="friendRequests.length > 0">{{ friendRequests.length }}</span>
      </div>
  
      <!-- Sliding friend requests panel -->
      <transition name="slide">
        <div class="friend-requests" v-if="showFriendRequests">
          <h2>Friend Requests</h2>
          <ul>
            <!-- Display friend requests -->
            <li v-for="request in friendRequests" :key="request">
              <div>{{ request }}</div>
              <button @click="acceptRequest(request)">Accept</button>
              <button @click="FollowBack(request)">Follow Back</button>
            </li>
          </ul>
        </div>
      </transition>
    </div>
  </template>
  
  <script>
  import axios from "axios";
  export default {

    data() {
      return {
        showFriendRequests: false, // Flag to control visibility of friend requests panel
        friendRequests: [
          "1","2","3"
        ] // Sample friend requests data
        ,my_userid:"4"
      };
    },
    methods: {
      // Toggle visibility of friend requests panel
      toggleFriendRequests() {
        this.showFriendRequests = !this.showFriendRequests;
      },
      // Method to accept a friend request
      acceptRequest(id) {
    //    let my_userid=2;
        // Implement the logic to accept the friend request with the given ID
        // For example, you can make an API call to accept the request
        axios.get('http://localhost:8080/network/request_accept/'+id+'/'+this.my_userid)
      .then(response => {
        // Update the followers array with the data received from the backend
        this.followers =response.data;
        // window.location.reload();
        console.log(response.data);
      })
      .catch(error => {
        console.error('Error fetching followers:', error);
      });
        console.log(`Accepted friend request with ID: ${id}`);
      },
      FollowBack(id) {
    //    let my_userid=2;
        // Implement the logic to accept the friend request with the given ID
        // For example, you can make an API call to accept the request
        axios.get('http://localhost:8080/network/follow/'+id+'/'+this.my_userid)
      .then(response => {
        // Update the followers array with the data received from the backend
        this.followers =response.data;
        // window.location.reload();
        console.log(response.data);
      })
      .catch(error => {
        console.error('Error fetching followers:', error);
      });
        console.log(`Accepted friend request with ID: ${id}`);
    }
    },
    
    mounted() {
    // Make an HTTP GET request to fetch followers data from the backend
    this.my_userid = this.$route.params.FROM;
      console.log(this.my_userid);
    axios.get('http://localhost:8080/network/my_network/'+this.my_userid)
      .then(response => {
        // Update the followers array with the data received from the backend
        this.friendRequests =response.data;
        console.log(response.data);
      })
      .catch(error => {
        console.error('Error fetching followers:', error);
      });
  }
  };
  </script>
  
  <style scoped>
  /* CSS for sliding animation */
  .friend-icon {
    position: absolute;
    right: 20px; /* Initial position on the right */
    top: 6px; /* Adjust the top position as needed */
    cursor: pointer; /* Change cursor to pointer on hover */
    z-index: 1; /* Ensure it's above the friend requests panel */
  }
  
  .friend-requests {
    position: absolute;
    right: 0; /* Initial position off-screen */
    top: 0;
    width: 300px;
    height: 100%;
    background-color: #f0f0f0;
    overflow-y: auto; /* Enable vertical scrolling if needed */
    z-index: 0; /* Ensure it's behind the friend icon */
  }
  
  /* Styling for friend request list */
  .friend-requests ul {
    list-style-type: none;
    padding: 10;
  }
  
  .friend-requests li {
    padding: 20px;
    border-bottom: 1px solid #ccc;
  }
  
  /* Button styling */
  .friend-requests button {
    margin-left: 10px;
  }
  
  /* Slide transition */
  .slide-enter-active, .slide-leave-active {
    transition: all 0.7s cubic-bezier(1, 2.2, 2,2); /* Speed up at the beginning, then slow down */
  }
  
  .slide-enter, .slide-leave-to {
    left: 0;
  }
  </style>
  