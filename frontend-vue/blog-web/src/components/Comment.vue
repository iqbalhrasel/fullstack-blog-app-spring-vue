<template>
  <section>
    <div>
      <p class="text-2xl font-semibold my-3">Comments ({{ blogStore.blog.commentCount.count }})</p>

      <div class="flex mb-5">
        <input v-model="commentText" type="text" placeholder="write your opinion" class="border border-gray-300 py-2 px-3 w-80 rounded-lg">

        <button @click="addComment" class="text-blue-500 hover:text-blue-600 ml-2"><send class="size-8"/></button>
      </div>

      <div v-for="comment in sortedComments" :key="comment.id">
        <div class="mb-2 bg-gray-100 p-2 rounded-xl">
          <div class="flex items-center">
            <usericon class="w-12 text-gray-400 border p-2 bg-gray-50 rounded-full"/>
            
            <p>
              <span class="text-xl font-bold ml-4 block">{{ comment.account.fullName }}</span>

              <span class="ml-4 text-sm">{{ comment.commentDate }}</span>
            </p>

          </div>
          
          <p v-if="showCommentDescId !== comment.id" class="ml-16">{{ comment.description }}</p>

          <div v-if="editableCommentId === comment.id" class="ml-16 mb-2">
            <input type="text" v-model="updateCommentText" class=" px-2 py-2 rounded-md border-2 border-blue-500">

            <button @click="cancelEdit" class="text-sm bg-green-500 text-white font-bold py-1 px-3 ml-5 rounded-lg">Cancel</button>
            <button @click="updateComment(comment)" class="text-sm bg-blue-500 text-white font-bold py-1 px-5 ml-1 rounded-lg">Save</button>
          </div>

          <div class="flex justify-end mr-4 mb-2" v-if="auth.isLoginSuccess && comment.account.id === auth.accountId">
            <button @click="toggleCommentInput(comment)" class="mr-3 capsule">Edit</button>
            <button @click="deleteComment(comment)" class="capsule">Delete</button>
          </div>
        </div>
        
      </div>
      
    </div>
  </section>
</template>

<script setup>
  import { ref, computed } from 'vue';
  import send from '@/components/icons/send.vue';
  import usericon from '@/components/icons/usericon.vue';
  import { useBlogStore } from '@/stores/blog';
  import { useAuthStore } from '@/stores/auth';

  const blogStore = useBlogStore();
  const auth = useAuthStore()

  const commentText = ref('')
  const updateCommentText = ref('')
  const editableCommentId = ref(null)
  const showCommentDescId = ref(null)

  const sortedComments = computed(() => {
    const tempComments = blogStore.blog.comments;
    tempComments.sort((a, b) => b.id - a.id);
    return tempComments;
  })

  function addComment() {
    blogStore.addComment(blogStore.blog.id, auth.accountId, commentText.value)

    commentText.value = '';
  }

  function toggleCommentInput(comment) {    
    if(editableCommentId.value === comment.id){
      editableCommentId.value = null      
    }
    else{
      editableCommentId.value = comment.id
      showCommentDescId.value = comment.id
      updateCommentText.value = comment.description
    }    
  }

  function cancelEdit() {
    editableCommentId.value = null
    showCommentDescId.value = null
  }

  function updateComment(comment) {
    blogStore.updateComment(blogStore.blog.id, comment, auth.accountId, updateCommentText.value);

    cancelEdit()
  }

  function deleteComment(comment) {
    blogStore.deleteCommentById(comment.id, blogStore.blog.id)
  }

  

</script>

<style>
  .capsule{
    @apply bg-white px-3 py-1 rounded-full text-sm hover:bg-gray-50 cursor-pointer
  }
</style>