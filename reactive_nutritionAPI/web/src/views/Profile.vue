<template>
  <div class="bg-amber-500 h-full flex flex-col items-center gap-4">
    <h1 class="text-red-600 text-center p-5 text-6xl font-mono">My Profile</h1>
    <form @submit.prevent="submitChanges" class="w-1/2 m-6 flex flex-col gap-2 p-2 items-center ">
      <label for="username" class="w-1/2 text-center text-gray-600 font-bold">Username</label>
      <input
          id="username"
          v-model="username"
          minlength="2"
          type="text"
          class="w-1/2 bg-amber-200 rounded p-1 text-center hover:bg-amber-300 outline-none font-mono"
          placeholder="Username"
      />
      <label for="kilograms" class="w-1/2 text-center text-gray-600 font-bold">Kilograms</label>
      <input
          id="kilograms"
          v-model="kilograms"
          min="1"
          type="number"
          class="w-1/2 bg-amber-200 rounded p-1 text-center hover:bg-amber-300 outline-none font-mono"
          placeholder="Kilograms"
      />
      <label for="height" class="w-1/2 text-center text-gray-600 font-bold">Height</label>
      <input
          id="height"
          v-model="height"
          min="1"
          type="number"
          class="w-1/2 bg-amber-200 rounded p-1 text-center hover:bg-amber-300 outline-none font-mono"
          placeholder="Height"
      />
      <label for="workoutState" class="w-1/2 text-center text-gray-600 font-bold">Workout Status</label>
      <select
          id="workoutState"
          v-model="workoutState"
          class="w-1/2 bg-amber-200 rounded p-1 text-center hover:bg-amber-300 outline-none font-mono"
      >
        <option value="" >Choose workoutState</option>
        <option value="SEDENTARY">SEDENTARY</option>
        <option value="LIGHTLY_ACTIVE">LIGHTLY ACTIVE</option>
        <option value="MODERATELY_ACTIVE">MODERATELY ACTIVE</option>
        <option value="VERY_ACTIVE">VERY ACTIVE</option>
        <option value="SUPER_ACTIVE">SUPER ACTIVE</option>
      </select>
      <label for="gender" class="w-1/2 text-center text-gray-600 font-bold">Gender</label>
      <select
          id="gender"
          v-model="gender"
          class="w-1/2 bg-amber-200 rounded p-1 text-center hover:bg-amber-300 outline-none font-mono"
      >
        <option value="" >Choose gender</option>
        <option value="MALE">MALE</option>
        <option value="FEMALE">FEMALE</option>
      </select>
      <label for="age" class="w-1/2 text-center text-gray-600 font-bold">Age</label>
      <input
          id="age"
          v-model="age"
          min="1"
          type="number"
          class="w-1/2 bg-amber-200 rounded p-1 text-center hover:bg-amber-300 outline-none font-mono"
          placeholder="Age"
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
        Make changes
      </button>
    </form>
  </div>
</template>

<script setup>
import {computed, onMounted, ref} from "vue";
import {useRouter} from "vue-router";
import {registerUser} from "@/api/userService.js";
import {useStore} from "vuex";

const store = useStore();
const router = useRouter();
const username = ref('');
const kilograms = ref();
const height = ref();
const workoutState = ref('');
const gender = ref('');
const age = ref();
const errorMessage = ref('');

const usernameFilled = computed(() => username.value.trim());
const kilogramsFilled = computed(() => kilograms.value);
const heightFilled = computed(() => height.value);
const workoutStateFilled = computed(() => workoutState.value.trim());
const genderFilled = computed(() => gender.value.trim());
const ageFilled = computed(() => age.value);

onMounted(() => {
  const userView = store.state.userView;
  username.value = userView.username;
  kilograms.value = userView.kilograms;
  height.value = userView.height;
  workoutState.value = userView.workoutState ? userView.workoutState : '';
  gender.value = userView.gender ? userView.gender : '';
  age.value = userView.age;
})

const submitChanges = () => {

  const userData = {
    username: usernameFilled.value,
    kilograms: kilogramsFilled.value,
    height: heightFilled.value,
    workoutState: workoutStateFilled.value,
    gender: genderFilled.value,
    age: ageFilled.value,
  };

  store.dispatch('editUser', userData)
      .then(data => {
        router.push('/');
      })
      .catch(error => {
        errorMessage.value = error.message;
      });
}
</script>
