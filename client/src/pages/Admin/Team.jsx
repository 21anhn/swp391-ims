import React, { useEffect, useState } from "react";
import {
  Box,
  InputBase,
  Typography,
  IconButton,
  Autocomplete,
  Stack,
  TextField,
} from "@mui/material";
import SearchIcon from "@mui/icons-material/Search";
import DeleteOutlineIcon from '@mui/icons-material/DeleteOutline';
import Table from "../../components/Table";
import FormModal from "../../components/FormModal";
import { createAccount as createPostAPI } from "../../services/apiServices";
import { fetchAccounts as fetchAccoutsAPI } from "../../services/apiServices";

const Team = () => {
  const [user, setUser] = useState([]);
  const [error, setError] = useState('');

  const getListUser = async () => {
    try {
      const res = await fetchAccoutsAPI();
      setUser(res.data);
      console.log(user)
      setError('');
    } catch (err) {
      setError(err.message);
    }
  };

  useEffect(() => {
    getListUser();
  }, []);

  const columns = [
    { field: 'id', headerName: 'ID' },
    {
      field: 'username',
      headerName: 'Username',
      width: 400,
      cellClassName: 'name-column--cell',
    },
    { field: 'phoneNumber', headerName: 'Phone Number', width: 300 },
    { field: 'email', headerName: 'Email', width: 600 },
    {
      field: 'roleName',
      headerName: 'Role',
      width: 270,
      renderCell: ({ row: { roleName } }) => {
        return (
          <Box
            width="100%"
            display="flex"
            borderRadius="5px"
          >
            <Typography variant="body2">{roleName}</Typography>
          </Box>
        );
      },
    },
    {
      field: 'button',
      headerName: '',
      width: 50,
      renderCell: (params) => {
        return (
          <Box m="5px" display="flex" justifyContent="end" borderRadius="5px" border="1px solid red">
            <IconButton
              color="error"
              sx={{ fontSize: 15, p: "5px" }}
              onClick={() => handleRowButtonClick(params.row.id)}
            >
              <DeleteOutlineIcon fontSize="8px" />
            </IconButton>
          </Box>
        );
      },
    },
  ];;

  //Form filter
  const options = ["Option 1", "Option 2"];
  const [value, setValue] = React.useState(options[0]);
  const [inputValue, setInputValue] = React.useState("");

  const accountFields = [
    { name: 'username', label: 'User name', type: 'text' },
    { name: 'phoneNumber', label: 'Phone number', type: 'number' },
    { name: 'email', label: 'Email', type: 'text' },
    {
      name: 'roleName', label: 'Role', type: 'select',
      options: [
        { value: "ROLE_ADMIN", label: "ADMIN" },
        { value: "ROLE_HR_MANAGER", label: "HR MANAGER" },
        { value: "ROLE_INTERN", label: "INTERN" },
        { value: "ROLE_MENTOR", label: "MENTOR" },
        { value: "ROLE_ICOORDINATOR", label: "INTERNSHIP COORDINATOR" }
      ]
    }
  ]

  const handleFormSubmit = async (formData) => {
    console.log('Form Data:', formData);

    try {
      const response = await createPostAPI(formData);
      console.log('Create successfully', response);
      await getListUser();
    } catch (error) {
      console.log('Create fail');
    }
  };

  const handleRowButtonClick = (id) => {
    console.log('Button clicked for row with id:', id);
    // Perform action based on row id
  };

  return (
    <Box m="20px">
      <Box
        display="flex"
        justifyContent="space-between"
        alignItems="center"
        bgcolor="white"
        p="5px 30px"
        m="0 20px"
        borderRadius="30px"
      >
        <Typography sx={{ ml: "5px", fontWeight: 700 }} variant="h5">
          List account
        </Typography>
        <Box
          display="flex"
          borderRadius={2}
          backgroundColor="white"
        >
          <Stack direction="row" alignItems="center" spacing={2}>
            <Stack
              direction="row"
              alignItems="center"
              sx={{
                p: "0 5px",
                flex: 1,
                border: "2px solid #E0E0E0",
                borderRadius: "30px",
              }}
            >
              <InputBase placeholder="Search" />
              <IconButton type="button">
                <SearchIcon />
              </IconButton>
            </Stack>
            <Stack spacing={5} sx={{ width: 200 }}>
              <Autocomplete
                sx={{
                  ".MuiOutlinedInput-root .MuiAutocomplete-input": {
                    p: 0
                  },
                  ".MuiOutlinedInput-notchedOutline": {
                    border: "2px solid #E0E0E0"
                  }
                }}
                value={value}
                onChange={(event, newValue) => {
                  setValue(newValue);
                }}
                inputValue={inputValue}
                onInputChange={(event, newInputValue) => {
                  setInputValue(newInputValue);
                }}
                id="controllable-states-demo"
                options={options}
                renderInput={(params) => (
                  <TextField {...params} />
                  // label="Role"
                )}
              />
            </Stack>
          </Stack>
        </Box>
      </Box>
      <Box
        height="80vh"
        bgcolor="white"
        p="5px 30px"
        m="10px 20px"
        borderRadius="30px"
        sx={{
          "& .MuiDataGrid-root": {
            border: "none",
            borderRadius: "10px",
          },
          ".MuiDataGrid-root .MuiDataGrid-container--top [role=row]": {
            textAlign: "center",
            fontWeight: 700
          },
          "& .MuiDataGrid-cell": {
            borderBottom: "none",
            display: "flex",
            alignItems: "center",
          },
          "& .MuiDataGrid-columnHeader": {
            textAlign: "center",
            fontWeight: 700
          },
          "& .MuiDataGrid-footerContainer": {
            borderTop: "none",
          },
        }}
      >
        <Box sx={{ m: "20px 0" }}>
          <FormModal formFields={accountFields} buttonText="Create" nameForm="Create account" onFormSubmit={handleFormSubmit} />
        </Box>
        <Table columns={columns} rows={user} pageSize={12} />
      </Box>
    </Box>
  );
};

export default Team;
