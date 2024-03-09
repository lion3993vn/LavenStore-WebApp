/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lavenstore.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Pham Hieu
 */
public class Utils {

    public static Timestamp convertStringToTimestamp(String dateString) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        java.util.Date parsedDate = format.parse(dateString);
        return new Timestamp(parsedDate.getTime());
    }
}
