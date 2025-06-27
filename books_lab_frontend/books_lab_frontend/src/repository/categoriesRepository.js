import axiosInstance from "../axios/axios.js";


const categoriesRepository={
    findAll:async()=>{
        return await axiosInstance.get("/categories");

    },
    findById: async (id) =>{
        return await axiosInstance.get(`/categories/${id}`);
    },
};
export default categoriesRepository;