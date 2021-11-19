package nio;

//import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

	public static void main(String[] args) {
        Path p = Paths.get(".");
        //File f = p.toFile();
        try {
            DirMonitor dm = new DirMonitor(p);
            dm.showAll();
            System.out.println(dm.sizeOfFiles());
            System.out.println(dm.mostRecent());
            //dm.mostRecent(f);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
	
	
}
