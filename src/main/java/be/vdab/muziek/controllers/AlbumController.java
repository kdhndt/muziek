package be.vdab.muziek.controllers;

import be.vdab.muziek.domain.Track;
import be.vdab.muziek.services.AlbumService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalTime;
import java.util.Optional;

@Controller
@RequestMapping("/album")
public class AlbumController {
    private final AlbumService albumService;

    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @GetMapping("{id}")
    public ModelAndView toonGeselecteerdAlbum(@PathVariable long id) {
        //Het nut van JPA is om hier zo weinig data mee te geven, de queries die JPA voor ons maakt en joint vergemakkelijken nl. dit proces
        var modelAndView = new ModelAndView("album");
        albumService.findById(id).ifPresent(album -> modelAndView.addObject("album", album));
        return modelAndView;
    }
}
