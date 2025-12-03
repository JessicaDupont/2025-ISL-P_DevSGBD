/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package be.isl.ue.ue_duj;

import be.isl.ue.dao.SectionDAO;
import be.isl.ue.entity.Person;
import be.isl.ue.entity.Section;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jessi
 */
public class SectionInit extends AbstractInit<Section> {

    private static SectionDAO sDAO = new SectionDAO();

    public SectionInit() {
        super();
    }

    @Override
    public void init() {
        super.sections = sDAO.load();
    }

    @Override
    public void saveAll() {
        for(Section s : super.sections){
            sDAO.save(s);
        }
    }
}
