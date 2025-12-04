/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package be.isl.ue.dao;

import be.isl.ue.dao.mapper.PersonMapper;
import be.isl.ue.entity.Person;
import be.isl.ue.ui.viewmodel.PersonViewModel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 *
 * @author jessi
 */
public class PersonDAO extends AbstractDAO<Person, PersonMapper, PersonViewModel> {

    public PersonDAO() {
        super();
        super.mapper = super.pM;
    }

    @Override
    protected void insert(Person e) {
        try {
            String sql = "INSERT INTO " + pT.TABLE_NAME + "("
                    + pT.FIRSTNAME + ", " + pT.LASTNAME + "," + pT.DATE_OF_BIRTH + ", "
                    + pT.EMAIL + ", " + pT.MOBILE + ", "
                    + pT.ADDRESS + ", " + pT.POSTAL_CODE + ", " + pT.CITY + ", " + pT.COUNTRY + ", "
                    + pT.IS_JURY_MEMBER + ", " + pT.IS_TEACHER + ", "
                    + pT.INSERTED_AT + ", " + pT.UPDATED_AT
                    + ") VALUES ("
                    + "?, ?, ?, "
                    + "?, ?, "
                    + "?, ?, ?, ?, "
                    + "?, ?, "
                    + "CURRENT_TIMESTAMP, CURRENT_TIMESTAMP"
                    + ");";

            PreparedStatement pStmt = super.connect2DB.getConn().prepareStatement(sql);
            int i = 1;
            pStmt.setString(i++, e.getFirstName());
            pStmt.setString(i++, e.getLastName());
            pStmt.setDate(i++, java.sql.Date.valueOf(e.getDateOfBirth()));
            pStmt.setString(i++, e.getEmail());
            pStmt.setString(i++, e.getMobile());
            pStmt.setString(i++, e.getAddress());
            pStmt.setString(i++, e.getPostalCode());
            pStmt.setString(i++, e.getCity());
            pStmt.setString(i++, e.getCountry());
            pStmt.setBoolean(i++, e.getIsJuryMember());
            pStmt.setBoolean(i++, e.getIsTeacher());

            pStmt.executeUpdate();
            pStmt.close();
            super.updateIdAfterInsert(e, pT.TABLE_NAME + "_" + pT.COLUMN_ID + "_seq");
        } catch (SQLException ex) {
            System.getLogger(PersonDAO.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }

    @Override
    protected void select(PersonViewModel vm) {
        try {
            String sql = "SELECT " + pT.getAllAliasAsColumns()
                    + " FROM " + pT.getTABLE_NAMEWithAlias();

            if (vm != null) {
                String where = " WHERE 1=1";
                where += addWhereLikeInSQL(vm.getFirstName(), pT.getAliasDotColumn(pT.FIRSTNAME));
                where += addWhereLikeInSQL(vm.getLastName(), pT.getAliasDotColumn(pT.LASTNAME));
                where += addWhereLikeInSQL(vm.getCity(), pT.getAliasDotColumn(pT.CITY));
                where += addWhereEqualsInSQL(vm.getDateOfBirth(), pT.getAliasDotColumn(pT.DATE_OF_BIRTH));
                where += addWhereLikeInSQL(vm.getEmail(), pT.getAliasDotColumn(pT.EMAIL));
                sql += where + " ORDER BY " + pT.getAliasDotColumn(pT.LASTNAME) + ", " + pT.getAliasDotColumn(pT.FIRSTNAME) + ";";
            }
            
            PreparedStatement stmt = super.connect2DB.getConn().prepareStatement(sql);
            if (vm != null) {
                int i = 1;
                if (isNotNullOrEmpty(vm.getFirstName())) {
                    stmt.setString(i++, "%" + vm.getFirstName() + "%");
                }
                if (isNotNullOrEmpty(vm.getLastName())) {
                    stmt.setString(i++, "%" + vm.getLastName() + "%");
                }
                if (isNotNullOrEmpty(vm.getCity())) {
                    stmt.setString(i++, "%" + vm.getCity() + "%");
                }
                if (isNotNullOrEmpty(vm.getDateOfBirth())) {
                    stmt.setDate(i++, java.sql.Date.valueOf(vm.getDateOfBirth()));
                }
                if (isNotNullOrEmpty(vm.getEmail())) {
                    stmt.setString(i++, "%" + vm.getEmail() + "%");
                }
            }

            ResultSet rs = stmt.executeQuery();
            super.entityList.clear();
            while (rs != null && rs.next()) {
                super.entityList.add(mapper.map(rs));
            }

            stmt.close();
        } catch (SQLException ex) {
            System.getLogger(PersonDAO.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }

    @Override
    protected void update(Person e) {
        try {
            e.setUpdatedAt(LocalDateTime.now());
            String sql = "UPDATE " + pT.TABLE_NAME + " SET "
                    + pT.FIRSTNAME + " = ?, "
                    + pT.LASTNAME + " = ?, "
                    + pT.DATE_OF_BIRTH + " = ?, "
                    + pT.EMAIL + " = ?, "
                    + pT.MOBILE + " = ?, "
                    + pT.ADDRESS + " = ?, "
                    + pT.POSTAL_CODE + " = ?, "
                    + pT.CITY + " = ?, "
                    + pT.COUNTRY + " = ?, "
                    + pT.IS_JURY_MEMBER + " = ?, "
                    + pT.IS_TEACHER + " = ?, "
                    + pT.UPDATED_AT + " = ? "
                    + " WHERE " + pT.COLUMN_ID + " = ? ;";

            PreparedStatement pStmt = super.connect2DB.getConn().prepareStatement(sql);
            int i = 1;
            pStmt.setString(i++, e.getFirstName());
            pStmt.setString(i++, e.getLastName());
            pStmt.setDate(i++, java.sql.Date.valueOf(e.getDateOfBirth()));
            pStmt.setString(i++, e.getEmail());
            pStmt.setString(i++, e.getMobile());
            pStmt.setString(i++, e.getAddress());
            pStmt.setString(i++, e.getPostalCode());
            pStmt.setString(i++, e.getCity());
            pStmt.setString(i++, e.getCountry());
            pStmt.setBoolean(i++, e.getIsJuryMember());
            pStmt.setBoolean(i++, e.getIsTeacher());
            pStmt.setTimestamp(i++, Timestamp.valueOf(e.getUpdatedAt()));
            pStmt.setInt(i++, e.getId());

            pStmt.executeUpdate();
            pStmt.close();
        } catch (SQLException ex) {
            System.getLogger(PersonDAO.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }

}
