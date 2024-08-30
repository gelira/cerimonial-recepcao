declare global {
  interface IEvent {
    id: string
    name: string
  }

  interface ITable {
    id: string
    eventId: string
    identifier: string
    locationDescription: string
  }

  interface IGuest {
    id: string
    eventId: string
    tableId: string
    name: string
    arrived: boolean
  }
}

export { }

