<script setup lang="ts">
import { computed, reactive, watch } from 'vue'
import { useRouter } from 'vue-router'

import { useEventsStore } from '../stores/events'
import { ROUTE_NAMES } from '../utils/consts'
import { formatDate } from '../utils'
import DeleteEventDialog from './DeleteEventDialog.vue'

const props = defineProps<{ event: IEvent }>()

const state = reactive({
  countForceRerender: 0,
  saveLocalLoading: false,
  savedLocal: false,
})

const router = useRouter()
const eventsStore = useEventsStore()

const formattedDate = computed(() => formatDate(props.event.date))

function goToEditView() {
  router.push({
    name: ROUTE_NAMES.EDIT_EVENT,
    params: {
      id: props.event.id,
    },
  })
}

function goToEventReceptionView() {
  router.push({
    name: ROUTE_NAMES.EVENT_RECEPTION,
    params: {
      id: props.event.id,
    },
  })
}

async function saveLocalStorage() {
  if (state.saveLocalLoading) {
    return
  }

  state.saveLocalLoading = true

  try {
    await eventsStore.saveEventLocalStorage(props.event)
  } catch {
  } finally {
    state.countForceRerender ++
    state.saveLocalLoading = false
  }
}

watch(
  () => state.countForceRerender,
  () => {
    state.savedLocal = !!eventsStore.getEventLocalStorage(props.event.id)
  },
  { immediate: true }
)
</script>

<template>
  <v-card>
    <v-card-title>{{ event.name }}</v-card-title>
    <v-card-subtitle>{{ formattedDate }}</v-card-subtitle>
    <v-card-actions class="justify-end">
      <v-btn
        icon="mdi-pencil-box-outline"
        @click="goToEditView()"
      ></v-btn>
      <v-btn
        icon="mdi-clipboard-text-outline"
        :disabled="!state.savedLocal"
        @click="goToEventReceptionView()"
      ></v-btn>
      <v-btn
        :loading="state.saveLocalLoading"
        :icon="state.savedLocal ? 'mdi-content-save-check-outline' : 'mdi-content-save-outline'"
        :color="state.savedLocal && !state.saveLocalLoading ? 'success' : undefined"
        @click="saveLocalStorage()"
      ></v-btn>
      <DeleteEventDialog
        :event="event"
        @deleted="state.countForceRerender++"
      />
    </v-card-actions>
  </v-card>
</template>