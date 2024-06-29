<template>
  <div class="container">
    <div class="row justify-content-center">
      <div class="col-md-8">
        <div class="card">
          <div class="card-header">
            <h2 class="text-center">Submit Form</h2>
          </div>
          <div class="card-body">
            <form @submit.prevent="submitForm">
                <div class="mb-3">
                <label for="UserId" class="form-label">UserID:</label>
                <input type="text" id="UserId" class="form-control" v-model="formData.UserId" required>
              </div>
              <div class="mb-3">
                <label for="Username" class="form-label">Username:</label>
                <input type="text" id="Username" class="form-control" v-model="formData.Username" required>
              </div>
              <div class="mb-3">
                <label for="password" class="form-label">password:</label>
                <input type="password" id="password" class="form-control" v-model="formData.password" required>
              </div>
              <div class="mb-3">
                <label for="type" class="form-label">type:</label>
                <textarea id="type" class="form-control" v-model="formData.type" rows="4" required></textarea>
              </div>
            
              <div class="text-center">
                <button type="submit" class="btn btn-primary">Submit</button>
              </div>
            </form>
            <div v-if="formSubmitted" class="mt-3">
              <div class="alert alert-success" role="alert">
                <h3 class="alert-heading">Form Submitted!</h3>
                <p>Thank you for submitting the form.</p>
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
// import 'bootstrap/dist/css/bootstrap.min.css';


export default {
  data() {
    return {
      formData: {
        UserId:'',
        type:'',
        Username: '',
        password: '',
     
      },
      formSubmitted: false
    };
  },
  methods: {
    async submitForm() {
      try {
        const response = await axios.post('http://localhost:8080/api/submit-form', 
       this.formData,
        
        );
        console.log(this.formData.Username,this.formData.password);
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
