<script setup lang="ts">
import { computed } from 'vue'

import { useGuestsStore } from '../stores/guests'
import {
  loadEventDataFromLocalStorage,
  saveEventDataToLocalStorage,
} from '../utils/localstorage'

const props = defineProps<{ guest: IGuestCleaned }>()

const guestsStore = useGuestsStore()

const nameSplitted = computed<{ part: string, decorate: boolean }[]>(() => {
  const guestName = props.guest.name
  const cleanedName = props.guest.cleanedName
  const cleanedSearch = guestsStore.cleanedSearch

  if (!cleanedSearch || !cleanedName.includes(cleanedSearch)) {
    return [ { part: guestName, decorate: false } ]
  }

  const startIndex = cleanedName.indexOf(cleanedSearch)
  const endIndex = startIndex + cleanedSearch.length

  return [
    { part: guestName.substring(0, startIndex), decorate: false },
    { part: guestName.substring(startIndex, endIndex), decorate: true },
    { part: guestName.substring(endIndex), decorate: false },
  ]
})

function handleClick() {
  guestsStore.toggleGuest(props.guest.id)

  const data = loadEventDataFromLocalStorage(props.guest.eventId)

  if (data) {
    data.guests = data.guests.map((g) => ({
      ...g,
      arrived: g.id === props.guest.id ? !props.guest.arrived : g.arrived
    }))

    saveEventDataToLocalStorage(props.guest.eventId, data)
  }
}
</script>

<template>
  <v-row>
    <v-col>
      <v-btn
        variant="text"
        :prepend-icon="guest.arrived ? 'mdi-checkbox-outline' : 'mdi-checkbox-blank-outline'"
        :color="guest.arrived ? 'success' : undefined"
        @click="handleClick()"
      >
        <span
          :class="{ arrived: guest.arrived }"
        >
          <span
            v-for="{ part, decorate } in nameSplitted"
            :key="part"
            :class="{ decorate }"
          >
            {{ part }}
          </span>
        </span>
      </v-btn>
    </v-col>
  </v-row>
</template>

<style scoped>
.arrived {
  text-decoration: line-through;
}

.decorate {
  font-weight: bold;
  text-decoration: underline;
}
</style>
