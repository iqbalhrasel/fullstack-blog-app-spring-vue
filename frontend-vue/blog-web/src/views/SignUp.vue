<template>
  <section class="bg-blue-500 grid lg:grid-cols-12 grid-cols-1 gap-6">

    <!-- info -->
    <div class="lg:col-span-6">
      <div class="mt-20 text-white font-bold text-4xl text-center">Blogist</div>
      <div class="mt-20 text-3xl font-semibold text-white ml-44 md:ml-10 md:text-center">
        <span>Start blogging with </span>
        <span class="block md:inline-block">your free plan.</span>
      </div>

      <div class="mt-20 text-center">
        <p class="pb-6 text-xl text-white font-bold">Already have account?</p>
        <router-link :to="{name: 'login'}" class="bg-blue-400 px-6 py-2 rounded-md text-white font-bold border-blue-300 border-2 hover:bg-blue-500">Login</router-link>
        
      </div>
    </div>

    <!-- form -->
    <div class="lg:col-span-6 bg-blue-500">
      
      <form @submit.prevent="handleRegister" class="mx-16 my-16 shadow-lg p-16 bg-white rounded-md">
        <!-- register -->
         <p class="text-4xl text-center text-gray-600 mb-10">Sign Up for Free</p>

         <input v-model="accountDto.fullName" type="text" placeholder="Enter full name" class="bg-gray-100 block px-5 py-4 rounded-md text-xl w-full mt-6">

         <input v-model="accountDto.username" type="text" placeholder="Enter username" class="bg-gray-100 block px-5 py-4 rounded-md text-xl w-full mt-6">

         <input v-model="accountDto.password" type="text" placeholder="Enter password" class="bg-gray-100 block px-5 py-4 rounded-md text-xl w-full mt-6">

         <div class="flex justify-center">
          <button type="submit" class="bg-blue-600 text-white font-semibold text-xl px-20 py-3 mt-6 shadow-lg rounded-full hover:bg-blue-700">Register</button>
         </div>
      </form>

    </div>
  </section>
  
</template>

<script setup>
  import { ref, onMounted } from 'vue';
  import { useAuthStore } from '@/stores/auth';

  const auth = useAuthStore();
  const accountDto = ref({
    fullName: '', 
    username: '', 
    password: ''
  })

  function handleRegister() {
    auth.registerAttempt(accountDto.value)
  }
  onMounted(() => {
    window.scrollTo(0, 0);
  })
</script>

<style>

</style>