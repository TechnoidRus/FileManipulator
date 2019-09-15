import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

public class copyFiles {

  public static void main(String[] args) throws Exception {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    for (; ; ) {
      System.out.println("Введите путь к папке которую хотите скопировать");
      Path inputPath = Paths.get(reader.readLine());
      if (!inputPath.toFile().exists()) {
        System.out.println("Файл не существует");
        continue;
      }
      System.out.println("Введите путь куда скопировать");
      Path outputPath = Paths.get(reader.readLine() + File.separator + inputPath.getFileName().toString());
      copyFile(inputPath, outputPath);
    }
  }

  private static void copyFile(Path inputPath, Path outputPath) throws IOException {
    List<Path> sources = Files.walk(inputPath).collect(toList());
    List<Path> destinations = sources.stream()
        .map(inputPath::relativize)
        .map(outputPath::resolve)
        .collect(toList());

    for (int i = 0; i < sources.size(); i++) {
      Files.copy(sources.get(i), destinations.get(i), StandardCopyOption.REPLACE_EXISTING);
    }
    System.out.println("Файлы скопированы!");
  }
}


