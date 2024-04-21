import {defaultInstance} from "../util/axios.js";

export const getItemList = async (formData) => {
    const params = new URLSearchParams();
    for(const [key, value] of formData.entries()){
        params.append(key, value);
    }
    try {
        const response = await defaultInstance.get('items', {params});
        return response.data;
    } catch (err) {
        console.log(err);
    }
}

export const getItem = async (id) => {
    try {
        const response = await defaultInstance.get('items/'+id);
        return response.data;
    } catch (err) {
        console.log(err);
    }
}

export const updateItem = async (params) => {
    try {
        const response = await defaultInstance.put('items', params);
        return response.data;
    } catch (err) {
        console.log(err);
    }
}

export const addItem = async (params) => {
    try {
        const response = await defaultInstance.post('items', params);
        return response.data;
    } catch (err) {
        console.log(err);
    }
}

