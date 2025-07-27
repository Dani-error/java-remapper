package dev.dani.remapper;

import dev.dani.remapper.asm.MappingRemapper;
import dev.dani.remapper.asm.parameter.ParameterAwareClassRemapper;
import dev.dani.remapper.model.MappingSet;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;

/**
 * Utility class for remapping Java class bytecode using a given mapping set.
 * <p>
 * Uses OW2's ASM library to read class bytes, apply name remapping (including parameters),
 * and produce remapped class bytecode.
 * </p>
 *
 * @author Dani-error
 * @since 27/7/25
 */
public class ClassFileRemapper {

    private final MappingRemapper remapper;

    public ClassFileRemapper(MappingSet mappingSet) {
        this.remapper = new MappingRemapper(mappingSet);
    }

    public byte[] remapClass(byte[] classBytes) {
        ClassReader reader = new ClassReader(classBytes);
        ClassWriter writer = new ClassWriter(0);
        ClassVisitor remapVisitor = new ParameterAwareClassRemapper(writer, remapper);
        reader.accept(remapVisitor, 0);
        return writer.toByteArray();
    }

}
