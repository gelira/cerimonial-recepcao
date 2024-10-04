<script setup lang="ts">
import { computed, reactive, watch } from 'vue';

const props = defineProps<{ event?: IEvent }>();

const state = reactive({ name: '', date: '' });

const invalidForm = computed(() => {
  return !state.name || !state.date;
});

watch(
  () => props.event,
  (e) => {
    if (e) {
      state.name = e.name;
      state.date = e.date;
    }
  },
  { immediate: true }
);
</script>

<template>
  <v-row>
    <v-col
      xs="12"
      sm="12"
      md="6"
      lg="6"
      xl="6"
      xxl="6"
    >
      <v-text-field
        clearable
        label="Nome do evento"
        variant="underlined"
        v-model="state.name"
      ></v-text-field>
    </v-col>
    <v-col
      xs="12"
      sm="12"
      md="3"
      lg="3"
      xl="3"
      xxl="3"
    >
      <v-text-field
        label="Data do evento"
        variant="underlined"
        type="date"
        v-model="state.date"
      ></v-text-field>
    </v-col>
    <v-col
      xs="12"
      sm="12"
      md="3"
      lg="3"
      xl="3"
      xxl="3"
      class="align-self-center"
    >
      <v-btn
        color="success"
        :disabled="invalidForm"
      >Salvar</v-btn>
    </v-col>
  </v-row>
</template>