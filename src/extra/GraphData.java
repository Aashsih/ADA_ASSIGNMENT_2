/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extra;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import q1.SpiderLeg;

/**
 *
 * @author aashi
 */
public class GraphData {
        //for every get-method of the WebPageDetails class that retuern a boolean
    //throw and Exception if they return false
    private Map<String, WebPageGraph> webPageInfo;
    
    
    public GraphData(List<String> urls){ 
        
        webPageInfo = new HashMap<>();
        
        //for-each url in urls extract all the information and then add it to the Map
        for(String url : urls){
//            boolean isValid = code to check validity of the url;
//            if(!isValid){
//                
//                notify the user the 'url' in this iteration is invalid and was not added
//            }
//          else execute the code below
            WebPageGraph webPage = new WebPageGraph();
            //extract all the details from the html specified in the url
            //and store that information in webPage
            
            //------------------------------------------------------
            webPageInfo.put(url, webPage);
            //else ends here
            
        }        
    }
    
    
    public GraphData(String url){ 
       
        webPageInfo = new HashMap<>();
        
        
        
//            boolean isValid = code to check validity of the url;
//            if(!isValid){
//                notify the user the 'url' in this iteration is invalid and was not added
//            }
//      else execute the code below
        WebPageGraph webPage = new WebPageGraph();
            //extract all the details from the html specified in the url
            //and store that information in webPage
            
            //------------------------------------------------------
        webPageInfo.put(url, webPage);
            
            
               
    }
    
    //is called from the constructor to parse the URL and make a graph
    private void extractInfoFromUrl(String url){
  
        try { 
            Document doc = Jsoup.connect(url).get();
           // Document doc = Jsoup.connect(url).timeout();
            
        } catch (IOException ex) {
            Logger.getLogger(SpiderLeg.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
