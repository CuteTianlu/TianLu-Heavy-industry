package mod;

import arc.Events;
import arc.util.Time;
import mindustry.game.EventType;
import mindustry.mod.Mod;
import mindustry.ui.dialogs.BaseDialog;
import mod.content.TianluItems;
import mod.content.TianluBlocks;
import mod.content.TianluNatural_StarTechTree;
import mod.content.TianluPlanets;

public class Tianlu extends Mod {
    public Tianlu() {
        Events.on(EventType.ClientLoadEvent.class, T -> {
            Time.run(10f, () -> {
                BaseDialog dialog = new BaseDialog("你好欢迎玩我的模组");
                dialog.cont.add("你好欢迎玩我的模组");
                Time.run(0F, dialog::addCloseButton);
                dialog.show();
            });
        });
    }
    @Override
    public void loadContent() {
        TianluItems.load();
        TianluBlocks.load();
        TianluPlanets.load();
        TianluNatural_StarTechTree.load();
    }
}