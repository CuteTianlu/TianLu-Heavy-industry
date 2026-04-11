package mod.wored.blocks;

import arc.Core;
import arc.graphics.Blending;
import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.Fill;
import arc.graphics.g2d.Lines;
import arc.math.Mathf;
import arc.scene.ui.layout.Table;
import mindustry.gen.Building;
import mindustry.graphics.Layer;
import mindustry.graphics.Pal;
import mindustry.ui.Bar;
import mindustry.world.Block;

import static mindustry.Vars.*;

import mindustry.world.blocks.defense.ForceProjector;

public class TianLuIronCurtain extends Block {
    public int sides;
    public boolean IronCurtainStart = false;
    public float IronCurtainStartDuration, IronCurtainCooldownTime, IronCurtainStartradius, radius, shieldRotation, shieldHealth, Duration = IronCurtainStartDuration, CooldownTime = IronCurtainCooldownTime;

    public TianLuIronCurtain(String name){
        super(name);
        IronCurtainCooldownTime = 3600f;
        IronCurtainStartDuration = 2100f;
        update = true;
        configurable = true;
        sides = 8;
        shieldRotation = 0;
        radius = 380f;
        shieldHealth = 2100000000f;

    }

    @Override
    public void setStats(){
        super.setStats();
    }

    @Override
    public void setBars(){
        super.setBars();

        addBar("shield", (TianLuIronCurtainCode entity) -> new Bar("stat.shieldhealth", Pal.accent, () -> IronCurtainStartradius / Duration).blink(Color.white));
        addBar("a", (TianLuIronCurtainCode entity) -> new Bar("stat.a", Pal.accent, () -> (CooldownTime <= 0) ? 1f : 0f).blink(Color.white));
    }

    @Override
    public void drawPlace(int x, int y, int rotation, boolean valid){
        super.drawPlace(x, y, rotation, valid);

        Draw.color(Pal.gray);
        Lines.stroke(3f);
        Lines.poly(x * tilesize + offset, y * tilesize + offset, sides, radius, shieldRotation);
        Draw.color(player.team().color);
        Lines.stroke(1f);
        Lines.poly(x * tilesize + offset, y * tilesize + offset, sides, radius, shieldRotation);
        Draw.color();
    }

    public class TianLuIronCurtainCode extends Building {

        @Override
        public void updateTile() {
            float fps = Core.graphics.getFramesPerSecond();
            shieldRotation += 0.1f * (60f / fps);
            if (IronCurtainStart) {
                if (CooldownTime <= 0) {
                    CooldownTime = IronCurtainCooldownTime;
                }
                if (!(Duration <= 0)) {
                    Duration -= (60f / fps);
                } else if (Duration <= 0) {
                    drawPlace((int) x, (int) y, 0, false);
                    Duration = IronCurtainStartDuration;
                    IronCurtainStart = false;
                }
            } else if (!IronCurtainStart) {
                if (!(CooldownTime <= 0)) {
                    CooldownTime -= (60f / fps);
                }
            }
        }

        @Override
        public void draw(){
            super.draw();

            Draw.alpha(2100000000f);
            Draw.z(Layer.blockAdditive);
            Draw.blend(Blending.additive);
            Draw.rect("a", x, y);
            Draw.blend();
            Draw.z(Layer.block);
            Draw.reset();

            drawShield();
        }

        public void drawShield() {
            if(IronCurtainStart){

                if(radius > 0.001f){
                    Draw.color(team.color, Color.white, Mathf.clamp(1f ));

                    if(renderer.animateShields){
                        Draw.z(Layer.shields + 0.001f);
                        Fill.poly(x, y, sides, radius, shieldRotation);
                    }else{
                        Draw.z(Layer.shields);
                        Lines.stroke(1.5f);
                        Draw.alpha(0.09f + Mathf.clamp(0.08f));
                        Fill.poly(x, y, sides, radius, shieldRotation);
                        Draw.alpha(1f);
                        Lines.poly(x, y, sides, radius, shieldRotation);
                        Draw.reset();
                    }
                }
            }
        }

        //配置代码
        @Override
        public void buildConfiguration(Table table) {
            table.button("开启铁幕装置", () -> {
                if (CooldownTime <= 0f) {
                    IronCurtainStart = true;
                } else if (!(CooldownTime <= 0)) {
                    table.add("冷却未好");
                }
            }).row();
        }
    }
}
