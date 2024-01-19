package org.amos;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.panache.common.Parameters;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Parameter;

import java.util.List;

@Entity
public class Person extends PanacheEntity {

    private String firstName;
    private String lastName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public static List<Person> getPersonByFirstName(String firstName){
        return Person.find("firstName",firstName).list();
    }

    public static List<Person> getPersonByFirstNameAndLastName(String firstName, String lastName){
        return Person.find("firstName = :fn and lastName = :ln",
                Parameters.with("fn",firstName)
                        .and("ln",lastName)
                        .map()
                        ).list();
    }
}
