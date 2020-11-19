package com.distribution.uidgen.repo;

import com.distribution.uidgen.entity.WorkerNode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkerNodeRepo extends JpaRepository<WorkerNode,Long> {
}
