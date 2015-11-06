package lv.rv;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import javax.xml.bind.JAXB;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;


import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 *
 * 
 * takes recently loaded xml file from heap
 * generates banner in html? format (or update existing html)
 */

public class BannerWriter implements Runnable
{
    public final static String m="BannerWriter";
    private PukuBanners banners;
    static boolean quit=false;
     
    public BannerWriter(PukuBanners pBanners)
    {
        if (pBanners==null || pBanners.getBannerSize()==0)
            banners = getCurrentBanners();
        else
            banners = pBanners;
    }
    
    @Override
    public void run()
        {
            
            int refreshInterval, x=0;
            
            
            //Depend on if we want to refresh banners each time 
            // new banners are polled from xml
            if (Common.XML_READ_INTERVAL<=Common.HTML_WRITE_INTERVAL)
            {
                refreshInterval=1;
            }
            else
            {
                refreshInterval=(int)(Common.XML_READ_INTERVAL/Common.HTML_WRITE_INTERVAL);
            }
            
            Common.log("Refresh interval: ".concat(String.valueOf(refreshInterval)),m);
            
            do
            {                      
                           if (quit)
                               {
                                   Common.log("quit",m);
                                   return;
                               }
                            if(!Thread.interrupted())	
                            {  
                                  if (banners==null || x>refreshInterval)
                                  {
                                      Common.log("get banner list",m);
                                      x=0;
                                      banners=getCurrentBanners();
                                  }
                                
                                  if (banners!=null)
                                  {
                                      Object o = banners.getPbs().poll();
                                      if (o!=null)
                                      {
                                        PukuBanner pb=(PukuBanner)o;
                                        try
                                        {
                                          Common.log("New banner found ".concat(pb.getPrice()),m);
                                          ban2Html(pb.getBannerAsHtml());
                                        }                                        
                                        catch (IOException ie)
                                        {
                                            Common.log("IOException!",m);
                                            ie.printStackTrace();
                                            return;
                                        }
                                      }
                                      //take each minute new element from banners
                                      // remove taken element so we dont use it again
                                      // if all elements are used or xml is about to refresh - start over
                                      
                                  x+=1;
                                  }
                                  else
                                  {
                                     Common.log("WARNING. banners were not generated yet.",m);
                                  }
                             try
                             {    
                              Common.log("sleep for minutes: ".concat( 
                                      String.valueOf(Common.getMinFromMs(Common.HTML_WRITE_INTERVAL))
                                                            )
                                      ,m);
                              Thread.sleep(Common.HTML_WRITE_INTERVAL);
                             }
                                    catch(InterruptedException le)
                                    {
                                        Common.log("was interuppted.",m);
                                        le.printStackTrace();
                                        return;	
                                    }
                        }            
                        else
                        {
                                Common.log("Interruption",m);
                                return;
                        }
            }                
            while (true);
               
            }
        /**
         * 
         * @return 
         */
        private PukuBanners getCurrentBanners()
        {
            if (BannerReader.xml==null)
                return null;
            
          PukuBanners lBanners=null;   
         try
         {
                DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();  
                //throws parser config exception
                DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();  
                Document doc = documentBuilder.parse(new InputSource(new StringReader(BannerReader.xml)));
                doc.getDocumentElement().normalize();  
                lBanners = JAXB.unmarshal(new StringReader(BannerReader.xml), PukuBanners.class);
            
            if (lBanners!=null)
            {
                Common.log(lBanners.toString(),m);
            } 
         }
         catch (ParserConfigurationException pce)
         {
             Common.log("ParserConfigurationException",m);
             pce.printStackTrace();
         }
         catch (SAXException sex)
         {
             Common.log("SAXException",m);
             sex.printStackTrace();
         }
         catch (IOException io)
         {
             Common.log("IOException",m);
             io.printStackTrace();
             System.exit(1);
         }
         return lBanners;
        }
        
        
        /**
         * 
         * @param html
         * @throws IOException 
         */
        private void ban2Html(String html) throws IOException
        {
            if (html==null || html.trim().length()==0)
            {
                Common.log("ERROR. Empty html",m);
                return;
            }
      
           
            
            File f = new File(Common.OUT_FILE);
            
            FileWriter fstream = new FileWriter(f , false);
            fstream.write(html);     
            fstream.close();
            Common.log("banner wrote to file.",m);
        }
}
