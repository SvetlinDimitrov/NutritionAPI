<template>
  <div class="bg-amber-500 h-full flex flex-col justify-center items-center">
    <p v-if="$store.getters.isSessionExpired"
       class="text-3xl font-mono text-red-600"
    >First login and then we gonna talk !</p>
    <div @click="goOnMyProfilePage"
         v-if="!$store.getters.isSessionExpired"
         class="m-8 w-1/2 text-center rounded-2xl text-gray-300 p-1 text-2xl border-2 border-gray-300 font-mono hover:cursor-pointer
         hover:bg-amber-200 hover:border-amber-200 hover:text-black hover:text-3xl transition-all ease-in-out duration-300">
      My Profile
    </div>
    <p v-if="$store.getters.isSessionExpired
    && $store.state.userView
    && $store.state.userView.userDetails === 'NOT_COMPLETED'"
       class="text-2xl font-mono text-red-600 text-center"
    >If you want use this app you will need to fill additional fields in your profile.</p>

    <div v-if="!$store.getters.isSessionExpired && $store.state.userView.userDetails === 'COMPLETED'"
         class="flex flex-col gap-4 justify-center items-center m-8 w-1/2 text-gray-800 border border-gray-300 rounded-lg shadow-md p-6">
      <h2 class="text-2xl font-semibold mb-4">My Records</h2>

      <div v-for="record in $store.state.records"
           :key="record.id"
           @click="handleRecordClick(record.id)"
           class="flex justify-between items-center mb-4 hover:cursor-pointer bg-amber-300 hover:bg-gray-100 p-4 rounded-lg w-full">
        <div class="flex items-center">
          <span class="text-lg">{{ record.dailyCaloriesToConsume }}</span>
          <span class="text-gray-600 ml-2">cal to consume</span>
        </div>
        <button @click.stop="deleteRecord(record.id)"
                class="bg-red-500 text-white px-4 py-2 rounded-md hover:bg-red-600 transition duration-300">Delete
        </button>
      </div>

      <button @click="addRecord" class="bg-green-500 text-white px-4 py-2 rounded-md">Add Record</button>
    </div>
  </div>
</template>

<script setup>
import {useRouter} from "vue-router";
import {onMounted, ref, watchEffect} from "vue";
import {useStore} from "vuex";

const store = useStore();
const router = useRouter()
const records = ref([]);
const goOnMyProfilePage = () => {
  router.push('/profile');
}

watchEffect(() => {
  if (!store.getters.isSessionExpired && store.state.userView.userDetails === 'COMPLETED' && store.state.extractRecords) {
    store.dispatch('getRecords')
  }
})
const handleRecordClick = (recordId) => {
  router.push({name: 'Record', params: {id: recordId}})
}
const deleteRecord = (recordId) => {
  store.dispatch('deleteRecord', recordId)
}
const addRecord = () => {
  store.dispatch('addRecord')
}
</script>
