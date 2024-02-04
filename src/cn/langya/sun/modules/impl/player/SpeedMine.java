package cn.langya.sun.modules.impl.player;

import cn.langya.sun.events.impl.misc.EventPacketRead;
import cn.langya.sun.events.impl.misc.EventPacketSend;
import cn.langya.sun.events.impl.player.EventUpdate;
import cn.langya.sun.modules.Category;
import cn.langya.sun.modules.Module;
import cn.langya.sun.values.BoolValue;
import cn.langya.sun.values.DoubleValue;
import com.cubk.event.annotations.EventTarget;
import net.minecraft.network.play.client.CPacketPlayerDigging;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.*;
import net.minecraft.potion.*;
import net.minecraft.init.*;
import net.minecraft.network.*;
import net.minecraft.block.state.*;

public class SpeedMine extends Module
{
    private final DoubleValue speed;
    private final BoolValue abortPacketSpoof;
    private final BoolValue speedCheckBypass;
    private final BoolValue grim;
    private EnumFacing facing;
    private BlockPos pos;
    private boolean boost;
    private float damage;
    
    public SpeedMine() {
        super("SpeedMine", Category.Player);
        speed = new DoubleValue("Speed", 1.1, 3.0, 1.0);
        grim = new BoolValue("GrimAC", true);
        abortPacketSpoof = new BoolValue("AbortPacketSpoof", true);
        speedCheckBypass = new BoolValue("VanillaCheckBypass", true);
        boost = false;
        damage = 0.0f;

        add(speed,abortPacketSpoof,speedCheckBypass, grim);
    }
    
    @Override
    public void onDisable() {
        if (mc.player == null) {
            return;
        }
        if (speedCheckBypass.get()) {
            if (MobEffects.SPEED != null) {
                mc.player.removePotionEffect(MobEffects.SPEED);
            }
        }
    }
    
    @EventTarget
    private void onPacketS(EventPacketSend e) {
        if (e.packet instanceof CPacketPlayerDigging) {
            if (((CPacketPlayerDigging) e.packet).getAction() == CPacketPlayerDigging.Action.START_DESTROY_BLOCK) {
                boost = true;
                pos = ((CPacketPlayerDigging)e.packet).getPosition();
                facing = ((CPacketPlayerDigging)e.packet).getFacing();
                damage = 0.0f;
            }
            else if (((CPacketPlayerDigging) e.packet).getAction() == CPacketPlayerDigging.Action.ABORT_DESTROY_BLOCK || ((CPacketPlayerDigging)e.packet).getAction() == CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK) {
                boost = false;
                pos = null;
                facing = null;
            }
        }
    }

    @EventTarget
    void onPacketR(EventPacketRead e) {

        if (e.packet instanceof CPacketPlayerDigging && ((CPacketPlayerDigging) e.packet).getAction() == CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK && grim.get()) {
            mc.getConnection().sendPacket(new CPacketPlayerDigging(CPacketPlayerDigging.Action.ABORT_DESTROY_BLOCK, ((CPacketPlayerDigging) e.packet).getPosition().add(0, 500, 0), ((CPacketPlayerDigging) e.packet).getFacing()));
        }


    }
    
    @EventTarget
    private void onUpdate(EventUpdate e) {
        if (speedCheckBypass.get()) {
            if (MobEffects.SPEED != null) {
                mc.player.addPotionEffect(new PotionEffect(MobEffects.SPEED, 89640, 2));
            }
        }
        if (mc.playerController.extendedReach()) {
            mc.playerController.blockHitDelay = 0;
        }
        else if (pos != null && boost) {
            final IBlockState blockState = mc.world.getBlockState(pos);
            damage += (float)(blockState.getBlock().getPlayerRelativeBlockHardness(blockState,mc.player, mc.world, pos) * speed.get());
            if (damage >= 1.0f) {
                if (Blocks.AIR != null) {
                    mc.world.setBlockState(pos, Blocks.AIR.getDefaultState(), 11);
                }
                if (abortPacketSpoof.get()) {
                    mc.getConnection().sendPacket(new CPacketPlayerDigging(CPacketPlayerDigging.Action.ABORT_DESTROY_BLOCK, pos, facing));
                }
                mc.getConnection().sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK, pos, facing));
                damage = 0.0f;
                boost = false;
            }
        }
    }
}
