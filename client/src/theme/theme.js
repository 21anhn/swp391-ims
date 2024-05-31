import { colors } from '@mui/material';
import { createTheme } from '@mui/material/styles';

const theme = createTheme({
  palette: {
    background: {
      default: "#F5F6F7",  // Màu nền chính
    },
    primary: {
      main: '#1976d2',  // Màu chính cho các thành phần chính
    },
    secondary: {
      main: '#dc004e',  // Màu phụ cho các thành phần phụ
    },
    text: {  // Màu chính của chữ
      secondary: '#6a1b9a',  // Màu phụ của chữ
    },
    error: {
      main: '#d32f2f',  // Màu thông báo lỗi
    },
    warning: {
      main: '#ffa000',  // Màu thông báo cảnh báo
    },
    info: {
      main: '#0288d1',  // Màu thông báo thông tin
    },
    success: {
      main: '#388e3c',  // Màu thông báo thành công
    },
    typography: {
      color: "#000000",
      fontFamily: ["Source Sans Pro", "sans-serif"].join(","),
      fontSize: 12,
      h1: {
        fontFamily: ["Source Sans Pro", "sans-serif"].join(","),
        fontSize: 40
      },
      h2: {
        fontFamily: ["Source Sans Pro", "sans-serif"].join(","),
        fontSize: 32
      },
      h3: {
        fontFamily: ["Source Sans Pro", "sans-serif"].join(","),
        fontSize: 24
      },
      h4: {
        fontFamily: ["Source Sans Pro", "sans-serif"].join(","),
        fontSize: 20
      },
      h5: {
        fontFamily: ["Source Sans Pro", "sans-serif"].join(","),
        fontSize: 16
      },
      h6: {
        fontFamily: ["Source Sans Pro", "sans-serif"].join(","),
        fontSize: 14
      }
    }
  },
});

export default theme;
