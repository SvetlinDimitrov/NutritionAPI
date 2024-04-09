import store from "@/store/index.js";
import axios from "../../axsiosClient.js";

export async function changeNutritionConsumption(recordId , body) {
    try {
        const authToken = store.state.accessToken;
        const config = {
            headers: {
                'Authorization': `Bearer ${authToken.value}`
            }
        };
        const response = await axios.patch(`${process.env.BASE_URL}/nutritionApi/records/edit/${recordId}`, body ,config);
        return response.data;
    } catch (error) {
        if (error.response.data != null) {
            throw new Error(error.response.data.message);
        } else {
            throw new Error("Invalid credentials");
        }
    }
}
export async function getRecordById(recordId) {
    try {
        const authToken = store.state.accessToken;
        const config = {
            headers: {
                'Authorization': `Bearer ${authToken.value}`
            }
        };
        const response = await axios.get(`${process.env.BASE_URL}/nutritionApi/records/${recordId}`, config);
        return response.data;
    } catch (error) {
        if (error.response.data != null) {
            throw new Error(error.response.data.message);
        } else {
            throw new Error("Invalid credentials");
        }
    }
}
export async function getRecords() {
    try {
        const authToken = store.state.accessToken;
        const config = {
            headers: {
                'Authorization': `Bearer ${authToken.value}`
            }
        };
        const response = await axios.get(`${process.env.BASE_URL}/nutritionApi/records`, config);
        return response.data;
    } catch (error) {
        if (error.response.data != null) {
            throw new Error(error.response.data.message);
        } else {
            throw new Error("Invalid credentials");
        }
    }
}
export async function deleteRecord(recordId) {
    try {
        const authToken = store.state.accessToken;
        const config = {
            headers: {
                'Authorization': `Bearer ${authToken.value}`
            }
        };
        const response = await axios.delete(`${process.env.BASE_URL}/nutritionApi/records/${recordId}`, config);
        return response.data;
    } catch (error) {
        if (error.response.data != null) {
            throw new Error(error.response.data.message);
        } else {
            throw new Error("Invalid credentials");
        }
    }
}

export async function creteRecord() {
    try {
        const authToken = store.state.accessToken;
        const config = {
            headers: {
                'Authorization': `Bearer ${authToken.value}`
            }
        };
        const response = await axios.post(`${process.env.BASE_URL}/nutritionApi/user/records`,{} ,config);
        return response.data;
    } catch (error) {
        if (error.response.data != null) {
            throw new Error(error.response.data.message);
        } else {
            throw new Error("Invalid credentials");
        }
    }
}