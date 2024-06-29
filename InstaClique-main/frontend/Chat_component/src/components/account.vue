<template>
  <div class="account-page">
    <h1>User Account Details</h1>

    <div>
      <form @submit.prevent="updateAccount">
        <div>
          <label for="name">Name:</label>
          <input type="text" id="name" v-model="user.name">
        </div>
        <div>
          <label for="email">Email:</label>
          <input type="email" id="email" v-model="user.email">
        </div>
        <div>
          <label for="age">Age:</label>
          <input type="number" id="age" v-model.number="user.age">
        </div>
        <!-- You can add more fields such as address, phone, etc. -->

        <button type="submit">Update</button>
      </form>
    </div>

    <div v-if="isUpdated">
      <p style="color: green;">Account details updated successfully!</p>
    </div>
  </div>
</template>

<script>
export default {
  name: 'AccountPage',
  data() {
    return {
      user: {
        name: 'John Doe',
        email: 'johndoe@example.com',
        age: 30
        // Add more properties like address, phone number, etc.
      },
      isUpdated: false
    };
  },
  methods: {
    updateAccount() {
      // Simulate updating account details (e.g., sending a request to the server)
      // In a real application, you would make an API request here to update user data
      // For demonstration, we just set isUpdated to true after a brief delay
      setTimeout(() => {
        this.isUpdated = true;
      }, 1000);
    }
  }
};
</script>

<style scoped>
.account-page {
  max-width: 600px;
  margin: 0 auto;
  padding: 20px;
  border: 1px solid #ccc;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

h1 {
  text-align: center;
  margin-bottom: 20px;
}

label {
  font-weight: bold;
}

input {
  width: 100%;
  padding: 8px;
  margin-bottom: 10px;
  border: 1px solid #ccc;
  border-radius: 4px;
}

button {
  padding: 10px 20px;
  background-color: #007bff;
  color: #fff;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

button:hover {
  background-color: #0056b3;
}

p {
  text-align: center;
}
</style>
