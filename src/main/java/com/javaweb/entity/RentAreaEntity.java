package com.javaweb.entity;

import javax.persistence.*;

@Entity
@Table(name = "rentarea")
public class RentAreaEntity {
    @Column(name = "value")
    Long value;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;


    @ManyToOne
    @JoinColumn(name = "buildingid")
    private BuildingEntity buildingEntity;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BuildingEntity getBuildingEntity() {
        return buildingEntity;
    }

    public void setBuildingEntity(BuildingEntity buildingEntity) {
        this.buildingEntity = buildingEntity;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

}