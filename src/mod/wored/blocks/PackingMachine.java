package mod.wored.blocks;

import arc.*;
import arc.graphics.g2d.*;
import arc.scene.ui.layout.*;
import arc.struct.ObjectMap;
import arc.struct.OrderedMap;
import arc.struct.Seq;
import arc.util.*;
import arc.util.io.*;
import mindustry.entities.units.*;
import mindustry.gen.*;
import mindustry.type.*;
import mindustry.type.Liquid;
import mindustry.world.*;
import mindustry.world.blocks.*;
import mindustry.world.blocks.defense.turrets.Turret;
import mindustry.world.blocks.production.GenericCrafter;
import mindustry.world.blocks.production.HeatCrafter;
import mindustry.world.consumers.ConsumeItems;
import mindustry.world.meta.*;
import mod.entities.bullet.MultipleFormulas;

import static mindustry.content.Items.*;
import static mindustry.content.Liquids.*;
import static mindustry.type.ItemStack.with;
import static mod.content.TianluItems.*;

public class PackingMachine extends GenericCrafter {
    public Seq<Turret.AmmoEntry> Recipe = new Seq<>();
    public ObjectMap<Item, MultipleFormulas> MultipleFormulas = new OrderedMap<>();
    public int itemsPerSecond = 100;
    public float RecipecraftTime;
    public Item item;
    public ConsumeItems ItemsRecipeConsumption;

    public PackingMachine(String name){
        super(name);
        liquidCapacity = 100;
    }

    @Override
    public void setBars(){
        super.setBars();
    }

    @Override
    public void setStats(){
        super.setStats();
    }

    @Override
    public void drawPlanConfig(BuildPlan plan, Eachable<BuildPlan> list){
        drawPlanConfigCenter(plan, plan.config, "center", true);
    }

    @Override
    public boolean outputsItems(){
        return true;
    }

    public class PackingMachinegn extends Building {
        public Item outputItem;

        //@Override
        //public void updateTile() {
        //    if (!(this.liquids == null)) {
        //        outputItem = (this.liquid == water) ? Barreled_Water : (this.liquid == slag) ? Barreled_Slag : (this.liquid == cryofluid) ? Barreled_Cryofluid : (this.liquid == oil) ? Barreled_Oil : outputItem;
        //    } else {
        //        outputItem = null;
        //    }
        //    super.updateTile();
        //}

        public void updateEfficiencyMultiplier() {
            float scale = efficiencyScale();
            efficiency *= scale;
            optionalEfficiency *= scale;
        }

        @Override
        public void drawSelect() {
            super.drawSelect();
            drawItemSelection(outputItem);
        }

        @Override
        public Item config() {
            return outputItem;
        }
    }
}