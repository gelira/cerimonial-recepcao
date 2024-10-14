<script setup lang="ts">
import { computed } from 'vue'

import GuestComponent from '../components/GuestComponent.vue'
import { useGuestsStore } from '../stores/guests'

const props = defineProps<{ table: ITable }>()

const guestsStore = useGuestsStore()

const filterCb = (g: IGuestCleaned) => g.tableId === props.table.id

const guestsTable = computed(() => {
  return guestsStore.guests.filter(filterCb).sort((a, b) => {
    const aIsFiltered = guestsStore.guestsFiltered.some((g) => g.id === a.id)
    const bIsFiltered = guestsStore.guestsFiltered.some((g) => g.id === b.id)

    if (aIsFiltered && !bIsFiltered) {
      return -1
    }
    
    if (!aIsFiltered && bIsFiltered) {
      return 1
    }
    
    return a.name.localeCompare(b.name)
  })
})

const hasFilteredGuests = computed(
  () => guestsStore.guestsFiltered.filter(filterCb).length > 0
)
</script>

<template>
  <v-expansion-panel
    v-show="hasFilteredGuests"
    :value="table.id"
    :title="table.identifier"
  >
    <v-expansion-panel-text>
      <GuestComponent
        v-for="guest in guestsTable"
        :key="guest.id"
        :guest="guest"
      />
    </v-expansion-panel-text>
  </v-expansion-panel>
</template>
