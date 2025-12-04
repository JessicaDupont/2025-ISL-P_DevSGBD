package be.isl.ue.entity;

import java.time.LocalDateTime;
import java.util.Comparator;

/**
 *
 * @author jessi
 */
public class Section extends AbstractEntity<Section> {

    private String name;
    private String description;
    private Person coordinator;

    public Section(String name, String description, Person coordinator) {
        this.name = name;
        this.description = description;
        this.coordinator = coordinator;
    }

    public Section(Integer id, String name, String description, Person coordinator, LocalDateTime insertedAt, LocalDateTime updatedAt) {
        super(id, insertedAt, updatedAt);
        this.name = name;
        this.description = description;
        this.coordinator = coordinator;
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

    public Person getCoordinator() {
        return coordinator;
    }

    public void setCoordinator(Person coordinator) {
        this.coordinator = coordinator;
    }

    @Override
    public int compareTo(Section o) {
        if (o == null) {
            return 1;
        }
int idComparison = super.compareTo(o);
        if (idComparison != 0) {
            return idComparison;
        }
        int nameComparison = Comparator
                .nullsLast(String::compareTo)
                .compare(this.name, o.name);

        if (nameComparison != 0) {
            return nameComparison;
        }

        if (this.coordinator == null && o.coordinator == null) {
            return 0;
        }
        if (this.coordinator == null) {
            return 1;
        }
        if (o.coordinator == null) {
            return -1;
        }

        return Comparator
                .nullsLast(String::compareTo)
                .compare(this.coordinator.getLastName(), o.coordinator.getLastName());
    }

    @Override
    public String toString() {
        return "Section{" +super.toString() + " name=" + name + ", description=" + description + ", coordinator=" + coordinator.toString() + '}';
    }

    
}
