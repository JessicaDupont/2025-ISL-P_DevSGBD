/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package be.isl.ue.dao;

import be.isl.ue.dao.mapper.Mapper;
import be.isl.ue.dao.table.Table;
import be.isl.ue.entity.Entity;
import be.isl.ue.entity.Person;
import be.isl.ue.ui.viewmodel.ViewModel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
public abstract class DAO<
        E extends Entity, M extends Mapper, V extends ViewModel> {

    protected Connect2DB connect2DB;
    private ArrayList<E> entityList = new ArrayList();
    protected M mapper;

    public DAO(M mapper) {
        connect2DB = new Connect2DB();
        this.mapper = mapper;
    }

    public List<E> load() {
        return this.load(null);
    }

    public abstract List<E> load(V vm);

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
                        + " WHERE " + mapper.getTable().COLUMN_ID + " = " + e.getId() + ";";
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
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void updateIdAfterInsert(Person e, String sequence) {
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
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
