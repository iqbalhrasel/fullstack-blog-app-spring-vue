<template>
  <div class="grid grid-cols-4">
    <div class="col-span-1"></div>

    <div class="col-span-2 mt-8">
      <div>
        <router-link :to="{name:'blogList', params:{type: 'myblogs'}}" class="bg-emerald-700 text-white px-5 py-2 rounded-md">My Blogs</router-link>
      </div>
      <div class="mt-8">
        <router-link :to="{name: 'createBlog'}" class="bg-emerald-700 text-white px-5 py-2 rounded-md">Create new blog</router-link>
      </div>
      <div class="mt-8">
        <button @click="auth.handleLogout" class="bg-orange-600 text-white px-5 py-2 rounded-md">Logout</button>
      </div>
    </div>

    <div class="col-span-1"></div>    
  </div>
</template>

<script setup>
import { useAuthStore } from '@/stores/auth';
import { onMounted, ref, computed, watch } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import { useBlogStore } from '@/stores/blog';

const blogStore = useBlogStore();

const router = useRouter()
const route = useRoute()  

const auth = useAuthStore()

onMounted(async () => {    
  if(!auth.isLoginSuccess){
    router.push({name:'login'})
  }
  
  window.scrollTo(0, 0);
})

</script>

<style>

</style>