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
import mindustry.world.blocks.production.HeatCrafter;
import mindustry.world.consumers.ConsumeItems;
import mindustry.world.meta.*;
import mod.entities.bullet.MultipleFormulas;

import static mindustry.content.Items.*;
import static mindustry.type.ItemStack.with;
import static mod.content.TianluItems.*;

public class TestItemAlpha extends GenericCrafter {
    public String Recipename1, Recipename2, Recipename3, Recipename4, Recipename5, Recipename6, Recipename7;
    public float[] Recipe1, Recipe2, Recipe3, Recipe4, Recipe5, Recipe6, Recipe7;
    public ItemStack[] RecipeItem1, RecipeItem2, RecipeItem3, RecipeItem4, RecipeItem5, RecipeItem6, RecipeItem7;
    public Item outputItemRecipe1, outputItemRecipe2, outputItemRecipe3, outputItemRecipe4, outputItemRecipe5, outputItemRecipe6;
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
        Recipename1 = "铁";
        Recipe1 = new float[]{60f, 12f};
        RecipeItem1 = with(tungsten, 6, lead, 6);
        outputItemRecipe1 = iron;
        Recipename1 = "钨钢";
        Recipe1 = new float[]{30f, 24f};
        RecipeItem1 = with(Tungsten_Steel_Plate, 6, tungsten, 6);
        outputItemRecipe1 = Tungsten_Steel;
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

        @Override
        public void drawSelect() {
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
            if (!(Recipename1 == null)) {
                table.button(Recipename1, () -> {
                    outputItem = outputItemRecipe1;
                    consumeItems(RecipeItem1);
                    craftTime = Recipe1[0];
                }).row();
            }
            if (!(Recipename2 == null)) {
                table.button(Recipename2, () -> {
                    outputItem = outputItemRecipe2;
                    consumeItems(RecipeItem2);
                    craftTime = Recipe2[0];
                }).row();
            }
            if (!(Recipename3 == null)) {
                table.button(Recipename3, () -> {
                    outputItem = outputItemRecipe3;
                    consumeItems(RecipeItem3);
                    craftTime = Recipe3[0];
                }).row();
            }
            if (!(Recipename4 == null)) {
                table.button(Recipename4, () -> {
                    outputItem = outputItemRecipe4;
                    consumeItems(RecipeItem4);
                    craftTime = Recipe4[0];
                }).row();
            }
        }
    }
}