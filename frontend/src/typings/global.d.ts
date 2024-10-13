declare global {
  interface IEvent {
    id: string
    name: string
    date: string
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

  interface IGuestCleaned extends IGuest {
    cleanedName: string
  }

  interface IEventLocalStorage {
    event: IEvent
    tables: ITable[]
    guests: IGuest[]
  }
}

export { }

