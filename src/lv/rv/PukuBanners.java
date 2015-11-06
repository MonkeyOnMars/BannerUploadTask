package lv.rv;


import java.util.Deque;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "content")
@XmlAccessorType(XmlAccessType.FIELD)
public class PukuBanners 
{
    @XmlElement( name = "item" )
    
    //lets use deque since there is no 
    // reqs for random banners.
    private Deque<PukuBanner> pbs; 
    /**
     * @return the pbs
     */
    public Deque<PukuBanner> getPbs() {
        return pbs;
    }

    /**
     * @param pbs the pbs to set
     * pbs - array of banners
     */
    public void setPbs(Deque<PukuBanner> pbs) {
        this.pbs = pbs;
    }
    
    @Override
    public String toString()
    {
        return "PukuBanners. Size:".concat(String.valueOf(getBannerSize()));
    }
    /**
     * 
     * @return 
     * returns size of banner array
     */
    public int getBannerSize()
    {
        return (pbs!=null?pbs.size():0);
    }
}
