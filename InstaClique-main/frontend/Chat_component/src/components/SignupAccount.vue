<!--template>
  <div>
    <h2>Sign Up</h2>
    <form @submit.prevent="signup">
      <input type="text" v-model="id" placeholder="ID" required />
      <input type="text" v-model="username" placeholder="Username" required />
      <input type="email" v-model="email" placeholder="Email" required />
      <input type="password" v-model="password" placeholder="Password" required />
      <button type="submit">Sign Up</button>
    </form>
    <div v-if="successMessage">{{ successMessage }}</div>
    <div v-if="errorMessage">{{ errorMessage }}</div>
  </div>
</template-->

<template>
  <div class="signup-container">
    <h2>Sign Up</h2>
    <form @submit.prevent="signup" class="signup-form">
      <input type="text" v-model="id" placeholder="User Id" required />
      <input type="text" v-model="username" placeholder="Username" required />
      <input type="email" v-model="email" placeholder="Email" required />
      <input type="password" v-model="password" placeholder="Password" required />
      <button type="submit" class="signup-button">Sign Up</button>
    </form>
    <div v-if="successMessage" class="success-message">{{ successMessage }}</div>
    <div v-if="errorMessage" class="error-message">{{ errorMessage }}</div>
  </div>
</template>
<script>
import AuthService from '../services/AuthService.js';
import axios from 'axios';
export default {
  data() {
    return {
      id : '',
      username: '',
      email: '',
      password: '',
      successMessage: '',
      errorMessage: '',
      
    };
  },
  methods: {
    signup() {            

      AuthService.signup({
        id : this.id,
        username: this.username,
        email: this.email,
        password: this.password
      }).then(response => {
        const userData = response.data;
        const userString = `Signed up successfully: {username: '${userData.username}', email: '${userData.email}', password: '${userData.password}'}`;
        this.successMessage = userString
        this.errorMessage = '';
        console.log('Signed up successfully:', response.data);
        
      }).catch(error => {
        if (error.response.data.message) {
      const userString = `Error signing up: {message: '${error.response.data.message}'}`;
      this.errorMessage = userString;
    } else {
      const userString = `Error signing up: ${JSON.stringify(error.response.data)}`;
      this.errorMessage = userString;
    }
        this.successMessage = '';
        console.error('Error signing up:', error.response.data);
      });
    }
  }
};
</script>

<style scoped>
/* Signup Container */
.signup-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-top: 50px;
}

/* Signup Form */
.signup-form {
  width: 300px;
}

.signup-form input {
  width: 100%;
  margin-bottom: 10px;
  padding: 10px;
  border: 1px solid #dbdbdb;
  border-radius: 5px;
}

.signup-button {
  width: 100%;
  padding: 10px;
  background-color: #3897f0;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}

/* Messages */
.success-message,
.error-message {
  margin-top: 10px;
  padding: 10px;
  border-radius: 5px;
}

.success-message {
  background-color: #c9f4be;
}

.error-message {
  background-color: #fbb0a5;
}
</style>