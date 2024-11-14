<template>
  <section>
    <div class="grid lg:grid-cols-3 md:grid-cols-4">
      <div class="col-span-1"></div>

      <div class="lg:col-span-1 md:col-span-2 md:mx-0 mx-16">
        <div class="shadow-xl mt-10 p-6 rounded-xl">
          <div v-if="auth.showRegSuccessMsg" class="text-center bg-green-500 text-white text-sm font-bold py-2 rounded-lg my-2 flex justify-between px-3 items-center">
            <span>Registration successfull! You can login now.</span>
            <span class="inline-block">
              <button @click="closeRegSuccessMsg"><close/></button>
            </span>
          </div>

          <form @submit.prevent="handleLogin">            
            <p class="text-3xl font-bold">Sign in</p>
            <p class="text-sm">Stay updated on your blogging world</p>

            <input v-model="username" type="text" placeholder="Username" class="border px-2 py-3 w-full mt-4 rounded-md">

            <input v-model="password" type="password" placeholder="Password" class="border px-2 py-3 w-full mt-4 rounded-md">

            <div v-if="auth.loginError !== null">
              <!-- login failed -->
              <p class="text-center bg-red-200 text-red-700 text-sm py-3 mt-6 rounded-md">{{ auth.loginError }}</p>
            </div>

            <button type="submit" class="w-full bg-blue-600 text-white text-sm font-bold py-3 rounded-full mt-5">Sign in</button>

            <p class=" mt-5 text-center">
              <span>New to Bloggist? </span>
              <span class="font-bold text-blue-700">
                <router-link :to="{name:'signUp'}">Join now</router-link>
              </span>
            </p>
          </form>
        </div>
      </div>

      <div class="col-span-1"></div>
    </div>
  </section>
</template>

<script setup>
  import { ref, onMounted } from 'vue';
  import close from '@/components/icons/close.vue';
  import { useAuthStore } from '@/stores/auth';

  const username = ref('')
  const password = ref('')

  const auth = useAuthStore();

  function handleLogin() {
    auth.username = username.value;
    auth.password = password.value;

    auth.loginAttempt()
  }

  function closeRegSuccessMsg() {
    auth.showRegSuccessMsg = false
  }

  onMounted(() => {
    window.scrollTo(0, 0);
  })
</script>

<style>

</style>