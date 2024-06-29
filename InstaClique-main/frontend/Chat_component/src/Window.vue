<template>
    <div id="app">
      <MyCustomChatWindow />
    </div>
  </template>
  
  <script>
//   import MyCustomChatWindow from "./MyCustomChatWindow.vue"; // Assuming MyCustomChatWindow.vue is in the components directory
  
  export default {
    name: "Window",
    components: {
      MyCustomChatWindow
    }
  };
  </script>
  
  <style>
  #app {
    font-family: Avenir, Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #2c3e50;
    margin-top: 60px;
  }
  </style>
  