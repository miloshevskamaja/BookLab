import {Grid} from "@mui/material";
import AuthorCard from "../AuthorCard/AuthorCard.jsx";


const AuthorGrid=({authors})=>{
    return (
        <Grid container spacing={{xs:2, md:3}}>
            {authors.map((author)=>(
                <Grid key={author.id} size={{xs:12, sm:6, md:4,lg:3}}>
                    <AuthorCard author={author}>
                    </AuthorCard>
                </Grid>
            ))}
        </Grid>
    );
};

export default AuthorGrid;