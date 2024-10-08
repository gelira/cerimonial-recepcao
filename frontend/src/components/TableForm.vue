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

const invalidForm = computed(() => !state.identifier?.length)

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
    identifier: state.identifier,
    locationDescription: state.locationDescription ?? '',
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