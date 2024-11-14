<template>
  <section>
    <div class="grid lg:grid-cols-4 md:grid-cols-6 grid-cols-8">
      <div class="col-span-1"></div>

      <div class="lg:col-span-2 md:col-span-4 col-span-6">
        <section v-if="Object.keys(blogStore.blog).length === 0 && blogStore.blogLoadingError === null">
          Loading...
        </section>

        <section v-else-if="Object.keys(blogStore.blog).length === 0 && blogStore.blogLoadingError !== null" class="text-red-700 text-center">
          {{ blogStore.blogLoadingError }}
        </section>

        <section v-else>
          <h1 class="text-4xl font-bold text-center my-7">{{ blogStore.blog.title }}</h1>

          <div class="mb-7 mt-10">
            <img :src="blogStore.blog.imgUrl" alt="">
          </div>

          <div v-html="blogStore.blog.content" class="body"></div>

          <hr class="mt-6 mb-3">

          <div class="flex items-center">
            
            <button @click="handleLike" >
              <span v-if="auth.isLoginSuccess && blogStore.blog.likes.some(like => like.account.id === auth.accountId) ">
                <likeicon class="w-7 text-blue-600"/>               
              </span>

              <span v-else>
                <likeicon class="w-7 text-gray-400"/>
              </span>
            </button>

            <span class="mx-2 font-bold text-md text-gray-500">•</span>
            <span class="">Likes ({{blogStore.blog.likeCount.count}})</span>

          </div>

          <!-- commenting -->
           <Comment :accId="auth.accountId"/>
          
        </section>
        
      </div>
      <div class="col-span-1"></div>
    </div>
  </section>
</template>

<script setup>
  import { onMounted, ref } from 'vue';
  import { useBlogStore } from '@/stores/blog';

  import Comment from '@/components/Comment.vue';

  import likeicon from '@/components/icons/likeicon.vue';

  import { useAuthStore } from '@/stores/auth';

  const blogStore = useBlogStore();
  const auth = useAuthStore()

  const props = defineProps(['id', 'slug'])

  onMounted(async() => {
    window.scrollTo(0, 0);

    blogStore.fetchBlog(props.id);
  })

  function handleLike(){
    blogStore.handleLike(props.id, auth.accountId)
  }

  
</script>

<style>
  .body h2{
    @apply font-bold text-2xl mt-4 mb-2
  }
  .body p{
    @apply mb-2
  }
  .body ul{
    @apply list-disc list-inside ml-2 mb-2
  }
  
</style>