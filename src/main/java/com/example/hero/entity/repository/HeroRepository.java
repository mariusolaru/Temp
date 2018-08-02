package com.example.hero.entity.repository;

import com.example.hero.entity.model.Hero;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HeroRepository extends JpaRepository<Hero, Long> {

}
