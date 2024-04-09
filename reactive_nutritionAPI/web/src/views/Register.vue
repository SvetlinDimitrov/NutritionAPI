<template>
  <div class="bg-amber-500 h-full flex flex-col items-center gap-4">
    <h1 class="text-red-600 text-center p-5 text-6xl">Register Page</h1>
    <form @submit.prevent="submitLogin" class="w-1/2 m-6 flex flex-col gap-4 p-2 items-center ">
      <input
          v-model="username"
          required
          minlength="2"
          type="text"
          class="w-1/2 bg-amber-200 rounded p-1 text-center hover:bg-amber-300 outline-none font-mono"
          placeholder="Username"
      />
      <input
          v-model="email"
          required
          type="email"
          class="w-1/2 bg-amber-200 rounded p-1 text-center hover:bg-amber-300 outline-none font-mono"
          placeholder="Email"
      />
      <input
          v-model="password"
          required
          minlength="4"
          type="password"
          class="w-1/2 bg-amber-200 rounded p-1 text-center hover:bg-amber-300 outline-none font-mono"
          placeholder="Password"
      />
      <p v-if="errorMessage.length !== 0"
         class="w-1/2 h-1/2 bg-red-600 rounded p-0.5 text-center text-white font-mono relative">
        <span v-text="errorMessage"></span>
        <span @click="errorMessage = ''" class="absolute top-0 right-0 p-1 cursor-pointer">&#x2716;</span>
      </p>
      <button
          type="submit"
          class="border-2 border-amber-200 bg-amber-200 rounded-2xl text-gray-400 font-mono text-sm p-1 hover:text-lg hover:bg-amber-300 transition duration-1000 ease-in-out"
      >
        Submit
      </button>
    </form>
  </div>
</template>

<script setup>
import {computed, ref} from "vue";
import {useRouter} from "vue-router";
import {registerUser} from "@/api/userService.js";

const router = useRouter();
const email = ref('');
const password = ref('');
const username = ref('');
const errorMessage = ref('');

const emailFilled = computed(() => email.value.trim());
const passwordFilled = computed(() => password.value.trim());
const usernameFilled = computed(() => username.value.trim());
const submitLogin = () => {

  const userData = {
    username: usernameFilled.value,
    email: emailFilled.value,
    password: passwordFilled.value
  };

  registerUser(userData)
      .then(() => router.push("/login"))
      .catch(error => errorMessage.value = error.message)
}
</script>

<style scoped>

</style>