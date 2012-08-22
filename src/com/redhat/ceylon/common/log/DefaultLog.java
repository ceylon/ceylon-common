package com.redhat.ceylon.common.log;

import java.io.PrintStream;
import java.util.concurrent.atomic.AtomicReference;

/**
 * A default implementation of {@link Log} which prints to standard error.
 * This is probably suitable for most command line applications. 
 * Other applications should set a {@link LogFactory} so that they can do 
 * something suitable.
 * @author tom
 */
public class DefaultLog implements Log {

    private static enum Level {
        OFF,
        ERROR,
        WARN,
        INFO,
        DEBUG
    }
    
    private static AtomicReference<Level> LEVEL = new AtomicReference<DefaultLog.Level>(Level.WARN);
    
    public static void setLevel(Level level) {
        DefaultLog.LEVEL.set(level);
    }
    
    private final PrintStream out = System.err;
    
    private DefaultLog() {
    }
    
    protected boolean isEnabled(Level level) {
        return level.ordinal() <= this.LEVEL.get().ordinal(); 
    }

    @Override
    public void error(String message, Throwable cause) {
        if (isEnabled(Level.ERROR)) {
            out.println(message);     
            if (cause != null) {
                cause.printStackTrace(out);
            }
        }
    }

    @Override
    public void warning(String message, Throwable cause) {
        if (isEnabled(Level.WARN)) {
            out.println(message);     
            if (cause != null) {
                cause.printStackTrace(out);
            }
        }
    }

    @Override
    public void info(String message, Throwable cause) {
        if (isEnabled(Level.INFO)) {
            out.println(message);     
            if (cause != null) {
                cause.printStackTrace(out);
            }
        }
    }

    @Override
    public void debug(String message, Throwable cause) {
        if (isEnabled(Level.DEBUG)) {
            out.println(message);     
            if (cause != null) {
                cause.printStackTrace(out);
            }
        }
    }

    private static DefaultLog instance = null;
    static synchronized DefaultLog getInstance() {
        if (instance == null) {
            instance = new DefaultLog();
        }
        return instance;
    }

}
