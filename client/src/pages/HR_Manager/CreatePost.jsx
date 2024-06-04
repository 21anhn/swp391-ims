import React from 'react'
import FormPage from '../../components/FormPage'
import { Box } from '@mui/material';
import { createPost } from '../../services/apiServices';
<<<<<<< HEAD
=======
import { useAuth } from '../../context/AuthContext';
>>>>>>> update

function CreatePost() {
    const [message, setMessage] = React.useState('');
    const [error, setError] = React.useState('');
<<<<<<< HEAD
=======
    const {user} = useAuth();
>>>>>>> update

    const postFields = [
        { name: 'campaignName', label: 'Campaigns name', type: 'text', multiline: true, rows: 2 },
        { name: 'jobDescription', label: 'Job description', type: 'text', multiline: true, rows: 5 },
        { name: 'requirements', label: 'Requirement', type: 'text', multiline: true, rows: 4 },
        { name: 'deadline', label: '', type: 'date'  },
    ]

    const handleFormSubmit = async (formData) => {
<<<<<<< HEAD
=======
        formData['hr_id'] = user.data.id;
>>>>>>> update
        console.log('Form Data:', formData);
        try {
          const response = await createPost(formData);
          console.log("Create successfully:", response.data);
          setMessage("Post created successfully!");
          setError("");
        } catch (error) {
          console.error("Create failed:", error);
          setError("Failed to create post");
          setMessage("");
        }
    };

  return (
    <Box 
    height="80vh"
    bgcolor="white"
    p="5px 30px"
    m="10px 20px"
    borderRadius="30px">
        {error && <p style={{ color: 'red' }}>{error}</p>}
        {message && <p style={{ color: 'green' }}>{message}</p>}
        <FormPage title='Creat post internship campaigns' formFields={postFields} buttonText="Create post" onFormSubmit={handleFormSubmit} displayUpload={false}/>
    </Box>
  )
}

export default CreatePost