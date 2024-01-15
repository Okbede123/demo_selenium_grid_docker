import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.HasDownloads;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.List;

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

    @Test
    public void TC_01_Download(){
        WebDriver driver;
            // de chay duoc testcase nay thi phai bat cat docker-compose.yml o folder compose len vi no co them cai config SE_OPTS=--enable-managed-downloads true
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setPlatform(Platform.ANY);
        desiredCapabilities.setBrowserName("chrome");
        ChromeOptions cOptions = new ChromeOptions();
//        cOptions.addArguments("--start-maximized");
        cOptions.setEnableDownloads(true);
        cOptions.merge(desiredCapabilities);
        try {
            driver = new RemoteWebDriver(new URL("http://192.168.1.4:4444/wd/hub"), cOptions);
            driver.get("https://file-examples.com/wp-content/storage/2017/02/file-sample_100kB.doc");
            System.out.println(driver.getTitle());
            //cho` cho file duoc download
            new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(d -> ((HasDownloads) d).getDownloadableFiles().contains("file-sample_100kB.doc"));
            //list all file da download
            List<String> files = ((HasDownloads) driver).getDownloadableFiles();
            ;
            //save file o may node sang may hub
            ((HasDownloads) driver).downloadFile("file-sample_100kB.doc",Path.of("C:\\Users\\Admin\\IdeaProjects\\testgithubcommandline\\savefile"));
            System.out.println(files);
            driver.quit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void TC_01_DownloadInput(){
        WebDriver driver;

        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setPlatform(Platform.ANY);
        desiredCapabilities.setBrowserName("chrome");
        ChromeOptions cOptions = new ChromeOptions();
        cOptions.addArguments("--start-maximized");
        cOptions.merge(desiredCapabilities);

        try {
            driver = new RemoteWebDriver(new URL("http://192.168.1.4:4444/wd/hub"), cOptions);
        //dung ham nay de handle lay file tu may hub
            ((RemoteWebDriver) driver).setFileDetector(new LocalFileDetector());
            driver.get("https://tus.io/demo");
            System.out.println(driver.getTitle());
            Thread.sleep(4000);
            driver.findElement(By.xpath("//input")).sendKeys("C:\\Users\\Admin\\IdeaProjects\\testgithubcommandline\\fileanh\\chu_ky.png");

            Thread.sleep(10000);
            driver.quit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void TC_Download() throws InterruptedException {
        WebDriver driver;
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://file-examples.com/wp-content/storage/2017/02/file-sample_100kB.doc");
        Thread.sleep(10000);

    }
}
