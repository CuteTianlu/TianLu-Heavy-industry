package mod.wored.meta;

import arc.struct.*;
import mindustry.world.meta.Stat;
import mindustry.world.meta.StatCat;

/** Describes one type of stat for content. */
public class TianLuStat {
    public static final Seq<TianLuStat> all = new Seq<>();

    public static final Stat
        tianluheatoutpu = new Stat("tianluheatoutpu", StatCat.crafting);
}
