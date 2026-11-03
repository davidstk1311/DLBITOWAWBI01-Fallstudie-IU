import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { useAuth } from '../context/AuthContext';

const Login = () => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');
    const { login } = useAuth();
    const navigate = useNavigate();

    const handleSubmit = async (e) => {
        e.preventDefault();
        setError('');

        try {
            await login(username, password);
            navigate('/projects');
        } catch (err) {
            setError('Login fehlgeschlagen.');
        }
    };

    return (
        <div style={{maxWidth: '400px', margin: '0 auto', padding: '20px'}}>
            <h2>Anmelden</h2>
            {error && <p style={{color: 'red'}}>{error}</p>}
            
            <form onSubmit={handleSubmit}>
                <div style={{marginBottom: '15px'}}>
                    <label>Benutzername:</label>
                    <input
                        type="text"
                        value={username}
                        onChange={(e) => setUsername(e.target.value)}
                        required
                        style={{width: '100%', padding: '8px', boxSizing: 'border-box'}}
                    />
                </div>

                <div style={{marginBottom: '15px'}}>
                    <label>Passwort:</label>
                    <input
                        type="password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        required
                        style={{width: '100%', padding: '8px', boxSizing: 'border-box'}}
                    />
                </div>

                <button type="submit" style={{width: '100%', padding: '10px', backgroundColor: '#007BFF', color: '#fff', border: 'none', cursor: 'pointer'}}>
                    Anmelden
                </button>
            </form>
        </div>
    );
};

export default Login;

