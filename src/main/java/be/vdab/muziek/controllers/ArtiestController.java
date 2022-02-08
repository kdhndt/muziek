package be.vdab.muziek.controllers;

import be.vdab.muziek.services.ArtiestService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("artiest")
public class ArtiestController {
    private final ArtiestService artiestService;

    public ArtiestController(ArtiestService artiestService) {
        this.artiestService = artiestService;
    }

    @GetMapping("{id}")
    public ModelAndView albumsVanArtiest(@PathVariable long id) {
        var modelAndView = new ModelAndView("artiest");
        artiestService.findById(id).ifPresent(artiest -> modelAndView.addObject(artiest));
        return modelAndView;
    }
}
