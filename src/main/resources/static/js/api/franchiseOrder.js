import {defaultInstance} from "../util/axios.js";

export const getFranchiseOrderList = async (formData) => {
    const params = new URLSearchParams();
    for(const [key, value] of formData.entries()){
        if(value)
            params.append(key, value);
    }
    try {
        const response = await defaultInstance.get('franchiseOrders', {params});
        return response.data;
    } catch (err) {
        console.log(err);
    }
}

export const getFranchiseOrder = async (franchiseOrderId,supplierId = null) => {
    try {
        let url = ""
        if(supplierId === null){
            url = 'franchiseOrders/'+franchiseOrderId
        } else {
            url = 'franchiseOrders/'+franchiseOrderId+'/'+supplierId
        }
        const response = await defaultInstance.get(url);
        return response.data;
    } catch (err) {
        console.log(err);
    }
}


export const updateFranchiseOrder = async (params) => {
    try {
        const response = await defaultInstance.put('franchiseOrders', params);
        return response.data;
    } catch (err) {
        console.log(err);
    }
}

export const updateFranchiseOrderDetail = async (params) => {
    try {
        const response = await defaultInstance.put('franchiseOrderDetails', params);
        return response.data;
    } catch (err) {
        console.log(err);
    }
}

export const addFranchiseOrder = async (params) => {
    try {
        const response = await defaultInstance.post('franchiseOrders', params);
        return response.data;
    } catch (err) {
        console.log(err);
    }
}