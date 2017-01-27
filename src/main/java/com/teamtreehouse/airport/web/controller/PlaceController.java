package com.teamtreehouse.airport.web.controller;

import com.teamtreehouse.airport.model.Place;
import com.teamtreehouse.airport.service.PlaceService;
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
// This is the Place entity controller. It has methods that receive HTTP POST and GET requests from the view,
// in order to store or retrieve information into or from the database.
// The data is stored/retrieved using the PlaceService interfaces.
// The concepts used here are dependency injection with autowiring.
// This means that the Spring application is passing to the PlaceService reference a PlaceServiceImpl object
// at runtime.
@Controller
public class PlaceController {

    @Autowired
    private PlaceService placeService;

    // This method receives an HTTP GET request for listing all the available places stored in the database.
    @RequestMapping("/places")
    public String listPlaces(Model model){
        if(!model.containsAttribute("place")){
            model.addAttribute("place", new Place());
        }
        model.addAttribute("places", placeService.findAll());
        return "place/index";
    }

    // This method receives an HTTP GET request for displaying a detailed page of a specified place from the database.
    @RequestMapping("/places/{placeId}/detail")
    public String placeDetail(@PathVariable Long placeId, Model model){
        model.addAttribute("place", placeService.findById(placeId));
        return "place/detail";
    }

    // This method receives an HTTP POST request for editing an existing place from the database.
    // If the information passed is valid, a positive flash message will be displayed.
    // Otherwise, an error message pops up, saying that the information passed is somehow invalid.
    @RequestMapping(value = "/places/{placeId}/edit", method = RequestMethod.POST)
    public String editPlace(@Valid Place place, BindingResult result, RedirectAttributes redirectAttributes){
        if(result.hasErrors()){
            redirectAttributes.addFlashAttribute("flash", new FlashMessage("Some of your input is invalid. Please try again!", FlashMessage.Status.FAILURE));
            return String.format("redirect:/places/%s/detail", place.getId());
        }

        redirectAttributes.addFlashAttribute("flash", new FlashMessage("The place has been updated successfully", FlashMessage.Status.SUCCESS));
        placeService.save(place);
        return String.format("redirect:/places/%s/detail", place.getId());
    }

    // This method receives an HTTP POST request from the client, to delete a specific place from the database.
    // A positive flash message pops up, saying that the delete operation succeeded as expected.
    @RequestMapping(value = "/places/{placeId}/delete", method = RequestMethod.POST)
    public String deletePlace(@PathVariable Long placeId, RedirectAttributes redirectAttributes){
        Place place = placeService.findById(placeId);
        redirectAttributes.addFlashAttribute("flash", new FlashMessage(String.format("Place '%s' has been deleted successfully!", place.getCityName()),
                FlashMessage.Status.SUCCESS));
        placeService.delete(place);
        return "redirect:/places/";
    }

    // This method receives an HTTP POST request from the client. It tries to add a new place in the database using the
    // passed information.
    // If the information passed in the form is valid, a positive flash message appears, saying that the place
    // has been successfully added.
    // Otherwise, an error flash message pops up, saying that some or all the information entered is incorrect.
    @RequestMapping(value = "/places/add-place", method = RequestMethod.POST)
    public String addPlace(@Valid Place place, BindingResult result, RedirectAttributes redirectAttributes){
        if(result.hasErrors()){
            redirectAttributes.addAttribute("place", place);
            redirectAttributes.addFlashAttribute("flash", new FlashMessage("Some of your input is invalid. Please try again!", FlashMessage.Status.FAILURE));
            return "redirect:/places";
        }

        placeService.save(place);

        redirectAttributes.addFlashAttribute("flash", new FlashMessage(String.format("Place '%s' has been added successfully!", place.getCityName()),
                FlashMessage.Status.SUCCESS));
        return String.format("redirect:/places/%s/detail", place.getId());
    }
}
