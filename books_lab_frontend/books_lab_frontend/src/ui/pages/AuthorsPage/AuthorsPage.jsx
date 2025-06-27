import useAuthors from "../../../hooks/useAuthors.js";
import {Box, Button, CircularProgress} from "@mui/material";
import AuthorGrid from "../../components/authors/AuthorGrid/AuthorGrid.jsx";
import React, {useState} from "react";
import AddAuthorDialog from "../../components/authors/AddAuthorDialog/AddAuthorDialog.jsx";


const AuthorsPage=()=>{
    const {authors, loading,onAdd} = useAuthors();
    const[addAuthorDialogOpen,setAuthorDialogOpen]=useState(false);
    return (
        <>
        <Box className="products-box">
            {loading && (
                <Box className="progress-box">
                    <CircularProgress/>
                </Box>
            )}
            {!loading &&
            <>
                <Box>
                    <Button variant="contained" color="primary" onClick={() => setAuthorDialogOpen(true)}>
                        Add Author
                    </Button>
                </Box>
            <AuthorGrid authors={authors}/>
            </>
            }
        </Box>
            <AddAuthorDialog
            open={addAuthorDialogOpen}
            onClose={()=>setAuthorDialogOpen(false)}
            onAdd={onAdd}
            />
        </>
    );
};

export default AuthorsPage;
