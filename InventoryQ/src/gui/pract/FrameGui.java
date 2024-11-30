/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.pract;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author HP
 */
public class FrameGui extends JFrame{
    //panels
    private JPanel idpnl;
    private JPanel quatitypnl;
    private JPanel buttonpnl;
    private JPanel mainpnl;
    // labels
    private JLabel idlbl;
    private JLabel quantitylbl;
    
    //text fields
    private JTextField idfld;
    private JTextField quantityfld;
    
    //buttons
    private JButton checkStockbtn;
    private JButton clearbtn;
    private JButton exitbtn;
    
    //list of products
   

    public FrameGui() throws Exception {
        //populating list of products
    
        
     JPanel idpnl= new JPanel();
     JPanel quatitypnl=new JPanel();
     JPanel buttonpnl= new JPanel();
     JPanel mainpnl= new JPanel(new BorderLayout());
     
     JLabel idlbl= new JLabel("Enter product ID: ");
     JLabel quantitylbl= new JLabel("Enter Quantity: ");
     JTextField idfld= new JTextField(20);
     JTextField quatityfld= new JTextField(8);
     
     JButton checkStockbtn= new JButton("Check Stock");
     checkStockbtn.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
             
             //retrieving inputs form user
             String prodIDEntered=idfld.getText();
             int quantityEntered=Integer.parseInt(quatityfld.getText());
             
             //creating a product obj with input
             Product theProduct=new Product(prodIDEntered, quantityEntered);
             
             StockCheck stockCheck;
             try {
                 stockCheck = new StockCheck(new File("Inventory.txt"));
                 boolean inStock=stockCheck.isProductInStock(theProduct);
                 
                 
                 if(inStock){
                    int amnt=stockCheck.getRstockAmount(theProduct);
                     System.out.println("("+theProduct.getProductId()+") In stock : restock needed ="+ amnt +" UNITS");
                 }
                 else{
                     System.out.println("("+theProduct.getProductId()+") out of stock : restock needed ="+ 100 +" UNITS");
                 }
                                  
             } catch (Exception ex) {
                 Logger.getLogger(FrameGui.class.getName()).log(Level.SEVERE, null, ex);
             }
             
         }
     });
     
     JButton clearbtn= new JButton("Clear");
     clearbtn.addActionListener((e) -> {
         idfld.setText("");
         quatityfld.setText("");
     });
     JButton exitbtn= new JButton("Exit");
     
     idpnl.add(idlbl);
     idpnl.add(idfld);
     
     quatitypnl.add(quantitylbl);
     quatitypnl.add(quatityfld);
     quatitypnl.add(checkStockbtn);
     
     buttonpnl.add(clearbtn);
     buttonpnl.add(exitbtn);
     
     mainpnl.add(idpnl, BorderLayout.NORTH);
     mainpnl.add(quatitypnl, BorderLayout.CENTER);
     mainpnl.add(buttonpnl, BorderLayout.SOUTH);
     add(mainpnl);
     
    setTitle("Inventory Management");
    setSize(400,400);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    pack();
    setVisible(true);
    
       
             
    }

    public class StockCheck implements ActionListener{
        private List<Product> products;
        
        StockCheck(File file) throws Exception{
            
            if(file.exists()){
                if(file.canRead()){
                     products= readfromInventoryFile(file);
                }
            }
        }
        
        public List<Product> readfromInventoryFile(File theFile) throws Exception{
           List<Product> products=new ArrayList<>();

               FileReader fr;
           try {
               
               fr = new FileReader(theFile);
               BufferedReader br=new BufferedReader(fr);
               String line=br.readLine();
               while(line!=null){
                   String[] arrTemp=line.split("\t");
                   String productId=arrTemp[0];
                   int quantity=Integer.parseInt(arrTemp[1]);
                   Product p=new Product(productId, quantity);
                   products.add(p);
                   
                   line=br.readLine();
               }
               br.close();
               fr.close();

           } catch (Exception ex) {
               Logger.getLogger(FrameGui.class.getName()).log(Level.SEVERE, null, ex);
           }
           return (List)products;
        }
        
        public boolean isProductInStock(Product product){
           for(Product theObj: products){
               if(theObj.getProductId().equalsIgnoreCase(product.getProductId())){
                   if(theObj.getQuantity()>0){
                       return true;
                   }
                   else{
                       return false;
                   }
               }
           }
           return false;
        }
        
        public int getRstockAmount(Product p){
            int restockAmnt=0;
            for(Product theP: products){
                if(theP.getProductId().equalsIgnoreCase(p.getProductId())){
                    restockAmnt=100-theP.getQuantity();
                }
            }
            return restockAmnt;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
        }
    }
    
    
}
