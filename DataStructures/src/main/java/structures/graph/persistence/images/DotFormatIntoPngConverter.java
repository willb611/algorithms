package structures.graph.persistence.images;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class DotFormatIntoPngConverter {
  private static final String BASE_COMMAND = "dot -Tpng";

  public static void createPngForFileWithPrefix(String prefix) throws Exception {
    Runtime runtime = Runtime.getRuntime();
    String command = buildCommand(prefix);
    System.out.println("[createPngForFileWithPrefix] Running from directory: ");
//    exec(runtime, "cd");
    System.out.println("[createPngForFileWithPrefix] Using command: " + command);
    exec(runtime, command);
  }

  private static void exec(Runtime runtime, String command) throws InterruptedException, IOException, TimeoutException {
    Process pr = runtime.exec("cmd " + command);
    pr.waitFor(5, TimeUnit.SECONDS);
    if (pr.isAlive()) {
      pr.destroy();
      throw new TimeoutException("Took too long running command; " + command);
    } else {
      System.out.println("Command executed successfully: " + command);
    }
  }

  private static String buildCommand(String prefix) {
    StringBuilder command = new StringBuilder();
    command.append(BASE_COMMAND)
        .append(" ")
        .append(getInputDotFileName(prefix))
        .append(" ")
        .append("-o ")
        .append(getOutputFileName(prefix));
    return command.toString();
  }

  private static String getInputDotFileName(String prefix) {
    return prefix + ".dot";
  }

  private static String getOutputFileName(String prefix) {
    return prefix + ".png";
  }
}
