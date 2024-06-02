import React, { useState } from 'react';
import PropTypes from 'prop-types';
import { Button, TextField, Typography, Box, InputAdornment } from '@mui/material';
import CloudUploadIcon from '@mui/icons-material/CloudUpload';

const FormPage = ({ title, formFields, buttonText, onFormSubmit, displayUpload }) => {

  const [formData, setFormData] = useState(
    formFields.reduce((acc, field) => {
      acc[field.name] = '';
      return acc;
    }, [])
  );

  const [selectedFile, setSelectedFile] = useState(null);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prevData) => ({
      ...prevData,
      [name]: value
    }));
  };

  const handleFileChange = (e) => {
    setSelectedFile(e.target.files[0]);
    console.log(selectedFile);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    await onFormSubmit(formData);
    
    // const data = new FormData();
    // formFields.forEach((field) => {
    //   data.append(field.name, formData[field.name]);
    // });
    // if (selectedFile) {
    //   data.append('image', selectedFile);
    // }
    // await onFormSubmit(data);
  };

  return (
    <Box component="form" onSubmit={handleSubmit} sx={{ mt: 3 }}>
      <Typography variant="h6" component="h2" gutterBottom textAlign="center">
        {title}
      </Typography>
      {formFields.map((field) => (
        <TextField
          key={field.name}
          fullWidth
          margin="normal"
          label={field.label}
          name={field.name}
          value={formData[field.name]}
          onChange={handleChange}
          type={field.type}
          multiline={field.multiline || false}
          rows={field.rows || 1}
        />
      ))}
      {displayUpload && (
        <Button
          variant="contained"
          component="label"
          startIcon={<CloudUploadIcon />}
          sx={{ marginTop: 2, marginBottom: 2 }}
        >
          Upload Image
          <input
            type="file"
            hidden
            onChange={handleFileChange}
          />
        </Button>
      )}
      <Button type="submit" variant="contained" color="primary" fullWidth>
        {buttonText}
      </Button>
    </Box>
  );
};

FormPage.propTypes = {
  formFields: PropTypes.arrayOf(
    PropTypes.shape({
      name: PropTypes.string.isRequired,
      label: PropTypes.string.isRequired,
      type: PropTypes.string.isRequired,
      multiline: PropTypes.bool,
      rows: PropTypes.number,
    })
  ).isRequired,
  buttonText: PropTypes.string,
  onFormSubmit: PropTypes.func.isRequired,
  displayUpload: PropTypes.bool,
};

FormPage.defaultProps = {
  buttonText: 'Submit',
  displayUpload: false,
};

export default FormPage;
