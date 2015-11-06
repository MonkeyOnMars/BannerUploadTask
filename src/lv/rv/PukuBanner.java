package lv.rv;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "item")
@XmlAccessorType(XmlAccessType.FIELD)
public class PukuBanner // TODO banner should be abstract or interface implements Banner 
{
    
    //TODO use template here as well 
    public String getBannerAsHtml() {
          
            return new StringBuffer(
                    "<DIV id='pukub' title='")
                    .append(getText())
                    .append("'")
                    .append(">")
                        .append("<a href='").append(getLink()).append("'").append(">") 
                        .append("<div id='pukuTop'>")
                        .append(getTitle())
                        .append("<H1>").append(getPrice()).append("</H1>")
                        .append("</DIV>")
                        .append("<div id='pukuBody'>")
                         .append("<img")
                         .append(" id='pukuBannerImg' src='")
                         .append(getImage())
                         .append("'")                        
                         .append("/>")
                        .append("</div>")
                    
                        .append("<div id='pukuBtm'>")
                        .append(getText())
                        .append("</div>")
                    .append("</a>")                        
                    .append("</div>")
                    .toString();
        }

     

        @XmlElement(name = "title")
        private String title;
        
        @XmlElement(name = "text")
        private String text;
        
        @XmlElement(name = "link")
        private String link;
        
        @XmlElement(name = "image")
        private String image;
        
        @XmlElement(name = "price")
        private String price;

        private BigDecimal priceValue;
        private String currency;
        
       
        /**
         * @return the title
         */
        public String getTitle() {
            return title;
        }

        /**
         * @param title the title to set
         */
        public void setTitle(String title) {
            this.title = title;
        }

        /**
         * @return the text
         */
        public String getText() {
            return text;
        }

        /**
         * @param text the text to set
         */
        public void setText(String text) {
            this.text = text;
        }

        /**
         * @return the link
         */
        public String getLink() {
            return link;
        }

        /**
         * @param link the link to set
         */
        public void setLink(String link) {
            this.link = link;
        }

        /**
         * @return the image
         */
        public String getImage() {
            return image;
        }

        /**
         * @param image the image to set
         */
        public void setImage(String image) {
            this.image = image;
        }

        /**
         * @return the price
         */
        public String getPrice() {
            return price;
        }

        /**
         * @param price the price to set
         */
        public void setPrice(String price) {
            this.price = price;
        }

        /**
         * @return the priceValue
         */
        public BigDecimal getPriceValue() {
            return priceValue;
        }

        /**
         * @param priceValue the priceValue to set
         */
        public void setPriceValue(BigDecimal priceValue) {
            this.priceValue = priceValue;
        }

        /**
         * @return the currency
         */
        public String getCurrency() {
            return currency;
        }

        /**
         * @param currency the currency to set
         */
        public void setCurrency(String currency) {
            this.currency = currency;
        }
        
        

}
