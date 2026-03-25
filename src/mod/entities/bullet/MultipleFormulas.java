package mod.entities.bullet;

import arc.struct.Seq;
import arc.util.Nullable;
import mindustry.mod.NoPatch;
import mindustry.type.Item;
import mindustry.type.ItemStack;
import mindustry.world.consumers.Consume;
import mindustry.world.consumers.ConsumeItems;
import mindustry.world.consumers.ConsumePower;

public class MultipleFormulas {
    public float RecipecraftTime;
    public Item RecipeoutputItem;
    public ConsumeItems RecipeconsumeItems(ItemStack... items){
        return Recipeconsume(new ConsumeItems(items));
    }
    public <T extends Consume> T Recipeconsume(T consume){
        if(consume instanceof ConsumePower){
            //there can only be one power consumer
            RecipeconsumeBuilder.removeAll(b -> b instanceof ConsumePower);
            RecipeconsPower = (ConsumePower)consume;
        }
        RecipeconsumeBuilder.add(consume);
        return consume;
    }
    @NoPatch
    public @Nullable ConsumePower RecipeconsPower;
    @NoPatch
    protected Seq<Consume> RecipeconsumeBuilder = new Seq<>();
}