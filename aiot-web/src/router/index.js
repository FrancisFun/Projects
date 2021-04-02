import Vue from 'vue'
import VueRouter from 'vue-router'
import auth from '../views/auth'
import management from "../views/management";
import home from '../views/home'
import statistics from "../views/statistics";
import equipments from "../views/equipments";
import users from "../views/users";
import tableView from "../components/tables/data-table"
import chartView from "../components/tables/chart-table"

Vue.use(VueRouter)

const routes = [
    {
        path: '/',
        redirect: '/auth'
    }, {
        path: '/auth',
        name: 'auth',
        component: auth
    }, {
        path: '/management',
        redirect: '/management/home'
    }, {
        path: '/management',
        name: 'management',
        component: management,
        children: [
            {
                path: 'home',
                name: 'home',
                component: home
            }, {
                path: 'statistics',
                redirect: '/management/statistics/table-view'
            }, {
                path: 'statistics',
                name: 'statistics',
                component: statistics,
                children: [
                    {
                        path: 'chart-view',
                        name: 'chartView',
                        component: chartView
                    }, {
                        path: 'table-view',
                        name: 'tableView',
                        component: tableView
                    }
                ]
            }, {
                path: 'equipments',
                name: 'equipments',
                component: equipments
            }, {
                path: 'users',
                name: 'users',
                component: users
            }
        ]
    },
]

const router = new VueRouter({
    mode: "history",
    routes
})

export default router
