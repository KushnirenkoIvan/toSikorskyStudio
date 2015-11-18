package webapp.util;

import org.apache.log4j.Logger;
import webapp.exception.InvalidDataFileException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Represents session to work with file
 *
 * @author ioann
 */
public class FileSession {

    private static FileSession session;
    private static String file = "/home/ivan/IdeaProjects/Kushnirenko_webapp/web/info.txt";
    private static boolean isOpen = false;

    private static Logger logger = Logger.getLogger(FileSession.class);

    private StringBuilder info;

    public StringBuilder getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = new StringBuilder(info);
    }

    public static void main(String[] args) {
        FileSession session = FileSession.buildSession();
        session.openSession();
        System.out.println(session.info.toString());
        session.closeSession();
    }

    private FileSession() throws InvalidDataFileException {
        System.out.println("FileSession created.");
        File fe = new File(file);
        if (!fe.exists() || fe.isDirectory()) {
            throw new InvalidDataFileException();
        }
    }

    public static FileSession buildSession() {
        if (session == null) {
            try {
                session = new FileSession();
            } catch (InvalidDataFileException exp) {
                exp.printStackTrace();
            }
        }
        return session;
    }

    public void openSession() {
        StringBuilder stringbuilder = new StringBuilder();
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(file));
            while (scanner.hasNextLine()) {
                stringbuilder.append(scanner.nextLine()).append("\n");
            }
            info = stringbuilder;
            isOpen = true;
        } catch (FileNotFoundException exp) {
            exp.printStackTrace();
        } finally {
            scanner.close();
        }
    }

    public boolean writeInfoToFile() {
        if (isOpen == false) {
            System.out.println("Session closed.");
            return isOpen;
        }
        FileWriter writer = null;
        try {
            writer = new FileWriter(file, false);
            writer.write(info.toString());
        } catch (IOException exp) {
            exp.printStackTrace();
            return false;
        } finally {
            if (writer != null) {
                try {
                    writer.flush();
                } catch (IOException exp) {
                    exp.printStackTrace();
                    logger.error("There is error flushing file writer object.");
                }
                try {
                    writer.close();
                } catch (IOException exp) {
                    exp.printStackTrace();
                    logger.error("There is error closing file writer object.");
                }
            }
        }
        return true;
    }

    public boolean closeSession() {
        writeInfoToFile();
        session = null;
        file = null;
        info = null;
        isOpen = false;
        System.out.println("Session closed.");
        return true;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void appendInfo(String app) {
        info.append(app).append("\n");
    }

}
