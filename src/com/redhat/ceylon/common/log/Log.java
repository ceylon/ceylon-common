package com.redhat.ceylon.common.log;

/**
 * <p>A very simple log facade.</p>
 * 
 * <p>At the point of use a {@code Log} instance is created like this:</p>
 * <pre>
 * Log log = LogFactory.getLog("foo");
 * // or
 * log = LogFactory.getLog(Foo.class);
 * </pre>
 * 
 * @see LogFactory
 * @author tom
 */
public interface Log {

    /** 
     * Log an error. 
     * Do this if something is definitely wrong,  but the program can continue.
     * If the program cannot continue, you should throw an exception.
     */
    public void error(String str, Throwable cause);

    /**
     * Log a warning.  
     * Do this if something is probably wrong, but the program can continue.
     * If something is definitely wrong, call 
     * {@link #error(String, Throwable)} instead.
     */
    public void warning(String str, Throwable cause);

    /** 
     * Log some information.
     * Do this if nothing is wrong, but the user might be interested.
     * If something is probably wrong call 
     * {@link #warning(String, Throwable)} instead. 
     */
    public void info(String str, Throwable cause);

    /** 
     * Log some debugging information.
     * Do this if nothing is wrong, but the message could be useful to 
     * someone debugging a problem.
     * If the user might be interested call 
     * {@link #info(String, Throwable)} instead.
     */
    public void debug(String str, Throwable cause);
    
}
