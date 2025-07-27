package dev.dani.remapper.asm.parameter;

import dev.dani.remapper.asm.MappingRemapper;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.commons.ClassRemapper;

/**
 * A {@link ClassRemapper} that uses a {@link MappingRemapper}
 * and delegates method visits to {@link ParameterAwareMethodRemapper}
 * for remapping parameter names.
 * <p>
 * Enhances class remapping by enabling parameter-aware method remapping.
 * </p>
 *
 * @author Dani-error
 * @since 27/7/25
 */
public class ParameterAwareClassRemapper extends ClassRemapper {

    private final MappingRemapper mappingRemapper;

    public ParameterAwareClassRemapper(ClassVisitor cv, MappingRemapper remapper) {
        super(cv, remapper);
        this.mappingRemapper = remapper;
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        MethodVisitor mv = super.visitMethod(access, name, descriptor, signature, exceptions);
        return new ParameterAwareMethodRemapper(api, mv, mappingRemapper, this.className, name, descriptor);
    }
}