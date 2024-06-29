import { createRouter, createWebHistory } from 'vue-router'
import SearchAccount from '../components/SearchAccount.vue'
import Signup from '../components/SignupAccount.vue'
import Login from '../components/LoginAccount.vue'
import YourAccount from '../components/YourAccount.vue'
import EnableAccount from '../components/EnableAccount.vue'
import DisableAccount from '../components/DisableAccount.vue'
import ReportAccount from '../components/ReportAccount.vue'
import AccountManagement from '../components/AccountManagement.vue'

const routes = [
    {
        path: '/search',
        name: 'SearchAccount',
        component: SearchAccount
    },
    {
      path: '/signup',
      name: 'Signup',
      component: Signup
    },
    {
      path: '/login',
      name: 'Login',
      component: Login
    },
    {
      path: '/your-account',
      name: 'YourAccount',
      component: YourAccount
    },
    {
      path: '/enable-account',
      name: 'EnableAccount',
      component: EnableAccount
    },
    {
      path: '/disable-account',
      name: 'DisableAccount',
      component: DisableAccount
    },
    {
      path: '/report-account',
      name: 'ReportAccount',
      component: ReportAccount
    },
    {
      path: '/account-management',
      name: 'AccountManagement',
      component: AccountManagement
    }
  ]
  
  const router = createRouter({
    history: createWebHistory(),
    routes
  })
  
  export default router
