package com.herokuapp.theinternet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PositiveTests {

    @Test
    public void loginTest() {
        System.out.println("Starting loginTest");
        //Create drive
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        //maximize browser window
        driver.manage().window().maximize();
        //open test page
        String url = "http://the-internet.herokuapp.com/login";
        driver.get(url);
        System.out.println("Page is opened.");

        //enter username
        WebElement username = driver.findElement(By.id("username"));
        username.sendKeys("tomsmith");
        //enter password
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("SuperSecretPassword!");
        //click login button

        WebElement loginButton = driver.findElement(By.className("radius"));
        loginButton.click();

        sleep(2000);


        //
        //verification:


        //new url
        String expectedURL = "http://the-internet.herokuapp.com/secure";
        String actualURL = driver.getCurrentUrl();
        Assert.assertEquals(actualURL,expectedURL, "Actual page URL isn't the same as expected");

        //logout is visible
        WebElement logoutButton = driver.findElement(By.xpath("//a[@class='button secondary radius']"));
        Assert.assertTrue(logoutButton.isDisplayed(),"Logout button isn't displayed");

        //successful login message
        WebElement successfulMsg = driver.findElement(By.cssSelector("div#flash"));
        String expectedMsg = "You logged into a secure area!";
        String actualMsg = successfulMsg.getText();
        Assert.assertEquals(actualMsg, expectedMsg, "Actual message isn't the same as expected");

        //Close driver
        driver.quit();
    }

    private void sleep(long m) {
        try {
            Thread.sleep(m);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
