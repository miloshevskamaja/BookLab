import React from 'react';
import {Button, Dialog, DialogActions, DialogContent, DialogTitle} from "@mui/material";



const DeleteBookDialog=({open, onClose, book, onDelete})=>{
  const handleSubmit = () =>{
      onDelete(book.id);
      onClose();
  }
  return (
      <Dialog open={open} onClose={onClose}>
          <DialogTitle>
              Delete a Book
          </DialogTitle>
          <DialogContent>
              Are you sure you want to delete <strong>{book.name}</strong>? This action cannot be undone.
          </DialogContent>
          <DialogActions>
              <Button onClick={onClose}>Cancel</Button>
              <Button onClick={handleSubmit} colot="error" variant="contained">Delete</Button>
          </DialogActions>
      </Dialog>
  );
};

export default DeleteBookDialog;