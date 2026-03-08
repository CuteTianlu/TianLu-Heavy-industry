package mod.content;

import mindustry.content.*;
//原版
import static mindustry.content.Items.copper;
import static mindustry.content.Items.lead;
import static mindustry.content.Blocks.mechanicalDrill;
import static mindustry.content.TechTree.*;
//模组
import static mod.content.TianluItems.Copper_lead_alloy;
import static mod.content.TianluBlocks.Beginner_Core;
import static mod.content.TianluBlocks.Copper_lead_alloy_Drill_bit;

public class TianluNatural_StarTechTree {
    public static void load(){
        Planets.serpulo.techTree = nodeRoot("Natural_Star", Beginner_Core, () -> {
            node(mechanicalDrill, () -> {
                node(Copper_lead_alloy_Drill_bit, () -> {});
            });
            node(copper, () -> {
                node(lead, () -> {
                    node(Copper_lead_alloy, () -> {});
                });
            });
        });
    }
}
