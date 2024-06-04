
import React from 'react';
import { TextField, Button, Grid, TextareaAutosize } from '@mui/material';

const InternshipForm = ({ formData, handleChange, handleSubmit }) => {
  return (
    <form onSubmit={handleSubmit}>
      <Grid container spacing={2}>
        <Grid item xs={12}>
          <TextField
            name="title"
            label="Title"
            fullWidth
            variant="outlined"
            value={formData.title}
            onChange={handleChange}
          />
        </Grid>
        <Grid item xs={12}>
          <TextareaAutosize
            name="introduction"
            minRows={4}
            placeholder="Introduction"
            style={{ width: '100%', padding: '10px' }}
            value={formData.introduction}
            onChange={handleChange}
          />
        </Grid>
        <Grid item xs={12} sm={6}>
          <TextField
            name="position"
            label="Position"
            fullWidth
            variant="outlined"
            value={formData.position}
            onChange={handleChange}
          />
        </Grid>
        <Grid item xs={12} sm={6}>
          <TextField
            name="duration"
            label="Duration"
            fullWidth
            variant="outlined"
            value={formData.duration}
            onChange={handleChange}
          />
        </Grid>
        <Grid item xs={12}>
          <TextareaAutosize
            name="jobDescription"
            minRows={4}
            placeholder="Job Description"
            style={{ width: '100%', padding: '10px' }}
            value={formData.jobDescription}
            onChange={handleChange}
          />
        </Grid>
        <Grid item xs={12}>
          <TextField
            name="location"
            label="Location"
            fullWidth
            variant="outlined"
            value={formData.location}
            onChange={handleChange}
          />
        </Grid>
        <Grid item xs={12}>
          <TextField
            name="education"
            label="Education"
            fullWidth
            variant="outlined"
            value={formData.education}
            onChange={handleChange}
          />
        </Grid>
        <Grid item xs={12}>
          <TextField
            name="skills"
            label="Skills"
            fullWidth
            variant="outlined"
            value={formData.skills}
            onChange={handleChange}
          />
        </Grid>
        <Grid item xs={12}>
          <TextField
            name="experience"
            label="Experience"
            fullWidth
            variant="outlined"
            value={formData.experience}
            onChange={handleChange}
          />
        </Grid>
        <Grid item xs={12}>
          <TextareaAutosize
            name="benefits"
            minRows={4}
            placeholder="Benefits"
            style={{ width: '100%', padding: '10px' }}
            value={formData.benefits}
            onChange={handleChange}
          />
        </Grid>
        <Grid item xs={12}>
          <TextareaAutosize
            name="applicationProcess"
            minRows={4}
            placeholder="Application Process"
            style={{ width: '100%', padding: '10px' }}
            value={formData.applicationProcess}
            onChange={handleChange}
          />
        </Grid>
        <Grid item xs={12}>
          <TextField
            name="contactInformation"
            label="Contact Information"
            fullWidth
            variant="outlined"
            value={formData.contactInformation}
            onChange={handleChange}
          />
        </Grid>
        <Grid item xs={12}>
          <TextField
            name="media"
            label="Media"
            fullWidth
            variant="outlined"
            value={formData.media}
            onChange={handleChange}
          />
        </Grid>
        <Grid item xs={12}>
          <TextareaAutosize
            name="testimonials"
            minRows={4}
            placeholder="Testimonials"
            style={{ width: '100%', padding: '10px' }}
            value={formData.testimonials}
            onChange={handleChange}
          />
        </Grid>
        <Grid item xs={12}>
          <TextField
            name="callToAction"
            label="Call to Action"
            fullWidth
            variant="outlined"
            value={formData.callToAction}
            onChange={handleChange}
          />
        </Grid>
        <Grid item xs={12}>
          <Button type="submit" fullWidth variant="contained" color="primary">
            Submit
          </Button>
        </Grid>
      </Grid>
    </form>
  );
};

export default InternshipForm;