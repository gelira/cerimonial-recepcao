<script setup lang="ts">
import { reactive } from 'vue'

import { useTablesStore } from '../stores/tables'

const props = defineProps<{ table: ITable }>()

const tablesStore = useTablesStore()

const state = reactive({
  openDialog: false,
})

function closeDialog() {
  state.openDialog = false
}

function deleteTable() {
  closeDialog()
  tablesStore.deleteTable(props.table.id)
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
        size="small"
        variant="tonal"
      ></v-btn>
    </template>

    <v-row justify="center">
      <v-col class="v-col-auto">
        <v-card>
          <v-card-title>Deletar mesa</v-card-title>
          <v-card-text>
            Deseja mesmo deletar a mesa {{ table.identifier }}?
          </v-card-text>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
              @click="deleteTable()"
              color="error"
            >Deletar</v-btn>
            <v-btn
              @click="closeDialog()"
              color="primary"
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
