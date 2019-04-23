import Vuex from 'vuex'
import Vue from 'vue'

Vue.use(Vuex);

const store = new Vuex.Store({
    state: {
        houseInfo: null,
        houseEdit: null
    },
    mutations: {
        setHouse (state, houseInfo) {
            state.houseInfo = houseInfo
        },
        setEdit (state, houseEdit) {
            state.houseEdit = houseEdit
        },
    },
    getters: {
        getHouse: state => {
            return state.houseInfo
        },
        getEdit: state => {
            return state.houseEdit
        }
    }
});

export default store;