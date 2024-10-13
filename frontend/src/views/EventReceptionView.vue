<script setup lang="ts">
import { onMounted } from 'vue'
import { useRoute, useRouter } from 'vue-router'

import { ROUTE_NAMES } from '../utils/consts'
import { loadEventDataFromLocalStorage } from '../utils/localstorage'
import { useGuestsStore } from '../stores/guests'
import { useTablesStore } from '../stores/tables'

const route = useRoute()
const router = useRouter()
const guestsStore = useGuestsStore()
const tablesStore = useTablesStore()

onMounted(() => {
  const eventId = route.params.id as string

  const eventSaved = loadEventDataFromLocalStorage(eventId)

  if (!eventSaved) {
    return router.push({ name: ROUTE_NAMES.LIST_EVENTS })
  }

  tablesStore.setTables(eventSaved.tables)
  guestsStore.setGuests(eventSaved.guests)
})
</script>

<template>
  <h2>HELLO WORLD</h2>
  <div>{{ JSON.stringify(tablesStore.tables) }}</div>
  <div>{{ JSON.stringify(guestsStore.guests) }}</div>
</template>