/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package be.isl.ue.ue_duj;

import be.isl.ue.test.CapacityDAOTest;
import be.isl.ue.test.PersonDAOTest;
import be.isl.ue.test.SectionDAOTest;
import be.isl.ue.test.UEDAOTest;

/**
 *
 * @author jessi
 */
public class UE_DUJ {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        
        //Person
        PersonDAOTest pTest = new PersonDAOTest();
        //pTest.tests();
        
        //Section
        SectionDAOTest sTest = new SectionDAOTest();
        //sTest.tests();
        
        //UE
        UEDAOTest ueTest = new UEDAOTest();
        //ueTest.tests();
        
        //Capacity
        CapacityDAOTest cTest = new CapacityDAOTest();
        //cTest.tests();
                
    }

}
