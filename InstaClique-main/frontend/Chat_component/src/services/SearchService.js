
import axios from 'axios';

const baseURL = 'http://localhost:8080/api/search'; // Adjust the base URL here

const SearchService = {
  searchByUsername(username) {
    return axios.get(`${baseURL}/username/${username}`); // Append username to search by username
  },
  searchByUserId(userId) {
    return axios.get(`${baseURL}/userid/${userId}`); // Append userId to search by user ID
  }
};

export default SearchService;

