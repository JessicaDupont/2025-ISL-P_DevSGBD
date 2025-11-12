/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package be.isl.ue.entity;

import java.time.LocalDateTime;

/**
 *
 * @author jessi
 */
public class Capacity extends Entity<Capacity> {

    private String name;
    private String description;
    private Boolean isThresholdOfSuccess;
    private UE ue;

    public Capacity(String name, String description, Boolean isThresholdOfSuccess, UE ue) {
        this.name = name;
        this.description = description;
        this.isThresholdOfSuccess = isThresholdOfSuccess;
        this.ue = ue;
    }

    public Capacity(Integer id, String name, String description, Boolean isThresholdOfSuccess, UE ue, LocalDateTime insertedAt, LocalDateTime updatedAt) {
        super(id, insertedAt, updatedAt);
        this.name = name;
        this.description = description;
        this.isThresholdOfSuccess = isThresholdOfSuccess;
        this.ue = ue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getIsThresholdOfSuccess() {
        return isThresholdOfSuccess;
    }

    public void setIsThresholdOfSuccess(Boolean isThresholdOfSuccess) {
        this.isThresholdOfSuccess = isThresholdOfSuccess;
    }

    public UE getUe() {
        return ue;
    }

    public void setUe(UE ue) {
        this.ue = ue;
    }

    @Override
    public int compareTo(Capacity o) {
        if (o == null) {
            return 1;
        }
int idComparison = super.compareTo(o);
        if (idComparison != 0) {
            return idComparison;
        }
        if (this.name == null && o.name == null) {
            return 0;
        }
        if (this.name == null) {
            return -1; 
        }
        if (o.name == null) {
            return 1;
        }

        return this.name.compareTo(o.name);
    }

}
