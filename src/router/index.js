import Vue from 'vue'
import VueRouter from 'vue-router'
import SimulatorView from '../views/SimulatorView.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'simulator',
    component: SimulatorView
  },
  {
    path: '/simulador',
    name: 'simulator',
    component: SimulatorView
  },
  {
    path: '/historial',
    name: 'history',
    component: function () {
      return import(/* webpackChunkName: "about" */ '../views/HistoryView.vue')
    }
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
