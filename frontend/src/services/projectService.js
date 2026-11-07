import api from './api';

export const getProjects = async () => {
    const response = await api.get('/projects');
    return response.data;
};

export const createProject = async (projectData) => {
    const response = await api.post('projects' ,projectData);
    return response.data;
}

export const deleteProject = async (projectID) => {
    const response = api.delete('/projects/${projectID}');
    return (await response).data
}