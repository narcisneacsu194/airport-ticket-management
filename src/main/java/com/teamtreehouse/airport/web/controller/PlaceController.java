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

@Controller
public class PlaceController {

    @Autowired
    private PlaceService placeService;

    @RequestMapping("/places")
    public String listPlaces(Model model){
        if(!model.containsAttribute("place")){
            model.addAttribute("place", new Place());
        }
        model.addAttribute("places", placeService.findAll());
        return "place/index";
    }

    @RequestMapping("/places/{placeId}/detail")
    public String placeDetail(@PathVariable Long placeId, Model model){
        model.addAttribute("place", placeService.findById(placeId));
        return "place/detail";
    }

    @RequestMapping(value = "/places/{placeId}/edit", method = RequestMethod.POST)
    public String editPlace(Place place, BindingResult result, RedirectAttributes redirectAttributes){
        if(result.hasErrors()){
            redirectAttributes.addFlashAttribute("flash", new FlashMessage("Some of your input is invalid. Please try again!", FlashMessage.Status.FAILURE));
            return String.format("redirect:/places/%s/detail", place.getId());
        }

        redirectAttributes.addFlashAttribute("flash", new FlashMessage("This place has been updated successfully", FlashMessage.Status.SUCCESS));
        placeService.save(place);
        return String.format("redirect:/places/%s/detail", place.getId());
    }

    @RequestMapping(value = "/places/{placeId}/delete", method = RequestMethod.POST)
    public String deletePlace(@PathVariable Long placeId, RedirectAttributes redirectAttributes){
        Place place = placeService.findById(placeId);
        redirectAttributes.addFlashAttribute("flash", new FlashMessage(String.format("Place %s has been deleted successfully!", place.getCityName()),
                FlashMessage.Status.SUCCESS));
        placeService.delete(place);
        return "redirect:/places/";
    }

    @RequestMapping(value = "/places/add-place", method = RequestMethod.POST)
    public String addPlace(Place place, BindingResult result, RedirectAttributes redirectAttributes){
        if(result.hasErrors()){
            redirectAttributes.addAttribute("place", place);
            redirectAttributes.addFlashAttribute("flash", new FlashMessage("Some of your input is invalid. Please try again!", FlashMessage.Status.FAILURE));
            return "redirect:/places";
        }

        placeService.save(place);

        redirectAttributes.addFlashAttribute("flash", new FlashMessage(String.format("Place %s has been added successfully!", place.getCityName()),
                FlashMessage.Status.SUCCESS));
        return String.format("redirect:/places/%s/detail", place.getId());
    }
}
