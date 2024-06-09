import React from 'react'
import { Typography, Card, CardContent, CardActions, Button, CardMedia } from '@mui/material';

function  CardItem({id, image, title, description,firstButtonText, secondButtonText, firstButtonClick, secondButtonClick}) {
  return (
    <Card sx={{ width: "100%" }}>
      <CardMedia
        sx={{ height: 200 }}
        image={image}
        title={title}
      />
      <CardContent>
        <Typography gutterBottom variant="body1" component="div" fontWeight="bold" noWrap>
          {title}
        </Typography>
        <Typography variant="body2" color="text.secondary">
          {description}
        </Typography>
      </CardContent>
      <CardActions>
      {firstButtonClick && (
          <Button variant="outlined" onClick={() => firstButtonClick(id)}>
            {firstButtonText || 'Detail'}
          </Button>
        )}
        {secondButtonClick && (
          <Button variant="contained" onClick={() => secondButtonClick(id)}>
            {secondButtonText || 'Apply'}
          </Button>
        )}
      </CardActions>
    </Card>
  );
}

export default CardItem