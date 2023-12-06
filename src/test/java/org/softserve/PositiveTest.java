package org.softserve;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.FindBy;

import java.time.Duration;

public class PositiveTest{
    private static final String BASE_URL = "https://www.greencity.social/#/ubs";
    private static final Long IMPLICITLY_WAIT_SECONDS = 10L;
    private static final Long ONE_SECOND_DELAY = 1000L;
    private static WebDriver driver;
    @FindBy(id = "email")
    private  WebElement emailInput;
    @FindBy(id = "password")
    private WebElement passwordInput;
    protected void presentationSleep() {
        presentationSleep(1);
    }
    protected void presentationSleep(int seconds) {
        try {
            Thread.sleep(seconds * ONE_SECOND_DELAY); // For Presentation ONLY
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    @BeforeAll
    public static void setup() {
        WebDriverManager.chromedriver().setup();
        //
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICITLY_WAIT_SECONDS)); // 0 by default
        driver.manage().window().maximize();
        System.out.println("@BeforeAll executed");
    }

    @AfterAll
    public static void tear() {
        if (driver != null) {
            driver.quit(); // close()
        }
        System.out.println("@AfterAll executed");
    }

    @BeforeEach
    public void setupThis() {
        driver.get(BASE_URL);
        System.out.println("\t@BeforeEach executed");
    }

    @AfterEach
    public void tearThis() throws InterruptedException {
        Thread.sleep(8000); // For Presentation
        System.out.println("\t@AfterEach executed");
    }

    @Test
    public void validLogin() throws InterruptedException {
        //
        // Steps TC
        driver.findElement(By.xpath("/html/body/app-root/app-main/div/div[2]/app-ubs/app-header/header/div[2]/div/div/div/ul/a")).click();//Used xpath in bad way, but I could not find better solution
        driver.findElement(By.id("email")).click();
        driver.findElement(By.id("email")).clear();
        driver.findElement(By.id("email")).sendKeys("tyv09754@zslsz.com");
        //
        driver.findElement(By.id("password")).click();
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys("Qwerty_1");
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        presentationSleep(); // For Presentation ONLY
        //
        // get Username
        String actualUserName = driver.findElement(By.cssSelector("li.ubs-user-name")).getText();
        String expectedUserName = "QwertyY";
        presentationSleep(); // For Presentation ONLY
        //
        // Check
        Assertions.assertEquals(expectedUserName, actualUserName);
        presentationSleep(); // For Presentation ONLY

        System.out.println("\t\tTest testUi() executed");
    }


}