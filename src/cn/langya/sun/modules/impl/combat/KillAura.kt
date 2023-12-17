package cn.langya.sun.modules.impl.combat

import cn.langya.sun.event.Event
import cn.langya.sun.event.EventManager
import cn.langya.sun.modules.Category
import cn.langya.sun.modules.Module
import cn.langya.sun.utils.render.RenderUtils
import cn.langya.sun.value.BoolValue
import cn.langya.sun.value.FloatValue
import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.GlStateManager
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityLivingBase
import net.minecraft.network.play.client.CPacketPlayerDigging
import net.minecraft.util.EnumFacing
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.MathHelper
import org.lwjgl.opengl.GL11
import java.awt.Color
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin


class KillAura : Module("杀人气质",true,Category.Combat) {

    //攻击距离
    val rangeValue = FloatValue("Range", 3F, 3F, 8F)

    //视角
    private val fovValue = FloatValue("FOV", 180f, 0f, 180f)

    //距离光环显示
    private val circleValue = BoolValue("Circle", true)
    private val circleRed = FloatValue("CircleRed", 255F, 0F, 255F)
    private val circleGreen = FloatValue("CircleGreen", 255F, 0F, 255F)
    private val circleBlue = FloatValue("CircleBlue", 255F, 0F, 255F)
    private val circleAlpha = FloatValue("CircleAlpha", 255F, 0F, 255F)
    private val circleAccuracy = FloatValue("CircleAccuracy", 15F, 0F, 60F)



    //打人光环显示
    private val mark = BoolValue("Mark", true)

    var target: EntityLivingBase? = null
    var click = 0
    var blocking = false

    @Event
    override fun onUpdate() {



        /*
        if (target!!.getDistanceToEntity(mc.player) <= rangeValue.get()) {
            setRotation(
                Rotation(
                    (RotationUtils.getAngles(target).yaw + Math.random() * 4f - 4f / 2).toFloat(),
                    (RotationUtils.getAngles(target).pitch + Math.random() * 4f - 4f / 2).toFloat()
                )
            )
            attackEntity()
        }

         */


        mc.world.loadedEntityList
            .asSequence()
            .filterIsInstance<EntityLivingBase>()
            .filter { mc.player.getDistanceToEntity(it) <= rangeValue.get() }
            .forEach {
                target = it
                setRotation(it)
                attackEntity(it)
            }

        if (target == null) {
            stopBlocking()
            click = 0
        }

    }

