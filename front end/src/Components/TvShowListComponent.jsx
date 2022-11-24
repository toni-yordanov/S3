import React, { useState, useEffect } from 'react'
import { Link } from 'react-router-dom'
import TvShowService from '../services/TvShowService'
import MovieService from '../services/TvShowService'

export const TvShowListComponent = () => {

    const [tvShows, setTvShows] = useState([])

    useEffect(() => {
      
        TvShowService.getTvShows().then((response) => {
            setTvShows(response.data.tvShows)
        }).catch(error => {
            console.log(error);
        })

    }, [])
    
    const deleteTvShow = (showId) =>{
        TvShowService.deleteMovie(showId).then((response) => {

        }).catch(error => {
            console.log(error);
        })
    }

  return (
    <div>
                <h2 className="text-center">Movie list</h2>
                <div className='row'>
                    <Link to={"/add-tvshow"} className='btn btn-link'>Add Show</Link>
                </div>
                <div className="row">
                    <table className="table table-striped table-bordered">
                        <thead>
                            <tr>
                                <th>Name</th>
                                <th>Description</th>
                                <th>Episodes</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                
                                tvShows.map(
                                    tvShow => 
                                    <tr key={tvShow.id}> 
                                        <td>{tvShow.name}</td>
                                        <td>{tvShow.description}</td>
                                        <td>{tvShow.episodes}</td>
                                        <td>
                                        <Link className="btn btn-info" to={`/update-tvshow/${movie.id}`} >Update</Link>
                                        <button className='btn btn-danger' onClick={() => deleteTvShow(tvShow.id)}>Delete</button>
                                        </td>
                                    </tr>
                            )}
                        </tbody>
                    </table>
                </div>
            </div>
  )
}

export default TvShowListComponent