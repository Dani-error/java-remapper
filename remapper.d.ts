declare module "java-remapper" {
    export interface MappingSet {
        classes: ClassMapping[];
    }

    export interface ClassMapping {
        obfuscatedName: string;
        deobfuscatedName: string;
        fields: FieldMapping[];
        methods: MethodMapping[];
    }

    export interface FieldMapping {
        obfuscatedName: string;
        deobfuscatedName: string;
        descriptor?: string;
    }

    export interface MethodMapping {
        obfuscatedName: string;
        deobfuscatedName: string;
        descriptor?: string;
        parameters?: ParameterMapping[];
    }

    export interface ParameterMapping {
        index: number;
        obfuscatedName: string;
        deobfuscatedName: string;
    }

    export function remap(b: Uint8Array, mappings: MappingSet): Promise<Uint8Array>;
}