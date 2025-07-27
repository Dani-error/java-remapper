package dev.dani.remapper.util;

import dev.dani.remapper.model.*;
import lombok.experimental.UtilityClass;
import org.teavm.jso.core.JSArray;

/**
 * A utility class with methods to get mapped member's information.
 *
 * @author Dani-error
 * @since 27/7/25
 */
@UtilityClass
public class MappingUtil {

    public ClassMapping getClassMapping(MappingSet mappingSet, String obfuscatedName) {
        JSArray<ClassMapping> array = mappingSet.classes();
        for (int i = 0; i < array.getLength(); i++) {
            ClassMapping cls = array.get(i);
            if (cls.obfuscatedName().equals(obfuscatedName)) {
                return cls;
            }
        }
        return null;
    }

    public FieldMapping getField(ClassMapping classMapping, String obfuscatedName) {
        JSArray<FieldMapping> array = classMapping.fields();
        for (int i = 0; i < array.getLength(); i++) {
            FieldMapping field = array.get(i);
            if (field.obfuscatedName().equals(obfuscatedName)) {
                return field;
            }
        }
        return null;
    }

    public MethodMapping getMethod(ClassMapping classMapping, String obfuscatedName, String descriptor) {
        JSArray<MethodMapping> array = classMapping.methods();
        for (int i = 0; i < array.getLength(); i++) {
            MethodMapping method = array.get(i);
            if (method.obfuscatedName().equals(obfuscatedName)
                    && ((descriptor == null && method.descriptor() == null)
                    || (descriptor != null && descriptor.equals(method.descriptor())))) {
                return method;
            }
        }
        return null;
    }

    public ParameterMapping getParameterByIndex(MethodMapping methodMapping, int index) {
        JSArray<ParameterMapping> array = methodMapping.parameters();
        if (array == null) {
            return null;
        }
        for (int i = 0; i < array.getLength(); i++) {
            ParameterMapping param = array.get(i);
            if (param.index() == index) {
                return param;
            }
        }
        return null;
    }

    public ParameterMapping getParameterByName(MethodMapping methodMapping, String name) {
        JSArray<ParameterMapping> array = methodMapping.parameters();
        if (array == null) {
            return null;
        }
        for (int i = 0; i < array.getLength(); i++) {
            ParameterMapping param = array.get(i);
            if (param.obfuscatedName().equals(name)) {
                return param;
            }
        }
        return null;
    }

}
