/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package be.isl.ue.dao.mapper;

import be.isl.ue.dao.table.AbstractTable;
import be.isl.ue.entity.AbstractEntity;
import java.sql.ResultSet;

/**
 *
 * @author jessi
 */
public abstract class AbstractMapper<E extends AbstractEntity, T extends AbstractTable> {
    protected T table;

    public T getTable() {
        return table;
    }

    public AbstractMapper(T table) {
        this.table = table;
    }
    
    public abstract E map(ResultSet rs);
}
