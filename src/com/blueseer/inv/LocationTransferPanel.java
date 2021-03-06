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
package com.blueseer.inv;

import bsmf.MainFrame;
import com.blueseer.utl.OVData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 *
 * @author vaughnte
 */
public class LocationTransferPanel extends javax.swing.JPanel {

    
    javax.swing.table.DefaultTableModel mymodel = new javax.swing.table.DefaultTableModel(new Object[][]{},
                    new String[]{"Site", "WH", "Loc", "Qty"});
    
    /**
     * Creates new form LocationTransferPanel
     */
    public LocationTransferPanel() {
        initComponents();
    }

    
     public void getlocqty(String parentpart) {
        try {
            Class.forName(bsmf.MainFrame.driver).newInstance();
            bsmf.MainFrame.con = DriverManager.getConnection(bsmf.MainFrame.url + bsmf.MainFrame.db, bsmf.MainFrame.user, bsmf.MainFrame.pass);
            try {
                Statement st = bsmf.MainFrame.con.createStatement();
                ResultSet res = null;

                int i = 0;

                
                mymodel.setRowCount(0);
                tablelocqty.setModel(mymodel);
                            
                // ReportPanel.TableReport.getColumn("CallID").setCellRenderer(new ButtonRenderer());
                //          ReportPanel.TableReport.getColumn("CallID").setCellEditor(
                    //       new ButtonEditor(new JCheckBox()));

               res = st.executeQuery("SELECT in_site, in_loc, in_wh, in_qoh, in_date  " +
                        " FROM  in_mstr  " +
                        " where in_part = " + "'" + parentpart.toString() + "'" + 
                        " order by in_loc ;");

                while (res.next()) {
                    i++;
                    mymodel.addRow(new Object[]{
                                res.getString("in_site"),
                                res.getString("in_wh"),
                                res.getString("in_loc"),
                                res.getInt("in_qoh")
                            });
              
                }
                
            } catch (SQLException s) {
                 MainFrame.bslog(s);
                 bsmf.MainFrame.show("unable to select in_mstr info");
            }
            bsmf.MainFrame.con.close();
        } catch (Exception e) {
            MainFrame.bslog(e);
        }
             
             
         }
    
