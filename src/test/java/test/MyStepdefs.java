package test;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class MyStepdefs {

    public WebDriver chromeDriver;

    @Step
    public void открытьБраузер() {
        System.setProperty("webdriver.chrome.driver", "C:\\testing\\chromedriver.exe");
        chromeDriver = new ChromeDriver();
        chromeDriver.manage().window().maximize();
        chromeDriver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
    }

    @Step
    public void закрытьБраузер() {
        chromeDriver.quit();
    }

    @Given("перейти на сайт Яндекса '(.*)'")
    public void перейтиНаСайтЯндекса (String site) {
        открытьБраузер();
        chromeDriver.get(site);
    }

    @Then("найти в Яндексе слово '(.*)'")
    public void найтиВЯндексеСлово (String word) {

        WebElement searchField = chromeDriver.findElement(By.xpath("//*[@id=\'text\']"));
        WebElement searchButton = chromeDriver.findElement(By.xpath("//button[@type='submit']"));

        searchField.click();
        searchField.sendKeys(word);
        searchButton.click();
    }

    @Then("в выдаче Яндекса есть ссылка '(.*)'")
    public void вВыдачеЯндексаЕстьСсылка (String siteToFind) {

        List<WebElement> SearchResultsSites = chromeDriver.findElements(By.xpath("//a[contains(@class,'link link_theme_outer')]//b"));

        boolean hasWikiLink = false;
        for (WebElement webElement : SearchResultsSites) {
            if (webElement.getText().equals(siteToFind)) {
                hasWikiLink = true;
            }
        }
        Assert.assertTrue(hasWikiLink);
    }

    @Then("закончить проверку поисковой выдачи Яндекса")
    public void закончитьПроверкуПоисковойВыдачиЯндекса() {
        закрытьБраузер();
    }

    @Given("перейти на сайт Google '(.*)'")
    public void перейтиНаСайтGoogle (String site) {
        открытьБраузер();
        chromeDriver.get(site);
    }

    @Then("найти в Google слово '(.*)'")
    public void найтиВGoogleСлово (String word) {

        WebElement searchField = chromeDriver.findElement(By.xpath("//input[@class=\'gLFyf gsfi\']"));

        searchField.click();
        searchField.sendKeys(word);
        searchField.sendKeys(Keys.ENTER);
    }

    @Then("в выдаче Google есть ссылка на '(.*)'")
    public void вВыдачеGoogleЕстьСсылкаНа (String siteToFind) {

        List<WebElement> SearchResultOpen = chromeDriver.findElements(By.xpath("//div[@class=\"TbwUpd NJjxre\"]//cite"));

        Boolean hasOpenLink = false;
        for (WebElement webElement : SearchResultOpen) {
            if (webElement.getText().equals(siteToFind)) {
                hasOpenLink = true;
            }
        }
        Assert.assertTrue(hasOpenLink);
    }

    @Then("закончить проверку поисковой выдачи Google")
    public void закончитьПроверкуПоисковойВыдачиGoogle() {
        закрытьБраузер();
    }
}