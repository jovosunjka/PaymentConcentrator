package com.rmj.e2eTests;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class PassengerPageTest {
    private WebDriver browser;
    private String baseUrl = "http://localhost:4200";
    private String passengerUsername = "steva";
    private String passengerPassword = "steva";
    private String userAdminUsername = "darko";
    private String userAdminPassword = "darko";

    private LoginPage loginPage;
    private PassengerPage passengerPage;
    private BuyTicketTab buyTicketTab;
    private ShowTicketsTab showTicketsTab;
    private ModalDilaog modalDialog;
    private ChangeAccountTypeTab changeAccountTypeTab;
    private LogoutButton logoutButton;
    private UserAdminPage userAdminPage;
    private AccountRequestsTab accountRequestsTab;
    private ShowScheduleTab showScheduleTab;
    private PositionsOfVehiclesTab positionsOfVehiclesTab;

    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy. HH:mm");

    // selektovanje button-a sa nekim tekstom u sebi
    //String xpathStr = String.format("//button[contains(.,'%s')]", nekiTekst);
    //driver.findElement(By.xpath(xpathStr));

    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/driver/chromedriver.exe");
        browser = new ChromeDriver();

        browser.manage().window().maximize();
        browser.navigate().to(baseUrl + "/login");


    }


    @After
    public void tearDown() throws Exception {
        // Shutdown the browser
        //if(browser != null) browser.quit();
    }
}
