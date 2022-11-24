import axios from "axios";

const TV_SHOW_URL = "http://localhost:8080/tvshows";
class TvShowService{
    
    getTvShows(){
        return axios.get(TV_SHOW_URL);
    }
    
    createTvShow(tvShow){
        return axios.post(TV_SHOW_URL, tvShow);
    }

    getTvShowById(tvShowId){
        return axios.get(TV_SHOW_URL + '/' + tvShowId);
    }

    updateTvShow(tvShow, tvShowId){
        return axios.put(TV_SHOW_URL + '/' + tvShowId, tvShow);
    }

    deleteTvShow(tvShowId){
        return axios.delete(TV_SHOW_URL + '/' + tvShowId);
    }
}

export default new TvShowService()