<!--template>
  <div>
    <h2>All User IDs</h2>
    <ul>
      <button v-for="userId in userIds" :key="userId" @click.prevent="doNothing">{{ userId }}</button>
    </ul>
  </div>
</template-->
<template>
  <div>
    <h2>All User IDs</h2>
    <ul>
      <li v-for="userId in userIds" :key="userId">
        <div>
          <span>{{ userId }}</span>
          <button @click="followUser(userId)">Follow</button>
        </div>
      </li>
    </ul>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name:"AllAccounts",
  data() {
    return {
      my_userid:"3",
      userIds: []
    };
  },
  created() {
    this.getAllUserIds();
  },
  methods: {
    getAllUserIds() {
      axios.get('http://localhost:8080/api/search/userIds')
        .then(response => {
          this.userIds = response.data;
        })
        .catch(error => {
          console.error('Error fetching user IDs:', error);
        });
    },
    followUser(userId)
    {console.log(userId);
      axios.get('http://localhost:8080/network/follow/'+userId+"/"+this.my_userid)
        .then(response => {
          console.log("Sent request");
          // this.userIds = response.data;
        })
        .catch(error => {
          console.error('Error fetching user IDs:', error);
        });

    }
  }
};
</script>
