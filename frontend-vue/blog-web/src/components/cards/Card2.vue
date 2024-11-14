<template>
  <section>
    
    <div class="flex shadow-lg p-4 rounded-lg my-8 bg-white">
      <div class="w-[75%]">
        <h3 class="text-xl font-semibold">
          <span class="bg-blue-600 text-white px-3 font-bold rounded-md mr-2">{{ props.blog.id }}</span>

          <span>{{ props.blog.title }}</span>
        </h3>
        <p >{{ showLimitedContent() }}</p>

        <div class="flex justify-between mt-4">
          <router-link class="card-button" :to="{name:'details', params:{cat:'myblogs', id: props.blog.id, slug: props.blog.blogUrl}}">Read more</router-link>

          <div class="inline-block">
            <router-link class="card-button" :to="{name:'editBlog', params:{id:props.blog.id}}">Edit</router-link>
            
            <button @click="deleteBlog" class="card-button">Delete</button>
          </div>
        </div>
      </div>

      <div class="ml-3">
        <img :src="props.blog.imgUrl" alt="" class="h-40 w-full object-cover rounded-md">
      </div>
    </div>
  </section>
</template>

<script setup>
import { useBlogStore } from '@/stores/blog';

const blogStore = useBlogStore()

const props = defineProps(['blog', 'index'])

function showLimitedContent() {
  const mainHtml = props.blog.content;
  const html = mainHtml.replace(/<img[^>]*>/g, '')

  const text = html.replace(/<[^>]*>/g, ' ');

  const splittedWords = text.trim().split(/\s+/)

  const finalWords = splittedWords.slice(0, 50).join(' ')
  return finalWords;
}

function deleteBlog() {
  blogStore.deleteBlogById(props.blog.id);
}

</script>

<style>
.card-button{
  @apply bg-black text-sm font-bold text-white py-1 px-3 rounded-md hover:text-yellow-400 mr-2
}
</style>