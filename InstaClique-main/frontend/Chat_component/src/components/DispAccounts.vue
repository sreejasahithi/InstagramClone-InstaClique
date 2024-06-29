<template>
  <div>
    <h1>Accounts</h1>
    <ul>
      <li v-for="account in accounts" :key="account.id">
        <p>{{ account.username }}</p>
        <p>{{ account.email }}</p>
        <!-- Add other fields as needed -->
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  data() {
    return {
      accounts: []
    };
  },
  mounted() {
    this.fetchAccounts();
  },
  methods: {
    async fetchAccounts() {
      try {
        const response = await fetch("/api/accounts");
        if (!response.ok) {
          throw new Error("Failed to fetch accounts");
        }
        this.accounts = await response.json();
      } catch (error) {
        console.error(error);
      }
    }
  }
};
</script>

<style scoped>
/* Add your CSS styles here */
</style>