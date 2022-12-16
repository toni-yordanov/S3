import React,{ Fragment } from 'react';
import { Link } from 'react-router-dom'
import authHeader from '../services/LoginService';
import {  Nav, NavItem } from 'react-bootstrap';


const HeaderComponent = () => {

    // Check if the user is logged in and has the "ROLE_ADMIN" role
    var isLoggedIn = 0;
    if (authHeader.getUser() !== null && authHeader.getUser().roles.includes("ROLL_ADMIN")) {
        isLoggedIn = 1;
        console.log(isLoggedIn);
    } else if (authHeader.getUser() !== null) {
        isLoggedIn = 2;
        console.log(isLoggedIn);
        console.log(authHeader.getUser().roles);
    } 

    // Create the menu based on the user's login status
    let menu = "";
    if(isLoggedIn === 0){
        menu = (
            <Fragment>
                <NavItem>
                    <Link className="btn btn-link" to={"/login"}>Login</Link>
                </NavItem>
                <NavItem>
                    <Link className="btn btn-link" to={"/register"}>Sign Up</Link>
                </NavItem>
            </Fragment>
        )
    }else if(isLoggedIn === 1) {
        menu = (
            <Fragment>
                <NavItem>
                    <Link className="btn btn-link" to={"/add-movie"}>Add movie</Link>
                </NavItem>
                <NavItem>
                    <Link className="btn btn-link" to={"/add-tvshow"}>Add movie</Link>
                </NavItem>
                <NavItem>
                    <Link className="btn btn-link tect-right" to={"/sign-out"}>Sign Out</Link>
                </NavItem>
            </Fragment>
        )
    }else{
        menu = (
            <Fragment>
                <NavItem>
                    <Link className="btn btn-link" to={"/my-profile"}>My Profile</Link>
                </NavItem>
                <NavItem className='text-right'>
                    <Link className="btn btn-link " to={"/sign-out"}>Sign Out</Link>
                </NavItem>
            </Fragment>
        )
    }

    return (
        <div>
            <header >
                <nav className='navbar navbar-expand-md navbar-dark bg-dark'>
                    <div><a href='/' className='navbar-brand'>Watch tracker</a></div>
                    <div>
                      <Nav>
                        <NavItem>
                          <Link className="btn btn-link" to={`/movies`} >Movies</Link>
                          </NavItem>
                        <NavItem>
                          <Link className="btn btn-link" to={`/tvshows`} >Tv Shows</Link>
                        </NavItem>
                            {menu}
                        </Nav>
                    </div>
                </nav>
            </header>
        </div>
    );
};

export default HeaderComponent;
