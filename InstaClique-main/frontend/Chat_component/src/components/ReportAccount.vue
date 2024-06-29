<!--template>
  <div>
    <h2>Report Account</h2>
    <form @submit.prevent="report">
      <input type="text" v-model="accountId" placeholder="Account ID" required />
      <button type="submit">Report Account</button>
    </form>
    <div v-if="successMessage">{{ successMessage }}</div>
    <div v-if="errorMessage">{{ errorMessage }}</div>
  </div>
</template>

<script>
import GeneralManagerService from '@/services/GeneralManagerService.js';

export default {
  data() {
    return {
      accountId: '',
      successMessage: '',
      errorMessage: ''
    };
  },
  methods: {
    report() {
      GeneralManagerService.reportAccount(this.accountId)
        .then(() => {
          this.successMessage = 'Account reported successfully';
          this.errorMessage = '';
        })
        .catch(error => {
          this.errorMessage = error.message || 'Failed to report account';
          this.successMessage = '';
        });
    }
  }
};
</script -->

<!--template>
  <div class="report-account">
    <h2>Report Account</h2>
    <form @submit.prevent="report">
      <div class="form-group">
        <label for="accountId">Account ID</label>
        <input type="text" v-model="accountId" id="accountId" class="form-control" required />
      </div>
      <div class="form-group">
        <label for="reason">Reason for Reporting</label>
        <textarea v-model="reason" id="reason" class="form-control" required></textarea>
      </div>
      <div class="form-group">
        <label for="category">Select Category</label>
        <select v-model="category" id="category" class="form-control" required>
          <option value="">Select</option>
          <option value="spam">Spam or fake account</option>
          <option value="harassment">Harassment or bullying</option>
          <option value="inappropriate">Inappropriate content</option>
          <option value="other">Other</option>
        </select>
      </div>
        <button type="submit" class="btn btn-primary">Report Account</button>
    </form>
    <div v-if="successMessage" class="alert alert-success">{{ successMessage }}</div>
    <div v-if="errorMessage" class="alert alert-danger">{{ errorMessage }}</div>
  </div>
</template-->

<template>
  <div class="report-account">
    <h2>Report Account</h2>
    <form @submit.prevent="report" class="report-form">
      <div class="form-group">
        <label for="accountId">Account ID</label>
        <input type="text" v-model="accountId" id="accountId" class="form-control" required />
      </div>
      <div class="form-group">
        <label for="reason">Reason for Reporting</label>
        <textarea v-model="reason" id="reason" class="form-control" required></textarea>
      </div>
      <div class="form-group">
        <label for="category">Select Category</label>
        <select v-model="category" id="category" class="form-control" required>
          <option value="">Select</option>
          <option value="spam">Spam or fake account</option>
          <option value="harassment">Harassment or bullying</option>
          <option value="inappropriate">Inappropriate content</option>
          <option value="other">Other</option>
        </select>
      </div>
      <button type="submit" class="btn btn-primary">Report Account</button>
    </form>
    <div v-if="successMessage" class="alert alert-success">{{ successMessage }}</div>
    <div v-if="errorMessage" class="alert alert-danger">{{ errorMessage }}</div>
  </div>
</template>

<script>
import GeneralManagerService from '../services/GeneralManagerService.js';

export default {
  data() {
    return {
      accountId: '',
      reason: '',
      category: '',
      evidence: null,
      successMessage: '',
      errorMessage: ''
    };
  },
  methods: {
    report() {
      GeneralManagerService.reportAccount(this.accountId)
        .then(() => {
          this.successMessage = 'Account reported successfully';
          this.errorMessage = '';
        })
        .catch(error => {
          this.errorMessage = error.message || 'Failed to report account';
          this.successMessage = '';
        });
    }
  }
};
</script>

<style scoped>
/* Container */
.report-account {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-top: 50px;
}

/* Form */
.report-form {
  width: 300px;
}

.form-group {
  margin-bottom: 15px;
}

.form-control {
  width: 100%;
  padding: 10px;
  border: 1px solid #ced4da;
  border-radius: 5px;
}

.btn {
  width: 100%;
  padding: 10px;
  background-color: #007bff;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}

/* Alerts */
.alert {
  width: 100%;
  margin-top: 10px;
  padding: 10px;
  border-radius: 5px;
}

.alert-success {
  background-color: #d4edda;
  color: #155724;
}

.alert-danger {
  background-color: #f8d7da;
  color: #721c24;
}
</style>