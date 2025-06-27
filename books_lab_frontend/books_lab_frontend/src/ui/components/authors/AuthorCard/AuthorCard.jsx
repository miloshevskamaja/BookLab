import {Card, CardContent, Typography} from "@mui/material";


const AuthorCard = ({author}) => {
    console.log(author);
    return (
        <>
        <Card>
            <CardContent>
            <Typography variant="h5">{author.name} {author.surname}</Typography>
            <Typography variant="body1" fontWeight="bold">Born in country with id {author.country}</Typography>
            </CardContent>
            </Card>
        </>
    );
};

export default AuthorCard;