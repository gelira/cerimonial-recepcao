<script setup lang="ts">
import { computed, reactive } from 'vue'

import { useGuestsStore } from '../stores/guests'

const props = defineProps<{
  table: ITable,
  guests: IGuestCleaned[],
}>()

const guestsStore = useGuestsStore()

const state = reactive({ guestName: '' })

const invalidForm = computed(() => !(state.guestName ?? '').trim().length)

function addGuest() {
  if (invalidForm.value) {
    return
  }

  guestsStore.createGuest(props.table.eventId, {
    tableId: props.table.id,
    name: state.guestName.trim(),
  })
    .then(() => state.guestName = '')
}
</script>

<template>
  <v-card title="Lista de convidados(as)">
    <v-card-text>
      <v-row>
        <v-col
          cols="12"
          sm="8"
        >
          <v-text-field
            label="Nome do(a) convidado(a)"
            variant="underlined"
            v-model="state.guestName"
            clearable
          ></v-text-field>
        </v-col>
        <v-col
          cols="12"
          sm="4"
        >
          <v-btn
            color="success"
            :disabled="invalidForm"
            @click="addGuest()"
          >Adicionar</v-btn>
        </v-col>
      </v-row>

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
              @click="guestsStore.deleteGuest(guest.id)"
            ></v-btn>
            <span>{{ guest.name }}</span>
          </v-list-item-title>
        </v-list-item>
      </v-list>
    </v-card-text>
  </v-card>
</template>

<style scoped>
.v-list-item-title {
  display: flex;
  align-items: center;
  gap: 12px;
}
</style>