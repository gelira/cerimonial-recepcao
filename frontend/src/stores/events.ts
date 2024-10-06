import { defineStore } from 'pinia'
import { computed, reactive } from 'vue'

import { apiFetchEvents, apiUpdateEvent } from '../api'

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

  function updateEvent(event: IEvent) {
    apiUpdateEvent(event)
      .then(() => {
        const { id, name, date } = event
        state.events = state.events.map((e) => {
          if (e.id !== id) {
            return e
          }
          return { ...e, date, name }
        })
      })
      .catch(() => {})
  }

  return { events, fetchEvents, updateEvent }
})
