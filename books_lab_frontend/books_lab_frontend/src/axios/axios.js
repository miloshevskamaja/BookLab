import axios from "axios";

const token = "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJST0xFX0xJQlJBUklBTiJdLCJzdWIiOiJtbSIsImlhdCI6MTc1MDg2NTI5MiwiZXhwIjoxNzUxNzI5MjkyfQ.sOFaV9oHxqMx0tmEWQNBMDugnW-1pNkYNSwkqSLangQ";

const axiosInstance = axios.create({
    baseURL: "http://localhost:9090",
    headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${token}`,
    },
});

export default axiosInstance;
