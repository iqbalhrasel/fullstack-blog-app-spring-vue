<template>
  <div class="grid grid-cols-10 md:grid-cols-6 bg-yellow-100">
    <div class="col-span-1"></div>

    <div class="col-span-8 md:col-span-4">
      <h2 class="text-3xl font-semibold my-2">Your Blogs</h2>
      <hr class="mb-2">

      <div v-if="blogStore.userBlogs.length === 0 && blogStore.userBlogsMsg === ''">Loading...</div>

      <div v-else-if="blogStore.userBlogs.length === 0 && blogStore.userBlogsMsg !== ''" class="mb-3 text-red-700">{{blogStore.userBlogsMsg}}</div>

      <div v-else v-for="(blog, index) in blogStore.userBlogs" :key="blog.id">
        <Card2 :blog="blog" :index="index"/>
      </div>      
    </div>
    
    <div class="col-span-1"></div>
  </div>
</template>

<script setup>
import { onMounted } from 'vue';
import Card2 from '@/components/cards/Card2.vue';
import { useRouter } from 'vue-router';
import { useBlogStore } from '@/stores/blog';
import { useAuthStore } from '@/stores/auth';

const blogStore = useBlogStore()
const auth = useAuthStore()

const router = useRouter()

onMounted(() => {
  if(!auth.isLoginSuccess){
      router.push({name:'login'})
  }
  else{
    blogStore.fetchBlogsByUser()
  }

  window.scrollTo(0, 0);
})
</script>

<style>

</style>