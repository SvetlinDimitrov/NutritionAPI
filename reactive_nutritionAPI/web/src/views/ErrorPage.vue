<template>
  <div class="bg-amber-500 flex flex-col items-center justify-center h-screen">
    <h1 class="text-4xl font-bold text-red-500 mb-4">Error {{ error }}</h1>
    <p class="text-lg text-gray-700 mb-8">Oops! Something went wrong.</p>
    <div class="flex space-x-4">
      <router-link to="/" class="bg-blue-500 text-white px-4 py-2 rounded-md hover:bg-blue-600 transition duration-300">Go to Home</router-link>
      <button @click="goBack" class="bg-gray-500 text-white px-4 py-2 rounded-md hover:bg-gray-600 transition duration-300">Go Back</button>
    </div>
  </div>
</template>

<script setup>
import { useRouter } from 'vue-router';

const router = useRouter();
const error = router.currentRoute.value.params.error || '404';

const goBack = () => {
  router.go(-1);
}
</script>
