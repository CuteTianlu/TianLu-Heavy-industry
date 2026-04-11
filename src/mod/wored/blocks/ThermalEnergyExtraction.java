package mod.wored.blocks;

import arc.*;
import arc.graphics.*;
import arc.math.*;
import arc.scene.ui.layout.Table;
import arc.struct.IntSet;
import arc.util.*;
import arc.util.io.Reads;
import arc.util.io.Writes;
import mindustry.content.*;
import mindustry.entities.*;
import mindustry.game.*;
import mindustry.graphics.*;
import mindustry.type.*;
import mindustry.ui.Bar;
import mindustry.world.*;
import mindustry.world.blocks.power.PowerGenerator;
import mindustry.world.meta.*;
import mindustry.world.blocks.heat.HeatBlock;

import java.util.Objects;

public class ThermalEnergyExtraction extends PowerGenerator {
    public IntSet cameFrom = new IntSet();
    public float PowerOutput = 1.8f;
    public float heatOutput = 10f;
    public Effect generateEffect = Fx.none;
    public float effectChance = 0.05f;
    public float minEfficiency = 0f;
    public float displayEfficiencyScale = 1f;
    public boolean displayEfficiency = true;
    public @Nullable LiquidStack outputLiquid;
    public Attribute attribute = Attribute.heat;

    public ThermalEnergyExtraction(String name){
        super(name);
        noUpdateDisabled = true;
        configurable = true;
    }

    @Override
    public void init(){
        if(outputLiquid != null){
            outputsLiquid = true;
            hasLiquids = true;
        }
        emitLight = true;
        super.init();
        lightClipSize = Math.max(lightClipSize, 45f * size * 2f * 2f);
    }

    @Override
    public void setStats(){
        super.setStats();

        stats.add(Stat.tiles, attribute, floating, size * size * displayEfficiencyScale, !displayEfficiency);
        stats.remove(generationType);
        stats.add(generationType, PowerOutput * 60.0f / displayEfficiencyScale, StatUnit.powerSecond);
        stats.add(Stat.output, heatOutput + " × 效率", StatUnit.heatUnits);

        if(outputLiquid != null){
            stats.add(Stat.output, StatValues.liquid(outputLiquid.liquid, outputLiquid.amount * size * size * 60f, true));
        }
    }

    @Override
    public void setBars(){
        super.setBars();

        addBar("heat", (ThermalEnergyExtractionCode entity) -> new Bar("bar.heat", Pal.lightOrange, () -> entity.heat));
    }

    @Override
    public void drawPlace(int x, int y, int rotation, boolean valid){
        super.drawPlace(x, y, rotation, valid);

        if(displayEfficiency){
            drawPlaceText(Core.bundle.formatFloat("bar.efficiency", sumAttribute(attribute, x, y) * 100 * displayEfficiencyScale, 1), x, y, valid);
        }
    }

    @Override
    public boolean canPlaceOn(Tile tile, Team team, int rotation){
        //make sure there's heat at this location
        return tile.getLinkedTilesAs(this, tempTiles).sumf(other -> other.floor().attributes.get(attribute)) > minEfficiency;
    }

    public class ThermalEnergyExtractionCode extends GeneratorBuild implements HeatBlock {
        float TianLupowerProduction;
        float heat;
        String ModeOfProduction = "heat";
        public float[] sideHeat = new float[4];
        public float sum;

        @Override
        public void updateTile(){
            productionEfficiency = sum + attribute.env();

            if(productionEfficiency > 0.1f && Mathf.chanceDelta(effectChance)){
                generateEffect.at(x + Mathf.range(3f), y + Mathf.range(3f));
            }

            if(outputLiquid != null){
                float added = Math.min(productionEfficiency * delta() * outputLiquid.amount, liquidCapacity - liquids.get(outputLiquid.liquid));
                liquids.add(outputLiquid.liquid, added);
                dumpLiquid(outputLiquid.liquid);
            }
            TianLupowerProduction = (Objects.equals(ModeOfProduction, "power")) ? PowerOutput : 0f;
        }

        @Override
        public float getPowerProduction(){
            return enabled ? TianLupowerProduction * productionEfficiency : 0f;
        }

        @Override
        public void afterPickedUp(){
            super.afterPickedUp();
            sum = 0f;
        }

        @Override
        public float totalProgress(){
            return enabled && sum > 0 ? super.totalProgress() : 0f;
        }

        @Override
        public void drawLight(){
            Drawf.light(x, y, (40f + Mathf.absin(10f, 5f)) * Math.min(productionEfficiency, 2f) * size, Color.scarlet, 0.4f);
        }

        public float[] sideHeat(){
            return sideHeat;
        }

        @Override
        public float heatFrac(){
            return heat = ((Objects.equals(ModeOfProduction, "heat")) ? productionEfficiency * heatOutput : 0f) + calculateHeat(sideHeat, cameFrom);
        }

        @Override
        public float heat(){
            return heat = ((Objects.equals(ModeOfProduction, "heat")) ? productionEfficiency * heatOutput : 0f) + calculateHeat(sideHeat, cameFrom);
        }

        //配置代码
        @Override
        public void buildConfiguration(Table table) {
            table.button("输出热力", () -> {
                ModeOfProduction = "heat";
            }).row();
            table.button("输出电力", () -> {
                ModeOfProduction = "power";
            }).row();
        }

        @Override
        public void onProximityAdded(){
            super.onProximityAdded();

            sum = sumAttribute(attribute, tile.x, tile.y);
        }
        @Override
        public void write(Writes write){
            super.write(write);
            write.f(heat);
            write.f(TianLupowerProduction);
            write.str(ModeOfProduction);
        }

        @Override
        public void read(Reads read, byte revision) {
            super.read(read, revision);
            heat = read.f();
            TianLupowerProduction = read.f();
            ModeOfProduction = read.str();
        }
    }
}
