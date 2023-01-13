import axios from "axios";
import authHeader from "./AuthenticationHeader";

const MOVIE_URL = "http://localhost:8080/movies";
class MovieService{
    
    getMovies(){
        return axios.get(MOVIE_URL);
    }
    createMovie(movie){
        return axios.post(MOVIE_URL, movie,{headers: authHeader()});
    }

    getMovieById(movieId){
        return axios.get(MOVIE_URL + '/' + movieId);
    }

    updateMovie(movie, movieId){
        return axios.put(MOVIE_URL + '/' + movieId, movie,{headers: authHeader()});
    }

    deleteMovie(movieId){
        return axios.delete(MOVIE_URL + '/' + movieId,{headers: authHeader()});
    }

}

export default new MovieService()