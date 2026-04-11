package mod;

import arc.Events;
import arc.util.Time;
import mindustry.game.EventType;
import mindustry.game.Teams;
import mindustry.mod.Mod;
import mindustry.ui.dialogs.BaseDialog;
import mod.content.*;

public class 奇才 extends Mod {
    @Override
    public void loadContent() {
        //modItems.load();
        modBlocks.load();
        erekirTechTreeYunru.load();
    }
}