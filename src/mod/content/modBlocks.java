package mod.content;

import arc.graphics.*;
import arc.math.*;
import arc.struct.*;
import mindustry.*;
import mindustry.entities.*;
import mindustry.entities.abilities.*;
import mindustry.entities.bullet.*;
import mindustry.entities.effect.*;
import mindustry.entities.part.DrawPart.*;
import mindustry.entities.part.*;
import mindustry.entities.pattern.*;
import mindustry.game.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.type.*;
import mindustry.type.unit.*;
import mindustry.world.*;
import mindustry.world.blocks.*;
import mindustry.world.blocks.campaign.*;
import mindustry.world.blocks.defense.*;
import mindustry.world.blocks.defense.turrets.*;
import mindustry.world.blocks.distribution.*;
import mindustry.world.blocks.environment.*;
import mindustry.world.blocks.heat.*;
import mindustry.world.blocks.legacy.*;
import mindustry.world.blocks.liquid.*;
import mindustry.world.blocks.logic.*;
import mindustry.world.blocks.payloads.*;
import mindustry.world.blocks.power.*;
import mindustry.world.blocks.production.*;
import mindustry.world.blocks.sandbox.*;
import mindustry.world.blocks.storage.*;
import mindustry.world.blocks.units.*;
import mindustry.world.consumers.*;
import mindustry.world.draw.*;
import mindustry.world.meta.*;
import arc.graphics.Color;
import mindustry.content.Fx;
import mindustry.content.UnitTypes;
import mindustry.entities.bullet.BasicBulletType;
import mindustry.entities.pattern.ShootAlternate;
import mindustry.entities.pattern.ShootPattern;
import mindustry.gen.Sounds;
import mindustry.type.ItemStack;
import mindustry.world.Block;
import mindustry.world.draw.*;
import mindustry.world.meta.Env;
import mod.wored.blocks.ThermalEnergyExtraction;

import static mindustry.content.Items.*;
import static mod.content.modItems.*;
import static mindustry.content.Liquids.*;
import static mindustry.type.Category.*;
import static mindustry.type.ItemStack.with;

public class modBlocks {
    public static Block //注册方块
        Heat_Extractor,//热力提取机
        Chemical_Power_Plant;//蒸汽发电机
    public static void load(){//建筑
        //生产-电力
        Heat_Extractor = new ThermalEnergyExtraction("热力提取机") {{
            requirements(power, with(tungsten, 140, graphite, 85, silicon, 60));
            PowerOutput = 2.4f;
            heatOutput = 0.8f;
            generateEffect = Fx.redgeneratespark;
            effectChance = 0.011f;
            update = true;
            size = 2;
            rotate = true;
            floating = true;
            ambientSound = Sounds.loopHum;
            ambientSoundVolume = 0.06f;
        }};
        Chemical_Power_Plant = new ConsumeGenerator("化学发电厂") {{//比例20芳油1臭氧
            requirements(power, with(tungsten, 280, graphite, 180, silicon, 80));
            description = "通过我不懂的化学制作电力的发电厂。还能出水原理是什么？是不是化学反应产生的水？答案是不知道，反正就是这样了。嘿嘿\nPS:有芳油就自给自足";
            generateEffect = Fx.steam;
            consumeEffect = Fx.explosion;
            drawer = new DrawMulti(new DrawRegion("-底"), new DrawLiquidTile(water), new DrawDefault());
            size = 6;
            powerProduction = 550f * 12f / 60f;
            outputLiquid = new LiquidStack(water, 1f);
            itemDuration = 120f;
            consumeLiquids(LiquidStack.with(ozone, 8f/60f, arkycite, 160f/60f)); 
        }};
    }
}