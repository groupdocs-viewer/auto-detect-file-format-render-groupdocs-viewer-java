package com.groupdocs.viewer.examples;

import com.groupdocs.viewer.FileType;
import com.groupdocs.viewer.License;
import com.groupdocs.viewer.Viewer;
import com.groupdocs.viewer.options.HtmlViewOptions;
import com.groupdocs.viewer.options.LoadOptions;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


/**
 * Demonstrates how to detect a document format from a file stream and render it to HTML using GroupDocs Viewer.
 * <p>
 * The example expects a PDF file (<code>sample.pdf</code>) placed in the <code>resources/input/</code>
 * directory (project root). The file is opened as a stream, so the format is taken from the file
 * signature rather than from the path extension. Rendered HTML pages are saved to <code>resources/output/</code>.
 * </p>
 */
public class AutoDetectFileFormatRenderExample {

    private static final String INPUT_FILE = "resources/input/sample.pdf";
    private static final String OUTPUT_DIR = "resources/output/";
    private static final String LICENSE_FILE = "GroupDocs.Viewer.Java.lic";

    /**
     * Loads the GroupDocs Viewer license if the license file exists.
     * <p>
     * To get a temporary license, visit:
     * <a href="https://purchase.groupdocs.com/temporary-license/">https://purchase.groupdocs.com/temporary-license/</a>.
     * Place the downloaded <code>GroupDocs.Viewer.Java.lic</code> file in the project root directory.
     * Without a license the library works in evaluation mode with watermarks and other limitations.
     * </p>
     *
     * @param licensePath path to the license file relative to the project root
     */
    public static void loadLicense(String licensePath) {
        File licenseFile = new File(licensePath);
        if (licenseFile.exists()) {
            try {
                License license = new License();
                license.setLicense(licensePath);
                System.out.println("GroupDocs Viewer license loaded successfully.");
            } catch (Exception e) {
                System.err.println("Failed to load GroupDocs Viewer license: " + e.getMessage());
            }
        } else {
            System.out.println("License file not found at " + licensePath + ". Running in evaluation mode.");
        }
    }

    /**
     * Detects the format of the input document from its stream and renders every page to HTML.
     * <p>
     * The method creates the output directory if it does not exist, reads the file signature with
     * {@link FileType#fromStream(InputStream)}, and passes the detected type to {@link LoadOptions}.
     * HTML pages are saved with the pattern <code>page_{index}.html</code> inside the output directory.
     * </p>
     */
    public static void renderAutoDetectedDocument() {
        Path outDirPath = Paths.get(OUTPUT_DIR);
        try {
            if (!Files.exists(outDirPath)) {
                Files.createDirectories(outDirPath);
                System.out.println("Created output directory: " + outDirPath.toAbsolutePath());
            }
        } catch (Exception e) {
            System.err.println("Failed to create output directory: " + e.getMessage());
            return;
        }

        File inputFile = new File(INPUT_FILE);
        if (!inputFile.exists()) {
            System.err.println("Input file not found: " + INPUT_FILE);
            return;
        }

        Path pageFilePathFormat = outDirPath.resolve("page_{0}.html");

        // fromStream reads the signature and does not rewind the stream, so detection and rendering use separate streams.
        FileType fileType;
        try (InputStream detectionStream = new FileInputStream(inputFile)) {
            fileType = FileType.fromStream(detectionStream);
        } catch (Exception e) {
            System.err.println("Failed to detect file type: " + e.getMessage());
            e.printStackTrace();
            return;
        }

        if (fileType == null || FileType.UNKNOWN.equals(fileType)) {
            System.err.println("Could not detect a supported file type for: " + INPUT_FILE);
            return;
        }

        System.out.println("Detected file type: " + fileType);

        LoadOptions loadOptions = new LoadOptions(fileType);
        try (InputStream inputStream = new FileInputStream(inputFile);
             Viewer viewer = new Viewer(inputStream, loadOptions)) {
            HtmlViewOptions options = HtmlViewOptions.forEmbeddedResources(pageFilePathFormat);
            viewer.view(options);
            System.out.println("Document rendered successfully. Output saved to: " + outDirPath.toAbsolutePath());
        } catch (Exception e) {
            System.err.println("Error during rendering: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Load license if available
        loadLicense(LICENSE_FILE);
        // Perform the rendering demonstration
        renderAutoDetectedDocument();
    }
}
