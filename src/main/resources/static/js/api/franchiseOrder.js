import {defaultInstance} from "../util/axios.js";

export const getFranchiseOrderList = async (formData) => {
    const params = new URLSearchParams();
    for(const [key, value] of formData.entries()){
        if(value)
            params.append(key, value);
    }
    try {
        const response = await defaultInstance.get('franchise/orders', {params});
        return response.data;
    } catch (err) {
        console.log(err);
    }
}

export const getFranchiseOrder = async (franchiseOrderId) => {
    try {
        const response = await defaultInstance.get("franchise/orders/"+franchiseOrderId);
        return response.data;
    } catch (err) {
        console.log(err);
    }
}


export const updateFranchiseOrder = async (params) => {
    try {
        const response = await defaultInstance.put('franchise/orders', params);
        return response.data;
    } catch (err) {
        console.log(err);
    }
}

export const updateFranchiseOrderDetail = async (params) => {
    try {
        const response = await defaultInstance.put('franchise/orderDetails', params);
        return response.data;
    } catch (err) {
        console.log(err);
    }
}

export const addFranchiseOrder = async (params) => {
    try {
        const config = {headers:{"Content-Type": "application/json"}}
        const response = await defaultInstance.post('franchise/orders', params, config);
        return response.data;
    } catch (err) {
        console.log(err);
    }
}