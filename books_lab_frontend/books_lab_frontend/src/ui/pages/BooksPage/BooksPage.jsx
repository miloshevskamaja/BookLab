import React, {useState} from 'react';
import useBooks from "../../../hooks/useBooks.js";
import {Box, Button, CircularProgress} from "@mui/material";
import BooksGrid from "../../components/books/BooksGrid/BooksGrid.jsx";
import "./BooksPage.css";
import AddBookDialog from "../../components/books/AddBookDialog/AddBookDialog.jsx";

const BooksPage=() => {
    const {books, loading,onAdd,onEdit,onDelete} = useBooks();
    const [addBookDialogOpen, setAddBookDialogOpen] = useState(false);

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
                        <Button variant="contained" color="primary" onClick={() => setAddBookDialogOpen(true)}>
                            Add Book
                        </Button>
                    </Box>
                    <BooksGrid books={books} onEdit={onEdit} onDelete={onDelete}/>
                </>
                }
            </Box>
            <AddBookDialog
                open={addBookDialogOpen}
                onClose={()=> setAddBookDialogOpen(false)}
                onAdd={onAdd}
            />
        </>
    );
};

export default BooksPage;