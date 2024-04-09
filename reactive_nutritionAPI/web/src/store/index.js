import {createStore} from 'vuex';
import {editUser, loginUser} from "@/api/userService.js";
import {creteRecord, deleteRecord, getRecords} from "@/api/recordsService.js";

export default createStore({
    state: {
        userView: localStorage.getItem('userView') ? JSON.parse(localStorage.getItem('userView')) : null,
        accessToken: localStorage.getItem('accessToken') ? JSON.parse(localStorage.getItem('accessToken')) : null,
        extractRecords: false,
        records: localStorage.getItem('records') ? JSON.parse(localStorage.getItem('records')) : null,
    },
    mutations: {
        addRecord(state) {
            state.records = null;
            localStorage.removeItem('records');
            state.extractRecords = true;
        },
        deleteRecord(state) {
            state.records = null;
            localStorage.removeItem('records');
            state.extractRecords = true;
        },
        extractRecords(state, data) {
            state.records = data;
            localStorage.setItem('records', JSON.stringify(data));
            state.extractRecords = false;
        },
        loginUser(state, data) {
            state.userView = data.userView;
            state.accessToken = data.accessToken;
            localStorage.setItem('userView', JSON.stringify(data.userView));
            localStorage.setItem('accessToken', JSON.stringify(data.accessToken));
            state.extractRecords = true;
        },
        logoutUser(state) {
            state.userView = null;
            state.accessToken = null;
            localStorage.removeItem('userView');
            localStorage.removeItem('accessToken');
            localStorage.removeItem('records');
            state.extractRecords = false;
        }
    },
    actions: {
        async addRecord({commit}) {
            try {
                await creteRecord();
                commit('addRecord');
            } catch (error) {
                throw error;
            }
        },
        async deleteRecord({commit}, recordId) {
            try {
                await deleteRecord(recordId);
                commit('deleteRecord');
            } catch (error) {
                throw error;
            }
        },
        async getRecords({commit}) {
            try {
                const data = await getRecords();
                commit('extractRecords', data);
                return data;
            } catch (error) {
                throw error;
            }
        },
        async loginUser({commit}, userData) {
            try {
                console.log("logging")
                const data = await loginUser(userData);
                commit('loginUser', data);
            } catch (error) {
                throw error;
            }
        },
        async editUser({commit}, userData) {
            try {
                const data = await editUser(userData);
                commit('loginUser', data);
            } catch (error) {
                throw error;
            }
        },
        async logoutUser({commit}) {
            console.log("logoutUser")
            commit('logoutUser');
        },
    },
    getters: {
        isSessionExpired: state => {
            if (!state.accessToken) {
                return true;
            }

            const expirationTime = new Date(state.accessToken.expiresIn);
            const currentTime = new Date();

            return expirationTime < currentTime;
        }
    }
});
