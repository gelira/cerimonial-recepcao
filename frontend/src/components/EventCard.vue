<script setup lang="ts">
import { computed, reactive, watch } from 'vue'
import { useRouter } from 'vue-router'

import { apiFetchGuests, apiFetchTables } from '../api'
import { ROUTE_NAMES } from '../utils/consts'
import { formatDate } from '../utils'

const props = defineProps<{ event: IEvent }>()

const state = reactive({
  countForceRerender: 0,
  saveLocalLoading: false,
  savedLocal: false,
})

const router = useRouter()

const formattedDate = computed(() => formatDate(props.event.date))

function goToEditView() {
  router.push({
    name: ROUTE_NAMES.EDIT_EVENT,
    params: {
      id: props.event.id,
    },
  })
}

async function saveLocal() {
  if (state.saveLocalLoading) {
    return
  }
  
  const event = props.event

  state.saveLocalLoading = true

  try {
    const [tablesData, guestsData] = await Promise.all([
      apiFetchTables(event.id),
      apiFetchGuests(event.id),
    ])

    const eventToSave: IEventSavedLocal = {
      event,
      ...tablesData,
      ...guestsData
    }

    localStorage.setItem(`event-${event.id}`, JSON.stringify(eventToSave))
  } catch {
  } finally {
    state.countForceRerender ++
    state.saveLocalLoading = false
  }
}

watch(
  () => state.countForceRerender,
  () => {
    state.savedLocal = !!localStorage.getItem(`event-${props.event.id}`)
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
      ></v-btn>
      <v-btn
        :loading="state.saveLocalLoading"
        :icon="state.savedLocal ? 'mdi-content-save-check-outline' : 'mdi-content-save-outline'"
        :color="state.savedLocal && !state.saveLocalLoading ? 'success' : undefined"
        @click="saveLocal()"
      ></v-btn>
    </v-card-actions>
  </v-card>
</template>