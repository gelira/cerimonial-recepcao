<script setup lang="ts">
import { computed, onMounted } from 'vue'
import { useRoute } from 'vue-router'

import { useEventsStore } from '../stores/events'
import TablesEditList from '../components/TablesEditList.vue'
import EventFormDialog from '../components/EventFormDialog.vue'

const route = useRoute()
const eventsStore = useEventsStore()

const event = computed(() => {
  const eventId = route.params.id as string
  return eventsStore.events.find((e) => e.id === eventId)
})

onMounted(() => {
  if (eventsStore.events.length === 0) {
    eventsStore.fetchEvents()
  }
})
</script>

<template>
  <div class="header-container">
    <h2>{{ event?.name }}</h2>
    <EventFormDialog
      title="Editar evento"
      :event="event"
      v-slot="slotProps"
    >
      <v-btn
        v-bind="slotProps"
        icon="mdi-pencil-box-outline"
        color="primary"
        size="small"
      ></v-btn>
    </EventFormDialog>
  </div>
  <TablesEditList
    v-if="!!event"
    :event="event"
  />
</template>

<style scoped>
.header-container {
  display: flex;
  justify-content: space-between;
  gap: 12px;
  margin: 0 0 8px;
}
</style>