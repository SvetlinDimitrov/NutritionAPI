import axios from "../../axsiosClient.js";

export async function getNutrientInfo(nutrientType, nutrientName) {
    try {
        const response = await axios.get(`${process.env.BASE_URL}/nutritionApi/${nutrientType}/${nutrientName}`);
        return response.data;
    } catch (error) {
        if (error.response.data != null) {
            throw new Error(error.response.data.message);
        } else {
            throw new Error("Invalid credentials");
        }
    }
}