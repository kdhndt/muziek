package be.vdab.muziek.controllers;

import be.vdab.muziek.domain.Track;
import be.vdab.muziek.exceptions.AlbumNietGevondenException;
import be.vdab.muziek.forms.ScoreForm;
import be.vdab.muziek.services.AlbumService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.time.LocalTime;
import java.util.Optional;

@Controller
@RequestMapping("album")
public class AlbumController {
    private final AlbumService albumService;

    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @GetMapping("{id}")
    public ModelAndView album(@PathVariable long id) {
        //het nut van JPA is om hier zo weinig data mee te geven, de queries die JPA voor ons maakt en joint vergemakkelijken nl. dit proces
        var modelAndView = new ModelAndView("album");
        //op deze manier kun je meerdere stukken data meegeven aan je view, addObject kan concateneren
        //geef de huidige score mee
        //geef je form model geen naam, Spring maakt automatisch een scoreForm bean en dit is ook waar @Valid gecontroleerd wordt voor je Errors
        albumService.findById(id).ifPresent(album -> modelAndView.addObject(album).addObject(new ScoreForm(album.getScore())));
        return modelAndView;
    }

    @PostMapping("{id}/score")
    public ModelAndView wijzigScore(@PathVariable long id, @Valid ScoreForm form, Errors errors, RedirectAttributes redirect) {
        if (errors.hasErrors()) {
            //html pagina heeft album informatie nodig
            var modelAndView = new ModelAndView("album");
            albumService.findById(id).ifPresent(album -> modelAndView.addObject(album));
            return modelAndView;
            //TODO Fix popup bij F5
        }
        try {
            albumService.wijzigScore(id, form.score());
            redirect.addAttribute("id", id);
            return new ModelAndView("redirect:/album/{id}");
        } catch (AlbumNietGevondenException ex) {
            //we geven geen data mee aan onze album pagina, er wordt aan de html kant gecontroleerd op th:if="${album}",
            //die detecteert dat er geen album object is en toont daar dus een foutmelding
            return new ModelAndView("album");
        }
    }
}