<template>
  <div class="bg-amber-500 h-screen">
    <div class="flex flex-row gap-2 justify-center">
      <div
          @click="toggleMinerals"
          class="overflow-hidden m-8 w-1/4 text-center rounded-2xl text-gray-300 p-1 text-2xl border-2 border-gray-300 font-mono hover:cursor-pointer
         hover:bg-amber-200 hover:border-amber-200 hover:text-black hover:text-3xl transition-all ease-in-out duration-300">
        Minerals
      </div>
      <div
          @click="toggleMacros"
          class="overflow-hidden m-8 w-1/4 text-center rounded-2xl text-gray-300 p-1 text-2xl border-2 border-gray-300 font-mono hover:cursor-pointer
         hover:bg-amber-200 hover:border-amber-200 hover:text-black hover:text-3xl transition-all ease-in-out duration-300">
        Macronutrients
      </div>
      <div
          @click="toggleVitamins"
          class="overflow-hidden m-8 w-1/4 text-center rounded-2xl text-gray-300 p-1 text-2xl border-2 border-gray-300 font-mono hover:cursor-pointer
         hover:bg-amber-200 hover:border-amber-200 hover:text-black hover:text-3xl transition-all ease-in-out duration-300">
        Vitamins
      </div>
    </div>
    <div class="flex flex-wrap gap-2 justify-center">
      <router-link
          v-if="minerals.show"
          v-for="mineral in minerals.values"
          :to="{name: 'NutrientView' , params: {type: 'electrolyte' , name: mineral}}"
          :key="mineral"
          class="p-2 overflow-hidden m-8 text-center rounded-2xl text-gray-300 text-xl border-2 border-gray-300 font-mono hover:cursor-pointer hover:bg-amber-200 hover:border-amber-200 hover:text-black hover:text-2xl transition-all ease-in-out duration-300"
      >
        {{ mineral }}
      </router-link>
      <div class="flex flex-wrap gap-2 justify-center">
        <router-link
            v-if="macros.show"
            v-for="macro in macros.values"
            :to="{name: 'NutrientView' , params: {type: 'macronutrient' , name: macro}}"
            :key="macro"
            class="p-2 overflow-hidden m-8 text-center rounded-2xl text-gray-300 text-xl border-2 border-gray-300 font-mono hover:cursor-pointer hover:bg-amber-200 hover:border-amber-200 hover:text-black hover:text-2xl transition-all ease-in-out duration-300"
        >
          {{ macro }}
        </router-link>
      </div>
      <div class="flex flex-wrap gap-2 justify-center">
        <router-link
            v-if="vitamins.show"
            v-for="vitamin in vitamins.values"
            :to="{name: 'NutrientView' , params: {type: 'vitamin' , name: vitamin}}"
            :key="vitamin"
            class="p-2 overflow-hidden m-8 text-center rounded-2xl text-gray-300 text-xl border-2 border-gray-300 font-mono hover:cursor-pointer hover:bg-amber-200 hover:border-amber-200 hover:text-black hover:text-2xl transition-all ease-in-out duration-300"
        >
          Vitamin {{ vitamin }}
        </router-link>
      </div>
    </div>
  </div>
</template>
<script setup>
import {ref} from "vue";

const minerals = ref(
    {
      values: ['Sodium', "Potassium", "Calcium", "Magnesium", "Chloride"],
      show: false
    }
)
const macros = ref(
    {
      values: ["Carbohydrates", "Protein", "Fat"],
      show: false
    }
)
const vitamins = ref(
    {
      values: ["A", "D", "E", "K", "C", "B1", "B2", "B3", "B5", "B6", "B7", "B9", "B12"],
      show: false
    }
)

const toggleMinerals = () => {
  macros.value.show = false;
  vitamins.value.show = false;

  if (minerals.value.show) {
    minerals.value.show = false;
    return;
  }
  minerals.value.show = true;
}
const toggleMacros = () => {
  minerals.value.show = false;
  vitamins.value.show = false;

  if (macros.value.show) {
    macros.value.show = false;
    return;
  }
  macros.value.show = true;
}
const toggleVitamins = () => {
  minerals.value.show = false;
  macros.value.show = false;

  if (vitamins.value.show) {
    vitamins.value.show = false;
    return;
  }
  vitamins.value.show = true;
}
</script>
