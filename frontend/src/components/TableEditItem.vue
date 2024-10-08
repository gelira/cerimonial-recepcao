<script setup lang="ts">
import DeleteTableDialog from './DeleteTableDialog.vue';
import GuestsEditList from './GuestsEditList.vue';

defineProps<{ table: ITable }>();
</script>

<template>
  <v-expansion-panel>
    <v-expansion-panel-title>
      <template #default>{{ table.identifier }}</template>
      <template #actions="{ expanded }">
        <div class="actions-container">
          <v-icon :icon="expanded ? 'mdi-chevron-up' : 'mdi-chevron-down'"></v-icon>
          <DeleteTableDialog :table-id="table.id" />
        </div>
      </template>
    </v-expansion-panel-title>
    <v-expansion-panel-text>
      <GuestsEditList :event-id="table.eventId" :table-id="table.id" />
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