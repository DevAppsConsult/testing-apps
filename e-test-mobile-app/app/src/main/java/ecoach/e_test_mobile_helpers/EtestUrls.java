package ecoach.e_test_mobile_helpers;

/**
 * Created by banktech on 12/1/2014.
 */
public class EtestUrls {
    public final static String DEV_URL = "http://184.172.4.83";
    public final static String STAGING_URL = "https://api.ecoachsolutions.com/main.php?";
    public final static String PROD_URL = "https://api.ecoachsolutions.com/main.php?";
    public final static String GoToSiteUrl = "https://www.ecoachsolutions.com";
    private static String baseurl = getBaseUrl();
    public final static String authUrl = baseurl + "/authenticate";
    // public final static String BookDownloadUrl = baseurl + "/DownloadBook";
    // public final static String GetBooksUrl = baseurl + "/GetMyBooksDistinct";
    public final static String PingUrl = baseurl + "/ping";

    private static String getBaseUrl() {
        if (MasterSettings.IsProductionMode()) {
            return PROD_URL;
        } else if (MasterSettings.IsStagingMode()) {
            return STAGING_URL;
        } else {
            //default to dev
            return DEV_URL;
        }
    }
}
