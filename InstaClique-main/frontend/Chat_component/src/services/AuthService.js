import axios from 'axios';

const baseURL = 'http://localhost:8080/api/auth';

export default {
  signup(userData) {
    
    return axios.post(`${baseURL}/signup`, userData);
  },
  login(userData) {
    return axios.post(`${baseURL}/login`, userData);
  }
};
