import {defaultInstance} from "../util/axios.js";

export const getOrderList = async (formData) => {
    const params = new URLSearchParams();
    for(const [key, value] of formData.entries()){
        if(value)
            params.append(key, value);
    }
    try {
        const response = await defaultInstance.get('orders', {params});
        return response.data;
    } catch (err) {
        console.log(err);
    }
}

export const getOrder = async (orderId,supplierId = null) => {
    try {
        let url = ""
        if(supplierId === null){
            url = 'orders/'+orderId
        } else {
            url = 'orders/'+orderId+'/'+supplierId
        }
        const response = await defaultInstance.get(url);
        return response.data;
    } catch (err) {
        console.log(err);
    }
}


export const updateOrder = async (params) => {
    try {
        const response = await defaultInstance.put('orders', params);
        return response.data;
    } catch (err) {
        console.log(err);
    }
}

export const updateOrderDetail = async (params) => {
    try {
        const response = await defaultInstance.put('orderDetails', params);
        return response.data;
    } catch (err) {
        console.log(err);
    }
}

export const addOrder = async (params) => {
    try {
        const response = await defaultInstance.post('orders', params);
        return response.data;
    } catch (err) {
        console.log(err);
    }
}