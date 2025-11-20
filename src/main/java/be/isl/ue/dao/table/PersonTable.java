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
public class PersonTable extends AbstractTable<Person> {

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

    public String getFIRSTNAME() {
        return getAliasColumn(FIRSTNAME);
    }

    public String getLASTNAME() {
        return getAliasColumn(LASTNAME);
    }

    public String getDATE_OF_BIRTH() {
        return getAliasColumn(DATE_OF_BIRTH);
    }

    public String getEMAIL() {
        return getAliasColumn(EMAIL);
    }

    public String getMOBILE() {
        return getAliasColumn(MOBILE);
    }

    public String getADDRESS() {
        return getAliasColumn(ADDRESS);
    }

    public String getPOSTAL_CODE() {
        return getAliasColumn(POSTAL_CODE);
    }

    public String getCITY() {
        return getAliasColumn(CITY);
    }

    public String getCOUNTRY() {
        return getAliasColumn(COUNTRY);
    }

    public String getIS_JURY_MEMBER() {
        return getAliasColumn(IS_JURY_MEMBER);
    }

    public String getIS_TEACHER() {
        return getAliasColumn(IS_TEACHER);
    }

    public String getFIRSTNAMEWithAlias() {
        return getAliasColumnWithAlias(FIRSTNAME);
    }

    public String getLASTNAMEWithAlias() {
        return getAliasColumnWithAlias(LASTNAME);
    }

    public String getDATE_OF_BIRTHWithAlias() {
        return getAliasColumnWithAlias(DATE_OF_BIRTH);
    }

    public String getEMAILWithAlias() {
        return getAliasColumnWithAlias(EMAIL);
    }

    public String getMOBILEWithAlias() {
        return getAliasColumnWithAlias(MOBILE);
    }

    public String getADDRESSWithAlias() {
        return getAliasColumnWithAlias(ADDRESS);
    }

    public String getPOSTAL_CODEWithAlias() {
        return getAliasColumnWithAlias(POSTAL_CODE);
    }

    public String getCITYWithAlias() {
        return getAliasColumnWithAlias(CITY);
    }

    public String getCOUNTRYWithAlias() {
        return getAliasColumnWithAlias(COUNTRY);
    }

    public String getIS_JURY_MEMBERWithAlias() {
        return getAliasColumnWithAlias(IS_JURY_MEMBER);
    }

    public String getIS_TEACHERWithAlias() {
        return getAliasColumnWithAlias(IS_TEACHER);
    }

    public PersonTable() {
        super("person", "person_id", "p");
    }
}
