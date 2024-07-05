import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class registerNewUser {
    WebDriver driver;

    @BeforeClass
    public void setupBrowser() {
        driver = new ChromeDriver();
        driver.get("http://shop.pragmatic.bg");
        driver.manage().window().maximize();
    }

    @Test
    public void testRegisterNewUser() {
        WebElement myAccountLink = driver.findElement(By.xpath("//span[text()='My Account']"));
        myAccountLink.click();

        WebElement registerLink = driver.findElement(By.linkText("Register"));
        registerLink.click();

        WebElement firstName = driver.findElement(By.id("input-firstname"));
        firstName.sendKeys("Pavel");

        WebElement lastName = driver.findElement(By.id("input-lastname"));
        lastName.sendKeys("Danailov");

        String prefix = RandomStringUtils.randomAlphanumeric(7);
        String sufix = RandomStringUtils.randomAlphabetic(5);
        String emailAddress = prefix + "@" + sufix + ".com";
        WebElement email = driver.findElement(By.id("input-email"));
        email.sendKeys(emailAddress);

        WebElement telephone = driver.findElement(By.id("input-telephone"));
        telephone.sendKeys("0786543334");

        WebElement password = driver.findElement(By.id("input-password"));
        password.sendKeys("parolata123");

        WebElement confirmPassword = driver.findElement(By.id("input-confirm"));
        confirmPassword.sendKeys("parolata123");

        WebElement privacyPolicyCheckbox = driver.findElement(By.name("agree"));
        privacyPolicyCheckbox.click();

        WebElement continueButton = driver.findElement(By.xpath("//input[@value='Continue']"));
        continueButton.click();

        WebElement successMessage = driver.findElement(By.cssSelector("#content h1"));
        Assert.assertEquals(successMessage.getText(), "Your Account Has Been Created!", "Registration failed!");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
