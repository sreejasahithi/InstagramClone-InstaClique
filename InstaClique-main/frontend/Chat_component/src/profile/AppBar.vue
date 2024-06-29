 <template>
    <div class="app-bar">
        <h1>Appbar</h1>
      <router-link to="/" class="logo">Instagram</router-link>
      <div class="actions">
        <button @click="my_profileshow">My Profile</button>
        <button @click="following_show">Following</button>
        <button @click="followers_show">Followers</button>
        <button @click="block_show">Blocked</button>
        <button @click="function1">Chat</button>
        <button @click="report">Report Account</button>
        <button @click="logout">Logout</button>
         
      </div>
    </div>

    <div >
      


    </div>
  </template>
  
  <script>
  import FeedVue from '../components/FeedVue.vue';
  import MyNetwork from '../Networks/MyNetwork.vue';
  import axios from 'axios';
  export default {
    name: 'AppBar',
    USER:"",
    showFriendRequests: false, // Flag to control visibility of friend requests panel
        friendRequests: [
          "1","2","3"
        ] ,my_userid:"4"
        ,
    
    mounted(){
      this.my_userid = this.$route.params.USER;
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


      console.log("in appbar");
      // console.log(this.USER);
      this.USER = this.$route.params.USER;
      console.log(this.USER);
    // this.To = this.$route.params.To;
    },
    methods: {
      function1(){
        this.$router.push({ name: 'NewChatList', params: { FROM:this.USER}});
      },followers_show(){
        this.$router.push({ name: 'Followers', params: { FROM:this.USER}});
      },following_show(){
        this.$router.push({ name: 'Following', params: { FROM:this.USER}});
      },
      block_show(){
        this.$router.push({ name: 'Blocked', params: { FROM:this.USER}});
      },
      report(){
        
        this.$router.push("/report-account");
      },
      logout() {
        // Logic to handle logout
        console.log('Logged out');
      },
      toggleFriendRequests() {
        this.showFriendRequests = !this.showFriendRequests;
      },my_profileshow(){
        this.$router.push('/uploadimage');
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
    }
  }
  </script>
  
  <style scoped>

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
  