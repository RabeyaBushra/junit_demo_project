
import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;

import static java.lang.Thread.sleep;
public class MYUnit {

        WebDriver driver;
        WebDriverWait wait;
        @Before
        public void setup(){
            System.setProperty("webdriver.gecko.driver", "./src/test/resources/geckodriver.exe");
            FirefoxOptions ops = new FirefoxOptions();
            ops.addArguments("--headed");
            driver=new FirefoxDriver(ops);
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
    @Test
    public void from_fill_up() throws InterruptedException {
        driver.get("https://demoqa.com/text-box");
        WebElement name=driver.findElement(By.cssSelector("#userName"));
        name.sendKeys("bushra");
        sleep(2000);
        WebElement email=driver.findElement(By.id("userEmail"));
        email.sendKeys("bushra@gmail.com");
        sleep(2000);
        WebElement clickBtnElement=driver.findElement(By.id("submit"));
        Actions action=new Actions(driver);
        action.moveToElement(clickBtnElement);
        action.click().build().perform();
        sleep(2000);
        String text1= driver.findElement(By.id("name")).getText();
        String text2= driver.findElement(By.id("email")).getText();
        Assert.assertTrue(text1.contains("Name:bushra"));
       Assert.assertTrue(text2.contains("Email:bushra@gmail.com"));
        sleep(2000);

         
        System.out.println("fill up the from");


    }
    @Test
    public void button()
    {
        driver.get("https://demoqa.com/buttons");
        WebElement doubleClickBtnElement=driver.findElement(By.id("doubleClickBtn"));
        WebElement rightClickBtnElement =driver.findElement(By.id("rightClickBtn"));

        Actions action =new Actions(driver);
        action.doubleClick(doubleClickBtnElement).perform();
        action.contextClick(rightClickBtnElement).perform();
        //WebElement element =driver.findElement(By.cssSelector("#3MQZT"));
        //element.click();

        String msg1=driver.findElement(By.id("doubleClickMessage")).getText();
        String msg2=driver.findElement(By.id("rightClickMessage")).getText();
        //String msg3=driver.findElement(By.id("dynamicClickMessage")).getText();

        Assert.assertTrue(msg1.contains("You have done a double click"));
        Assert.assertTrue(msg2.contains("You have done a right click"));
        //Assert.assertTrue(msg3.contains("You have done a dynamic click"));

    }

    @Test
    public void ClickIfMultipleButton() {
        driver.get("https://demoqa.com/buttons");
        List<WebElement> buttonElement=driver.findElements(By.tagName("button"));
        Actions actions= new Actions(driver);
        actions.doubleClick(buttonElement.get(1)).perform();
        actions.contextClick(buttonElement.get(2)).perform();
        actions.click(buttonElement.get(3)).perform();
    }

    @Test
    public void handleAlert() throws InterruptedException {
        driver.get("https://demoqa.com/alerts");
        driver.findElement(By.id("alertButton")).click();
        sleep(2000);
        driver.switchTo().alert().accept();
        sleep(2000);
        driver.findElement(By.id("confirmButton")).click();
        sleep(2000);
        driver.switchTo().alert().dismiss();
        sleep(2000);
        driver.findElement(By.id("promtButton")).click();
        sleep(2000);
        driver.switchTo().alert().sendKeys("Fahim");
        sleep(2000);
        driver.switchTo().alert().accept();
        sleep(2000);
        String text1 =driver.findElement(By.id("promptResult")).getText();
        Assert.assertTrue(text1.contains("Fahim"));
        sleep(2000);

    }


    @Test
    public void selectDate(){
            driver.get("https://demoqa.com/date-picker");
            driver.findElement(By.id("datePickerMonthYearInput")).clear();
            driver.findElement(By.id("datePickerMonthYearInput")).sendKeys("05/08/1993");
            driver.findElement(By.id("datePickerMonthYearInput")).sendKeys(Keys.ENTER);
        }
 @Test
    public void selectDropdown() throws InterruptedException {
            driver.get("https://demoqa.com/select-menu");
            Select color=new Select(driver.findElement(By.id("oldSelectMenu")));
            color.selectByValue("1");
            sleep(2000);
            Select cars=new Select(driver.findElement(By.cssSelector("#cars")));
            sleep(2000);
            if (cars.isMultiple()) {

                cars.selectByValue("volvo");

                cars.selectByValue("audi");

            }
            sleep(2000);
        }
@Test
 public void HandlesNewTabs() throws InterruptedException {
     driver.get("https://demoqa.com/links");
     driver.findElement(By.id("simpleLink")).click();
     sleep(5000);
     ArrayList<String> w = new ArrayList<String>(driver.getWindowHandles());
     driver.switchTo().window(w.get(1));
     String tittle = driver.getTitle();
     System.out.println("New tab title: " + driver.getTitle());
    wait = new WebDriverWait(driver,Duration.ofSeconds(50));
    WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@src='/images/Toolsqa.jpg']")));
    Boolean status = element.isDisplayed();
    Assert.assertEquals(true,status);
     //Assert.assertTrue(tittle.contains("ToolsQa"));
    driver.close();
     driver.switchTo().window(w.get(0));

 }
 @Test
public void handleChildWindow() throws InterruptedException {

    driver.get("https://demoqa.com/browser-windows");
    WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(40));
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("windowButton")));
    driver.findElement(By.id(("windowButton"))).click();
    String mainWindowHandle = driver.getWindowHandle();
    Set<String> allWindowHandles = driver.getWindowHandles();
    Iterator<String> iterator = allWindowHandles.iterator();
    while (iterator.hasNext()) {
        String ChildWindow = iterator.next();
        if (!mainWindowHandle.equalsIgnoreCase(ChildWindow)) {
            driver.switchTo().window(ChildWindow);
            String text= driver.findElement(By.id("sampleHeading")).getText();
            Assert.assertTrue(text.contains("This is a sample page"));

        }

    }

}

