package mod.content;

import arc.graphics.Color;
import arc.math.geom.Rect;
import mindustry.content.Fx;
import mindustry.entities.bullet.BasicBulletType;
import mindustry.entities.pattern.ShootSpread;
import mindustry.gen.Sounds;
import mindustry.gen.TankUnit;
import mindustry.type.UnitType;
import mindustry.type.Weapon;
import mindustry.type.unit.TankUnitType;
import mindustry.world.meta.Env;

public class TianluUnitType {
    public static UnitType
    assault;

    public static void load() {
        assault = new TankUnitType("assault") {{
            constructor = TankUnit::create;
            description = "基础的T1坦克有基础的攻击能力";
            details = "PS: 能把原版T2单位按在地上打";
            squareShape = true;
            omniMovement = true;
            rotateMoveFirst = false;
            rotateSpeed = 1.3f;
            envDisabled = Env.none;
            tankMoveVolume *= 0.32f;
            tankMoveSound = Sounds.tankMoveSmall;
            treadRects = new Rect[]{new Rect(12 - 32f, 7 - 32f, 14, 51)};
            speed = 0.8f;
            health = 1200;
            armor = 7.8f;
            drag = 0.08f;
            accel = 0.1f;
            hitSize = 12f;
            itemCapacity = 5;
            faceTarget = false;
            alwaysUnlocked = false;
            weapons.add(new Weapon("assault1"){{
                shoot = new ShootSpread(){{
                    shots = 2;
                    shotDelay = 3f;
                    spread = 2f;
                }};
                reload = 30f;
                recoil = 0f;
                x = 0f;
                y = 0f;
                shootY = 6;
                rotate = true;
                rotateSpeed = 6f;
                bullet = new BasicBulletType(8f, 60f){{
                    sprite = "missile-large";
                    lifetime = 20f;
                    shootEffect = Fx.shootSmall;
                    smokeEffect = Fx.shootBigSmoke;
                    shootEffect = Fx.shootBigColor;
                    width = 6f;
                    height = 12f;
                    lifetime = 40f;
                    hitSize = 4f;
                    hitColor = backColor = trailColor = Color.valueOf("feb380");
                    frontColor = Color.white;
                    trailWidth = 1.7f;
                    trailLength = 5;
                    despawnEffect = hitEffect = Fx.hitBulletColor;
                }};
            }});
        }};
    }
}