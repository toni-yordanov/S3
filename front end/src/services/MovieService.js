import axios from "axios";

const MOVIE_URL = "http://localhost:8080/movies";
class MovieService{
    
    getMovies(){
        return axios.get(MOVIE_URL);
    }
}

export default new MovieService()