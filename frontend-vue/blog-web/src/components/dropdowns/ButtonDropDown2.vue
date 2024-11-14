<template>
  <section>
    <div class=" relative inline-block text-left">

      <!-- <div> -->
        <div @click="toggleDropDown" class="bg-blue-500 text-white h-14 w-14 rounded-full inline-flex items-center place-content-center cursor-pointer border">          
          <p>{{ nameInitails }}</p>
        </div>
      <!-- </div> -->

      <!-- options start -->
      <div v-show="showDropDown" class="ml-3 absolute right-0 w-48 bg-white border border-gray-200 rounded-md shadow-lg">
        
        <div class="bg-red-200 px-2 py-2 rounded">
          <span class=" font-bold">{{ auth.fullName }}</span>

          <span class="text-sm block mt-1">            
            <router-link :to="{name:'adminPanel'}">View panel</router-link>
          </span>
        </div>

        <div @click="toggleDropDown">
          
          <router-link :to="{name: 'blogList', params:{type:'myblogs'}}" class="dd-opt">My blogs</router-link>

          <router-link :to="{name: 'createBlog'}" class="dd-opt">Create new blog</router-link>
          
          <button @click="handleLogout" class="dd-opt w-48 text-left">Logout</button>
          
        </div>
      </div>
      <!-- options end -->
    </div>
  </section>
</template>

<script setup>
import { ref, computed, onMounted, onBeforeUnmount } from 'vue';
import { useAuthStore } from '@/stores/auth';
import { useRouter } from 'vue-router';

const auth = useAuthStore()
const router = useRouter()

const nameInitails = computed(() => {
  
  if(auth.fullName !== null){
    const nameParts = auth.fullName.split(' ');
    const initials = nameParts.map(part => part.charAt(0).toUpperCase()).join('');

    return initials
  }
  else {
    return ''
  }
})

const showDropDown = ref(false)

const closeDropdown = (e) => {
  if(!e.target.closest(".relative")){
    showDropDown.value = false
  }
}

function toggleDropDown() {
  showDropDown.value = !showDropDown.value
}

function handleLogout(){
  auth.handleLogout()
}

onMounted(() => {  
  document.addEventListener('click', closeDropdown)
})

onBeforeUnmount(() => {
  document.removeEventListener('click', closeDropdown)
})

</script>

<style>
.dd-opt{
  @apply block px-4 py-2 text-gray-700 hover:bg-gray-100
}
</style>