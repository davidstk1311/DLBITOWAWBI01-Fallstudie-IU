import React, { createContext, useState, useEffect } from 'react';
import { loginUser, logoutUser } from '../services/AuthService.js';

export const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
    const [user, setUser] = useState(null);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        const token = localStorage.getItem('token');
        const username = localStorage.getItem('username');
        const role = localStorage.getItem('role');

        if (token && username) {
            setUser({ token, username, role });
        }

        setLoading(false);
    }, []);

    const login = async (username, password) => {
        const data = await loginUser(username, password);

        setUser({
            username: data.username,
            role: data.role,
            token: data.token,
        });

        return data;
    };

    const logout = () => {
        logoutUser();
        setUser(null);
    };

    return (
        <AuthContext.Provider value={{ user, loading, login, logout }}>
            {!loading && children}
        </AuthContext.Provider>
    );
};
