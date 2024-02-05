package cn.langya.sun.command;

public interface Command {
    boolean run(String[] var1);

    String usage();
}

