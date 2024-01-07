package cn.langya.sun.modules.impl.combat

import cn.enaium.cf4m.annotation.Event
import cn.langya.sun.events.impl.Render3DEvent
import cn.langya.sun.events.impl.UpdateEvent
import cn.langya.sun.modules.Category
import cn.langya.sun.modules.Module
import cn.langya.sun.utils.render.RenderUtil
import cn.langya.sun.values.*
import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.GlStateManager
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityLivingBase
import net.minecraft.network.play.client.CPacketPlayerDigging
import net.minecraft.util.EnumFacing
import net.minecraft.util.math.BlockPos
import org.lwjgl.opengl.GL11
import java.awt.Color
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin


class KillAura : Module("KillAura",Category.Combat) {

    //攻击距离
    val rangeValue = FloatValue("Range", 3F, 8F, 3F)

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
    private val fovValue = FloatValue("FOV", 180f, 0f, 180f)

    //距离光环显示
    private val circleValue = BoolValue("Circle", true)
    private val circleRed = IntValue("CircleRed", 255, 255, 0)
    private val circleGreen = IntValue("CircleGreen", 255, 255, 0)
    private val circleBlue = IntValue("CircleBlue", 255, 255, 0)
    private val circleAlpha = IntValue("CircleAlpha", 255, 255, 0)
    private val circleAccuracy = IntValue("CircleAccuracy", 15, 60, 0)

    //打人光环显示
    private val mark = BoolValue("Mark", true)


    var target: EntityLivingBase? = null
    var click = 0
    var blocking = false


    @Event
    fun onUpdate(event: UpdateEvent) {

        if(!state) return

        for(entity in mc.world.loadedEntityList) {
            if (mc.player.getDistanceToEntity(entity) <= rangeValue.get() && entity != mc.player) {
                target = entity as EntityLivingBase?
                attackEntity(entity)
            } else {
                target = null
            }
        }

        if (target == null) {
            stopBlocking()
            click = 0
        }

    }

    @Event
    fun onRender3D(event: Render3DEvent) {

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
                    (cos(i * Math.PI / 180.0).toFloat() * rangeValue.get()),
                    ((sin(i * Math.PI / 180.0).toFloat() * rangeValue.get()))
                )
            }
            GL11.glVertex2f(
                (cos(360 * Math.PI / 180.0).toFloat() * rangeValue.get()).toFloat(),
                ((sin(360 * Math.PI / 180.0).toFloat() * rangeValue.get()).toFloat())
            )

            GL11.glEnd()

            GL11.glDisable(GL11.GL_BLEND)
            GL11.glEnable(GL11.GL_TEXTURE_2D)
            GL11.glEnable(GL11.GL_DEPTH_TEST)
            GL11.glDisable(GL11.GL_LINE_SMOOTH)

            GL11.glPopMatrix()
        }

    }


    //视角
    fun isFovInRange(entity: Entity? = target, fov: Float = fovValue.get()): Boolean {

        var fov = fov
        fov *= 0.5.toFloat()
        val v: Double =
            ((mc.player.rotationYaw - getPlayerRotation(entity)) % 360.0 + 540.0) % 360.0 - 180.0
        return v > 0.0 && v < fov || -fov < v && v < 0.0
    }

    //获取玩家转头
    fun getPlayerRotation(entity: Entity?): Float {
        val x: Double = entity!!.posX - mc.player.posX
        val z: Double = entity.posZ - mc.player.posZ
        var yaw = atan2(x, z) * 57.2957795
        yaw = -yaw
        return yaw.toFloat()
    }

    fun startBlock() {

        if(!isFovInRange()) {
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

        if(!isFovInRange()) {
            return
        }

      //  drawEntityESP(entity, Color(255, 255, 255).rgb)

        stopBlocking()

        mc.playerController.attackEntity(mc.player, entity!!)

    }




    fun stopBlocking() {

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

    private fun drawEntityESP(entity: Entity?, color: Int) {

        if(!mark.get()) {
            return
        }

        GL11.glPushMatrix()
        GL11.glDisable(3553)
        GL11.glEnable(2848)
        GL11.glEnable(2832)
        GL11.glEnable(3042)
        GL11.glBlendFunc(770, 771)
        GL11.glHint(3154, 4354)
        GL11.glHint(3155, 4354)
        GL11.glHint(3153, 4354)
        GL11.glDepthMask(false)
        GL11.glEnable(2929)
        GlStateManager.alphaFunc(516, 0.0f)
        GL11.glShadeModel(7425)
        GlStateManager.disableCull()
        GL11.glBegin(5)
        val entity2 = entity!!
        val x: Double =
            entity2.lastTickPosX + (entity2.posX - entity2.lastTickPosX) * mc.renderPartialTicks - Minecraft.getMinecraft().renderManager.renderPosX
        val y: Double =
            entity2.lastTickPosY + (entity2.posY - entity2.lastTickPosY) * mc.renderPartialTicks - Minecraft.getMinecraft().renderManager.renderPosY + sin(
                System.currentTimeMillis() / 200.0
            ) * (entity2.height / 2.0f) + 1.0f * (entity2.height / 2.0f)
        val z: Double =
            entity2.lastTickPosZ + (entity2.posZ - entity2.lastTickPosZ) * mc.renderPartialTicks - Minecraft.getMinecraft().renderManager.renderPosZ
        var i = 0.0f
        while (i < 6.283185307179586) {
            val vecX = x + 0.67 * cos(i.toDouble())
            val vecZ = z + 0.67 * sin(i.toDouble())
            RenderUtil.glColor(
                Color(
                    RenderUtil.getColor(color).red,
                    RenderUtil.getColor(color).green,
                    RenderUtil.getColor(color).blue,
                    0
                ).rgb
            )
            GL11.glVertex3d(vecX, y - cos(System.currentTimeMillis() / 200.0) * (entity2.height / 2.0f) / 2.0, vecZ)
            RenderUtil.glColor(
                Color(
                    RenderUtil.getColor(color).red,
                    RenderUtil.getColor(color).green,
                    RenderUtil.getColor(color).blue,
                    160
                ).rgb
            )
            GL11.glVertex3d(vecX, y, vecZ)
            i += 0.09817477042468103.toFloat()
        }
        GL11.glEnd()
        GL11.glShadeModel(7424)
        GL11.glDepthMask(true)
        GL11.glEnable(2929)
        GlStateManager.alphaFunc(516, 0.1f)
        GlStateManager.enableCull()
        GL11.glDisable(2848)
        GL11.glDisable(2848)
        GL11.glEnable(2832)
        GL11.glEnable(3553)
        GL11.glPopMatrix()
        GL11.glColor3f(255.0f, 255.0f, 255.0f)
    }

}
