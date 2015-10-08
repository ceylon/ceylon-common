package com.redhat.ceylon.common.tool;

/**
 * Interface for {@code Exception} classes which need to have control 
 * over how the ceylon tool reports the exception.
 * 
 * @see FatalToolError
 */
public abstract class ToolError extends RuntimeException {

    private int exitCode = -1;
    
    public ToolError(String message, Throwable cause) {
        super(message, cause);
    }

    public ToolError(String message) {
        super(message);
    }

    public ToolError(String message, int exitCode) {
        super(message);
        this.exitCode = exitCode;
    }

    public ToolError(Throwable cause) {
        super(cause);
    }

    public boolean getShowStacktrace() {
        return false;
    }
    
    /**
     * Gets the error message, which should be a single line of text.
     * This is the message which should be printed to stdout/stderr
     * and as such may be distinct from the exception's 
     * {@link Throwable#getMessage()} or {@link Throwable#getLocalizedMessage()}.
     */
    public String getErrorMessage() {
        return getLocalizedMessage();
    }

    public boolean isExitCodeProvided(){
        return exitCode != -1;
    }

    public int getExitCode(){
        return exitCode;
    }
}
