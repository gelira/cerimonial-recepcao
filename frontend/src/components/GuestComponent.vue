<script setup lang="ts">
import { computed } from 'vue'

import { useGuestsStore } from '../stores/guests'

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
</script>

<template>
  <v-row>
    <v-col>
      <v-btn
        variant="text"
        :prepend-icon="guest.arrived ? 'mdi-checkbox-outline' : 'mdi-checkbox-blank-outline'"
        :color="guest.arrived ? 'success' : undefined"
        @click="guestsStore.toggleGuest(guest.id)"
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
