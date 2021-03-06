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
package com.blueseer.fgl;

import bsmf.MainFrame;
import com.blueseer.far.*;
import com.blueseer.utl.BlueSeerUtils;
import com.blueseer.utl.IBlueSeerc;
import com.blueseer.utl.OVData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.SwingWorker;

/**
 *
 * @author vaughnte
 */
public class PayControl extends javax.swing.JPanel implements IBlueSeerc {

    public PayControl() {
        initComponents();
    }

    // global variable declarations
                boolean isLoad = false;
    
    
    // interface functions implemented
    public void executeTask(String x, String[] y) { 
      
        class Task extends SwingWorker<String[], Void> {
       
          String type = "";
          String[] key = null;
          
          public Task(String type, String[] key) { 
              this.type = type;
              this.key = key;
          } 
           
        @Override
        public String[] doInBackground() throws Exception {
            String[] message = new String[2];
            message[0] = "";
            message[1] = "";
            
            
             switch(this.type) {
                case "update":
                    message = updateRecord(key);
                    break;
                case "get":
                    message = getRecord(key);    
                    break;    
                default:
                    message = new String[]{"1", "unknown action"};
            }
            
            return message;
        }
 
        
       public void done() {
            try {
            String[] message = get();
           
            BlueSeerUtils.endTask(message);
          
            
            } catch (Exception e) {
                MainFrame.bslog(e);
            } 
           
        }
    }  
      
      
       Task z = new Task(x, y); 
       z.execute(); 
       
    }
   
    public void setComponentDefaultValues() {
       isLoad = true;
        
       isLoad = false;
    }
    
    public String[] setAction(int i) {
        String[] m = new String[2];
        if (i > 0) {
            m = new String[]{BlueSeerUtils.SuccessBit, BlueSeerUtils.getRecordSuccess};  
        } else {
           m = new String[]{BlueSeerUtils.ErrorBit, BlueSeerUtils.getRecordError};  
        }
        return m;
    }
    
    public boolean validateInput(String x) { 
        boolean b = true;
                                
                if (tbbank.getText().isEmpty() || ! OVData.isValidBank(tbbank.getText())) {
                    b = false;
                    bsmf.MainFrame.show("must enter a valid bank");
                    tbbank.requestFocus();
                    return b;
                }
                if (tblbracct.getText().isEmpty() || ! OVData.isValidGLAcct(tblbracct.getText())) {
                    b = false;
                    bsmf.MainFrame.show("must enter a valid Labor acct");
                    tblbracct.requestFocus();
                    return b;
                }
                if (tbsalacct.getText().isEmpty() || ! OVData.isValidGLAcct(tbsalacct.getText())) {
                    b = false;
                    bsmf.MainFrame.show("must enter a valid Salaried acct");
                    tbsalacct.requestFocus();
                    return b;
                }
                if (tbtaxacct.getText().isEmpty() || ! OVData.isValidGLAcct(tbtaxacct.getText())) {
                    b = false;
                    bsmf.MainFrame.show("must enter a valid Tax acct");
                    tbtaxacct.requestFocus();
                    return b;
                }
                if (tbwithholdacct.getText().isEmpty() || ! OVData.isValidGLAcct(tbwithholdacct.getText())) {
                    b = false;
                    bsmf.MainFrame.show("must enter a valid Withholding acct");
                    tbwithholdacct.requestFocus();
                    return b;
                }
               
                
               
        return b;
    }
    
    public void initvars(String[] arg) {
            setComponentDefaultValues();
            executeTask("get", null);
    }
    
    public String[] updateRecord(String[] x) {
     String[] m = new String[2];
     
     try {
           
            Class.forName(bsmf.MainFrame.driver).newInstance();
            bsmf.MainFrame.con = DriverManager.getConnection(bsmf.MainFrame.url + bsmf.MainFrame.db, bsmf.MainFrame.user, bsmf.MainFrame.pass);
            Statement st = bsmf.MainFrame.con.createStatement();
            ResultSet res = null;
            try {
                
                    
                
                    int i = 0;
                    res = st.executeQuery("SELECT *  FROM  pay_ctrl ;");
                    while (res.next()) {
                        i++;
                    }
                     if (i == 0) {

                    st.executeUpdate("insert into pay_ctrl values (" + "'" + tbbank.getText() + "'" + ","
                            + "'" + tblbracct.getText() + "'" + "," 
                            + "'" + tblbrcc.getText() + "'" + ","
                            + "'" + tbsalacct.getText() + "'" + ","
                            + "'" + tbsalcc.getText() + "'" + ","
                            + "'" + tbtaxacct.getText() + "'" + ","
                            + "'" + tbtaxcc.getText() + "'" + ","
                            + "'" + tbwithholdacct.getText() + "'"
                            + ")" + ";");                 
                    m = new String[] {BlueSeerUtils.SuccessBit, BlueSeerUtils.addRecordSuccess};
                } else {
                    st.executeUpdate("update pay_ctrl set " 
                            + " payc_bank = " + "'" + tbbank.getText() + "'" + ","
                            + " payc_labor_acct = " + "'" + tblbracct.getText() + "'" + "," 
                            + " payc_labor_cc = " + "'" + tblbrcc.getText() + "'" + "," 
                            + " payc_salaried_acct = " + "'" + tbsalacct.getText() + "'" + ","
                            + " payc_salaried_cc = " + "'" + tbsalcc.getText() + "'" + ","
                            + " payc_withhold_acct = " + "'" + tbwithholdacct.getText() + "'" + ","
                            + " payc_payrolltax_acct = " + "'" + tbtaxacct.getText() + "'" + ","
                            + " payc_payrolltax_cc = " + "'" + tbtaxcc.getText() + "'" +         
                            ";");   
                    m = new String[] {BlueSeerUtils.SuccessBit, BlueSeerUtils.updateRecordSuccess};
                }
                    
                    
            } catch (SQLException s) {
                MainFrame.bslog(s);
                m = new String[]{BlueSeerUtils.ErrorBit, BlueSeerUtils.updateRecordSQLError};  
            } finally {
               if (res != null) res.close();
               if (st != null) st.close();
               if (bsmf.MainFrame.con != null) bsmf.MainFrame.con.close();
            }
        } catch (Exception e) {
            MainFrame.bslog(e);
            m = new String[]{BlueSeerUtils.ErrorBit, BlueSeerUtils.updateRecordConnError};
        }
     
     return m;
     }
      
