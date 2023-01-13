import axios from 'axios';
import authHeader from './AuthenticationHeader';


const API_URL = "http://localhost:8080/";

class LoginService {

    login(email, password) {
        var reqBody = "email="+email+"&password="+password+"&grant_type=password";
        return fetch(API_URL + "login", {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
            },
            body: reqBody
        })
    }

logout() {
    sessionStorage.removeItem('user');
}

register(name,email,password){
    const User = {
        name: name,
        email: email,
        password: password,
      }
    return axios.post(API_URL + "users/register", User);
}

addRole(email){
    return axios.post(API_URL + "users/role/addtouser", email);
}

getUser() {
    return JSON.parse(sessionStorage.getItem('user'));;
}

getUserInfo(){
    return axios.get(API_URL + 'users/user' ,{headers : authHeader()})
}

checkToken(){
    function check(){
        axios.get(API_URL + "check",{headers: authHeader()}).catch(() => {window.history.pushState({}, '', "/sign-out");window.location.reload();});
    }
    check();
}

checkTokenAdmin(){
    function check(){
        return axios.get(API_URL + "admin",{headers: authHeader()}).catch(() => {window.history.pushState({}, '', "/sign-out");window.location.reload();});
    }
    return check();
}


}    export default new LoginService();