import { createApp } from 'vue';
import { createRouter, createWebHistory } from 'vue-router'; // Import specific functions from 'vue-router'
import App from './App.vue';
import Chats from './Chats.vue';
import Images from './Images.vue';// Corrected component name
import Followers from './Networks/Followers.vue'; // PascalCase component name
import Following from './Networks/Following.vue'; // PascalCase component name
import Blocked from './Networks/Blocked.vue';
import MyNetwork from './Networks/MyNetwork.vue'; // PascalCase component name
import NewChats from './NewChats.vue'; // PascalCase component name
import ShowNewChats from './ShowNewChats.vue'; // PascalCase component name
import NewChatList from './NewChatList.vue'; // PascalCase component name
import displayImage from './displayImage.vue';
import uploading from './uploading.vue';


//Vaish Paths

import SearchAccount from './components/SearchAccount.vue'
import LoginAccount from './components/LoginAccount.vue'
import SignupAccount from './components/SignupAccount.vue'
import EnableAccount from './components/EnableAccount.vue'
import DisableAccount from './components/DisableAccount.vue'
import YourAccount from './components/YourAccount.vue'
import ReportAccount from './components/ReportAccount.vue'
import AccountManagement from './components/AccountManagement.vue'
import Share_message from './Share_message.vue';
import Profile from './profile/Profile.vue';
import AppBar from './profile/AppBar.vue';
import AllAccounts from './AllAccounts.vue';
// import App from './App.vue';
import BusinessAccForm from "./components/BusinessAccForm.vue";
import FeedVue from "./components/FeedVue.vue";
import FormView from './components/FormView.vue';
import HomeView from "./components/HomeView.vue";
import PostVue from "./components/PostVue.vue";
import AddPost from "./components/addPost.vue";
import addPostPers from "./components/addPostPers.vue";
import BillPayment from "./components/billPayment.vue";
import BusAccProf from "./components/busAccProf.vue";
import personalAccProfile from "./components/personalAccProfile.vue";
import Admin from "./Admin.vue";
// Create the Vue app instance
const app = createApp(App);

// Define routes
const routes = [
  { path: '/images', component: Images },
  { path: '/followers/:FROM', component: Followers ,name:"Followers"},
  { path: '/following/:FROM', component: Following ,name:"Following"},
  { path: '/blocked:FROM', component: Blocked,name:"Blocked" },
  { path: '/my_network', component: MyNetwork,name:"MyNetwork" },
  { path: '/new_chat/:From/:To', component: NewChats ,name:'NewChats' },
  { path: '/show_new_chats', component: ShowNewChats },
  { path: '/new_chatlist/:FROM', component: NewChatList ,name:'NewChatList'},
  { path: '/uploadimage', component: uploading },
  { path: '/displayimage', component: displayImage },
  { path: '/share_to_followers/:From/:Chat', component: Share_message,name:'Share_message' },
  { path: '/appbar/:USER', component:AppBar,name:'AppBar' },
  { path: '/getaccounts', component:AllAccounts,name:'AllAccounts' },
  // { path: '/', component: HomeView },
  {path: '/form', component : FormView},
  {path: '/businessAcc', component : BusinessAccForm},
  {path:'/profile',component: BusAccProf},
  {path:'/pay',component: BillPayment},
  {path:'/post',component: AddPost},
  {path:'/p',component: PostVue},
  {path:'/feed',component: FeedVue},
  {path:'/persProfile',component: personalAccProfile},
  {path:'/persPost',component: addPostPers},
  {
    path: '/search',
    name: 'SearchAccount',
    component: SearchAccount
},
{
  path: '/signup',
  name: 'Signup',
  component: SignupAccount
},
{
  path: '/login',
  name: 'Login',
  component: LoginAccount
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
,
{
  path:'/admin',
  component:Admin
},


];

// Create the router instance
const router = createRouter({
  history: createWebHistory(''), // Pass an empty string for the base URL
  routes
});

// Use the router instance in the Vue app
app.use(router);

// Mount the app to the element with id "app"
app.mount('#app');
