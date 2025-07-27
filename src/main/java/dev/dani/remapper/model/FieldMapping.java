package dev.dani.remapper.model;

import org.teavm.jso.JSBody;
import org.teavm.jso.JSObject;

/**
 * Represents the mapping of a field in a remapped class.
 * <p>
 * Provides access to the field’s obfuscated name, deobfuscated name,
 * and its descriptor (type signature).
 * </p>
 *
 * @author Dani-error
 * @since 27/7/25
 */
public interface FieldMapping extends JSObject {
    @JSBody(script = "return this.obfuscatedName;")
    String obfuscatedName();

    @JSBody(script = "return this.deobfuscatedName;")
    String deobfuscatedName();

    @JSBody(script = "return this.descriptor || null;")
    String descriptor();
}
