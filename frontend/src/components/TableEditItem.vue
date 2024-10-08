<script setup lang="ts">
import { computed } from 'vue'

import { useGuestsStore } from '../stores/guests'

import DeleteTableDialog from './DeleteTableDialog.vue'
import GuestsEditList from './GuestsEditList.vue'
import EditTableDialog from './EditTableDialog.vue';

const props = defineProps<{ table: ITable }>()

const guestsStore = useGuestsStore()

const guests = computed(
  () => guestsStore.guests.filter((g) => g.tableId === props.table.id)
)
</script>

<template>
  <v-expansion-panel>
    <v-expansion-panel-title>
      <template #default>{{ table.identifier }}</template>
      <template #actions="{ expanded }">
        <div class="actions-container">
          <span>({{ guests.length }})</span>
          <v-icon :icon="expanded ? 'mdi-chevron-up' : 'mdi-chevron-down'"></v-icon>
          <EditTableDialog :table="table" />
          <DeleteTableDialog :table-id="table.id" />
        </div>
      </template>
    </v-expansion-panel-title>
    <v-expansion-panel-text>
      <GuestsEditList
        :event-id="table.eventId"
        :table-id="table.id"
        :guests="guests"
      />
    </v-expansion-panel-text>
  </v-expansion-panel>
</template>

<style scoped>
.actions-container {
  display: flex;
  flex-direction: row;
  align-items: center;
  gap: 8px;
}
</style>