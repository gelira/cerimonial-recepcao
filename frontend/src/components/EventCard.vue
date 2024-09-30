<script setup lang="ts">
import { computed } from 'vue';

const props = defineProps<{ event: IEvent }>();

const formattedDate = computed(() => {
  const [year, month, day] = props.event.date.split('-');
  return `${day}/${month}/${year}`;
});
</script>

<template>
  <v-card>
    <v-card-title>{{ props.event.name }}</v-card-title>
    <v-card-subtitle>{{ formattedDate }}</v-card-subtitle>
    <v-card-actions class="justify-end">
      <v-btn icon="mdi-pencil-box-outline"></v-btn>
      <v-btn icon="mdi-clipboard-text-outline"></v-btn>
      <v-btn icon="mdi-content-save-outline"></v-btn>
    </v-card-actions>
  </v-card>
</template>