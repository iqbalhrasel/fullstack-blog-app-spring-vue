<template>
  <section :class="props.sectionStyle" >
    <ul :class="props.ulStyle">
      <li :class="props.liStyle" class="md:mr-11">
        <a href="#">
          <span>Features</span>
        </a>
      </li>
      <li :class="props.liStyle" class="md:mr-11">
        <a href="#">
          <span>Pricing</span>
        </a>
      </li>
      <li :class="props.liStyle" >
        <a href="#">Contact</a>
      </li>

      <li v-if="auth.isLoginSuccess" class="bg-green-500 w-full text-center py-2 mt-2 text-gray-700 font-bold md:hidden">
        
        <router-link :to="{name:'adminPanel'}" class="nav-admin-btn mr-10">View panel</router-link>
        <button class="nav-admin-btn" @click="auth.handleLogout">Logout</button>
      </li>
    </ul>
  </section>
</template>

<script setup>
import { useAuthStore } from '@/stores/auth';

const props = defineProps(['sectionStyle', 'ulStyle', 'liStyle'])
const auth = useAuthStore()

</script>

<style>
.nav-admin-btn{
  @apply  border py-1 px-2 rounded-md bg-green-100
}
</style>