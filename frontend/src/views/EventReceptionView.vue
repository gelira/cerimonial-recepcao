<script setup lang="ts">
import { onMounted, reactive, watch } from 'vue'
import { useRoute, useRouter } from 'vue-router'

import TableComponent from '../components/TableComponent.vue'
import { useGuestsStore } from '../stores/guests'
import { useTablesStore } from '../stores/tables'
import { ROUTE_NAMES } from '../utils/consts'
import { loadEventDataFromLocalStorage } from '../utils/localstorage'

const route = useRoute()
const router = useRouter()
const guestsStore = useGuestsStore()
const tablesStore = useTablesStore()

const state = reactive<{
  search: string,
  tableIds: string[],
  event?: IEvent
}>({ search: '', tableIds: [] })

watch(
  () => state.search ?? '', 
  (value) => guestsStore.setSearch(value)
)

onMounted(() => {
  const eventId = route.params.id as string

  const eventSaved = loadEventDataFromLocalStorage(eventId)

  if (!eventSaved) {
    return router.push({ name: ROUTE_NAMES.LIST_EVENTS })
  }

  tablesStore.setTables(eventSaved.tables)
  guestsStore.setGuests(eventSaved.guests)

  state.event = eventSaved.event
  state.tableIds = eventSaved.tables.map(({ id }) => id)
})
</script>

<template>
  <h2>Evento {{ state.event?.name }}</h2>
  <v-row>
    <v-col>
      <v-text-field
        clearable
        label="Procurar convidado"
        variant="underlined"
        v-model="state.search"
      ></v-text-field>
    </v-col>
  </v-row>
  <v-expansion-panels
    multiple
    v-model="state.tableIds"
  >
    <TableComponent
      v-for="table in tablesStore.tables"
      :key="table.id"
      :table="table"
    />
  </v-expansion-panels>
</template>