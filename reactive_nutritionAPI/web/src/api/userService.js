import axios from '../../axsiosClient.js';
import  store  from '../store/index.js';

export async function loginUser(userData) {
    try {
        const response = await axios.post(`${process.env.BASE_URL}/nutritionApi/user/login`, userData);
        return response.data;
    } catch (error) {
        if (error.response.data != null) {
            throw new Error(error.response.data.message);
        } else {
            throw new Error("Invalid credentials");
        }
    }
}
export async function registerUser(userData) {
    try {
        const response = await axios.post(`${process.env.BASE_URL}/nutritionApi/user/register`, userData);
        return response.data;
    } catch (error) {
        if (error.response.data != null) {
            throw new Error(error.response.data.message);
        } else {
            throw new Error("Invalid credentials");
        }
    }
}
export async function editUser(userData) {
    try {
        const authToken = store.state.accessToken;
        const config = {
            headers: {
                'Authorization': `Bearer ${authToken.value}`
            }
        };
        const response = await axios.patch(`${process.env.BASE_URL}/nutritionApi/user/details`, userData , config);
        return response.data;
    } catch (error) {
        if (error.response.data != null) {
            throw new Error(error.response.data.message);
        } else {
            throw new Error("Invalid credentials");
        }
    }
}
