import { defineStore } from 'pinia'
import { computed, reactive } from 'vue'

import { apiCreateTable, apiDeleteTable, apiFetchTables, apiUpdateTable } from '../api'

interface State {
  tables: ITable[]
}

export const useTablesStore = defineStore('tables', () => {
  const state = reactive<State>({ tables: [] })
  
  const tables = computed(() => state.tables)
  
  function setTables(tablesList: ITable[]) {
    state.tables = tablesList
  }

  function fetchTables(eventId: string) {
    apiFetchTables(eventId)
      .then((data) => setTables(data.tables))
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

  async function updateTable(tableId: string, body: { identifier: string, locationDescription: string }) {
    try {
      await apiUpdateTable({ id: tableId, ...body })

      state.tables = state.tables.map((t) => {
        if (t.id !== tableId) {
          return t
        }

        return { ...t, ...body }
      })
    } catch {}
  }

  return {
    tables,
    setTables,
    fetchTables,
    cleanTables,
    createTable,
    deleteTable,
    updateTable,
  }
})
