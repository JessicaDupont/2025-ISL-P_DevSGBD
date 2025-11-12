/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package be.isl.ue.dao.mapper;

import be.isl.ue.dao.table.Table;
import be.isl.ue.entity.Entity;
import java.sql.ResultSet;

/**
 *
 * @author jessi
 */
public abstract class Mapper<E extends Entity, T extends Table> {
    protected T table;

    public T getTable() {
        return table;
    }

    public void setTable(T table) {
        this.table = table;
    }

    public Mapper(T table) {
        this.table = table;
    }
    
    public abstract E map(ResultSet rs);
}
