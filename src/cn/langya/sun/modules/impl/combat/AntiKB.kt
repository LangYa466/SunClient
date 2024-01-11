package cn.langya.sun.modules.impl.combat

import com.cubk.event.annotations.EventTarget
import cn.langya.sun.events.impl.PacketReadEvent
import cn.langya.sun.events.impl.PacketSendEvent
import cn.langya.sun.events.impl.UpdateEvent
import cn.langya.sun.modules.Category
import cn.langya.sun.modules.Module
import cn.langya.sun.shit.MathUtil
import cn.langya.sun.utils.MoveUtils
import cn.langya.sun.values.*
import net.minecraft.client.Minecraft
import net.minecraft.client.entity.EntityPlayerSP
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityLivingBase
import net.minecraft.network.play.client.CPacketAnimation
import net.minecraft.network.play.client.CPacketConfirmTransaction
import net.minecraft.network.play.client.CPacketEntityAction
import net.minecraft.network.play.client.CPacketUseEntity
import net.minecraft.network.play.server.SPacketEntityVelocity
import net.minecraft.network.play.server.SPacketExplosion
import net.minecraft.util.EnumHand
import net.minecraft.util.math.Vec3d

/**
 * @author LangYa
 * @ClassName Velocity
 * @date 2023/12/31 9:59
 * @Version 1.0
 */

class AntiKB : Module("Velocity", true, Category.Combat) {
    private val modes = StringValue("Velocity", "JumpRester")
    private val grimRayCastValue = BoolValue("Grim-RayCast", false)
    private val grimCheckRangeValue = FloatValue("Grim-CheckRange", 4.0F, 2.0F, 6.0F)
    private val grimAttackPacketCountValue = FloatValue("Grim-AttackPacket-Count", 12.0F, 5.0F, 50.0F)
    private var velocityInput = false
    private var attacked = false
    private var motionNoXZ = 0.0
    private var lastSprinting = false

    init {
        modes.values.add("JumpRester")
        modes.values.add("GrimY")
        modes.values.add("Hypixel")
        modes.values.add("Cancel")
    }

    @EventTarget
    fun onUpdate(event: UpdateEvent) {

        when (modes.get()) {
            "GrimY" -> {
                if (velocityInput) {
                    if (attacked) {
                        val thePlayer2: EntityPlayerSP = mc.player
                        thePlayer2.motionX *= motionNoXZ
                        val thePlayer3: EntityPlayerSP = mc.player
                        thePlayer3.motionZ *= motionNoXZ
                        attacked = false
                    } else if (mc.player.hurtTime == 6 && mc.player.onGround && !mc.gameSettings.keyBindJump.isKeyDown()) {
                        mc.player.movementInput.jump = true
                    }
                    if (mc.player.hurtTime == 0) {
                        velocityInput = false
                    }
                }
            }
        }
    }

    @EventTarget
    fun onPacketSend(e: PacketSendEvent) {

        val packet = e.packet

        when (modes.get()) {
            "GrimY" -> {
                if (packet is CPacketEntityAction && velocityInput) {
                    if (packet.action === CPacketEntityAction.Action.START_SPRINTING) {
                        if (lastSprinting) {
                            e.cancel()
                        }
                        lastSprinting = true
                    } else if (packet.action === CPacketEntityAction.Action.STOP_SPRINTING) {
                        if (!lastSprinting) {
                            e.cancel()
                        }
                        lastSprinting = false
                    }
                }
            }
        }
    }


    @EventTarget
    fun onPacketRead(e: PacketReadEvent) {
        var spey = e.packet

        if (e.packet is SPacketEntityVelocity ) {
            spey = e.packet as SPacketEntityVelocity
            if (spey.entityID != mc.player.entityId || mc.player == null) return

            when (modes.get()) {

                "JumpRester" -> {
                    if (mc.player.onGround && mc.player.hurtTime > 0) {
                        mc.player.setSprinting(false)
                        mc.player.movementInput.jump = true
                    }
                }

                "GrimY" -> {
                    velocityInput = true
                    val movingObjectPosition = mc.objectMouseOver
                    val targets =
                        (grimRayCastValue.get() && movingObjectPosition != null && KillAura().target != null) as EntityLivingBase
                    if (mc.player!!.getDistanceToEntity(targets) <= grimCheckRangeValue.get()
                    ) {
                        for (i in 0 until grimAttackPacketCountValue.get().toInt()) {
                            if (mc.player.serverSprintState && MoveUtils.isMoving()) {
                                sendPacketC0F()
                                mc.connection!!.sendPacket(
                                    CPacketUseEntity(
                                        targets as Entity,
                                        EnumHand.MAIN_HAND
                                    )
                                )
                                mc.connection!!.sendPacket(CPacketAnimation())
                            } else {
                                sendPacketC0F()
                                mc.connection!!.sendPacket(
                                    CPacketEntityAction(
                                        mc.player as Entity,
                                        CPacketEntityAction.Action.START_SPRINTING
                                    )
                                )
                                mc.player.setSprinting(false)
                                mc.connection!!.sendPacket(
                                    CPacketUseEntity(
                                        targets as Entity,
                                        EnumHand.MAIN_HAND
                                    )
                                )
                                mc.connection!!.sendPacket(CPacketAnimation())
                                mc.connection!!.sendPacket(
                                    CPacketEntityAction(
                                        mc.player as Entity,
                                        CPacketEntityAction.Action.STOP_SPRINTING
                                    )
                                )
                            }
                        }
                        attacked = true
                        KillAura().blocking = false
                        motionNoXZ = getMotionNoXZ(spey)
                    }
                }

                "Hypixel" -> {
                    if (e.packet is SPacketEntityVelocity) {
                        e.cancel()
                        if (mc.player.onGround || spey.motionY / 8000.0 < 0.2 || spey.motionY / 8000.0 > 0.41995) {
                            mc.player.motionY = spey.motionY / 8000.0
                        }
                    }
                }

                "Cancel" -> {
                    if (e.packet is SPacketEntityVelocity) {
                        val packetEntityVelocity: SPacketEntityVelocity = e.packet as SPacketEntityVelocity
                        if (packetEntityVelocity.getEntityID() !== mc.player.getEntityId()) {
                            return
                        }
                        e.cancel()
                    }
                    if (e.packet is SPacketExplosion) {
                        e.cancel()
                    }
                }

            }

        }
    }

    fun sendPacketC0F() {
        Minecraft.getMinecraft().connection!!.sendPacket(
            CPacketConfirmTransaction(
                MathUtil.getRandom(102, 1000024123),
                MathUtil.getRandom(102, 1000024123).toShort(),
                true
            )
        )
    }


    private fun getMotionNoXZ(packetEntityVelocity: SPacketEntityVelocity): Double {
        val strength: Double = Vec3d(
            packetEntityVelocity.motionX.toDouble(),
            packetEntityVelocity.motionY.toDouble(),
            packetEntityVelocity.motionZ.toDouble()
        ).lengthVector()
        val motionNoXZ: Double = if (strength >= 20000.0) {
            if (mc.player.onGround) {
                0.05425
            } else {
                0.065
            }
        } else if (strength >= 5000.0) {
            if (mc.player.onGround) {
                0.01625
            } else {
                0.0452
            }
        } else {
            0.0075
        }
        return motionNoXZ
    }

}