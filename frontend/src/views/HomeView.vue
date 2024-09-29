<script setup lang="ts">
import { onMounted } from 'vue';
import { useRouter } from 'vue-router';

import { useEventsStore } from '../stores/events';
import { ROUTE_NAMES } from '../utils/consts';

const eventsStore = useEventsStore();
const router = useRouter();

onMounted(() => {
  eventsStore.fetchEvents();
  router.push({ name: ROUTE_NAMES.LIST_EVENTS });
});
</script>

<template>
  <v-app-bar title="Cerimonial" elevation="0">
    <template v-slot:prepend>
      <v-app-bar-nav-icon></v-app-bar-nav-icon>
    </template>
  </v-app-bar>
  <v-main>
    <v-container>
      <router-view></router-view>
    </v-container>
  </v-main>
</template>
