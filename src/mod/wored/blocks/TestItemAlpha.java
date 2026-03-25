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
import mindustry.world.*;
import mindustry.world.blocks.*;
import mindustry.world.blocks.defense.turrets.Turret;
import mindustry.world.blocks.production.GenericCrafter;
import mindustry.world.consumers.ConsumeItems;
import mindustry.world.meta.*;
import mod.entities.bullet.MultipleFormulas;

public class TestItemAlpha extends GenericCrafter {
    public Seq<Turret.AmmoEntry> Recipe = new Seq<>();
    public ObjectMap<Item, MultipleFormulas> MultipleFormulas = new OrderedMap<>();
    public int itemsPerSecond = 100;
    public float RecipecraftTime;
    public Item item;
    public ConsumeItems ItemsRecipeConsumption;

    public TestItemAlpha(String name){
        super(name);
        size = 4;
        hasItems = true;
        update = true;
        solid = true;
        group = BlockGroup.transportation;
        configurable = true;
        saveConfig = true;
        noUpdateDisabled = true;
        envEnabled = Env.any;
        clearOnDoubleTap = true;
        itemCapacity = 20;
    }

    public void Recipe(Object... objects){
        MultipleFormulas = OrderedMap.of(objects);
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

    @Override
    public void init(){
        MultipleFormulas.each((item, type) -> {{

        }});
        super.init();
    }

    public class MultiFormulaCode extends Building {
        public Item outputItem;
        @Override
        public void updateTile() {
//            if (!(outputItem == null)) {
//                if (!i) {
//                    if (!(ItemsRecipeConsumption == null)) {
//                        i = true;
//                    }
//                } if (i) {
//                    if (!(RecipecraftTime <= 0)) {
//                        RecipecraftTime -= fps * 60f;
//                    } else {
//                        Item item = items.first();
//                        produced(outputItem);
//                        items.set(outputItem, 1);
//                        items.set(outputItem, 0);
//                        dump(outputItem);
//                        i = false;
//                    }
//                }
//            }
            super.updateTile();

        }

        public void updateEfficiencyMultiplier() {
            float scale = efficiencyScale();
            efficiency *= scale;
            optionalEfficiency *= scale;
        }

        public void a(Item outputItem) {
        }

        @Override
        public void drawSelect(){
            super.drawSelect();
            drawItemSelection(outputItem);
        }

        @Override
        public Item config() {
            return outputItem;
        }

        //配置代码
        @Override
        public void buildConfiguration(Table table) {
            table.button("铜铅合金", () -> {}).row();
        }
    }
}