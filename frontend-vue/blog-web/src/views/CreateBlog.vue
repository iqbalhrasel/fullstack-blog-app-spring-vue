<template>
  <section>
    <div class="lg:mx-32 mx-16 mt-10">
      
      <form @submit.prevent="handleForm">
        <div>
          <label class="mr-6 font-bold">Blog Title</label>
          <input v-model="blog.title" type="text" placeholder="Write blog title" class="text-input" >
        </div>
        
        <div class="mt-2">
          <label class="mr-7 font-bold">Blog URL</label>
          <input v-model="blog.blogUrl" type="text" placeholder="Write blog url" class="text-input">
        </div>
        
        <div class="mt-2 mb-3">
          <label class="mr-[0.88rem] font-bold">Image URL</label>
          <input v-model="blog.imgUrl" type="text" placeholder="Write blog image url" class="text-input">
        </div>
        
        <div class="bg-gray-100 p-2 border border-gray-300 rounded-t-md font-bold">
          <!-- buttons -->
          <button @click.prevent="addTag('<h2></h2>')" class="btn">H2</button>
          <button @click.prevent="addTag('<h3></h3>')" class="btn">H3</button>
          <button @click.prevent="addTag('<h4></h4>')" class="btn">H4</button>
          <button @click.prevent="addTag('<h5></h5>')" class="btn">H5</button>
          <button @click.prevent="addTag('<p></p>')" class="btn">Paragraph</button>
          <button @click.prevent="addTag('<img src=&quot;&quot; alt=&quot;&quot;>')" class="btn">IMG</button>
        </div>
        <textarea v-model="blog.content" ref="textFocus" rows="14"  placeholder="Write your content" class="border border-gray-300 rounded-b-md w-full p-3"></textarea>
        
        <button type="submit" class="bg-blue-600 text-white text-sm font-bold px-6 py-2 rounded-lg mt-3">Create</button>
      </form>
    </div>
    
  </section>
</template>

<script setup>
  import { onMounted, ref, computed, watch } from 'vue';
  import { useRoute, useRouter } from 'vue-router';
  import { useBlogStore } from '@/stores/blog';
  import { useAuthStore } from '@/stores/auth';

  const props = defineProps(['id'])

  const blogStore = useBlogStore();
  const auth = useAuthStore()

  const router = useRouter()
  const route = useRoute()  

  const blog = ref({
    id: 0,
    title: '',
    blogUrl: '',
    imgUrl: '',
    content: '',
    account:{id: auth.accountId}
  })

  watch(() => route.name, (newName, oldName) => {
    if(newName === 'createBlog'){
      blog.value.id = 0;
      blog.value.title = '';
      blog.value.blogUrl = '';
      blog.value.imgUrl = '';
      blog.value.content = '';
    }
    else{
      fetchBlogForEdit()
    }
  })


  async function fetchBlogForEdit() {
    await blogStore.fetchBlog(props.id)        
    blog.value = blogStore.blog
  }

  onMounted(async () => {    
    if(!auth.isLoginSuccess){
      router.push({name:'login'})
    }
    else{
      if(props.id === undefined || props.id < 1){
        blog.value.id = 0;
        blog.value.title = '';
        blog.value.blogUrl = '';
        blog.value.imgUrl = '';
        blog.value.content = '';
      }
      else{
        fetchBlogForEdit()
      }
    }
    
    window.scrollTo(0, 0);
  })

  // form inputs  
  
  const textFocus = ref(null)

  function addTag(tag) {
    blog.value.content = blog.value.content + '\n' + tag
    textFocus.value.focus()
  }

  function handleForm() {
    if(route.name === 'createBlog'){
      blogStore.submitBlog(blog.value)
      console.log('create ' + blog.value.id);
      
    }
    else{
      console.log('update exisiting blog ' +blog.value.id);
      blogStore.updateBlog(blog.value)
    }
  }
</script>

<style>
  .btn{
    @apply mx-2 px-2 border rounded-md border-gray-100 hover:border hover:border-gray-300
  }
  .text-input{
    @apply border px-3 py-1 w-96 rounded-md
  }
</style>