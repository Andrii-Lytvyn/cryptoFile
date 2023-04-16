import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
  final static int CRYPTO = 213;
  final static List<String> list = new ArrayList<>();

  public static void main(String[] args) throws IOException {
    File cryptoFile = new File("src/res/crypto.txt");
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    while (true) {
      System.out.println("1: Прочитать файл");
      System.out.println("2: Расшифровка файла");
      System.out.println("3: Добавление информации");
      System.out.println("0: Выход");
      System.out.print("Введите действие: ");
      int choose = Integer.parseInt(br.readLine());
      switch (choose) {
        case 1 -> readCrypted(cryptoFile);
        case 2 -> makeUnCrypt(cryptoFile);
        case 3 -> addInfoToFile(cryptoFile);
        case 0 -> System.exit(0);
      }
    }
  }

  /**
   * Перезаписывает лист по-символьно сдвигая ASCII код на количество при CRYPTO > 0 вправо
   * При CRYPTO < 0 влево
   *
   * @param list передаем лист для шифрования
   */
  private static void makeCrypto(List<String> list) {
    StringBuilder tempString = new StringBuilder();
    int j = 0;
    for (String items : list) {
      for (int i = 0; i < items.length(); i++) {
        int code = (int) items.charAt(i) + CRYPTO;
        tempString.append((char) code);
      }
      list.set(j, tempString.toString());
      tempString = new StringBuilder();
      j++;
    }
  }

  /**
   * Добавляет записи с ввода в файл. Предварительно очищает лист.
   * Вызывает метод makeUnCrypt - для расшифрованного чтения информации из файла.
   * Вызывает метод makeCrypto для шифрования.
   * Вызывает метод makeOutputFile для записи в файл.
   *
   * @throws IOException может выбрасывать.
   */
  private static void addInfoToFile(File cryptoFile) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    list.clear();
    System.out.println("Сейчас в файле: ");
    makeUnCrypt(cryptoFile);
    addInfoFromFileToList(cryptoFile);

    int counter = 1;
    System.out.println("Нажмите 'q' для выхода");
    while (true) {
      System.out.print("Введите запись №" + counter + ": ");
      String line = br.readLine();
      if (!line.equalsIgnoreCase("q")) {
        list.add(line);
        counter++;
      } else
        break;
    }
    makeCrypto(list);
    makeOutputFile(list);
  }

  /**
   * Метод записывает всю информацию из файла в лист. Лист изначально очищается.
   *
   * @param cryptoFile - файл с шифрованными данными
   * @throws IOException выбрасывает
   */
  private static void addInfoFromFileToList(File cryptoFile) throws IOException {
    list.clear();
    BufferedReader inputFileReader = new BufferedReader(new FileReader(cryptoFile));
    for (String row = inputFileReader.readLine(); row != null; row = inputFileReader.readLine()) {
      StringBuilder tempString = new StringBuilder();
      for (int i = 0; i < row.length(); i++) {
        int code = (int) row.charAt(i) - CRYPTO;
        tempString.append((char) code);
      }
      list.add(tempString.toString());
    }
  }

  /**
   * Метод по расшифровке файла. Проверяет не пустой ли файл. Если нет расшифровывает и отображает записи.
   *
   * @param cryptoFile - Файл для расшифровки
   * @throws IOException выбрасывает
   */
  public static void makeUnCrypt(File cryptoFile) throws IOException {
    BufferedReader inputFileReader = new BufferedReader(new FileReader(cryptoFile));
    if (cryptoFile.length() == 0) {
      System.out.println("Файл пуст");
    } else {
      System.out.println("Расшифрованные записи: \n");
      for (String row = inputFileReader.readLine(); row != null; row = inputFileReader.readLine()) {
        StringBuilder tempString = new StringBuilder();
        for (int i = 0; i < row.length(); i++) {
          int code = (int) row.charAt(i) - CRYPTO;
          tempString.append((char) code);
        }
        System.out.println(tempString);
      }
      //   list.forEach(System.out::println); - это просто крутая запис.
      System.out.println();
      inputFileReader.close();
    }
  }

  /**
   * Метод читает зашифрованные записи из файла
   *
   * @param cryptoFile
   * @throws IOException
   */
  public static void readCrypted(File cryptoFile) throws IOException {

    if (cryptoFile.length() == 0) {
      System.out.println("Файл пуст");
    } else {
      System.out.println("Зашифрованные записи: \n");
      BufferedReader inputFileReader = new BufferedReader(new FileReader(cryptoFile));
      for (String row = inputFileReader.readLine(); row != null; row = inputFileReader.readLine()) {
        System.out.println(row);
      }
      System.out.println();
      inputFileReader.close();
    }
  }

  /**
   * Создает выходной файл
   *
   * @param list передаем лист
   * @throws IOException выбрасывает
   */
  public static void makeOutputFile(List<String> list) throws IOException {
    try {
      FileWriter cryptoFile = new FileWriter("src/res/crypto.txt");
      for (String items : list) {
        cryptoFile.write(items + "\n");
      }
      cryptoFile.close();
    } catch (FileNotFoundException e) {
      FileWriter cryptoFile = new FileWriter("src/res/crypto.txt");
    } catch (IOException e) {
      System.err.println("Input/output exception: " + e.getMessage());
    }
  }
}