/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package be.isl.ue.dao.table;

import be.isl.ue.entity.Person;

/**
 *
 * @author jessi
 */
public class PersonTable extends Table<Person> {
    public final String FIRSTNAME = "first_name";
    public final String LASTNAME = "last_name";
    public final String DATE_OF_BIRTH = "date_of_birth";
    public final String EMAIL = "email";
    public final String MOBILE = "mobile";
    public final String ADDRESS = "address";
    public final String POSTAL_CODE = "postal_code";
    public final String CITY = "city";
    public final String COUNTRY = "country";
    public final String IS_JURY_MEMBER = "is_jury_member";
    public final String IS_TEACHER = "is_teacher";
    
    public PersonTable() {
        super("person", "person_id");
    }   
}
