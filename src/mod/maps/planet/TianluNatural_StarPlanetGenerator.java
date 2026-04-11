package mod.maps.planet;

import arc.graphics.*;
import arc.math.*;
import arc.math.geom.*;
import arc.util.*;
import arc.util.noise.*;
import mindustry.content.*;
import mindustry.game.*;
import mindustry.maps.generators.*;
import mindustry.type.*;
import mindustry.world.*;
import mindustry.world.blocks.environment.Floor;

import static mindustry.Vars.*;
import static mod.content.TianluBlocks.*;

public class TianluNatural_StarPlanetGenerator extends PlanetGenerator{
    public int min = 20, max = 30, octaves = 2, foct = 3;
    public float radMin = 12f, radMax = 60f, persistence = 0.4f, scale = 30f, mag = 0.46f, thresh = 1f;
    public float fmag = 0.5f, fscl = 50f, fper = 0.6f;
    public float stoneWall = 3f, iceChance = 0f, carbonChance = 0f, berylChance = 0f, ferricChance = 1f;

    public float ironScale = 1f;
    Color c1 = Color.valueOf("5057a6"), c2 = Color.valueOf("272766");

    {
        defaultLoadout = Schematics.readBase64("bXNjaAF4nGNgZmBmYWDJS8xNZWB7tmDH0/3NDFzJ+XklqXklvokFDEzVtQzcKanFyUWZBSWZ+XkMEk/2LniyY9eT3Q1Pdna8WNb4fE73i/0zn+xYxcCWk5iUmlPMwBQdy8ggbWRgaGRgZmRckpmYl1Oq+7Rj7vNdy6EWMDAwghCQAAB82zHQ");
    }

    Block[][] arr = {
    {Blocks.sand, Blocks.stone},
    };

    {
        baseSeed = 1;
    }

    @Override
    public float getHeight(Vec3 position){
        return 0;
    }

    @Override
    public void getColor(Vec3 position, Color out){
        float depth = Simplex.noise3d(seed, 2, 0.56, 1.7f, position.x, position.y, position.z) / 2f;
        out.set(c1).lerp(c2, Mathf.clamp(Mathf.round(depth, 0.15f))).a(1f - 0.2f).toFloatBits();
    }

    @Override
    public float getSizeScl(){
        return 2000;
    }

    @Override
    public void addWeather(Sector sector, Rules rules){
        //no weather... yet
    }

    @Override
    public void genTile(Vec3 position, TileGen tile){
    }

    @Override
    protected void generate() {
        pass((x, y) -> {
            float max = 0;
            for(Point2 p : Geometry.d8){
                max = Math.max(max, world.getDarkness(x + p.x, y + p.y));
            }
            if(max > 0){
                block = floor.asFloor().wall;
            }
        });

        Schematics.placeLaunchLoadout(width / 2, height / 2);
        pass((x, y) -> {
            if(!nearWall(x, y)){
                //矿物生成部分
                if(noise(x + 999, y + 999 - x, 2, 0.12f, 45f, 1f) < 0.27f && floor == Blocks.stone){
                    ore = IronBlock;
                }
                if(noise(x + 256, y + 256 - x, 2, 0.24f, 32f, 1f) < 0.27f && floor == Blocks.stone){
                    ore = Blocks.oreCopper;
                }
                if(noise(x + 349, y + 349 - x, 2, 0.12f, 25f, 1f) < 0.27f && floor == Blocks.stone){
                    ore = Blocks.oreLead;
                }
            }
        });
        seed = state.rules.sector.planet.id;
        int sx = width/2, sy = height/2;
        rand = new Rand(seed);

        Floor stone = Blocks.stone.asFloor();

        tiles.eachTile(t -> t.setFloor(stone));
        pass((x, y) -> {
            if(floor == stone || Ridged.noise2d(seed + 1, x, y, 2, 0.2f, 1f / 240f) > 0.45f || Mathf.within(x, y, sx, sy, 20 + Ridged.noise2d(seed, x, y, 3, 0.5f, 1f / 30f) * 6f)) return;

            int radius = 6;
            for(int dx = x - radius; dx <= x + radius; dx++){
                for(int dy = y - radius; dy <= y + radius; dy++){
                    if(Mathf.within(dx, dy, x, y, radius + 0.0001f) && tiles.in(dx, dy) && tiles.getn(dx, dy).floor() == stone){
                        return;
                    }
                }
            }
            block = Blocks.stoneWall.asFloor().wall;
        });
        decoration(0.017f);

        //lead generates around stone walls
        oreAround(Blocks.stoneVent, Blocks.stone, 3, 70f,  0.09f);
    }

    float rawHeight(Vec3 position){
        return Simplex.noise3d(seed, 8, 0.7f, 1f, position.x, position.y, position.z);
    }

    Block getBlock(Vec3 position){
        float height = rawHeight(position);
        Tmp.v31.set(position);
        position = Tmp.v33.set(position).scl(2f);
        float temp = Simplex.noise3d(seed, 8, 0.6, 1f/2f, position.x, position.y + 99f, position.z);
        height *= 1.2f;
        height = Mathf.clamp(height);

        //float tar = (float)noise.octaveNoise3D(4, 0.55f, 1f/2f, position.x, position.y + 999f, position.z) * 0.3f + Tmp.v31.dst(0, 0, 1f) * 0.2f;

        return arr[Mathf.clamp((int)(temp * arr.length), 0, arr[0].length - 1)][Mathf.clamp((int)(height * arr[0].length), 0, arr[0].length - 1)];
    }
}
