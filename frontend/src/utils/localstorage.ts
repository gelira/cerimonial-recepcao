const EVENTS_SAVED_KEY = 'events-saved'

export function saveEventDataToLocalStorage(eventId: string, data: IEventLocalStorage) {
  localStorage.setItem(`event-${eventId}`, JSON.stringify(data))
}

export function removeEventDataFromLocalStorage(eventId: string) {
  localStorage.removeItem(`event-${eventId}`)
}

export function loadEventDataFromLocalStorage(eventId: string) {
  const data = localStorage.getItem(`event-${eventId}`)

  if (!data) {
    return null
  }

  return JSON.parse(data) as IEventLocalStorage
}

export function getListEventSaved() {
  return JSON.parse(localStorage.getItem(EVENTS_SAVED_KEY) ?? '[]') as string[]
}

export function addEventSaved(eventId: string) {
  localStorage.setItem(EVENTS_SAVED_KEY, JSON.stringify([
    ...getListEventSaved(),
    eventId,
  ]))
}

export function removeEventSaved(eventId: string) {
  const eventsSaved = getListEventSaved()

  localStorage.setItem(EVENTS_SAVED_KEY, JSON.stringify(eventsSaved.filter(
    (e) => e !== eventId
  )))
}