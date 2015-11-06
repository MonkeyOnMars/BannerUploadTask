package lv.rv;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


/**
 *
// *  1. Читать с ресурса http://www.pukukurjers.lv/veikals.xml данные раз в 15 минут 
// *      (можно использовать любое решение шедулинга доступное в java ).
// *   2. Данные с xml файла сохранять в памяти или в базе данных (на выбор)
// *   3. Данные из памяти/базы раз в 1 минуту записывать в html файл (данные отображать ввиде html-блока(баннера) 
//      дизайн любой 
//    4. Оформить как консольную аппликацию(предпочтительно) и соответственно html 
//    файл просто будет создаваться/перезаписиваться в директории.
//    5. Решение можно выложить на github или прислать в архиве(на выбор) 
//    при необходимости с инструкциями запуска.
// */
public class BannerUpload 
{
    
    /**
     * load properties file 
     * if found updates default application config from properties file.
     */ 
    private static void readProps()
    {
      
      Properties prop = new Properties();
	InputStream input = null;

	try {
		input = new FileInputStream("cfg/config.properties");
		// load a properties file
		prop.load(input);

		// get the property value and print it out
                if (prop.getProperty("OUT_FILE")!=null && prop.getProperty("OUT_FILE").trim().length()>0)
                {
                    Common.OUT_FILE=prop.getProperty("OUT_FILE");
                }
		
               if (prop.getProperty("READ_TIME")!=null && prop.getProperty("READ_TIME").trim().length()>0)
               {
                   int read_time=(new Integer(prop.getProperty("READ_TIME"))).intValue();
                   //convert to ms
                   read_time=read_time*60*1000;
                   Common.XML_READ_INTERVAL= read_time; //new Integer(prop.getProperty("READ_TIME"));
               }
               
               if (prop.getProperty("WRITE_TIME")!=null && prop.getProperty("WRITE_TIME").trim().length()>0)
               {
                    int write_time=(new Integer(prop.getProperty("WRITE_TIME"))).intValue();
                    write_time=write_time*60*1000;
                   Common.HTML_WRITE_INTERVAL= write_time;
               }             
                 if (prop.getProperty("XML_URL")!=null && prop.getProperty("XML_URL").trim().length()>0)
                    Common.XML_URL=prop.getProperty("XML_URL");             
          
          

	} catch (IOException ex) {
		ex.printStackTrace();
	} finally {
		if (input != null) {
			try {
				input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
    }
    /**
     * 
     * @param args 
     */
    public static void main(String[] args) 
    {
        
        BannerUpload.readProps();
        
        Common.log(System.getProperty("line.separator"));
        Common.log("===================");
        Common.log("Use CTRL+C to exit.");
        Common.log("===================");
        Common.log(System.getProperty("line.separator"));
        
        
         if (Common.OUT_FILE ==null)
            {
                try
                {        
                 Common.OUT_FILE = new StringBuffer( (new File(".").getCanonicalPath()) )
                         .append("/banner.html").toString();
                }
                catch (IOException io)
                {
                    Common.log("IO Exception looking for current proj directory.");
                    return;
                }               
               // Common.log(new StringBuffer("Set out to: ").append(Common.OUT_FILE).toString());
            }
        Common.log(System.getProperty("line.separator"));
        Common.log("Banner will be written to ".concat(Common.OUT_FILE));
        Common.log("You can use bannerConsumer.html to see results as well. This page injects generated banner.html onload.");
        
         Common.log(new StringBuffer("Read Interval: ").append(Common.getMinFromMs(Common.XML_READ_INTERVAL)).toString());
         Common.log(new StringBuffer("Write Interval: ").append(Common.getMinFromMs(Common.HTML_WRITE_INTERVAL)).toString());
         Common.log("Update config file cfg/config.properties to change this params.");
         
         Common.log(System.getProperty("line.separator"));
         Common.log("START...");
        
        BannerReader br =  new BannerReader(Common.XML_URL);
        BannerWriter bw =  new BannerWriter(null);
        Thread[] trs = new Thread[2];
        trs[0]=new Thread(br);
        trs[1]=new Thread(bw);
        
        trs[0].start();
        try
        {
            Thread.sleep(1000);
        }
        catch (InterruptedException ex)
        {
            
        }
        trs[1].start();
        
        try
        {
            trs[1].join();
        }
        catch (InterruptedException e)
        {
                e.printStackTrace();
        }       
        
    }
    
}
