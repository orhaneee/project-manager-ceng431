package DataAccessLayer;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * This class is responsible for writing to and reading from files.
 * It returns and receives String after operations and
 * checks for the last saved file
 *
 */
public class FileManipulator {

    /**
     * Writing directly to a file with current date.
     * @param content JSON string passed from Project Manager.
     */
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

    /**
     * Gets current date as string and besides as formatted.
     * @return current date
     */
    private String getDateAsString() {
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    }

    /**
     * Gets last written file and checks dates.
     * @return last file name
     */
    private String getLastFile() {
        File folder = new File("./");
        File[] listOfFiles = folder.listFiles();
        String lastFile = "";

        if (listOfFiles != null) {
            for (File file : listOfFiles) {
                if (!file.isDirectory()) {
                    String fileName = file.getName();
                    int i = fileName.lastIndexOf('.');
                    if (i > 0 && fileName.substring(i+1).equals("txt")) {
                        if (file.getName().compareTo(lastFile) > 0) {
                            lastFile = file.getName();
                        }
                    }
                }
            }
        }
        return lastFile.length() > 0 ? lastFile : null;
    }

    /**
     * Reads the content of the given file.
     * @return contents of the JSON as string
     * @throws FileNotFoundException
     */
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
