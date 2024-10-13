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

export async function apiUpdateEvent(event: IEvent) {
  const { id, ...rest } = event

  await fetch(`${BASE_URL}/events/${id}`, {
    method: 'PUT',
    headers: {
      'content-type': 'application/json',
    },
    body: JSON.stringify(rest),
  })
}

export async function apiCreateTable(table: ITable) {
  const { id: _, ...rest } = table

  await fetch(`${BASE_URL}/tables`, {
    method: 'POST',
    headers: {
      'content-type': 'application/json',
    },
    body: JSON.stringify(rest),
  })
}

export async function apiDeleteTable(tableId: string) {
  await fetch(`${BASE_URL}/tables/${tableId}`, {
    method: 'DELETE',
  })
}

export async function apiDeleteEvent(eventId: string) {
  await fetch(`${BASE_URL}/events/${eventId}`, {
    method: 'DELETE',
  })
}

export async function apiCreateGuest(body: { tableId: string, name: string }) {
  await fetch(`${BASE_URL}/guests`, {
    method: 'POST',
    headers: {
      'content-type': 'application/json',
    },
    body: JSON.stringify(body),
  })
}

export async function apiDeleteGuest(guestId: string) {
  await fetch(`${BASE_URL}/guests/${guestId}`, {
    method: 'DELETE',
  })
}

export async function apiUpdateTable(body: { id: string, identifier: string, locationDescription: string }) {
  const { id, ...rest } = body

  await fetch(`${BASE_URL}/tables/${id}`, {
    method: 'PUT',
    headers: {
      'content-type': 'application/json',
    },
    body: JSON.stringify(rest),
  })
}
