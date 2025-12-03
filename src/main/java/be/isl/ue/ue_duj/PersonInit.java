/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package be.isl.ue.ue_duj;

import be.isl.ue.dao.PersonDAO;
import be.isl.ue.entity.Person;

/**
 *
 * @author jessi
 */
public class PersonInit extends AbstractInit<Person> {

    private static PersonDAO pDAO = new PersonDAO();

    public PersonInit() {
        super();
    }

    @Override
    public void init() {
        super.persons = pDAO.load();
    }

    @Override
    public void saveAll() {
        for(Person p : super.persons){
            pDAO.save(p);
        }
    }
}
