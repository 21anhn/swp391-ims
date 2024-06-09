import React from "react";
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
import DeleteOutlineIcon from "@mui/icons-material/DeleteOutline";
import BorderColorIcon from "@mui/icons-material/BorderColor";
import Table from "../../components/Table";
import { fetchSchedule } from "../../services/apiServices";
import { useParams } from "react-router-dom";

export default function ListSchedule() {
    const {id} = useParams();
  const [schedule, setSchedule] = React.useState([]);
  const [error, setError] = React.useState("");
  //form filter
  const options = ["Option 1", "Option 2"];
  const [value, setValue] = React.useState(options[0]);
  const [inputValue, setInputValue] = React.useState("");

  const getSchedule = async () => {
    try {
      const res = await fetchSchedule(id);
      setSchedule(res);
      setError("");
    } catch (err) {
      setError(err.message);
    }
  };

  React.useEffect(() => {
    getSchedule();
  }, []);

  const columns = [
    { field: "id", headerName: "ID" },
    {
      field: "campaignName",
      headerName: "Campaign Name",
      flex: 0.2,
      cellClassName: "name-column--cell",
    },
    { field: "dateTime", headerName: "Date time", flex: 0.2 },
    { field: "location", headerName: "Location", flex: 0.2 },
    {
      field: "button",
      headerName: "",
      flex: 0.1,
      renderCell: (params) => {
        return (
          <>
            <Box
              m="5px"
              display="flex"
              borderRadius="5px"
              border="1px solid red"
            >
              <IconButton
                color="error"
                sx={{ fontSize: 15, p: "5px" }}
                onClick={() => handleRowButtonClick(params.row.id)}
              >
                <DeleteOutlineIcon fontSize="8px" />
              </IconButton>
            </Box>
            <Box
              m="5px"
              display="flex"
              borderRadius="5px"
              border="1px solid #1976D2"
            >
              <IconButton
                color="primary"
                sx={{ fontSize: 15, p: "5px" }}
                onClick={() => handleRowButtonClick(params.row.id)}
              >
                <BorderColorIcon fontSize="8px" />
              </IconButton>
            </Box>
          </>
        );
      },
    },
  ];

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
          List schedule
        </Typography>
        <Box display="flex" borderRadius={2} backgroundColor="white">
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
                    p: 0,
                  },
                  ".MuiOutlinedInput-notchedOutline": {
                    border: "2px solid #E0E0E0",
                  },
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
            marginTop: "30px",
          },
          ".MuiDataGrid-root .MuiDataGrid-container--top [role=row]": {
            textAlign: "center",
            fontWeight: 700,
          },
          "& .MuiDataGrid-cell": {
            borderBottom: "none",
            display: "flex",
            alignItems: "center",
          },
          "& .MuiDataGrid-columnHeader": {
            textAlign: "center",
            fontWeight: 700,
          },
          "& .MuiDataGrid-footerContainer": {
            borderTop: "none",
          },
        }}
      >
        <Table columns={columns} rows={schedule} pageSize={10} />
      </Box>
    </Box>
  );
}
