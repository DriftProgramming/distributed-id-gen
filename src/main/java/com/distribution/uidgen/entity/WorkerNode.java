package com.distribution.uidgen.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "WORKER_NODE")
public class WorkerNode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String hostName;

    String port;

    Integer type;

    Date launchDate = new Date();

}
