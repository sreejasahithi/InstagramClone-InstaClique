<template>
    <div class="profile">
        <h1>PROFILE </h1>
      <AppBar></AppBar>
    </div>
  </template>
  
  <script>
  import AppBar from "./AppBar.vue";
  export default {
    name: 'Profile',
    methods: {
      logout() {
        // Logic to handle logout
        console.log('Logged out');
      }
    }
  }
  </script>
  
  <style scoped>
  .app-bar {
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 10px 20px;
    background-color: #fafafa;
  }
  
  .logo {
    font-size: 1.5rem;
    font-weight: bold;
    text-decoration: none;
    color: #333;
  }
  
  .actions {
    display: flex;
    align-items: center;
  }
  
  button {
    margin-left: 10px;
    padding: 8px 12px;
    border: none;
    border-radius: 5px;
    background-color: #3897f0;
    color: white;
    cursor: pointer;
    text-decoration: none;
  }
  
  .button {
    margin-left: 10px;
    padding: 8px 12px;
    border: none;
    border-radius: 5px;
    background-color: #fafafa;
    color: #333;
    cursor: pointer;
    text-decoration: none;
  }
  
  .button:hover {
    background-color: #f1f1f1;
  }
  </style>
  