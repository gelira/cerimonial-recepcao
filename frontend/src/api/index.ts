const BASE_URL = 'http://localhost:8080'

export async function apiFetchEvents() {
  const response = await fetch(`${BASE_URL}/events`)
  const data = await response.json()

  return data as { events: IEvent[] }
}

export async function apiFetchTables(eventId: string) {
  const params = new URLSearchParams({ eventId })

  const response = await fetch(`${BASE_URL}/tables?${params}`)
  const data = await response.json()

  return data as { tables: ITable[] }
}

export async function apiFetchGuests(eventId: string) {
  const params = new URLSearchParams({ eventId })

  const response = await fetch(`${BASE_URL}/guests?${params}`)
  const data = await response.json()

  return data as { guests: IGuest[] }
}
