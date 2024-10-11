<script setup lang="ts">
import { computed, reactive, watch } from 'vue'

import { useEventsStore } from '../stores/events'

const props = defineProps<{ title: string, event?: IEvent }>()

const eventsStore = useEventsStore()

const state = reactive({ name: '', date: '', openDialog: false })

const invalidForm = computed(() => {
  const name = (state.name ?? '').trim()
  const date = (state.date ?? '').trim()

  const emptyFields = !name || !date

  if (!props.event) {
    return emptyFields
  }

  return emptyFields || props.event.name === name && props.event.date === date
})

function closeDialog() {
  state.openDialog = false
  state.name = ''
  state.date = ''
}

function save() {
  const name = (state.name ?? '').trim()
  const date = (state.date ?? '').trim()

  if (props.event) {
    eventsStore.updateEvent({ id: props.event.id, name, date })
    closeDialog()
  }
}

function updateState() {
  if (props.event) {
    state.name = props.event.name
    state.date = props.event.date
  }
}

watch(
  () => props.event,
  () => updateState(),
  { immediate: true }
)

watch(
  () => state.openDialog,
  (v) => {
    if (v) {
      updateState()
    }
  }
)
</script>

<template>
  <v-dialog
    persistent
    v-model="state.openDialog"
    transition="dialog-bottom-transition"
  >
    <template #activator="{ props: activatorProps }">
      <slot v-bind="activatorProps"></slot>
    </template>

    <v-row justify="center">
      <v-col
        cols="10"
        md="6"
      >
        <v-card :title="title">
          <v-card-text>
            <v-text-field
              clearable
              label="Nome do evento"
              variant="underlined"
              v-model="state.name"
            ></v-text-field>
            <v-text-field
              label="Data do evento"
              variant="underlined"
              type="date"
              v-model="state.date"
            ></v-text-field>
          </v-card-text>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
              @click="save()"
              :disabled="invalidForm"
              color="success"
            >Salvar</v-btn>
            <v-btn
              @click="closeDialog()"
              color="error"
            >Fechar</v-btn>
          </v-card-actions>
        </v-card>
      </v-col>
    </v-row>
  </v-dialog>
</template>
