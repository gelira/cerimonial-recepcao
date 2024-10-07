<script setup lang="ts">
import { computed } from 'vue';

import { useGuestsStore } from '../stores/guests';
import DeleteTableDialog from './DeleteTableDialog.vue';

const props = defineProps<{ table: ITable }>();

const guestsStore = useGuestsStore();

const guests = computed(() => {
  return guestsStore.guests.filter((g) => g.tableId === props.table.id);
})
</script>

<template>
  <v-expansion-panel>
    <v-expansion-panel-title>
      <template #default>{{ props.table.identifier }}</template>
      <template #actions="{ expanded }">
        <div class="actions-container">
          <v-icon :icon="expanded ? 'mdi-chevron-up' : 'mdi-chevron-down'"></v-icon>
          <DeleteTableDialog :table-id="props.table.id" />
        </div>
      </template>
    </v-expansion-panel-title>
    <v-expansion-panel-text>{{ JSON.stringify(guests) }}</v-expansion-panel-text>
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