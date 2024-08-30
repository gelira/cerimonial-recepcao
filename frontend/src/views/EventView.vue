<script setup lang="ts">
import { computed, ref, watch } from 'vue';
import { useRoute } from 'vue-router';

import TableComponent from '@/components/TableComponent.vue';
import { useEventsStore } from '@/stores/events';
import { useGuestsStore } from '@/stores/guests';
import { useTablesStore } from '@/stores/tables';

const route = useRoute();

const eventsStore = useEventsStore();
const tablesStore = useTablesStore();
const guestsStore = useGuestsStore();

const search = ref('');

const eventId = computed(() => route.params.id as string);

const event = computed(() => eventsStore.events.find((e) => e.id === eventId.value));
const tables = computed(() => tablesStore.tables.filter((t) => t.eventId === eventId.value));

watch(eventId, (id) => {
  tablesStore.fetchTables(id);
  guestsStore.fetchGuests(id);
}, { immediate: true});

watch(search, (value) => {
  guestsStore.setSearch(value);
});
</script>

<template>
  <h2>Evento {{ event?.name }}</h2>
  <div>
    <input type="text" v-model="search">
    <button @click="search = ''">Limpar</button>
  </div>
  <TableComponent
    v-for="table in tables"
    :key="table.id"
    :table="table"
  />
</template>