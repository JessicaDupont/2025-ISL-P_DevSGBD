/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package be.isl.ue.ue_duj;

import be.isl.ue.dao.UeDAO;
import be.isl.ue.entity.UE;

/**
 *
 * @author jessi
 */
public class UEInit extends AbstractInit<UE> {

    private static UeDAO ueDAO = new UeDAO();

    public UEInit() {
        super();
    }

    @Override
    public void init() {
        super.ues = ueDAO.load();
    }

    @Override
    public void saveAll() {
        for(UE ue : super.ues){
            ueDAO.save(ue);
        }
    }

}
