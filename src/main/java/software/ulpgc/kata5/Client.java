package software.ulpgc.kata5;

import javax.imageio.IIOException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class Client {
    private static final String url = "http://localhost:8080/hello";

    public static void main(String[] args) throws IOException {
        try(InputStream is = new URL(url).openStream()){
            System.out.println(new String(is.readAllBytes()));
        }
    }
}
