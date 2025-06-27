
import {Box, Button, Card, CardActions, CardContent, Typography} from "@mui/material";
import {useNavigate} from "react-router";
import {useState} from "react";
import EditIcon from '@mui/icons-material/Edit';
import DeleteIcon from '@mui/icons-material/Delete';
import InfoIcon from '@mui/icons-material/Info';
import DeleteBookDialog from "../DeleteBookDialog/DeleteBookDialog.jsx";
import EditBookDialog from "../EditBookDialog/EditBookDialog.jsx";

const BookCard = ({book, onDelete, onEdit}) =>{
    const navigate = useNavigate();
    const [deleteBookDialogOpen, setDeleteBookDialogOpen] = useState(false);
    const [editBookDialogOpen, setEditBookDialogOpen] = useState(false);
  return (
      <>
      <Card>
          <CardContent>
            <Typography variant="h5">{book.name}</Typography>
              <Typography variant="subtitle2">
                  Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ab aperiam assumenda blanditiis cum
                  ducimus enim modi natus odit quibusdam veritatis.
              </Typography>
              <Typography variant="body1" fontWeight="bold"
                            sx={{textAlign:"right", fontSize:"1.25rem"}}>Book category: {book.category}
              </Typography>
              <Typography variant="body2" sx={{textAlign:"right"}}>Author id: {book.author}</Typography>
          </CardContent>
          <CardActions sx={{justifyContent: "space-between"}}>
              <Button size="small" color="info" startIcon={<InfoIcon/>} onClick={()=>navigate(`/books/${book.id}`)}>
                  Details
              </Button>
              <Box>
              <Button size="small" color="warning" startIcon={<EditIcon/>} sx={{mr:"0.25rem"}} onClick={()=> setEditBookDialogOpen(true)}>Edit</Button>
              <Button size="small" color="error" startIcon={<DeleteIcon/>} onClick={()=>setDeleteBookDialogOpen(true)}>Delete</Button>
              </Box>
              </CardActions>
      </Card>
          <DeleteBookDialog
          open={deleteBookDialogOpen}
          onClose={() => setDeleteBookDialogOpen(false)}
          book={book}
          onDelete={onDelete}
          />
          <EditBookDialog
              open={editBookDialogOpen}
              onClose={()=>setEditBookDialogOpen(false)}
              book={book}
              onEdit={onEdit}
          />

      </>
  );
};

export default BookCard;