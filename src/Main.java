import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {

        File cryptoFile = new File("src/res/crypto.txt");
        List<String> crypto = new ArrayList<>();
        crypto.add("hello");
        crypto.add("Hello world");
        crypto.add("!1");
        makeCrypto(crypto);

        //addInfoToFile(cryptoFile);
    }

    private static void makeCrypto(List<String> list) {
        char tempChar = 0;
        char newChar = 0;
        String tempString = "";
        int code;
        int j = 0;

        for (String items : list) {

            for (int i = 0; i < items.length(); i++) {
                tempChar = items.charAt(i);
                code = (int) tempChar;
                code++;
                newChar = (char) code;
                tempString += newChar;
            }
            list.set(j, tempString);
            j++;


        }


    }


    private static void addInfoToFile(File cryptoFile) throws IOException {
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


    public void parseTaskFromFile(File tasksFile) throws IOException {
        BufferedReader inputFileReader = new BufferedReader(new FileReader(tasksFile));
        for (String row = inputFileReader.readLine(); row != null; row = inputFileReader.readLine()) {
            //  String[] temp = row.split(SEP);
//            Task task = new Task();
//            task.setID(Integer.parseInt(temp[0]));
//            task.setAuthor(temp[1]);
//            task.setExecutor(temp[2]);
//            task.setTitle(temp[3]);
//            task.setStartTime(temp[4]);
//            task.setFinishTime(temp[5]);
//            task.setPriority(Boolean.parseBoolean(temp[6]));
//            task.setDifficult(Boolean.parseBoolean(temp[7]));
//            task.setStatus(Boolean.parseBoolean(temp[8]));
//            task.setDeleted(Boolean.parseBoolean(temp[9]));
//            tasks.add(task);
        }
        inputFileReader.close();
    }

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