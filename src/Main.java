import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    final static int CRYPTO = 1;

    public static void main(String[] args) throws IOException {

        File cryptoFile = new File("src/res/crypto.txt");
//        List<String> crypto = new ArrayList<>();
//        crypto.add("hello");
//        crypto.add("Hello world");
//        crypto.add("!1");
        //makeCrypto(crypto);

      //addInfoToFile(cryptoFile);
        makeUnCrypt(cryptoFile);
    }

    /**
     * Перезаписывает лист по-символьно сдвигая ASCII код на количество CRYPTO вправо
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
     * Добавляет записи с экрана в файл. Надо разбить на 2 метода.
     * Вызывает метод makeCrypto для шифрования.
     * Вызывает метод makeOutputFile для записи в файл.
     * @throws IOException может выбрасывать.
     */
    private static void addInfoToFile() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<String> list = new ArrayList<>();
        int counter = 1;
        System.out.println("Нажмите '1' для выхода");
        while (true) {
            System.out.print("Введите запись №" + counter + ": ");
            String line = br.readLine();
            if (!line.equals("1")) {
                list.add(line);
                counter++;
            } else
                break;

        }
        makeCrypto(list);
        makeOutputFile(list);
    }

    /**Метод по расшифровке файл и выводит значение на экран
     *
     * @param tasksFile - Файл для расшифровки
     * @throws IOException выбрасывает
     */
    public static void makeUnCrypt(File tasksFile) throws IOException {
        BufferedReader inputFileReader = new BufferedReader(new FileReader(tasksFile));
        for (String row = inputFileReader.readLine(); row != null; row = inputFileReader.readLine()) {
            //System.out.println(row);
            StringBuilder tempString = new StringBuilder();
            for (int i = 0; i < row.length(); i++) {
                int code = (int) row.charAt(i) - CRYPTO;
               tempString.append((char) code);
            }
            System.out.println(tempString);
        }
        inputFileReader.close();
    }


    /**
     * Создает выходной файл, создает его заново
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