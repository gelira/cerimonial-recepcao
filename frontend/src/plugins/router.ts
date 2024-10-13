import { createRouter, createWebHistory } from 'vue-router'

import { ROUTE_NAMES } from '../utils/consts'
import HomeView from '../views/HomeView.vue'
import ListEventsView from '../views/ListEventsView.vue'
import EditEventView from '../views/EditEventView.vue'
import EventReceptionView from '../views/EventReceptionView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: ROUTE_NAMES.HOME,
      component: HomeView,
      children: [
        {
          path: 'events',
          name: ROUTE_NAMES.LIST_EVENTS,
          component: ListEventsView
        },
        {
          path: 'event/:id',
          name: ROUTE_NAMES.EDIT_EVENT,
          component: EditEventView
        },
        {
          path: 'event-reception/:id',
          name: ROUTE_NAMES.EVENT_RECEPTION,
          component: EventReceptionView
        },
      ],
    },
  ],
})

export default router
