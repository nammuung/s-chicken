import {defaultInstance} from "../util/axios.js";

export const getWeather = async () => {
    try {
        const response = await defaultInstance.get('weathers/now');
        return response.data;
    } catch (err) {
        console.log(err);
    }
}

export const getWeatherList = async () => {
    try {
        const response = await defaultInstance.get('weathers');
        return response.data;
    } catch (err) {
        console.log(err);
    }
}
