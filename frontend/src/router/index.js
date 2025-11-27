import { createRouter, createWebHistory } from 'vue-router'
import Home from '../views/Home.vue'
import Students from '../views/Students.vue'
import Teachers from '../views/Teachers.vue'

const routes = [
  { path: '/', component: Home },
  { path: '/students', component: Students },
  { path: '/teachers', component: Teachers }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router
