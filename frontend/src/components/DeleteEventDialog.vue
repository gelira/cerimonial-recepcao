<script setup lang="ts">
import { reactive } from 'vue'

import { useEventsStore } from '../stores/events'

const props = defineProps<{ event: IEvent }>()
const emit = defineEmits<{ (e: 'deleted'): void }>()

const eventsStore = useEventsStore()

const state = reactive({
  openDialog: false,
})

function closeDialog() {
  state.openDialog = false
}

function deleteEventLocalStorage() {
  closeDialog()
  eventsStore.deleteEventLocalStorage(props.event)
  
  emit('deleted')
}

function deleteEvent() {
  eventsStore.deleteEvent(props.event)
  deleteEventLocalStorage()
}
</script>

<template>
  <v-dialog
    persistent
    v-model="state.openDialog"
    transition="dialog-bottom-transition"
  >
    <template #activator="{ props: activatorProps }">
      <v-btn
        v-bind="activatorProps"
        icon="mdi-trash-can-outline"
        color="error"
      ></v-btn>
    </template>

    <v-row justify="center">
      <v-col class="v-col-auto">
        <v-card>
          <v-card-title>Deletar evento</v-card-title>
          <v-card-text>
            Deseja mesmo deletar o evento {{ event.name }}?
          </v-card-text>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
              @click="deleteEvent()"
              color="error"
              variant="elevated"
            >Deletar</v-btn>
            <v-btn
              @click="deleteEventLocalStorage()"
              v-if="!!eventsStore.getEventLocalStorage(event.id)"
              color="error"
              variant="elevated"
            >Deletar Localmente</v-btn>
            <v-btn
              @click="closeDialog()"
              color="primary"
              variant="elevated"
            >Fechar</v-btn>
          </v-card-actions>
        </v-card>
      </v-col>
    </v-row>
  </v-dialog>
</template>

<style scoped>
@media screen and (min-width: 64em) {
  .v-card {
    min-width: 600px;
  }
}
</style>
