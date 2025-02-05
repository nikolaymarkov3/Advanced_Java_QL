package runner;

import io.qameta.allure.Step;
import okhttp3.*;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import utils.Logger;

import java.io.IOException;
import java.net.CookieManager;
import java.net.CookiePolicy;

//import static runner.BaseTest.getWebDriver;
import static runner.BaseTest.driver;
import static utils.PropertyLoader.getPropertyLoader;

public class Okhttp3Helpers {
//    private static WebDriver driver = null;
    public Okhttp3Helpers(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
   

    private static Okhttp3Helpers okhttp3Helpers;

    private static final String CONFIG_FILE_NAME_UI = "test.properties";
    private static final String CONFIG_FILE_NAME_API = "api.properties";
    private static final String BASE_URL = getPropertyLoader().get(CONFIG_FILE_NAME_UI, "baseUrl");
    private static final String LOGIN = getPropertyLoader().get(CONFIG_FILE_NAME_UI, "login");
    private static final String PASSWORD= getPropertyLoader().get(CONFIG_FILE_NAME_UI, "password");
    private static final String PAGE_ENDPOINT = getPropertyLoader().get(CONFIG_FILE_NAME_API, "pageEndpoint");
    private static final String LOGIN_ENDPOINT = getPropertyLoader().get(CONFIG_FILE_NAME_API, "loginEndpoint");
    private static final String LOGIN_PAGE = BASE_URL + PAGE_ENDPOINT;
    private static final String LOGIN_AUTH = BASE_URL + LOGIN_ENDPOINT;

    public static Okhttp3Helpers getInstanceOkhttp() {
        if (okhttp3Helpers == null) {
            okhttp3Helpers = new Okhttp3Helpers(driver);
        }
        return okhttp3Helpers;
    }
    @Step("API авторизация")
    public void apiAuth() throws IOException {
        CookieManager cookieManager = new CookieManager();
        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
        JavaNetCookieJar cookieJar = new JavaNetCookieJar(cookieManager);

        OkHttpClient client = new OkHttpClient.Builder()
                .cookieJar(cookieJar)
                .followRedirects(false)
                .build();

        Request loginPageRequest = new Request.Builder()
                .url(LOGIN_PAGE)
                .build();
        client.newCall(loginPageRequest).execute();

        RequestBody formBody = new FormBody.Builder()
                .add("_csrf_token", "")
                .add("_username", LOGIN)
                .add("_password", PASSWORD)
                .add("_submit", "Войти")
                .build();

        Request request = new Request.Builder()
                .url(LOGIN_AUTH)
                .post(formBody)
                .build();
        client.newCall(request).execute();

        driver.manage().deleteAllCookies();

        cookieManager.getCookieStore().getCookies().forEach(httpCookie -> {
            Cookie cookie = new Cookie(
                    httpCookie.getName(),
                    httpCookie.getValue(),
                    httpCookie.getDomain(),
                    httpCookie.getPath(),
                    null
            );
            driver.manage()
                    .addCookie(cookie);
        });
        driver.navigate().refresh();
        Logger.logInfo("Апи авторизация");
    }
}
