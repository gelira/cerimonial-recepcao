<script setup lang="ts">
import { computed } from 'vue';

import GuestComponent from '@/components/GuestComponent.vue';
import { useGuestsStore } from '@/stores/guests';

const props = defineProps<{ table: ITable }>();
const guestsStore = useGuestsStore();

const guests = computed(() => guestsStore.guests.filter((g) => g.tableId === props.table.id));
</script>

<template>
  <div v-show="guests.length > 0">
    <h4>{{ table.identifier }}</h4>
    <ul>
      <li v-for="guest in guests" :key="guest.id">
        <GuestComponent :guest="guest" />
      </li>
    </ul>
  </div>
</template>
