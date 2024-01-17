package cn.langya.sun.modules.impl.combat

import cn.langya.sun.events.impl.Render3DEvent
import cn.langya.sun.events.impl.UpdateEvent
import cn.langya.sun.modules.Category
import cn.langya.sun.modules.Module
import cn.langya.sun.modules.impl.misc.Teams
import cn.langya.sun.utils.misc.TimeUtil
import cn.langya.sun.utils.player.RotationUtil
import cn.langya.sun.values.BoolValue
import cn.langya.sun.values.FloatValue
import cn.langya.sun.values.IntValue
import com.cubk.event.annotations.EventTarget
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityLivingBase
import net.minecraft.network.play.client.CPacketPlayerDigging
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.math.BlockPos
import org.lwjgl.opengl.GL11
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin


class KillAura : Module("KillAura",Category.Combat) {
    //最大攻击速度
    private val maxCPSValue = IntValue("MaxCPS", 8, 20, 0)

    //最小攻击速度
    private val minCPSValue = IntValue("MinCPS", 5, 19, 0)

    //攻击距离
    val groundrangeValue = FloatValue("GroundRange", 3F, 8F, 3F)
    val airrangeValue = FloatValue("AirRange", 3F, 8F, 3F)

    //攻击是否穿墙
    private val throughWalls = BoolValue("ThroughWalls", true)

    //挥手
    private val swingValue = BoolValue("Swing", true)

    //AB
    private val autoBlockvalue = BoolValue("AutoBlock", true)

    //静默转头
    private val silentrotationValue = BoolValue("SilentRotation", true)

    //最大转头速度
    private val maxTurnSpeed = FloatValue("MaxTurnSpeed", 180f, 180f, 0f)

    //最小转头速度
    private val minTurnSpeed = FloatValue("MinTurnSpeed", 180f, 180f, 0f)

    //移动修复
    private val movefixValue = BoolValue("MoveFix", true)

    //视角
    private val fovValue = FloatValue("FOV", 180f, 180f, 0f)

    //距离光环显示
    private val circleValue = BoolValue("Circle", true)
    private val circleRed = IntValue("CircleRed", 255, 255, 0)
    private val circleGreen = IntValue("CircleGreen", 255, 255, 0)
    private val circleBlue = IntValue("CircleBlue", 255, 255, 0)
    private val circleAlpha = IntValue("CircleAlpha", 255, 255, 0)
    private val circleAccuracy = IntValue("CircleAccuracy", 15, 60, 0)

    //打人光环显示
    private val mark = BoolValue("Mark", true)
    init {
        add(maxCPSValue,minCPSValue,groundrangeValue,airrangeValue,throughWalls,swingValue,autoBlockvalue,silentrotationValue,maxTurnSpeed,minTurnSpeed,movefixValue,fovValue,circleValue,circleRed,circleGreen,circleBlue,circleAlpha,circleAccuracy,mark)
    }

    val attackTimer = TimeUtil()

    public var target: Entity? = null
    var click = 0
    var blocking = false


    @EventTarget
    fun onUpdate(event: UpdateEvent) {

        if(!state || mc.player == null || mc.world == null) return

        for(entity in mc.world.loadedEntityList) {
            if (mc.player.getDistanceToEntity(entity) <= getRange() && !Teams.isSameTeam(entity) && entity != mc.player &&  attackTimer.hasTimePassed(randomClickDelay(minCPSValue.get(), maxCPSValue.get())) && !Teams.isSameTeam(entity) && isFovInRange(entity) && !entity.isDead) {
                target = entity
                attackTimer.reset()
                attackEntity(entity)
            } else {
                target = null
            }
        }


        if (target == null) {
            stopBlocking()
            click = 0
        }

        if(mc.player.attackingEntity == null) {
            startBlock()
        }

    }

