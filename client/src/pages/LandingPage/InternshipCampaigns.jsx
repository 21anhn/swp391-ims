import { Card, CardActions, CardContent, Container, Typography, CardHeader, Avatar, Button, Grid } from '@mui/material';
import axios from 'axios';
import React, { useEffect, useState } from 'react'

const URL = 'https://664ea233fafad45dfae0a20e.mockapi.io/api/login/schema';

function InternshipCampaigns() {

    const [user,setUser] = useState(null);

    const getListUser = async () => {
        const res = await axios.get(`${URL}`)
        if(res.status === 200) {
            setUser(res.data);
        }
    }

    useEffect(() => {
        getListUser();
    }, [])

  return (
    <Container>
      <Grid container spacing={2}>
        {user &&
          user.map((user) => (
            <Grid item xs={4} key={user.id}>
              <Card>
                <CardHeader
                  avatar={<Avatar alt={user.name} src={user.avatar}></Avatar>}
                />
                <CardContent>
                  <Typography>{user.name}</Typography>
                </CardContent>
                <CardActions>
                  <Button variant="contained" color="success">
                    View detail
                  </Button>
                </CardActions>
              </Card>
            </Grid>
          ))}
      </Grid>
    </Container>
  );
}

export default InternshipCampaigns