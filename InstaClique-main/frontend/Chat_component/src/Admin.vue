<template>
  <div class="container">
    <h2>Admin</h2>
    <button @click="enableAccount" class="button">Enable Account</button>
    <button @click="disableAccount" class="button">Disable Account</button>
  </div>
</template>

<script>
export default {
  name:"Admin" ,
  methods: {
    enableAccount() {
      this.$router.push('/enable-account');
    },
    disableAccount() {
      this.$router.push('/disable-account');
    }
  }
};
</script>

<style scoped>
.container {
  text-align: center;
  margin-top: 20px;
}

.button {
  padding: 10px 20px;
  font-size: 16px;
  background-color: #3498db;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s ease;
  margin: 10px; /* Added margin between buttons */
}

.button:hover {
  background-color: #2980b9;
}
</style>
