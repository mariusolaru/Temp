package com.example.hero.controllers;

import com.example.hero.control.HeroService;
import com.example.hero.control.QuestService;
import com.example.hero.entity.model.Hero;
import com.example.hero.entity.model.Quest;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping(value = "quests")
public class QuestController {

    @Autowired
    private QuestService questService;

    @Autowired
    private HeroService heroService;

    /**
     * Endpoint for getting all quests from database
     * @return A list of quests
     */
    @GetMapping
    public @ResponseBody ResponseEntity<List<Quest>> getQuests() {
        return new ResponseEntity<>(questService.getQuests(), HttpStatus.OK);
    }

    /**
     * Endpoint for getting a quest based on its id in the database.
     * @param id The `id` in the database of the targeted quest.
     * @return A `Quest` object representing the entry in the DB with id `id`
     * @throws NotFoundException if targeted quest doesn't exist
     */
    @GetMapping(value = "/{id}")
    public @ResponseBody ResponseEntity<Quest> getQuest(@PathVariable("id") Long id) throws NotFoundException {
        Quest quest = questService.getQuest(id);
        if (quest == null) {
            throw new NotFoundException(String.format("Quest with id=%s was not found.", id));
        }
        return new ResponseEntity<>(quest, HttpStatus.OK);
    }

    /**
     * Endpoint for creating a quest
     * @param quest A json with the new quest to be inserted
     * @return Created at route
     * @throws URISyntaxException
     */
    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public @ResponseBody ResponseEntity<?> saveHero(@RequestBody Quest quest) throws URISyntaxException {
        Quest savedQuest = questService.create(quest);
        return ResponseEntity.created(new URI("/quests/" + quest.getId())).body(quest);
    }

    /**
     * Endpoint for deleting a quest from database
     * @param id Id of targeted quest
     * @return No content
     * @throws NotFoundException if the targeted quest was not found
     */
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteQuest(@PathVariable Long id) throws NotFoundException {
        Quest questDb = questService.getQuest(id);
        if (questDb == null) {
            throw new NotFoundException(String.format("Quest with id=%s was not found.", id));
        }
        questService.delete(questDb);

        return ResponseEntity.noContent().build();
    }

    /**
     * Endpoint for joining a quest
     * @param heroId
     * @param questId
     * @return Created at route
     * @throws URISyntaxException
     */
    @PostMapping(value = "/{userId}/{questId}")
    @ResponseStatus(value = HttpStatus.CREATED)
    public @ResponseBody ResponseEntity<?> joinQuestAndCompleteQuest(@RequestParam Long heroId,
                                                                     @RequestParam Long questId) throws URISyntaxException {

        Hero hero = heroService.getHero(heroId);
        Quest quest = questService.getQuest(questId);

        if (questService.joinQuest(questId , heroId) == true){
            questService.completeQuest(questId , heroId);

            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            return ResponseEntity.noContent().build();
        }

    }

}

