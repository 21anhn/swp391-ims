import React from 'react';
import { Modal, Box, Typography, TextField, Button, FormControl, InputLabel, MenuItem, Select } from '@mui/material';
import { useModalContext } from '../context/ModalContext';

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

const FormModal = ({ formFields, nameForm, onFormSubmit }) => {
  const { modalOpen, handleClose, formData, handleChange, handleSubmit } = useModalContext();

  return (
    <Modal
      open={modalOpen}
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
        <form onSubmit={(e) => handleSubmit(e, onFormSubmit)}>
          {formFields.map((field) => {
            if (field.type === "select") {
              return (
                <FormControl key={field.name} fullWidth margin="normal">
                  <InputLabel>{field.label}</InputLabel>
                  <Select
                    name={field.name}
                    value={formData[field.name] || ''}
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
            } else if (field.type === 'file') {
              return (
                <input
                  key={field.name}
                  type="file"
                  name={field.name}
                  accept={field.accept}
                  onChange={handleChange}
                  style={{ margin: '10px 0' }}
                />
              );
            } else {
              return (
                <TextField
                  key={field.name}
                  fullWidth
                  margin="normal"
                  label={field.label}
                  name={field.name}
                  value={formData[field.name] || ''}
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
  );
};

export default FormModal;