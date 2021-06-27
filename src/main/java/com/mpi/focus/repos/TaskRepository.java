package com.mpi.focus.repos;


import com.mpi.focus.models.Task;
import com.mpi.focus.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
