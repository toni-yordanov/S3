import axios from 'axios';
import authHeader from './AuthenticationHeader';

const API_BASE_URL = 'http://localhost:8080';

const WatchlistService = {
  getWatchlists: () => {
    return axios.get(`${API_BASE_URL}/watchlist`);
  },
  getWatchlistForUser: () => {
    return axios.get(`${API_BASE_URL}/watchlist/personal`,{headers: authHeader()});
  },
  getWatchlistById: (id) => {
    return axios.get(`${API_BASE_URL}/watchlist/${id}`);
  },
  saveWatchlist: (watchlist) => {
    return axios.post(`${API_BASE_URL}/watchlist`, watchlist,{headers: authHeader()});
  },
  updateWatchlist: (watchlist) => {
    return axios.put(`${API_BASE_URL}/watchlist/${watchlist.id}`, watchlist);
  },
  deleteWatchlist: (watchlistId) => {
    return axios.delete(`${API_BASE_URL}/watchlist/${watchlistId}`);
  },
};

export default WatchlistService;
