<template>
  <div>
    <h2>Account Details</h2>
    <label for="accountId">Enter Account ID:</label>
    <input type="text" v-model="accountId" id="accountId" required />
    <button @click="getAccountDetails">Get Account Details</button>

    <div v-if="account">
      <h3>Account Information</h3>
      <p>Username: {{ account.username }}</p>
      <p>Email: {{ account.email }}</p>
      
    </div>

    
  </div>
</template>


<script>

import GeneralManagerService from '../services/GeneralManagerService.js';
export default {
  data() {
    return {
      accountId: '',
      account: null,
      keyword: '',
      searchResults: [],
    };
  },
  methods: {
    async getAccountDetails() {
      try {
        const response = await GeneralManagerService.getAccountDetails(this.accountId);
        this.account = response;
      } catch (error) {
        console.error('Error fetching account details:', error);
        this.account = null;
      }
    }
  },
};
</script>
