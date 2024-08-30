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
  
  const guests = computed(() => {
    if (!state.search) {
      return state.guests
    }

    return state.guests.filter((g) => {
      const cleanedName = removeAccents(g.name.toLowerCase());
      const cleanedSearch = removeAccents(state.search.toLowerCase());

      return cleanedName.includes(cleanedSearch);
    })
  })
  
  const search = computed(() => state.search)

  function fetchGuests(eventId: string) {
    apiFetchGuests(eventId)
      .then((data) => state.guests = data.guests)
      .catch(() => state.guests = [])
  }

  function setSearch(search: string) {
    state.search = search
  }

  return { guests, search, fetchGuests, setSearch }
})
