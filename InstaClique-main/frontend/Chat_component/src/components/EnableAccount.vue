<!--template>
  <div>
    <h2>Enable Account</h2>
    <form @submit.prevent="enableAccount">
      <label for="accountId">Account ID:</label>
      <input type="text" v-model="accountId" id="accountId" required />
      <button type="submit">Enable Account</button>
    </form>
    <div v-if="message">{{ message }}</div>
  </div>
</template-->


<template>
  <div class="enable-account-container">
    <h2>Enable Account</h2>
    <form @submit.prevent="enableAccount" class="enable-account-form">
      <label for="accountId">Account ID:</label>
      <input type="text" v-model="accountId" id="accountId" class="account-id-input" required />
      <button type="submit" class="enable-account-button">Enable Account</button>
    </form>
    <div v-if="message" class="message">{{ message }}</div>
  </div>
</template>
<script>
import GeneralManagerService from '../services/GeneralManagerService.js';

export default {
  data() {
    return {
      accountId: '',
      message: ''
    };
  },
  methods: {
    enableAccount() {
      GeneralManagerService.enableAccount(this.accountId)
        .then(response => {
          this.message = response.data;
        })
        .catch(error => {
          this.message = error.response.data;
        });
    }
  }
};
</script>

<style scoped>
/* Container */
.enable-account-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-top: 50px;
}

/* Form */
.enable-account-form {
  width: 300px;
}

.account-id-input {
  width: 100%;
  margin-bottom: 10px;
  padding: 10px;
  border: 1px solid #dbdbdb;
  border-radius: 5px;
}

.enable-account-button {
  width: 100%;
  padding: 10px;
  background-color: #3897f0;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}

/* Message */
.message {
  margin-top: 10px;
  padding: 10px;
  border-radius: 5px;
  background-color: #c9f4be;
}
</style>