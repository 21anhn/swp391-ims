import React from 'react'
import { Typography, Card, CardContent, CardActions, Button, CardMedia } from '@mui/material';

function CardItem({id, image, title, description,detailButtonText, applyButtonText, onDetailButtonClick, onApplyButtonClick}) {
  return (
    <Card sx={{ width: "100%" }}>
      <CardMedia
        sx={{ height: 200 }}
        image={image}
        title={title}
      />
      <CardContent>
        <Typography gutterBottom variant="body1" component="div" fontWeight="bold">
          {title}
        </Typography>
        <Typography variant="body2" color="text.secondary">
          {description}
        </Typography>
      </CardContent>
      <CardActions>
      {onDetailButtonClick && (
          <Button variant="outlined" onClick={() => onDetailButtonClick(id)}>
            {detailButtonText || 'Detail'}
          </Button>
        )}
        {onApplyButtonClick && (
          <Button variant="contained" onClick={onApplyButtonClick}>
            {applyButtonText || 'Apply'}
          </Button>
        )}
      </CardActions>
    </Card>
  );
}

export default CardItem