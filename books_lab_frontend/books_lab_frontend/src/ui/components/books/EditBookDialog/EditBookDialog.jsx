import React, {useState} from 'react';
import {
    Button,
    Dialog, DialogActions,
    DialogContent,
    DialogTitle,
    FormControl,
    InputLabel,
    MenuItem,
    Select,
    TextField
} from "@mui/material";
import useCategories from "../../../../hooks/useCategories.js";
import useAuthors from "../../../../hooks/useAuthors.js";

const EditBookDialog = ({open, onClose, book, onEdit}) => {
    const [formData, setFormData] = useState({
        "name": book.name,
        "category":book.category,
        "author": book.author,

    });
    const categories = useCategories();
    const {authors, loading} = useAuthors();

    const handleChange = (event)=>{
        const {name,value} = event.target;
        setFormData({...formData, [name]:value});
    };

    const handleSubmit = () =>{
        onEdit(book.id, formData);
        setFormData(formData);
        onClose();
    }
    return (
        <Dialog open={open} onClose={onClose}>
            <DialogTitle>Edit</DialogTitle>
            <DialogContent>
                <TextField
                    margin="dense"
                    label="Name"
                    name="name"
                    value={formData.name}
                    onChange={handleChange}
                    fullWidth
                />
                <FormControl fullWidth margin="dense">
                    <InputLabel>Category</InputLabel>
                    <Select name="category"
                            value={formData.category}
                            onChange={handleChange}
                            label="Category"
                            variant="outlined">
                        {categories.map((category, index)=>(
                            <MenuItem key={index} value={category}>{category}</MenuItem>
                        ))}
                    </Select>
                </FormControl>
                <FormControl fullWidth margin="dense">
                    <InputLabel>Author</InputLabel>
                    <Select name="author"
                            value={formData.author}
                            onChange={handleChange}
                            label="Author"
                            variant="outlined">
                        {authors.map((author)=>(
                            <MenuItem key={author.id} value={author.id}>{author.name}</MenuItem>
                        ))}
                    </Select>
                </FormControl>
            </DialogContent>
            <DialogActions>
                <Button onClick={onClose}>Cancel</Button>
                <Button onClick={handleSubmit} variant="contained" color="primary">Edit</Button>
            </DialogActions>
        </Dialog>
    );
};

export default EditBookDialog;