import React from 'react';
import { Routes, Route, Link } from 'react-router-dom';
import Home from './pages/Home';
import Notes from './pages/Notes';
import Bookmarks from './pages/Bookmarks';

export default function App() {
  return (
    <div className="min-h-screen bg-gray-50 dark:bg-gray-900 text-gray-900 dark:text-gray-100">
      <nav className="p-4 border-b">
        <Link className="mr-4" to="/">Home</Link>
        <Link className="mr-4" to="/notes">Notes</Link>
        <Link to="/bookmarks">Bookmarks</Link>
      </nav>

      <main className="p-6">
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/notes" element={<Notes />} />
          <Route path="/bookmarks" element={<Bookmarks />} />
        </Routes>
      </main>
    </div>
  );
}