    @Event
    fun onRender3D() {

        if (circleValue.get()) {
            GL11.glPushMatrix()
            GL11.glTranslated(
                mc.player!!.lastTickPosX + (mc.player!!.posX - mc.player!!.lastTickPosX) * mc.timer.field_194147_b - mc.renderManager.renderPosX,
                mc.player!!.lastTickPosY + (mc.player!!.posY - mc.player!!.lastTickPosY) * mc.timer.field_194147_b - mc.renderManager.renderPosY,
                mc.player!!.lastTickPosZ + (mc.player!!.posZ - mc.player!!.lastTickPosZ) * mc.timer.field_194147_b - mc.renderManager.renderPosZ
            )
            GL11.glEnable(GL11.GL_BLEND)
            GL11.glEnable(GL11.GL_LINE_SMOOTH)
            GL11.glDisable(GL11.GL_TEXTURE_2D)
            GL11.glDisable(GL11.GL_DEPTH_TEST)
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA)

            GL11.glLineWidth(1F)
            GL11.glColor4f(
                circleRed.get() / 255.0F,
                circleGreen.get() / 255.0F,
                circleBlue.get() / 255.0F,
                circleAlpha.get() / 255.0F
            )
            GL11.glRotatef(90F, 1F, 0F, 0F)
            GL11.glBegin(GL11.GL_LINE_STRIP)

            for (i in 0..360 step (61 - circleAccuracy.get()).toInt()) { // You can change circle accuracy  (60 - accuracy)
                GL11.glVertex2f(
                    (cos(i * Math.PI / 180.0).toFloat() * rangeValue.get()),
                    ((sin(i * Math.PI / 180.0).toFloat() * rangeValue.get()))
                )
            }
            GL11.glVertex2f(
                (cos(360 * Math.PI / 180.0).toFloat() * rangeValue.get()),
                ((sin(360 * Math.PI / 180.0).toFloat() * rangeValue.get()))
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

    fun getRotationtoEntityYaw(entity: EntityLivingBase): Float {
        val xDiff: Double = entity.posX - mc.player.posX
        val zDiff: Double = entity.posZ - mc.player.posZ
        val yaw = (atan2(zDiff, xDiff) * 180.0 / Math.PI).toFloat() - 90.0f
        return yaw
    }

    fun getRotationtoEntityPitch(entity: EntityLivingBase): Float {
        val xDiff: Double = entity.posX - mc.player.posX
        val zDiff: Double = entity.posZ - mc.player.posZ
        val yDiff: Double =
            entity.posY + entity.eyeHeight * 0.9 - (mc.player.posY + mc.player.getEyeHeight())
        val distance: Float = MathHelper.sqrt(xDiff * xDiff + zDiff * zDiff)
        val pitch = (-(atan2(yDiff, distance.toDouble()) * 180.0 / Math.PI)).toFloat()
        return pitch
    }



    //  攻击
    private fun attackEntity(entity: Entity) {

        // 防止连续发送攻击包
        if (mc.player.attackingEntity != null) {
            return
        }

        if(!isFovInRange()) {
            return
        }

        drawEntityESP(entity, Color(255, 255, 255).rgb)

        // 停止防砍
        stopBlocking()

        // 注册AttackEvent
        EventManager().onAttack()

        mc.playerController.attackEntity(mc.player, entity)

    }


    //  转头
    fun setRotation(target: EntityLivingBase) {

        var yaw1 = 0F
        var pitch1 = 0F

        yaw1 =  mc.player.rotationYaw
        pitch1 = mc.player.rotationPitch

        mc.player.rotationYaw = getRotationtoEntityYaw(target)
        mc.player.rotationPitch = getRotationtoEntityPitch(target)

        mc.player.renderArmYaw = yaw1
        mc.player.renderYawOffset = yaw1
        mc.player.renderArmPitch = pitch1

    }

    fun stopBlocking() {

        if (blocking) {
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
            entity2.lastTickPosX + (entity2.posX - entity2.lastTickPosX) * mc.timer.field_194147_b - Minecraft.getMinecraft().renderManager.renderPosX
        val y: Double =
            entity2.lastTickPosY + (entity2.posY - entity2.lastTickPosY) * mc.timer.field_194147_b - Minecraft.getMinecraft().renderManager.renderPosY + sin(
                System.currentTimeMillis() / 200.0
            ) * (entity2.height / 2.0f) + 1.0f * (entity2.height / 2.0f)
        val z: Double =
            entity2.lastTickPosZ + (entity2.posZ - entity2.lastTickPosZ) * mc.timer.field_194147_b - Minecraft.getMinecraft().renderManager.renderPosZ
        var i = 0.0f
        while (i < 6.283185307179586) {
            val vecX = x + 0.67 * cos(i.toDouble())
            val vecZ = z + 0.67 * sin(i.toDouble())
            RenderUtils.glColor(
                Color(
                    RenderUtils.getColor(color).red,
                    RenderUtils.getColor(color).green,
                    RenderUtils.getColor(color).blue,
                    0
                ).rgb
            )
            GL11.glVertex3d(vecX, y - cos(System.currentTimeMillis() / 200.0) * (entity2.height / 2.0f) / 2.0, vecZ)
            RenderUtils.glColor(
                Color(
                    RenderUtils.getColor(color).red,
                    RenderUtils.getColor(color).green,
                    RenderUtils.getColor(color).blue,
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