    public void initvars(String[] arg) {
        
        //tbpart.requestFocus();
        mymodel.setRowCount(0);
        tablelocqty.setModel(mymodel);
        
        java.util.Date now = new java.util.Date();
       DateFormat dfdate = new SimpleDateFormat("yyyy-MM-dd");
        dcdate.setDate(now);
       
        tbpart.setText("");
        tbqty.setText("");
        tbrmks.setText("");
        
        
        
         ArrayList<String> sites = new ArrayList();
        ddsitefrom.removeAllItems();
        ddsiteto.removeAllItems();
        sites = OVData.getSiteList();
        for (String code : sites) {
            ddsitefrom.addItem(code);
            ddsiteto.addItem(code);
        }
        
         ArrayList<String> wh = new ArrayList();
        ddwhfrom.removeAllItems();
        ddwhto.removeAllItems();
        wh = OVData.getWareHouseList(); 
        for (String code : wh) {
            ddwhfrom.addItem(code);
            ddwhto.addItem(code);
        }
        
        ArrayList<String> mylist = new ArrayList();
        ddlocfrom.removeAllItems();
        ddlocto.removeAllItems();
        if (ddwhfrom.getSelectedItem() != null) {
            mylist = OVData.getLocationListByWarehouse(ddwhfrom.getSelectedItem().toString());
            for (String code : mylist) {
                ddlocfrom.addItem(code);
                ddlocto.addItem(code);
            }
        }
        
        
        
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        ddlocfrom = new javax.swing.JComboBox();
        ddsitefrom = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        ddwhfrom = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        ddlocto = new javax.swing.JComboBox();
        ddsiteto = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        ddwhto = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        tbpart = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        tblot = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        tbqty = new javax.swing.JTextField();
        dcdate = new com.toedter.calendar.JDateChooser();
        tbrmks = new javax.swing.JTextField();
        bttransfer = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablelocqty = new javax.swing.JTable();

        setBackground(new java.awt.Color(0, 102, 204));

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Warehouse/Location Transfer"));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("From:"));

        jLabel6.setText("Site:");

        jLabel7.setText("Location:");

        ddwhfrom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ddwhfromActionPerformed(evt);
            }
        });

        jLabel10.setText("Warehouse:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(ddsitefrom, 0, 118, Short.MAX_VALUE)
                    .addComponent(ddlocfrom, 0, 118, Short.MAX_VALUE)
                    .addComponent(ddwhfrom, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ddsitefrom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ddwhfrom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ddlocfrom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("To:"));

        jLabel8.setText("Site:");

        jLabel9.setText("Location:");

        ddwhto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ddwhtoActionPerformed(evt);
            }
        });

        jLabel11.setText("Warehouse:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel9)
                    .addComponent(jLabel8)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ddwhto, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(ddlocto, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ddsiteto, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ddsiteto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ddwhto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ddlocto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        tbpart.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tbpartFocusLost(evt);
            }
        });

        jLabel2.setText("Qty:");

        jLabel3.setText("EffectiveDate:");

        jLabel1.setText("Part:");

        jLabel5.setText("Lot/SerialNo:");

        jLabel4.setText("Remarks:");

        dcdate.setDateFormatString("yyyy-MM-dd");

        bttransfer.setText("Transfer");
        bttransfer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttransferActionPerformed(evt);
            }
        });

        tablelocqty.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3"
            }
        ));
        jScrollPane1.setViewportView(tablelocqty);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tbqty, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tbrmks, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dcdate, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tblot, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tbpart, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(0, 13, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(bttransfer, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tbpart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tbqty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dcdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tbrmks, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tblot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bttransfer)
                .addContainerGap())
        );

        add(jPanel4);
    }// </editor-fold>//GEN-END:initComponents

    private void bttransferActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttransferActionPerformed
        boolean proceed = true;
        boolean rtn;
        DateFormat dfdate = new SimpleDateFormat("yyyy-MM-dd");
        Double qty = 0.00;
        String op = "";
        
        String sitefrom = "";
        String siteto = "";
        String whfrom = "";
        String whto = "";
        String locfrom = "";
        String locto = "";
        
        if (ddlocfrom.getSelectedItem() != null)
        locfrom = ddlocfrom.getSelectedItem().toString();
        
        if (ddlocto.getSelectedItem() != null)
        locto = ddlocto.getSelectedItem().toString();
        
        if (ddwhfrom.getSelectedItem() != null)
        whfrom = ddwhfrom.getSelectedItem().toString();
        
        if (ddwhto.getSelectedItem() != null)
        whto = ddwhto.getSelectedItem().toString();
        
        if (ddsitefrom.getSelectedItem() != null)
        sitefrom = ddsitefrom.getSelectedItem().toString();
        
        if (ddsiteto.getSelectedItem() != null)
        siteto = ddsiteto.getSelectedItem().toString();
        
        
        qty = Double.valueOf(tbqty.getText());
        
        if (qty == 0) {
            proceed = false;
            bsmf.MainFrame.show("Qty cannot be zero");
            tbqty.requestFocus();
            return;
        }
        
        if (! OVData.isValidItem(tbpart.getText())) {
            proceed = false;
            bsmf.MainFrame.show("Part Number invalid");
            tbpart.requestFocus();
            return;
        }
        
        if (qty > OVData.getItemQtyByWarehouseAndLocation(tbpart.getText(), sitefrom, whfrom, locfrom) ) {
            proceed = false;
            bsmf.MainFrame.show("Insufficient Qty at WH/LOC to transfer");
            tbqty.requestFocus();
            return;
        }
        
        if (proceed) {    
            //Date effdate, String part, int qty, String type, double price, double cost, String site, 
            //  String loc, String cust, String nbr, String order, int line, String po, String terms, String lot, String rmks, 
            //  String ref, String acct, String cc, String jobnbr, String serial, String program, String userid

            // do the transaction
            OVData.TRHistIssDiscrete(dcdate.getDate(), 
                  tbpart.getText(), 
                  qty.intValue(),
                      op,
                  "LOC-TRNF", 
                  0.00, 
                  0.00, 
                siteto, 
                locto,  
                whto,
                "", 
                "", 
                "", 
                0, 
                "", 
                "", 
                tblot.getText(), // lot 
                tbrmks.getText(), //rmks
                tblot.getText(), //ref
                "", 
                "", 
                "", 
                tblot.getText(),  // serial
                "LocactionTransferMaint", // program
                bsmf.MainFrame.userid
                );
            
        // do the 'to' side
       rtn = OVData.UpdateInventoryDiscrete(tbpart.getText(), siteto, ddlocto.getSelectedItem().toString(), whto, qty);
      
       // now do the 'from' side
       if (! rtn) {
       qty = -1 * qty;
       rtn = OVData.UpdateInventoryDiscrete(tbpart.getText(), sitefrom, ddlocfrom.getSelectedItem().toString(), whfrom, qty);
       }
       
      
       if (! rtn)
           bsmf.MainFrame.show("Transfer Complete");
       else
           bsmf.MainFrame.show("Problem with Transfer Occurred");
       
       initvars(null);
       
       } // proceed
       
        
    }//GEN-LAST:event_bttransferActionPerformed

    private void tbpartFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tbpartFocusLost
        if (! OVData.isValidItem(tbpart.getText())) {
            bsmf.MainFrame.show("invalid item");
            tbpart.requestFocus();
            return;
        } else {
        getlocqty(tbpart.getText());
        }
    }//GEN-LAST:event_tbpartFocusLost

    private void ddwhfromActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ddwhfromActionPerformed
        if (ddwhfrom.getSelectedItem() != null) {
             ddlocfrom.removeAllItems();
             ArrayList<String> loc = OVData.getLocationListByWarehouse(ddwhfrom.getSelectedItem().toString());
             for (String lc : loc) {
                ddlocfrom.addItem(lc);
             }
        }
    }//GEN-LAST:event_ddwhfromActionPerformed

    private void ddwhtoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ddwhtoActionPerformed
        if (ddwhto.getSelectedItem() != null) {
             ddlocto.removeAllItems();
             ArrayList<String> loc = OVData.getLocationListByWarehouse(ddwhto.getSelectedItem().toString());
             for (String lc : loc) {
                ddlocto.addItem(lc);
             }
        }
    }//GEN-LAST:event_ddwhtoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bttransfer;
    private com.toedter.calendar.JDateChooser dcdate;
    private javax.swing.JComboBox ddlocfrom;
    private javax.swing.JComboBox ddlocto;
    private javax.swing.JComboBox ddsitefrom;
    private javax.swing.JComboBox ddsiteto;
    private javax.swing.JComboBox<String> ddwhfrom;
    private javax.swing.JComboBox<String> ddwhto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablelocqty;
    private javax.swing.JTextField tblot;
    private javax.swing.JTextField tbpart;
    private javax.swing.JTextField tbqty;
    private javax.swing.JTextField tbrmks;
    // End of variables declaration//GEN-END:variables
}
