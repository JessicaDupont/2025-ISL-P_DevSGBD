/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package be.isl.ue.dao;

import be.isl.ue.dao.mapper.*;
import be.isl.ue.dao.table.*;
import be.isl.ue.entity.AbstractEntity;
import be.isl.ue.entity.Person;
import be.isl.ue.ui.viewmodel.ViewModel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jessi
 */
public abstract class AbstractDAO<
        E extends AbstractEntity, M extends AbstractMapper, V extends ViewModel> {

    protected Connect2DB connect2DB;
    protected ArrayList<E> entityList = new ArrayList();
    protected M mapper;
    protected PersonTable pT = new PersonTable();
    protected PersonMapper pM = new PersonMapper(pT);
    protected SectionTable sT = new SectionTable();
    protected SectionMapper sM = new SectionMapper(sT, pM);
    protected CapacityTable cT = new CapacityTable();
    protected CapacityMapper cM = new CapacityMapper(cT);
    protected UETable ueT = new UETable();
    protected UEMapper ueM = new UEMapper(ueT, sM, cM);

    public AbstractDAO() {
        connect2DB = new Connect2DB();
    }

    public List<E> load() {
        return this.load(null);
    }

    public List<E> load(V vm) {
        select(vm);
        return getList();
    }

    public List<E> getList() {
        return entityList;
    }

    public void save(E e) {
        if (e != null) {
            if (e.getId() != null) {
                update(e);
            } else {
                insert(e);
            }
            Collections.sort(entityList);
        }
    }

    protected abstract void insert(E e);

    protected abstract void select(V vm);

    protected abstract void update(E e);

    public void delete(E e) {
        if (e.getId() != null) {
            try {
                String sql = "DELETE FROM " + mapper.getTable().TABLE_NAME
                        + " WHERE " + mapper.getTable().COLUMN_ID + " = ? ;";
                PreparedStatement stmt = connect2DB.getConn().prepareStatement(sql);
                stmt.setInt(1, e.getId());
                stmt.executeUpdate();
                stmt.close();
                for (E entity : entityList) {
                    if (Objects.equals(entity.getId(), e.getId())) {
                        entityList.remove(entity);
                        break;
                    }
                }
                stmt.close();
            } catch (SQLException ex) {
                Logger.getLogger(AbstractDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void updateIdAfterInsert(E e, String sequence) {
        try {
            String sql = "SELECT currval('" + sequence + "') AS id";
            Statement stmt = this.connect2DB.getConn().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                e.setId(rs.getInt("id"));
            }
            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(AbstractDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected boolean isNotNullOrEmpty(String s) {
        return s != null && !s.isEmpty();
    }

    protected boolean isNotNullOrEmpty(Integer i) {
        return i != null && i != 0;
    }

    protected boolean isNotNullOrEmpty(LocalDate d) {
        return d != null && d != LocalDate.of(0, 0, 0);
    }

    protected boolean isNotNullOrEmpty(LocalDateTime d) {
        return d != null && d != LocalDateTime.of(0, 0, 0, 0, 0);
    }

    protected String addWhereInSQL(String vmGet, String columnName) {
        String result = "";
        if (isNotNullOrEmpty(vmGet)) {
            result = " AND " + columnName + " LIKE ? ";
        }
        return result;
    }
}
