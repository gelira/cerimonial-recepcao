<script setup lang="ts">
import EventCard from '../components/EventCard.vue';
import { useEventsStore } from '../stores/events';

const eventsStore = useEventsStore();
</script>

<template>
  <v-row>
    <v-col
      v-for="event in eventsStore.events"
      :key="event.id"
      xs="12"
      sm="12"
      md="6"
      lg="4"
      xl="3"
      xxl="3"
    >
      <EventCard :event="event" />
    </v-col>
  </v-row>
</template>
