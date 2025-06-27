import useAuthors from "../../../../hooks/useAuthors.js";
import useCategories from "../../../../hooks/useCategories.js";
import React, {useState} from "react";
import {
    Button,
    Dialog,
    DialogActions,
    DialogContent,
    DialogTitle,
    FormControl,
    InputLabel,
    MenuItem,
    Select,
    TextField
} from "@mui/material";


const initialFormData = {
    "name":"",
    "category":"",
    "author":"",
};

const AddBookDialog=({open,onClose,onAdd})=>{
    const [formData,setFormData]=useState(initialFormData);
    const { authors, loading } = useAuthors();

    const categories= useCategories();

    const [errors, setErrors] = useState({});

    const handleChange = (event) => {
        const {name, value} = event.target;
        setFormData({...formData, [name]: value});
    };

    const handleSubmit=()=>{
        // onAdd(formData);

        const newErrors = {};

        if(!formData.name || formData.name.trim()===""){
            newErrors.name="Name is required.";
        }
        if (!formData.category) {
            newErrors.category = "Please select a category.";
        }
        if (!formData.author) {
            newErrors.author = "Please select a author.";
        }


        if (Object.keys(newErrors).length > 0) {
            setErrors(newErrors);
            return;
        }

        onAdd({
            name:formData.name,
            author:formData.author,
            category:formData.category,
        });

        setFormData(initialFormData);
        setErrors({});
        onClose();
    };

    return(
        <Dialog open={open} onClose={onClose}>
            <DialogTitle>Add Book</DialogTitle>
            <DialogContent>
                <TextField
                    margin="dense"
                    label="Name"
                    name="name"
                    value={formData.name}
                    onChange={handleChange}
                    fullWidth
                    error={!!errors.name}
                    helperText={errors.name}
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
                    {errors.category && (
                        <p style={{ color: 'red', fontSize: '0.8rem', margin: '3px 14px 0 14px' }}>
                            {errors.category}
                        </p>
                    )}
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
                {errors.author && (
                    <p style={{ color: 'red', fontSize: '0.8rem', margin: '3px 14px 0 14px' }}>
                        {errors.author}
                    </p>
                )}
                </FormControl>
            </DialogContent>
            <DialogActions>
                <Button onClick={onClose}>Cancel</Button>
                <Button onClick={handleSubmit} variant="contained" color="primary">Add</Button>
            </DialogActions>
        </Dialog>
    );
};
export default AddBookDialog;