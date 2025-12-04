/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package be.isl.ue.test;

import be.isl.ue.dao.CapacityDAO;
import be.isl.ue.entity.Capacity;
import be.isl.ue.entity.UE;
import be.isl.ue.ui.viewmodel.CapacityViewModel;
import java.util.List;

/**
 *
 * @author jessi
 */
public class CapacityDAOTest {
    private Capacity cTest;
    private CapacityDAO cDAO;
    private CapacityViewModel cVM;
    private List<Capacity> capacities;

    public CapacityDAOTest() {
        cDAO = new CapacityDAO();
        
        UEDAOTest ueDAO = new UEDAOTest();
        ueDAO.testCreate();
        UE ue = ueDAO.getUE();
        cTest = new Capacity("partie 1", "bim bam boum", true, ue);
        
        cVM = new CapacityViewModel();
    }
    public Capacity getCapacity() {
        return cTest;
    }

    public void setCapacity(Capacity c) {
        this.cTest = c;
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
        capacities = cDAO.load();
        display(capacities, "Load");
    }
    public void testGetList() {
        capacities = cDAO.getList();
        display(capacities, "Get List");
    }
    public void testCreate() {
        cDAO.save(cTest);
        capacities = cDAO.load(cVM);
        display(capacities, "Create"+cTest.getId());
    }
    public void testRead() {
        capacities = cDAO.load(cVM);
        display(capacities, "Read");
    }
    public void testUpdate() {
        cTest.setDescription("Super SGBD");
        cDAO.save(cTest);
        capacities = cDAO.load(cVM);
        display(capacities, "Update"+cTest.getId());
    }
    public void testDelete() {
        cDAO.delete(cTest);
        capacities = cDAO.load(cVM);
        display(capacities, "Delete"+cTest.getId());
    }

    private void display(List<Capacity> capacities, String title) {
        System.out.println("*** Capacity : " + title + " : " + capacities.size() + " ***");
        for (Capacity c : capacities) {
            System.out.println(c.toString());
        }
    }

}
