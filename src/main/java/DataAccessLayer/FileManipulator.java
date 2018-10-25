package DataAccessLayer;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class FileManipulator {

    public void writeToFile(String content) {
        OutputStream os = null;
        try {
            os = new FileOutputStream(new File("Project-"
                    + getDateAsString() + ".txt"));
            os.write(content.getBytes(), 0, content.length());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                assert os != null;
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String getDateAsString() {
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        return dateFormat.format(date);
    }

    private String getLastFile() {
        File folder = new File("./");
        File[] listOfFiles = folder.listFiles();

        if (listOfFiles == null) {
            return null;
        }
        else {
            String lastFile = "";
            for (File file : listOfFiles) {
                if (file.getName().compareTo(lastFile) > 0) {
                    lastFile = file.getName();
                }
            }
            return lastFile;
        }
    }

    public String readLastFile() throws FileNotFoundException {
        if (getLastFile() != null) {
            Scanner in = new Scanner(new FileReader(getLastFile()));
            StringBuilder sb = new StringBuilder();
            while (in.hasNext()) {
                sb.append(in.next());
            }
            in.close();
            return sb.toString();
        }
        else {
            return null;
        }
    }

}
