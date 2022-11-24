import React from 'react';
import { Link } from 'react-router-dom'

const HeaderComponent = () => {
    return (
        <div>
            <header >
                <nav className='navbar navbar-expand-md navbar-dark bg-dark'>
                    <div><a href='/' className='navbar-brand'>Watch Tracker</a></div>
                    <div>
                    <Link className="btn btn-link" to={`/movies`} >Movies</Link>
                    <Link className="btn btn-link" to={`/tvshows`} >Movies</Link>
                    </div>
                </nav>
            </header>
        </div>
    );
};

export default HeaderComponent;