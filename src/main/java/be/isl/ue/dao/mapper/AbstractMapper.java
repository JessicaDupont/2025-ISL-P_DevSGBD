package be.isl.ue.dao.mapper;

import be.isl.ue.dao.table.AbstractTable;
import be.isl.ue.entity.AbstractEntity;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author jessi
 * @param <E>
 * @param <T>
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
    public boolean isMapped(ResultSet rs) {
        try {
            rs.findColumn(table.getAlias_Column(table.COLUMN_ID));
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }
}
