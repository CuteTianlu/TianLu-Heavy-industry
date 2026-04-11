package mod.content;

import mindustry.world.Block;
import mod.wored.blocks.TestItemAlpha;
import mod.wored.blocks.TianLuRangeMiningMachine;
import mod.wored.blocks.PackingMachine;

import static mindustry.content.Items.*;
import static mindustry.type.Category.*;
import static mindustry.type.ItemStack.with;

public class TestProject {
    public static Block //注册方块
        A1,B1,C1;
    public static void load(){//建筑
        A1 = new TestItemAlpha("测试项A1") {{
            requirements(crafting, with(copper, 999));
            size = 4;
        }};
        B1 = new TianLuRangeMiningMachine("测试项B1") {{
            requirements(crafting, with(null, 0));
            size = 2;
            range = 19;
        }};
        C1 = new PackingMachine("测试项C1") {{
            requirements(crafting, with(null, 0));
            size = 2;
        }};
    }
}