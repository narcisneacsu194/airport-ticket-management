package com.teamtreehouse.airport.web.controller;

import com.teamtreehouse.airport.model.Booking;
import com.teamtreehouse.airport.service.BookingService;
import com.teamtreehouse.airport.service.UserService;
import com.teamtreehouse.airport.web.FlashMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String bookingForm(Model model){
        if(!model.containsAttribute("booking")){
            model.addAttribute("booking", new Booking());
        }
        model.addAttribute("users", userService.findAll());
        return "booking/index";
    }

    @RequestMapping(value = "/bookings/add-booking", method = RequestMethod.POST)
    public String addBooking(@Valid Booking booking, BindingResult result, RedirectAttributes redirectAttributes){
        if(result.hasErrors()){
            redirectAttributes.addFlashAttribute("booking", booking);
            redirectAttributes.addFlashAttribute("flash",
                    new FlashMessage("Some of your input is invalid. Please try again!",
                            FlashMessage.Status.FAILURE));
            return "redirect:/";
        }

        bookingService.save(booking);
        redirectAttributes.addFlashAttribute("flash",
                new FlashMessage("The booking has been added successfully.",
                        FlashMessage.Status.SUCCESS));
        return "redirect:/";
    }
}
