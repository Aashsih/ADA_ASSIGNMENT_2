/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package q2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;
import q1.SpiderLeg;

/**
 *
 * @author aashi
 */
public final class Spider {
    private static final int DEPTH_OF_EXPLORATION = 2;
    
   
    public static final List<String> performWebSearch(Set<String> initialURLs, String keyword){// throws IllegalArgumentException {
        return crawl(initialURLs, keyword, DEPTH_OF_EXPLORATION);
    }
    
    //still havent implemented the distance part.
    private static final List<String> crawl(Set<String> initialURLs, String keyword, int depthOfExploration){// throws IllegalArgumentException{
       
       Queue<String> unvisitedURLs = new ConcurrentLinkedQueue<>(initialURLs);
       Set<String> visitedURLs = new HashSet<>();
       List<Integer> distance = new ArrayList<>();
       SpiderLeg webContent = new SpiderLeg();
       
       for(String url : initialURLs){
           distance.add(0);
       }
       int i = 0;
       while(!unvisitedURLs.isEmpty() && distance.get(i) < depthOfExploration){
           String aURL = unvisitedURLs.remove();
           visitedURLs.add(aURL);
           //webContent.extractAllContent(aURL);//later change this to extractin something specific to verify if it is HTML
           try{
               
               for(String hyperlink : webContent.extractHyperlinks(aURL)){
                   if(isContentRelevant(hyperlink, keyword) && !visitedURLs.contains(hyperlink) && !unvisitedURLs.contains(hyperlink)){
                       distance.add(distance.get(i) + 1);//check this again
                       unvisitedURLs.add(hyperlink);
                       
                   }
               }
           }
           catch(IllegalArgumentException iae){
               
           }
           i++;
       }
       if(visitedURLs.size() == initialURLs.size()){
           return null;
       }
       return new ArrayList<String>(visitedURLs);
   } 
   
    //Complete later
    private static final boolean isContentHTML(SpiderLeg webContent){
       return true;
    }
    //Makes Rules here
    private static final boolean isContentRelevant(String url, String keyword){
       //Instantiate another SpiderLeg here, extract all content from the url and check if it is relecant by some rules
       return true;
    }
    
    public static void main(String[] args){
        HashSet<String> test = new HashSet<String>();
        test.add("https://www.google.co.nz/?gfe_rd=cr&ei=hSD0V_n0B7LM8geLwr7oDQ");
        //test.add("lets see what you come up with");
        List<String> urls = performWebSearch(test, "HMM");
        if(urls != null){
            for(String url : urls){
                System.out.println(url);
           }
        }
        else{
            System.out.println("Oops!! Somewhere down your life you forgot what a URL is");
        }
        
    }

//        catch(IllegalArgumentException iae){
//            iae.printStackTrace();
//            System.out.println("Ok Seriously, do you even know what a url is??");
//        }
        
}
