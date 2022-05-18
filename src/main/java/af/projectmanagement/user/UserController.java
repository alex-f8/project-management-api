package af.projectmanagement.user;

import af.projectmanagement.useractivity.UserActivity;
import af.projectmanagement.useractivity.UserActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserActivityRepository userActivityRepository;

    @RequestMapping(value = {"/getUserInfo/{id}"})
    public UserFrontDTO getUserInfo(@PathVariable Long id) {
        return new UserFrontDTO(userRepository.findById(id).get());
    }

    @RequestMapping(value = {"/userStartsWork/{id}"}, method = RequestMethod.GET)
    public void userStarstWork(@PathVariable long id) {
        userActivityRepository.save(new UserActivity(id, LocalDateTime.now(), true));
    }

    @RequestMapping(value = {"/userFinishWork/{id}"}, method = RequestMethod.GET)
    public void userFinishWork(@PathVariable long id) {
        userActivityRepository.save(new UserActivity(id, LocalDateTime.now(), false));
    }

    @GetMapping(value = {"/getUserActivity/{id}"})
    public List<UserActivity> getUserActivity(@PathVariable long id) {
        return userActivityRepository.findAllByUserId(id);
    }
}
