package mod.wored.blocks;

import arc.struct.*;
import mindustry.content.*;
import mindustry.gen.*;
import mindustry.type.*;
import mindustry.world.blocks.production.GenericCrafter;
import mindustry.world.meta.*;
import mod.content.TianluItems;

public class TianLuGenericCrafter extends GenericCrafter {
    public float craftTime;
    public TianLuGenericCrafter(String name) {
        super(name);
        craftEffect = Fx.smeltsmoke;
        outputItem = new ItemStack(TianluItems.Tungsten_Steel_Plate, 5);
        consumeItem(TianluItems.Tungsten_Steel, 10);
        consumePower(3.5f);
        hasItems = true;
        craftTime = 240f;
        size = 2;
        alwaysUnlocked = false;
        liquidCapacity = 60f;
        update = true;
        solid = true;
        ambientSound = Sounds.loopMachine;
        sync = true;
        ambientSoundVolume = 0.03f;
        flags = EnumSet.of(BlockFlag.factory);
        drawArrow = false;
    }

    @Override
    public void setStats() {
        stats.timePeriod = craftTime;
        super.setStats();
        if((hasItems && itemCapacity > 0) || outputItems != null){
            stats.add(Stat.productionTime, craftTime / 60f, StatUnit.seconds);
        }

        if(outputItems != null){
            stats.add(Stat.output, StatValues.items(craftTime, outputItems));
        }

        if(outputLiquids != null){
            stats.add(Stat.output, StatValues.liquids(1f, outputLiquids));
        }
    }

    @Override
    public void setBars(){
        super.setBars();

        //set up liquid bars for liquid outputs
        if(outputLiquids != null && outputLiquids.length > 0){
            //no need for dynamic liquid bar
            removeBar("liquid");

            //then display output buffer
            for(var stack : outputLiquids){
                addLiquidBar(stack.liquid);
            }
        }
    }

    public float craftTimeadd = craftTime;
    public class GenericCrafterliquidext extends GenericCrafterBuild {
        @Override
        public void updateTile() {
            super.updateTile();
            Liquid liquid = liquids.current();
            if (liquid != null) {
                float heatCapacity = liquid.heatCapacity;
                craftTime *= heatCapacity;
            }
            if (liquid == null) {
                craftTime = craftTimeadd;
            }
        }
    }
}
