// 代码来自派蒙大神
package cn.langya.sun.modules.impl.move;

 
import cn.langya.sun.events.impl.UpdateEvent;
import cn.langya.sun.events.impl.WorldLoadEvent;
import cn.langya.sun.modules.Category;
import cn.langya.sun.modules.Module;
import cn.langya.sun.values.StringValue;
import com.cubk.event.annotations.EventTarget;
import net.minecraft.block.*;
import net.minecraft.init.*;
import net.minecraft.network.play.client.CPacketPlayerDigging;
import net.minecraft.util.math.BlockPos;

import java.util.*;


public class NoWater extends Module
{
    private final StringValue modeValue = new StringValue("Mode","Vanilla");
    public static boolean shouldCancelWater;
    
    public NoWater() {
        super("NoWater", Category.Move);
        modeValue.getValues().add("Vanilla");
        modeValue.getValues().add("Grim");
    }
    
    public void onDisable() {
        shouldCancelWater = false;
    }
    
    @EventTarget
    public void onWorldLoad(final WorldLoadEvent e) {
        shouldCancelWater = false;
    }
    
    @EventTarget
    public void onUpdate(final UpdateEvent e) {
        if (mc.player == null) {
            return;
        }
        shouldCancelWater = false;
        final Map<BlockPos, Block> searchBlock = searchBlocks(5);
        for (final Map.Entry<BlockPos, Block> block : searchBlock.entrySet()) {
            final boolean checkBlock = mc.world.getBlockState((BlockPos)block.getKey()).getBlock() == Blocks.WATER || mc.world.getBlockState((BlockPos)block.getKey()).getBlock() == Blocks.FLOWING_WATER || mc.world.getBlockState((BlockPos)block.getKey()).getBlock() == Blocks.LAVA || mc.world.getBlockState((BlockPos)block.getKey()).getBlock() == Blocks.FLOWING_WATER;
            if (checkBlock) {
                shouldCancelWater = true;
                if (!modeValue.get().equals("Grim") || !shouldCancelWater) {
                    continue;
                }
                mc.getConnection().getNetworkManager().sendPacket(new CPacketPlayerDigging(CPacketPlayerDigging.Action.ABORT_DESTROY_BLOCK, (BlockPos)block.getKey(), mc.objectMouseOver.sideHit));
                mc.getConnection().getNetworkManager().sendPacket(new CPacketPlayerDigging(CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK, (BlockPos)block.getKey(), mc.objectMouseOver.sideHit));
            }
        }
    }

    public static Map<BlockPos, Block> searchBlocks(final int radius) {
        final Map<BlockPos, Block> blocks = new HashMap<BlockPos, Block>();
        for (int x = radius; x > -radius; --x) {
            for (int y = radius; y > -radius; --y) {
                for (int z = radius; z > -radius; --z) {
                    final BlockPos blockPos = new BlockPos(mc.player.lastTickPosX + x, mc.player.lastTickPosY + y, mc.player.lastTickPosZ + z);
                    final Block block = mc.world.getBlockState(blockPos).getBlock();
                    blocks.put(blockPos, block);
                }
            }
        }
        return blocks;
    }

}
