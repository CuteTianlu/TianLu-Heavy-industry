package mod.content;

import arc.graphics.Color;
import mindustry.content.Fx;
import mindustry.content.Items;
import mindustry.content.Liquids;
import mindustry.content.UnitTypes;
import mindustry.entities.bullet.BasicBulletType;
import mindustry.entities.pattern.ShootAlternate;
import mindustry.entities.pattern.ShootPattern;
import mindustry.gen.Sounds;
import mindustry.type.Category;
import mindustry.type.ItemStack;
import mindustry.world.Block;
import mindustry.world.blocks.defense.turrets.ItemTurret;
import mindustry.world.blocks.environment.OreBlock;
import mindustry.world.blocks.power.ConsumeGenerator;
import mindustry.world.blocks.production.Drill;
import mindustry.world.blocks.production.HeatCrafter;
import mindustry.world.blocks.storage.CoreBlock;
import mindustry.world.consumers.ConsumeItemFlammable;
import mindustry.world.blocks.production.GenericCrafter;
import mindustry.world.draw.DrawTurret;
import mindustry.world.meta.Env;
import mod.wored.blocks.TianLuHeatProducer;
import mod.wored.blocks.TianLuItemTurretadd;

import static mindustry.type.Category.*;
import static mindustry.type.ItemStack.with;

