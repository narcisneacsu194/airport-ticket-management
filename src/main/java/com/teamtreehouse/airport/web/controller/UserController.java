package com.teamtreehouse.airport.web.controller;

import com.teamtreehouse.airport.model.Booking;
import com.teamtreehouse.airport.model.User;
import com.teamtreehouse.airport.service.BookingService;
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
import java.util.Comparator;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private BookingService bookingService;

    @RequestMapping("/users")
    public String listUsers(Model model){
        if(!model.containsAttribute("user")){
            model.addAttribute("user", new User());
        }
        model.addAttribute("users", userService.findAll());
        return "user/index";
    }

    @RequestMapping("/users/{userId}/detail")
    public String userDetail(@PathVariable Long userId, Model model){
        User user = userService.findById(userId);
        model.addAttribute("user", user);

        Collections.sort(user.getBookings(), (o1, o2) ->
            -1 * o1.getDepartureDate().compareTo(o2.getDepartureDate()));

        model.addAttribute("bookings", user.getBookings());

        return "user/detail";
    }

    @RequestMapping(value = "/users/{userId}/edit", method = RequestMethod.POST)
    public String editUser(@Valid User user, BindingResult result, RedirectAttributes redirectAttributes){
        if(result.hasErrors()){
            redirectAttributes.addFlashAttribute("flash",
                    new FlashMessage("Some of your input is invalid. Please try again!", FlashMessage.Status.FAILURE));
            return String.format("redirect:/users/%s/detail", user.getId());
        }

        userService.save(user);
        redirectAttributes.addFlashAttribute("flash",
                new FlashMessage(String.format("User '%s' has been added successfully!", user.getName()),
                        FlashMessage.Status.SUCCESS));
        return String.format("redirect:/users/%s/detail", user.getId());
    }

    @RequestMapping(value = "/users/{userId}/delete", method = RequestMethod.POST)
    public String deleteUser(@PathVariable Long userId, RedirectAttributes redirectAttributes){
        User user = userService.findById(userId);
        redirectAttributes.addFlashAttribute("flash",
                new FlashMessage(String.format("User '%s' has been deleted successfully!", user.getName()),
                        FlashMessage.Status.SUCCESS));
        userService.delete(user);
        return "redirect:/users";
    }

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
