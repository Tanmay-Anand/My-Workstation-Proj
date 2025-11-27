import React from 'react';
import { Routes, Route, Link, Navigate } from 'react-router-dom';
import PrivateRoute from './components/PrivateRoute';
import Home from './pages/Home';
import Notes from './pages/Notes';
import Bookmarks from './pages/Bookmarks';
import Login from './pages/Login';
import Signup from './pages/Signup';

export default function App() {
  return (
    <div className="min-h-screen bg-gray-50 dark:bg-gray-900 text-gray-900 dark:text-gray-100">
      <nav className="p-4 border-b">
  <Link className="mr-4" to="/home">Home</Link>
  <Link className="mr-4" to="/notes">Notes</Link>
  <Link to="/bookmarks">Bookmarks</Link>
</nav>


      <main className="p-6">
        <Routes>
  {/* Default route → Signup */}
  <Route path="/" element={<Navigate to="/signup" replace />} />

        {/* Public pages */}
          <Route path="/login" element={<Login />} />
          <Route path="/signup" element={<Signup />} />

          {/* Private pages */}
          <Route
            path="/home"
            element={
              <PrivateRoute>
                <Home />
              </PrivateRoute>
            }
          />
          <Route
            path="/notes"
            element={
              <PrivateRoute>
                <Notes />
              </PrivateRoute>
            }
          />
          <Route
            path="/bookmarks"
            element={
              <PrivateRoute>
                <Bookmarks />
              </PrivateRoute>
            }
          />

        </Routes>
      </main>
    </div>
  );
}

// Only what’s inside the <Route> area changes.
// Navbar stays where it is.

// Anything outside Routes is stable.