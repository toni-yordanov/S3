
import './App.css';
import {BrowserRouter as Router, Route, Routes} from 'react-router-dom'
import FooterComponent from './Components/FooterComponent';
import HeaderComponent from './Components/HeaderComponent';
import ListMovieComponent from './Components/MovieListComponent';
import CreateMovieComponent from './Components/CreateMovieComponent';

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
                    
                </Routes>
            </div>
            <FooterComponent/>
        
    </Router>
</div>
    
    
  );
}

export default App;
