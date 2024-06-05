package com.example.courseworkdatabases.repository;

import com.example.courseworkdatabases.entity.Card;
import com.example.courseworkdatabases.entity.composite.CardID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CardRepo extends JpaRepository<Card, CardID> {
    @Query(value = "SELECT c.* FROM unmatched.Card c WHERE c.boost > ?1" ,nativeQuery = true)
    Optional<List<Card>> findCardsWhereBoostGreaterThan(short boost);
    @Query(value = "SELECT c.* FROM unmatched.Card c WHERE c.boost < ?1" ,nativeQuery = true)
    Optional<List<Card>> findCardsWhereBoostLessThan(short boost);
    @Query(value = "SELECT c.* FROM unmatched.Card c WHERE c.boost = ?1" ,nativeQuery = true)
    Optional<List<Card>> findCardsWhereBoostEquals(short boost);
    @Query(value = "SELECT c.* FROM unmatched.Card c WHERE c.value > ?1" ,nativeQuery = true)
    Optional<List<Card>> findCardsWhereValueGreaterThan(short value);
    @Query(value = "SELECT c.* FROM unmatched.Card c WHERE c.value < ?1" ,nativeQuery = true)
    Optional<List<Card>> findCardsWhereValueLessThan(short value);
    @Query(value = "SELECT c.* FROM unmatched.Card c WHERE c.value = ?1" ,nativeQuery = true)
    Optional<List<Card>> findCardsWhereValueEquals(short value);
    @Query(value = "SELECT c.* FROM unmatched.Card c WHERE c.type = ?1", nativeQuery = true)
    Optional<List<Card>> findCardsByType(String type);
    @Query(value = "SELECT c.* FROM unmatched.Card c WHERE c.name IN (SELECT hc.card_name FROM unmatched.Herocard hc WHERE hc.hero_name = ?1 AND hc.belong = 'Hero')", nativeQuery = true)
    Optional<List<Card>> findAllCardsByHero(String heroName);
    @Query(value = "SELECT c.* FROM unmatched.Card c WHERE c.name IN (SELECT hc.card_name FROM unmatched.Herocard hc WHERE hc.hero_name = (SELECT h.name FROM unmatched.Hero h WHERE h.sidekick_name = ?1) AND hc.belong = 'Sidekick')", nativeQuery = true)
    Optional<List<Card>> findAllCardsBySidekick(String sidekickName);
    @Query(value = "SELECT hc.* FROM unmatched.Herocard hc WHERE hc.hero_name = ?1 AND hc.card_number > ?2", nativeQuery = true)
    Optional<List<Card>> findAllCardsByHeroGreaterThan(String heroName, short numOfCards);
    @Query(value = "SELECT hc.* FROM unmatched.Herocard hc WHERE hc.hero_name = ?1 AND hc.card_number < ?2", nativeQuery = true)
    Optional<List<Card>> findAllCardsByHeroLessThan(String heroName, short numOfCards);
    @Query(value = "SELECT hc.* FROM unmatched.Herocard hc WHERE hc.hero_name = ?1 AND hc.card_number = ?2", nativeQuery = true)
    Optional<List<Card>> findAllCardsByHeroEquals(String heroName, short numOfCards);
    Optional<Card> findCardByNameAndBoost(String name, short boost);
    boolean existsByNameAndBoost(String name, short boost);
}
