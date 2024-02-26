/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lavenstore.users;

/**
 *
 * @author Pham Hieu
 */
public class Constants {
    public static String GOOGLE_CLIENT_ID = "115927365135-90o9anknfgf5hur4crt016fe8cuift5s.apps.googleusercontent.com";

    public static String GOOGLE_CLIENT_SECRET = "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";

    public static String GOOGLE_REDIRECT_URI = "http://localhost:8080/thelavenstore/LoginGoogleController";

    public static String GOOGLE_LINK_GET_TOKEN = "https://accounts.google.com/o/oauth2/token";

    public static String GOOGLE_LINK_GET_USER_INFO = "https://www.googleapis.com/oauth2/v1/userinfo?access_token=";

    public static String GOOGLE_GRANT_TYPE = "authorization_code";
}
