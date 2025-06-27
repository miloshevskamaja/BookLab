import axiosInstance from "../axios/axios.js";

const authorRepository={
    findAll: async () => {
        return await axiosInstance.get("/authors");
    },
    findById: async (id)=>{
        return await axiosInstance.get(`/authors/${id}`);
    },
    add: async (data)=>{
        return await axiosInstance.post("/authors/add",data);
    },
};
export default authorRepository;