<template>
  <div v-if="record && record.dailyCaloriesToConsume !== undefined"
      class="bg-amber-500 h-full flex flex-col justify-center items-center">
    <div class=" h-full container mx-auto p-6 overflow-auto">
      <div class="bg-gray-100 rounded-lg shadow-md p-6">

        <div class="flex items-center justify-between mb-4">
          <h3 class="text-xl font-semibold">Daily Calories to Consume</h3>
          <span class="text-lg font-semibold">{{ record.dailyCaloriesToConsume }}</span>
        </div>

        <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-4">
          <div v-for="intakeView in record.dailyIntakeViews" :key="intakeView.id" class="bg-white rounded-lg shadow-md p-4">
            <h4 class="text-lg font-semibold mb-2">{{ intakeView.nutrientName }}</h4>
            <p class="text-sm text-gray-600 mb-2">{{ intakeView.measurement }}</p>
            <div class="flex items-center justify-between">
              <p class="text-sm text-gray-600">Lower Bound Intake: {{ intakeView.lowerBoundIntake }}</p>
              <p class="text-sm text-gray-600">Upper Bound Intake: {{ intakeView.upperBoundIntake }}</p>
            </div>
            <p class="text-sm text-gray-600">Daily Consumed: {{intakeView.dailyConsumed}}</p>
            <button
                @click="addConsumptionRedirect(intakeView.nutrientName)"
                class="text-sm bottom-2 right-2 bg-blue-500 text-white px-4 py-2 mt-2 rounded-md hover:bg-blue-600 transition duration-300">Add consumed</button>
          </div>
        </div>

        <div class="mt-4 text-sm text-gray-600">
          <p>User ID: {{ record.userID }}</p>
          <p>User Name: {{ record.userName }}</p>
        </div>
      </div>
    </div>
  </div>
  <router-view></router-view>
</template>

<script setup>
import {onMounted, ref, watchEffect} from "vue";
import {getRecordById} from "@/api/recordsService.js";
import {useRoute, useRouter} from "vue-router";

const router = useRouter()
const route = useRoute()
const record = ref();

watchEffect(() => {
  getRecordById(route.params.id)
      .then(data => record.value = data)
      .catch(() => router.push('/error'));
})
const addConsumptionRedirect = (name) => {
  router.push({name: 'ChangeConsumption' , params: {name: name}})
}
</script>