    public String[] getRecord(String[] x) {
       String[] m = new String[2];
       
        try {

            Class.forName(bsmf.MainFrame.driver).newInstance();
            bsmf.MainFrame.con = DriverManager.getConnection(bsmf.MainFrame.url + bsmf.MainFrame.db, bsmf.MainFrame.user, bsmf.MainFrame.pass);
            Statement st = bsmf.MainFrame.con.createStatement();
            ResultSet res = null;
            try {
                
                int i = 0;
                res = st.executeQuery("select * from pay_ctrl;");
                while (res.next()) {
                    i++;
                    tbbank.setText(res.getString("payc_bank"));
                    tblbracct.setText(res.getString("payc_labor_acct"));
                    tblbrcc.setText(res.getString("payc_labor_cc"));
                    tbsalacct.setText(res.getString("payc_salaried_acct"));
                    tbsalcc.setText(res.getString("payc_salaried_cc"));
                    tbtaxacct.setText(res.getString("payc_payrolltax_acct"));
                    tbtaxcc.setText(res.getString("payc_payrolltax_cc"));
                    tbwithholdacct.setText(res.getString("payc_withhold_acct"));
                }
               
                // set Action if Record found (i > 0)
                m = setAction(i);
                
            } catch (SQLException s) {
                MainFrame.bslog(s);
                m = new String[]{BlueSeerUtils.ErrorBit, BlueSeerUtils.getRecordSQLError};  
            } finally {
               if (res != null) res.close();
               if (st != null) st.close();
               if (bsmf.MainFrame.con != null) bsmf.MainFrame.con.close();
            }
        } catch (Exception e) {
            MainFrame.bslog(e);
            m = new String[]{BlueSeerUtils.ErrorBit, BlueSeerUtils.getRecordConnError};  
        }
      return m;
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
        tblbrcc = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        tbbank = new javax.swing.JTextField();
        tblbracct = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btupdate = new javax.swing.JButton();
        tbsalacct = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        tbsalcc = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        tbtaxacct = new javax.swing.JTextField();
        tbtaxcc = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        tbwithholdacct = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(0, 102, 204));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("PayRoll Control Maintenance"));

        jLabel2.setText("Labor Acct");

        jLabel1.setText("Default Bank");

        jLabel3.setText("Labor CC");

        btupdate.setText("Update");
        btupdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btupdateActionPerformed(evt);
            }
        });

        jLabel4.setText("Salaried Acct");

        jLabel5.setText("Salaried CC");

        jLabel6.setText("Taxes Acct");

        jLabel7.setText("Taxes CC");

        jLabel8.setText("Default Withholding Acct");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tbwithholdacct)
                    .addComponent(tbtaxcc, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btupdate)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tblbracct, javax.swing.GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)
                            .addComponent(tbsalacct, javax.swing.GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)
                            .addComponent(tbsalcc, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tblbrcc, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tbbank, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tbtaxacct, javax.swing.GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tbbank, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tblbracct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tblbrcc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tbsalacct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tbsalcc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(9, 9, 9)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tbtaxacct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tbtaxcc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tbwithholdacct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btupdate)
                .addContainerGap(155, Short.MAX_VALUE))
        );

        add(jPanel1);
    }// </editor-fold>//GEN-END:initComponents

    private void btupdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btupdateActionPerformed
        if (! validateInput("updateRecord")) {
           return;
       }
        executeTask("update", null);
    }//GEN-LAST:event_btupdateActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btupdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField tbbank;
    private javax.swing.JTextField tblbracct;
    private javax.swing.JTextField tblbrcc;
    private javax.swing.JTextField tbsalacct;
    private javax.swing.JTextField tbsalcc;
    private javax.swing.JTextField tbtaxacct;
    private javax.swing.JTextField tbtaxcc;
    private javax.swing.JTextField tbwithholdacct;
    // End of variables declaration//GEN-END:variables
}
