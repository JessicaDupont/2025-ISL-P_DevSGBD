/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package be.isl.ue.dao.table;

import be.isl.ue.entity.Capacity;

/**
 *
 * @author jessi
 */
public class CapacityTable extends AbstractTable<Capacity> {

    public final String NAME = "name";
    public final String DESCRIPTION = "description";
    public final String IS_THRESHOLD_OF_SUCCESS = "is_threshold_of_success";
    public final String FK_UE = "ue_id";

    public CapacityTable() {
        super("capacity", "capacity_id", "c");
    }

    @Override
    public String getAllAliasAsColumns() {
        return super.getAllAliasAsColumns() + ", "
                +getAliasAsColumn(this.NAME)+", "
                +getAliasAsColumn(this.DESCRIPTION)+", "
                +getAliasAsColumn(this.IS_THRESHOLD_OF_SUCCESS)+", "
                +getAliasAsColumn(this.FK_UE);
    }

}
