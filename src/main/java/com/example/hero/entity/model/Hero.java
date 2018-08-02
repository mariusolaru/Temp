package com.example.hero.entity.model;

import javax.persistence.*;

@Entity
@Table(name = "heroes")
public class Hero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "race")
    public String race;

    @Column(name = "name")
    public String name;

    @Column(name = "gainedFame")
    public int gainedFame;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quest_id")
    public Quest quest;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGainedFame() {
        return gainedFame;
    }

    public void setGainedFame(int gainedFame) {
        this.gainedFame = gainedFame;
    }

}
