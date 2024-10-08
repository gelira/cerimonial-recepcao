<script setup lang="ts">
import { reactive } from 'vue'

import { useTablesStore } from '../stores/tables'
import TableForm from './TableForm.vue';

const props = defineProps<{ table: ITable }>()

const tablesStore = useTablesStore()

const state = reactive({
  openDialog: false,
})

function closeDialog() {
  state.openDialog = false
}

function save(value: { identifier: string, locationDescription: string }) {
  tablesStore.updateTable(props.table.id, value)
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
        icon="mdi-pencil-box-outline"
        color="primary"
        size="small"
      ></v-btn>
    </template>

    <TableForm
      title="Editar mesa"
      :table="props.table"
      @save="save"
      @close="closeDialog"
    />
  </v-dialog>
</template>
