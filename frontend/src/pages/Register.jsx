import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { useAuth } from '../context/AuthContext';

const Register = () => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [role, setRole] = useState('ROLE_USER');
    const [message, setMessage] = useState('');
    const [error, setError] = useState('');

    const navigate = useNavigate();

    const handleSubmit = async (e) => {
        e.preventDefault();
        setError('');
        setMessage('');

        try {
             await registerUser(username, password, role);
            setMessage('Registrierung erfolgreich! Bitte melden Sie sich an.');
            navigate('/login');
        } catch (err) {
            setError('Registrierung fehlgeschlagen. Bitte später erneut versuchen.');
        }
    };

    return (
        <div style={{maxWidth: '400px', margin: '0 auto', padding: '20px'}}>
            <h2>Konto registrieren</h2>
            {message && <p style={{color: 'green'}}>{message}</p>}
            {error && <p style={{color: 'red'}}>{error}</p>}
            <form onSubmit={handleSubmit}>
                <div style={{marginBottom: '15px'}}>
                    <label>Benutzername:</label>
                    <input
                        type="text"
                        value={username}
                        onChange={(e) => setUsername(e.target.value)}
                        required
                    />
                </div>
                <div style={{marginBottom: '15px'}}>
                    <label>Passwort:</label>
                    <input
                        type="password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        required
                    />
                </div>
                <div style={{marginBottom: '15px'}}>
                    <label>Rolle:</label>
                    <select value={role} onChange={(e) => setRole(e.target.value)}>
                        <option value="ROLE_USER">Benutzer</option>
                        <option value="ROLE_ADMIN">Administrator</option>
                    </select>
                </div>
                <button type="submit">Registrieren</button>
            </form>
        </div>
    );
};

