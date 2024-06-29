<template>
  <div class="container">
    <h2>Choose Account Type</h2>
    <select v-model="selectedAccountType" class="select">
      <option value="user">User</option>
      <option value="admin">Admin</option>
    </select>
    <button @click="submit" class="submit-button">Submit</button>
  </div>
</template>

<script>
export default {
  data() {
    return {
      selectedAccountType: '' // Default selection will be set by the component using props
    };
  },
  methods: {
    submit() {
    // Determine the route based on the selected account type
    let route;
    if (this.selectedAccountType === 'user') {
      route = '/signup';
    } else if (this.selectedAccountType === 'admin') {
      route = '/admin';
    } else {
      // Handle other cases or throw an error if necessary
      console.error('Invalid account type selected');
      return;
    }
    // Navigate to the determined route
    this.$router.push(route);
  }
  },
  mounted() {
    // Set the default selection based on the prop passed to the component
    this.selectedAccountType = this.defaultSelectedAccountType;
  },
  props: {
    defaultSelectedAccountType: {
      type: String,
      default: 'user' // Default value if prop is not provided
    }
  }
};
</script>

<style scoped>
.container {
  text-align: center;
  margin-top: 20px;
  background-color: #ff7eb9; /* Instagram-like background color */
  height: 100vh; /* Ensure the container covers the entire viewport height */
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

.select {
  padding: 10px;
  font-size: 16px;
  border-radius: 5px;
  border: 1px solid #ccc;
  margin-bottom: 10px;
}

.submit-button {
  padding: 10px 20px;
  font-size: 16px;
  background-color: #3498db;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

.submit-button:hover {
  background-color: #2980b9;
}
</style>