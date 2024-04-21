import {defaultInstance} from "../util/axios.js";

export const getSupplierList = async (formData) => {
    const params = new URLSearchParams();
    for(const [key, value] of formData.entries()){
        params.append(key, value);
    }
    try {
        const response = await defaultInstance.get('supplier', {params});
        return response.data;
    } catch (err) {
        console.log(err);
    }
}

export const getSupplier = async (id) => {
    try {
        const response = await defaultInstance.get('supplier/'+id);
        return response.data;
    } catch (err) {
        console.log(err);
    }
}

export const updateSupplier = async (params) => {
    try {
        const response = await defaultInstance.put('supplier', params);
        return response.data;
    } catch (err) {
        console.log(err);
    }
}

export const addSupplier = async (params) => {
    try {
        const response = await defaultInstance.post('supplier', params);
        return response.data;
    } catch (err) {
        console.log(err);
    }
}

