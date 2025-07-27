package dev.dani.remapper.model;

import org.teavm.jso.JSBody;
import org.teavm.jso.JSObject;

/**
 * Represents the mapping of a parameter in a remapped method.
 * <p>
 * This interface accesses JavaScript properties (using TeaVM)
 * to retrieve information about the parameter’s index and its obfuscated
 * and deobfuscated names.
 * </p>
 *
 * @author Dani-error
 * @since 27/7/25
 */
public interface ParameterMapping extends JSObject {
    @JSBody(script = "return this.index;")
    int index();

    @JSBody(script = "return this.obfuscatedName;")
    String obfuscatedName();

    @JSBody(script = "return this.deobfuscatedName;")
    String deobfuscatedName();
}