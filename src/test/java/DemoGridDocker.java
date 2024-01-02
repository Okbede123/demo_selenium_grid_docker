import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class DemoGridDocker {



    @Test
    public void TC_01(){
        WebDriver driver;

        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setPlatform(Platform.ANY);
        desiredCapabilities.setBrowserName("chrome");
        ChromeOptions cOptions = new ChromeOptions();
        cOptions.addArguments("--start-maximized");
        cOptions.merge(desiredCapabilities);
        try {
            driver = new RemoteWebDriver(new URL("http://192.168.1.4:4445/wd/hub"), cOptions);
            driver.get("https://www.quangcaogiatot.com");
            System.out.println(driver.getTitle());
            Thread.sleep(10000);
            driver.quit();
        } catch (MalformedURLException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
