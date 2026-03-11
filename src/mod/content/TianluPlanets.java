package mod.content;
import arc.graphics.Color;
import mindustry.game.Schematics;
import mindustry.graphics.g3d.HexMesh;
import mindustry.graphics.g3d.HexSkyMesh;
import mindustry.graphics.g3d.MultiMesh;
import mindustry.maps.planet.SerpuloPlanetGenerator;
import mindustry.type.Planet;
import mod.maps.planet.TianluNatural_StarPlanetGenerator;

public class TianluPlanets {
    public static Planet Natural_Star;
    public static void load() {
        Natural_Star = new Planet("Natural_Star", mindustry.content.Planets.sun, 0.8f, 2) {{
            generator = new TianluNatural_StarPlanetGenerator();
            meshLoader = () ->new MultiMesh(
                new HexMesh(this, 1),
                new HexSkyMesh(this, 2, 0.3f, 0.15f, 1, Color.valueOf("#b0bac0").a(0.55f), 2, 0.42f, 1.2f, 0.45f)
            );
            sectorSeed = 7274;
            startSector = 74;
            alwaysUnlocked = true;

            defaultCore = TianluBlocks.Beginner_Core;
        }};
    }
}