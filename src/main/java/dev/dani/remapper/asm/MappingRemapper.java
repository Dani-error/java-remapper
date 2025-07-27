package dev.dani.remapper.asm;

import dev.dani.remapper.util.MappingUtil;
import lombok.RequiredArgsConstructor;
import org.objectweb.asm.commons.Remapper;
import dev.dani.remapper.model.*;

/**
 * A remapper that uses a MappingSet to translate obfuscated names
 * into their deobfuscated equivalents.
 * <p>
 * Extends ASM's {@link Remapper} and overrides methods to remap
 * class names, field names, method names, and parameter names
 * based on the provided mappings.
 * </p>
 *
 * @author Dani-error
 * @since 27/7/25
 */
@RequiredArgsConstructor
public class MappingRemapper extends Remapper {

    private final MappingSet mappingSet;

    @Override
    public String map(String internalName) {
        ClassMapping classMapping = MappingUtil.getClassMapping(mappingSet, internalName.replace('/', '.'));
        if (classMapping != null) {
            return classMapping.deobfuscatedName().replace('.', '/');
        }
        return internalName;
    }

    @Override
    public String mapFieldName(String owner, String name, String descriptor) {
        ClassMapping classMapping = MappingUtil.getClassMapping(mappingSet, owner.replace('/', '.'));
        if (classMapping != null) {
            FieldMapping field = MappingUtil.getField(classMapping, name);
            if (field != null) {
                return field.deobfuscatedName();
            }
        }
        return name;
    }

    @Override
    public String mapMethodName(String owner, String name, String descriptor) {
        ClassMapping classMapping = MappingUtil.getClassMapping(mappingSet, owner.replace('/', '.'));
        if (classMapping != null) {
            MethodMapping method = MappingUtil.getMethod(classMapping, name, descriptor);
            if (method != null) {
                return method.deobfuscatedName();
            }
        }
        return name;
    }

    public String mapParameterName(String owner, String methodName, String desc, String currentName) {
        ClassMapping classMapping = MappingUtil.getClassMapping(mappingSet, owner.replace('/', '.'));
        if (classMapping != null) {
            MethodMapping method = MappingUtil.getMethod(classMapping, methodName, desc);
            if (method != null) {
                ParameterMapping param = MappingUtil.getParameterByName(method, currentName);

                if (param != null) {
                    return param.deobfuscatedName();
                }
            }
        }
        return currentName;
    }

    public String mapParameterNameByIndex(String owner, String methodName, String desc, String currentName, int index) {
        ClassMapping classMapping = MappingUtil.getClassMapping(mappingSet, owner.replace('/', '.'));
        if (classMapping != null) {
            MethodMapping method = MappingUtil.getMethod(classMapping, methodName, desc);
            if (method != null) {
                ParameterMapping param = MappingUtil.getParameterByIndex(method, index);

                if (param != null) {
                    return param.deobfuscatedName();
                }
            }
        }
        return currentName;
    }

}
