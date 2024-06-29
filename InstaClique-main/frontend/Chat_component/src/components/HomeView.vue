<template>
  <div class="home-page">
    <div class="container">
      <div class="row justify-content-center">
        <div class="col-md-6">
          <div class="text-center mb-4">
            <h1 style="color: white;">Welcome to Our Website</h1>
            <p class="lead" style="color: white">Explore our services and features.</p>
          </div>

          <div class="card">
            <div class="card-header" style="background-color:#FC402D;">
              <h3>Sign In</h3>
            </div>
            <div class="card-header" style="background-color: #FDC0B2;" >
              
              <form @submit.prevent="signIn">
                <div class="mb-3">
                  <label for="email" class="form-label">Email:</label>
                  <input type="email" id="email" class="form-control" v-model="signInData.email" required>
                </div>
                <div class="mb-3">
                  <label for="password" class="form-label">Password:</label>
                  <input type="password" id="password" class="form-control" v-model="signInData.password" required>
                </div>
                <button type="submit" class="btn btn-primary">Sign In</button>
              </form>
            </div>
          </div>

          <div class="text-center mt-4">
            <p>Don't have an account? <router-link to="/signup">Sign Up</router-link></p>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      signInData: {
        email: '',
        password: ''
      }
    };
  },
  methods: {
    signIn() {
      // Implement sign-in logic here
      console.log('Signing in with:', this.signInData);
      // Example: Send sign-in request to backend using Axios
    }
  }
};
</script>

<style scoped>
.home-page {
  /* Set background image */
  background-image: url('https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTZvcIqbcVj421djMFpGdMwh8Ii9QH7VuvAGA&s');
  background-size: cover; /* Cover the entire container */
  background-position: center; /* Center the image */
  min-height: 100vh; /* Minimum height of the viewport */
  display: flex; /* Flex container to center content */
  justify-content: center; /* Center content horizontally */
  align-items: center; /* Center content vertically */
}

.card {
  background-color: rgba(255, 255, 255, 0.9); /* Semi-transparent white background for card */
  border-radius: 10px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.card-header {
  background-color: #007bff; /* Bootstrap primary color */
  color: #fff;
  padding: 10px;
  border-top-left-radius: 10px;
  border-top-right-radius: 10px;
}

.card-body {
  padding: 20px;
}

.btn-primary {
  background-color: #007bff; /* Bootstrap primary color */
  border-color: #007bff;
}

.btn-primary:hover {
  background-color: #0056b3; /* Darker shade on hover */
  border-color: #0056b3;
}
</style>
