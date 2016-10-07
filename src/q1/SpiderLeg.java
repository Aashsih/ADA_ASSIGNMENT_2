package q1;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author aashi
 */
public class SpiderLeg implements WebScrapper{ 
    private final static String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36";
    
    private String title;
    private List<String> metaDataDescription;
    private List<String> metaDataKeyword;
    private List<String> hyperlinks;
    private List<String> imageInfo;

    public SpiderLeg(){
        this.title = "";
        this.metaDataDescription = new ArrayList<>();
        this.metaDataKeyword = new ArrayList<>();
        this.hyperlinks = new ArrayList<>();
        this.imageInfo = new ArrayList<>();
    }
    
    
    
    public final void getTitle(String url) throws IllegalArgumentException{
        extractTitle(url);
        System.out.print("The title of the WebPage is: ");
        if(this.title.equals("")){
            System.out.println("not available");
        }
        else{
            System.out.println(title);
        }
        System.out.println("--------------------------------------------------------");
    }
    public final void getHyperlink(String url) throws IllegalArgumentException{
        extractHyperlinks(url);
        System.out.println("The Following are the hyperlinks the web page contains");
        System.out.println("--------------------------------------------------------");
        int number = 0;
        for(String hyperlink : hyperlinks){
            System.out.println(++number + ". " + hyperlink);
        }
        System.out.println("--------------------------------------------------------");
    }
    public final void getImages(String url) throws IllegalArgumentException{
        extractImageInfo(url);
        System.out.println("The Following are the image names the web page contains");
        System.out.println("--------------------------------------------------------");
        int number = 0;
        for(String imageInformation : this.imageInfo){
            String[] imageData = imageInformation.split(",");
            System.out.println(++number + ". name: " + imageData[0] +", Width: " + imageData[1] + ", height: " + imageData[2] + ", alt: " + imageData[3] + "\n");
        }
        System.out.println("--------------------------------------------------------");
    }
    public final void getMeta(String url) throws IllegalArgumentException{
        extractMetaDataDescription(url);
        extractMetaDataKeywords(url);
        System.out.print("The meta data of the WebPage is: \n");
        System.out.println("--------------------------------------------------------");
        for(int i = 0; i < this.metaDataDescription.size(); i++){
            System.out.println("Meta description : " + this.metaDataDescription.get(i) + "\n");
        }
        for(int i = 0; i < this.metaDataKeyword.size(); i++){
            System.out.println("Meta keyword : " + this.metaDataKeyword.get(i) + "\n");
        }
    }
    
   
    public final void extractAllContent(String url) throws IllegalArgumentException{
        try {
            Connection conn = Jsoup.connect(url);
            //conn.timeout(10000);
            Document webPage = conn.userAgent(USER_AGENT).get();
            
            this.title = webPage.title();
            if(title == null || title.equals("")){
                this.title = "";
            }
            for(Element hyperLink : webPage.select("a[href]")){
                this.hyperlinks.add(hyperLink.attr("abs:href"));
            }
            String imageInfo = "";
            for (Element imageName : webPage.select("img[src]")) {
                String src = imageName.attr("abs:src"); //extra
                imageInfo =imageName.attr("name") +" ," + imageName.attr("width") +" ,"+
                           imageName.attr("height") +" , "+ imageName.attr("alt");
                this.imageInfo.add(imageInfo);
                
//            InputStream stream = new URL(src).openStream();
//            Object obj = ImageIO.createImageInputStream(stream);
//            ImageReader reader = ImageIO.getImageReaders(obj).next();
//            imageInfo +="Width:" + reader.getWidth(0) + ", Height: " + reader.getHeight(0) + "\n";
//            stream.close();
            }
            Elements description =  webPage.select("meta[name*=description]");
            Elements keywords =  webPage.select("meta[name*=keywords]");
         
            for(Element content : description){
                this.metaDataDescription.add(content.attr("content"));
                
            }
            
            for(Element keyword : keywords){
                this.metaDataKeyword.add(keyword.attr("content"));
        
            }
            
        } catch (IOException ex) {
            this.title = "";
            this.metaDataDescription = new ArrayList<>();
            this.metaDataKeyword = new ArrayList<>();
            this.hyperlinks = new ArrayList<>();
            this.imageInfo = new ArrayList<>();
        }
        
    }
    
    
     public final String extractTitle(String url) throws IllegalArgumentException{
        try {
            Connection conn = Jsoup.connect(url);
            //conn.timeout(10000);
            Document webPage = conn.userAgent(USER_AGENT).get();
            
            this.title = webPage.title();
            if(title == null || title.equals("")){
                this.title = "";
            }
            
        }
        catch (IOException ex) {
            this.title = "";
        }
        return this.title;
    }

