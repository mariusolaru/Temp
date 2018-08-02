package com.example.hero.control;

import com.example.hero.entity.model.Hero;
import com.example.hero.entity.model.Quest;

import java.util.List;

public interface QuestService {

    Quest getQuest(Long id);
    List<Quest> getQuests();
    Quest create(Quest quest);
    void delete(Quest quest);
    List<Hero> getHeroesWhoTakePartAtQuest(Long id);
    boolean joinQuest(Long questId, Long heroId);
    void completeQuest(Long questId, Long heroId);

}
