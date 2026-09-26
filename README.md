# GroupDocs Viewer Java – Auto-Detect File Format and Render

## Overview
This showcase project demonstrates how to use **GroupDocs Viewer** (Java) to detect a document format from a file stream and render it to HTML. It is a minimal, runnable Maven project that helps developers understand how to open a document when the format is not taken from the file path.

## Prerequisites
- **Java Development Kit (JDK) 8** or higher
- **Apache Maven** 3.5+ installed and added to your `PATH`
- An **Internet connection** for Maven to download dependencies

## License
GroupDocs Viewer requires a license file to work without evaluation limitations (watermarks, page limits, etc.).

1. **Obtain a temporary license** – Visit https://purchase.groupdocs.com/temporary-license/ to get a free 30‑day temporary license.
2. **Place the license file** – Save the downloaded `GroupDocs.Viewer.Java.lic` file in the **project root directory** (the same level where `pom.xml` resides).
3. **Without a license** – The library will run in evaluation mode which adds watermarks to rendered pages and may impose other restrictions.

## Project Structure
```
auto-detect-file-format-render-groupdocs-viewer-java/
│   pom.xml
│   README.md
│   GroupDocs.Viewer.Java.lic   (optional – place your license here)
│
├───src
│   └───main
│       └───java
│           └───com
│               └───groupdocs
│                   └───viewer
│                       └───examples
│                           └───AutoDetectFileFormatRenderExample.java
│
├───resources
│   ├───input
│   │   └───sample.pdf           (sample PDF – replace with your own)
│   └───output                   (generated HTML pages will be saved here)
```

## Setup & Run
1. **Clone or copy the project**
   ```bash
   git clone https://github.com/your-repo/auto-detect-file-format-render-groupdocs-viewer-java.git
   cd auto-detect-file-format-render-groupdocs-viewer-java
   ```
2. **Add your license file** (optional but recommended) – copy `GroupDocs.Viewer.Java.lic` into the project root.
3. **Place a document** you want to render inside `resources/input/`. The project already contains `sample.pdf`, which you can replace.
4. **Build the project**
   ```bash
   mvn clean compile
   ```
5. **Run the example**
   - Using Maven Exec plugin:
     ```bash
     mvn exec:java
     ```

## What Happens When You Run It?
- The program loads the license (if present).
- It checks `resources/input/sample.pdf`. If the file is missing, execution stops with an error message.
- An output directory `resources/output/` is created if it does not already exist.
- The file is opened as a stream. `FileType.fromStream` reads the file signature and prints the detected type.
- The detected type is passed to `LoadOptions`, and the same bytes are rendered to HTML with names like `page_1.html`, `page_2.html`, and so on.
- Console output informs you about the progress and any possible errors.

## Customization
- **Change output format** – replace `HtmlViewOptions` with `PdfViewOptions` or `PngViewOptions` and adjust the output file name.
- **Render another document** – replace `resources/input/sample.pdf` with another supported file, or change `INPUT_FILE` in the source.
- **Render a page range** – call `viewer.view(options, 1, 2)` to render the first two pages instead of the whole document.

## Notes
- The included `sample.pdf` is a real PDF document, so format detection and HTML rendering can be checked immediately.
- Detection uses a separate stream from rendering. `FileType.fromStream` reads the signature and does not rewind the stream.
- Ensure the `resources/input/` and `resources/output/` directories are at the same level as `pom.xml` as shown in the structure above.

---

*This is a showcase project for GroupDocs Viewer. It is intended for demonstration and educational purposes.*
