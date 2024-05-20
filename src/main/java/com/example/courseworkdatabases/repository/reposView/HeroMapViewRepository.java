package com.example.courseworkdatabases.repository.reposView;

import com.example.courseworkdatabases.entity.view.HeroMapView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HeroMapViewRepository extends JpaRepository<HeroMapView, Long> {

}
