import React, { useState } from 'react';
import PropTypes from 'prop-types';
import { Button, TextField, Typography, Box, InputAdornment } from '@mui/material';
import CloudUploadIcon from '@mui/icons-material/CloudUpload';

const FormPage = ({ title, formFields, buttonText, onFormSubmit, displayUpload }) => {

  const [formData, setFormData] = useState(
    formFields.reduce((acc, field) => {
      acc[field.name] = '';
      return acc;
    }, {})
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
  };

  return (
    <Box>
      <Typography variant="h4" textAlign="center">{title}</Typography>
      <form onSubmit={handleSubmit}>
        {formFields.map((field) => (
          <TextField
            key={field.name}
            fullWidth
            margin="normal"
            type={field.type}
            multiline={field.multiline || false}
            rows={field.rows || 1}
            name={field.name}
            label={field.label}
            onChange={handleChange}
            required={field.required}
          />
        ))}
        {displayUpload && (
          <TextField
            fullWidth
            margin="normal"
            type="file"
            accept=".pdf,image/*"
            onChange={handleFileChange}
            InputProps={{
              startAdornment: (
                <InputAdornment position="start">
                  <CloudUploadIcon />
                </InputAdornment>
              ),
            }}
          />
        )}
        <Button type="submit" variant="contained" fullWidth>{buttonText}</Button>
      </form>
    </Box>
  );
};

FormPage.propTypes = {
  title: PropTypes.string.isRequired,
  formFields: PropTypes.arrayOf(PropTypes.object).isRequired,
  buttonText: PropTypes.string.isRequired,
  onFormSubmit: PropTypes.func.isRequired,
  displayUpload: PropTypes.bool,
};

FormPage.defaultProps = {
  displayUpload: false,
};

export default FormPage;