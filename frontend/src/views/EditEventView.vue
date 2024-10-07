<script setup lang="ts">
import { computed } from 'vue';
import { useRoute } from 'vue-router';

import EventForm from '../components/EventForm.vue';
import TablesEditList from '../components/TablesEditList.vue';
import { useEventsStore } from '../stores/events';

const route = useRoute();
const eventsStore = useEventsStore();

const eventId = computed(() => route.params.id as string);

const event = computed(() => {
  return eventsStore.events.find((e) => e.id === eventId.value);
});

function save(value: { name: string, date: string }) {
  if (event.value) {
    eventsStore.updateEvent({ id: event.value.id, ...value });
  }
}
</script>

<template>
  <EventForm :event="event" @save="save" />
  <TablesEditList :event-id="eventId" />
</template>
