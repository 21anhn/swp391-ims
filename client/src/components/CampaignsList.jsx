import React from 'react'
import { Grid } from '@mui/material'
import  CardItem  from './CardItem'

export default function CampaignsList({campaigns, view, text}) {
  return (
    <Grid container spacing={2} p="20px">
      {campaigns &&
        campaigns.map((item) => (
          <Grid item xs={4} key={item.id}>
            <CardItem
              id={item.id}
              image={item.image}
              title={item.campaignName}
              description={item.jobDescription}
              firstButtonText ={text}
              firstButtonClick={view}
            />
          </Grid>
        ))}
    </Grid>
  )
}