public class TianluBlocks {
    public static Block //注册方块
        Copper_Lead_Alloy_Mixer,//铜铅合金混合机
        Tungsten_Steel_Refining_Furnace,//钨钢精炼炉
        High_Temperature_Press_Machine, //高温压片机
        Combustion_Heat_Generator,//燃烧发热机
        Thermal_energy_storage,//热能电池
        Steam_gener_Generator,//蒸汽发电机
        Copper_lead_alloy_Drill_bit,//铜铅合金钻头
        Beginner_Core,//初级核心
        Suppress,
        iron;//铁
    public static void load(){//建筑
        //生产-工厂
        Copper_Lead_Alloy_Mixer = new GenericCrafter("copper_lead_alloy_mixer") {{
            description = "制作铜铅合金但是他里面是个熔炉\nPS: 搅拌加热获得美食";
            requirements(crafting, with(Items.copper, 35,Items.lead, 25));
            outputItem = new ItemStack(TianluItems.Copper_lead_alloy, 3);
            consumeItems(with(Items.copper, 6, Items.lead, 6));
            hasItems = true;
            craftTime = 240f;
            size = 2;
            liquidCapacity = 60f;
        }};
        High_Temperature_Press_Machine = new GenericCrafter("High_Temperature_Press_Machine") {{
            requirements(crafting, with(Items.tungsten, 60, Items.thorium, 50, Items.silicon, 25, TianluItems.Tungsten_Steel, 120));
            description = "通过高温和挤压制作钨钢板";
            details = "PS:力大砖飞";
            craftEffect = Fx.smeltsmoke;
            outputItem = new ItemStack(TianluItems.Tungsten_Steel_Plate, 5);
            consumeItem(TianluItems.Tungsten_Steel, 10);
            consumePower(4.2f);
            hasItems = true;
            craftTime = 240f;
            size = 2;
            alwaysUnlocked = false;
            liquidCapacity = 60f;
        }};
        Tungsten_Steel_Refining_Furnace = new HeatCrafter("Tungsten_Steel_Refining_Furnace") {{
            requirements(crafting, with(Items.tungsten, 50, Items.thorium, 80, Items.silicon, 25));
            description = "制作钨钢";
            details = "想想能干什么";
            craftEffect = Fx.smeltsmoke;
            outputItem = new ItemStack(TianluItems.Tungsten_Steel, 1);
            consumeItems(with(Items.tungsten, 5, Items.plastanium, 5));
            consumePower(3.6f);
            hasItems = true;
            craftTime = 240f;
            size = 3;
            alwaysUnlocked = false;
            heatRequirement = 18f;
            liquidCapacity = 60f;
        }};
        //生产-工厂-热力
        Combustion_Heat_Generator = new TianLuHeatProducer("Combustion_Heat_Generator") {{
            requirements(crafting, with(Items.copper, 50, Items.titanium, 25, Items.graphite, 35));
            description = "把能烧的全扔进去\n恭喜你没燃料了";
            heatOutput = 5f;
            craftEffect = Fx.smeltsmoke;
            hasItems = true;
            update = true;
            size = 2;
            craftTime = 320f;
            itemCapacity = 20;
            consume(new ConsumeItemFlammable(1));
        }};
        //生产-电力
        Steam_gener_Generator = new ConsumeGenerator("Steam_gener_Generator") {{
            requirements(power, with(Items.copper, 50, Items.titanium, 25, Items.graphite, 35));
            description = "使用燃烧物加热水之后用蒸汽来带动发电机然后\n把多余的蒸汽排出去所以这玩意儿其实就是涡轮";
            powerProduction = 5f;
            itemDuration = 120f;
            consume(new ConsumeItemFlammable());
        }};
        //生产-开采
        Copper_lead_alloy_Drill_bit = new Drill("Copper_lead_alloy_Drill_bit") {{
            requirements(production, with(Items.copper, 4, TianluItems.Copper_lead_alloy, 10));
            tier = 3;
            drillTime = 450;
            consumePower(1f);
            size = 2;
            envEnabled ^= Env.space;
            consumeLiquid(Liquids.water, 0.05f).boost();
        }};
        //核心
        Beginner_Core = new CoreBlock("Beginner_Core") {{
            requirements(effect, with(TianluItems.iron, 1250, TianluItems.Copper_lead_alloy, 950));
            size = 3;
            hasItems = true;
            itemCapacity = 2500;
            unitType = UnitTypes.alpha;
            health = 5000;
            armor = 20;
            description = "基础的小型核心";
            alwaysUnlocked = true;
        }};
        Suppress = new TianLuItemTurretadd("Suppress") {{
            health = 80;
            size = 2;
            reload = 2.2f;//子弹发射间隔
            range = 240f;//自动攻击范围(10=1格)
            maxAmmo = 45;//最大弹药数
            recoilTime = 5f;//后坐力恢复时间
            recoil = 1;//后坐力
            shoot = new ShootAlternate(3.5f);
            ammoPerShot = 20;
            shootSound = Sounds.shootDuo;
            targetGround = true;
            targetAir = true;
            hasItems = false;
            inaccuracy = 5f;
            shake = 1f;
            ammo(
                TianluItems.iron, new BasicBulletType(40f/*子弹速度*/, 15){{
                    requirements(Category.turret, with(TianluItems.iron, 1));
                    ammoPerShot = 2;
                    width = 5f;
                    height = 4f;
                    shoot = new ShootPattern();
                    drawer = new DrawTurret(){{
                        lifetime = 6f;//子弹存在时间  子弹存在时间×子弹速度÷10大约等于子弹飞行多少格
                    }};
                }},
                TianluItems.Steel, new BasicBulletType(40f/*子弹速度*/, 30){{
                    requirements(Category.turret, with(TianluItems.iron, 1));
                    ammoPerShot = 3;
                    width = 5f;
                    height = 4f;
                    shoot = new ShootPattern();
                    drawer = new DrawTurret(){{
                        lifetime = 6f;//子弹存在时间  子弹存在时间×子弹速度÷10大约等于子弹飞行多少格
                    }};
                }},
                TianluItems.Copper_lead_alloy, new BasicBulletType(40f/*子弹速度*/, 13){{
                    requirements(Category.turret, with(TianluItems.Copper_lead_alloy, 1));
                    splashDamage = 8f;
                    splashDamageRadius = 12f;
                    splashDamagePierce = false;
                    knockback = 0f;
                    ammoPerShot = 4;
                    width = 5f;
                    height = 4f;
                    shoot = new ShootPattern();
                    drawer = new DrawTurret(){{
                        lifetime = 6f;//子弹存在时间  子弹存在时间×子弹速度÷10大约等于子弹飞行多少格
                    }};
                }},
                Items.silicon, new BasicBulletType(45f/*子弹速度*/, 30){{
                    requirements(Category.turret, with(Items.silicon, 1));
                    ammoMultiplier = 9;
                    ammoPerShot = 8;
                    width = 5f;
                    height = 4f;
                    homingPower = 12f;
                    homingRange = 22f;
                    shoot = new ShootPattern();
                    drawer = new DrawTurret() {{
                        lifetime = 6f;//子弹存在时间  子弹存在时间×子弹速度÷10大约等于子弹飞行多少格
                    }};
                }}
            );
        }};
        //地形
        iron = new OreBlock("iron") {{
            mapColor = Color.valueOf("#808080");
            itemDrop = TianluItems.iron;
            useColor = true;
            oreDefault = true;
            oreScale = 35;
            playerUnmineable = false;
        }};
    }
}