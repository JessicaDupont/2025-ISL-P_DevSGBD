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

    public SectionMapper(SectionTable table, PersonMapper personM) {
        super(table);
        pM = personM;
    }

    @Override
    public Section map(ResultSet rs) {
        try {
            Person coordinator = pM.map(rs);
            return new Section(
                    rs.getInt(table.COLUMN_ID),
                    rs.getString(table.NAME),
                    rs.getString(table.DESCRIPTION),
                    coordinator,
                    rs.getTimestamp(table.INSERTED_AT).toLocalDateTime(),
                    rs.getTimestamp(table.UPDATED_AT).toLocalDateTime()
            );
        } catch (SQLException ex) {
            System.getLogger(SectionMapper.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return null;
    }

}
