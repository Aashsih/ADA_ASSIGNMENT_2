/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package q1;

import java.net.MalformedURLException;
import java.util.List;

/**
 *
 * @author aashi
 */
public interface WebScrapper {
    
    public void getTitle(String url) throws IllegalArgumentException;
    
    public void getHyperlink(String url) throws IllegalArgumentException;
    
    public void getImages(String url) throws IllegalArgumentException;
    
    public void getMeta(String url) throws IllegalArgumentException;
    
}
