import { createRouter, createWebHistory } from 'vue-router'
import Home from '../views/Home.vue'
import SignUp from '@/views/SignUp.vue'
import LoginPage from '@/views/LoginPage.vue'
import Details from '@/views/Details.vue'
import CreateBlog from '@/views/CreateBlog.vue'

import BlogList from '@/views/BlogList.vue'
import AdminPanel from '@/views/AdminPanel.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home
    },
    {
      path: '/signup',
      name: 'signUp',
      component: SignUp
    },
    {
      path: '/login',
      name: 'login',
      component: LoginPage
    },
    {
      path: '/adminpanel',
      name: 'adminPanel',
      component: AdminPanel
    },
    {
      path: '/createblog',
      name: 'createBlog',
      component: CreateBlog
    },
    {
      path: '/editblog/:id',
      name: 'editBlog',
      component: CreateBlog,
      props: true
    },
    {
      path: '/:type',
      name: 'blogList',
      component: BlogList
    },
    
    {
      path: '/:cat/:id/:slug',
      name: 'details',
      component: Details,
      props: true
    }
  ]
})

export default router
