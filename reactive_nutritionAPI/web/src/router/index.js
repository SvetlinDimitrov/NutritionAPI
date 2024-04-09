import {createRouter, createWebHistory} from 'vue-router';
import Home from "../views/Home.vue";
import store from '@/store'
import Login from "../views/Login.vue";
import Register from "@/views/Register.vue";
import Logout from "@/views/Logout.vue";
import Profile from "@/views/Profile.vue";
import MacroInfo from "@/views/MacroInfo.vue";
import NutrientView from "@/views/NutrientView.vue";
import Record from "@/views/Record.vue";
import ChangeConsumption from "@/views/ChangeConsumption.vue";
import ErrorPage from "@/views/ErrorPage.vue";

const routes = [
    {
        path: '/',
        name: "Home",
        component: Home
    },
    {
        path: '/login',
        name: "Login",
        component: Login,
    },
    {
        path: '/register',
        name: "Register",
        component: Register,
    },
    {
        path: '/logout',
        name: "Logout",
        component: Logout,
    },
    {
        path: '/profile',
        name: "Profile",
        component: Profile,
    },
    {
        path: '/macroInfo',
        name: "MacroInfo",
        component: MacroInfo,
    },
    {
        path: '/macroInfo/:type/:name',
        name: 'NutrientView',
        component: NutrientView
    },
    {
        path: '/record/:id',
        name: 'Record',
        component: Record,
        children: [
            {
                path: 'changeConsumption/:name',
                name: 'ChangeConsumption',
                component: ChangeConsumption
            }
        ]
    },
    {
        path: '/:catchAll(.*)',
        name: 'ErrorPage',
        component: ErrorPage
    },
];


const router = createRouter({
    history: createWebHistory(),
    routes
});

router.beforeEach((to, from, next) => {

    const isUserSessionExpired = store.getters.isSessionExpired;
    const nonAuthRoutes = ['/login', '/register', '/'];

    if(store.state.userView === null){
        if (nonAuthRoutes.includes(to.path) || to.path.includes('/macroInfo')) {
            next();
        } else {
            next('/login');
        }
    }else if (isUserSessionExpired){
        store.dispatch('logoutUser');
        next('/login');
    } else {
        if (to.path === '/login' || to.path === '/register') {
            next('/');
        } else {
            next();
        }
    }
});

export default router;
