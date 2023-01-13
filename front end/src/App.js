
import './App.css';
import {BrowserRouter as Router, Route, Routes} from 'react-router-dom'
import FooterComponent from './Components/FooterComponent';
import HeaderComponent from './Components/HeaderComponent';
import ListMovieComponent from './Components/MovieListComponent';
import CreateMovieComponent from './Components/CreateMovieComponent';
import TvShowListComponent from './Components/TvShowListComponent';
import CreateTvShowComponent from './Components/CreateTvShowComponent';
import LoginComponent from './Components/LoginComponent';
import SignOutComponent from './Components/SignOutComponent';
import CreateWatchlist from './Components/CreateWatchlistElementComponent';
import UserPage from './Components/UserPageComponent';
import ChatRoom from './Components/ChatRoom';
import RegisterPage from './Components/RegisterComponent';
import UpdateWatchlist from './Components/UpdateWatchlistComponent';
import WatchlistComponent from './Components/WatchlistComponent';


function App() {
  return (

<div>
    <Router>
        

            <HeaderComponent/>

            <div className="container">
                <Routes> 
                   
                    <Route path="/movies" element={< ListMovieComponent />} />
                    <Route path="/add-movie" element={< CreateMovieComponent />} />
                    <Route path="/update-movie/:id" element={< CreateMovieComponent />} />
                    <Route path="/tvShows" element={< TvShowListComponent />} />
                    <Route path="/add-tvshow" element={< CreateTvShowComponent />} />
                    <Route path="/update-tvshow/:id" element={< CreateTvShowComponent />} />
                    <Route path="/login" element={< LoginComponent />} />
                    <Route path="/sign-out" element={< SignOutComponent />} />
                    <Route path="/index" element={this}/>
                    <Route path="/watchlist-add/:showType/:showId" element={<CreateWatchlist/>}/>
                    <Route path="/update-watchlist-item/:id" element={<UpdateWatchlist/>}/>
                    <Route path="/my-profile" element={<UserPage/>}/>
                    <Route path='/chat' element={<ChatRoom/>}/>
                    <Route path="/register" element={<RegisterPage/>}/>
                    <Route path="/watchlist" element={<WatchlistComponent/>}/>
                </Routes>
            </div>
            <FooterComponent/>
        
    </Router>
</div>
    
    
  );
}

export default App;
