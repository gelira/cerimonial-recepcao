<script setup lang="ts">
import { computed } from 'vue';
import { useRoute } from 'vue-router';

import { useEventsStore } from '../stores/events';
import EventForm from '../components/EventForm.vue';

const route = useRoute();
const eventsStore = useEventsStore();

const event = computed(() => {
  const eventId = route.params.id as string;
  return eventsStore.events.find((e) => e.id === eventId);
});

function save(value: { name: string, date: string }) {
  if (event.value) {
    eventsStore.updateEvent({ id: event.value.id, ...value });
  }
}
</script>

<template>
  <EventForm :event="event" @save="save" />
</template>