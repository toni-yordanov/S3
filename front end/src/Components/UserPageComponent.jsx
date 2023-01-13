import React, { useState, useEffect } from 'react';
import 'chart.js/auto';
import UserService from '../services/LoginService';
import WatchlistService from '../services/WatchlistService';
import { Doughnut } from 'react-chartjs-2'

const UserPage = () => {
    const [user, setUser] = useState({});
    const [watchlist, setWatchlist] = useState([])
    const [doughnut, setDoughnut] = useState({
        labels: ["Completed", "Watching", "Dropped"],
        datasets: [{
            data: [0, 0, 0],
            backgroundColor: [
                "#00FF00",//green
                "#0000FF",//blue
                "#FF0000",//red
            ]
        }]
    });
    
    useEffect(() => {
        UserService.getUserInfo().then((response) => {
            setUser(response.data);
            console.log(response.data)
        });
        WatchlistService.getWatchlistForUser().then((response) => {
            setWatchlist(response.data)
           
        }).catch(error => {
            console.log(error);
        })

        WatchlistService.getWatchlistForUser().then((response) => {
            setWatchlist(response.data)
            setDoughnut({
                labels: ["Completed", "Watching", "Dropped"],
                datasets: [{
                    data: getWatchlistStatusCount(response.data),
                    backgroundColor: [
                        "#00FF00",//green
                        "#0000FF",//blue
                        "#FF0000",//red
                    ]
                }]
            });
        }).catch(error => {
            console.log(error);
        });
    }, []);


    
   


    const getWatchlistStatusCount = () => {
        const statusCount = {
            completed: 0,
            watching: 0,
            dropped: 0
        };
    
        watchlist.forEach(item => {
            if (item.status === 'Completed') {
                statusCount.completed++;
            } else if (item.status === 'Watching') {
                statusCount.watching++;
            } else if (item.status === 'Dropped') {
                statusCount.dropped++;
            }
        });
    
        return [statusCount.completed, statusCount.watching, statusCount.dropped];
    }
    const data = {
        labels: ["Completed", "Watching", "Dropped"],
        datasets: [{
            data: getWatchlistStatusCount(watchlist),
            backgroundColor: [
                "#00FF00",//green
                "#0000FF",//blue
                "#FF0000",//red
            ]
        }]
    
    }

  


    const logout = () => {
        UserService.logout();
        window.location.href = '/';
    };

    
    return (
        <div>
            <h2>Welcome, {user.name}</h2>
            <p>Email: {user.email}</p>
            <button onClick={logout}>Log out</button>

            <div style={{ maxWidth: '35rem', maxHeight: '35rem', margin: 'auto', textAlign: 'center' }}>
                <h4 style={{ marginTop: '10px' }}>Watchlist statistics</h4>
                <Doughnut data={data} />
            </div>

        </div>
    );

}

export default UserPage;
