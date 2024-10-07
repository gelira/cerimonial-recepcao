<script setup lang="ts">
import { computed } from 'vue'

import { useGuestsStore } from '../stores/guests'

const props = defineProps<{ tableId: string }>()

const guestsStore = useGuestsStore()

const guests = computed(
  () => guestsStore.guests.filter((g) => g.tableId === props.tableId)
)
</script>

<template>
  <v-list>
    <v-list-item
      v-for="guest in guests"
      :key="guest.id"
      variant="plain"
    >
      <v-list-item-title>
        <v-btn
          icon="mdi-trash-can-outline"
          color="error"
          size="small"
          variant="tonal"
        ></v-btn>
        <span>{{ guest.name }}</span>
      </v-list-item-title>
    </v-list-item>
  </v-list>
</template>

<style scoped>
.v-list-item-title {
  display: flex;
  align-items: center;
  gap: 12px;
}

@media screen and (min-width: 64em) {
  .v-list-item:hover {
    background-color: rgba(0, 0, 0, 0.04);
  }
}
</style>