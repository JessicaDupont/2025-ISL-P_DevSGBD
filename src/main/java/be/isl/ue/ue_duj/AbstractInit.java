/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package be.isl.ue.ue_duj;

import be.isl.ue.entity.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jessi
 * @param <E>
 */
public abstract class AbstractInit<E extends AbstractEntity> {
    protected static List<Person> persons;
    protected static List<Section> sections;
    protected static List<UE> ues;

    public AbstractInit() {
        init();
    }

    public static List<Person> getPersons() {
        return persons;
    }

    public static List<Section> getSections() {
        return sections;
    }

    public static List<UE> getUes() {
        return ues;
    }
    
    public abstract void init();
    public abstract void saveAll();
    
}
