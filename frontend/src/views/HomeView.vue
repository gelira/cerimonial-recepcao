<script setup lang="ts">
import { onMounted } from 'vue';
import { RouterLink, RouterView } from 'vue-router';

import { useEventsStore } from '@/stores/events';

const eventsStore = useEventsStore();

onMounted(() => {
  eventsStore.fetchEvents();
});
</script>

<template>
  <h1>Cerimonial</h1>
  <ul>
    <li v-for="event in eventsStore.events" :key="event.id">
      <RouterLink :to="{ name: 'event', params: { id: event.id } }">
        {{ event.name }}
      </RouterLink>
    </li>
  </ul>
  <RouterView />
</template>
