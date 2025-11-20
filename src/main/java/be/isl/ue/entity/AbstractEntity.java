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
public abstract class AbstractEntity<E extends AbstractEntity> implements Comparable<E> {

    protected Integer id;
    protected LocalDateTime insertedAt;
    protected LocalDateTime updatedAt;

    public AbstractEntity() {
    }

    public AbstractEntity(Integer id, LocalDateTime insertedAt, LocalDateTime updatedAt) {
        this.id = id;
        this.insertedAt = insertedAt;
        this.updatedAt = updatedAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getInsertedAt() {
        return insertedAt;
    }

    public void setInsertedAt(LocalDateTime insertedAt) {
        this.insertedAt = insertedAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public int compareTo(E o) {
        if (o == null) {
            return 1;
        }

        if (this.id == null && o.id == null) {
            return 0;
        }
        if (this.id == null) {
            return 1;
        }
        if (o.id == null) {
            return -1;
        }

        return this.id.compareTo(o.id);
    }

    @Override
    public String toString() {
        return "id=" + id + ", insertedAt=" + insertedAt + ", updatedAt=" + updatedAt;
    }

}
