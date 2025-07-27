package dev.dani.remapper.model;

import org.teavm.jso.JSBody;
import org.teavm.jso.JSObject;
import org.teavm.jso.core.JSArray;

/**
 * Represents the root mapping set containing all class mappings.
 * <p>
 * Provides access to the array of all mapped classes.
 * </p>
 *
 * @author Dani-error
 * @since 27/07/2025
 */
public interface MappingSet extends JSObject {

    @JSBody(script = "return this.classes || [];")
    JSArray<ClassMapping> classes();

}