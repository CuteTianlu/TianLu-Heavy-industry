package mod.wored.blocks;

import mindustry.entities.bullet.BasicBulletType;
import mindustry.entities.pattern.ShootAlternate;
import mindustry.entities.pattern.ShootPattern;
import mindustry.gen.Sounds;
import mindustry.type.Category;
import mindustry.world.blocks.defense.turrets.ItemTurret;
import mindustry.world.draw.DrawTurret;
import mod.content.TianluItems;

import static mindustry.type.ItemStack.with;

public class TianLuItemTurretadd extends ItemTurret {
    public TianLuItemTurretadd(String name){
        super(name);

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
            }}
        );
    }
}
