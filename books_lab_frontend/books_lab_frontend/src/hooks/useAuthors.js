import {useCallback, useEffect, useState} from "react";
import authorsRepository from "../repository/authorRepository.js";


const initialState={
    "authors": [],
    "loading": true,
}

const useAuthors=() =>{
    const[state, setState] = useState(initialState);

    const fetchAuthors = useCallback(()=>{
        authorsRepository
            .findAll()
            .then((response) => {
                setState({
                    "authors": response.data,
                    "loading": false,
                });
            })
            .catch((error) => console.log(error));
    }, []);

    const onAdd=useCallback((data)=>{
        authorsRepository
            .add(data)
            .then(()=>{
                console.log("Succesfully");
                fetchAuthors();
            })
            .catch((error)=>console.log(error));
    }, [fetchAuthors]);

    useEffect(() => {
        fetchAuthors();
    }, [fetchAuthors]);

    return {
        authors: state.authors,
        loading: state.loading,
        onAdd:onAdd,
    };
};

export default useAuthors;