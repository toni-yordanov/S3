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
    localStorage.removeItem('user');
}

register(email,firstName,lastName,dateOfBirth,gender,phoneNumber,password){
    return axios.post(API_URL + "register", {
        email,
        firstName,
        lastName,
        dateOfBirth,
        gender,
        phoneNumber,
        password
    });
}

getUser() {
    return JSON.parse(localStorage.getItem('user'));;
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