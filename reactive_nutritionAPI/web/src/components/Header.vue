<template>
  <header class="flex justify-between bg-amber-200">
    <div class="text-2xl p-2.5 hover:text-3xl hover:bg-amber-600 hover:text-white transition-all duration-300 ease-in-out" >
      <router-link :to="{ name: 'Home'}">Home</router-link>
    </div>
    <div v-if="$store.getters.isSessionExpired"
        class="text-2xl p-2.5 flex gap-3 m-0 p-0">
      <div class="text-2xl p-2.5 hover:text-3xl hover:bg-amber-600 hover:text-white transition-all duration-300 ease-in-out">
        <router-link :to="{ name: 'Login'}">Login</router-link>
      </div>
      <div class="text-2xl p-2.5 hover:text-3xl hover:bg-amber-600 hover:text-white transition-all duration-300 ease-in-out">
        <router-link :to="{ name: 'Register'}">Register</router-link>
      </div>
      <div class="text-2xl p-2.5 hover:text-3xl hover:bg-amber-600 hover:text-white transition-all duration-300 ease-in-out">
        <router-link :to="{ name: 'MacroInfo'}">MacroInfo</router-link>
      </div>
    </div>
    <div v-if="!$store.getters.isSessionExpired"
         class="text-2xl p-2.5 flex gap-3 m-0 p-0">
      <div class="text-2xl p-2.5 hover:text-3xl hover:bg-amber-600 hover:text-white transition-all duration-300 ease-in-out">
        <router-link :to="{ name: 'Logout'}">Logout</router-link>
      </div>
      <div class="text-2xl p-2.5 hover:text-3xl hover:bg-amber-600 hover:text-white transition-all duration-300 ease-in-out">
        <router-link :to="{ name: 'MacroInfo'}">MacroInfo</router-link>
      </div>
    </div>
  </header>
</template>

<script setup>

</script>

<style scoped>

</style>