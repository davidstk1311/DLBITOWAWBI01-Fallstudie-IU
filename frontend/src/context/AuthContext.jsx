import React, { createContext, useState, useEffect } from 'react';
import {loginUser, logoutUser, getCurrentUser} from '../services/authService.';

export const AuthContext = createContext();

export const AuthProvider = ({childeren}) => {
    const [user, setUser] = userState(null);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        const token = localStorage.getItem('token');
        const username = localStorage.getItem('username');
        const role = localStorage.getItem('role');
        if (token && username) {
            setUser({ token, username});
        
            setLoading(false);
        }, []);


    const login = async (username, password) => {
        const data = await loginUser(username, password);

        seUser({username: data.username, role: data.role});
        return data; }

        const logout = () => {
            logoutUser();
            seUser(null);
        };

        return (
            <AuthContext.Provider value={{ user, loading, login, logout }}>
                {!loading && childeren}
            </AuthContext.Provider>
        );
    }
