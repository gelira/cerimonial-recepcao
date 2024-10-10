<script setup lang="ts">
import { computed, reactive, watch } from 'vue'

const props = defineProps<{ title: string, table?: ITable }>()
const emit = defineEmits<{
  (e: 'save', value: { identifier: string, locationDescription: string }): void
  (e: 'close'): void
}>()

const state = reactive({
  identifier: '',
  locationDescription: '',
})

const invalidForm = computed(() => {
  const identifier = (state.identifier ?? '').trim()
  const locationDescription = (state.locationDescription ?? '').trim()

  if (!identifier.length) {
    return true
  }

  if (!props.table) {
    return false
  }

  return props.table.identifier === identifier &&
    props.table.locationDescription === locationDescription
})

function close() {
  emit('close')
  state.identifier = ''
  state.locationDescription = ''
}

function save() {
  if (invalidForm.value) {
    return
  }

  emit('save', {
    identifier: state.identifier.trim(),
    locationDescription: (state.locationDescription ?? '').trim(),
  })

  close()
}

watch(
  () => props.table,
  (v) => {
    if (v) {
      state.identifier = v.identifier
      state.locationDescription = v.locationDescription
    }
  },
  { immediate: true }
)
</script>

<template>
  <v-row justify="center">
    <v-col class="v-col-auto">
      <v-card :title="props.title">
        <v-card-text>
          <v-text-field
            clearable
            label="Identificador da mesa"
            variant="underlined"
            v-model="state.identifier"
          ></v-text-field>
          <v-text-field
            clearable
            label="Localização"
            variant="underlined"
            v-model="state.locationDescription"
          ></v-text-field>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn
            @click="save()"
            :disabled="invalidForm"
            color="success"
          >Salvar</v-btn>
          <v-btn
            @click="close()"
            color="error"
          >Fechar</v-btn>
        </v-card-actions>
      </v-card>
    </v-col>
  </v-row>
</template>

<style scoped>
@media screen and (min-width: 64em) {
  .v-card {
    min-width: 600px;
  }
}
</style>
