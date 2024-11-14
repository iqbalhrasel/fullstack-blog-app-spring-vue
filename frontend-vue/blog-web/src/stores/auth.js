import { ref, onMounted } from 'vue'
import { defineStore } from 'pinia'
import axios from 'axios';
import { useRouter } from 'vue-router';

export const useAuthStore = defineStore('authStore', () => {
  const apiUrl = import.meta.env.VITE_API_BASE_URL;

  const username = ref(null);
  const password = ref(null);
  const accountId = ref(null)
  const fullName = ref(null)
  const isLoginSuccess = ref(false);

  const loginError = ref(null);  
  const showRegSuccessMsg = ref(false)  

  const router = useRouter()

  const loadCredentials = () => {
    const storedUsername = localStorage.getItem('username');
    const storedPassword = localStorage.getItem('password');
    const storedFullName = localStorage.getItem('fullName');
    const storedAccId = localStorage.getItem('accountId');

    if (storedUsername && storedPassword) {
      username.value = storedUsername;
      password.value = storedPassword;
      fullName.value = storedFullName;
      accountId.value = Number.parseInt(storedAccId) ;
      
      isLoginSuccess.value = true;      
    }
  };

  function handleLogout(){
    username.value = null
    password.value = null
    accountId.value = null
    isLoginSuccess.value = false
    fullName.value = null

    localStorage.removeItem('username');
    localStorage.removeItem('password');
    localStorage.removeItem('fullName');
    localStorage.removeItem('accountId');

    router.push({name:'home'})
  }

  const loginAttempt = async () => {
    try {
      const response = await axios.post(`${apiUrl}/login`, {
        username: username.value,
        password: password.value
      });
      
      if (response.status === 200) {
        isLoginSuccess.value = true;
        loginError.value = null
        fullName.value = response.data.fullName
        accountId.value = response.data.id

        localStorage.setItem('username', username.value);
        localStorage.setItem('password', password.value);
        localStorage.setItem('fullName', fullName.value);
        localStorage.setItem('accountId', accountId.value);

        router.push({name: 'home'})
      }
      
    } catch (err) {
      console.error(err);
      loginError.value = 'Bad credentials.';
      isLoginSuccess.value = false
      username.value = null
      password.value = null
      accountId.value = null
      fullName.value = null
    }
  };

  const registerAttempt = async (accountDto) => {    
    try {
      const response = await axios.post(`${apiUrl}/register`, {
        fullName: accountDto.fullName,
        username: accountDto.username,
        password: accountDto.password
      });      
      
      if (response.status === 200) {
        showRegSuccessMsg.value = true
        router.push({name: 'login'})
      }
      
    } catch (err) {
      console.error(err);
      showRegSuccessMsg.value = false
    }
  };

  return {username, password, accountId, isLoginSuccess, loginError, fullName, showRegSuccessMsg, loadCredentials, handleLogout, loginAttempt, registerAttempt}
})
