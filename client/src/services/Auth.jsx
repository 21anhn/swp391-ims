import React from 'react';
import { useAuth } from '../context/AuthContext';
import { Navigate } from 'react-router-dom';

const Auth = ({ children, requiredRole }) => {
  const { user, loading } = useAuth();
  
  // Check if the user is loaded from localStorage
  const storedUser = localStorage.getItem('user');
  const parsedUser = storedUser ? JSON.parse(storedUser) : null;

  if (loading) {
    <Navigate to="/login" />;
  }

  // Check if user is not authenticated
  if (!user && !parsedUser) {
    return <Navigate to="/login" />;
  }

  const currentUser = user || parsedUser;

  // Check if the user does not have the required role
  if (requiredRole && currentUser.data.roleName !== requiredRole) {
    return <Navigate to="/" />;
  }

  return children;
};

export default Auth;