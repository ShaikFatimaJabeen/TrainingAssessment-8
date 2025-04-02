import java.util.ArrayList;
import java.util.List;

public class MemoryLeak {
    private static final List<byte[]> memoryLeakList = new ArrayList<>();

    public static void main(String[] args) {
        try {
            while (true) {
                byte[] data = new byte[1024 * 1024]; // Allocate 1MB per loop
                memoryLeakList.add(data);
                Thread.sleep(100); // Slow down allocation
            }
        } catch (OutOfMemoryError e) {
            System.err.println("OutOfMemoryError detected!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

