import {defaultInstance} from "../util/axios.js";

export const getSalesList = async () => {
    try {
        const response = await defaultInstance.get('sales');
        return response.data;
    } catch (err) {
        console.log(err);
    }
}

export const getSales = async (id) => {
    try {
        const response = await defaultInstance.get('sales/'+id);
        return response.data;
    } catch (err) {
        console.log(err);
    }
}
export const getSalesPerMonth = async () => {
    try {
        const response = await defaultInstance.get('sales/month');
        return response.data;
    } catch (err) {
        console.log(err);
    }
}

export const getSalesPerWeeks = async () => {
    try {
        const response = await defaultInstance.get('sales/weeks');
        return response.data;
    } catch (err) {
        console.log(err);
    }
}

export const getSalesPerDays = async () => {
    try {
        const response = await defaultInstance.get('sales/days');
        return response.data;
    } catch (err) {
        console.log(err);
    }
}

