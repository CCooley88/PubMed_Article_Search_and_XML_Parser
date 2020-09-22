package pubmed;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.GZIPInputStream;


// This is used to unzip .gz files takes a source and target document path as arguments
public class DecompressZip {
    public static void decompressGzip(Path source, Path target) throws IOException {    	
        if (Files.notExists(source)) {
	           System.err.printf("The path %s doesn't exist!", source);
	           return;
	       }
	       
        try (GZIPInputStream gis = new GZIPInputStream(
                                      new FileInputStream(source.toFile()));
             FileOutputStream fos = new FileOutputStream(target.toFile())) {
            byte[] buffer = new byte[1024];
            int len;
            while ((len = gis.read(buffer)) > 0) {
                fos.write(buffer, 0, len);
            }
        }
    }
}
