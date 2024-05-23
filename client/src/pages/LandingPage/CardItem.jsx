import React from 'react'
import { Typography, Card, CardContent, CardActions, Button, CardMedia } from '@mui/material';

function CardItem() {
  return (
    <Card sx={{ maxWidth: 345 }}>
      <CardMedia
        sx={{ height: 140 }}
        image="https://blogs.ntu.edu.sg/adminternship/files/2019/10/BG3-12-1024x1024.jpg"
        title="green iguana"
      />
      <CardContent>
        <Typography gutterBottom variant="h5" component="div">
          Lizard
        </Typography>
        <Typography variant="body2" color="text.secondary">
          Lizards are a widespread group of squamate reptiles, with over 6,000
          species, ranging across all continents except Antarctica
        </Typography>
      </CardContent>
      <CardActions>
        <Button variant='outlined' >Detail Job</Button>
        <Button variant="contained">Apply</Button>
      </CardActions>
    </Card>
  )
}

export default CardItem