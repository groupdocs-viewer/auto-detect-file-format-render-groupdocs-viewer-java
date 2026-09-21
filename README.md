# Auto-Detect File Format and Render with GroupDocs Viewer for Java

This repository demonstrates auto-detecting file formats and rendering documents to HTML using GroupDocs Viewer for Java. The solution leverages the unified API to handle over 100 file formats—including DOCX, XLSX, PDF, and more—without requiring explicit format specification.

## Problem
Developers often need to support multiple document formats in their applications but face complexity in managing format-specific rendering logic. Manually detecting and routing each file type increases code maintenance and reduces scalability.

## What This Example Shows
This example shows how GroupDocs Viewer for Java automatically detects the file format from the input document and renders it to HTML using a single, consistent API. It demonstrates:
- Automatic format detection via the `Viewer(String filePath)` constructor
- Rendering to HTML with embedded resources (CSS, fonts, images)
- Logging detected file type for transparency
- Minimal code footprint for robust multi-format support

## Expected Outcome
After running this example, you’ll see HTML output files generated in `resources/output/`, regardless of whether the input was a Word, Excel, PDF, or other supported format. The process requires no manual format detection or conditional logic.

## How it works

```mermaid
flowchart LR
    A[Input: document.<ext>] --> B[Viewer(String filePath)]
    B --> C[viewer.getFileInfo()]
    C --> D[Detect FileType]
    D --> E[HtmlViewOptions.forEmbeddedResources()]
    E --> F[viewer.view(HtmlViewOptions)]
    F --> G[Output: HTML files in resources/output/]
```

**Important:** GroupDocs Viewer auto-detects formats based on file extension. Ensure your input files have valid extensions (e.g., `.docx`, `.xlsx`, `.pdf`). If extensions are missing or ambiguous, explicitly specify `FileType` via `LoadOptions`.

## Topics covered
Implement an auto-detect feature to render any file stream to the correct viewer type.

## Prerequisites
- Java 8 or higher
- Maven 3.x
- Supported document files placed in `resources/input/`

## License
This example uses GroupDocs Viewer for Java. For full functionality, apply a license. Get a [free 30-day temporary license](https://purchase.groupdocs.com/temporary-license) or [contact sales](https://purchase.groupdocs.com/).

## Setup & Run
1. Clone the repository.
2. Place a supported document (e.g., `sample.docx`) in `resources/input/`.
3. Run:
   ```bash
   mvn compile exec:java
   ```
4. View output in `resources/output/`.

## FAQ

**Q: Does GroupDocs Viewer support auto-detection for all 100+ formats?**
A: Yes. The `Viewer` constructor automatically detects formats based on file extension for over 100 document types, including Office, PDF, CAD, email, and image formats.

**Q: What happens if the file extension is missing or incorrect?**
A: Auto-detection may fail. In such cases, use `LoadOptions` with an explicit `FileType` to force format interpretation.

**Q: Can I render to PDF or images instead of HTML?**
A: Yes. Replace `HtmlViewOptions` with `PdfViewOptions` or `PngViewOptions`—the auto-detection logic remains unchanged.

For full documentation, see [GroupDocs Viewer for Java docs](https://docs.groupdocs.com/viewer/java/).
