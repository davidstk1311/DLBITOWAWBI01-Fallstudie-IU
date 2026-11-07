import React, { useEffect, useState } from 'react';
import { getProjects, createProject, deleteProject } from '../services/projectService';

const Projects = () => {
    const [projects, setProjects] = useState([]);
    const [title, setTitle] = useState('');
    const [description, setDescription] = useState('');
    const [error, setError] = useState('');
    const [loading, setLoading] = useState(false);

    const userRole = localStorage.getItem('userRole');
    const isRoleManager = userRole === 'project_manager_role';

    const fetchProjects = async () => {
        try {
            setLoading(true);
            const data = await getProjects();
            setProjects(data);
        } catch (error) {
            setError('Failed to fetch projects');
        } finally {
            setLoading(false);
        }
    };

    useEffect(() => {
        fetchProjects();
    }, []);


    const handleCreateProject = async (e) => {
        e.preventDefault();
        if (!title) return;

        try {
            await createProject({ title, description });
            setTitle('');
            setDescription('');
            fetchProjects();
        } catch (error) {
            setError('Fehler beim Erstellen des Projekts');
        }
    };

    const handleDeleteProject = async (id) => {
        try {
            await deleteProject(id);
            fetchProjects();
        } catch (error) {
            setError('Fehler beim Entfernen des Projekts');
        }
    };



    return (
        <div style={{ padding: '15px', margin: '15px' }}>
            <h3>Neues Projekt anlegen</h3>
            <form onSubmit={handleCreateProject}>
                <div style={{ margin: '20px' }}>
                    <input
                        type='text'
                        placeholder='Projektnamen eingeben...'
                        value={title}
                        onChange={(e) => setTitle(e.target.value)}
                        required
                    />
                </div>
            </form>
            {loading ? (
                <p>Lade Projekte...</p>
            ) : projects.length === 0 ? (
                <p>Noch keine Projekte verfügbar</p>
            ) : (
                <ul style={{ margin: '10px', listStyle: 'none' }}>
                    {projects.map((project) => (
                        <li
                            key={project.id}
                            style={{
                                padding: '15px',
                                color: 'blue',
                                display: 'flex',
                                alignItems: 'center'
                            }}
                        >
                            <div>
                                <h4 style={{ font: 'italic' }}>{project.title}</h4>
                                <p style={{margin: '10px'}}>{project.description}</p>
                            </div>
                        {isProjectLeader && 
                        (
                            <button onClick={handleDeleteProject(project.id)} style={{color: 'red'}}>
                                Löschen
                                </button>
                        )}
                        </li>
                    ))}
                        </ul>
            )}
        </div>
    );
};

export default Projects;