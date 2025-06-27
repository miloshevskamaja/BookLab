import {useEffect, useState} from "react";
import categoriesRepository from "../repository/categoriesRepository.js";


const useCategories= () =>{
    const[categories, setCategories]=useState([]);

    useEffect(() => {
        categoriesRepository
            .findAll()
            .then((response) =>{
                console.log("Categories response:", response.data);
                setCategories(response.data);
            })
            .catch((error)=>console.log(error));
    }, []);
    return categories;
};
export default useCategories;