import axios from "axios";

const baseURL = import.meta.env.MODE === 'development'
    ? 'http://localhost:8081'
    : '/api';
const token = "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJST0xFX0xJQlJBUklBTiJdLCJzdWIiOiJtbSIsImlhdCI6MTc1MDg2NTI5MiwiZXhwIjoxNzUxNzI5MjkyfQ.sOFaV9oHxqMx0tmEWQNBMDugnW-1pNkYNSwkqSLangQ";

const axiosInstance = axios.create({
    // baseURL: "http://localhost:8081",
    // baseURL: baseURL,
    baseURL: '/api',
    headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${token}`,
    },
});

export default axiosInstance;
