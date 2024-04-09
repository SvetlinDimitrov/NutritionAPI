<template>
  <div v-if="info" class="bg-amber-500 h-full p-5">
    <div class="bg-amber-500 h-full p-5">
      <div class="bg-amber-200 w-1/2 h-full mx-auto overflow-auto p-6 rounded-lg shadow-md">
        <h2 class="text-2xl font-semibold mb-4">{{ info.name }}</h2>
        <p class="text-gray-700 mb-6">{{ info.description }}</p>

        <div>
          <h3 class="text-lg font-semibold mb-2">Functions:</h3>
          <ul class="list-disc list-inside">
            <li v-for="func in info.functions" :key="func.key">
              <strong>{{ func.key }}:</strong> {{ func.value }}
            </li>
          </ul>
        </div>

        <div class="mt-6">
          <h3 class="text-lg font-semibold mb-2">Sources:</h3>
          <ul class="list-disc list-inside">
            <li v-for="source in info.sources" :key="source.key">
              <strong>{{ source.key }}:</strong> {{ source.value }}
            </li>
          </ul>
        </div>

        <div class="mt-6">
          <h3 class="text-lg font-semibold mb-2">Health Considerations:</h3>
          <ul class="list-disc list-inside">
            <li v-for="consideration in info.healthConsiderations" :key="consideration.key">
              <strong>{{ consideration.key }}:</strong> {{ consideration.value }}
            </li>
          </ul>
        </div>

        <div class="mt-6">
          <h3 class="text-lg font-semibold mb-2">Recommended Intake:</h3>
          <p class="text-gray-700">
            Male: {{ info.maleLowerBoundIntake }} - {{ info.maleHigherBoundIntake }} {{ info.measure }}<br>
            Female: {{ info.femaleLowerBoundIntake }} - {{ info.femaleHigherBoundIntake }} {{ info.measure }}
          </p>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import {ref, watchEffect} from "vue";
import {useRoute} from 'vue-router'
import {getNutrientInfo} from "@/api/nutrientApi.js";

const route = useRoute()
const info = ref();
watchEffect(() => {
  getNutrientInfo(route.params.type, route.params.name)
      .then(data => info.value = data)
})

</script>
