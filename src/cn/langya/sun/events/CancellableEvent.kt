package cn.langya.sun.events

abstract class CancellableEvent {

    fun setCancelled(cancelled: Boolean) {
        isCancelled = cancelled
    }

    fun cancelEvent() {
        isCancelled = true
    }

    companion object {
        @JvmField
        var isCancelled = false
    }

}
