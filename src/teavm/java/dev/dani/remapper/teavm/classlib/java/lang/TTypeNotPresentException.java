package dev.dani.remapper.teavm.classlib.java.lang;

import org.teavm.classlib.java.lang.TRuntimeException;
import org.teavm.classlib.java.lang.TThrowable;

import java.io.Serial;

/**
 * Exception thrown when a specified type is not present.
 * <p>
 * This class is intended to fix class not found errors in TeaVM runtime
 * by providing a compatible implementation of {@code TypeNotPresentException}.
 * </p>
 *
 * @author Dani-error
 * @since 27/07/2025
 */
public class TTypeNotPresentException extends TRuntimeException {

    @Serial
    private static final long serialVersionUID = -5101214195716534496L;

    private final String typeName;

    public TTypeNotPresentException(String typeName, TThrowable cause) {
        super("Type " + typeName + " not present", cause);
        this.typeName = typeName;
    }

    public String typeName() { return typeName;}
}