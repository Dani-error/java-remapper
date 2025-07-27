package dev.dani.remapper.model;

import org.teavm.jso.JSBody;
import org.teavm.jso.JSObject;
import org.teavm.jso.core.JSArray;

/**
 * Represents the mapping of a method in a remapped class.
 * <p>
 * This interface accesses JavaScript properties (using TeaVM)
 * to get the method’s obfuscated name, deobfuscated name,
 * descriptor, and parameters.
 * </p>
 *
 * @author Dani-error
 * @since 27/7/25
 */
public interface MethodMapping extends JSObject {
    @JSBody(script = "return this.obfuscatedName;")
    String obfuscatedName();

    @JSBody(script = "return this.deobfuscatedName;")
    String deobfuscatedName();

    @JSBody(script = "return this.descriptor || null;")
    String descriptor();

    @JSBody(script = "return this.parameters || [];")
    JSArray<ParameterMapping> parameters();
}