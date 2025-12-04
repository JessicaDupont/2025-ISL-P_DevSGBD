/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package be.isl.ue.test;

import be.isl.ue.dao.UEDAO;
import be.isl.ue.entity.Section;
import be.isl.ue.entity.UE;
import be.isl.ue.ui.viewmodel.UEViewModel;
import java.util.List;

/**
 *
 * @author jessi
 */
public class UEDAOTest {
    private UE ueTest;
    private UEDAO ueDAO;
    private UEViewModel ueVM;
    private List<UE> ues;

    public UEDAOTest() {
        ueDAO = new UEDAO();
        
        SectionDAOTest sDAO = new SectionDAOTest();
        sDAO.testCreate();
        Section s = sDAO.getSection();
        ueTest = new UE("DevSGBD","Projet de developpement SGBD", "bla bla blou",80,true,s);
        
        ueVM = new UEViewModel();
        ueVM.setName(ueTest.getName());
        ueVM.setSection(s.getName());
    }

    public UE getUE() {
        return ueTest;
    }

    public void setUE(UE ue) {
        this.ueTest = ue;
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
        ues = ueDAO.load();
        display(ues, "Load");
    }
    public void testGetList() {
        ues = ueDAO.getList();
        display(ues, "Get List");
    }
    public void testCreate() {
        ueDAO.save(ueTest);
        ues = ueDAO.load(ueVM);
        display(ues, "Create"+ueTest.getId());
    }
    public void testRead() {
        ues = ueDAO.load(ueVM);
        display(ues, "Read");
    }
    public void testUpdate() {
        ueTest.setDescription("Super SGBD");
        ueDAO.save(ueTest);
        ues = ueDAO.load(ueVM);
        display(ues, "Update"+ueTest.getId());
    }
    public void testDelete() {
        ueDAO.delete(ueTest);
        ues = ueDAO.load(ueVM);
        display(ues, "Delete"+ueTest.getId());
    }

    private void display(List<UE> ues, String title) {
        System.out.println("*** UE : " + title + " : " + ues.size() + " ***");
        for (UE ue : ues) {
            System.out.println(ue.toString());
        }
    }
}
