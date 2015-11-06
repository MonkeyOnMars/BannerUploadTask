
package lv.rv;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Common 
{
    public static  String OUT_FILE               = null; //"C:/temp/banner.html";
    public final   static String XML_URL         = "http://www.pukukurjers.lv/veikals.xml";
    public static  int XML_READ_INTERVAL   = 15*60*1000;//1*60*1000;
    public static  int HTML_WRITE_INTERVAL = 1*60*1000; //1*30*1000;
    
    public final static boolean db=false;
    public final static boolean DBG = true;   
        
    public static int getMinFromMs(int milis)
     {
         return milis/60/1000;
     }
//TODO replace with log4j
 
    public static void log(String msg)
    {
        if (DBG)
        System.out.println(msg);
    }
    
     public static void log(String msg, String method)
    {
        if (DBG)
        {
        if (method==null)
            Common.log(msg);
        else        
        {
            Date d = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss:SSS");

            System.out.println(new StringBuffer( formatter.format(d)).append(": ")
                    .append(method).append(": ").append(msg).toString());
        }
        }
    }   
     
     
     
}
