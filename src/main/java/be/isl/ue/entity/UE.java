/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package be.isl.ue.entity;

import java.time.LocalDateTime;
import java.util.Comparator;

/**
 *
 * @author jessi
 */
public class UE extends Entity<UE> {

    private String code;
    private String name;
    private String description;
    private Integer numberOdPeriods;
    private Boolean isDecisive;
    private Section section;

    public UE(String code, String name, String description, Integer numberOdPeriods, Boolean isDecisive, Section section) {
        this.code = code;
        this.name = name;
        this.description = description;
        this.numberOdPeriods = numberOdPeriods;
        this.isDecisive = isDecisive;
        this.section = section;
    }

    public UE(Integer id, String code, String name, String description, Integer numberOdPeriods, Boolean isDecisive, Section section, LocalDateTime insertedAt, LocalDateTime updatedAt) {
        super(id, insertedAt, updatedAt);
        this.code = code;
        this.name = name;
        this.description = description;
        this.numberOdPeriods = numberOdPeriods;
        this.isDecisive = isDecisive;
        this.section = section;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getNumberOdPeriods() {
        return numberOdPeriods;
    }

    public void setNumberOdPeriods(Integer numberOdPeriods) {
        this.numberOdPeriods = numberOdPeriods;
    }

    public Boolean getIsDecisive() {
        return isDecisive;
    }

    public void setIsDecisive(Boolean isDecisive) {
        this.isDecisive = isDecisive;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    @Override
    public int compareTo(UE o) {
        if (o == null) {
            return 1;
        }
        int idComparison = super.compareTo(o);
        if (idComparison != 0) {
            return idComparison;
        }
        return Comparator
                .comparing(UE::getName, Comparator.nullsLast(String::compareTo))
                .thenComparing(UE::getCode, Comparator.nullsLast(String::compareTo))
                .thenComparing(UE::getDescription, Comparator.nullsLast(String::compareTo))
                .thenComparing(UE::getSection, Comparator.nullsLast(Comparator.naturalOrder()))
                .compare(this, o);
    }

}
