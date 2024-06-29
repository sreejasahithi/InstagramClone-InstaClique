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

      
    <button @click="toggleFollowersList" :class="{ active: showFollowersList }"><img :src="' https://www.pngplay.com/wp-content/uploads/5/Plus-Symbol-Download-Free-PNG.png'" alt="NONE" height="25px" width="25px" />
                   
      <!-- <i v-if="!showFollowersList" class="fas fa-plus"></i> -->
      <!-- <i v-else class="fas fa-times"></i> -->
    </button>
    <div class="Followers_show">
    <ul class="followers-list" v-show="showFollowersList">
      
      <li v-for="follower in following" :key="follower">
        
        <button @click="addUserToChat(follower) " > Add {{ follower }} to chat</button>
      
      </li>

    </ul>
    </div>
</div>

      <span class="search-icon" @click="filterChats">🔍</span> <!-- Search icon -->
    </div>
    <ul class="chat-items">
      <li v-for="chat in chats" :key="chat">
        <router-link :to="{'name': 'NewChats','params': {From :From, To :chat } }" class="custom-list">
        <div class="chat-item">
          <!-- <img :src="chat.avatar" alt="Avatar" height="30px"/> -->
          <div class="chat-info">
            <p class="chat-name" >{{ chat }}</p>
            <!-- <p class="chat-last-message" v-if="chat.lastMessage"> -->
              <!-- {{ chat.lastMessage }}
            </p> -->
          </div>
        </div>
      </router-link>
      </li>
    </ul>
  </div>
</template>

<script>
import axios from 'axios';

import {userid} from "./Global_variable.js";
export default {
  name: "ChatList",
  data() {
    return {
      searchTerm: "",
      chats: [
      
      ],
      From:"1",
      showFollowersList: false,
      following: [ 
      ]
    };
  },
  computed: {
    filteredChats() {
      return this.chats.filter((chat) =>
        chat(this.searchTerm)
      );
    },
  },
  methods: {
    filterChats() {
      // No additional logic needed here, handled by computed property
    },
    toggleNewChatList() {
      // this.content="Unshow";
      this.showNewChatList = !this.showNewChatList;
      // this.chats.append()
    },
    startNewChat(user) {
      console.log("Starting new chat with:", user);
      
      window.location.reload();
    },
    toggleFollowersList() {
      this.showFollowersList = !this.showFollowersList;
    },
    addUserToChat(user) {
      // Implement logic to add user to chat
      // this.chats.push(user);
      axios.get(`http://localhost:8080/api/chatlist/`+user+"/"+this.From)
          .then(response => {
            // console.log(response);
            this.chats = response.data;
            console.log("CHAT list updated");
            console.log(this.chats);
            
          })
          .catch(error => {
            console.error('Error fetching chat data:', error);
          });
      window.location.reload();
      // You can emit an event here to inform the parent component
    },
    getChatData() {
        axios.get(`http://localhost:8080/api/chatlist/`+this.From)
          .then(response => {
            // console.log(response);
            this.chats = response.data;
            console.log("CHATS:");
            console.log(this.chats);
            
          })
          .catch(error => {
            console.error('Error fetching chat data:', error);
          });
      },
      getFollowersData() {
        axios.get(`http://localhost:8080/following/`+this.From)
          .then(response => {
            this.following = response.data;
            console.log("FOLLOW Data");
            console.log(this.following);
          })
          .catch(error => {
            console.error('Error fetching chat data:', error);
          });
      },

  },
  mounted(){
 
      console.log("in new chatlist");
      // console.log(this.FROM);
      this.From = this.$route.params.FROM;
      console.log(this.FROM);
    this.getChatData();
    this.getFollowersData();
  }
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
