package tests;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.Assert;
import org.testng.annotations.*;



import java.util.Random;

public class RegisterTest {

    WebDriver driver;
    By buttonSignIn = By.xpath("//a[contains(@href ,'login')]");
    By buttonRegisterAccount = By.cssSelector("a[data-test='register-link']");
    By firstName = By.id("first_name");
    By lastName = By.id("last_name");
    By dateOfBirth = By.id("dob");
    By address = By.id("address");
    By postCode = By.id("postcode");
    By city = By.id("city");
    By state = By.id("state");
    By country = By.id("country");
    By phone = By.id("phone");
    By email = By.id("email");
    By password = By.id("password");
    By buttonRegister = By.className("btnSubmit");
    By actualText = By.xpath("//div[@class='col-lg-6 auth-form']/h3");

    @BeforeMethod
    public void proba() throws InterruptedException {
        driver = new ChromeDriver();
        Thread.sleep(2000);
        driver.get("https://practicesoftwaretesting.com/#/");
        driver.manage().window().maximize();;
    }

    @Test
    public void registerUser() throws InterruptedException {
        hoverAndClick(buttonSignIn);
        Thread.sleep(2000);
        hoverAndClick(buttonRegisterAccount);
        Thread.sleep(2000);
        typeIn(firstName, "Jovana");
        typeIn(lastName, "Nedic");
        hoverAndClick(dateOfBirth);
        typeIn(dateOfBirth, "09/03/1984");
        typeIn(address, "kragujevac kragujevac");
        typeIn(postCode, "34000");
        typeIn(city, "Kragujevac");
        typeIn(state, "Serbia");
        typeIn(country, "Serbia");
        typeIn(phone, "0123456789");
        typeIn(email, randomize(8) + "@test.com");
        typeIn(password, randomize(10));
        Thread.sleep(2000);
        hoverAndClick(buttonRegister);
        Thread.sleep(2000);


        String actualResult = getTextFromElement(actualText);
        String expectedResult = "Login";
        driver.navigate().refresh();
        Assert.assertEquals(actualResult,expectedResult);
    }


    WebElement getElement (By locator) {

        return driver.findElement(locator);
    }
    void typeIn (By locator, String input) {

        getElement(locator).sendKeys(input);
    }

    String getTextFromElement(By locator){

        return getElement(locator).getText();
    }

    public String randomize ( int length){
        String[] chars = {"a", "m", "n", "d", "s", "z"};
        String result = "";
        for (int i = 0; i < length; i++) {
            Random random = new Random();
            int index = random.nextInt(chars.length);
            result = result + chars[index];
        }
        return result;
    }

    public void hover(By locator) {
        Actions actions = new Actions(driver);
        actions.moveToElement(getElement(locator))
                .perform();
    }

    public void hoverAndClick(By locator) {
        Actions actions = new Actions(driver);
        actions.moveToElement(getElement(locator))
                .click()
                .perform();

    }


//    @AfterMethod
//    public void afterMethodMethod(){
//        driver.quit();
//    }




}
