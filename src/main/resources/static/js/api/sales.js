import {defaultInstance} from "../util/axios.js";

export const getSalesList = async (page, id) => {
    try {
        if(id){
            const response = await defaultInstance.get(`sales/${id}&page=`+page);
            return response.data;
        } else {
            const response = await defaultInstance.get('sales?page='+page);
            return response.data;
        }
    } catch (err) {
        console.log(err);
    }
}

export const getSalesDays = async (id) => {
    try {
        if(id){
            const response = await defaultInstance.get('sales/days/one/'+id);
            return response.data;
        } else{
            const response = await defaultInstance.get('sales/days/one');
            return response.data;
        }
    } catch (err) {
        console.log(err);
    }
}
export const getSellDays = async (id) => {
    try {
        if(id){
            const response = await defaultInstance.get('sell/days/'+id);
            return response.data;
        } else {
            const response = await defaultInstance.get('sell/days');
            return response.data;
        }
    } catch (err) {
        console.log(err);
    }
}
export const getSalesPerMonth = async (id) => {
    try {
        if(id) {
            const response = await defaultInstance.get('sales/month/'+id);
            return response.data;
        } else {
            const response = await defaultInstance.get('sales/month');
            return response.data;
        }
    } catch (err) {
        console.log(err);
    }
}

export const getSalesPerWeeks = async (id) => {
    try {
        if(id){
            const response = await defaultInstance.get('sales/weeks/'+id);
            return response.data;
        }else {
            const response = await defaultInstance.get('sales/weeks');
            return response.data;
        }
    } catch (err) {
        console.log(err);
    }
}

export const getSalesPerDays = async (id) => {
    try {
        if(id){
            const response = await defaultInstance.get('sales/days/'+id);
            return response.data;
        } else {
            const response = await defaultInstance.get('sales/days');
            return response.data;
        }
    } catch (err) {
        console.log(err);
    }
}

