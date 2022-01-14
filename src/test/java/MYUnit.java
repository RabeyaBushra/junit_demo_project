import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;

public class MYUnit {

        WebDriver driver;
        @Before
        public void setup(){
            System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver.exe");
            ChromeOptions ops = new ChromeOptions();
            ops.addArguments("--headed");
            driver=new ChromeDriver(ops);
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        }
        @Test
        public void getTitle(){
            driver.get("https://demoqa.com");
            String title=driver.getTitle();
            System.out.println(title);
            Assert.assertTrue(title.contains("ToolsQA"));
        }


    @Test
    public void img_check()
    {
        driver.get("https://demoqa.com");
       WebElement img = driver.findElement(By.className("banner-image"));
       boolean status = img.isDisplayed();
        System.out.println("image is display");
        Assert.assertTrue(status);
    }



    @After
    public void finishTest(){
            driver.close();
        }

}

