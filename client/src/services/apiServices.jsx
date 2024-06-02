const API_LOGIN_URL = 'http://localhost:8080/api/login';
const API_CREATE_ACCOUNT_URL = 'http://localhost:8080/api/admin';
const API_GETALL_ACCOUNT_URL = 'http://localhost:8080/api/admin';
const API_CREATE_POST_URL = 'http://localhost:8080/api/hr';
const API_GETALL_POST_URL = 'https://664ea233fafad45dfae0a20e.mockapi.io/api/login/schema';
const API_GET_POST_DETAIL = 'http://localhost:8080/api/hr/details';

export const login = async (username, password) => {
  const response = await fetch(`${API_LOGIN_URL}`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify({ username, password }),
  });

  if (!response.ok) {
    throw new Error('Login failed');
  }

  return response.json();
};

export const createAccount = async (accountData) => {
  const response = await fetch(`${API_CREATE_ACCOUNT_URL}`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(accountData),
  });

  if (!response.ok) {
    throw new Error('Failed to create account');
  }

  return response.json();
};

export const fetchAccounts = async () => {
  const response = await fetch(`${API_GETALL_ACCOUNT_URL}`);

  if (!response.ok) {
    throw new Error('Failed to fetch accounts');
  }

  return response.json();
};

export const createPost = async (postData) => {
  const response = await fetch(`${API_CREATE_POST_URL}`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(postData),
  });

  if (!response.ok) {
    throw new Error('Failed to create post');
  }

  return response.json();
};

export const fetchPosts = async () => {
  const response = await fetch(`${API_GETALL_POST_URL}`);

  if (!response.ok) {
    throw new Error('Failed to fetch posts');
  }

  return response.json();
};

export const fetchPostDetail = async (postId) => {

  const response = await fetch(`${API_GET_POST_DETAIL}?id=${postId}`, {
    method: 'GET',
    headers: {
      'Content-Type': 'application/json',
    },
  });
  console.log(response);
  if (!response.ok) { 
    throw new Error('Failed to fetch post details');
  }

  return response.json();
};

export const createApplication = async (applicationData) => {
  const response = await fetch(`${API_CREATE_ACCOUNT_URL}`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(applicationData),
  });

  if (!response.ok) {
    throw new Error('Failed to create application');
  }

  return response.json();
};