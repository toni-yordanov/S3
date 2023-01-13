import React, { useState, useEffect } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import WatchlistService from '../services/WatchlistService';

const UpdateWatchlist =() => {

const {id} = useParams();

    
const [watchlist, setWatchlist] = useState({completionDate:""});

    useEffect(() => {
        getWatchlist();
    }, []);
    
    const getWatchlist = () => {
        WatchlistService.getWatchlistById(id)
            .then((response) => {
                let separateDate = [];
                let watchlistData = response.data;
                if(watchlistData.completionDate !== null){
                    separateDate = (""+ new Date(watchlistData.completionDate).toLocaleDateString()).toString().replaceAll('/','-').split('-');
                    if(separateDate[0].length < 2){
                        separateDate[0] = "0"+ separateDate[0]
                    }
                    if(separateDate[1].length < 2){
                        separateDate[1] = "0"+ separateDate[1]
                    }
                    watchlistData.completionDate = separateDate[2] + "-" + separateDate[0] +"-" + separateDate[1];
                }else{
                    watchlistData.completionDate = "";
                }
                setWatchlist(watchlistData);
            })
            .catch((error) => {
                console.log(error);
            });
    }

    

  const history = useNavigate();

  
  
  const handleStatusChange = (event) => {
    setWatchlist({...watchlist,"status": event.target.value});
  };

  const handleCompletionDateChange = (event) => {
    let completionDate = event.target.value;
    
    setWatchlist({...watchlist,"completionDate": completionDate});
  };


  


  const goBack = () => {
    
      history("/watchlist")
    
  }


  const handleSubmit = (event) => {
    event.preventDefault();
    WatchlistService.saveWatchlist(watchlist)
      .then(() => {
        goBack();
      })
      .catch((error) => {
        console.error(error);
      });
      goBack();
  };

  return (


    <form onSubmit={handleSubmit}>
      {/* <p>Type: {showType} id: {showId}</p> */}
      <label htmlFor="status">Status:</label>
      <select id="status" value={watchlist.status} onChange={handleStatusChange}>
        <option value="Watching">Watching</option>
        <option value="Completed">Completed</option>
        <option value="Dropped">Dropped</option>
      </select>
      <br />
      <label htmlFor="completionDate">Completion Date:</label>
      <input
        type="date"
        id="completionDate"
        required pattern="(\d{1}|\d{2})-(\d{2}|\d{1})-\d{4}" 
        value={watchlist.completionDate}
        onChange={handleCompletionDateChange}
      />

      <br />
      <input type="submit" className='btn btn-primary' value="Update" />
      <button type="button" className='btn btn-danger' onClick={goBack}>
        Cancel
      </button>
    </form>
  )
  }
  export default UpdateWatchlist;
