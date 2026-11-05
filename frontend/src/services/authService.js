import API from './api';

export const loginUser = async (username, password) => {
    const response = await API.post('/auth/login', { username, password });

    if (response.data.token) {
        localStorage.setItem('token', response.data.token);
        localStorage.setItem('username', response.data.username);
        localStorage.setItem('role', response.data.role);
    }
    return response.data;
};

export const registerUser = async (username, password, role) => {
    const response = await API.post('/auth/register', { username, password, role });
    return response.data;
}

export const logoutUser = () => {
    localStorage.removeItem('token');
    localStorage.removeItem('username');
    localStorage.removeItem('role');
};
