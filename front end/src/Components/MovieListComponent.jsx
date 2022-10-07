import React, { Component } from 'react';
import MovieService from '../services/MovieService';

class ListMovieComponent extends Component {
    constructor(props){
        super(props)

        this.state = {
            movies: []
        }
    }

    componentDidMount(){
        MovieService.getMovies().then((res) => {
            this.setState({ movies: res.data.movies});
        });
    }

    

    render() 
    {
        return(
            <div>
                <h2 className="text-center">Movie list</h2>
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
                                
                                this.state.movies.map(
                                    movie => 
                                    <tr key={movie.id}> 
                                        <td>{movie.name}</td>
                                        <td>{movie.description}</td>
                                        <td>{movie.runtime}</td>
                                    </tr>
                            )}
                        </tbody>
                    </table>
                </div>
            </div>
        )
    }
}


export default ListMovieComponent;
