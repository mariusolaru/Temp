package com.example.hero.control;

import com.example.hero.entity.model.Hero;

import java.util.List;

public interface HeroService {

    Hero getHero(Long id);
    List<Hero> getHeroes();
    Hero create(Hero hero);
    void delete(Hero hero);
    boolean findHeroInHeroesWhoTakeTheQuest(Long heroId , Long questId);

}
