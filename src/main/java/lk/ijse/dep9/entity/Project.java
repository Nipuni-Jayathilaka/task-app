package lk.ijse.dep9.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

//@ToString(exclude = "taskSet")
//@EqualsAndHashCode(exclude = "taskSet")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Project implements SuperEntity{
    private int id;
    private String name;
    private String username;
//    @Setter(AccessLevel.NONE)
////    @OneToMany(mappedBy = "project", fetch = FetchType.EAGER, cascade = {CascadeType.REMOVE})//when remove
////    // the project remove the related task list automatically, if use all without using remove then all the
//    // things we done will happen to all the tasks cascaded with the projects
//    private Set<Task> taskSet = new HashSet<>();

    public Project(String name, String username) {
        this.name = name;
        this.username = username;
    }
}
