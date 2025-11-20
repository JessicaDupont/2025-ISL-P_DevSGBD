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
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author jessi
 */
public class PersonDAO extends AbstractDAO<Person, PersonMapper, PersonViewModel> {

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
            //pStmt.setTimestamp(i++, Timestamp.valueOf(e.getInsertedAt()));
            //pStmt.setTimestamp(i++, Timestamp.valueOf(e.getUpdatedAt()));

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
            String sql = "SELECT " + t.getCOLUMN_IDWithAlias() + ", "
                    + t.getFIRSTNAMEWithAlias() + ", " + t.getLASTNAMEWithAlias() + ", " + t.getDATE_OF_BIRTHWithAlias() + ", "
                    + t.getEMAILWithAlias() + ", " + t.getMOBILEWithAlias() + ", "
                    + t.getADDRESSWithAlias() + ", " + t.getPOSTAL_CODEWithAlias() + ", " + t.getCITYWithAlias() + ", " + t.getCOUNTRYWithAlias() + ", "
                    + t.getIS_JURY_MEMBERWithAlias() + ", " + t.getIS_TEACHERWithAlias() + ", "
                    + t.getINSERTED_ATWithAlias() + ", " + t.getUPDATED_ATWithAlias()
                    + " FROM " + t.TABLE_NAME + " AS " + t.TABLE_ALIAS;
            System.out.println("SQL "+sql);
            if (vm != null) {
                String where = " WHERE 1=1 ";
                if (isNotNullOrEmpty(vm.getFirstName())) {
                    where += " AND " + t.getFIRSTNAME() + " like ? ";
                }
                if (isNotNullOrEmpty(vm.getLastName())) {
                    where += " AND " + t.getLASTNAME() + " like ? ";
                }
                if (isNotNullOrEmpty(vm.getCity())) {
                    where += " AND " + t.getCITY() + " like ? ";
                }
                if (isNotNullOrEmpty(vm.getDateOfBirth())) {
                    where += " AND " + t.getDATE_OF_BIRTH() + " = ? ";
                }
                if (isNotNullOrEmpty(vm.getEmail())) {
                    where += " AND " + t.getEMAIL() + " like ? ";
                }
                sql += where + "ORDER BY " + t.getLASTNAME() + ", " + t.getFIRSTNAME() + ";";
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
            //System.out.println("RS : "+rs);
            super.getList().clear();
            while (rs.next()) {
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
            PersonTable t = mapper.getTable();
            e.setUpdatedAt(LocalDateTime.now());
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
