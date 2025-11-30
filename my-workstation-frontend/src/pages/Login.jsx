import React, { useState } from 'react';
import api from '../api/api'; // FIXED: Should be ../api/api not ../services/api
import { useDispatch } from 'react-redux';
import { setCredentials } from '../store/slices/authSlice';
import { useNavigate } from 'react-router-dom';
import { Link } from 'react-router-dom';


export default function Login() {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [err, setErr] = useState(null);
  const dispatch = useDispatch();
  const navigate = useNavigate();

  const submit = async (e) => {
    e.preventDefault();
    try {
      const res = await api.post('/auth/login', { username, password });
      const token = res.data.token;
      dispatch(setCredentials({ token }));
      navigate('/home'); // go inside app
    } catch (e) {
      setErr(e.response?.data || e.message);
    }
  }

  return (
    <div className="max-w-md mx-auto p-6">
      <h2 className="text-2xl mb-4">Login</h2>

      {err && <div className="text-red-600 mb-2">{err}</div>}

      <form onSubmit={submit}>
        <input
          value={username}
          onChange={e => setUsername(e.target.value)}
          placeholder="username"
          className="mb-2 w-full p-2 border rounded"
        />
        <input
          value={password}
          onChange={e => setPassword(e.target.value)}
          placeholder="password"
          type="password"
          className="mb-2 w-full p-2 border rounded"
        />
        <button className="btn w-full mt-2">Login</button>
      </form>

      <p className="mt-4 text-center">
        New user? <Link className="text-blue-500" to="/signup">Create an account</Link>
      </p>
    </div>
  );
}

