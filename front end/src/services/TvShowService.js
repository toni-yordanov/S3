import axios from "axios";

const TV_SHOW_URL = "http://localhost:8080/tvshows";
class TvShowService{
    
    getTvShows(){
        return axios.get(TV_SHOW_URL);
    }
}

export default new TvShowService()