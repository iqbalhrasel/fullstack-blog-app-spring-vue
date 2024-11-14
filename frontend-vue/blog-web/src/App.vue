<script setup>
  import NavBar from "./components/nav/NavBar.vue";
  import FooterSection from "./components/forhome/FooterSection.vue";

  import { onMounted } from 'vue';
  import { useAuthStore } from "./stores/auth";

  const auth = useAuthStore()
  onMounted(() => {
    auth.loadCredentials()
    window.scrollTo(0, 0);
  })

</script>

<template>
  <NavBar/>  
  <RouterView />
  <FooterSection />    
</template>

<style>

</style>
