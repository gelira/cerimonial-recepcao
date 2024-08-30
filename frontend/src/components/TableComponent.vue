<script setup lang="ts">
import { computed } from 'vue';

import { useGuestsStore } from '@/stores/guests';

const props = defineProps<{ table: ITable }>();
const guestsStore = useGuestsStore();

const guests = computed(() => guestsStore.guests.filter((g) => g.tableId === props.table.id));
</script>

<template>
  <h4>{{ table.identifier }}</h4>
  <ul>
    <li v-for="guest in guests" :key="guest.id">
      {{ guest.name }}
    </li>
  </ul>
</template>
