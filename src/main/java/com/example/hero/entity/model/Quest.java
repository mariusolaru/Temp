package com.example.hero.entity.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "quests")
@JsonIgnoreProperties(value = {"heroesWhoTookTheQuest"})
public class Quest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "gainedFame")
    public int gainedFame;

    @Column(name = "scope")
    public String scope;

    @Column(name = "condition")
    public String condition;

    @Column(name = "only_one_hero")
    public boolean canBeCompletedByOneHero;

    @Column(name = "heroes_who_completed")
    public Long heroesWhoCompleted;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "quest")
    public List<Hero> heroesWhoTookTheQuest;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getGainedFame() {
        return gainedFame;
    }

    public void setGainedFame(int gainedFame) {
        this.gainedFame = gainedFame;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public List<Hero> getHeroesWhoTookTheQuest() {
        return heroesWhoTookTheQuest;
    }

    public void setHeroesWhoTookTheQuest(List<Hero> heroesWhoTookTheQuest) {
        this.heroesWhoTookTheQuest = heroesWhoTookTheQuest;
    }

    public boolean canBeCompletedByOneHero() {
        return canBeCompletedByOneHero;
    }

    public void setCanBeCompletedByOneHero(boolean canBeCompletedByOneHero) {
        this.canBeCompletedByOneHero = canBeCompletedByOneHero;
    }

    public Long getHeroesWhoCompleted() {
        return heroesWhoCompleted;
    }

    public void setHeroesWhoCompleted(Long heroesWhoCompleted) {
        this.heroesWhoCompleted = heroesWhoCompleted;
    }
}
