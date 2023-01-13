import React, { useState } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import WatchlistService from '../services/WatchlistService';

const CreateWatchlist =() => {
  const [status, setStatus] = useState('Watching');
  const [completionDate, setCompletionDate] = useState('');

  const history = useNavigate();

  const {showType, showId} = useParams();
  
  const handleStatusChange = (event) => {
    setStatus(event.target.value);
  };

  const handleCompletionDateChange = (event) => {
    setCompletionDate(event.target.value);
  };

  const goBack = () => {
    if(showType === "Movie"){
      history("/movies")
    }
    if(showType === "TvShow"){
      history('/tvShows')
    }
  }


  const handleSubmit = (event) => {
    event.preventDefault();
    const newWatchlist = {
      mediaId: showId,
      type: showType,
      status: status,
      completionDate: completionDate,
    };
    WatchlistService.saveWatchlist(newWatchlist)
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
      <p>Type: {showType} id: {showId}</p>
      <label htmlFor="status">Status:</label>
      <select id="status" value={status} onChange={handleStatusChange}>
        <option value="Watching">Watching</option>
        <option value="Completed">Completed</option>
        <option value="Dropped">Dropped</option>
      </select>
      <br />
      <label htmlFor="completionDate">Completion Date:</label>
      <input
        type="date"
        id="completionDate"
        value={completionDate}
        onChange={handleCompletionDateChange}
      />
      <br />
      <input type="submit" className='btn btn-primary' value="Save" />
      <button type="button" className='btn btn-danger' onClick={goBack}>
        Cancel
      </button>
    </form>
  )
  }
  export default CreateWatchlist;
