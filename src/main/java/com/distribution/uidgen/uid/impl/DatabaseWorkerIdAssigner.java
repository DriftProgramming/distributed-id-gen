package com.distribution.uidgen.uid.impl;

import com.distribution.uidgen.entity.WorkerNode;
import com.distribution.uidgen.enums.WorkerNodeType;
import com.distribution.uidgen.repo.WorkerNodeRepo;
import com.distribution.uidgen.uid.WorkerIdAssigner;
import com.distribution.uidgen.utils.DockerUtils;
import com.distribution.uidgen.utils.NetUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component("dbWorkerIdAssigner")
@Slf4j
public class DatabaseWorkerIdAssigner implements WorkerIdAssigner {

    @Autowired
    WorkerNodeRepo workerNodeRepo;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public long assignWorkerId() {
        WorkerNode workerNode = buildWorkerNode();
        workerNode = workerNodeRepo.save(workerNode);
        log.info("Add worker node:" + workerNode);
        return workerNode.getId();
    }

    private WorkerNode buildWorkerNode() {
        WorkerNode workerNodeEntity = new WorkerNode();
        if (DockerUtils.isDocker()) {
            workerNodeEntity.setType(WorkerNodeType.CONTAINER.value());
            workerNodeEntity.setHostName(DockerUtils.getDockerHost());
            workerNodeEntity.setPort(DockerUtils.getDockerPort());

        } else {
            workerNodeEntity.setType(WorkerNodeType.ACTUAL.value());
            workerNodeEntity.setHostName(NetUtils.getLocalAddress());
            workerNodeEntity.setPort(System.currentTimeMillis() + "-" + RandomUtils.nextInt(0, 100000));
        }

        return workerNodeEntity;
    }
}
