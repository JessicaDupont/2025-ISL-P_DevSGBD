/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package be.isl.ue.dao;

import be.isl.ue.entity.AbstractEntity;
import be.isl.ue.ui.viewmodel.ViewModel;
import java.util.List;

/**
 *
 * @author jessi
 */
public interface InterfaceDAO<E extends AbstractEntity, V extends ViewModel> {
    List<E> load();
    List<E> load(V vm);
    List<E> getList();
    void save(E e);
    void delete(E e);
}
