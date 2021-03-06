/*
The MIT License (MIT)

Copyright (c) Terry Evans Vaughn "VCSCode"

All rights reserved.

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */
package com.blueseer.ctr;

import bsmf.MainFrame;
import com.blueseer.utl.OVData;
import static bsmf.MainFrame.backgroundcolor;
import static bsmf.MainFrame.backgroundpanel;
import java.awt.Color;
import java.awt.GradientPaint;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 *
 * @author vaughnte
 */


public class POSControlMaint extends javax.swing.JPanel {

    /**
     * Creates new form ClockControl
     */
    public POSControlMaint() {
        initComponents();
    }

    
     public void getcontrol() {
          DecimalFormat df = new DecimalFormat("#0.00");   
         try {

            Class.forName(bsmf.MainFrame.driver).newInstance();
            bsmf.MainFrame.con = DriverManager.getConnection(bsmf.MainFrame.url + bsmf.MainFrame.db, bsmf.MainFrame.user, bsmf.MainFrame.pass);
            try {
                Statement st = bsmf.MainFrame.con.createStatement();
                ResultSet res = null;
                
                int i = 0;
                    res = st.executeQuery("SELECT * FROM  pos_ctrl;");
                    while (res.next()) {
                        i++;
                        taxpercent.setText(df.format(res.getDouble("posc_taxpercent")));
                        ddbank.setSelectedItem(res.getString("posc_bank"));
                        tbtaxacct.setText(res.getString("posc_taxacct"));
                       
                    }
           
            }
            catch (SQLException s) {
                MainFrame.bslog(s);
                bsmf.MainFrame.show("Unable to retrieve ov_ctrl");
            }
            bsmf.MainFrame.con.close();
        } catch (Exception e) {
            MainFrame.bslog(e);
        }
     }
    
    
    public void initvars(String[] arg) {
        ddbank.removeAllItems();
        ArrayList<String> mybanks = OVData.getbanklist();
        for (String bank : mybanks) {
            ddbank.addItem(bank);
        }
        getcontrol();
        
           
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btupdate = new javax.swing.JButton();
        taxpercent = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        tbtaxacct = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        ddbank = new javax.swing.JComboBox<>();

        setBackground(new java.awt.Color(0, 102, 204));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("POS Control"));

        btupdate.setText("Update");
        btupdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btupdateActionPerformed(evt);
            }
        });

        taxpercent.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                taxpercentFocusLost(evt);
            }
        });

        jLabel1.setText("Sales Tax Percent");

        jLabel2.setText("Tax Account");

        jLabel3.setText("Bank");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btupdate)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(taxpercent, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tbtaxacct)
                            .addComponent(ddbank, 0, 131, Short.MAX_VALUE))))
                .addContainerGap(53, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(taxpercent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tbtaxacct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(ddbank, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btupdate)
                .addGap(26, 26, 26))
        );

        add(jPanel1);
    }// </editor-fold>//GEN-END:initComponents

    private void btupdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btupdateActionPerformed
        try {

            Class.forName(bsmf.MainFrame.driver).newInstance();
            bsmf.MainFrame.con = DriverManager.getConnection(bsmf.MainFrame.url + bsmf.MainFrame.db, bsmf.MainFrame.user, bsmf.MainFrame.pass);
            try {
                Statement st = bsmf.MainFrame.con.createStatement();
                ResultSet res = null;
                boolean proceed = true;
                int i = 0;
                String login = "";
                
             
                
                
                res = st.executeQuery("SELECT *  FROM  pos_ctrl ;");
                    while (res.next()) {
                        i++;
                    }
                if (i == 0) {
                    
                    st.executeUpdate("insert into pos_ctrl values (" + 
                            "'" + taxpercent.getText() + "'" +  "," +
                            "'" + tbtaxacct.getText() + "'" + "," +
                            "'" + ddbank.getSelectedItem().toString() + "'" +
                            
                            ")"  + ";");              
                          bsmf.MainFrame.show("Inserting Defaults");
                } else {
                    st.executeUpdate("update pos_ctrl set " +
                            " posc_taxpercent = " + "'" + taxpercent.getText() + "'" + "," +
                            " posc_taxacct = " + "'" + tbtaxacct.getText() + "'"  + "," +
                            " posc_bank = " + "'" + ddbank.getSelectedItem().toString() + "'" +
                            ";");   
                   
                    bsmf.MainFrame.show("Updated Defaults");
                   
                }
              
            } catch (SQLException s) {
                MainFrame.bslog(s);
                bsmf.MainFrame.show("Problem updating pos_ctrl");
            }
            bsmf.MainFrame.con.close();
        } catch (Exception e) {
            MainFrame.bslog(e);
        }
    }//GEN-LAST:event_btupdateActionPerformed

    private void taxpercentFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_taxpercentFocusLost
        DecimalFormat df = new DecimalFormat("#0.00");
        if (! taxpercent.getText().isEmpty()) {
        double taxpct = Double.valueOf(taxpercent.getText());
        taxpercent.setText(df.format(taxpct));
        }
    }//GEN-LAST:event_taxpercentFocusLost


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btupdate;
    private javax.swing.JComboBox<String> ddbank;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField taxpercent;
    private javax.swing.JTextField tbtaxacct;
    // End of variables declaration//GEN-END:variables
}
