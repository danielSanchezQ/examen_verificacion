package app;

import app.MainApp;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.junit.*;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.util.Date;
import java.time.format.DateTimeFormatter;

/**
 * Created by netwave on 23/05/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MainApp.class)
@WebIntegrationTest(value = "server.port=9090")
public class SeleniumTest {
    protected WebDriver driver;

    @Before
    public void setup() {
        ChromeDriverManager.setup();
        driver = new ChromeDriver();
    }


    @Test
    public void TestStore()
    {
        Date d = new Date();
        driver.get("localhost:9090");
        driver.findElement(By.id("TagName_s")).sendKeys("SeleniumTest"+d.toString());
        driver.findElement(By.id("Data")).sendKeys("SeleniumTest data text"+d.toString());
        driver.findElement(By.id("Pwd_s")).sendKeys("SeleniumTestPwd"+d.toString());
        driver.findElement(By.id("send_b")).click();
        String ret_msg = driver.findElement(By.id("ret_msg")).getText();
        assert ret_msg.equals("Succesfully Stored");
    }


    @After
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
