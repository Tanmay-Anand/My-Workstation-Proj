import { createSlice } from '@reduxjs/toolkit';
import { jwtDecode } from 'jwt-decode';

const initialState = {
  token: null,
  user: null,
  expiresAt: null
};

const authSlice = createSlice({
  name: 'auth',
  initialState,
  reducers: {
    setCredentials(state, action) {
      const { token } = action.payload;
      state.token = token;
      try {
        const claims = jwtDecode(token);
        state.user = { username: claims.sub, uid: claims.uid };
        state.expiresAt = claims.exp * 1000; // exp claim in seconds
      } catch (e) {
        state.user = null;
        state.expiresAt = null;
      }
    },
    clearCredentials(state) {
      state.token = null;
      state.user = null;
      state.expiresAt = null;
    }
  }
});

export const { setCredentials, clearCredentials } = authSlice.actions;
export default authSlice.reducer;

export const scheduleLogout = (token) => dispatch => {
  const claims = jwtDecode(token);
  const expMs = claims.exp * 1000;
  const delay = expMs - Date.now();
  if (delay <= 0) {
    dispatch(clearCredentials());
    return;
  }
  setTimeout(() => {
    dispatch(clearCredentials());
    window.location.href = '/login';
  }, delay);
};
