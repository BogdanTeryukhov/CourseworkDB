package com.example.courseworkdatabases.entity.view;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "heromapview")
public class HeroMapView {
    @Id
    private Long id;
    @Column(name = "hero_name")
    private String heroName;
    @Column(name = "map_name")
    private String mapName;

    public HeroMapView(String heroName, String mapName) {
        this.heroName = heroName;
        this.mapName = mapName;
    }

    @Override
    public String toString() {
        return "HeroMapView{" +
                "heroName='" + heroName + '\'' +
                ", mapName='" + mapName + '\'' +
                '}';
    }
}
