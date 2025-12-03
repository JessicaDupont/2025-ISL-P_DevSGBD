/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package be.isl.ue.ue_duj;

import be.isl.ue.entity.*;
import java.util.List;

/**
 *
 * @author jessi
 */
public class UE_DUJ {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        
        //Person
        PersonInit p = new PersonInit();
        List<Person> pL = p.getPersons();
        System.out.println("PERSONS : "+pL.size());
        for(Person i : pL){
            System.out.println(i.toString());
        }
        
        //Section
        SectionInit s = new SectionInit();
        List<Section> sL = s.getSections();
        System.out.println("SECTIONS : "+sL.size());
        for(Section i : sL){
            System.out.println(i.toString());
        }
        
        //UE
        UEInit ue = new UEInit();
        List<UE> ueL = ue.getUes();
        System.out.println("UE : "+ueL.size());
        for(UE i : ueL){
            System.out.println(i.toString());
        }
                
    }

}
