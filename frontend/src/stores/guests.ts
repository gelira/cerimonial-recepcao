import { defineStore } from 'pinia'
import { computed, reactive } from 'vue'

import { apiFetchGuests } from '@/api'

interface State {
  guests: IGuest[]
}

export const useGuestsStore = defineStore('guests', () => {
  const state = reactive<State>({ guests: [] })
  
  const guests = computed(() => state.guests)
  
  function fetchGuests(eventId: string) {
    apiFetchGuests(eventId)
      .then((data) => state.guests = data.guests)
      .catch(() => state.guests = [])
  }

  return { guests, fetchGuests }
})
