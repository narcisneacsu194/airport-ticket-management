package com.teamtreehouse.airport.web.controller;

import com.teamtreehouse.airport.model.Booking;
import com.teamtreehouse.airport.service.BookingService;
import com.teamtreehouse.airport.service.PlaceService;
import com.teamtreehouse.airport.service.UserService;
import com.teamtreehouse.airport.web.FlashMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
// This is the Booking controller, where I included two main methods which route the user to a home page with a
// booking form in which a user can schedule a flight, and also the post method to store that information
// into the database.
// Another thing to mention is that I included a InitBinder method in order to accept a certain Date format
// for each Date field within the Booking form.
// The data is stored/retrieved using the BookingService, UserService, and PlaceService interfaces.
// The concepts used here are dependency injection with autowiring.
// This means that Spring application is passing to the BookingService reference for example a BookingServiceImpl object
// at runtime.
@Controller
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private UserService userService;

    @Autowired
    private PlaceService placeService;

    // This is the method that sends a GET request for the home page of the website, that include the booking form.
    // It passes three parameters, a booking object, and also users and places objects.
    @RequestMapping("/")
    public String bookingForm(Model model){
        if(!model.containsAttribute("booking")){
            model.addAttribute("booking", new Booking());
        }
        model.addAttribute("users", userService.findAll());
        model.addAttribute("places", placeService.findAll());
        return "booking/index";
    }

    // This is the InitBinder method for setting a format for the Date fields within the booking form.
    // This method is very important, as without it I could not pass dates from the fields to this controller.
    @InitBinder
    public void dataBinding(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, "departureDate", new CustomDateEditor(dateFormat, true));
        binder.registerCustomEditor(Date.class, "returnDate", new CustomDateEditor(dateFormat, true));
    }

    // This is the method that gets the POST request from the booking form, and stores the provided
    // information into the database.
    // It includes a form of validation, so if the information passed is valid, a positive flash message appears
    // Otherwise, an error flash message pops up, saying that some/all the information passed is invalid.
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
