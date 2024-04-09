<template>
  <div class="bg-amber-500 h-full flex flex-col items-center gap-4">
    <h1 class="text-red-600 text-center p-5 text-6xl">Login Page</h1>
    <div class="w-1/2 m-6 flex flex-col gap-4 p-2 items-center ">
      <input
          v-model="email"
          :class="{ 'border-red-500 border-2': showRequiredFields.email }"
          @input="showRequiredFields.email = email.trim() === ''"
          type="text"
          class="w-1/2 bg-amber-200 rounded p-1 text-center hover:bg-amber-300 outline-none font-mono"
          placeholder="Email"/>
      <input
          v-model="password"
          :class="{ 'border-red-500 border-2': showRequiredFields.password }"
          @input="showRequiredFields.password = password.trim() === ''"
          type="password"
          class="w-1/2 bg-amber-200 rounded p-1 text-center hover:bg-amber-300 outline-none font-mono"
          placeholder="Password"/>
      <p v-if="errorMessage.length !== 0"
         class="w-1/2 h-1/2 bg-red-600 rounded p-0.5 text-center text-white font-mono relative">
        <span v-text="errorMessage"></span>
        <span @click="errorMessage = ''"
              class="absolute top-0 right-0 p-1 cursor-pointer">&#x2716;</span>
      </p>
      <button
          @click="submitLogin"
          class="border-2 border-amber-200 bg-amber-200 rounded-2xl text-gray-400 font-mono text-sm p-1 hover:text-lg hover:bg-amber-300 transition duration-1000 ease-in-out">
        Submit
      </button>
    </div>
  </div>
</template>

<script setup>
import {computed, ref} from "vue";
import {useStore} from "vuex";
import {useRouter} from "vue-router";

const store = useStore();
const router = useRouter();
const email = ref('')
const password = ref('')
const errorMessage = ref('')
const showRequiredFields = ref({
  email: false,
  password: false
})

const emailFilled = computed(() => email.value.trim());
const passwordFilled = computed(() => password.value.trim());
const submitLogin = () => {
  if (emailFilled.value.length === 0) {
    showRequiredFields.value.email = true;
  } else if (passwordFilled.value.length === 0) {
    showRequiredFields.value.password = true;
  } else {
    const userData = {
      email: emailFilled.value,
      password: passwordFilled.value
    };

    store.dispatch('loginUser', userData)
        .then(data => {
          router.push('/');
        })
        .catch(error => {
          errorMessage.value = error.message;
        });
  }
}
</script>

<style scoped>

</style>
