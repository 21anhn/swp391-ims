CREATE DATABASE SWP391_IMS
USE [SWP391_IMS]

CREATE TABLE roles(
		role_id INT PRIMARY KEY IDENTITY(1, 1),
		role_name VARCHAR(20))

CREATE TABLE users(
		id INT PRIMARY KEY IDENTITY(1, 1),
		username VARCHAR(40) UNIQUE,
		password VARCHAR(40),
		full_name NVARCHAR(255),
		dob DATE,
		gender NVARCHAR(20),
		email VARCHAR(40), 
		phone_number VARCHAR(10),
		address NVARCHAR(255),
		role_id INT REFERENCES roles(role_id))

CREATE TABLE internship_campaigns(
		campaign_id INT PRIMARY KEY IDENTITY(1, 1),
		campaign_name NVARCHAR(255),
		job_description NVARCHAR(255), 
		requirements NVARCHAR(255),
		posted_date DATETIME,
		deadline DATETIME,
		hr_id INT REFERENCES users(id)) -- HR đăng thông tin

CREATE TABLE applications( -- Hồ sơ nộp ứng tuyển từ internship campaign
		application_id INT PRIMARY KEY IDENTITY(1, 1),
		application_date DATETIME,
		status VARCHAR(20), -- Tình trạng hồ sơ (Duyệt/xem xét)
		intern_id INT REFERENCES users(id), -- Khi được duyệt thì trở thành intern
		campaign_id INT REFERENCES internship_campaigns(campaign_id)) -- Guest ứng tuyển chiến dịch thực tập nào 

CREATE TABLE educational_backgrounds( -- Thông tin học vấn của user,
		education_id INT PRIMARY KEY IDENTITY(1, 1),
		university_name NVARCHAR(255),
		major NVARCHAR(255),
		degree NVARCHAR(255),
		gpa FLOAT,
		graduation_year VARCHAR(4),
		user_id INT REFERENCES users(id))

CREATE TABLE work_histories( -- Thông tin lịch sử làm việc
		work_history_id INT PRIMARY KEY IDENTITY(1, 1),
		 company_name NVARCHAR(255),
		 position NVARCHAR(40),
		 start_date DATE,
		 end_date DATE,
		 job_description NVARCHAR(255),
		 user_id INT REFERENCES users(id))

CREATE TABLE schedules( -- Lịch phỏng vấn
		schedule_id INT PRIMARY KEY IDENTITY(1, 1),
		interview_date DATETIME,
		interview_location NVARCHAR(255),
		status VARCHAR(20), -- Lịch phỏng vấn còn khả dụng/không
		coordinator_id INT REFERENCES users(id)) -- Ai là người tạo, gửi lịch phỏng vấn

CREATE TABLE training_programs( -- Chương trình học
		program_id INT PRIMARY KEY IDENTITY(1, 1),
		program_name NVARCHAR(255),
		description NVARCHAR(255),
		objectives NVARCHAR(255),
		coordinator_id INT REFERENCES users(id)) -- Người thiết kế chương trình học này

-- Những chương trình học mà intern tham gia
-- 1 intern có thể tham gia N chương trình học, 
-- 1 chương trình học có thế có N intern tham gia
CREATE TABLE training_program_intern( 
		id INT PRIMARY KEY IDENTITY(1, 1),
		intern_id INT REFERENCES users(id),
		program_id INT REFERENCES training_programs(program_id),
		feedback NVARCHAR(255) -- Feedback của intern về chương trình học
)

CREATE TABLE skills(
		skill_id INT PRIMARY KEY IDENTITY(1, 1),
		skill_name NVARCHAR(255))

-- Sau khi qua khóa training program thì intern sẽ đạt được nhiều skill
CREATE TABLE training_program_skill( 
		id INT PRIMARY KEY IDENTITY(1, 1),
		skill_id INT REFERENCES skills(skill_id),
		program_id INT REFERENCES training_programs(program_id))

-- 
CREATE TABLE tasks(
		task_id INT PRIMARY KEY IDENTITY(1, 1),
		task_name NVARCHAR(255),
		task_description NVARCHAR(255),
		start_time DATETIME,
		end_time DATETIME,
		mentor_id INT REFERENCES users(id)) -- Mentor giao task

CREATE TABLE assessments( -- Đánh giá của gv đối với intern sau mỗi task hoàn thành
		assessment_id INT PRIMARY KEY IDENTITY(1, 1),
		assessment_date DATETIME,
		score FLOAT,
		feedback NVARCHAR(255),
		intern_id INT REFERENCES users(id),
		task_id INT REFERENCES tasks(task_id))

CREATE TABLE educational_resources(
		resouce_id INT PRIMARY KEY IDENTITY(1, 1),
		resource_name NVARCHAR(255),
		description NVARCHAR(255),
		created_date DATE)

-- 1 Program training có N resources
-- 1 resources có thể có trong N Program training
CREATE TABLE program_training_resources(
		id INT PRIMARY KEY IDENTITY(1, 1),
		resouce_id INT REFERENCES educational_resources(resouce_id),
		program_id INT REFERENCES training_programs(program_id))

-- Lưu hội thoại giữa mentor, intern
CREATE TABLE messages (
    message_id INT PRIMARY KEY IDENTITY(1, 1),
    sender_id INT REFERENCES users(id),
    receiver_id INT REFERENCES users(id),
    message_content NVARCHAR(255),
    sent_date DATETIME
)