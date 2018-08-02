package com.example.hero.control;

import com.example.hero.entity.model.Hero;
import com.example.hero.entity.model.Quest;
import com.example.hero.entity.repository.HeroRepository;
import com.example.hero.entity.repository.QuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestServiceImpl implements QuestService {

    @Autowired
    private QuestRepository questRepository;

    @Autowired
    private HeroRepository heroRepository;

    @Override
    public Quest getQuest(Long id) {
        return questRepository.findById(id).orElse(null);
    }

    @Override
    public List<Quest> getQuests() {
        return questRepository.findAll();
    }

    @Override
    public Quest create(Quest quest) {
        return questRepository.save(quest);
    }

    @Override
    public void delete(Quest quest) {
        questRepository.deleteById(quest.getId());
    }

    @Override
    public List<Hero> getHeroesWhoTakePartAtQuest(Long id){
        Quest quest = questRepository.findById(id).orElse(null);
        return quest.getHeroesWhoTookTheQuest();
    }

    @Override
    public boolean joinQuest(Long questId, Long heroId){
        Quest quest = questRepository.findById(questId).orElse(null);
        Hero hero = heroRepository.findById(heroId).orElse(null);

        if(quest.canBeCompletedByOneHero && quest.getHeroesWhoCompleted().longValue() > 1)
            return false;

        if(quest.getCondition().equals("only human can join this quest") && !hero.getRace().equals("human") ||
           quest.getCondition().equals("only elf can join this quest") && !hero.getRace().equals("elf") ||
           quest.getCondition().equals("only dwarf can join this quest") && !hero.getRace().equals("dwarf") ||
           quest.getCondition().equals("only ork can join this quest") && !hero.getRace().equals("ork") )
            return false;

        if(quest.getCondition().equals("only heroes very famous can join it") && hero.getGainedFame() < 5)
            return false;

        //and so on with other conditions
        return true;
    }

    @Override
    public void completeQuest(Long questId, Long heroId){
        Quest quest = questRepository.findById(questId).orElse(null);
        Hero hero = heroRepository.findById(heroId).orElse(null);

        hero.gainedFame += quest.getGainedFame();
        quest.heroesWhoCompleted += 1;
        quest.getHeroesWhoTookTheQuest().add(hero);

        heroRepository.save(hero);
        questRepository.save(quest);
    }

}
