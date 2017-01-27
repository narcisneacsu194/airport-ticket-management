package com.teamtreehouse.airport.web.controller;

import com.teamtreehouse.airport.model.User;
import com.teamtreehouse.airport.service.UserService;
import com.teamtreehouse.airport.web.FlashMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Collections;
// This is the User entity controller. It routes the client requests, and stores or retrieves the desired data in or from the database.
// The UserService interface is used here, invoking the concepts of dependency injection and autowiring,
// which means that the Spring application is passing to the UserService reference a UserServiceImpl object
// at runtime.
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    // This method receives an HTTP GET request from the client, and sends back to the view a list of all the available users
    // from the database.
    @RequestMapping("/users")
    public String listUsers(Model model){
        if(!model.containsAttribute("user")){
            model.addAttribute("user", new User());
        }
        model.addAttribute("users", userService.findAll());
        return "user/index";
    }

    // This method receives an HTTP GET request from the client, and sends back to the view information about a specific
    // user entity from the database. It also sorts descending all the available bookings the user had made.
    @RequestMapping("/users/{userId}/detail")
    public String userDetail(@PathVariable Long userId, Model model){
        User user = userService.findById(userId);
        model.addAttribute("user", user);

        Collections.sort(user.getBookings(), (o1, o2) ->
            -1 * o1.getDepartureDate().compareTo(o2.getDepartureDate()));

        model.addAttribute("bookings", user.getBookings());

        return "user/detail";
    }

    // This method receives an HTTP POST request from the client, and edits a specified user entity from the database
    // using the information provided in the form fields.
    // If the information is correct, a positive flash message appears on the screen saying that it was edited successfully.
    // Otherwise, an error flash message pops up, saying that some or all the information entered is invalid.
    @RequestMapping(value = "/users/{userId}/edit", method = RequestMethod.POST)
    public String editUser(@Valid User user, BindingResult result, RedirectAttributes redirectAttributes){
        if(result.hasErrors()){
            redirectAttributes.addFlashAttribute("flash",
                    new FlashMessage("Some of your input is invalid. Please try again!", FlashMessage.Status.FAILURE));
            return String.format("redirect:/users/%s/detail", user.getId());
        }

        userService.save(user);
        redirectAttributes.addFlashAttribute("flash",
                new FlashMessage(String.format("User '%s' has been updated successfully!", user.getName()),
                        FlashMessage.Status.SUCCESS));
        return String.format("redirect:/users/%s/detail", user.getId());
    }

    // This method receives an HTTP POST request from the client, and simply deletes a specified user from the database.
    // A flash message pops up saying that the user was deleted successfully.
    @RequestMapping(value = "/users/{userId}/delete", method = RequestMethod.POST)
    public String deleteUser(@PathVariable Long userId, RedirectAttributes redirectAttributes){
        User user = userService.findById(userId);
        redirectAttributes.addFlashAttribute("flash",
                new FlashMessage(String.format("User '%s' has been deleted successfully!", user.getName()),
                        FlashMessage.Status.SUCCESS));
        userService.delete(user);
        return "redirect:/users";
    }

    // This method receives an HTTP POST method for adding a new User record in the database.
    // If the information provided is correct, a positive flash message appears stating that the
    // user has been successfully added.
    // Otherwise, an error message pops saying the opposite.
    @RequestMapping(value = "/users/add-user", method = RequestMethod.POST)
    public String addUser(@Valid User user, BindingResult result, RedirectAttributes redirectAttributes){
        if(result.hasErrors()){
            redirectAttributes.addFlashAttribute("user", user);
            redirectAttributes.addFlashAttribute("flash",
                    new FlashMessage("Some of your input is invalid. Please try again!",
                            FlashMessage.Status.FAILURE));
            return "redirect:/users";
        }

        userService.save(user);
        redirectAttributes.addFlashAttribute("flash",
                new FlashMessage(String.format("User '%s' has been added successfully!", user.getName()),
                        FlashMessage.Status.SUCCESS));
        return String.format("redirect:/users/%s/detail", user.getId());
    }
}
