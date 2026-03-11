package mod.content;

import mindustry.content.*;
//原版
import static mindustry.content.Items.*;
import static mindustry.content.Blocks.*;
import static mindustry.content.TechTree.*;
//模组
import static mod.content.TianluBlocks.*;
import static mod.content.TianluItems.*;

public class TianluNatural_StarTechTree {
    public static void load(){
        TianluPlanets.Natural_Star.techTree = nodeRoot("Natural_Star", Beginner_Core, () -> {
            node(conveyor, () -> {
                node(junction, () -> {
                    node(router, () -> {
                        node(overflowGate, () -> {
                            node(underflowGate, () -> {});
                        });
                        node(sorter, () -> {
                            node(invertedSorter, () -> {});
                        });
                        node(distributor, () -> {});
                    });
                });
                node(Copper_Lead_Alloy_Conveyor, () -> {});
            });
            node(mechanicalDrill, () -> {
                node(Copper_lead_alloy_Drill_bit, () -> {
                });
            });
            node(TianluBlocks.Copper_Lead_Alloy_Mixer, () -> {
                node(TianluBlocks.Steam_gener_Generator, () -> {
                    node(TianluBlocks.Electric_Silicon_Furnace, () -> {
                    });
                });
            });
            node(copper, () -> {
                node(lead, () -> {
                    node(Copper_lead_alloy, () -> {
                    });
                });
            });
        });
    }
}
