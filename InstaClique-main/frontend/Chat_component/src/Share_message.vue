<template>
    <div class="align">
    <div class="chat-list">
      <div class="search-bar">
        <input
          type="text"
          v-model="searchTerm"
          placeholder="Search chats..."
          @keyup="filterChats"
        />
        <h1>In sharing message with followers</h1>
        
      <button @click="toggleFollowersList" :class="{ active: showFollowersList }"><img :src="' https://www.pngplay.com/wp-content/uploads/5/Plus-Symbol-Download-Free-PNG.png'" alt="NONE" height="25px" width="25px" />
                     
        <!-- <i v-if="!showFollowersList" class="fas fa-plus"></i> -->
        <!-- <i v-else class="fas fa-times"></i> -->
      </button>
      <div class="Followers_show">
      <ul class="followers-list" v-show="showFollowersList">
        
        <li v-for="follower in following" :key="follower">
          
          <button @click="sendmessage(follower) " > Share to {{ follower }} </button>
        
        </li>
  
      </ul>
      </div>
      </div>
      </div>
  </div>
  
    
  </template>
  <script>
  import axios from "axios";
  
  export default {
    name: "NewChats",
    data() {
      return {
        Chat:{}, // Array to store chat messages
        message: "", // Input message
        file: null, // Selected file
        From: "3", // Sender ID
        To: "4", // Receiver ID
        followers:[],
      
      };
    },
    mounted() {
      console.log(this.$route);
      this.From = this.$route.params.From;
    //   this.To = this.$route.params.To;
  
      console.log("From User ID:", this.From);
      console.log("To User ID:", this.Chat);
    //   this.getChatData();
    this.getFollowersData();
    },
    methods: {
        getFollowersData() {
        axios.get(`http://localhost:8080/followers/`+this.From)
          .then(response => {
            this.following = response.data;
            console.log("FOLLOW_share");
            console.log(this.following);
          })
          .catch(error => {
            console.error('Error fetching chat data:', error);
          });
      },
      getChatData() {
        axios
          .get(`http://localhost:8080/api/chat/${this.To}/${this.From}`)
          .then((response) => {
            this.chats = response.data;
          })
          .catch((error) => {
            console.error("Error fetching chat data:", error);
          });
      },
      formatDate(dateTimeString) {
      const dateTime = new Date(dateTimeString);
      const options = { year: 'numeric', month: 'short', day: 'numeric', hour: 'numeric', minute: 'numeric', hour12: true };
      return dateTime.toLocaleString('en-US', options);
    },
      sendMessage(follower) {
      console.log(new Date().toLocaleString());
        this.chat.to_id=follower;
        axios
          .post("http://localhost:8080/api/chat", Chat)
          .then((response) => {
            console.log("Message sent successfully");
  
            // this.getChatData();

            this.message = "";
            this.file = null;
            this.$refs.fileInput.value = "";
          })
          .catch((error) => {
            console.error("Error sending message:", error);
          });
      },
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
      deleteMessage(from,time,to) {
        console.log(from,time,to);
        axios
          .get(`http://localhost:8080/api/deletechat/${to}/${from}/${time}`)
          .then((response) => {
            this.chats = response.data;
            console.log("DONE DELETION");
            window.location.reload();
          })
          .catch((error) => {
            console.error("Error fetching chat data:", error);
          });
        
      },
    },
  };
  </script>
  
  <style scoped>
  /* Styles for chat list */
  </style>
  
  
  <style scoped>
  .align{
    align-content: justify;
    align-items: justify;
  }
  
  
  .custom-link,
  .custom-link:focus,
  .custom-link:hover,
  .custom-link:active{
    text-decoration: none !important; /* Remove underline */
    color: inherit; /* Inherit text color */
  }
  .active {
    color: red; /* Change color when active */
  }
  .followinguser{
    background-color: aquamarine;
  }
  
  .followers-list {
    position:absolute;
    top:50px;
    left:0;
    background-color: #f5f5f5;
    border-radius: 5px;
    padding: 10px;
    
    box-shadow: 0 0 2px rgba(0, 0, 0, 0.1);
    margin-bottom: 15px;
    width: 850px;
    z-index: 999; /* Ensure it's above other content */
  }
  
  
  .followers-list button {
    top:100px;
    background-color: #b58484;
    border-radius: 5px;
    padding: 10px;
    box-shadow: 0 0 2px rgba(0, 0, 0, 0.1);
    margin-bottom: 15px;
    height:45px;
    width: 850px;
  }
  
  .followers-list button:hover {
    background-color: #46ddee; /* Background color on hover */
  }
  
  .followers-list button.following_user {
    background-color: aquamarine; /* Background color for following users */
  }
  .fa-plus::before {
    content: "\f067"; /* FontAwesome plus icon */
  }
  
  .fa-times::before {
    content: "\f00d"; /* FontAwesome times icon */
  }
  
  
  .chat-list {
    top:100px;
    background: linear-gradient(to left, #86baea, #d6e8e9);;
    border-radius: 5px;
    padding: 10px;
    box-shadow: 0 0 2px rgba(0, 0, 0, 0.1);
    margin-bottom: 15px;
    width: 850px;
    
  }
  
  .search-bar {
    display: flex;
    align-items: center;
    padding: 5px 10px;
    border-radius: 5px;
    background-color: #e8e8e8;
    margin-bottom: 10px;
  }
  
  .search-bar input {
    flex: 1;
    border: none;
    padding: 5px;
    font-size: 16px;
  }
  
  .search-bar .search-icon {
    margin-left: 5px;
    color: #ccc;
    cursor: pointer;
  }
  
  .chat-items {
    list-style: none;
    padding: 0;
    margin: 0;
  }
  
  .chat-item {
    display: flex;
    align-items: center;
  }
  .chat-name{
    color:rgb(61, 126, 180);
    position:relative;
    text-shadow:true;
    margin-left:340px;
  }
  </style>
  