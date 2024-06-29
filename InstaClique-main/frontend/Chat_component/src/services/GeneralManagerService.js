import axios from 'axios';

const API_URL = 'http://localhost:8080/api/general-manager/';

class GeneralManagerService {
  // Enable account by ID
  enableAccount(accountId) {
    return axios.post(`${API_URL}enable-account/${accountId}`);
  }

  // Disable account by ID with reason
  disableAccount(accountId, reason) {
    return axios.post(`${API_URL}disable-account/${accountId}`, reason);
  }

  // Check and disable account by ID if needed
  checkAndDisableAccount(accountId) {
    return axios.post(`${API_URL}check-and-disable-account/${accountId}`);
  }

  reportAccount(accountId) {
    return axios.post(`${API_URL}report-account/${accountId}`)
    .then(response => {
        return response.data;
      })
      .catch(error => {
        throw new Error(error.response.data.message || 'Failed to report account');
      });
  }


  getAccountDetails(accountId) {
    return axios.get(`${API_URL}account-details/${accountId}`)
      .then(response => {
        return response.data;
      })
      .catch(error => {
        throw new Error(error.response.data.message || 'Failed to fetch account details');
      });
  }

  
}

export default new GeneralManagerService();
