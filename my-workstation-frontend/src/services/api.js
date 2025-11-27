import axios from 'axios';
import { store } from '../store'; // import store to read token
import { clearCredentials } from '../store/slices/authSlice';

const api = axios.create({
  baseURL: import.meta.env.VITE_API_BASE_URL || '/api',
  timeout: 8000,
});

api.interceptors.request.use(config => {
  const state = store.getState();
  const token = state.auth.token;
  if (token) {
    config.headers.Authorization = `Bearer ${token}`;
  }
  return config;
});

api.interceptors.response.use(
  res => res,
  err => {
    if (err.response && err.response.status === 401) {
      // token expired or invalid — clear auth and redirect to login
      store.dispatch(clearCredentials());
      // you might import navigate from react-router or set a window.location:
      window.location.href = '/login';
    }
    return Promise.reject(err);
  }
);

export default api;
