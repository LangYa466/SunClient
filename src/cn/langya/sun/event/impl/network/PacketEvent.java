package cn.langya.sun.event.impl.network;

import cn.langya.sun.event.Event;
import net.minecraft.network.Packet;



public class PacketEvent extends Event {
    private Packet<?> packet;

    public PacketEvent(Packet<?> packet) {
        this.packet = packet;
    }

    // @Exclude(Strategy.NAME_REMAPPING)
    public Packet<?> getPacket() {
        return packet;
    }

    // @Exclude(Strategy.NAME_REMAPPING)
    public void setPacket(Packet<?> packet) {
        this.packet = packet;
    }

    // @Exclude(Strategy.NAME_REMAPPING)

}
