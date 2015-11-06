package lv.rv;


import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;



/**
 *
 * @author root
 * reads url and save xml to heap/db every 15 min
 */
public class BannerReader implements Runnable
{
       public static String xml;       
       private final String urlPath;
       public static String m = "BannerReader";
      
       static boolean quit=false;
        
       public static String getXml()
       {
           return xml;
       }
       
       public BannerReader(String pUrlPath)
       {
           urlPath=pUrlPath;
       }
       
       @Override
        public void run()
        {
         Common.log("start",m);
         
         do
         {    
               if (quit)
               {
                   Common.log("quit");
                   return;
               }
                if(!Thread.interrupted())	
                {    			
                URL url = null;
                try
                {
                   url = new URL(urlPath);
                   final URLConnection connection = url.openConnection();
                   connection.connect();

              
                                       InputStream ip = null;
                                       try
                                       {
                                       connection.connect();
                                       ip = connection.getInputStream();
                                       java.util.Scanner s = new java.util.Scanner(ip).useDelimiter("\\A");

                                       synchronized (this)
                                               {
                                                   xml = s.next();
                                                   Common.log("xml generated, length: "
                                                           .concat(String.valueOf(xml.length())),m);
                                               }
                                       ip.close();
                                       }
                                       catch (Exception ex)
                                       {
                                           Common.log("Exception. Something went terribly wrong.",m);
                                           ex.printStackTrace();
                                           return;
                                       }
                                        finally
                                       {
                                           if(ip != null) 
                                           {
                                               try
                                               {
                                               ip.close();
                                               }
                                               catch (IOException ioe)
                                               {
                                                   //System.out.println("ioe error in potok".concat(String.valueOf(threadId)));
                                                   Common.log("IOException. Cant close inputStream. Something went terribly wrong.",m);
                                                   ioe.printStackTrace();
                                                   return;
                                               }
                                           }

                                       }
                       
                        try
                        {   
                             Common.log("sleep for minutes ".concat( 
                                      String.valueOf(Common.getMinFromMs(Common.XML_READ_INTERVAL))
                                                            )
                                      ,m);
                            Thread.sleep(Common.XML_READ_INTERVAL);
                        }
                        catch(InterruptedException le)
                        {
                            Common.log("was interuppted.",m);
                            le.printStackTrace();
                            return;	
                        }
                
                
                } 
                 catch (MalformedURLException mue)
                {
                            Common.log(new StringBuffer("Error. Something went terribly wrong. url Malformed. ")
                                    .append(urlPath).toString(),m);
                            mue.printStackTrace();
                             return;
                }
                 catch (IOException ioe)
                {
                    Common.log(new StringBuffer("Error. IO. ").append(urlPath).toString(),m);
                    ioe.printStackTrace();
                    return;
                }
                }
         }
         while (true);
          
         }
         
        

       
        
 
}
