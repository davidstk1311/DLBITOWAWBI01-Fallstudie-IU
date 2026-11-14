
package com.iu.backend.repository;
import com.iu.backend.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import com.iu.backend.model.User;


@Repository 
public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findByAssignedUsers(User currentUser);
}