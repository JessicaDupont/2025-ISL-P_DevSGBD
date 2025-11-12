/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package be.isl.ue.dao;

import be.isl.ue.dao.mapper.PersonMapper;
import be.isl.ue.dao.table.PersonTable;
import be.isl.ue.entity.Person;
import be.isl.ue.ui.viewmodel.PersonViewModel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

/**
 *
 * @author jessi
 */
public class PersonDAO extends DAO<Person, PersonMapper, PersonViewModel> {

    public PersonDAO() {
        super(new PersonMapper(new PersonTable()));
    }

    @Override
    public List<Person> load(PersonViewModel vm) {
        select(vm);
        return super.getList();
    }

    @Override
    protected void insert(Person e) {
        try {
            PersonTable t = mapper.getTable();
            String sql = "INSERT INTO " + t.TABLE_NAME + "("
                    + t.FIRSTNAME + ", " + t.LASTNAME + "," + t.DATE_OF_BIRTH + ", "
                    + t.EMAIL + ", " + t.MOBILE + ", "
                    + t.ADDRESS + ", " + t.POSTAL_CODE + ", " + t.CITY + ", " + t.COUNTRY + ", "
                    + t.IS_JURY_MEMBER + ", " + t.IS_TEACHER + ", "
                    + t.INSERTED_AT + ", " + t.UPDATED_AT
                    + ") VALUES ("
                    + "?, ?, ?, "
                    + "?, ?, "
                    + "?, ?, ?, ?, "
                    + "?, ?, "
                    + "?, ?"
                    + ");";

            PreparedStatement pStmt = super.connect2DB.getConn().prepareStatement(sql);
            int i = 0;
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
            pStmt.setTimestamp(i++, Timestamp.valueOf(e.getInsertedAt()));
            pStmt.setTimestamp(i++, Timestamp.valueOf(e.getUpdatedAt()));

            pStmt.executeUpdate();
            pStmt.close();
            super.updateIdAfterInsert(e, t.TABLE_NAME + "_" + t.COLUMN_ID + "_seq");
        } catch (SQLException ex) {
            System.getLogger(PersonDAO.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }

    @Override
    protected void select(PersonViewModel vm) {
        try {
            PersonTable t = mapper.getTable();
            String sql = "SELECT " + t.COLUMN_ID + ", "
                    + t.FIRSTNAME + ", " + t.LASTNAME + "," + t.DATE_OF_BIRTH + ", "
                    + t.EMAIL + ", " + t.MOBILE + ", "
                    + t.ADDRESS + ", " + t.POSTAL_CODE + ", " + t.CITY + ", " + t.COUNTRY + ", "
                    + t.IS_JURY_MEMBER + ", " + t.IS_TEACHER
                    + t.INSERTED_AT + ", " + t.UPDATED_AT
                    + " FROM " + t.TABLE_NAME;
            if (vm != null) {
                String where = " WHERE 1=1 ";
                if (vm.getFirstName().isEmpty()) {
                    where += " AND " + t.FIRSTNAME + " like ? ";
                }
                if (vm.getLastName().isEmpty()) {
                    where += " AND " + t.LASTNAME + " like ? ";
                }
                if (vm.getCity().isEmpty()) {
                    where += " AND " + t.CITY + " like ? ";
                }
                if (vm.getDateOfBirth().isEmpty()) {
                    where += " AND " + t.DATE_OF_BIRTH + " = ? ";
                }
                if (vm.getEmail().isEmpty()) {
                    where += " AND " + t.EMAIL + " like ? ";
                }
                sql += where + "ORDER BY " + t.LASTNAME + ", " + t.FIRSTNAME + ";";
            }

            PreparedStatement stmt = super.connect2DB.getConn().prepareStatement(sql);
            if (vm != null) {
                int i = 0;
                if (vm.getFirstName().isEmpty()) {
                    stmt.setString(i++, "%" + vm.getFirstName() + "%");
                }
                if (vm.getLastName().isEmpty()) {
                    stmt.setString(i++, "%" + vm.getLastName() + "%");
                }
                if (vm.getCity().isEmpty()) {
                    stmt.setString(i++, "%" + vm.getCity() + "%");
                }
                if (vm.getDateOfBirth().isEmpty()) {
                    stmt.setDate(i++, java.sql.Date.valueOf(vm.getDateOfBirth()));
                }
                if (vm.getEmail().isEmpty()) {
                    stmt.setString(i++, "%" + vm.getEmail() + "%");
                }
            }

            ResultSet rs = stmt.executeQuery();
            super.getList().clear();
            while (rs.next()) {
                super.getList().add(mapper.map(rs));
            }

            stmt.close();
        } catch (SQLException ex) {
            System.getLogger(PersonDAO.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }

    @Override
    protected void update(Person e) {
        try {
            PersonTable t = mapper.getTable();
            String sql = "UPDATE " + t.TABLE_NAME + " SET "
                    + t.FIRSTNAME + " = ?, "
                    + t.LASTNAME + " = ?, "
                    + t.DATE_OF_BIRTH + " = ?, "
                    + t.EMAIL + " = ?, "
                    + t.MOBILE + " = ?, "
                    + t.ADDRESS + " = ?, "
                    + t.POSTAL_CODE + " = ?, "
                    + t.CITY + " = ?, "
                    + t.COUNTRY + " = ?, "
                    + t.IS_JURY_MEMBER + " = ?, "
                    + t.IS_TEACHER + " = ?, "
                    + t.UPDATED_AT + " = ? "
                    + " WHERE " + t.COLUMN_ID + " = ? ;";

            PreparedStatement pStmt = super.connect2DB.getConn().prepareStatement(sql);
            int i = 0;
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
