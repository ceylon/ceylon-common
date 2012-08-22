package com.redhat.ceylon.common.log;

import com.redhat.ceylon.common.config.CeylonConfig;

/**
 * <p>The factory used to create {@link Log} instances.</p>
 * 
 * <p>The factory methods used to create {@link Log} instances 
 * are {@link #getLog(String)} and {@link #getLog(Class)}. It is implementation 
 * dependent whether these return new instances each time they are called with 
 * the same argument.</p>
 * 
 * <h3>Configuring a LogFactory</h3>
 * 
 * <ol>
 * <li>If {@link #setFactory(LogFactory)} has been called then that instance 
 *     is used.</li>
 * <li>Otherwise, if the {@code logging.factory} property of the default 
 *     {@linkplain CeylonConfig configuration} is not null it is assumed to be 
 *     the name of a subclass of {@code LogFactory}, and an instance is 
 *     created using the nullary constructor. If an instance is successfully 
 *     created it is used as the {@code LogFactory}.</li> 
 * <li>Otherwise (if the {@code logging.factory} property was null, or an 
 *     instance could not be created), an instance of the LogFactory itself is 
 *     used as the {@code LogFactory}.</li>
 * <li>If instantiation of the given {@code logging.factory} was attempted
 *     and failed, a {@linkplain Log#error(String, Throwable) error} is 
 *     logged</li>
 * </ol>
 * 
 * <p>Note: An application seeking to replace the default {@code LogFactory} with its 
 * own implementation by calling {@link #setFactory(LogFactory)}, but it must 
 * do so before the {@code Log} factory methods are called, (i.e. before any log 
 * messages are generated).</p>
 *  
 * @author tom
 */
public class LogFactory {

    protected LogFactory() {
    }
    
    private static LogFactory logFactory = null;
    
    private static synchronized LogFactory getFactory() {
        if (logFactory == null) {
            String factoryClassName = CeylonConfig.get("logging.factory");
            if (factoryClassName != null) {
                try {
                    logFactory = (LogFactory) Class.forName(factoryClassName).newInstance();
                } catch (Exception e) {
                    DefaultLog.getInstance().error("Error initializing logging factory", e);
                }
            }
            if (logFactory == null) {
                logFactory = new LogFactory();
            }
        }
        return logFactory;
    }

    /**
     * Sets the factory to use to create {@link Log} instances. This must be 
     * before any Log instances are created.
     * @param factory
     */
    public static synchronized void setFactory(LogFactory factory) {
        if (logFactory != null) {
            throw new RuntimeException("Too late!");
        }
        logFactory = factory;
    }
    
    protected Log createLog(String category) {
        return DefaultLog.getInstance();
    }
 
    public static Log getLog(String category) {
        return getFactory().createLog(category);
    }
    
    public static Log getLog(Class<?> cls) {
        return getLog(cls.getName());
    }
    
}
