/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package be.isl.ue.dao;

import be.isl.ue.dao.mapper.UEMapper;
import be.isl.ue.entity.UE;
import be.isl.ue.ui.viewmodel.UEViewModel;
import be.isl.ue.ui.viewmodel.ViewModel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 *
 * @author jessi
 */
public class UeDAO extends AbstractDAO<UE, UEMapper, UEViewModel> {

    public UeDAO() {
        super();
        mapper = super.ueM;
    }

    @Override
    protected void insert(UE e) {
        try {
            String sql = "INSERT INTO " + ueT.TABLE_NAME + " ("
                    + ueT.NAME + ", " + ueT.CODE + ", " + ueT.DESCRIPTION + ", "
                    + ueT.IS_DECISIVE + ", " + ueT.NUMBER_OF_PERIODS + ", "
                    + ueT.INSERTED_AT + ", " + ueT.UPDATED_AT
                    + ") VALUES ("
                    + "?, ?, ?, "
                    + "?, ?, "
                    + "CURRENT_TIMESTAMP, CURRENT_TIMESTAMP"
                    + ");";
            PreparedStatement pStmt = super.connect2DB.getConn().prepareStatement(sql);
            int i = 1;
            pStmt.setString(i++, e.getName());
            pStmt.setString(i++, e.getCode());
            pStmt.setString(i++, e.getDescription());
            pStmt.setBoolean(i++, e.getIsDecisive());
            pStmt.setInt(i++, e.getNumberOfPeriods());

            pStmt.executeUpdate();
            pStmt.close();
            super.updateIdAfterInsert(e, ueT.TABLE_NAME + "_" + ueT.COLUMN_ID + "_seq");
        } catch (SQLException ex) {
            System.getLogger(UeDAO.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }

    @Override
    protected void select(UEViewModel vm) {
        try {
            String sql = "SELECT " + ueT.getAllAliasAsColumns() + ", "
                    + sT.getAllAliasAsColumns() + ", "
                    + pT.getAllAliasAsColumns() + ", "
                    + cT.getAllAliasAsColumns()
                    + " FROM " + ueT.getTABLE_NAMEWithAlias()
                    + " LEFT JOIN " + sT.getTABLE_NAMEWithAlias()
                    + " ON " + sT.getAliasDotColumn(sT.COLUMN_ID) + " = " + ueT.getAliasDotColumn(ueT.FK_SECTION)
                    + " LEFT JOIN " + pT.getTABLE_NAMEWithAlias()
                    + " ON " + pT.getAliasDotColumn(pT.COLUMN_ID) + " = " + sT.getAliasDotColumn(sT.FK_PERSON)
                    + " LEFT JOIN " + cT.getTABLE_NAMEWithAlias()
                    + " ON " + cT.getAliasDotColumn(cT.FK_UE) + " = " + ueT.getAliasDotColumn(ueT.COLUMN_ID);

            if (vm != null) {
                String where = " WHERE 1=1 ";
                where += addWhereInSQL(vm.getSection(), ueT.getAliasDotColumn(ueT.FK_SECTION));
                where += addWhereInSQL(vm.getName(), ueT.getAliasDotColumn(ueT.NAME));
                where += addWhereInSQL(vm.getDescription(), ueT.getAliasDotColumn(ueT.DESCRIPTION));
                //where += addWhereInSQL(vm.getCapacity(),
                sql += where + " ORDER BY " + ueT.getAliasDotColumn(ueT.COLUMN_ID);
            }
            System.out.println("SQL : " + sql);
            PreparedStatement stmt = super.connect2DB.getConn().prepareStatement(sql);
            if (vm != null) {
                int i = 1;
                if (isNotNullOrEmpty(vm.getName())) {
                    stmt.setString(i++, "%" + vm.getSection() + "%");
                }
                if (isNotNullOrEmpty(vm.getName())) {
                    stmt.setString(i++, "%" + vm.getName() + "%");
                }
                if (isNotNullOrEmpty(vm.getDescription())) {
                    stmt.setString(i++, "%" + vm.getDescription() + "%");
                }
            }

            ResultSet rs = stmt.executeQuery();
            super.entityList.clear();
            UE currentUE = null;
            int lastUEId = -1;
            while (rs.next()) {
                int currentUEId = rs.getInt(ueT.getAlias_Column(ueT.COLUMN_ID));

                if (currentUEId != lastUEId) {
                    currentUE = mapper.map(rs);

                    if (currentUE != null) {
                        super.entityList.add(currentUE);
                    }

                    lastUEId = currentUEId;
                }
                rs.getInt(cT.getAlias_Column(cT.COLUMN_ID));
                
                if (!rs.wasNull() && currentUE != null) {
                    cM.map(rs, currentUE);
                }
            }

            stmt.close();
        } catch (SQLException ex) {
            System.getLogger(UeDAO.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }

    }

    @Override
    protected void update(UE e) {
        try {
            String sql = "UPDATE " + ueT.TABLE_NAME + " SET "
                    + ueT.CODE + " = ?, "
                    + ueT.NAME + " = ?, "
                    + ueT.DESCRIPTION + " = ?, "
                    + ueT.IS_DECISIVE + " = ?, "
                    + ueT.NUMBER_OF_PERIODS + " = ?, "
                    + ueT.FK_SECTION + " = ?, "
                    + ueT.UPDATED_AT + " = ? "
                    + " WHERE " + ueT.COLUMN_ID + " = ?";
            PreparedStatement pStmt = super.connect2DB.getConn().prepareStatement(sql);
            int i = 1;
            pStmt.setString(i++, e.getCode());
            pStmt.setString(i++, e.getName());
            pStmt.setString(i++, e.getDescription());
            pStmt.setBoolean(i++, e.getIsDecisive());
            pStmt.setInt(i++, e.getNumberOfPeriods());
            pStmt.setInt(i++, e.getSection().getId());
            pStmt.setTimestamp(i++, Timestamp.valueOf(e.getUpdatedAt()));
            pStmt.setInt(i++, e.getId());

            pStmt.executeUpdate();
            pStmt.close();
        } catch (SQLException ex) {
            System.getLogger(UeDAO.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }

}
