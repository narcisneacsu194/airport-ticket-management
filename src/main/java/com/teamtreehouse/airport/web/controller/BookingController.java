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

@Controller
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private UserService userService;

    @Autowired
    private PlaceService placeService;

    @RequestMapping("/")
    public String bookingForm(Model model){
        if(!model.containsAttribute("booking")){
            model.addAttribute("booking", new Booking());
        }
        model.addAttribute("users", userService.findAll());
        model.addAttribute("places", placeService.findAll());
        return "booking/index";
    }

    @InitBinder
    public void dataBinding(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, "departureDate", new CustomDateEditor(dateFormat, true));
        binder.registerCustomEditor(Date.class, "returnDate", new CustomDateEditor(dateFormat, true));
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
