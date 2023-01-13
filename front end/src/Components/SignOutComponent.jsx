import LoginService from '../services/LoginService';
import { useEffect } from 'react';

export const SignOutComponent = () => {
  
  useEffect(() => {
    LoginService.logout();
    window.location.href = '/';
  }, []);

  return <div>Signing out...</div>;
};

export default SignOutComponent;
