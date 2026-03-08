package mod.wored.blocks;

import arc.Core;
import arc.struct.*;
import arc.util.io.*;
import mindustry.graphics.*;
import mindustry.type.Item;
import mindustry.ui.*;
import mindustry.world.blocks.heat.HeatBlock;
import mindustry.world.blocks.production.*;
import mindustry.world.draw.*;
import mindustry.world.meta.*;

public class TianLuHeatProducer extends GenericCrafter{
    public float heatOutput = 10f;
    public float warmupRate = 0.15f;
    public boolean Attribute_Efficiency;
    public int Custom_attribute;
    public float fps = Core.graphics.getFramesPerSecond();

    public TianLuHeatProducer(String name){
        super(name);

        drawer = new DrawMulti(new DrawDefault(), new DrawHeatOutput());
        rotateDraw = false;
        rotate = true;
        canOverdrive = false;
        drawArrow = true;
        craftTime = 320f;
        flags = EnumSet.of();
        Attribute_Efficiency = false;
        Custom_attribute = 1;
    }

    @Override
    public void setStats(){
        super.setStats();

        stats.add(Stat.output, heatOutput + " × 效率", StatUnit.heatUnits);
    }

    @Override
    public void setBars(){
        super.setBars();

        addBar("heat", (HeatProducerBuild entity) -> new Bar("bar.heat", Pal.lightOrange, () -> entity.heat / heatOutput));
    }

    public float heatmax(float heat, float Calculate_thermal_energy, float Speed) {
        if (heat < Calculate_thermal_energy) {
            heat += Calculate_thermal_energy / Speed;
        }
        return heat;
    }

    public class HeatProducerBuild extends GenericCrafterBuild implements HeatBlock {
        public float heat;

        @Override
        public void updateTile() {
            super.updateTile();

            Item item = items.first();
            if (item != null) {
                float flammability = item.flammability;
                heat = heatmax(heat, heatOutput * flammability, fps * flammability);
            }
            if (item == null) {
                heat = 0f;
            }
        }

        @Override
        public float heatFrac(){
            return heat / heatOutput;
        }

        @Override
        public float heat(){
            return heat;
        }


        @Override
        public void write(Writes write){
            super.write(write);
            write.f(heat);
        }

        @Override
        public void read(Reads read, byte revision) {
            super.read(read, revision);
            heat = read.f();
        }
    }
}