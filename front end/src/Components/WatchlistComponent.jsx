import React, { useState, useEffect } from 'react'
import { Link } from 'react-router-dom'
import WatchlistService from '../services/WatchlistService'
import TvShowService from '../services/TvShowService'
import MovieService from '../services/MovieService'

export const WatchlistComponent = () => {
    const [watchlist, setWatchlist] = useState([])
    const [itemName, setItemName] = useState(new Map());


    useEffect(() => {
        getWatchlist();
        watchlist.forEach(item => {
            getItemName(item.mediaId, item.type);
            
        });
    }, [watchlist])
    
    

    const getWatchlist = () => {
        WatchlistService.getWatchlistForUser().then((response) => {
            setWatchlist(response.data)
            
        }).catch(error => {
            console.log(error);
        })
    }

    const deleteFromWatchlist = (itemId) => {
        WatchlistService.deleteFromWatchlist(itemId).then((response) => {
            getWatchlist();
        }).catch(error => {
            console.log(error);
        })
    }

    const getItemName = async (id, type) => {
        let name = '';
        if (type === 'TvShow') {
            const response = await TvShowService.getTvShowById(id);
            name = response.data.name;
        } else if (type === 'Movie') {
            const response = await MovieService.getMovieById(id);
            name = response.data.name;
        } else {
            console.log("Invalid item type");
        }
        itemName[`${id}-${type}`] = name;
        setItemName(itemName);
    }
    
    
    
      
    return (
        <div>
            <h2 className="text-center">My Watchlist</h2>
            <div className="row">
                <table className="table table-striped table-bordered">
                    <thead>
                        <tr>
                            <th>Name</th>
                            <th>Status</th>
                            <th>Completion date</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        {watchlist.map(item =>
                            <tr key={item.id}>
                                <td>{itemName[`${item.mediaId}-${item.type}`]}</td>


                                <td>{item.status}</td>
                                <td>{item.completionDate}</td>
                                <td>

                                    <Link className="btn btn-info" to={`/update-watchlist-item/${item.id}`}>Update</Link>
                                    <button className="btn btn-danger" onClick={() => deleteFromWatchlist(item.id)} style={{ marginLeft: "10px" }}>Delete</button>

                                </td>

                            </tr>
                        )}
                    </tbody>
                </table>
            </div>
        </div>
    )
}

export default WatchlistComponent
