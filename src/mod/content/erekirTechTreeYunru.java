package mod.content;

import arc.struct.*;
import mindustry.Vars;
import mindustry.content.*;
import mindustry.ctype.*;
import mindustry.game.Objectives.*;
import mindustry.type.*;
//原版
import static mindustry.content.Items.*;
import static mindustry.content.Blocks.*;
import static mindustry.content.TechTree.*;
//模组
import static mod.content.modBlocks.*;
import static mod.content.modItems.*;

public class erekirTechTreeYunru {
    private static TechNode context = null;
    public static Seq<TechNode> all = new Seq<>();
    public static Seq<TechNode> roots = new Seq<>();
    public static void load(){
        addToNode(Blocks.chemicalCombustionChamber, () -> {
            node(modBlocks.Heat_Extractor, () -> {
            });
        });
        addToNode(Blocks.chemicalCombustionChamber, () -> {
            node(modBlocks.Chemical_Power_Plant, () -> {
            });
        });
    };
    //谢谢更多实用设备要不然我还真不知道怎么写这个东西PS:更多实用设备的源代码
    public static void addToNode(UnlockableContent p, Runnable c) {
        context = TechTree.all.find(t -> t.content == p);
        c.run();
    }
    public static void node(UnlockableContent content, Runnable children){
        node(content, content.researchRequirements(), children);
    }

    public static void node(UnlockableContent content, ItemStack[] requirements, Runnable children){
        node(content, requirements, null, children);
    }

    public static void node(UnlockableContent content, ItemStack[] requirements, Seq<Objective> objectives, Runnable children){
        TechNode node = new TechNode(context, content, requirements);
        if(objectives != null){
            node.objectives.addAll(objectives);
        }

        TechNode prev = context;
        context = node;
        children.run();
        context = prev;
    }

    public static void node(UnlockableContent content, Seq<Objective> objectives, Runnable children){
        node(content, content.researchRequirements(), objectives, children);
    }

    public static void node(UnlockableContent block){
        node(block, () -> {});
    }

    public static void nodeProduce(UnlockableContent content, Seq<Objective> objectives, Runnable children){
        node(content, content.researchRequirements(), objectives.add(new Produce(content)), children);
    }

    public static void nodeProduce(UnlockableContent content, Runnable children){
        nodeProduce(content, new Seq<>(), children);
    }
}