import React, { useState } from 'react'
import {useNavigate, Link, useParams} from 'react-router-dom'
import MovieService from '../services/MovieService'

export const CreateMovieComponent = () => {

    const [name ,setName ]= useState('')
    const [runtime ,setRuntime ]= useState(0)
    const [description ,setDescription ]= useState('')
    const history = useNavigate();
    const {id} = useParams();


    console.log(id)

    const saveOeUpdateMovie = (e) =>{
        e.preventDefault();

        const movie = {name, description, runtime}

        if(id){
            MovieService.updateMovie(movie, id).then((response) =>{

                console.log(response.data)
    
                history('/movies')
    
            }).catch(error =>{
                console.log(error)
            })

        }else{
            MovieService.createMovie(movie).then((response) =>{

                console.log(response.data)
    
                history('/movies')
    
            }).catch(error =>{
                console.log(error)
            })
        }
       
    }

    useState(() => {
        
        MovieService.getMovieById(id).then((response) => {
            setName(response.data.name)
            setDescription(response.data.description)
            setRuntime(response.data.runtime)
        }).catch(error => {
            console.log(error);
        })
    }, [])
    

    const title = () => {
        if(id){
            return <h2 className='text-center'>Update Movie</h2>
        }
        else{
            return <h2 className='text-center'>Add Movie</h2>
        }
    }
  return (
    <div>
        <br/><br/><br/>
        <div className='container'>
            <div className='row'>
                <div className='card col-md-6 offset-md-3 offset-md-3'>
                    {
                        title()
                    }
                    <div className='card-body'>
                        <form>
                            <div className='form-group mb-2'>
                                <label className='form-lable'>Name:</label>
                                <input
                                type='text'
                                placeholder='Enter movie name'
                                name='name'
                                className='form-controll'
                                value={name}
                                onChange = {(e) => setName(e.target.value)}
                                ></input>
                            </div>
                            <div className='form-group mb-2'>
                                <label className='form-lable'>Description:</label>
                                <input
                                type='textarea'
                                placeholder='Enter movie description'
                                name='description'
                                className='form-controll'
                                value={description}
                                onChange = {(e) => setDescription(e.target.value)}
                                ></input>
                            </div>
                            <div className='form-group mb-2'>
                                <label className='form-lable'>Runtime:</label>
                                <input
                                type='number'
                                placeholder='0'
                                name='runtime'
                                className='form-controll'
                                value={runtime}
                                onChange = {(e) => setRuntime(e.target.value)}
                                ></input>
                            </div>

                            <button className='btn btn-success' onClick={(e) => saveOeUpdateMovie(e)}>Submit</button>
                            <Link to='/movies' className="btn btn-danger">Cancel</Link>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
  )
}

export default CreateMovieComponent