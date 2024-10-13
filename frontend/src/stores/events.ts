import { defineStore } from 'pinia'
import { computed, reactive } from 'vue'

import {
  apiFetchEvents,
  apiUpdateEvent,
  apiDeleteEvent,
  apiFetchGuests,
  apiFetchTables,
} from '../api'

interface State {
  events: IEvent[]
}

export const useEventsStore = defineStore('events', () => {
  const state = reactive<State>({ events: [] })
  
  const events = computed(() => state.events)

  function getEventLocalStorage(eventId: string) {
    const strData = localStorage.getItem(`event-${eventId}`)

    if (!strData) {
      return null
    }

    return JSON.parse(strData) as IEventLocalStorage
  }

  function sortByLocalStorage(events?: IEvent[]) {
    state.events = (events ?? state.events).sort((a, b) => {
      const aExists = !!getEventLocalStorage(a.id)
      const bExists = !!getEventLocalStorage(b.id)

      if (aExists && !bExists) {
        return -1
      }

      if (!aExists && bExists) {
        return 1
      }

      return a.name.localeCompare(b.name)
    })
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

  function deleteEvent(event: IEvent) {
    apiDeleteEvent(event.id)
      .then(() => {
        state.events = state.events.filter((e) => e.id !== event.id)
      })
      .catch(() => {})
  }

  async function saveEventLocalStorage(event: IEvent) {
    const [tablesData, guestsData] = await Promise.all([
      apiFetchTables(event.id),
      apiFetchGuests(event.id),
    ])

    const eventToSave: IEventLocalStorage = {
      event,
      ...tablesData,
      ...guestsData
    }

    localStorage.setItem(`event-${event.id}`, JSON.stringify(eventToSave))

    sortByLocalStorage()
  }

  function deleteEventLocalStorage(event: IEvent) {
    localStorage.removeItem(`event-${event.id}`)

    sortByLocalStorage()
  }

  function fetchEvents() {
    apiFetchEvents()
      .then((data) => sortByLocalStorage(data.events))
      .catch(() => state.events = [])
  }

  return {
    events,
    fetchEvents,
    updateEvent,
    deleteEvent,
    saveEventLocalStorage,
    deleteEventLocalStorage,
    getEventLocalStorage,
  }
})
