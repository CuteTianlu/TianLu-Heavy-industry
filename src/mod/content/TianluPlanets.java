package mod.content;
import arc.graphics.Color;
import mindustry.game.Team;
import mindustry.graphics.g3d.HexMesh;
import mindustry.graphics.g3d.HexSkyMesh;
import mindustry.graphics.g3d.MultiMesh;
import mindustry.maps.planet.ErekirPlanetGenerator;
import mindustry.type.Planet;

public class TianluPlanets {
    public static Planet Natural_Star;
    public static void load() {
        Natural_Star = new Planet("Natural_Star", mindustry.content.Planets.sun, 0.8f, 2) {{
            generator = new ErekirPlanetGenerator();
            meshLoader = () ->new MultiMesh(
                new HexMesh(this, 1),
                new HexSkyMesh(this, 2, 0.3f, 0.15f, 1, Color.valueOf("#b0bac0").a(0.55f), 2, 0.42f, 1.2f, 0.45f)
            );
            startSector = 72;
            alwaysUnlocked = true;
            ruleSetter = r -> {
                r.waveTeam = Team.malis;
                r.placeRangeCheck = false;
                r.showSpawns = true;
                r.fog = true;
                r.staticFog = true;
                r.lighting = false;
                r.coreDestroyClear = true;
                r.onlyDepositCore = true;
            };
        }};
    }
}