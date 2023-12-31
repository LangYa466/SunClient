package cn.langya.sun.events.impl;

import net.minecraft.network.Packet;

/**
 * @author LangYa
 * @ClassName PacketSendEvent
 * @date 2023/12/31 9:44
 * @Version 1.0
 */

public class PacketSendEvent {
    public Packet packet;
    public boolean cancelled;

    public PacketSendEvent(final Packet packet) {
        this.packet = packet;
    }

    public void cancel() {
        this.cancelled = true;
    }


}
