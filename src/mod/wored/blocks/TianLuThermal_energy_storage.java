package mod.wored.blocks;

import arc.*;
import arc.graphics.g2d.*;
import arc.struct.*;
import arc.util.*;
import arc.util.io.Reads;
import arc.util.io.Writes;
import mindustry.*;
import mindustry.entities.units.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.ui.*;
import mindustry.world.*;
import mindustry.world.blocks.heat.HeatBlock;
import mindustry.world.blocks.heat.HeatConsumer;
import mindustry.world.draw.*;
import mindustry.world.meta.Stat;
import mindustry.world.meta.StatUnit;

public class TianLuThermal_energy_storage extends Block {
    public float visualMaxHeat;
    public DrawBlock drawer = new DrawDefault();
    public float Consumption_rate;
    public float Heat_loss;
    public float Charging_speed;

    public TianLuThermal_energy_storage(String name){
        super(name);
        update = solid = rotate = true;
        rotateDraw = false;
        size = 3;
        visualMaxHeat = 128.5f;//最高容量
        Consumption_rate = 3f;//输出时间越大越久
        Charging_speed = 4f;//充能速度越大越久
        Heat_loss = 0.05f;//一秒损失多少就填多少
    }

    @Override
    public void setStats(){
        super.setStats();
        stats.add(Stat.output, Consumption_rate, new StatUnit("Consumption_rate", "[red]" + Iconc.waves + " " + "[]"));
    }

    @Override
    public void setBars(){
        super.setBars();
        //TODO show number
        addBar("heat", (HeatConductorBuild entity) -> new Bar(() -> Core.bundle.format("bar.heatamount", (int)(entity.heat + 0.001f)), () -> Pal.lightOrange, () -> entity.heat / visualMaxHeat));
        addBar("Thermal_energy_storage_capacity", (HeatConductorBuild entity) -> new Bar(() -> Core.bundle.format("bar.heatamount", (int)(entity.Thermal_energy_storage_capacity + 0.001f)), () -> Pal.lightOrange, () -> entity.Thermal_energy_storage_capacity / visualMaxHeat));
    }

    @Override
    public void load(){
        super.load();

        drawer.load(this);
    }

    @Override
    public void drawPlanRegion(BuildPlan plan, Eachable<BuildPlan> list){
        drawer.drawPlan(this, plan, list);
    }

    @Override
    public TextureRegion[] icons(){
        return drawer.finalIcons(this);
    }

    public class HeatConductorBuild extends Building implements HeatBlock, HeatConsumer {
        public float heat = 0f;
        public float Thermal_energy_storage_capacity = 0f;
        public float[] sideHeat = new float[4];
        public IntSet cameFrom = new IntSet();
        public long lastHeatUpdate = -1;
        public float Consumption_rateadd = 0f;
        public float fps = Core.graphics.getFramesPerSecond();


        @Override
        public void draw(){
            drawer.draw(this);
        }

        @Override
        public void drawLight(){
            super.drawLight();
            drawer.drawLight(this);
        }

        @Override
        public float[] sideHeat(){
            return sideHeat;
        }

        @Override
        public float heatRequirement(){
            return visualMaxHeat;
        }

        @Override
        public void updateTile() {
            super.updateTile();
            updateHeat();
        }

        public void updateHeat(){
            if(lastHeatUpdate == Vars.state.updateId) return;
            lastHeatUpdate = Vars.state.updateId;
            if (heat > visualMaxHeat) {
                heat = visualMaxHeat;
            }
            if (heat < visualMaxHeat && calculateHeat(sideHeat, cameFrom) > 0f && heat <= calculateHeat(sideHeat, cameFrom) + 0.5f) {
                heat += calculateHeat(sideHeat, cameFrom) / (Consumption_rate * fps);
            }
            if (heat > calculateHeat(sideHeat, cameFrom)) {
                heat -= (Heat_loss / fps);
            }
            if ( 0 - visualMaxHeat > calculateHeat(sideHeat, cameFrom) || heat < 0f ) {
                heat = 0;
            }
            if (Thermal_energy_storage_capacity > 0f && (calculateHeat(sideHeat, cameFrom)) < 0f) {
                Consumption_rateadd = (Heat_loss / fps) + (calculateHeat(sideHeat, cameFrom) / (Consumption_rate * fps));
                Thermal_energy_storage_capacity -= Consumption_rateadd;
            }
            if (Thermal_energy_storage_capacity < visualMaxHeat && (calculateHeat(sideHeat, cameFrom)) > 0) {
                Thermal_energy_storage_capacity += calculateHeat(sideHeat, cameFrom) / (Charging_speed * fps);
            }
            if (heat < 0.5f && Thermal_energy_storage_capacity > 0f) {
                Thermal_energy_storage_capacity -= visualMaxHeat / (60f * fps);
            }
        }
        @Override
        public float heatFrac(){
            return heat / visualMaxHeat;
        }

        @Override
        public float heat(){
            return heat;
        }

        @Override
        public void write(Writes write){
            super.write(write);
            write.f(heat);
            write.f(Thermal_energy_storage_capacity);
        }

        @Override
        public void read(Reads read, byte revision){
            super.read(read, revision);
            heat = read.f();
            Thermal_energy_storage_capacity = read.f();
        }
    }
}
