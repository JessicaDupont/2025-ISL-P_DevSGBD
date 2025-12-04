package be.isl.ue.test;

import be.isl.ue.dao.SectionDAO;
import be.isl.ue.entity.Person;
import be.isl.ue.entity.Section;
import be.isl.ue.ui.viewmodel.SectionViewModel;
import java.util.List;

/**
 *
 * @author jessi
 */
public class SectionDAOTest {
    private Section sTest;
    private SectionDAO sDAO;
    private SectionViewModel sVM;
    private List<Section> sections;

    public SectionDAOTest() {
        sDAO = new SectionDAO();
        
        PersonDAOTest pDAO = new PersonDAOTest();
        pDAO.testCreate();
        Person coordinator = pDAO.getPerson();
        sTest = new Section("Informatique de gestion","bla bla bla",coordinator);
        
        sVM = new SectionViewModel();
        sVM.setName(sTest.getName());
        sVM.setCoordinatorLastName(sTest.getCoordinator().getLastName());
    }

    public Section getSection() {
        return sTest;
    }

    public void setSection(Section s) {
        this.sTest = s;
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
        sections = sDAO.load();
        display(sections, "Load");
    }
    public void testGetList() {
        sections = sDAO.getList();
        display(sections, "Get List");
    }
    public void testCreate() {
        sDAO.save(sTest);
        sections = sDAO.load();
        display(sections, "Create"+sTest.getId());
    }
    public void testRead() {
        sections = sDAO.load(sVM);
        display(sections, "Read");
    }
    public void testUpdate() {
        sTest.setName("Informatique de gestion : Developpeur d'application");
        sDAO.save(sTest);
        sections = sDAO.load(sVM);
        display(sections, "Update"+sTest.getId());
    }
    public void testDelete() {
        sDAO.delete(sTest);
        sections = sDAO.load(sVM);
        display(sections, "Delete"+sTest.getId());
    }

    private void display(List<Section> sections, String title) {
        System.out.println("*** SECTION : " + title + " : " + sections.size() + " ***");
        for (Section s : sections) {
            System.out.println(s.toString());
        }
    }
}
