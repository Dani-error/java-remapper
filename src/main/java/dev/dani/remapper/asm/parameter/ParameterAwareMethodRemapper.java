package dev.dani.remapper.asm.parameter;

import dev.dani.remapper.asm.MappingRemapper;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;

/**
 * A {@link MethodVisitor} that remaps method parameter names
 * using a {@link MappingRemapper}.
 * <p>
 * It overrides parameter and local variable visits to replace
 * obfuscated parameter names with their deobfuscated counterparts,
 * based on the class, method name, and descriptor.
 * </p>
 *
 * @author Dani-error
 * @since 27/7/25
 */
public class ParameterAwareMethodRemapper extends MethodVisitor {
    private final MappingRemapper mappingRemapper;
    private final String owner;
    private final String methodName;
    private final String descriptor;

    public ParameterAwareMethodRemapper(int api, MethodVisitor mv, MappingRemapper remapper,
                                        String owner, String methodName, String descriptor) {
        super(api, mv);
        this.mappingRemapper = remapper;
        this.owner = owner;
        this.methodName = methodName;
        this.descriptor = descriptor;
    }

    @Override
    public void visitParameter(String name, int access) {
        String newName = mappingRemapper.mapParameterName(owner, methodName, descriptor, name);
        super.visitParameter(newName, access);
    }

    @Override
    public void visitLocalVariable(String name, String desc, String signature, Label start, Label end, int index) {
        String newName = mappingRemapper.mapParameterNameByIndex(owner, methodName, descriptor, name, index);
        super.visitLocalVariable(newName, desc, signature, start, end, index);
    }
}