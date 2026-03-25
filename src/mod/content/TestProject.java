package mod.content;

import mindustry.world.Block;
import mod.wored.blocks.TestItemAlpha;
import mod.entities.bullet.MultipleFormulas;

import static mod.content.TianluItems.*;
import static mindustry.content.Items.*;
import static mindustry.type.Category.*;
import static mindustry.type.ItemStack.with;

public class TestProject {
    public static Block //注册方块
        A1;
    public static void load(){//建筑
        A1 = new TestItemAlpha("测试项") {{
            requirements(crafting, with(copper, 999));
            size = 4;
            Recipe(TianluItems.iron, new MultipleFormulas() {{
                    RecipeconsumeItems(with(copper, 6, lead, 6));
                    RecipecraftTime = 240f;
                    RecipeoutputItem = Copper_lead_alloy;
            }},
                TianluItems.iron, new MultipleFormulas() {{
                    consumeItems(with(iron, 6, Tungsten_Steel, 6));
                    RecipecraftTime = 240f;
                }}
            );
        }};
    }
}