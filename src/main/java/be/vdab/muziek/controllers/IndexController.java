package be.vdab.muziek.controllers;

import be.vdab.muziek.services.AlbumService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping
public class IndexController {
    private final AlbumService albumService;

    public IndexController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @GetMapping
    public ModelAndView toonIndex() {
        var modelAndView = new ModelAndView("index");
        modelAndView.addObject("albums", albumService.findAll());
        return modelAndView;
    }
}