USE [SWP391_IMS]

INSERT INTO roles(role_name) VALUES ('ROLE_ADMIN'),
									('ROLE_HR_MANAGER'),
									('ROLE_INTERN'),
									('ROLE_MENTOR'),
									('ROLE_ICOORDINATOR')
									
INSERT INTO users(username, password, role_id) VALUES ('nguyenvana', '12345', 1),
													  ('nguyenvanb', '12345', 2),
													  ('nguyenvanc', '12345', 3),
													  ('nguyenvand', '12345', 4),
													  ('nguyenvane', '12345', 5)