@Test
public void modalDialog() throws InterruptedException {
            driver.get("https://demoqa.com/modal-dialogs");
            wait =new WebDriverWait(driver,Duration.ofSeconds(40));
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.id("showSmallModal")));
            element.click();
            //driver.findElement(By.id("showSmallModal")).click();
            //element.click();
            sleep(2000);
            driver.findElement(By.id("closeSmallModal")).click();
        }
        @Test
    public void webTables() throws InterruptedException {
            driver.get("https://demoqa.com/webtables");
            wait =new WebDriverWait(driver,Duration.ofSeconds(40));
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@id=\"edit-record-1\"]//*[name()=\"svg\"]")));
            element.click();
            //driver.findElement(By.id("//input[@id='firstName']")).clear();
            //sleep(2000);
            driver.findElement(By.id("firstName")).sendKeys("mina");
            sleep(2000);

            driver.findElement(By.id("submit")).click();
        }
@Test
    public void scrapData(){
            driver.get("https://demoqa.com/webtables");
            WebElement table = driver.findElement(By.className("rt-tbody"));
            List<WebElement> allRows = table.findElements(By.className("rt-tr"));
            int i=0;
            for (WebElement row : allRows) {
                List<WebElement> cells = row.findElements(By.className("rt-td"));
                for (WebElement cell : cells) {
                    i++;
                    System.out.println("num["+i+"] "+ cell.getText());
                }
            }
        }

@Test
public void uploadImg()
{
    driver.get("https://demoqa.com/upload-download");
    wait =new WebDriverWait(driver,Duration.ofSeconds(40));
    WebElement imgElement= wait.until(ExpectedConditions.elementToBeClickable(By.id("uploadFile")));

    imgElement.sendKeys("D:\\img.jpg");

}
@Test
    public void handleIframe(){
            driver.get("https://demoqa.com/frames");
            driver.switchTo().frame("frame2");
            String text= driver.findElement(By.id("sampleHeading")).getText();
            Assert.assertTrue(text.contains("This is a sample page"));
            driver.switchTo().defaultContent();
        }
        @Test
    public void mouseHover() throws InterruptedException {
            driver.get("https://green.edu.bd/");
            WebElement mainMenu = driver.findElement(By.xpath("//a[@class='dropdown-toggle'][contains(text(),'About Us')]"));
            Actions actions = new Actions(driver);
            actions.moveToElement(mainMenu).perform();
            Thread.sleep(3000);
            WebElement subMenu = driver.findElement(By.xpath("//li[@id='menu-item-325']//a[contains(text(),'History')]"));
            actions.moveToElement(subMenu);
            actions.click().build().perform();}

@Test
    public void keyboardEvents() throws InterruptedException {
            driver.get("https://www.google.com/");
            WebElement searchElement = driver.findElement(By.name("q"));
            Actions action = new Actions(driver);
            action.moveToElement(searchElement);
            action.keyDown(Keys.SHIFT);
            action.sendKeys("Selenium Webdriver")
                    .keyUp(Keys.SHIFT)
                    .doubleClick()
                    .contextClick()
                    .perform();
            Thread.sleep(5000);
        }




@Test
    public void takeScreenShot() throws IOException {
            driver.get("https://demoqa.com");
            File screenshotFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            String time = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss-aa").format(new Date());
            String fileWithPath = "./src/test/resources/screenshots/" + time + ".png";
            File DestFile = new File(fileWithPath);
            FileUtils.copyFile(screenshotFile, DestFile);
        }


    public static void readFromExcel(String filePath,String fileName,String sheetName) throws IOException {
            File file =    new File(filePath+"\\"+fileName);
            FileInputStream inputStream = new FileInputStream(file);
            Workbook workbook = null;
            String fileExtensionName = fileName.substring(fileName.indexOf("."));
            if(fileExtensionName.equals(".xls")){
                workbook = new HSSFWorkbook(inputStream);
            }
            Sheet sheet = workbook.getSheet(sheetName);
            int rowCount = sheet.getLastRowNum()-sheet.getFirstRowNum();
            for (int i = 0; i < rowCount+1; i++) {
                Row row = sheet.getRow(i);
                for (int j = 0; j < row.getLastCellNum(); j++) {
                    DataFormatter formatter= new DataFormatter();
                    System.out.println(formatter.formatCellValue((row.getCell(j)))+"|| ");

                }
                System.out.println();
            }
        }

    @Test
    public void readExcelFile() throws IOException {
            readFromExcel("D:\\QA\\HW\\Junit\\project\\","book2.xls","Sheet1");
        }






    @After
    public void finishTest(){
            driver.close();
        }

}

