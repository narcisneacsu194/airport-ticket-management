package com.teamtreehouse.airport.web.controller;

import com.teamtreehouse.airport.model.Trip;
import com.teamtreehouse.airport.service.TripService;
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

@Controller
public class TripController {

    @Autowired
    private TripService tripService;

    @RequestMapping(value = "/trips")
    public String listTrips(Model model){
        if(!model.containsAttribute("trip")){
            model.addAttribute("trip", new Trip());
        }

        model.addAttribute("trips", tripService.findAll());

        return "trip/index";
    }

    @RequestMapping(value = "/trips/{tripId}/detail")
    public String tripDetail(@PathVariable Long tripId, Model model){
        model.addAttribute("trip", tripService.findById(tripId));
        return "trip/detail";
    }

    @RequestMapping(value = "/trips/{tripId}/edit", method = RequestMethod.POST)
    public String editTrip(@Valid Trip trip, BindingResult result, RedirectAttributes redirectAttributes){
        if(result.hasErrors()){
            redirectAttributes.addFlashAttribute("flash",
                    new FlashMessage("Some of your input is invalid. Please try again!",
                            FlashMessage.Status.FAILURE));
            return String.format("redirect:/trips/%s/detail", trip.getId());
        }

        tripService.save(trip);
        redirectAttributes.addFlashAttribute("flash",
                new FlashMessage("This trip has been updated successfully!",
                        FlashMessage.Status.SUCCESS));
        return String.format("redirect:/trips/%s/detail", trip.getId());
    }

    @RequestMapping(value = "/trips/{tripId}/delete", method = RequestMethod.POST)
    public String deleteTrip(@PathVariable Long tripId, RedirectAttributes redirectAttributes){
        Trip trip = tripService.findById(tripId);
        redirectAttributes.addFlashAttribute("flash",
                new FlashMessage("The trip has been deleted successfully!",
                        FlashMessage.Status.SUCCESS));
        tripService.delete(trip);
        return "redirect:/trips";
    }

    @RequestMapping(value = "/trips/add-trip", method = RequestMethod.POST)
    public String addTrip(@Valid Trip trip, BindingResult result, RedirectAttributes redirectAttributes){
        if(result.hasErrors()){
            redirectAttributes.addFlashAttribute("trip", trip);
            redirectAttributes.addFlashAttribute("flash",
                    new FlashMessage("Some of your input is invalid. Please try again!",
                            FlashMessage.Status.FAILURE));
            return "redirect:/trips";
        }

        tripService.save(trip);
        redirectAttributes.addFlashAttribute("flash",
                new FlashMessage("The trip has been added successfully!",
                        FlashMessage.Status.SUCCESS));
        return String.format("redirect:/trips/%s/detail", trip.getId());
    }
}
