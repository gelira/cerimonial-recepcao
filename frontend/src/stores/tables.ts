import { defineStore } from 'pinia'
import { computed, reactive } from 'vue'

import { apiFetchTables } from '../api'

interface State {
  tables: ITable[]
}

export const useTablesStore = defineStore('tables', () => {
  const state = reactive<State>({ tables: [] })
  
  const tables = computed(() => state.tables)
  
  function fetchTables(eventId: string) {
    apiFetchTables(eventId)
      .then((data) => state.tables = data.tables)
      .catch(() => state.tables = [])
  }

  return { tables, fetchTables }
})
