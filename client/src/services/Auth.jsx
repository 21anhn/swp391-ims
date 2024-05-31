import React from 'react';
import { useAuth } from './AuthContext';
import { Navigate } from 'react-router-dom';

const Auth = ({ children, requiredRole }) => {
  const { user } = useAuth();

  if (!user) {
    return <Navigate to="/login" />;
  }

  if (requiredRole && user.data.roleName !== requiredRole) {
    return <Navigate to="/" />;
  }

  return children;
};

export default Auth;