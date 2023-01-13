import React, { useState, useEffect } from 'react'
import { Link } from 'react-router-dom'
import TvShowService from '../services/TvShowService'
import authHeader from '../services/LoginService'

export const TvShowListComponent = () => {

    const [tvShows, setTvShows] = useState([])

    useEffect(() => {
        getAllTvShows();
        

    }, [])

    const getAllTvShows = () =>{
        
        TvShowService.getTvShows().then((response) => {
            setTvShows(response.data.tvShows)
        }).catch(error => {
            console.log(error);
        })
    }
    
    const deleteTvShow = (showId) =>{
        TvShowService.deleteTvShow(showId).then((response) => {
            getAllTvShows();
        }).catch(error => {
            console.log(error);
        })
        
    }

  return (
    <div>
                <h2 className="text-center">Show list</h2>
                <div className='row'>
                {authHeader.getUser() !== null && authHeader.getUser().roles.includes("ROLL_ADMIN")?
                    <Link to={"/add-movie"} className="btn btn-link">
                        Add Movie
                    </Link>
                    : null
                }

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
                                        {authHeader.getUser() !== null && authHeader.getUser().roles.includes("ROLL_ADMIN") ?
                                        <>
                                        <Link className="btn btn-info" to={`/update-tvshow/${tvShow.id}`} >Update</Link>
                                        <button className='btn btn-danger' onClick={() => deleteTvShow(tvShow.id)} style = {{marginLeft:"10px"}}>Delete</button>
                                        </>
                                        :null}
                                        {authHeader.getUser() !== null ?
                                        <Link
                                     className="btn btn-info"
                                     to={`/watchlist-add/TvShow/${tvShow.id}`
                                }
                                style={{ marginLeft: "10px" }}
                                 >
                                     Add to Watchlist
                                 </Link>
                                 :<></>}
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