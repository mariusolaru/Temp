package com.example.hero.controllers;

import com.example.hero.control.HeroService;
import com.example.hero.entity.model.Hero;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "heroes")
public class HeroController {

    @Autowired
    private HeroService heroService;

    /**
     * Endpoint for getting all heroes from database
     * @return A list of heroes
     */
    @GetMapping
    public @ResponseBody ResponseEntity<List<Hero>> getDoctors() {
        return new ResponseEntity<>(heroService.getHeroes(), HttpStatus.OK);
    }

    /**
     * Endpoint for getting a hero based on its id in the database.
     * @param id The `id` in the database of the targeted hero.
     * @return A `Hero` object representing the entry in the DB with id `id`
     * @throws NotFoundException if targeted hero doesn't exist
     */
    @GetMapping(value = "/{id}")
    public @ResponseBody ResponseEntity<Hero> getDoctor(@PathVariable("id") Long id) throws NotFoundException {
        Hero hero = heroService.getHero(id);
        if (hero == null) {
            throw new NotFoundException(String.format("Hero with id=%s was not found.", id));
        }
        return new ResponseEntity<>(hero, HttpStatus.OK);
    }

    /**
     * Endpoint for creating a hero
     * @param hero A json with the new hero to be inserted
     * @return Created at route
     * @throws URISyntaxException
     */
    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public @ResponseBody ResponseEntity<?> saveHero(@RequestBody Hero hero) throws URISyntaxException {
        Hero savedHero = heroService.create(hero);
        return ResponseEntity.created(new URI("/heroes/" + hero.getId())).body(hero);
    }

    /**
     * Endpoint for deleting a hero from database
     * @param id Id of targeted hero
     * @return No content
     * @throws NotFoundException if the targeted hero was not found
     */
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteHero(@PathVariable Long id) throws NotFoundException {
        Hero heroDb = heroService.getHero(id);
        if (heroDb == null) {
            throw new NotFoundException(String.format("Hero with id=%s was not found.", id));
        }
        heroService.delete(heroDb);

        return ResponseEntity.noContent().build();
    }

}
