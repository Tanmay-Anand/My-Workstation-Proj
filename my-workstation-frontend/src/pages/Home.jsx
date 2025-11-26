import React, { useEffect, useState } from 'react';
import api from '../services/api';

export default function Home() {
  const [ping, setPing] = useState(null);
  const [err, setErr] = useState(null);

  useEffect(() => {
    api.get('/ping')
      .then(res => setPing(res.data))
      .catch(e => setErr(e.message || 'Error'));
  }, []);

  return (
    <div>
      <h1 className="text-2xl font-semibold mb-4">My Workstation — Frontend (Phase 1)</h1>
      <div className="p-4 bg-white rounded shadow">
        <p>Ping result: <strong>{ping ?? (err ? `Failed — ${err}` : 'loading...')}</strong></p>
      </div>
    </div>
  );
}
