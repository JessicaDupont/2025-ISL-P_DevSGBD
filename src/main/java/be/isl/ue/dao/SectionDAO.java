/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package be.isl.ue.dao;

import be.isl.ue.dao.mapper.SectionMapper;
import be.isl.ue.entity.Section;
import be.isl.ue.ui.viewmodel.SectionViewModel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 *
 * @author jessi
 */
public class SectionDAO extends AbstractDAO<Section, SectionMapper, SectionViewModel> {

    public SectionDAO() {
        super();
        mapper = sM;
    }

    @Override
    protected void insert(Section e) {
        try {
            String sql = "INSERT INTO " + sT.TABLE_NAME + "("
                    + sT.NAME + ", " + sT.DESCRIPTION + ", "
                    + sT.FK_PERSON + ", "
                    + sT.INSERTED_AT + ", " + sT.UPDATED_AT
                    + ")VALUES( "
                    + "?, ?, "
                    + "?, "
                    + "CURRENT_TIMESTAMP, CURRENT_TIMESTAMP"
                    + ");";
            PreparedStatement pStmt = super.connect2DB.getConn().prepareStatement(sql);
            int i = 1;
            pStmt.setString(i++, e.getName());
            pStmt.setString(i++, e.getDescription());
            pStmt.setInt(i++, e.getCoordinator().getId());

            pStmt.executeUpdate();
            pStmt.close();
            super.updateIdAfterInsert(e, sT.TABLE_NAME + "_" + sT.COLUMN_ID + "_seq");
        } catch (SQLException ex) {
            System.getLogger(SectionDAO.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }

    @Override
    protected void select(SectionViewModel vm) {
        try {
            String sql = "SELECT " + sT.getAllAliasAsColumns() + ", "
                    + pT.getAllAliasAsColumns()
                    + " FROM " + sT.getTABLE_NAMEWithAlias()
                    + " LEFT JOIN " + pT.getTABLE_NAMEWithAlias()
                    + " ON " + pT.getAliasDotColumn(pT.COLUMN_ID) + " = " + sT.getAliasDotColumn(sT.FK_PERSON);

            if (vm != null) {
                String where = " WHERE 1=1 ";
                where += addWhereInSQL(vm.getName(), sT.getAliasDotColumn(sT.NAME));
                where += addWhereInSQL(vm.getCoordinatorLastName(), pT.getAliasDotColumn(pT.LASTNAME));
                sql += where + "ORDER BY " + sT.getAliasDotColumn(sT.NAME) + ";";
            }

            PreparedStatement stmt = super.connect2DB.getConn().prepareStatement(sql);
            if (vm != null) {
                int i = 1;
                if (isNotNullOrEmpty(vm.getName())) {
                    stmt.setString(i++, "%" + vm.getName() + "%");
                }
                if (isNotNullOrEmpty(vm.getCoordinatorLastName())) {
                    stmt.setString(i++, "%" + vm.getCoordinatorLastName() + "%");
                }
            }

            ResultSet rs = stmt.executeQuery();
            //System.out.println("RS : "+rs);
            super.entityList.clear();
            while (rs.next()) {
                super.entityList.add(mapper.map(rs));
            }

            stmt.close();
        } catch (SQLException ex) {
            System.getLogger(SectionDAO.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }

    @Override
    protected void update(Section e) {
        try {
            e.setUpdatedAt(LocalDateTime.now());
            String sql = "UPDATE " + sT.TABLE_NAME + " SET "
                    + sT.NAME + " = ?, "
                    + sT.DESCRIPTION + " = ?, "
                    + sT.FK_PERSON + " = ?, "
                    + sT.UPDATED_AT + " = ? "
                    + " WHERE " + sT.COLUMN_ID + " = ? ;";

            PreparedStatement pStmt = super.connect2DB.getConn().prepareStatement(sql);
            int i = 1;
            pStmt.setString(i++, e.getName());
            pStmt.setString(i++, e.getDescription());
            pStmt.setInt(i++, e.getCoordinator().getId());
            pStmt.setTimestamp(i++, Timestamp.valueOf(e.getUpdatedAt()));
            pStmt.setInt(i++, e.getId());

            pStmt.executeUpdate();
            pStmt.close();
        } catch (SQLException ex) {
            System.getLogger(SectionDAO.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }

}
