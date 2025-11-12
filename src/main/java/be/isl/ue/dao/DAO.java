/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package be.isl.ue.dao;

import be.isl.ue.entity.Entity;
import be.isl.ue.ui.viewmodel.ViewModel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author jessi
 */
public abstract class DAO<E extends Entity> {

    protected Connect2DB connect2DB;
    private ArrayList<E> entityList = new ArrayList();

    public DAO() {
        connect2DB = new Connect2DB();
    }

    public List<E> load(){
        return this.load(null);
    }

    public abstract List<E> load(ViewModel vm);

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

    protected abstract void select(E e);

    protected abstract void update(E e);

    public abstract void delete(E e);

}
