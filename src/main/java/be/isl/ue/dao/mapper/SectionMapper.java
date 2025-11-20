/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package be.isl.ue.dao.mapper;

import be.isl.ue.dao.table.PersonTable;
import be.isl.ue.dao.table.SectionTable;
import be.isl.ue.entity.Person;
import be.isl.ue.entity.Section;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author jessi
 */
public class SectionMapper extends AbstractMapper<Section, SectionTable> {

    private PersonMapper pM;

    public PersonMapper getPersonMapper() {
        return pM;
    }

    public SectionMapper(SectionTable table, PersonMapper personM) {
        super(table);
        pM = personM;
    }

    @Override
    public Section map(ResultSet rs) {
        try {
            return new Section(
                    rs.getInt(table.getCOLUMN_ID()),
                    rs.getString(table.getNAME()),
                    rs.getString(table.getDESCRIPTION()),
                    pM.map(rs),
                    rs.getTimestamp(table.getINSERTED_AT()).toLocalDateTime(),
                    rs.getTimestamp(table.getUPDATED_AT()).toLocalDateTime()
            );
        } catch (SQLException ex) {
            System.getLogger(SectionMapper.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return null;
    }

}
