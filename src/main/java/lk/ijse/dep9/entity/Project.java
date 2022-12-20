package lk.ijse.dep9.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@ToString(exclude = "taskSet")
@EqualsAndHashCode(exclude = "taskSet")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Project implements SuperEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String name;
    @ManyToOne
    @JoinColumn(name = "username",referencedColumnName = "username")
    private User user;
    @Setter(AccessLevel.NONE)
    @OneToMany(mappedBy = "project", fetch = FetchType.EAGER, cascade = {CascadeType.REMOVE})//when remove
    // the project remove the related task list automatically, if use all without using remove then all the
    // things we done will happen to all the tasks cascaded with the projects
    private Set<Task> taskSet = new HashSet<>();

    public Project(String name, User user) {
        this.name = name;
        this.user = user;
    }
}
