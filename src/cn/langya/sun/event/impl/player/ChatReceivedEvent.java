package cn.langya.sun.event.impl.player;

import cn.langya.sun.event.Event;
import lombok.Getter;
import net.minecraft.util.text.ITextComponent;


public class ChatReceivedEvent extends Event {

    /**
     * Introduced in 1.8:
     * 0 : Standard Text Message
     * 1 : 'System' message, displayed as standard text.
     * 2 : 'Status' message, displayed above action bar, where song notifications are.
     */
    public final byte type;
    public ITextComponent message;
    // @Exclude(Strategy.NAME_REMAPPING)
    @Getter
    private final String rawMessage;

    public ChatReceivedEvent(byte type, ITextComponent message) {
        this.type = type;
        this.message = message;
        this.rawMessage = message.getUnformattedText();
    }

}
