<script setup lang="ts">
import { computed, reactive } from 'vue'

import { useTablesStore } from '../stores/tables'

const props = defineProps<{ eventId: string }>()

const tablesStore = useTablesStore()

const state = reactive({
  openDialog: false,
  identifier: '',
  locationDescription: '',
})

const invalidForm = computed(() => !state.identifier?.length)

function closeDialog() {
  state.openDialog = false
  state.identifier = ''
  state.locationDescription = ''
}

function save() {
  if (invalidForm.value) {
    return
  }

  tablesStore.createTable({
    id: '',
    eventId: props.eventId,
    identifier: state.identifier ?? '',
    locationDescription: state.locationDescription ?? '',
  })
    .then(() => closeDialog())
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
        icon="mdi-plus"
        color="success"
        size="small"
      ></v-btn>
    </template>

    <v-row justify="center">
      <v-col class="v-col-auto">
        <v-card>
          <v-card-title>Nova Mesa</v-card-title>
          <v-card-text>
            <v-text-field
              clearable
              label="Identificador da mesa"
              variant="underlined"
              v-model="state.identifier"
            ></v-text-field>
            <v-text-field
              clearable
              label="Localização"
              variant="underlined"
              v-model="state.locationDescription"
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

<style scoped>
@media screen and (min-width: 64em) {
  .v-card {
    min-width: 600px;
  }
}
</style>
