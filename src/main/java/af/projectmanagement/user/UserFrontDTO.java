package af.projectmanagement.user;

import af.projectmanagement.project.ProjectDTO;

import java.util.Set;

public class UserFrontDTO {
    private long id;
    private String name;
    private String surname;
    private String role;
    Set<ProjectDTO> projects = null;

    public UserFrontDTO(long id, String name, String surname, String role, Set<ProjectDTO> projects) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.role = role;
        this.projects = projects;
    }

    public UserFrontDTO(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.surname = user.getSurname();
        this.role = user.getRole();
        this.projects = user.getProjects();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Set<ProjectDTO> getProjects() {
        return projects;
    }

    public void setProjects(Set<ProjectDTO> projects) {
        this.projects = projects;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", role='" + role + '\'' +
                ", projects=" + projects +
                '}';
    }
}
