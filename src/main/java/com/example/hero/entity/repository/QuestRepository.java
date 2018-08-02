package com.example.hero.entity.repository;

import com.example.hero.entity.model.Quest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestRepository extends JpaRepository<Quest, Long> {

}
