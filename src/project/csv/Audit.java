package project.csv;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class Audit {
    private static Audit instance = null;

    private Audit(){
    }

    public static Audit getInstance() {
        if (instance == null) {
            synchronized (Audit.class) {
                if (instance == null) {
                    instance = new Audit();
                }
            }
        }
        return instance;
    }

    public void WriteToAudit(String text) {
        try {
            FileWriter file = new FileWriter("audit.csv", true);
            Date data = new Date();
            file.write(text + " , " + data + "\n");
            file.flush();
            file.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}