import React from 'react'; 
import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom'; 
import { AuthProvider } from './context/AuthContext'; 
import ProtectedRoute from './components/ProtectedRoute'; 
import Login from './pages/Login'; import Register from './pages/Register';


function App() {
  const ProjectsPlaceholder = () => (
    <div style={{ padding: '20px', textAlign: 'center' }}>
      <h1>Projektübersicht</h1>
      <p>Der JWT Login funktioniert einwandfrei. </p>
    </div>
  );

  return (
    <AuthProvider>
      <Router> 
        <Routes>
          <Route path="/login" element={<Login />} />
          <Route path="/register" element={<Register />} />
          <Route
            path="/projects"
            element={
              <ProtectedRoute>
                <ProjectsPlaceholder />
              </ProtectedRoute>
            }
          />
        </Routes>
      </Router>
    </AuthProvider>
  );
}

export default App
