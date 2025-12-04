/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package be.isl.ue.dao.mapper;

import be.isl.ue.dao.table.CapacityTable;
import be.isl.ue.entity.Capacity;
import be.isl.ue.entity.UE;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jessi
 */
public class CapacityMapper extends AbstractMapper<Capacity, CapacityTable> {

    private UEMapper ueM;

    public CapacityMapper(CapacityTable table) {
        super(table);
        this.ueM = ueM;
    }

    @Override
    public Capacity map(ResultSet rs) {
        try {
            UE ue = new UE(rs.getInt(table.getAlias_Column(table.FK_UE)));
            return new Capacity(
                    rs.getInt(table.getAlias_Column(table.COLUMN_ID)),
                    rs.getString(table.getAlias_Column(table.NAME)),
                    rs.getString(table.getAlias_Column(table.DESCRIPTION)),
                    rs.getBoolean(table.getAlias_Column(table.IS_THRESHOLD_OF_SUCCESS)),
                    ue,
                    rs.getTimestamp(table.getAlias_Column(table.INSERTED_AT)) == null
                    ? null
                    : rs.getTimestamp(table.getAlias_Column(table.INSERTED_AT)).toLocalDateTime(),
                    rs.getTimestamp(table.getAlias_Column(table.UPDATED_AT)) == null
                    ? null
                    : rs.getTimestamp(table.getAlias_Column(table.UPDATED_AT)).toLocalDateTime()
            );
        } catch (SQLException ex) {
            System.getLogger(CapacityMapper.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return null;
    }

    public Capacity map(ResultSet rs, UE e) {
        try {
            Capacity result = new Capacity(//String name, String description, Boolean isThresholdOfSuccess, UE ue
                    rs.getString(table.getAlias_Column(table.NAME)),
                    rs.getString(table.getAlias_Column(table.DESCRIPTION)),
                    rs.getBoolean(table.getAlias_Column(table.IS_THRESHOLD_OF_SUCCESS)),
                    e
            );
            ArrayList<Capacity> c = e.getCapacities();
            c.add(result);
            e.setCapacities(c);
        } catch (SQLException ex) {
            System.getLogger(CapacityMapper.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return null;
    }

}
