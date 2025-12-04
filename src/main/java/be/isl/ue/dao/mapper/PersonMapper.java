package be.isl.ue.dao.mapper;

import be.isl.ue.dao.table.PersonTable;
import be.isl.ue.entity.Person;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author jessi
 */
public class PersonMapper extends AbstractMapper<Person, PersonTable> {

    public PersonMapper(PersonTable table) {
        super(table);
    }

    public Person map(ResultSet rs) {
        try {
            return new Person(
                    rs.getInt(table.getAlias_Column(table.COLUMN_ID)),
                    rs.getString(table.getAlias_Column(table.FIRSTNAME)),
                    rs.getString(table.getAlias_Column(table.LASTNAME)),
                    rs.getDate(table.getAlias_Column(table.DATE_OF_BIRTH)) == null 
                            ? null 
                            : rs.getDate(table.getAlias_Column(table.DATE_OF_BIRTH)).toLocalDate(),
                    rs.getString(table.getAlias_Column(table.EMAIL)),
                    rs.getString(table.getAlias_Column(table.MOBILE)),
                    rs.getString(table.getAlias_Column(table.ADDRESS)),
                    rs.getString(table.getAlias_Column(table.POSTAL_CODE)),
                    rs.getString(table.getAlias_Column(table.CITY)),
                    rs.getString(table.getAlias_Column(table.COUNTRY)),
                    rs.getBoolean(table.getAlias_Column(table.IS_JURY_MEMBER)),
                    rs.getBoolean(table.getAlias_Column(table.IS_TEACHER)),
                    rs.getTimestamp(table.getAlias_Column(table.INSERTED_AT)) == null 
                            ? null 
                            : rs.getTimestamp(table.getAlias_Column(table.INSERTED_AT)).toLocalDateTime(),
                    rs.getTimestamp(table.getAlias_Column(table.UPDATED_AT)) == null 
                            ? null 
                            : rs.getTimestamp(table.getAlias_Column(table.UPDATED_AT)).toLocalDateTime()
            );
        } catch (SQLException ex) {
            System.getLogger(PersonMapper.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return null;
    }

}
