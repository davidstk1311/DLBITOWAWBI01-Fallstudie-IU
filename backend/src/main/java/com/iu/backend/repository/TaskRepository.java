
package com.iu.backend.repository;
import com.iu.backend.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;


public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByProjectId(Long projectId);
}