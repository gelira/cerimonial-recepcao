import { defineStore } from 'pinia'
import { computed, reactive } from 'vue'

import { apiFetchEvents } from '@/api'

interface State {
  events: IEvent[]
}

export const useEventsStore = defineStore('events', () => {
  const state = reactive<State>({ events: [] })
  
  const events = computed(() => state.events)
  
  function fetchEvents() {
    apiFetchEvents()
      .then((data) => state.events = data.events)
      .catch(() => state.events = [])
  }

  return { events, fetchEvents }
})
