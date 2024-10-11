<script setup lang="ts">
import { reactive } from 'vue'

import { useTablesStore } from '../stores/tables'
import TableForm from './TableForm.vue'

const props = defineProps<{ event: IEvent }>()

const tablesStore = useTablesStore()

const state = reactive({
  openDialog: false,
})

function closeDialog() {
  state.openDialog = false
}

function save(value: { identifier: string, locationDescription: string }) {
  tablesStore.createTable({
    id: '',
    eventId: props.event.id,
    identifier: value.identifier,
    locationDescription: value.locationDescription,
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

    <TableForm
      title="Nova mesa"
      @save="save"
      @close="closeDialog"
    />
  </v-dialog>
</template>
