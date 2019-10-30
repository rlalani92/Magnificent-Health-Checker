import service.Monitor;

public class MagnificentApplication {
    public static void main(String[] args) throws InterruptedException {
        new Monitor().checkHealth();
    }
}
