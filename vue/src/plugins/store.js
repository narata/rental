import Vuex from 'vuex'
import Vue from 'vue'

Vue.use(Vuex);

const store = new Vuex.Store({
    state: {
        daily: null
    },
    mutations: {
        setDaily (state, dailyInfo) {
            state.daily = dailyInfo
        }
    },
    getters: {
        getDaily: state => {
            return state.daily
        }
    }
});

export default store;