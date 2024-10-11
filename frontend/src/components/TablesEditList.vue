<script setup lang="ts">
import { onMounted, onUnmounted } from 'vue'

import TableEditItem from './TableEditItem.vue'
import CreateTableDialog from './CreateTableDialog.vue'
import { useTablesStore } from '../stores/tables'
import { useGuestsStore } from '../stores/guests'

const props = defineProps<{ event: IEvent }>()

const tablesStore = useTablesStore()
const guestsStore = useGuestsStore()

onMounted(() => {
  tablesStore.fetchTables(props.event.id)
  guestsStore.fetchGuests(props.event.id)
})

onUnmounted(() => {
  tablesStore.cleanTables()
  guestsStore.cleanGuests()
})
</script>

<template>
  <v-card>
    <v-card-title>
      <div class="v-card-title">Mesas</div>
      <CreateTableDialog :event="props.event" />
    </v-card-title>
    <v-card-text>
      <v-expansion-panels variant="accordion" elevation="1">
        <TableEditItem
          v-for="table in tablesStore.tables"
          :key="table.id"
          :table="table"
        />
      </v-expansion-panels>
    </v-card-text>
  </v-card>
</template>

<style scoped>
.v-card-title {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
</style>