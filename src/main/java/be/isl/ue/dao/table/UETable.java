/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package be.isl.ue.dao.table;

import be.isl.ue.entity.UE;

/**
 *
 * @author jessi
 */
public class UETable extends AbstractTable<UE> {

    public final String CODE = "code";
    public final String NAME = "name";
    public final String DESCRIPTION = "description";
    public final String NUMBER_OF_PERIODS = "num_of_periods";
    public final String IS_DECISIVE = "is_decisive";
    public final String FK_SECTION = "section_id";

    public UETable() {
        super("ue", "ue_id", "ue");
    }

    @Override
    public String getAllAliasAsColumns() {
        return super.getAllAliasAsColumns() + ", "
                + getAliasAsColumn(this.CODE) + ", "
                + getAliasAsColumn(this.NAME) + ", "
                + getAliasAsColumn(this.DESCRIPTION) + ", "
                + getAliasAsColumn(this.NUMBER_OF_PERIODS) + ", "
                + getAliasAsColumn(this.IS_DECISIVE);

    }
}
