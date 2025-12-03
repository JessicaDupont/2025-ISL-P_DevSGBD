/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package be.isl.ue.dao.mapper;

import be.isl.ue.dao.table.UETable;
import be.isl.ue.entity.UE;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author jessi
 */
public class UEMapper extends AbstractMapper<UE, UETable> {

    private SectionMapper sM;
    private CapacityMapper cM;

    public UEMapper(UETable table, SectionMapper sM, CapacityMapper cM) {
        super(table);
        this.sM = sM;
        this.cM = cM;
    }

    @Override
    public UE map(ResultSet rs) {
        try {
            return new UE(
                    rs.getInt(table.getAlias_Column(table.COLUMN_ID)),
                    rs.getString(table.getAlias_Column(table.CODE)),
                    rs.getString(table.getAlias_Column(table.NAME)),
                    rs.getString(table.getAlias_Column(table.DESCRIPTION)),
                    rs.getInt(table.getAlias_Column(table.NUMBER_OF_PERIODS)),
                    rs.getBoolean(table.getAlias_Column(table.IS_DECISIVE)),
                    sM.map(rs),
                    rs.getTimestamp(table.getAlias_Column(table.INSERTED_AT)) == null
                    ? null
                    : rs.getTimestamp(table.getAlias_Column(table.INSERTED_AT)).toLocalDateTime(),
                    rs.getTimestamp(table.getAlias_Column(table.UPDATED_AT)) == null
                    ? null
                    : rs.getTimestamp(table.getAlias_Column(table.UPDATED_AT)).toLocalDateTime()
            );
        } catch (SQLException ex) {
            System.getLogger(UEMapper.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return null;
    }
;

}
