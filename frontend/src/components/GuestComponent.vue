<script setup lang="ts">
import { computed } from 'vue';

import { useGuestsStore } from '@/stores/guests';
import { removeAccents } from '@/utils';

const props = defineProps<{ guest: IGuest }>();
const guestsStore = useGuestsStore();

const search = computed(() => guestsStore.search);

const nameSplitted = computed<{ namePart: string, mark: boolean }[]>(() => {
  const guestName = props.guest.name;

  const cleanedName = removeAccents(guestName.toLowerCase());
  const cleanedSearch = removeAccents(search.value.toLowerCase());

  if (!cleanedSearch || !cleanedName.includes(cleanedSearch)) {
    return [ { namePart: guestName, mark: false } ];
  }

  const startIndex = cleanedName.indexOf(cleanedSearch);
  const endIndex = startIndex + cleanedSearch.length;

  return [
    { namePart: guestName.substring(0, startIndex), mark: false },
    { namePart: guestName.substring(startIndex, endIndex), mark: true },
    { namePart: guestName.substring(endIndex), mark: false },
  ];
});
</script>

<template>
  <span v-for="part in nameSplitted" :key="part.namePart" :class="{ 'mark': part.mark }">
    {{ part.namePart }}
  </span>
</template>

<style scoped>
.mark {
  font-weight: bold;
  text-decoration: underline;
}
</style>
