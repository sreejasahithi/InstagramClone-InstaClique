<template>
  <div class="container">
    <div class="row justify-content-center">
      <div class="col-md-8">
        <div class="card">
          <div class="card-header">
            <h2 class="text-center">Business Account Details</h2>
          </div>
          <div class="card-body">
            <form @submit.prevent="submitForm">
              <div class="mb-3">
                <label for="CompanyName" class="form-label">Company Name:</label>
                <input type="text" id="CompanyName" class="form-control" v-model="formData.company" required>
              </div>
              <div class="mb-3">
                <label for="Username" class="form-label">Username:</label>
                <input type="text" id="Username" class="form-control" v-model="formData.Username" required>
              </div>
              <div class="mb-3">
                <label for="password" class="form-label">Password:</label>
                <input type="password" id="password" class="form-control" v-model="formData.password" required>
              </div>
              <div class="text-center">
                <button type="submit" class="btn btn-primary">Submit</button>
              </div>
            </form>
            <div v-if="formSubmitted" class="mt-3">
              <div class="alert alert-success" role="alert">
                <h3 class="alert-heading">Form Submitted!</h3>
                <p>Your Account has been created!</p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      formData: {
        company: '',
        Username: '',
        password: '',
        type:'business',
        bill:1000,
        UserId : "2"
      },
      formSubmitted: false
    };
  },
  methods: {
    async submitForm() {
      try {
        const response = await axios.post('http://localhost:8080/api/busAcc', this.formData);
        console.log('Form submitted successfully:', response.data);
        this.formSubmitted = true;
      } catch (error) {
        console.error('Error submitting form:', error);
        // Handle error (e.g., show error message to user)
      }
    }
  }
};
</script>

<style scoped>
/* Add custom CSS styles if needed */
</style>
