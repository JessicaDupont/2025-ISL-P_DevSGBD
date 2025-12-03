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

    public AbstractTable(String name, String columnId, String alias) {
        this.TABLE_NAME = name;
        this.COLUMN_ID = columnId;
        this.TABLE_ALIAS = alias;
    }

    public String getTABLE_NAMEWithAlias() {
        return this.TABLE_NAME + " AS " + this.TABLE_ALIAS;
    }

    public String getAliasDotColumn(String c) {
        return TABLE_ALIAS + "." + c;
    }

    public String getAlias_Column(String c) {
        return TABLE_ALIAS + "_" + c;
    }

    public String getAliasAsColumn(String c) {
        return getAliasDotColumn(c) + " AS " + getAlias_Column(c) + " ";
    }

    public String getAllAliasAsColumns() {
        return getAliasAsColumn(this.COLUMN_ID) + ", "
                + getAliasAsColumn(this.INSERTED_AT) + ", "
                + getAliasAsColumn(this.UPDATED_AT);
    }

}
