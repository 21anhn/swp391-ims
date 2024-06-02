import React, { createContext, useState, useContext } from 'react';

// Create a context
const ModalContext = createContext();

// Create a provider component
export const ModalProvider = ({ children }) => {
  const [modalOpen, setModalOpen] = useState(false);
  const [formData, setFormData] = useState({});

  const handleOpen = (initialFormData) => {
    setFormData(initialFormData);
    setModalOpen(true);
  };

  const handleClose = () => setModalOpen(false);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prevData) => ({
      ...prevData,
      [name]: value,
    }));
  };

  const handleSubmit = (e, onSubmit) => {
    e.preventDefault();
    try {
      onSubmit(formData);
      alert('Form submitted successfully!');
    } catch (error) {
      alert('Form submission failed.');
    } finally {
      handleClose();
    }
  };

  return (
    <ModalContext.Provider value={{ modalOpen, handleOpen, handleClose, formData, handleChange, handleSubmit }}>
      {children}
    </ModalContext.Provider>
  );
};

// Create a custom hook to use the ModalContext
export const useModalContext = () => {
  return useContext(ModalContext);
};