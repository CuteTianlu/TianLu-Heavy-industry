package mod.content;

import arc.graphics.Color;
import mindustry.content.Fx;
import mindustry.content.UnitTypes;
import mindustry.entities.bullet.BasicBulletType;
import mindustry.entities.pattern.ShootAlternate;
import mindustry.entities.pattern.ShootPattern;
import mindustry.gen.Sounds;
import mindustry.type.ItemStack;
import mindustry.world.Block;
import mindustry.world.blocks.defense.turrets.ItemTurret;
import mindustry.world.blocks.distribution.Conveyor;
import mindustry.world.blocks.environment.OreBlock;
import mindustry.world.blocks.power.ConsumeGenerator;
import mindustry.world.blocks.production.Drill;
import mindustry.world.blocks.production.HeatCrafter;
import mindustry.world.blocks.storage.CoreBlock;
import mindustry.world.consumers.ConsumeItemFlammable;
import mindustry.world.blocks.production.GenericCrafter;
import mindustry.world.draw.*;
import mindustry.world.meta.Env;
import mod.wored.blocks.TianLuIronCurtain;
import mod.wored.blocks.TianLuThermalEnergyExtraction;
import mod.wored.blocks.TianLuHeatProducer;

import static mindustry.content.Items.*;
import static mod.content.TianluItems.*;
import static mindustry.content.Liquids.*;
import static mindustry.type.Category.*;
import static mindustry.type.ItemStack.with;

