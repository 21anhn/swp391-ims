import React from "react";
import {
  Container,
  Typography,
  Grid,
  Paper,
  List,
  ListItem,
  ListItemText,
  Box,
  Button,
  Chip,
  Stack,
  CircularProgress
} from "@mui/material";
import FormModal from "../../components/FormModal";
import { ModalProvider } from "../../context/ModalContext";
import { useModalContext } from "../../context/ModalContext";
import { createApplication } from "../../services/apiServices";
import { useParams } from "react-router-dom";
import { fetchPostDetail } from "../../services/apiServices";

const ApplicationButton = () => {
    const { handleOpen } = useModalContext();
  
    const applicationFields = [
      { name: "fullName", label: "Fullname", type: "text" },
      { name: "email", label: "Email", type: "text" },
      { name: "phoneNumber", label: "Phone number", type: "number" },
      { name: "intro", label: "Introduce your self", type: "text" },
    ];
  
    return (
      <Button fullWidth variant="contained" color="primary" onClick={() => handleOpen(applicationFields.reduce((acc, field) => {
        acc[field.name] = '';
        return acc;
      }, {}))}>
        Apply now
      </Button>
    );
  };  

function DetailCampaign() {
    const {id} = useParams();
    const [post, setPost] = React.useState(null);
    const [loading, setLoading] = React.useState(true);
    const [error, setError] = React.useState(null);
  
    const applicationFields = [
      { name: "fullName", label: "Fullname", type: "text" },
      { name: "email", label: "Email", type: "text" },
      { name: "phoneNumber", label: "Phone number", type: "number" },
      { name: "intro", label: "Introduce your self", type: "text" },
      { name: "file", type: "file" },
    ];

    React.useEffect(() => {
        const fetchData = async () => {
          try {
            const response = await fetchPostDetail(id);
            setPost(response);
            setLoading(false);
          } catch (error) {
            setError(error);
            setLoading(false);
          }
        };
    
        fetchData();
    }, [id]);

    const handleFormSubmit = async (formData) => {
      console.log("Form Data:", formData);

      // try {
      //   const response = await createApplication(formData);
      //   console.log('Create successfully', response);
      // } catch(error) {
      //   console.log('Create fail');
      // }
    };

    if (loading) return <CircularProgress />;
    if (error) return <Typography color="error">Error: {error.message}</Typography>;

  return (
    <ModalProvider>
      <Box
        sx={{
          bgcolor: "#F6FCFF",
          ".MuiList-root": {
            p: 0,
          },
          ".MuiListItem-root": {
            p: 0,
          },
        }}
      >
        <Container>
          <Grid container spacing={2}>
            <Grid item xs={8}>
              <Paper elevation={3} sx={{ padding: 3, mb: 2 }}>
                <Box mb={2} borderBottom={1} borderColor="divider">
                  <Typography variant="h4" fontWeight="bold">
                    Mô tả công việc
                  </Typography>
                </Box>
                <Stack borderBottom={1} borderColor="divider">
                  <Typography variant="body1" gutterBottom>
                    {post.campaignName}
                  </Typography>
                </Stack>
                <Stack borderBottom={1} borderColor="divider">
                  <Typography variant="h6" fontWeight="bold" gutterBottom>
                    Trách nhiệm công việc
                  </Typography>
                  <List>
                    <ListItem>
                      <ListItemText primary="Tham gia phát triển Game sử dụng Cocos Creator;" />
                    </ListItem>
                    <ListItem>
                      <ListItemText primary="Tham gia đóng góp ý tưởng để cải tiến tính năng trong game cũng như chất lượng sản phẩm;" />
                    </ListItem>
                    <ListItem>
                      <ListItemText primary="Phối hợp với các team để cùng hoàn thành dự án;" />
                    </ListItem>
                    <ListItem>
                      <ListItemText primary="Một số công việc khác theo sự phân công của quản lý." />
                    </ListItem>
                  </List>
                </Stack>
                <Stack borderBottom={1} borderColor="divider">
                  <Typography variant="h6" gutterBottom fontWeight="bold">
                    Kỹ năng & Chuyên môn
                  </Typography>
                  <List>
                    <ListItem>
                      <ListItemText primary="Tối thiểu 1 - 2 năm kinh nghiệm làm Game Developer." />
                    </ListItem>
                    <ListItem>
                      <ListItemText primary="Có thể làm việc fulltime;" />
                    </ListItem>
                    <ListItem>
                      <ListItemText primary="Có kiến thức tốt về thuật toán, cấu trúc dữ liệu và lập trình hướng đối tượng (OOP);" />
                    </ListItem>
                    <ListItem>
                      <ListItemText primary="Có kiến thức tốt về việc sử dụng git version control;" />
                    </ListItem>
                    <ListItem>
                      <ListItemText primary="Có sản phẩm đã/đang phát hành trên các store (Android, iOS) là một lợi thế;" />
                    </ListItem>
                    <ListItem>
                      <ListItemText primary="Có tư duy tốt, ham học hỏi, chăm chỉ, cầu tiến;" />
                    </ListItem>
                    <ListItem>
                      <ListItemText primary="Có khả năng chịu được áp lực công việc, có khả năng làm việc nhóm;" />
                    </ListItem>
                  </List>
                </Stack>
                <Stack>
                  <Typography variant="h6" gutterBottom fontWeight="bold">
                    Phúc lợi dành cho bạn
                  </Typography>
                  <List>
                    <ListItem>
                      <ListItemText primary="Lương tháng 15 triệu đến 50 triệu tùy theo năng lực, có thể hưởng % của dự án." />
                    </ListItem>
                    <ListItem>
                      <ListItemText primary="Được làm việc trong môi trường năng động với những quản lý tâm lý và những đồng nghiệp trẻ tuổi đầy nhiệt huyết." />
                    </ListItem>
                    <ListItem>
                      <ListItemText primary="Tham gia các hoạt động tập thể sôi động của Công ty : Team Building, Du lịch, dã ngoại" />
                    </ListItem>
                    <ListItem>
                      <ListItemText primary="Đóng đầy đủ BHXH, BHYT, BHTN theo quy định của nhà nước." />
                    </ListItem>
                    <ListItem>
                      <ListItemText primary="Được hưởng các chế độ phúc lợi và các hoạt động văn hóa tập thể phong phú, đa dạng" />
                    </ListItem>
                    <ListItem>
                      <ListItemText primary="Thời gian làm việc: Từ thứ 2 đến chủ nhật, Sáng 8h00-12h00, chiều 13h- 17h00 (Mỗi tháng nghỉ 2 ngày tự chọn)." />
                    </ListItem>
                  </List>
                </Stack>
              </Paper>
            </Grid>
            <Grid item xs={4}>
              <Box mb={2}>
                <ApplicationButton/>
                <FormModal
                  formFields={applicationFields}
                  nameForm="Application job"
                  onFormSubmit={handleFormSubmit}
                />
              </Box>
              <Box mb={2}>
                <Button
                  variant="outlined"
                  color="primary"
                  fullWidth
                  sx={{ fontWeight: "bold" }}
                >
                  Tạo CV để ứng tuyển
                </Button>
              </Box>
              <Paper elevation={3} sx={{ padding: 3 }}>
                <Typography variant="h6" gutterBottom>
                  Thông tin chung
                </Typography>
                <List>
                  <ListItem>
                    <ListItemText
                      primary="Năm kinh nghiệm tối thiểu"
                      secondary="Từ 1 năm"
                    />
                  </ListItem>
                  <ListItem>
                    <ListItemText
                      primary="Cấp bậc"
                      secondary="Junior, Middle, Senior"
                    />
                  </ListItem>
                  <ListItem>
                    <ListItemText primary="Loại hình" secondary="In Office" />
                  </ListItem>
                  <ListItem>
                    <ListItemText
                      primary="Loại hợp đồng"
                      secondary="Fulltime"
                    />
                  </ListItem>
                  <ListItem>
                    <ListItemText
                      primary="Các công nghệ sử dụng"
                      secondary={
                        <Stack direction="row">
                          <Chip label="Android" sx={{ mr: 1, mb: 1 }} />
                          <Chip label="iOS" sx={{ mr: 1, mb: 1 }} />
                          <Chip label="Game Developer" sx={{ mb: 1 }} />
                        </Stack>
                      }
                    />
                  </ListItem>
                  <ListItem>
                    <ListItemText
                      primary="Quy trình phỏng vấn"
                      secondary={
                        <List component="div" disablePadding>
                          <ListItem>
                            <ListItemText primary="Vòng 1: Phỏng vấn qua điện thoại." />
                          </ListItem>
                          <ListItem>
                            <ListItemText primary="Vòng 2: Phỏng vấn trực tiếp với Tech Lead." />
                          </ListItem>
                          <ListItem>
                            <ListItemText primary="Vòng 3: Deal lương." />
                          </ListItem>
                        </List>
                      }
                    />
                  </ListItem>
                </List>
              </Paper>
            </Grid>
          </Grid>
        </Container>
      </Box>
    </ModalProvider>
  );
}

export default DetailCampaign;
