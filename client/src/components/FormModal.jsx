import React, { useState } from 'react';
import PropTypes from 'prop-types';
import { Button, Modal, Box, TextField, Typography, FormControl, InputLabel, MenuItem, Select } from '@mui/material';
import CustomAlert from './Alert';

const style = {
  position: 'absolute',
  top: '50%',
  left: '50%',
  transform: 'translate(-50%, -50%)',
  width: 400,
  bgcolor: 'background.paper',
  border: '2px solid #000',
  boxShadow: 24,
  p: 4,
};

const FormModal = ({ formFields, buttonText, nameForm, onFormSubmit }) => {
  const initialFormData = formFields.reduce((acc, field) => {
    acc[field.name] = '';
    return acc;
  }, {});
  
  const [open, setOpen] = useState(false);
  const [formData, setFormData] = useState(initialFormData);

  // Alert process
  const [alertOpen, setAlertOpen] = useState(false);
  const [alertMessage, setAlertMessage] = useState('');
  const [alertSeverity, setAlertSeverity] = useState('success');

  const handleOpen = () => {
    setFormData(initialFormData); // Reset form data
    setOpen(true);
  };
  const handleClose = () => setOpen(false);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prevData) => ({
      ...prevData,
      [name]: value,
    }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    try {
      onFormSubmit(formData);
      setAlertMessage('Form submitted successfully!');
      setAlertSeverity('success');
    } catch (error) {
      setAlertMessage('Form submission failed.');
      setAlertSeverity('error');
    } finally {
      setAlertOpen(true);
      handleClose();
    }
  };

  const handleAlertClose = (event, reason) => {
    if (reason === 'clickaway') {
      return;
    }
    setAlertOpen(false);
  };

  return (
    <div>
      <Button variant="contained" color="primary" onClick={handleOpen}>
        {buttonText}
      </Button>
      <Modal
        open={open}
        onClose={handleClose}
        aria-labelledby="modal-title"
        aria-describedby="modal-description"
      >
        
          <Box sx={style}>
            <Typography
              id="modal-title"
              variant="h6"
              component="h2"
              textAlign="center"
            >
              {nameForm}
            </Typography>
            <form onSubmit={handleSubmit}>
              {formFields.map((field) => {
                if (field.type === "select") {
                  return (
                    <FormControl key={field.name} fullWidth margin="normal">
                      <InputLabel>{field.label}</InputLabel>
                      <Select
                        name={field.name}
                        value={formData[field.name]}
                        onChange={handleChange}
                      >
                        {field.options.map((option) => (
                          <MenuItem key={option.value} value={option.value}>
                            {option.label}
                          </MenuItem>
                        ))}
                      </Select>
                    </FormControl>
                  );
                } else {
                  return (
                    <TextField
                      key={field.name}
                      fullWidth
                      margin="normal"
                      label={field.label}
                      name={field.name}
                      value={formData[field.name]}
                      onChange={handleChange}
                      type={field.type}
                    />
                  );
                }
              })}
              <Button
                type="submit"
                variant="contained"
                color="primary"
                fullWidth
              >
                Submit
              </Button>
            </form>
          </Box>
          
       
      </Modal>
      <CustomAlert
        open={alertOpen}
        handleClose={handleAlertClose}
        severity={alertSeverity}
        message={alertMessage}
      />
    </div>
  );
};

FormModal.propTypes = {
  formFields: PropTypes.arrayOf(
    PropTypes.shape({
      name: PropTypes.string.isRequired,
      label: PropTypes.string.isRequired,
      type: PropTypes.string.isRequired,
      options: PropTypes.arrayOf(
        PropTypes.shape({
          value: PropTypes.string.isRequired,
          label: PropTypes.string.isRequired,
        })
      ),
    })
  ).isRequired,
  buttonText: PropTypes.string,
  nameForm: PropTypes.string.isRequired,
  onFormSubmit: PropTypes.func.isRequired,
};

FormModal.defaultProps = {
  buttonText: 'Open Form',
};

export default FormModal;