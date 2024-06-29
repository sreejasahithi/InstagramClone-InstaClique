<!--template>
  <div>
    <h2>Disable Account</h2>
    <form @submit.prevent="disableAccount">
      <label for="accountId">Account ID:</label>
      <input type="text" v-model="accountId" id="accountId" required />
      <label for="reason">Reason:</label>
      <textarea v-model="reason" id="reason" required></textarea>
      <button type="submit">Disable Account</button>
    </form>
    <div v-if="message">{{ message }}</div>
  </div>
</template-->


<template>
  <div class="disable-account-container">
    <h2>Disable Account</h2>
    <form @submit.prevent="disableAccount" class="disable-account-form">
      <label for="accountId">Account ID:</label>
      <input type="text" v-model="accountId" id="accountId" class="account-id-input" required />
      <label for="reason">Reason:</label>
      <textarea v-model="reason" id="reason" class="reason-textarea" required></textarea>
      <button type="submit" class="disable-account-button">Disable Account</button>
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
      reason: '',
      message: ''
    };
  },
  methods: {
    disableAccount() {
      GeneralManagerService.disableAccount(this.accountId, this.reason)
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
.disable-account-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-top: 50px;
}

/* Form */
.disable-account-form {
  width: 300px;
}

.account-id-input,
.reason-textarea {
  width: 100%;
  margin-bottom: 10px;
  padding: 10px;
  border: 1px solid #dbdbdb;
  border-radius: 5px;
}

.disable-account-button {
  width: 100%;
  padding: 10px;
  background-color: #db4437;
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