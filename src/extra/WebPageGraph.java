/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extra;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author aashi
 */
public class WebPageGraph {

    Map<Vertex,List<String>> graph;
    
    public WebPageGraph(){
        graph = new HashMap<>();
//        Vertex vertex  = new Vertex();
//        List<Vertex> 
    }
    
  
    
    private class Vertex {
        private String title;
        private String hyperLink;
        private String imageURL;
        private String metaData;
        
        @Override
        public int hashCode(){
            return hyperLink.hashCode();
        }
        
//        public Vertex(String title, String hyperLink, String imageURL, String metaData){
//            this.title = title;
//            this.hyperLink = hyperLink;
//            this.imageURL = imageURL;
//            this.metaData = metaData;
//        }
        
    }
    
    
    
   
    
}
