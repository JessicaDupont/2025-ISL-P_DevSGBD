/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package be.isl.ue.test;

import be.isl.ue.dao.PersonDAO;
import be.isl.ue.entity.Person;
import be.isl.ue.ui.viewmodel.PersonViewModel;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author jessi
 */
public class PersonDAOTest {

    private Person pTest;
    private PersonDAO pDAO;
    private PersonViewModel pVM;
    private List<Person> persons;

    public PersonDAOTest() {
        pDAO = new PersonDAO();
        
        pTest = new Person("Jessica", "Dupont", LocalDate.of(1990, 12, 03),
                "j.dupont@test.net", "0123/456789",
                "rue de la source 5/1", "4570", "Marchin", "Belgique",
                false, false);
        
        pVM = new PersonViewModel();
        pVM.setFirstName(pTest.getFirstName());
        pVM.setLastName(pTest.getLastName());
        pVM.setCity(pTest.getCity());
        pVM.setEmail(pTest.getEmail());
        pVM.setDateOfBirth(pTest.getDateOfBirth().toString());
    }

    public Person getPerson() {
        return pTest;
    }

    public void setPerson(Person p) {
        this.pTest = p;
    }

    public void tests() {
        testLoad();
        testGetList();
        testRead();
        testCreate();
        testUpdate();
        testDelete();
    }

    public void testLoad() {
        persons = pDAO.load();
        display(persons, "Load");
    }

    public void testGetList() {
        persons = pDAO.getList();
        display(persons, "Get List");
    }

    public void testRead() {
        persons = pDAO.load(pVM);
        display(persons, "Read");
    }

    public void testCreate() {
        pDAO.save(pTest);
        persons = pDAO.load(pVM);
        display(persons, "Create"+pTest.getId());
    }

    public void testUpdate() {
        pTest.setIsTeacher(true);
        pDAO.save(pTest);
        persons = pDAO.load(pVM);
        display(persons, "Update"+pTest.getId());
    }

    public void testDelete() {
        pDAO.delete(pTest);
        persons = pDAO.load(pVM);
        display(persons, "Delete"+pTest.getId());
    }

    private void display(List<Person> persons, String title) {
        System.out.println("*** PERSON : " + title + " : " + persons.size() + " ***");
        for (Person p : persons) {
            System.out.println(p.toString());
        }
    }

}
