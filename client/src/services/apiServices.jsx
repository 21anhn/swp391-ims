const API_LOGIN_URL = 'http://localhost:8080/api/login';
const API_CREATE_ACCOUNT_URL = 'http://localhost:8080/api/admin';
const API_GETALL_ACCOUNT_URL = 'http://localhost:8080/api/admin';
const API_CREATE_POST_URL = '';
const API_GETALL_POST_URL = '';


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