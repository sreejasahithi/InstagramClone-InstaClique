<template>
  <div>
    <form @submit.prevent="submitSearch">
      <div>
        <label>
          Search by:
          <select v-model="searchBy">
            <option value="username">Username</option>
            <option value="userId">User ID</option>
          </select>
        </label>
      </div>
      <div>
        <input type="text" v-model="searchQuery" placeholder="Enter username or user ID">
        <button type="submit">Search</button>
      </div>
    </form>
    <ul v-if="searchResults.length > 0">
      <li v-for="user in searchResults" :key="user.id">
        UserID: {{ user.id }}, Username: {{ user.username }}, Email: {{ user.email }}
      </li>
    </ul>
    <p v-else>No results found.</p>
    <p v-if="errorMessage" class="error-message">{{ errorMessage }}</p>
  </div>
</template>

<script>
import SearchService from '../services/SearchService.js';

export default {
  data() {
    return {
      searchBy: 'username', 
      searchQuery: '',
      searchResults: [],
      errorMessage: ''
    };
  },
  methods: {
    submitSearch() {
      if (this.searchQuery.trim() === '') {
        this.errorMessage = 'Please enter a username or user ID.';
        return;
      }

      if (this.searchBy === 'username') {
        SearchService.searchByUsername(this.searchQuery)
          .then(response => {
            this.searchResults = response.data;
            this.errorMessage = ''; 
          })
          .catch(error => {
            console.error('Error searching users by username:', error);
            this.errorMessage = 'An error occurred while searching users by username. Please try again.'; 
          });
      } else {
        SearchService.searchByUserId(this.searchQuery)
          .then(response => {
            this.searchResults = [response.data]; 
            this.errorMessage = ''; 
          })
          .catch(error => {
            console.error('Error searching users by user ID:', error);
            this.errorMessage = 'An error occurred while searching users by user ID. Please try again.'; 
          });
      }
    }
  }
}
</script>

<style scoped>
.error-message {
  color: red;
}
</style>


