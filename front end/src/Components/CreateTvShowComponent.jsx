import React, { useState } from 'react'
import {useNavigate, Link, useParams} from 'react-router-dom'
import TvShowService from '../services/TvShowService'

export const CreateTvShowComponent = () => {

    const [name ,setName ]= useState('')
    const [episodes ,setEpisodes ]= useState(0)
    const [description ,setDescription ]= useState('')
    const history = useNavigate();
    const {id} = useParams();


    console.log(id)

    const saveOrUpdateTvShow = (e) =>{
        e.preventDefault();

        const tvShow = {name, description, episodes}

        if(id){
            TvShowService.updateTvShow(tvShow, id).then((response) =>{

                console.log(response.data)
    
                history('/tvShows')
    
            }).catch(error =>{
                console.log(error)
            })

        }else{
            TvShowService.createTvShow(tvShow).then((response) =>{

                console.log(response.data)
    
                history('/tvShows')
    
            }).catch(error =>{
                console.log(error)
            })
        }
       
    }

    useState(() => {
        
        TvShowService.getTvShowById(id).then((response) => {
            setName(response.data.name)
            setDescription(response.data.description)
            setEpisodes(response.data.episodes)
        }).catch(error => {
            console.log(error);
        })
    }, [])
    

    const title = () => {
        if(id){
            return <h2 className='text-center'>Update Show</h2>
        }
        else{
            return <h2 className='text-center'>Add Show</h2>
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
                                placeholder='Enter Show name'
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
                                placeholder='Enter Show description'
                                name='description'
                                className='form-controll'
                                value={description}
                                onChange = {(e) => setDescription(e.target.value)}
                                ></input>
                            </div>
                            <div className='form-group mb-2'>
                                <label className='form-lable'>episodes:</label>
                                <input
                                type='number'
                                placeholder='0'
                                name='episodes'
                                className='form-controll'
                                value={episodes}
                                onChange = {(e) => setEpisodes(e.target.value)}
                                ></input>
                            </div>

                            <button className='btn btn-success' onClick={(e) => saveOrUpdateTvShow(e)}>Submit</button>
                            <Link to='/TvShows' className="btn btn-danger">Cancel</Link>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
  )
}

export default CreateTvShowComponent