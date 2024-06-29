<!--template>
  <div>
    <h2>Login</h2>
    <form @submit.prevent="login">
      <input type="text" v-model="id" placeholder="ID" required />
      <input type="text" v-model="username" placeholder="Username" required />
      <input type="password" v-model="password" placeholder="Password" required />
      <button type="submit">Login</button>
    </form>
    <div v-if="successMessage">{{ successMessage }}</div>
    <div v-if="errorMessage">{{ errorMessage }}</div>
  </div>
</template-->


<template>
  <div class="login-container">
    <h2>Login</h2>
    <form @submit.prevent="login" class="login-form">
      <input type="text" v-model="id" placeholder="User id" required />
      <input type="text" v-model="username" placeholder="Username" required />
      <input type="password" v-model="password" placeholder="Password" required />
      <button type="submit" class="login-button">Log In</button>
    </form>
    <div v-if="successMessage" class="success-message">{{ successMessage }}</div>
    <div v-if="errorMessage" class="error-message">{{ errorMessage }}</div>
  </div>
</template>
<script>
// import {userid}  from "../Global_variable.js";
import AuthService from '../services/AuthService.js';
export default {
  
  data() {
    return {
      id : '',
      username: '',
      password: '',
      successMessage: '',
      errorMessage: '',
      
    };
  },
  methods: {
    login() {
      // userid=this.id;
      AuthService.login({
        id : this.id,
        username: this.username,
        password: this.password
      }).then(response => {
        const userData = response.data;
        const userString = `Logged in successfully: {username: '${userData.username}', password: '${userData.password}'}`;
        this.successMessage = userString
        this.errorMessage = '';
        console.log('Logged in successfully:', response.data);
        // this.$router.push('/your-account');
        this.$router.push({ name: 'AppBar', params: { USER:this.id}});

      }).catch(error => {
        if (error.response.data.message) {
      const userString = `Error logging in: {message: '${error.response.data.message}'}`;
      this.errorMessage = userString;
    } else {
      const userString = `Error logging in: ${JSON.stringify(error.response.data)}`;
      this.errorMessage = userString;
    }
        this.successMessage = '';
        console.error('Error logging in:', error.response.data);
      });
    }
  }
};
</script>

<style scoped>
/* Login Container */
.login-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-top: 50px;
}

/* Login Form */
.login-form {
  width: 300px;
}

.login-form input {
  width: 100%;
  margin-bottom: 10px;
  padding: 10px;
  border: 1px solid #dbdbdb;
  border-radius: 5px;
}

.login-button {
  width: 100%;
  padding: 10px;
  background-color: #3897f0;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}

/* Messages */
.success-message, .error-message {
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