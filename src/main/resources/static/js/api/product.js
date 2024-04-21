import {defaultInstance} from "../util/axios.js";

export const getProductList = async (formData) => {
    const params = new URLSearchParams();
    for(const [key, value] of formData.entries()){
        params.append(key, value);
    }
    try {
        const response = await defaultInstance.get('products', {params});
        return response.data;
    } catch (err) {
        console.log(err);
    }
}

export const getProduct = async (id) => {
    try {
        const response = await defaultInstance.get('products/'+id);
        return response.data;
    } catch (err) {
        console.log(err);
    }
}

export const updateProduct = async (params) => {
    try {
        const response = await defaultInstance.put('products', params);
        return response.data;
    } catch (err) {
        console.log(err);
    }
}

export const addProduct = async (params) => {
    try {
        const response = await defaultInstance.post('products', params);
        return response.data;
    } catch (err) {
        console.log(err);
    }
}