public class TianluBlocks {
    public static Block //注册方块
        Electric_Silicon_Furnace,//电硅炉
        Copper_Lead_Alloy_Mixer,//铜铅合金混合机
        Tungsten_Steel_Refining_Furnace,//钨钢精炼炉
        High_Temperature_Press_Machine, //高温压片机
        Combustion_Heat_Generator,//燃烧发热机
        Heat_energy_storage,//热能电池
        Heat_Extractor,//热力提取机
        Steam_gener_Generator,//蒸汽发电机
        Copper_lead_alloy_Drill_bit,//铜铅合金钻头
        iron_Drill_bit,//铁钻头
        Steel_drilling_rig,//钢钻机
        Beginner_Core,//初级核心
        Suppress,//压制
        Copper_Lead_Alloy_Conveyor,//铜铅合金传送带
        IronCurtain,//铁幕装置
        IronBlock;//铁
    public static void load(){//建筑
        //电硅炉
        Electric_Silicon_Furnace = new GenericCrafter("电硅炉") {{
            requirements(crafting, with(copper, 50, lead, 30));
            description = "就是不需要煤炭的硅";
            health = 600;
            size = 2;
            hasPower = true;
            hasItems = true;
            hasLiquids = false;
            itemCapacity = 20;
            craftTime = 30f;
            consumeItems(with(sand, 2));
            consumePower(1f);
            outputItem = new ItemStack(silicon, 1);
            researchCost = with(copper, 150, lead, 80);
        }};
        //生产-工厂
        Copper_Lead_Alloy_Mixer = new GenericCrafter("铜铅合金混合机") {{
            description = "制作铜铅合金但是他里面是个熔炉\nPS: 搅拌加热获得美食";
            requirements(crafting, with(copper, 35, lead, 25));
            outputItem = new ItemStack(Copper_lead_alloy, 3);
            consumeItems(with(copper, 6, lead, 6));
            hasItems = true;
            craftTime = 240f;
            size = 2;
            itemCapacity = 20;
            researchCost = with(copper, 40, lead, 30);
        }};
        High_Temperature_Press_Machine = new GenericCrafter("高温压片机") {{
            requirements(crafting, with(tungsten, 60, thorium, 50, silicon, 25, Tungsten_Steel, 120));
            description = "通过高温和挤压制作钨钢板";
            details = "PS:力大砖飞";
            craftEffect = Fx.smeltsmoke;
            outputItem = new ItemStack(Tungsten_Steel_Plate, 5);
            consumeItem(Tungsten_Steel, 10);
            consumePower(4.2f);
            hasItems = true;
            craftTime = 240f;
            size = 2;
            itemCapacity = 20;
        }};
        Tungsten_Steel_Refining_Furnace = new HeatCrafter("钨钢精炼炉") {{
            requirements(crafting, with(tungsten, 50, thorium, 80, silicon, 25));
            description = "制作钨钢";
            details = "想想能干什么";
            craftEffect = Fx.smeltsmoke;
            outputItem = new ItemStack(Tungsten_Steel, 1);
            consumeItems(with(tungsten, 5, plastanium, 5));
            consumePower(3.6f);
            hasItems = true;
            craftTime = 240f;
            size = 3;
            heatRequirement = 18f;
            itemCapacity = 20;
        }};
        //生产-热力
        Combustion_Heat_Generator = new TianLuHeatProducer("燃烧发热机") {{
            requirements(crafting, with(copper, 50, titanium, 25, graphite, 35));
            description = "把能烧的全扔进去\n恭喜你没燃料了";
            Attribute_Efficiency = true;//是否根据物品部分属性修改效率 true开启  false关闭
            Custom_attribute = "flammability";//根据物品那个属性修改输出
            heatOutput = 5f;
            craftEffect = Fx.smeltsmoke;
            hasItems = true;
            update = true;
            size = 2;
            craftTime = 320f;
            itemCapacity = 20;
            consume(new ConsumeItemFlammable(1));
        }};
        Heat_Extractor = new TianLuThermalEnergyExtraction("热力提取机") {{
            requirements(power, with(copper, 40, graphite, 35, lead, 50, silicon, 35, metaglass, 40));
            PowerOutput = 4.5f;
            heatOutput = 5;
            generateEffect = Fx.redgeneratespark;
            effectChance = 0.011f;
            update = true;
            size = 2;
            rotate = true;
            floating = true;
            ambientSound = Sounds.loopHum;
            ambientSoundVolume = 0.06f;
        }};
        //生产-电力
        Steam_gener_Generator = new ConsumeGenerator("蒸汽发电机") {{
            requirements(power, with(copper, 50, titanium, 25, graphite, 35));
            description = "使用燃烧物加热水之后用蒸汽来带动发电机然后\n把多余的蒸汽排出去所以这玩意儿其实就是涡轮";
            generateEffect = Fx.steam;
            consumeEffect = Fx.explosion;
            drawer = new DrawMulti(new DrawRegion("-底"), new DrawLiquidTile(water), new DrawDefault());
            size = 2;
            powerProduction = 5f;
            itemDuration = 120f;
            consumeLiquid(water, 0.2f);
            consume(new ConsumeItemFlammable());
        }};
        //生产-开采
        Copper_lead_alloy_Drill_bit = new Drill("铜铅合金钻头") {{
            requirements(production, with(copper, 4, Copper_lead_alloy, 10));
            tier = 3;
            drillTime = 450;
            consumePower(1f);
            size = 2;
            envEnabled ^= Env.space;
            consumeLiquid(water, 0.05f).boost();
        }};
        iron_Drill_bit = new Drill("铁钻头") {{
            requirements(production, with(iron, 25, silicon, 10));
            health = 450;
            tier = 3;
            drillTime = 250;
            consumePower(1f);
            size = 2;
            envEnabled ^= Env.space;
            consumeLiquid(water, 0.04f).boost();
        }};
        Steel_drilling_rig = new Drill("钢钻机") {{
            requirements(production, with(Steel, 35, silicon, 15));
            health = 650;
            tier = 4;
            drillTime = 125;
            consumePower(1f);
            size = 2;
            envEnabled ^= Env.space;
            consumeLiquid(water, 0.03f).boost();
        }};
        //核心
        Beginner_Core = new CoreBlock("初级核心") {{
            requirements(effect, with(iron, 1250, Copper_lead_alloy, 950));
            size = 3;
            hasItems = true;
            itemCapacity = 2500;
            unitType = UnitTypes.alpha;
            health = 5000;
            armor = 20;
            description = "基础的小型核心";
            alwaysUnlocked = true;
        }};
        Suppress = new ItemTurret("压制") {{
            requirements(turret, with(Copper_lead_alloy, 120, copper, 80));
            description = "强大的T1炮台但是精度较差但是如果遇到防御力较高的单位难以穿透";
            health = 80;
            size = 2;
            reload = 1.8f;//子弹发射间隔
            range = 240f;//自动攻击范围(10=1格)
            maxAmmo = 45;//最大弹药数
            recoilTime = 5f;//后坐力恢复时间
            recoil = 1;//后坐力
            shoot = new ShootAlternate(3.5f);
            shootSound = Sounds.shootDuo;
            targetGround = true;
            targetAir = true;
            hasItems = false;
            inaccuracy = 6.8f;
            shake = 1f;
            ammoPerShot = 1;
            researchCost = with(Copper_lead_alloy, 180, copper, 100);
            ammo(
                iron, new BasicBulletType(40f/*子弹速度*/, 15) {{
                    ammoMultiplier = 12;
                    width = 5f;
                    height = 4f;
                    shoot = new ShootPattern();
                    drawer = new DrawTurret(){{
                        lifetime = 6f;//子弹存在时间  子弹存在时间×子弹速度÷10大约等于子弹飞行多少格
                    }};
                }},
                Steel, new BasicBulletType(40f/*子弹速度*/, 30){{
                    ammoMultiplier = 12;
                    width = 5f;
                    height = 4f;
                    shoot = new ShootPattern();
                    drawer = new DrawTurret(){{
                        lifetime = 6f;//子弹存在时间  子弹存在时间×子弹速度÷10大约等于子弹飞行多少格
                    }};
                }},
                Copper_lead_alloy, new BasicBulletType(40f/*子弹速度*/, 13) {{
                    ammoMultiplier = 12;
                    splashDamage = 8f;
                    splashDamageRadius = 12f;
                    splashDamagePierce = false;
                    knockback = 0f;
                    width = 5f;
                    height = 4f;
                    shoot = new ShootPattern();
                    drawer = new DrawTurret(){{
                        lifetime = 6f;//子弹存在时间  子弹存在时间×子弹速度÷10大约等于子弹飞行多少格
                    }};
                }},
                silicon, new BasicBulletType(45f/*子弹速度*/, 23) {{
                    ammoMultiplier = 12;
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
        //运输
        Copper_Lead_Alloy_Conveyor = new Conveyor("铜铅合金传送带") {{
            requirements(distribution, with(TianluItems.Copper_lead_alloy, 1));
            health = 32;
            speed = 0.045f;
            displayedSpeed = 6.5f;
            researchCost = with(TianluItems.Copper_lead_alloy, 35);
        }};
        //辅助
        IronCurtain = new TianLuIronCurtain("铁幕装置") {{
            requirements(effect, with(Tungsten_Steel_Plate, 2000, Copper_lead_alloy, 900, Steel, 280));
            description = "铁幕装置是异世界的科技别管哪来的图纸\nPS：红色警戒2";
            armor = 10;
            IronCurtainStartDuration = 2100f;
            IronCurtainCooldownTime = 3600f;
            IronCurtainStartradius = 380;
            shieldHealth = 2100000000f;
        }};
        //地形
        IronBlock = new OreBlock("铁") {{
            mapColor = Color.valueOf("#808080");
            itemDrop = TianluItems.iron;
            useColor = true;
            oreDefault = false;
            wallOre = false;
            oreScale = 35;
            playerUnmineable = false;
        }};
    }
}