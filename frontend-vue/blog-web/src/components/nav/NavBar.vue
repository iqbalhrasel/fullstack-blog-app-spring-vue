<template>
  <nav class="bg-blue-500 flex justify-between items-center py-7 lg:px-24 md:px-12 px-5 sticky top-0 z-50 shadow-md">

    <section class="text-white font-bold text-3xl hover:text-blue-100 w-44">
      <router-link :to="{name: 'home'}">
        <span>B</span>
        <span class="text-blue-100 hover:text-blue-200">lo</span>
        <span class="text-blue-200 hover:text-blue-300">gg</span>
        <span class="text-blue-300 hover:text-blue-400">ist</span>
      </router-link>
    </section>

    <NavMenu sectionStyle="md:block hidden" ulStyle="flex" liStyle="text-blue-100 font-semibold"/>
    

    <!-- login sign up -->
    <div v-if="!auth.isLoginSuccess" class="text-white font-semibold">
      <router-link :to="{name: 'login'}" class="text-blue-100">Login</router-link>
      <!-- <a href="#" class="text-blue-100">Login</a> -->
      <router-link :to="{name: 'signUp'}" class="border-blue-200 border-2 py-2 px-5 rounded-md ml-6 hover:bg-blue-400 hover:border-blue-400">Sign up</router-link>
    </div>

    <!-- name -->
    <div class="md:hidden" v-if="auth.isLoginSuccess">
      <router-link :to="{name:'adminPanel'}" class="flex">
        <span class="mr-2 border-2 w-7 h-7 rounded-full flex place-content-center"><usericon class="w-4 inline-block text-gray-300"/></span>
        <span class="text-blue-100 font-bold">{{ auth.fullName }}</span>   
      </router-link>         
    </div>
   
    <!-- dropdown -->
    <div v-if="auth.isLoginSuccess" class="w-44 md:flex md:justify-end hidden">
      <ButtonDropDown2 />
    </div>

    <!-- burger icon -->
    <div class="md:hidden flex justify-end w-44">
      <span class=" text-blue-100 cursor-pointer " @click="toggleMenu">
        <burgericon class="w-7"/>
      </span>        
    </div>       
  </nav>

  <!-- Menu for small devices -->
  <NavMenu @click="toggleMenu" v-if="isMenuVisible" 
  sectionStyle="md:hidden bg-blue-500 border-t-2 border-blue-400 pb-3 shadow-md sticky top-[5.75rem]" 
  ulStyle="flex flex-col items-center" 
  liStyle="mt-2 text-blue-100 font-semibold"/>
  <!--  top-0 z-50  -->
</template>

<script setup>
import { ref } from 'vue';
import NavMenu from './NavMenu.vue';
import ButtonDropDown2 from '../dropdowns/ButtonDropDown2.vue';
import { useAuthStore } from '@/stores/auth';

import burgericon from '../icons/burgericon.vue';
import usericon from '../icons/usericon.vue';

const auth = useAuthStore()

const isMenuVisible = ref(false);

function toggleMenu() {
  isMenuVisible.value = !isMenuVisible.value;
}
</script>

<style>
/* Add any necessary styles here */
</style>
