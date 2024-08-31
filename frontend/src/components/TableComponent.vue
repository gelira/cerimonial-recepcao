<script setup lang="ts">
import { computed } from 'vue';

import GuestComponent from '@/components/GuestComponent.vue';
import { useGuestsStore } from '@/stores/guests';

const props = defineProps<{ table: ITable }>();
const guestsStore = useGuestsStore();

const filterCb = (tableId: string) => (g: IGuestCleaned) => g.tableId === tableId;

const guestsTable = computed(() => {
  return guestsStore.guests.filter(filterCb(props.table.id)).sort((a, b) => {
    const aIsFiltered = guestsStore.guestsFiltered.some((g) => g.id === a.id);
    const bIsFiltered = guestsStore.guestsFiltered.some((g) => g.id === b.id);

    if (aIsFiltered && !bIsFiltered) {
      return -1;
    }
    
    if (!aIsFiltered && bIsFiltered) {
      return 1;
    }
    
    return a.name.localeCompare(b.name);
  });
});

const hasFilteredGuests = computed(
  () => guestsStore.guestsFiltered.filter(filterCb(props.table.id)).length > 0
);
</script>

<template>
  <div v-show="hasFilteredGuests">
    <h4>{{ table.identifier }}</h4>
    <ul>
      <li v-for="guest in guestsTable" :key="guest.id">
        <GuestComponent :guest="guest" />
      </li>
    </ul>
  </div>
</template>