    public final List<String> extractHyperlinks(String url) throws IllegalArgumentException{
        try {
            Connection conn = Jsoup.connect(url);
            //conn.timeout(10000);
            Document webPage = conn.userAgent(USER_AGENT).get();
            this.hyperlinks = new ArrayList<>();
            for(Element hyperLink : webPage.select("a[href]")){
                this.hyperlinks.add(hyperLink.attr("abs:href"));
            }
                                
        } catch (IOException ex) {
           
        }
        return this.hyperlinks;
    }
    
    
    

    public final List<String> extractImageInfo(String url) throws IllegalArgumentException{
         try {
            Connection conn = Jsoup.connect(url);
            //conn.timeout(10000);
            Document webPage = conn.userAgent(USER_AGENT).get();
            this.imageInfo = new ArrayList<>();
            String imageInfo = "";
            for (Element imageName : webPage.select("img[src]")) {
                String src = imageName.attr("abs:src"); //extra
                imageInfo =imageName.attr("name") +" ," + imageName.attr("width") +" ,"+
                           imageName.attr("height") +" , "+ imageName.attr("alt");
                this.imageInfo.add(imageInfo);
                
//            InputStream stream = new URL(src).openStream();
//            Object obj = ImageIO.createImageInputStream(stream);
//            ImageReader reader = ImageIO.getImageReaders(obj).next();
//            imageInfo +="Width:" + reader.getWidth(0) + ", Height: " + reader.getHeight(0) + "\n";
//            stream.close();
            }
            
        } catch (IOException ex) {
            
        }
        return this.imageInfo;
    }

    

    public final List<String> extractMetaDataDescription(String url) throws IllegalArgumentException{
        try {
            Connection conn = Jsoup.connect(url);
            //conn.timeout(10000);
            Document webPage = conn.userAgent(USER_AGENT).get();
            String metaInfo =  "";
            
            Elements description =  webPage.select("meta[name=description]");
            
         
            for(Element content : description){
                this.metaDataDescription.add(content.attr("content"));
                
            }
           
        } catch (IOException ex) {
            
        }
        return this.metaDataDescription;
    }
    public final List<String> extractMetaDataKeywords(String url)throws IllegalArgumentException{
        try {
            Connection conn = Jsoup.connect(url);
            //conn.timeout(10000);
            Document webPage = conn.userAgent(USER_AGENT).get();
            String metaInfo =  "";
         
            Elements keywords =  webPage.select("meta[name=keywords]");
            for(Element keyword : keywords){
                this.metaDataKeyword.add(keyword.attr("content"));
        
            }
         
        } catch (IOException ex) {
            
        }
        return this.metaDataKeyword;
    }
    
//    //Getters and Setters
//    public List<String> getMetaDataDescription() {
//        return metaDataDescription;
//    }
//
//    public List<String> getMetaDataKeyword() {
//        return metaDataKeyword;
//    }
//
//    public List<String> getHyperlinks() {
//        return hyperlinks;
//    }
//
//    public List<String> getImageInfo() {
//        return imageInfo;
//    }
//    
//    public String getWebPageTitle(){
//        return this.title;
//    }
    
    
    public static void main(String[] args){
        SpiderLeg leg = new SpiderLeg();
        String url1 = "https://www.google.co.nz/?gfe_rd=cr&ei=mtq_V9KTGMnN8gevq6KICQ";
        String url2 = "https://www.google.co.nz";
        try{
            leg.getTitle(url2);
            leg.getHyperlink(url2);
            leg.getImages(url2);
            leg.getMeta(url2);
        }
        catch(IllegalArgumentException mue){
            System.out.println("Enter a valid url, not some crap!!");
        }
        
    }
}