    @EventTarget
    fun onRender3D(event: Render3DEvent) {
        if(!state) return

        if (circleValue.get()) {
            GL11.glPushMatrix()
            GL11.glTranslated(
                mc.player.lastTickPosX + (mc.player.posX - mc.player.lastTickPosX) * mc.renderPartialTicks - mc.renderManager.renderPosX,
                mc.player.lastTickPosY + (mc.player.posY - mc.player.lastTickPosY) * mc.renderPartialTicks - mc.renderManager.renderPosY,
                mc.player.lastTickPosZ + (mc.player.posZ - mc.player.lastTickPosZ) * mc.renderPartialTicks - mc.renderManager.renderPosZ
            )
            GL11.glEnable(GL11.GL_BLEND)
            GL11.glEnable(GL11.GL_LINE_SMOOTH)
            GL11.glDisable(GL11.GL_TEXTURE_2D)
            GL11.glDisable(GL11.GL_DEPTH_TEST)
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA)

            GL11.glLineWidth(1F)
            GL11.glColor4f(
                circleRed.get().toFloat() / 255.0F,
                circleGreen.get().toFloat() / 255.0F,
                circleBlue.get().toFloat() / 255.0F,
                circleAlpha.get().toFloat() / 255.0F
            )
            GL11.glRotatef(90F, 1F, 0F, 0F)
            GL11.glBegin(GL11.GL_LINE_STRIP)

            for (i in 0..360 step 61 - circleAccuracy.get()) { // You can change circle accuracy  (60 - accuracy)
                GL11.glVertex2f(
                    (cos(i * Math.PI / 180.0).toFloat() * getRange()),
                    ((sin(i * Math.PI / 180.0).toFloat() * getRange()))
                )
            }
            GL11.glVertex2f(
                (cos(360 * Math.PI / 180.0).toFloat() * getRange()).toFloat(),
                ((sin(360 * Math.PI / 180.0).toFloat() * getRange()).toFloat())
            )

            GL11.glEnd()

            GL11.glDisable(GL11.GL_BLEND)
            GL11.glEnable(GL11.GL_TEXTURE_2D)
            GL11.glEnable(GL11.GL_DEPTH_TEST)
            GL11.glDisable(GL11.GL_LINE_SMOOTH)

            GL11.glPopMatrix()
        }

    }

    public fun getRange(): Float {
        return if(mc.player.onGround) {
            groundrangeValue.get()
        } else {
            airrangeValue.get()
        }
    }

    // CPS
    private fun randomClickDelay(minCPS: Int, maxCPS: Int): Long {
        return (Math.random() * (1000 / minCPS - 1000 / maxCPS + 1) + 1000 / maxCPS).toLong()
    }

    //视角
    private fun isFovInRange(entity: Entity?, fov: Float = fovValue.get()): Boolean {

        var fov = fov
        fov *= 0.5.toFloat()
        val v: Double =
            ((mc.player.rotationYaw - getPlayerRotation(entity)) % 360.0 + 540.0) % 360.0 - 180.0
        return v > 0.0 && v < fov || -fov < v && v < 0.0
    }

    //获取玩家转头
    private fun getPlayerRotation(entity: Entity?): Float {
        val x: Double = entity!!.posX - mc.player.posX
        val z: Double = entity.posZ - mc.player.posZ
        var yaw = atan2(x, z) * 57.2957795
        yaw = -yaw
        return yaw.toFloat()
    }

    private fun startBlock() {

        if(!autoBlockvalue.get()) {
            return
        }


        mc.connection!!.networkManager.sendPacket(CPacketPlayerDigging(
            CPacketPlayerDigging.Action.START_DESTROY_BLOCK,
            BlockPos(mc.player.posX, mc.player.posY, mc.player.posZ),
            EnumFacing.UP))

    }


    //  攻击
    private fun attackEntity(entity: Entity?, swing: Boolean = swingValue.get()) {

        if (mc.player.attackingEntity != null) {
            return
        }

        if(!isFovInRange(entity)) {
            return
        }

        doRotation(target as EntityLivingBase)

        stopBlocking()

        if(swing) {
            mc.player.swingArm(EnumHand.MAIN_HAND)
        }

        mc.playerController.attackEntity(mc.player, entity!!)

    }

    private fun doRotation(target: EntityLivingBase) {
        RotationUtil.setVisualRotations(mc.player.rotationYaw)
        val rotations: FloatArray = RotationUtil.getGrimRotations(target)
        mc.player.rotationYaw = rotations[0]
        mc.player.rotationPitch = rotations[1]
    }

    private fun stopBlocking() {

        if (mc.player.isUsingItem || blocking) {
            mc.connection!!.sendPacket(
                CPacketPlayerDigging(
                    CPacketPlayerDigging.Action.RELEASE_USE_ITEM,
                    BlockPos.ORIGIN, EnumFacing.DOWN
                )
            )
            blocking = false
        }

    }

}
