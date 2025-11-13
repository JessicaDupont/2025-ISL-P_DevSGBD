/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
                    rs.getInt(table.COLUMN_ID),
                    rs.getString(table.FIRSTNAME),
                    rs.getString(table.LASTNAME),
                    rs.getDate(table.DATE_OF_BIRTH).toLocalDate(),
                    rs.getString(table.EMAIL),
                    rs.getString(table.MOBILE),
                    rs.getString(table.ADDRESS),
                    rs.getString(table.POSTAL_CODE),
                    rs.getString(table.CITY),
                    rs.getString(table.COUNTRY),
                    rs.getBoolean(table.IS_JURY_MEMBER),
                    rs.getBoolean(table.IS_TEACHER),
                    rs.getTimestamp(table.INSERTED_AT).toLocalDateTime(),
                    rs.getTimestamp(table.UPDATED_AT).toLocalDateTime()
            );
        } catch (SQLException ex) {
            System.getLogger(PersonMapper.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return null;
    }

}
