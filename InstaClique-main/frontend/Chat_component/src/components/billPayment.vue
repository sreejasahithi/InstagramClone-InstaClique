<template>
  <div class="payment-page">
    <h1>Payment Details</h1>
    
    <!-- Payment Form -->
    <form @submit.prevent="processPayment">
      <!-- Payment Amount Input -->
      <div class="form-group">
        <label for="amount">Amount:</label>
        <input type="number" id="amount" v-model="paymentAmount" required>
      </div>

      <!-- Credit Card Number Input -->
      <div class="form-group">
        <label for="cardNumber">Card Number:</label>
        <input type="text" id="cardNumber" v-model="cardNumber" required>
      </div>

      <!-- Expiry Date Input -->
      <div class="form-group">
        <label for="expiryDate">Expiry Date (MM/YYYY):</label>
        <input type="text" id="expiryDate" v-model="expiryDate" placeholder="MM/YYYY" required>
      </div>

      <!-- CVV Input -->
      <div class="form-group">
        <label for="cvv">CVV:</label>
        <input type="text" id="cvv" v-model="cvv" required>
      </div>

      <!-- Submit Button -->
      <button @click = submitForm>Process Payment</button>
    </form>

    <!-- Payment Status Message -->
    <div v-if="paymentStatus" class="payment-status">
      <p>{{ paymentStatus }}</p>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      
       paymentAmount:0,
        cardNumber:'',
        expiryDate: '',
        cvv : "",
        paymentStatus: false,
      
      formSubmitted: false
    };
  },

  methods: {
    async submitForm() {
      try {
        const response = await axios.post('http://localhost:8080/api/updateBill', null, {
          params: {
            amt: this.paymentAmount  // Send paymentAmount as amt parameter
          }
        });
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
.payment-page {
  max-width: 600px;
  margin: 0 auto;
  padding: 20px;
  border: 1px solid #ccc;
  border-radius: 5px;
  background-color: #f9f9f9;
}

.form-group {
  margin-bottom: 15px;
}

label {
  font-weight: bold;
}

input {
  width: 100%;
  padding: 8px;
  border: 1px solid #ccc;
  border-radius: 5px;
}

button {
  padding: 10px 20px;
  background-color: #007bff;
  color: #fff;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}

.payment-status {
  margin-top: 20px;
  padding: 10px;
  border: 1px solid #007bff;
  border-radius: 5px;
  background-color: #e7f3ff;
}
</style>
