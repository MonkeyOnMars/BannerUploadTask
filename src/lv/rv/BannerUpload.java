package lv.rv;

import java.io.File;
import java.io.IOException;

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
    public static void main(String[] args) 
    {
        Common.log("===================");
        Common.log("Use CTRL+C to exit.");
        Common.log("===================");
        Common.log("");
        
        
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
        
        Common.log("Banner will be written to ".concat(Common.OUT_FILE));
        Common.log("You can use bannerConsumer.html to see results as well. This page injects generated banner.html onload.");
        
        
        Common.log("START...");
        
        BannerReader br =  new BannerReader(Common.XML_URL);
        BannerWriter bw = new BannerWriter(null);
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
