package structures.graph.persistence.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

public class FilePrinter {
  static private PrintStream makeFileStream(String fileName) throws FileNotFoundException {
    File outputFile = new File(fileName);
    return new PrintStream(outputFile);
  }

  static public void printToFile(String result, String fileName) throws FileNotFoundException {
    try (PrintStream outputStream = makeFileStream(fileName)) {
      outputStream.print(result);
    }
  }
}
