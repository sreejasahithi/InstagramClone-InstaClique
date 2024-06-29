<template>
    <div class="container">
      <div v-if="!showSearchBox" class="users">
        <ul>
          <li v-for="user in users" :key="user.id">
            {{ user.name }}
          </li>
        </ul>
      </div>
      <button class="add-button" @click="toggleSearchBox">+</button>
      <div v-if="showSearchBox" class="search-box">
        <input type="text" v-model="searchQuery" placeholder="Search followers">
        <ul>
          <li v-for="user in filteredUsers" :key="user.id" @click="addUser(user.id)">
            {{ user.name }}
          </li>
        </ul>
      </div>
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        users: [
          { id: 1, name: 'User 1' },
          { id: 2, name: 'User 2' },
          { id: 3, name: 'User 3' },
          { id: 4, name: 'User 4' },
          { id: 5, name: 'User 5' },
        ],
        searchQuery: '',
        showSearchBox: false,
      };
    },
    computed: {
      filteredUsers() {
        return this.users.filter(user =>
          user.name.toLowerCase().includes(this.searchQuery.toLowerCase())
        );
      }
    },
    methods: {
      addUser(userId) {
        // Perform the action to add the user from followers
        console.log('Adding user with ID: ' + userId);
        // You can make an AJAX call here to add the user from followers
        // For demonstration, let's log the user ID to the console
      },
      toggleSearchBox() {
        this.showSearchBox = !this.showSearchBox;
      }
    }
  };


  
  </script>
  
  <style scoped>
 /* Overall container styling */
.container {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 400px; /* Adjust width as needed */
  margin: 0 auto; /* Center horizontally */
  border-radius: 5px; /* Add subtle rounded corners */
  background: linear-gradient(to right, #f4c3d1, #ffffff); /* Light gradient background */
  box-shadow: 0 1px 2px rgba(0, 0, 0, 0.1); /* Subtle box shadow for depth */
  padding: 20px; /* Add some padding inside */
  animation: container-glow 2s ease-in-out infinite alternate; /* Subtle glowing animation */
}

@keyframes container-glow {
  from {
    box-shadow: 0 0 0 rgba(0, 0, 0, 0.1);
  }
  to {
    box-shadow: 0 0 5px rgba(0, 0, 0, 0.2);
  }
}

/* Users list styling */
.users {
  width: 100%; /* Take full width */
  list-style: none; /* Remove default bullet points */
  padding: 0; /* Remove default padding */
  margin: 0; /* Remove default margin */
}

.users li {
  margin-bottom: 10px; /* Space between users */
  font-weight: bold; /* Bold user names */
  cursor: pointer; /* Indicate hover interaction */
  animation: user-list-item-fade 0.5s ease-in-out; /* Subtle fade-in animation */
}

@keyframes user-list-item-fade {
  from {
    opacity: 0;
  }
  to {
    opacity: 1;
  }
}

/* Add button styling */
.add-button {
  width: 40px; /* Fixed button width */
  height: 40px; /* Fixed button height */
  border-radius: 50%; /* Circular button */
  background-color: #ff20aa; /* Instagram blue */
  color: #ffffff; /* White text */
  border: none; /* Remove border */
  cursor: pointer; /* Indicate hover interaction */
  font-size: 20px; /* Larger font for '+' symbol */
  text-align: center; /* Center text */
  line-height: 40px; /* Vertical centering */
  margin-top: 20px; /* Space above button */
  margin-bottom: 10px; /* Space below button */
  transition: background-color 0.2s ease; /* Smooth color transition */
  animation: button-pulse 1s ease-in-out infinite alternate; /* Pulsating animation */
}

@keyframes button-pulse {
  from {
    transform: scale(1);
  }
  to {
    transform: scale(1.1);
  }
}

.add-button:hover {
  background-color: #f11364; /* Darker blue on hover */
}

/* Search box styling */
.search-box {
  width: 100%; /* Take full width */
  margin-top: 10px; /* Space above search box */
}

.search-box input {
  width: 100%; /* Full width input field */
  padding: 10px;
  border: 1px solid #e020e6; /* Light gray border */
  border-radius: 3px; /* Rounded corners */
  box-sizing: border-box; /* Include padding in width */
  font-size: 14px; /* Standard font size */
  animation: input-glow 1s ease-in-out infinite alternate; /* Subtle glowing animation for the input field */
}

@keyframes input-glow {
  from {
    box-shadow: 0 0 0 rgba(0, 0, 0, 0);
  }
  to {
    box-shadow: 0 0 3px rgba(255, 10, 96, 0.971); /* Blue glow */
  }}
  </style>
  

