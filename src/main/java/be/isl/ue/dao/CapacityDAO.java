/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package be.isl.ue.dao;

import be.isl.ue.dao.mapper.CapacityMapper;
import be.isl.ue.entity.Capacity;
import be.isl.ue.ui.viewmodel.CapacityViewModel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 *
 * @author jessi
 */
public class CapacityDAO extends AbstractDAO<Capacity, CapacityMapper, CapacityViewModel> {

    public CapacityDAO() {
        super();
        mapper = cM;
    }

    @Override
    protected void insert(Capacity e) {
        try {
            String sql = "INSERT INTO " + cT.TABLE_NAME + "("
                    + cT.NAME + ", " + cT.DESCRIPTION + ", "
                    + cT.IS_THRESHOLD_OF_SUCCESS + ", "
                    + cT.FK_UE + ", "
                    + cT.INSERTED_AT + ", " + cT.UPDATED_AT
                    + ")VALUES( "
                    + "?, ?, "
                    + "?, "
                    + "?, "
                    + "CURRENT_TIMESTAMP, CURRENT_TIMESTAMP"
                    + ");";
            PreparedStatement pStmt = super.connect2DB.getConn().prepareStatement(sql);
            int i = 1;
            pStmt.setString(i++, e.getName());
            pStmt.setString(i++, e.getDescription());
            pStmt.setBoolean(i++, e.getIsThresholdOfSuccess());
            pStmt.setInt(i++, e.getUe().getId());

            pStmt.executeUpdate();
            pStmt.close();
            super.updateIdAfterInsert(e, sT.TABLE_NAME + "_" + sT.COLUMN_ID + "_seq");
        } catch (SQLException ex) {
            System.getLogger(CapacityDAO.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }

    @Override
    protected void select(CapacityViewModel vm) {
        try {
            String sql = "SELECT " + cT.getAllAliasAsColumns() + ", "
                    + ueT.getAllAliasAsColumns()
                    + " FROM " + cT.getTABLE_NAMEWithAlias()
                    + " LEFT JOIN " + ueT.getTABLE_NAMEWithAlias()
                    + " ON " + ueT.getAliasDotColumn(ueT.COLUMN_ID) + " = " + cT.getAliasDotColumn(cT.FK_UE);
            sql += " ORDER BY " + ueT.getAliasDotColumn(ueT.COLUMN_ID);

            PreparedStatement stmt = super.connect2DB.getConn().prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();
            super.entityList.clear();
            while (rs.next()) {
                super.entityList.add(mapper.map(rs));
            }

            stmt.close();
        } catch (SQLException ex) {
            System.getLogger(CapacityDAO.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }

    @Override
    protected void update(Capacity e) {
        try {
            e.setUpdatedAt(LocalDateTime.now());
            String sql = "UPDATE " + cT.TABLE_NAME + " SET "
                    + cT.NAME + " = ?, "
                    + cT.DESCRIPTION + " = ?, "
                    + cT.IS_THRESHOLD_OF_SUCCESS + " = ?, "
                    + cT.FK_UE + " = ?, "
                    + cT.UPDATED_AT + " = ? "
                    + " WHERE " + cT.COLUMN_ID + " = ? ;";

            PreparedStatement pStmt = super.connect2DB.getConn().prepareStatement(sql);
            int i = 1;
            pStmt.setString(i++, e.getName());
            pStmt.setString(i++, e.getDescription());
            pStmt.setBoolean(i++, e.getIsThresholdOfSuccess());
            pStmt.setInt(i++, e.getUe().getId());
            pStmt.setTimestamp(i++, Timestamp.valueOf(e.getUpdatedAt()));
            pStmt.setInt(i++, e.getId());

            pStmt.executeUpdate();
            pStmt.close();
        } catch (SQLException ex) {
            System.getLogger(SectionDAO.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }

}
