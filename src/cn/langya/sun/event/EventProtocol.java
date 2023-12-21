package cn.langya.sun.event;

import cn.langya.sun.utils.ClientUtils;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author cedo
 * @since 04/18/2022
 */
public class EventProtocol {
    // If multi-threading you can use a CopyOnWriteArrayList instead of a regular ArrayList
    private final List<EventListener> listeners = new CopyOnWriteArrayList<>();


    /**
     * Register the specified listener
     *
     * @param listener The listener to register
     * @throws IllegalArgumentException If the listener does not implement EventListener
     */
    public void register(Object listener) {
        if (!(listener instanceof EventListener)) {
            throw new IllegalArgumentException("Listener must implement EventListener");
        }
        listeners.add((EventListener) listener);
    }

    /**
     * Unregister the specified listener
     *
     * @param listener The listener to remove
     * @throws IllegalArgumentException If the listener does not implement EventListener
     */
    public void unregister(Object listener) {
        if (!(listener instanceof EventListener)) {
            throw new IllegalArgumentException("Listener must implement EventListener");
        }
        listeners.remove((EventListener) listener);
    }

    public void handleEvent(Event event) {
        for (EventListener listener : listeners) {
            try {
                listener.onEvent(event);
                ClientUtils.loginfo("Event " + event.getClass().getSimpleName() + "handled by listener " + listener.getClass().getSimpleName());
            } catch (Exception exception) {
                exception.printStackTrace();
                ClientUtils.loginfo("Error handling event " + event.getClass().getSimpleName() + " for listener " + listener.getClass().getSimpleName());
            }
        }
    }


}
