import { defineStore } from 'pinia'
import { computed, reactive } from 'vue'

import { apiFetchGuests } from '../api'
import { removeAccents } from '../utils'

interface State {
  guests: IGuestCleaned[]
  search: string
}

export const useGuestsStore = defineStore('guests', () => {
  const state = reactive<State>({ guests: [], search: '' })
  
  const guests = computed(() => state.guests)
  const cleanedSearch = computed(() => removeAccents(state.search.toLowerCase()))

  const guestsFiltered = computed(() => {
    if (!cleanedSearch.value) {
      return state.guests
    }

    return state.guests.filter((g) => g.cleanedName.includes(cleanedSearch.value))
  })

  function fetchGuests(eventId: string) {
    apiFetchGuests(eventId)
      .then((data) => {
        state.guests = data.guests.map(
          (g) => ({ ...g, cleanedName: removeAccents(g.name.toLowerCase()) })
        )
      })
      .catch(() => state.guests = [])
  }

  function cleanGuests() {
    state.guests = []
  }

  function setSearch(search: string) {
    state.search = search
  }

  function toggleGuest(guestId: string) {
    state.guests = state.guests.map((g) => {
      if (g.id === guestId) {
        return { ...g, arrived: !g.arrived }
      }

      return g
    })
  }

  return { guests, guestsFiltered, cleanedSearch, fetchGuests, cleanGuests, setSearch, toggleGuest }
})
