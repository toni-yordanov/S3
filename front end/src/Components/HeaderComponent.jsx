import React,{ Fragment, useState, useEffect } from 'react';
import { Link } from 'react-router-dom'
import authHeader from '../services/LoginService';
import {  Nav, NavItem } from 'react-bootstrap';


const HeaderComponent = () => {

    // Check if the user is logged in and has the "ROLE_ADMIN" role
    const [isLoggedIn, setIsLoggedIn] = useState(false);

    useEffect(() => {
        if (authHeader.getUser() !== null && authHeader.getUser().roles.includes("ROLL_ADMIN")) {
            setIsLoggedIn(1);
        } else if (authHeader.getUser() !== null) {
            setIsLoggedIn(2);
        } else {
            setIsLoggedIn(0);
        }
    }, [authHeader.getUser()]);

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
                    <Link className="btn btn-link" to={"/add-tvshow"}>Add show</Link>
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
