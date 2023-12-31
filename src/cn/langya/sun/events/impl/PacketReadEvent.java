package cn.langya.sun.events.impl;

import net.minecraft.network.Packet;

/**
 * @author LangYa
 * @ClassName PacketReadEvent
 * @date 2023/12/31 9:55
 * @Version 1.0
 */

public class PacketReadEvent {
    public Packet packet;

    public PacketReadEvent(final Packet packet) {
        this.packet = packet;
    }
}
