package test.ebs.unit;

import main.ebs.Login;
import main.ebs.Splash;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;

import static org.junit.jupiter.api.Assertions.*;

public class SplashTest {

    @Test
    void testSplashMain() {
        Splash.main(new String[]{});
    }

    @Test
    void testFframe() throws InterruptedException {

        Splash.fframe frame = new Splash.fframe();

        assertNotNull(frame);

        Thread.sleep(7200);

        assertFalse(frame.isVisible());

    }

    @Test
    void testLoginOpens() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        // Start the frame in a separate thread
        new Thread(() -> {
            Splash.fframe frame = new Splash.fframe();
            // Count down the latch when the run method completes
            latch.countDown();
        }).start();

        // Wait until the latch is counted down or timeout occurs
        latch.await();

        // Sleep for additional time if necessary
        Thread.sleep(3000);

        Login login = Splash.getLogin();

        assertNotNull(login);
        assertTrue(login.isVisible());
        login.dispose();
    }
}
