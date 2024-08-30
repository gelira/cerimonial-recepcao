import { defineStore } from 'pinia'
import { computed, reactive } from 'vue'

import { apiFetchGuests } from '@/api'
import { removeAccents } from '@/utils'

interface State {
  guests: IGuest[]
  search: string
}

export const useGuestsStore = defineStore('guests', () => {
  const state = reactive<State>({ guests: [], search: '' })
  
  const guests = computed(() => state.guests)
  const search = computed(() => state.search)

  const guestsFiltered = computed(() => {
    if (!state.search) {
      return state.guests
    }

    return state.guests.filter((g) => {
      const cleanedName = removeAccents(g.name.toLowerCase());
      const cleanedSearch = removeAccents(state.search.toLowerCase());

      return cleanedName.includes(cleanedSearch);
    })
  })

  function fetchGuests(eventId: string) {
    apiFetchGuests(eventId)
      .then((data) => state.guests = data.guests)
      .catch(() => state.guests = [])
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

  return { guests, guestsFiltered, search, fetchGuests, setSearch, toggleGuest }
})
