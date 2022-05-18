package af.projectmanagement.project;

import af.projectmanagement.forms.UserWorkDayUpdateForm;
import af.projectmanagement.forms.ProjectUserForm;
import af.projectmanagement.user.User;
import af.projectmanagement.user.UserForProjectDTORepository;
import af.projectmanagement.user.UserFrontDTO;
import af.projectmanagement.user.UserRepository;
import af.projectmanagement.workday.WorkDay;
import af.projectmanagement.workday.WorkDayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProjectDTORopository projectDTORopository;

    @Autowired
    UserForProjectDTORepository userForProjectDTORepository;

    @Autowired
    WorkDayRepository workDayRepository;

    @RequestMapping(value = {"/projectList"}, method = RequestMethod.GET)
    public List<Project> projectsList() {
        return projectRepository.findAll();
    }

    @RequestMapping(value = {"/getProject/{id}"})
    public Project getProject(@PathVariable long id) {
        Optional<Project> project = projectRepository.findById(id);
        return project.get();
    }

    @RequestMapping(value = {"/createProject"}, method = RequestMethod.POST)
    public Project createProject(@RequestBody Project project) {
        Project newProject = new Project(project.getName(), project.getDateStart(), project.getDateFinish());
        projectRepository.save(newProject);
        return newProject;
    }

    @RequestMapping(value = {"/updateProject"}, method = RequestMethod.POST)
    public String updateProject(@RequestBody Project project) {
        if (project.getName() != null || project.getDateStart() != null || project.getDateFinish() != null) {
            Optional<Project> projectUpdate = projectRepository.findById(project.getId());
            if (projectUpdate.isPresent()) {
                if (project.getName() != null)
                    projectUpdate.get().setName(project.getName());
                if (project.getDateStart() != null)
                    projectUpdate.get().setDateStart(project.getDateStart());
                if (project.getDateFinish() != null)
                    projectUpdate.get().setDateFinish(project.getDateFinish());
                projectRepository.save(projectUpdate.get());
                return "Project updated!";
            } else return "Project not found in Database!";
        } else return "No walid data!";
    }

    @RequestMapping(value = {"/deleteProject/{id}"}, method = RequestMethod.DELETE)
    public void deleteProject(@PathVariable Long id) {
        projectRepository.deleteById(id);
    }

    @RequestMapping(value = {"/usersList"}, method = RequestMethod.GET)
    public List<UserFrontDTO> userList() {
        List<UserFrontDTO> userDTOs = new ArrayList<>();
        userRepository.findAll().forEach((n) -> userDTOs.add(new UserFrontDTO(n)));
        return userDTOs;
    }

    @PostMapping("/createUser")
    public User createUser(@RequestBody User user) {
        return userRepository.save(new User(user.getName(), user.getSurname(), user.getPassword(), user.getRole()));
    }

    @PostMapping("/updateUser")
    public String updateUser(@RequestBody User user) {
        if (user.getName() != null || user.getSurname() != null || user.getRole() != null) {
            Optional<User> userUpdate = userRepository.findById(user.getId());
            if (userUpdate.isPresent()) {
                if (user.getName() != null)
                    userUpdate.get().setName(user.getName());
                if (user.getSurname() != null)
                    userUpdate.get().setSurname(user.getSurname());
                if (user.getRole() != null)
                    userUpdate.get().setRole(user.getRole());
                userRepository.save(userUpdate.get());
                return "User updated!";
            } else return "User not found in Database!";
        } else return "No walid data!";
    }

    @PostMapping("/updateUserPassword")
    public void updateUserPassword(@RequestBody User user) {
        Optional<User> userUpdate = userRepository.findById(user.getId());
        userUpdate.get().setPassword(user.getPassword());
        userRepository.save(userUpdate.get());
    }

    @DeleteMapping("/deleteUser/{id}")
    public void deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
    }

    @RequestMapping(value = {"/findUserByName"}, method = RequestMethod.GET)
    public List<User> findUserByName(@RequestParam String name) {
        return userRepository.findByName(name.strip());
    }

    @RequestMapping(value = {"/findUserBySurname"}, method = RequestMethod.GET)
    public List<User> findUserBySurname(@RequestParam String surname) {
        return userRepository.findByName(surname.strip());
    }

    @PostMapping("/addUserToProject")
    public void addUserToProject(@RequestBody ProjectUserForm puForm) {
        Optional<Project> project = projectRepository.findById(puForm.getPid());
        Optional<User> user = userRepository.findById(puForm.getUid());
        if (project.get() != null && user.get() != null) {
            if (puForm.getDays() != null) {
                for (String day : puForm.getDays()) {
                    workDayRepository.save(new WorkDay(puForm.getPid(), puForm.getUid(), LocalDate.parse(day)));
                }
            } else {
                workDayRepository.save(new WorkDay(puForm.getPid(), puForm.getUid(), null));
            }
        }
    }

    @RequestMapping(value = {"/deleteUserFromProject/{pid}/{uid}"}, method = RequestMethod.GET)
    public void deleteUserFromProject(@PathVariable long pid, @PathVariable long uid) {
        try {
            List<WorkDay> workDays = workDayRepository.findByPidAndUid(pid, uid);
            workDayRepository.deleteAll(workDays);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Here can add User in to Project with assignment Date(or Dates) or with out Date.
     * In future possible add empty field of Date with query to "/updateWorkDayForUser".
     * Also with "/updateWorkDayForUser" possible assign multiple new dates
     *
     * if combination of pid, uid and date isn't in base (is unique),
     * then postgresql will allow save this row, else ignored
     */
    @RequestMapping(value = {"/addWorkDaysForUser"}, method = RequestMethod.POST)
    public void addWorkDayForUser(@RequestBody ProjectUserForm projectUserForm) throws IOException {
        Optional<User> user = userRepository.findById(projectUserForm.getPid());
        Optional<ProjectDTO> projectDTO = projectDTORopository.findById(projectUserForm.getUid());
        if (projectDTO.get() != null && user.get() != null) {
            for (String day : projectUserForm.getDays()) {
                try {
                    workDayRepository.save(new WorkDay(projectUserForm.getPid(), projectUserForm.getUid(), LocalDate.parse(day)));
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    @RequestMapping(value = {"/updateWorkDayForUser"}, method = RequestMethod.PUT)
    public void updateWorkDayForUser(@RequestBody UserWorkDayUpdateForm[] updateDays) {
        for (UserWorkDayUpdateForm updateDay : updateDays) {
            Optional<WorkDay> workDay = workDayRepository.findById(updateDay.getId());
            workDay.get().setWorkDay(LocalDate.parse(updateDay.getDay()));
            workDayRepository.save(workDay.get());
        }
    }

    @RequestMapping(value = {"/updateProjectForUser/{workDayId}/{newProjectId}"}, method = RequestMethod.GET)
    public void updateProjectForUser(@PathVariable long workDayId, @PathVariable long newProjectId) {
        Optional<WorkDay> workDay = workDayRepository.findById(workDayId);
        workDay.get().setPid(newProjectId);
        workDayRepository.save(workDay.get());
    }

    @RequestMapping(value = {"/deleteWorkDayForUser/{id}"}, method = RequestMethod.DELETE)
    public void deleteWorkDayForUser(@PathVariable long id) {
        try {
            workDayRepository.deleteById(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @RequestMapping(value = {"/getUserSchedule/{id}"}, method = RequestMethod.GET)
    public List<WorkDay> getUserSchedule(@PathVariable long id) {
        return workDayRepository.findByUidOrderByWorkDayAsc(id);
    }

}
