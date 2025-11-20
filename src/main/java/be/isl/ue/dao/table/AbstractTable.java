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

    public final String TABLE_ALIAS;
    public final String COLUMN_ID;
    public final String INSERTED_AT = "inserted_ts";
    public final String UPDATED_AT = "updated_ts";

    public String getTABLE_NAME() {
        return TABLE_NAME;
    }
    public String getTABLE_NAMEWithAlias() {
        return TABLE_NAME+" AS "+TABLE_ALIAS;
    }
    public String getCOLUMN_ID() {
        return getAliasColumn(COLUMN_ID);
    }

    public String getUPDATED_AT() {
        return getAliasColumn(UPDATED_AT);
    }

    public String getINSERTED_AT() {
        return getAliasColumn(INSERTED_AT);
    }

    public String getCOLUMN_IDWithAlias() {
        return getAliasColumnWithAlias(COLUMN_ID);
    }

    public String getUPDATED_ATWithAlias() {
        return getAliasColumnWithAlias(UPDATED_AT);
    }

    public String getINSERTED_ATWithAlias() {
        return getAliasColumnWithAlias(INSERTED_AT);
    }

    public AbstractTable(String name, String columnId, String alias) {
        this.TABLE_NAME = name;
        this.COLUMN_ID = columnId;
        this.TABLE_ALIAS = alias;
    }

    protected String getAliasColumnWithAlias(String c) {
        return TABLE_ALIAS + "." + c + " AS \"" + TABLE_ALIAS + "." + c + "\"";
    }

    protected String getAliasColumn(String c) {
        return TABLE_ALIAS + "." + c;
    }

}
