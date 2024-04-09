<template>
  <div>
    <div v-if="loading" class="spinner">
      <div class="spinner-inner"></div>
    </div>

    <div v-else>
    </div>

  </div>
</template>

<script setup>
import { onMounted, ref } from 'vue';
import { useStore } from 'vuex';
import { useRouter } from 'vue-router';

const store = useStore();
const router = useRouter();


const loading = ref(false);

onMounted(async () => {
  loading.value = true;

  try {
    await store.dispatch('logoutUser');
    await router.push('/');
  } catch (error) {
    console.error('Error logging out:', error);
  } finally {
    loading.value = false;
  }
});
</script>

<style scoped>
.spinner {
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  z-index: 9999;
}

.spinner-inner {
  border: 4px solid rgba(0, 0, 0, 0.1);
  border-left-color: #333;
  border-radius: 50%;
  width: 50px;
  height: 50px;
  animation: spin 1s linear infinite;
}

@keyframes spin {
  0% { transform: rotate(0deg); }
  100% { transform: rotate(360deg); }
}
</style>
