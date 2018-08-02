package com.example.hero.control;

import com.example.hero.entity.model.Hero;
import com.example.hero.entity.model.Quest;
import com.example.hero.entity.repository.HeroRepository;
import com.example.hero.entity.repository.QuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HeroServiceImpl implements HeroService {

    @Autowired
    private HeroRepository heroRepository;

    @Autowired
    private QuestRepository questRepository;

    @Override
    public Hero getHero(Long id) {
        return heroRepository.findById(id).orElse(null);
    }

    @Override
    public List<Hero> getHeroes() {
        return heroRepository.findAll();
    }

    @Override
    public Hero create(Hero hero) {
        return heroRepository.save(hero);
    }

    @Override
    public void delete(Hero hero) {
        heroRepository.deleteById(hero.getId());
    }

    @Override
    public boolean findHeroInHeroesWhoTakeTheQuest(Long heroId , Long questId){
        Quest quest = questRepository.findById(questId).orElse(null);
        for(Hero hero : quest.getHeroesWhoTookTheQuest()){
            if(hero.getId().equals(heroId))
                return true;
        }
        return false;
    }

}
