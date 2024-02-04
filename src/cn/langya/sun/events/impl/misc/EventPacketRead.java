package cn.langya.sun.events.impl.misc;

import com.cubk.event.impl.Event;
import net.minecraft.network.Packet;

/**
 * @author LangYa
 * @ClassName PacketReadEvent
 * @date 2023/12/31 9:55
 * @Version 1.0
 */

public class EventPacketRead implements Event {
    public Packet packet;
    public boolean cancelled;

    public EventPacketRead(final Packet packet) {
        this.packet = packet;
    }
    public void cancel() {
        this.cancelled = true;
    }
}
