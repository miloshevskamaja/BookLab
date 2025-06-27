import axiosInstance from "../axios/axios.js";

const countryRepository={
    findAll:async () =>{
        return await axiosInstance.get("/countries");
    },
    findById:async ()=>{
        return await axiosInstance.get(`/countries/${id}`);
    },
};
export default countryRepository;