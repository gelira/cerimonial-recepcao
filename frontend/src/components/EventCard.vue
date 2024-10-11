<script setup lang="ts">
import { computed } from 'vue'
import { useRouter } from 'vue-router'

import { ROUTE_NAMES } from '../utils/consts'

const props = defineProps<{ event: IEvent }>()

const router = useRouter()

const formattedDate = computed(() => {
  const [year, month, day] = props.event.date.split('-')
  return `${day}/${month}/${year}`
})

function goToEditView() {
  router.push({
    name: ROUTE_NAMES.EDIT_EVENT,
    params: {
      id: props.event.id,
    },
  })
}
</script>

<template>
  <v-card>
    <v-card-title>{{ event.name }}</v-card-title>
    <v-card-subtitle>{{ formattedDate }}</v-card-subtitle>
    <v-card-actions class="justify-end">
      <v-btn icon="mdi-pencil-box-outline" @click="goToEditView()"></v-btn>
      <v-btn icon="mdi-clipboard-text-outline"></v-btn>
      <v-btn icon="mdi-content-save-outline"></v-btn>
    </v-card-actions>
  </v-card>
</template>