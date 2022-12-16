import React, { useState } from 'react';
import LoginService from '../services/LoginService';
import { useNavigate } from "react-router";

export const LoginComponent = () => {
  const [msg, setMsg] = React.useState(null);
  const navigate = useNavigate();
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  
  const handleSubmit = e =>{
    e.preventDefault();
    LoginService.login(username, password)
        .then((response) => response.json())
        .then((responseData) => {
            localStorage.setItem("user", JSON.stringify(responseData));
            navigate("/index");
        })
        .catch(err=>{setMsg("Detail mismatch");})
}


  return (

    <form method="post" id="login-form" onSubmit={handleSubmit}>
    {/* <center><LockOutlinedIcon/></center> */}
    <h2><center>Sign In</center></h2>
    <div className="form-group">
        <label>Email address</label>
        <input type="email" className="form-control" placeholder="Enter email" onChange={e => setUsername(e.target.value)} required/>
    </div>

    <div className="form-group">
        <label>Password</label>
        <input type="password" className="form-control" placeholder="Enter password" onChange={e => setPassword(e.target.value)} required/>
    </div>

    <br></br>
    <div className="form-group"><button type="submit" className="btn btn-primary btn-block">Submit</button></div>
    <br></br>

    <h1>{msg}</h1>
</form>

);
    
}

export default LoginComponent