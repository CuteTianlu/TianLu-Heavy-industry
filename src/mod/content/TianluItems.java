package mod.content;

import arc.graphics.Color;//导入必须的东西
import arc.struct.Seq;
import mindustry.ctype.Content;
import mindustry.ctype.ContentType;
import mindustry.type.Item;//导入必须的东西

public class TianluItems {
    public static Item//注册物品  PS:记得放在前面
        Copper_lead_alloy,//铜铅合金
        Tungsten_Steel,//钨钢
        Tungsten_Steel_Plate,//钨钢板
        iron,//铁
        Steel,//钢
        Barreled_Water,//水桶
        Barreled_Oil,//油桶
        Barreled_Slag,//矿渣桶
        Barreled_Cryofluid;//冷冻液桶
    public static void load(){//物品代码放在这里
        Copper_lead_alloy = new /*创建物品代码*/Item/*类型*/("铜铅合金"/*物品名字*/, Color.valueOf("#6c8587"/*物品在分类器或者其他东西上的颜色*/)) {{//铜铅_合金
            cost = 1f;//一般不用管
        }};//铜铅合金的代码我教你怎么做
        Tungsten_Steel = new Item("钨钢", Color.valueOf("#6c8587")) {{//钨钢
            cost = 1f;
        }};
        Tungsten_Steel_Plate = new Item("钨钢板", Color.valueOf("#6c8587")) {{//钨钢_板
            cost = 1f;
        }};
        iron = new Item("铁", Color.valueOf("#808080")) {{
            hardness = 3;
            cost = 1f;
            description = "一种普通的金属";
            details = "可以练钢";
            alwaysUnlocked = false;
        }};
        Steel = new Item("钢", Color.valueOf("#e0e0e0")) {{
            hardness = 3;
            cost = 1f;
            description = "加工过的铁";
            details = "能崩掉你的牙";
            alwaysUnlocked = false;
        }};
        Barreled_Water = new Item("水桶", Color.valueOf("#596ab8")) {{
        }};
        Barreled_Oil = new Item("油桶", Color.valueOf("#313131")) {{
        }};
        Barreled_Slag = new Item("矿渣桶", Color.valueOf("#ffa166")) {{
        }};
        Barreled_Cryofluid = new Item("冷冻液桶", Color.valueOf("#6ecdec")) {{
        }};
    }//记得加}
}//记得加}