package tehnut.setnow.util;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.block.BlockBed;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChunkCoordinates;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import tehnut.setnow.SetNow;

public class EventHandler {

    @SubscribeEvent
    public void onInteract(PlayerInteractEvent event) {
        if (event.action == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK) {
            if (!event.world.isRemote) {
                if (event.world.getBlock(event.x, event.y, event.z) instanceof BlockBed) {
                    if (!SetNow.blacklistDims.contains(event.entityPlayer.dimension)) {
                        event.entityPlayer.setSpawnChunk(new ChunkCoordinates(event.x, event.y, event.z), false, event.entityPlayer.dimension);
                        event.entityPlayer.addChatComponentMessage(new ChatComponentTranslation("chat.SetNow.set.success"));
                    } else {
                        event.entityPlayer.addChatComponentMessage(new ChatComponentTranslation("chat.SetNow.set.fail"));
                    }
                }
            }
        }
    }
}
