package test.ebs.unit;

import main.ebs.Splash;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
