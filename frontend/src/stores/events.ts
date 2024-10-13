import { defineStore } from 'pinia'
import { computed, reactive, watch } from 'vue'

import {
  apiFetchEvents,
  apiUpdateEvent,
  apiDeleteEvent,
  apiFetchGuests,
  apiFetchTables,
} from '../api'

import {
  saveEventDataToLocalStorage,
  loadEventDataFromLocalStorage,
  addEventSaved,
  removeEventDataFromLocalStorage,
  removeEventSaved,
  getListEventSaved,
} from '../utils/localstorage'

interface State {
  events: IEvent[]
}

export const useEventsStore = defineStore('events', () => {
  const state = reactive<State>({ events: [] })
  
  const events = computed(() => state.events)

  function getEventLocalStorage(eventId: string) {
    return loadEventDataFromLocalStorage(eventId)
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

    const guestsPreviousSaved = loadEventDataFromLocalStorage(event.id)?.guests ?? []

    guestsData.guests = guestsData.guests.map((g) => ({
      ...g,
      arrived: !!guestsPreviousSaved.find((gp) => gp.id === g.id)?.arrived
    }))

    saveEventDataToLocalStorage(event.id, {
      event,
      ...tablesData,
      ...guestsData,
    })

    addEventSaved(event.id)

    sortByLocalStorage()
  }

  function deleteEventLocalStorage(event: IEvent) {
    removeEventDataFromLocalStorage(event.id)
    removeEventSaved(event.id)
    sortByLocalStorage()
  }

  function fetchEvents() {
    apiFetchEvents()
      .then((data) => sortByLocalStorage(data.events))
      .catch(() => state.events = [])
  }

  watch(
    () => state.events,
    (value) => {
      getListEventSaved().forEach((e) => {
        if (value.every((v) => v.id !== e)) {
          removeEventDataFromLocalStorage(e)
          removeEventSaved(e)
        }
      })
    }
  )

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
