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
package com.blueseer.adm;

import bsmf.MainFrame;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import oshi.SystemInfo;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.hardware.NetworkIF;
import oshi.software.os.OperatingSystem;

/**
 *
 * @author vaughnte
 */
public class AboutPanel extends javax.swing.JPanel {

    
    String currenttext = "";
    /**
     * Creates new form AboutPanel
     */
    public AboutPanel() {
        initComponents();
         currenttext = jTextArea2.getText();
    }

    
    public void getSysInfo() {
                 /* Total number of processors or cores available to the JVM */
          jTextArea2.setText("");
          
       SystemInfo si = new SystemInfo(); 
       
      
       
       HardwareAbstractionLayer hal = si.getHardware();
       long availableMemory = hal.getMemory().getAvailable();
       jTextArea2.append("Available memory (bytes): " + 
       (availableMemory == Long.MAX_VALUE ? "no limit" : availableMemory) + "\n"); 
       
       long totalMemory = hal.getMemory().getTotal();
       jTextArea2.append("Total memory (bytes): " + 
       (totalMemory == Long.MAX_VALUE ? "no limit" : totalMemory) + "\n"); 
       
       jTextArea2.append("Processor: " + si.getHardware().getProcessor().getName() + "\n"); 
       jTextArea2.append("Processor Vendor: " + si.getHardware().getProcessor().getVendor() + "\n"); 
       jTextArea2.append("Processor Model: " + si.getHardware().getProcessor().getModel() + "\n"); 
       jTextArea2.append("Processor Logical Count: " + si.getHardware().getProcessor().getLogicalProcessorCount() + "\n"); 
       jTextArea2.append("Processor Physical Count: " + si.getHardware().getProcessor().getPhysicalProcessorCount() + "\n"); 
       
       OperatingSystem os = si.getOperatingSystem();
       jTextArea2.append("Operating System: " + os + "\n");
       jTextArea2.append("OS Family: " + os.getFamily() + "\n");
       jTextArea2.append("OS Manufacturer: " + os.getManufacturer() + "\n");
       jTextArea2.append("OS Version: " + os.getVersion().getVersion() + "\n");
       jTextArea2.append("OS Bit: " + os.getBitness() + "\n");
       jTextArea2.append("OS Build: " + os.getVersion().getBuildNumber() + "\n");
       jTextArea2.append("OS Codename: " + os.getVersion().getCodeName() + "\n");
       jTextArea2.append("OS FileSystem: " + os.getFileSystem() + "\n");
       
       
        NetworkIF[] s = si.getHardware().getNetworkIFs();
       for (NetworkIF x : s) {
        jTextArea2.append("Network: " + x.getName() + " / " + String.join(", ", x.getIPv4addr()) + "\n");
       }
       
       jTextArea2.append("Java Version: " + System.getProperty("java.version") + "\n");   
       jTextArea2.append("Java VM: " + System.getProperty("java.vm.name") + "\n");
       jTextArea2.append("Java VM Version: " + System.getProperty("java.vm.version") + "\n");
       jTextArea2.append("Java Runtime Name: " + System.getProperty("java.runtime.name") + "\n");   
       jTextArea2.append("Java Runtime Version: " + System.getProperty("java.runtime.version") + "\n");  
       jTextArea2.append("Java Class Version: " + System.getProperty("java.class.version") + "\n");   
       jTextArea2.append("Java Compiler: " + System.getProperty("sun.management.compiler") + "\n");   
       
       
       // now get patch info...created from git command :  git rev-parse HEAD
       BufferedReader fsr;
        try {
            fsr = new BufferedReader(new FileReader(new File(".patch")));
            String line = "";
            while ((line = fsr.readLine()) != null) {
               jTextArea2.append("Patch: " + line);
            }
            fsr.close();
        } catch (FileNotFoundException ex) {
            MainFrame.bslog(ex);
        } catch (IOException ex) {
            MainFrame.bslog(ex);
        }
           
    }
    
    
    public void initvars(String[] arg) {
        
        if (arg != null && arg.length > 0 && arg[0].equals("SysInfo")) {
            getSysInfo();
        } else {
            jTextArea2.setText(currenttext);
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

        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();

        setLayout(new java.awt.BorderLayout());

        jTextArea2.setEditable(false);
        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jTextArea2.setText("The MIT License (MIT)\n\nCopyright (c) Terry Evans Vaughn \"VCSCode\"\n\nAll rights reserved.\n\nPermission is hereby granted, free of charge, to any person obtaining a copy\nof this software and associated documentation files (the \"Software\"), to deal\nin the Software without restriction, including without limitation the rights\nto use, copy, modify, merge, publish, distribute, sublicense, and/or sell\ncopies of the Software, and to permit persons to whom the Software is\nfurnished to do so, subject to the following conditions:\n\nThe above copyright notice and this permission notice shall be included in all\ncopies or substantial portions of the Software.\n\nTHE SOFTWARE IS PROVIDED \"AS IS\", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR\nIMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,\nFITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE\nAUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER\nLIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,\nOUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE\nSOFTWARE.");
        jScrollPane2.setViewportView(jTextArea2);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 989, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 844, Short.MAX_VALUE)
        );

        add(jPanel1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea2;
    // End of variables declaration//GEN-END:variables
}
