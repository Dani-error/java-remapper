package dev.dani.remapper.model;

import org.teavm.jso.JSBody;
import org.teavm.jso.JSObject;
import org.teavm.jso.core.JSArray;

/**
 * Represents the mapping of a class.
 * <p>
 * Provides access to the class’s obfuscated and deobfuscated names,
 * along with its fields and methods mappings.
 * </p>
 *
 * @author Dani-error
 * @since 27/7/25
 */
public interface ClassMapping extends JSObject {
    @JSBody(script = "return this.obfuscatedName;")
    String obfuscatedName();

    @JSBody(script = "return this.deobfuscatedName;")
    String deobfuscatedName();

    @JSBody(script = "return this.fields || [];")
    JSArray<FieldMapping> fields();

    @JSBody(script = "return this.methods || [];")
    JSArray<MethodMapping> methods();
}