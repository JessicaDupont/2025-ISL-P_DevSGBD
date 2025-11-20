/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package be.isl.ue.dao.table;

import be.isl.ue.entity.Section;

/**
 *
 * @author jessi
 */
public class SectionTable extends AbstractTable<Section> {

    public final String NAME = "name";
    public final String DESCRIPTION = "description";
    public final String FK_PERSON = "person_id";

    public SectionTable() {
        super("section", "section_id", "s");
    }

    public String getNAME() {
        return TABLE_ALIAS + "." + NAME;
    }

    public String getDESCRIPTION() {
        return TABLE_ALIAS + "." + DESCRIPTION;
    }

    public String getFK_PERSON() {
        return TABLE_ALIAS + "." + FK_PERSON;
    }

}
