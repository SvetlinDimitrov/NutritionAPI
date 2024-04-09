<template>
  <div class="fixed top-0 left-0 w-full h-full flex items-center justify-center z-50 bg-black bg-opacity-80">
    <div class="bg-white w-96 p-8 rounded-lg shadow-lg">
      <h2 class="text-2xl font-semibold mb-4">Add Consumption</h2>
      <form @submit.prevent="handleSubmit">
        <div class="mb-4">
          <label for="consumption" class="block text-sm font-medium text-gray-700">Consumption</label>
          <input type="number"
                 id="consumption"
                 v-model="consumption"
                 min="1"
                 required
                 class="mt-1 p-2 block w-full border-gray-300 rounded-md shadow-sm focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm">
        </div>
        <div class="flex justify-start">
          <button
              type="submit" class="inline-flex py-2 px-4 border border-transparent shadow-sm text-sm font-medium rounded-md text-white bg-indigo-600 hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500">Submit</button>
        </div>
      </form>
      <button
          @click="handleClose" class="mt-5 inline-flex justify-center py-2 px-4 border border-transparent shadow-sm text-sm font-medium rounded-md text-white bg-red-600 hover:bg-red-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500">Close</button>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import {useRoute, useRouter} from "vue-router";
import {changeNutritionConsumption} from "@/api/recordsService.js";
import {useStore} from "vuex";

const route = useRoute();
const store = useStore();
const router = useRouter();
const consumption = ref('');

const handleSubmit = async () => {
  const body = {
    "name" : route.params.name,
    "measure": consumption.value
  }
  await changeNutritionConsumption(route.params.id , body);
  await router.push({name : 'Record', params: {id: route.params.id }})
}
const handleClose = () => {
  router.go(-1)
}
</script>