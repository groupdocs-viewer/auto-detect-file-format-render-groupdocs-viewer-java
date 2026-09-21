package com.groupdocs.viewer.examples;

import com.groupdocs.viewer.Viewer;
import com.groupdocs.viewer.options.HtmlViewOptions;
import com.groupdocs.viewer.options.LoadOptions;
import com.groupdocs.viewer.results.FileInfo;
import com.groupdocs.viewer.FileType;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Demonstrates auto-detecting file format and rendering documents to HTML using GroupDocs Viewer for Java.
 * <p>
 * Key features:
 * <ul>
 *   <li>Automatically detects file format from input stream or file path</li>
 *   <li>Supports over 100 document formats (DOCX, XLSX, PDF, etc.)</li>
 *   <li>Renders to HTML with embedded resources</li>
 *   <li>Uses GroupDocs Viewer's built-in auto-detection via Viewer constructor</li>
 * </ul>
 */
public class AutoDetectFileFormatRenderExample {

    /**
     * Sets up license for GroupDocs.Viewer.
     * Uses temporary license if available; otherwise falls back to evaluation mode.
     * See: https://purchase.groupdocs.com/temporary-license
     */
    private static void setupLicense() {
        try {
            String licensePath = "resources/groupdocs.viewer.lic";
            File licenseFile = new File(licensePath);
            if (licenseFile.exists()) {
                com.groupdocs.viewer.License license = new com.groupdocs.viewer.License();
                license.setLicense(licensePath);
                System.out.println("GroupDocs.Viewer licensed successfully.");
            } else {
                System.out.println("No license file found. Running in evaluation mode.");
            }
        } catch (Exception e) {
            System.err.println("License setup failed: " + e.getMessage());
        }
    }

    /**
     * Main entry point.
     */
    public static void main(String[] args) {
        setupLicense();
        run();
    }

    /**
     * Demonstrates auto-detecting file format and rendering to HTML.
     * <p>
     * Input:  Any supported document placed in resources/input/
     * Output: HTML files in resources/output/
     */
    public static void run() {
        // -----------------------------------------------------------------
        // Step 1. Define input and output paths.
        // -----------------------------------------------------------------
        String inputPath = "resources/input/document"; // Extension will be auto-detected
        String outputPath = "resources/output";

        // Ensure output directory exists
        try {
            Files.createDirectories(Paths.get(outputPath));
        } catch (IOException e) {
            System.err.println("Failed to create output directory: " + e.getMessage());
            return;
        }

        // -----------------------------------------------------------------
        // Step 2. Load document using Viewer with auto-detection.
        // -----------------------------------------------------------------
        // GroupDocs.Viewer automatically detects file format from the file extension.
        // If extension is missing or ambiguous, use LoadOptions with FileType.
        // Here, we assume standard extensions (e.g., ".docx", ".xlsx", ".pdf").
        try (Viewer viewer = new Viewer(inputPath)) {
            // -----------------------------------------------------------------
            // Step 3. Get file info to confirm detected format (optional but useful for logging).
            // -----------------------------------------------------------------
            FileInfo fileInfo = viewer.getFileInfo();
            System.out.println("Detected file type: " + fileInfo.getFileType());

            // -----------------------------------------------------------------
            // Step 4. Configure HTML rendering options with embedded resources.
            // -----------------------------------------------------------------
            HtmlViewOptions viewOptions = HtmlViewOptions.forEmbeddedResources();

            // -----------------------------------------------------------------
            // Step 5. Render document to HTML.
            // -----------------------------------------------------------------
            viewer.view(viewOptions);

            // -----------------------------------------------------------------
            // Step 6. Confirm output location and completion.
            // -----------------------------------------------------------------
            System.out.println("Rendering completed. Output saved to: " + outputPath);
        } catch (Exception e) {
            System.err.println("Error during rendering: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
