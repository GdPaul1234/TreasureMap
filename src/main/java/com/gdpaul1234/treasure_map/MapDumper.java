package com.gdpaul1234.treasure_map;

import com.gdpaul1234.treasure_map.model.Adventurer;
import com.gdpaul1234.treasure_map.model.Map;
import com.gdpaul1234.treasure_map.model.Mountain;
import com.gdpaul1234.treasure_map.model.Treasure;
import com.samskivert.mustache.Mustache;

import java.util.List;

public class MapDumper {

    private final Map map;

    public MapDumper(Map map) {
        this.map = map;
    }

    public String dump() {
        String template = """
                {{map}}
                {{#mountains}}
                {{this}}
                {{/mountains}}
                # {T comme Trésor} - {Axe horizontal} - {Axe vertical} - {Nb. de trésors
                restants}
                {{#treasures}}
                {{this}}
                {{/treasures}}
                # {A comme Aventurier} - {Nom de l’aventurier} - {Axe horizontal} - {Axe
                vertical} - {Orientation} - {Nb. trésors ramassés}
                {{#adventurers}}
                {{this}}
                {{/adventurers}}
                """;

        return Mustache.compiler()
                .compile(template)
                .execute(new Object() {
                    final Map map = MapDumper.this.map;

                    final List<Mountain> mountains = this.map.getFieldOf(Mountain.class);

                    final List<Treasure> treasures = this.map.getFieldOf(Treasure.class).stream()
                            .filter(t -> t.getNbTreasure() > 0)
                            .toList();

                    final List<Adventurer> adventurers = this.map.getFieldOf(Adventurer.class);
                });
    }
}
