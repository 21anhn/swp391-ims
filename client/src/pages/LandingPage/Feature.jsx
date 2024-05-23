import React from "react";
import {
  Container,
  Typography,
  Grid,
  Stack,
  CardMedia,
} from "@mui/material";
import CoPresentIcon from "@mui/icons-material/CoPresent";
import ComputerIcon from "@mui/icons-material/Computer";
import VolunteerActivismIcon from "@mui/icons-material/VolunteerActivism";

const features = [
  { title: "Feature 1", description: "Description of feature 1" },
  { title: "Feature 2", description: "Description of feature 2" },
  // Add more features as needed
];

function Features() {
  return (
    <Container>
      <Typography
        variant="h4"
        textAlign="center"
        sx={{ color: "#0F70D1", fontWeight: 700 }}
      >
        Why Choose Us
      </Typography>
      <Grid container>
        <Grid item xs={6}>
          <Stack direction="row" alignItems="center" sx={{ marginTop: 4 }}>
            <CoPresentIcon sx={{ color: "#0F70D1" }}></CoPresentIcon>
            <Typography variant="h6" sx={{ marginLeft: 2, fontWeight: 700 }}>
              Mentor experience
            </Typography>
          </Stack>
          <Typography>
            Lorem ipsum dolor sit amet consectetur adipisicing elit. At possimus
            dolor aperiam corrupti! Id porro repudiandae impedit fugiat? Iusto
            illum possimus enim aut adipisci omnis sequi minima iste sapiente
            magni.
          </Typography>
          <Stack direction="row" alignItems="center" sx={{ marginTop: 4 }}>
            <ComputerIcon sx={{ color: "#0F70D1" }}></ComputerIcon>
            <Typography variant="h6" sx={{ marginLeft: 2, fontWeight: 700 }}>
              Quality project
            </Typography>
          </Stack>
          <Typography>
            Lorem ipsum dolor sit amet, consectetur adipisicing elit. Facere
            accusantium doloremque eius vel dolor vero officiis rem atque
            asperiores dolorem aliquid praesentium possimus maiores, cupiditate
            ab velit distinctio esse. Aperiam.
          </Typography>
          <Stack direction="row" alignItems="center" sx={{ marginTop: 4 }}>
            <VolunteerActivismIcon
              sx={{ color: "#0F70D1" }}
            ></VolunteerActivismIcon>
            <Typography variant="h6" sx={{ marginLeft: 2, fontWeight: 700 }}>
              Opportunity
            </Typography>
          </Stack>
          <Typography>
            Lorem ipsum dolor sit amet consectetur adipisicing elit. Molestias
            placeat aperiam cum id fugit inventore, quibusdam eveniet, eaque
            natus fugiat ducimus dolorem ipsa laborum, ut maxime facilis animi
            voluptas hic.
          </Typography>
        </Grid>
        <Grid item xs={6}>
          <Stack direction="row" alignItems="center">
            <CardMedia
              sx={{ height: 140 }}
              image="https://blogs.ntu.edu.sg/adminternship/files/2019/10/BG3-12-1024x1024.jpg"
              title="green iguana"
            />
          </Stack>
        </Grid>
      </Grid>
    </Container>
  );
}

export default Features;
