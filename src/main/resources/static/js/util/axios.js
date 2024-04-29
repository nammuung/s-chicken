const BASE_URL = '/v1/api/'
const axiosApi = (url, options) => {
    return axios.create({
        baseURL: url,
        ...options,
    });
}
export const defaultInstance = axiosApi(BASE_URL);