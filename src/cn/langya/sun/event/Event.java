package cn.langya.sun.event;


public class Event {
    private boolean cancelled;
    public void cancel() {
        this.cancelled = true;
    }

    public static class StateEvent extends Event {
        private boolean pre = true;
        public boolean isPre() { return pre;}
        public boolean isPost() { return !pre;}
        public void setPost() { pre = false; }
    }
}
