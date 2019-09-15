import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Objects;

public class FileSizeCounter {

  private static long totalSize;
  private static final long GIGABYTE = 1073741824;
  private static final long MEGABYTE = 1048576;
  private static final long KILOBYTE = 1024;

  public static void main(String[] args) {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    for (; ; ) {
      try {
        System.out.println("Введите путь к папке:");
        File file = new File(reader.readLine());
        if (file.exists()) {
          calculateFileSize(file);
        } else {
          System.out.println("Файл не существует");
          continue;
        }
        printSize();
        System.out.println();
      } catch (Exception e) {
        System.out.println("Чтото пошло не так, попробуйте другую папку");
      }
    }
  }

  private static void calculateFileSize(File file) {

    if (file.isDirectory()) {
      Arrays.stream(Objects.requireNonNull(file.listFiles())).forEach(FileSizeCounter::calculateFileSize);
    } else {
      totalSize += file.length();
    }
  }

  private static void printSize() {

    if (totalSize > GIGABYTE) {
      System.out.printf("Общий размер файлов %.1f Гб", (double) totalSize / GIGABYTE);
    } else if (totalSize > MEGABYTE) {
      System.out.printf("Общий размер файлов %.1f Мб", (double) totalSize / MEGABYTE);
    } else if (totalSize > KILOBYTE) {
      System.out.printf("Общий размер файлов %.1f Кб", (double) totalSize / KILOBYTE);
    } else {
      System.out.printf("Общий размер файлов %d б", totalSize);
    }

  }
}
