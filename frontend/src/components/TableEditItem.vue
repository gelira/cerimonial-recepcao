<script setup lang="ts">
import { computed } from 'vue';

import { useGuestsStore } from '../stores/guests';

const props = defineProps<{ table: ITable }>();

const guestsStore = useGuestsStore();

const guests = computed(() => {
  return guestsStore.guests.filter((g) => g.tableId === props.table.id);
})
</script>

<template>
  <v-expansion-panel :title="props.table.identifier">
    <v-expansion-panel-text>{{ JSON.stringify(guests) }}</v-expansion-panel-text>
  </v-expansion-panel>
</template>
