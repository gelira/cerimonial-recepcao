import { defineStore } from 'pinia'
import { computed, reactive } from 'vue'

import { apiCreateTable, apiDeleteTable, apiFetchTables } from '../api'

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

  function cleanTables() {
    state.tables = []
  }

  async function createTable(table: ITable) {
    try {
      await apiCreateTable(table)
      
      fetchTables(table.eventId)
    } catch {}
  }

  async function deleteTable(tableId: string) {
    try {
      await apiDeleteTable(tableId)
      
      state.tables = state.tables.filter((t) => t.id !== tableId)
    } catch {}
  }

  return { tables, fetchTables, cleanTables, createTable, deleteTable }
})
