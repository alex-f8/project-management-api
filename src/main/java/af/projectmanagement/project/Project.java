package af.projectmanagement.project;

import af.projectmanagement.user.UserForProjectDTO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.time.LocalDate;
import java.util.*;

@Entity(name = "project")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "date_start")
    private LocalDate dateStart;

    @Column(name = "date_finish")
    private LocalDate dateFinish;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "project_usr",
            joinColumns = {@JoinColumn(name = "project_id")},
            inverseJoinColumns = {@JoinColumn(name = "usr_id")})
    private List<UserForProjectDTO> users = new ArrayList<>();

    public Project() {
    }

    public Project(String name, LocalDate dateStart, LocalDate dateFinish) {
        this.name = name;
        this.dateStart = dateStart;
        this.dateFinish = dateFinish;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateStart() {
        return dateStart;
    }

    public void setDateStart(LocalDate dateStart) {
        this.dateStart = dateStart;
    }

    public LocalDate getDateFinish() {
        return dateFinish;
    }

    public void setDateFinish(LocalDate dateFinish) {
        this.dateFinish = dateFinish;
    }

    public List<UserForProjectDTO> getUsers() {
        return users;
    }

    public void setUsers(List<UserForProjectDTO> users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return id == project.id && Objects.equals(name, project.name) && Objects.equals(dateStart, project.dateStart) && Objects.equals(dateFinish, project.dateFinish);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, dateStart, dateFinish);
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dateStart=" + dateStart +
                ", dateFinish=" + dateFinish +
                ", users=" + users +
                '}';
    }
}
