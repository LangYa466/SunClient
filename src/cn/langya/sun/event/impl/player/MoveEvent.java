package cn.langya.sun.event.impl.player;

import cn.langya.sun.event.Event;
import cn.langya.sun.utils.MovementUtils;

public class MoveEvent extends Event {

    private double x, y, z;

    public MoveEvent(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    // @Exclude(Strategy.NAME_REMAPPING)
    public double getX() {
        return x;
    }

    // @Exclude(Strategy.NAME_REMAPPING)
    public void setX(double x) {
        this.x = x;
    }

    // @Exclude(Strategy.NAME_REMAPPING)
    public double getY() {
        return y;
    }

    // @Exclude(Strategy.NAME_REMAPPING)
    public void setY(double y) {
        this.y = y;
    }

    // @Exclude(Strategy.NAME_REMAPPING)
    public double getZ() {
        return z;
    }

    // @Exclude(Strategy.NAME_REMAPPING)
    public void setZ(double z) {
        this.z = z;
    }

    // @Exclude(Strategy.NAME_REMAPPING)
    public void setSpeed(double speed) {
        MovementUtils.setSpeed(this, speed);
    }

}
