package common;

public class ConfigLoadException extends RuntimeException {
    public ConfigLoadException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
