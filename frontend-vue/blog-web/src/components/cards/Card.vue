<template>
  <section class="rounded-xl overflow-hidden hover:shadow-md hover:shadow-slate-300 cursor-pointer">

    <div class="p-3 overflow-hidden">
      <img :src="props.blog.imgUrl" alt="coffee" class="w-full object-cover rounded-lg" :class="props.imgStyle">
    </div>

    <div class="p-4">
      <div class="flex justify-between">
        <span class="font-bold text-lg text-gray-600">{{ props.blog.title }}</span>

        <div class="flex items-center text-gray-500">
          <span class="inline-block mr-1">
            <svg xmlns="http://www.w3.org/2000/svg" class="h-4 w-4" viewBox="0 0 20 20" fill="currentColor">
              <path d="M2 10.5a1.5 1.5 0 113 0v6a1.5 1.5 0 01-3 0v-6zM6 10.333v5.43a2 2 0 001.106 1.79l.05.025A4 4 0 008.943 18h5.416a2 2 0 001.962-1.608l1.2-6A2 2 0 0015.56 8H12V4a2 2 0 00-2-2 1 1 0 00-1 1v.667a4 4 0 01-.8 2.4L6.8 7.933a4 4 0 00-.8 2.4z" />
            </svg>
          </span>

          <span class="text-sm mr-2">
            {{ props.blog.likeCount.count }}
          </span>

          <span class="mr-2 text-gray-300">•</span>

          <span class="inline-block mr-1">
            <svg xmlns="http://www.w3.org/2000/svg" class="h-4 w-4" viewBox="0 0 20 20" fill="currentColor">
              <path fill-rule="evenodd" d="M18 5v8a2 2 0 01-2 2h-5l-5 4v-4H4a2 2 0 01-2-2V5a2 2 0 012-2h12a2 2 0 012 2zM7 8H5v2h2V8zm2 0h2v2H9V8zm6 0h-2v2h2V8z" clip-rule="evenodd" />
            </svg>
          </span>

          <span class="text-sm">
            {{ props.blog.commentCount.count }}
          </span>
        </div>
      </div>
      

      <div class="flex justify-between text-gray-600 mt-3">
        <div>
          <span class="text-gray-400">By</span>
          <span class="font-bold ml-1">{{ props.blog.account.fullName }}</span>
        </div>
        <span class="text-gray-400">2h ago</span>
      </div>
    </div>

  </section>
</template>

<script setup>
  const props = defineProps(['imgStyle', 'blog'])
</script>

<style>

</style>