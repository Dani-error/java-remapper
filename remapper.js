let remapFunc = null;

export const remap = async (b, mappings) => {
    if (!remapFunc) {
        const { load } = await import("./runtime.js");
        const { exports } = await load(new URL("./java-remapper.wasm", import.meta.url).href);

        remapFunc = exports.remap;
    }

    return remapFunc(b, mappings);
};