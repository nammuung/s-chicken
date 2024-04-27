import {defaultInstance} from "../util/axios.js";

export const getStockList = async (formData) => {
    const params = new URLSearchParams();
    for(const [key, value] of formData.entries()){
        if(value)
            params.append(key, value);
    }
    try {
        const response = await defaultInstance.get('stocks', {params});
        return response.data;
    } catch (err) {
        console.log(err);
    }
}

export const getStock = async (id) => {
    try {
        const response = await defaultInstance.get('stocks/'+id);
        return response.data;
    } catch (err) {
        console.log(err);
    }
}

export const updateStock = async (params) => {
    try {
        const response = await defaultInstance.put('stocks', params);
        return response.data;
    } catch (err) {
        console.log(err);
    }
}

export const addStock = async (params) => {
    try {
        const response = await defaultInstance.post('stocks', params);
        return response.data;
    } catch (err) {
        console.log(err);
    }
}

