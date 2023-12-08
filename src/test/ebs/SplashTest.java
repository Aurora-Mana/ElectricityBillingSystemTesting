package test.ebs;

import main.ebs.Splash;
import org.junit.jupiter.api.Test;

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
}
