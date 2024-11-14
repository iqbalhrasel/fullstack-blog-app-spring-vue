<template>
  <div>
    <!-- <button @click="handleToggle">Toggle</button> -->
    <section>
      <HeroSection />
    </section>

    <section class="pt-10">
      <div class="text-center text-gray-600 font-bold text-3xl">Popular Blogs</div>

      <!-- card list goes here :popular blogs-->
      <div class="mt-8 grid lg:grid-cols-3 md:grid-cols-2 gap-10 md:mx-28 mx-14" >
        <div v-if="blogStore.loadingPopularBlogs && blogStore.popularBlogsError === null" class="text-2xl text-center">Loading...</div>

        <div v-else-if="blogStore.popularBlogsError !== null" class="text-red-700">{{ blogStore.popularBlogsError }}</div>

        <div v-else v-for="popularBlog in blogStore.popularBlogs" :key="popularBlog.id">
          <router-link :to="{name: 'details', params:{cat:'popularblogs',id:popularBlog.id, slug:popularBlog.blogUrl}}">
            <Card imgStyle="h-48" :blog="popularBlog"/>
          </router-link>
          
        </div>        
      </div>
      <!-- cards list ends here -->
    </section>

    <section>
      <div class="grid md:grid-cols-7 mx-28 mt-20">
        <div class="md:col-span-4 bg-gray-100 h-72 rounded-l-3xl">
          <h3 class="font-bold text-2xl mt-5 ml-10">Why I started Blogist</h3>
          <p class="mt-5 mx-10">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Molestiae nihil natus a. nihil natus a. nihil natus a.</p>

          <div class="flex mt-5 ml-10">
            <div>
              <img src="../assets/images/coffee.jpg" alt="" class="rounded-full w-36">
            </div>
            <div class="ml-5 text-gray-500">
              <span class="block mt-4">Lorem, ipsum.</span>
              <span>Feb 2, 2024</span>
            </div>
          </div>
        </div>

        <div class="col-span-3 bg-yellow-400 h-72 rounded-r-3xl hidden md:block">
          <p class="text-center font-bold text-4xl text-white mt-32">My Ambition</p>
        </div>        
      </div>
    </section>

    <section class="mt-20">
      <div class="text-center text-gray-600 font-bold text-3xl">Latest Blogs</div>

      <!-- cards goes here -->
      <div class="mt-8 grid lg:grid-cols-2 gap-10 md:mx-28 mx-14" >
        <div v-if="blogStore.loadingLatestBlogs && blogStore.latestBlogsError === null" class="text-2xl text-center">Loading...</div>

        <div v-else-if="blogStore.latestBlogsError !== null" class="text-red-700">{{ blogStore.latestBlogsError }}</div>

        <div v-for="latestBlog in blogStore.latestBlogs" :key="latestBlog.id">
          <router-link :to="{name: 'details', params:{cat:'latestblogs' ,id:latestBlog.id, slug: latestBlog.blogUrl}}">
            <Card imgStyle="h-60" :blog="latestBlog"/>
          </router-link>
          
        </div>
        
        
      </div>
      <!-- cards list ends here -->
       <div>
        <div class="text-center mt-10">
          <span class="bg-blue-600 text-white px-2 py-1 text-lg rounded">01</span>
          <span class="text-gray-600 px-2 py-1 text-lg rounded ml-2">02</span>
          <span class="text-gray-600 px-2 py-1 text-lg rounded ml-2">03</span>
          <span class="text-gray-600 px-2 py-1 text-lg rounded ml-2">04</span>
          <span class="text-gray-600 px-2 py-1 text-lg rounded ml-2">Next</span>
        </div>
       </div>
    </section>

    <section>
      <JoinUs />
    </section>
  </div>
</template>

<script setup>
  import { useBlogStore } from '@/stores/blog';
  import { onMounted } from 'vue';
  import HeroSection from '@/components/forhome/HeroSection.vue';
  import JoinUs from '@/components/forhome/JoinUs.vue';
  import Card from '@/components/cards/Card.vue';

  const blogStore = useBlogStore()

  onMounted(() => {
    blogStore.fetchPopularBlogs();
    blogStore.fetchLatestBlogs();

    window.scrollTo(0, 0);
  })

</script>

<style>

</style>