package cn.langya.sun.modules.impl.combat;

import cn.enaium.cf4m.annotation.Event
import cn.langya.sun.Sun
import cn.langya.sun.events.impl.AttackEvent
import cn.langya.sun.events.impl.Render3DEvent
import cn.langya.sun.events.impl.UpdateEvent
import cn.langya.sun.modules.Category
import cn.langya.sun.modules.Module
import cn.langya.sun.values.BoolValue
import cn.langya.sun.values.FloatValue
import net.minecraft.entity.Entity
import net.minecraft.network.play.client.CPacketPlayerDigging
import net.minecraft.util.EnumFacing
import net.minecraft.util.math.BlockPos
import org.lwjgl.opengl.GL11
import kotlin.math.cos
import kotlin.math.sin


class KillAura : Module("杀人气质", true, Category.Combat) {

    //攻击距离
    private val rangeValue = FloatValue("Range", 3F, 3F, 8F)

    //距离光环显示
    private val circleValue = BoolValue("Circle", true)
    private val circleRed = FloatValue("CircleRed", 255F, 0F, 255F)
    private val circleGreen = FloatValue("CircleGreen", 255F, 0F, 255F)
    private val circleBlue = FloatValue("CircleBlue", 255F, 0F, 255F)
    private val circleAlpha = FloatValue("CircleAlpha", 255F, 0F, 255F)
    private val circleAccuracy = FloatValue("CircleAccuracy", 15F, 0F, 60F)

    var target: Entity? = null
    var click = 0
    private var blocking = false

    @Event
    fun onUpdate(event: UpdateEvent) {

        if (mc.player == null || mc.world == null) return


        for (entity in mc.world.loadedEntityList) {
            if (mc.player.getDistanceToEntity(entity) <= rangeValue.get()) {
                target = entity
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


    //  攻击
    private fun attackEntity(entity: Entity) {

        // 防止连续发送攻击包
        if (mc.player.attackingEntity != null) {
            return
        }

        // 停止防砍
        stopBlocking()

        // 注册AttackEvent
        Sun.eventManager.post(AttackEvent())

        mc.playerController.attackEntity(mc.player, entity)

    }


    private fun stopBlocking() {

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

}