/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package be.isl.ue.dao.table;

import be.isl.ue.entity.AbstractEntity;

/**
 *
 * @author jessi
 */
public abstract class AbstractTable<E extends AbstractEntity> {

    public final String TABLE_NAME;
    public final String COLUMN_ID;
    public final String INSERTED_AT = "inserted_ts";
    public final String UPDATED_AT = "updated_ts";

    public AbstractTable(String name, String columnId) {
        this.TABLE_NAME = name;
        this.COLUMN_ID = columnId;
    }

}
