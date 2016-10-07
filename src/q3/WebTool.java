/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package q3;


import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import q2.Spider;

/**
 *
 * @author aashi
 */
public class WebTool {
    private static final int FRAME_WIDTH = 1000;
    private static final int FRAME_HEIGHT = 150;
    private static final int MIN_SEED_URL_FIELD_SIZE = 50;
    private static final int MIN_KEYWORD_FIELD_SIZE = 15;
    
    private static final String FRAME_NAME = "Search Tool";
    private static final String URL_LABEL = "URL:";
    private static final String KEYWORD_LABEL = "Keyword:";
    private static final String BUTTON_NAME = "Search";
    private static final String RESULT_LABEL = "Search Results:";
    
    
    
    private JFrame webtool;
    //Search Panel Components
    private JPanel webSearchPanel;
    private JLabel url;
    private JLabel keywordLabel;
    private JTextField seedURL;
    private JTextField keyword;
    private JButton search;
    
    //SearchResult Panel Components
    private JPanel resultPanel;
    private JLabel resultLabel;
    private JScrollPane resultScrollPane;
    private JTextPane searchResult;
    
    public WebTool(){
        initGUI();
        setupGUI();
    }
    private void initGUI(){
        this.webtool = new JFrame(FRAME_NAME);
        
        //Search Panel
        this.webSearchPanel = new JPanel();
        this.url = new JLabel(URL_LABEL);
        this.keywordLabel = new JLabel(KEYWORD_LABEL);
        this.seedURL = new JTextField(MIN_SEED_URL_FIELD_SIZE);
        this.keyword = new JTextField(MIN_KEYWORD_FIELD_SIZE);
        this.search = new JButton(BUTTON_NAME);
        
        //Search Result Panel
        this.resultPanel = new JPanel();
        this.resultLabel = new JLabel(RESULT_LABEL);
        this.resultScrollPane = new JScrollPane();
        this.searchResult = new JTextPane();
    }
    private void setupGUI(){
        //if more things are going to be added while setting up a frame then make a method to setup just the fram
        webtool.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	webtool.setSize(FRAME_WIDTH, FRAME_HEIGHT);
//        
//        this.seedURL.setMinimumSize(this.seedURL.getPreferredSize());
//        this.keyword.setMinimumSize(this.keyword.getPreferredSize());
//        
        //Search Panel
        this.search.addActionListener(new SearchButtonListener());
        this.webSearchPanel.setLayout(new FlowLayout());
        this.webSearchPanel.add(this.url);
        this.webSearchPanel.add(this.seedURL);
        this.webSearchPanel.add(this.keywordLabel);
        this.webSearchPanel.add(this.keyword);
        this.webSearchPanel.add(this.search);
        
        //Search Result Panel
        this.resultScrollPane = new JScrollPane(this.searchResult);
        this.resultScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        this.resultScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        //this.resultScrollPane.add(this.searchResult);
        this.searchResult.setEditable(false);
        this.resultPanel.setLayout(new BoxLayout(this.resultPanel, BoxLayout.Y_AXIS));
        this.resultPanel.add(this.resultLabel);
        this.resultPanel.add(this.resultScrollPane);
        this.resultPanel.setVisible(false);
        
        //Add Panels To Frame
        this.webtool.getContentPane().add(this.webSearchPanel, BorderLayout.NORTH);
        this.webtool.getContentPane().add(this.resultPanel, BorderLayout.CENTER);
        this.webtool.setVisible(true);
    }
    
    public static void main(String[] args){
        WebTool aWebTool = new WebTool();
    }
    
    private class SearchButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

            String seedURL = WebTool.this.seedURL.getText();
            String keyword = WebTool.this.keyword.getText();

            HashSet<String> searchURLs = new HashSet<String>();
            searchURLs.add(seedURL);

            List<String> urls = Spider.performWebSearch(searchURLs, keyword);
            if(urls != null){
                StringBuilder result = new StringBuilder();
                for(String url : urls){
                    result.append(url + "\n");
                }
                WebTool.this.searchResult.setText(result.toString());
            }
            else{
                WebTool.this.searchResult.setText("inavlid URL");
            }
            WebTool.this.resultPanel.setVisible(true);

//            catch(IllegalArgumentException iae){
//                
//                WebTool.this.searchResult.setText("inavlid URL");
//            }
//            finally{
//                WebTool.this.resultPanel.setVisible(true);
//            }
            
        }
        
		
    }
    
}
