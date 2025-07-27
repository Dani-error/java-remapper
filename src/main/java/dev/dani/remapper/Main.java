package dev.dani.remapper;


import dev.dani.remapper.model.MappingSet;
import org.teavm.jso.JSExport;
import org.teavm.jso.core.JSObjects;

/**
 * Main entry point for the remapper.
 * <p>
 * Exposes a static method to remap class bytecode given a
 * {@link MappingSet}, intended for JS interoperability via TeaVM.
 * </p>
 *
 * @author Dani-error
 * @since 27/7/25
 */
public class Main {

    @JSExport
    public static byte[] remap(byte[] b, MappingSet mappings) {
        ClassFileRemapper remapper = new ClassFileRemapper(mappings == null || JSObjects.isUndefined(mappings) ? JSObjects.create() : mappings);
        return remapper.remapClass(b);
    }

}
