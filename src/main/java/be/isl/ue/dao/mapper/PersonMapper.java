/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package be.isl.ue.dao.mapper;

import be.isl.ue.dao.table.PersonTable;
import be.isl.ue.entity.Person;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

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
                    rs.getInt(table.getCOLUMN_ID()),
                    rs.getString(table.getFIRSTNAME()),
                    rs.getString(table.getLASTNAME()),
                    rs.getDate(table.getDATE_OF_BIRTH()) == null ? null : rs.getDate(table.getDATE_OF_BIRTH()).toLocalDate(),
                    rs.getString(table.getEMAIL()),
                    rs.getString(table.getMOBILE()),
                    rs.getString(table.getADDRESS()),
                    rs.getString(table.getPOSTAL_CODE()),
                    rs.getString(table.getCITY()),
                    rs.getString(table.getCOUNTRY()),
                    rs.getBoolean(table.getIS_JURY_MEMBER()),
                    rs.getBoolean(table.getIS_TEACHER()),
                    rs.getTimestamp(table.getINSERTED_AT()) == null ? null : rs.getTimestamp(table.getINSERTED_AT()).toLocalDateTime(),
                    rs.getTimestamp(table.getUPDATED_AT()) == null ? null : rs.getTimestamp(table.getUPDATED_AT()).toLocalDateTime()
            );
        } catch (SQLException ex) {
            System.getLogger(PersonMapper.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return null;
    }

}
