import React, { useState, useEffect } from 'react'
import { Link } from 'react-router-dom'
import MovieService from '../services/MovieService'

export const MovieListComponent = () => {

    const [movies, setMovies] = useState([])

    useEffect(() => {
      
        getAllMovies();

    }, [])
    
    const getAllMovies = () => {
        MovieService.getMovies().then((response) => {
            setMovies(response.data.movies)
        }).catch(error => {
            console.log(error);
        })
    } 
    const deleteMovie = (movieId) =>{
        MovieService.deleteMovie(movieId).then((response) => {
            getAllMovies()
        }).catch(error => {
            console.log(error);
        })
    }

  return (
    <div>
                <h2 className="text-center">Movie list</h2>
                <div className='row'>
                    <Link to={"/add-movie"} className='btn btn-link'>Add Movie</Link>
                </div>
                <div className="row">
                    <table className="table table-striped table-bordered">
                        <thead>
                            <tr>
                                <th>Name</th>
                                <th>Description</th>
                                <th>Runtime</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                
                                movies.map(
                                    movie => 
                                    <tr key={movie.id}> 
                                        <td>{movie.name}</td>
                                        <td>{movie.description}</td>
                                        <td>{movie.runtime}</td>
                                        <td>
                                        <Link className="btn btn-info" to={`/update-movie/${movie.id}`} >Update</Link>
                                        <button className='btn btn-danger' onClick={() => deleteMovie(movie.id)} style = {{marginLeft:"10px"}}>Delete</button>
                                        </td>
                                    </tr>
                            )}
                        </tbody>
                    </table>
                </div>
            </div>
  )
}

export default MovieListComponent