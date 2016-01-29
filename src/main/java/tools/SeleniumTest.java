package tools;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;


import java.util.concurrent.TimeUnit;

/**
 * Created by star on 16-1-28.
 */
public class SeleniumTest {
    {

        System.out.println("初始化");
    }
    public static  void  main(String[] args){
//        System.setProperty("webdriver.firefox.bin", "D:/Program Files/Mozilla Firefox/firefox.exe");
        ProfilesIni pi = new ProfilesIni();
                FirefoxProfile profile = pi.getProfile("default");
        profile.setPreference("browser.download.lastDir","/home/star");
        profile.setPreference("browser.helperApps.neverAsk.saveToDisk","application/octet-stream");
//        profile.setPreference("browser.download.manager.showWhenStarting","application/octet-stream");
        WebDriver webDriver=new FirefoxDriver(profile);
        webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        webDriver.navigate().to("http://www.xixihd.com/?user=login&reurl=%2F");
        webDriver.findElement(By.name("username")).sendKeys("starlifht");
        webDriver.findElement(By.name("password")).sendKeys("787870724");
        webDriver.findElement(By.id("submits")).click();
        webDriver.navigate().to("http://www.xixihd.com/?downloads=content&id=32551");
        webDriver.findElement(By.id("download")).click();
        try {
            TimeUnit.MILLISECONDS.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        webDriver.close();
    }
}

