import {defaultInstance} from "../util/axios.js";

export const getReleaseHistoryList = async (id) => {
    try {
        const response = await defaultInstance.get('history/release/'+id);
        return response.data;
    } catch (err) {
        console.log(err);
    }
}

export const getReceiveHistoryList = async (id) => {
    try {
        const response = await defaultInstance.get('history/receive/'+id);
        return response.data;
    } catch (err) {
        console.log(err);
    }
}


export const addReleaseHistory = async (params) => {
    try {
        const response = await defaultInstance.post('history/release', params);
        return response.data;
    } catch (err) {
        console.log(err);
    }
}
export const addReceiveHistory = async (params) => {
    try {
        const response = await defaultInstance.post('history/receive', params);
        return response.data;
    } catch (err) {
        console.log(err);
    }
}

