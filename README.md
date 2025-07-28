# 🔖 java-remapper
A lightweight JavaScript/TypeScript tool for parsing and applying Java class mappings. It supports multiple mapping formats and allows remapping of Java `.class` files in the browser or Node.js.

---

### Features
- **Supports multiple mapping formats**:
  - `SRG/XSRG`
  - `CSRG/TSRG`
  - `TSRG v2`
  - `ProGuard`
  - `Tiny v1`
  - `Tiny v2`
- **Automatic format detection** based on file content
- **Remap `.class` files** from obfuscated to deobfuscated names using mappings
- **Works in Node.js and browser environments**

---

### Installation
```bash
npm install java-remapper
```

---

### Usage

#### **1. Detect mapping format**
```ts
import { detectMappingFormat } from "java-remapper";

const content = `
tiny\t2\t0\tnamed
c\texample/ClassName\tcom/example/MyClass
`;
const format = detectMappingFormat(content);
console.log(format); // Output: MappingFormat.TINY2
```

---

#### **2. Parse mappings**
```ts
import { parseMappings, MappingFormat } from "java-remapper";

const mappingsContent = "..."; // Mapping file content
const mappings = parseMappings(MappingFormat.TINY2, mappingsContent);
```

---

#### **3. Remap `.class` files**
```ts
import { remap } from "java-remapper";
import fs from "fs";

const classBytes = fs.readFileSync("Example.class");
const newBytes = await remap(classBytes, mappings);
fs.writeFileSync("ExampleRemapped.class", newBytes);
```

---

#### **4. Types**
```ts
import { MappingSet } from "java-remapper";
```

---

### 🛠 API
#### `detectMappingFormat(content: string): MappingFormat | null`
Detects the mapping format from content.

#### `parseMappings(format: MappingFormat, content: string): MappingSet`
Parses a mapping file into a `MappingSet`.

#### `remap(b: Uint8Array, mappings: MappingSet): Promise<Uint8Array>`
Remaps a Java `.class` file byte array using the given mappings.

---

### Notes
- This library only remaps **Java class files**, not full JARs. To handle JARs, extract `.class` files, remap them individually, and repackage.
- Ensure mappings and class files match the same version of obfuscation/deobfuscation.

---

### License
This project is licensed under the MIT License.
