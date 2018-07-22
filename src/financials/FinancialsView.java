/*
 * FinancialsView.java
 */

package financials;

import business.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import javax.swing.Icon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.application.Action;
import org.jdesktop.application.FrameView;
import org.jdesktop.application.ResourceMap;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.TaskMonitor;

/**
 * The application's main frame.
 */
public class FinancialsView extends FrameView {
    //globals for form
    Annuity an;
    Loan ln;
    public FinancialsView(SingleFrameApplication app) {
        super(app);

        initComponents();

        // status bar initialization - message timeout, idle icon and busy animation, etc
        ResourceMap resourceMap = getResourceMap();
        int messageTimeout = resourceMap.getInteger("StatusBar.messageTimeout");
        messageTimer = new Timer(messageTimeout, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                statusMessageLabel.setText("");
            }
        });
        messageTimer.setRepeats(false);
        int busyAnimationRate = resourceMap.getInteger("StatusBar.busyAnimationRate");
        for (int i = 0; i < busyIcons.length; i++) {
            busyIcons[i] = resourceMap.getIcon("StatusBar.busyIcons[" + i + "]");
        }
        busyIconTimer = new Timer(busyAnimationRate, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                busyIconIndex = (busyIconIndex + 1) % busyIcons.length;
                statusAnimationLabel.setIcon(busyIcons[busyIconIndex]);
            }
        });
        idleIcon = resourceMap.getIcon("StatusBar.idleIcon");
        statusAnimationLabel.setIcon(idleIcon);
        progressBar.setVisible(false);

        // connecting action tasks to status bar via TaskMonitor
        TaskMonitor taskMonitor = new TaskMonitor(getApplication().getContext());
        taskMonitor.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                String propertyName = evt.getPropertyName();
                if ("started".equals(propertyName)) {
                    if (!busyIconTimer.isRunning()) {
                        statusAnimationLabel.setIcon(busyIcons[0]);
                        busyIconIndex = 0;
                        busyIconTimer.start();
                    }
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(true);
                } else if ("done".equals(propertyName)) {
                    busyIconTimer.stop();
                    statusAnimationLabel.setIcon(idleIcon);
                    progressBar.setVisible(false);
                    progressBar.setValue(0);
                } else if ("message".equals(propertyName)) {
                    String text = (String)(evt.getNewValue());
                    statusMessageLabel.setText((text == null) ? "" : text);
                    messageTimer.restart();
                } else if ("progress".equals(propertyName)) {
                    int value = (Integer)(evt.getNewValue());
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(false);
                    progressBar.setValue(value);
                }
            }
        });
    }

    @Action
    public void showAboutBox() {
        if (aboutBox == null) {
            JFrame mainFrame = FinancialsApp.getApplication().getMainFrame();
            aboutBox = new FinancialsAboutBox(mainFrame);
            aboutBox.setLocationRelativeTo(mainFrame);
        }
        FinancialsApp.getApplication().show(aboutBox);
    }

    /** This method is called from within the constructor to
     * initialize the form.  
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jradAnnuity = new javax.swing.JRadioButton();
        jradLoan = new javax.swing.JRadioButton();
        jLabel2 = new javax.swing.JLabel();
        jtxtAmount = new javax.swing.JTextField();
        jtxtRate = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jtxtTerm = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jtxtResult = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jBtnCalc = new javax.swing.JButton();
        jbtnSched = new javax.swing.JButton();
        jBtnClear = new javax.swing.JButton();
        menuBar = new javax.swing.JMenuBar();
        javax.swing.JMenu fileMenu = new javax.swing.JMenu();
        javax.swing.JMenuItem exitMenuItem = new javax.swing.JMenuItem();
        javax.swing.JMenu helpMenu = new javax.swing.JMenu();
        javax.swing.JMenuItem aboutMenuItem = new javax.swing.JMenuItem();
        statusPanel = new javax.swing.JPanel();
        javax.swing.JSeparator statusPanelSeparator = new javax.swing.JSeparator();
        statusMessageLabel = new javax.swing.JLabel();
        statusAnimationLabel = new javax.swing.JLabel();
        progressBar = new javax.swing.JProgressBar();
        buttonGroup1 = new javax.swing.ButtonGroup();

        mainPanel.setName("mainPanel"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(financials.FinancialsApp.class).getContext().getResourceMap(FinancialsView.class);
        jLabel1.setFont(resourceMap.getFont("jLabel1.font")); // NOI18N
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        buttonGroup1.add(jradAnnuity);
        jradAnnuity.setText(resourceMap.getString("jradAnnuity.text")); // NOI18N
        jradAnnuity.setName("jradAnnuity"); // NOI18N
        jradAnnuity.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jradAnnuityItemStateChanged(evt);
            }
        });

        buttonGroup1.add(jradLoan);
        jradLoan.setText(resourceMap.getString("jradLoan.text")); // NOI18N
        jradLoan.setName("jradLoan"); // NOI18N
        jradLoan.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jradLoanItemStateChanged(evt);
            }
        });

        jLabel2.setFont(resourceMap.getFont("jLabel2.font")); // NOI18N
        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        jtxtAmount.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jtxtAmount.setText(resourceMap.getString("jtxtAmount.text")); // NOI18N
        jtxtAmount.setName("jtxtAmount"); // NOI18N

        jtxtRate.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jtxtRate.setName("jtxtRate"); // NOI18N

        jLabel3.setFont(resourceMap.getFont("jLabel3.font")); // NOI18N
        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        jtxtTerm.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jtxtTerm.setName("jtxtTerm"); // NOI18N

        jLabel4.setFont(resourceMap.getFont("jLabel4.font")); // NOI18N
        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N

        jtxtResult.setEditable(false);
        jtxtResult.setBackground(resourceMap.getColor("jtxtResult.background")); // NOI18N
        jtxtResult.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jtxtResult.setName("jtxtResult"); // NOI18N

        jLabel5.setFont(resourceMap.getFont("jLabel5.font")); // NOI18N
        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N

        jBtnCalc.setText(resourceMap.getString("jBtnCalc.text")); // NOI18N
        jBtnCalc.setName("jBtnCalc"); // NOI18N
        jBtnCalc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnCalcActionPerformed(evt);
            }
        });

        jbtnSched.setText(resourceMap.getString("jbtnSched.text")); // NOI18N
        jbtnSched.setEnabled(false);
        jbtnSched.setName("jbtnSched"); // NOI18N
        jbtnSched.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnSchedActionPerformed(evt);
            }
        });

        jBtnClear.setText(resourceMap.getString("jBtnClear.text")); // NOI18N
        jBtnClear.setName("jBtnClear"); // NOI18N
        jBtnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnClearActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jLabel1)
                        .addGap(56, 56, 56)
                        .addComponent(jradAnnuity)
                        .addGap(54, 54, 54)
                        .addComponent(jradLoan))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addGap(154, 154, 154)
                                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jtxtAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jtxtRate, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jtxtTerm, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(mainPanelLayout.createSequentialGroup()
                                        .addGap(30, 30, 30)
                                        .addComponent(jBtnCalc))
                                    .addComponent(jtxtResult, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2))
                                .addGap(193, 193, 193))
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addGap(200, 200, 200)
                                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(mainPanelLayout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(jBtnClear))
                                    .addComponent(jbtnSched))))))
                .addContainerGap(228, Short.MAX_VALUE))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jradAnnuity)
                        .addComponent(jradLoan))
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jtxtAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jtxtRate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jtxtTerm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtnCalc)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jtxtResult, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jbtnSched)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtnClear)
                .addContainerGap(227, Short.MAX_VALUE))
        );

        menuBar.setName("menuBar"); // NOI18N

        fileMenu.setText(resourceMap.getString("fileMenu.text")); // NOI18N
        fileMenu.setName("fileMenu"); // NOI18N

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(financials.FinancialsApp.class).getContext().getActionMap(FinancialsView.class, this);
        exitMenuItem.setAction(actionMap.get("quit")); // NOI18N
        exitMenuItem.setName("exitMenuItem"); // NOI18N
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        helpMenu.setText(resourceMap.getString("helpMenu.text")); // NOI18N
        helpMenu.setName("helpMenu"); // NOI18N

        aboutMenuItem.setAction(actionMap.get("showAboutBox")); // NOI18N
        aboutMenuItem.setName("aboutMenuItem"); // NOI18N
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        statusPanel.setName("statusPanel"); // NOI18N

        statusPanelSeparator.setName("statusPanelSeparator"); // NOI18N

        statusMessageLabel.setName("statusMessageLabel"); // NOI18N

        statusAnimationLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        statusAnimationLabel.setName("statusAnimationLabel"); // NOI18N

        progressBar.setName("progressBar"); // NOI18N

        javax.swing.GroupLayout statusPanelLayout = new javax.swing.GroupLayout(statusPanel);
        statusPanel.setLayout(statusPanelLayout);
        statusPanelLayout.setHorizontalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(statusPanelSeparator, javax.swing.GroupLayout.DEFAULT_SIZE, 644, Short.MAX_VALUE)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(statusMessageLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 480, Short.MAX_VALUE)
                .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(statusAnimationLabel)
                .addContainerGap())
        );
        statusPanelLayout.setVerticalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addComponent(statusPanelSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(statusMessageLabel)
                    .addComponent(statusAnimationLabel)
                    .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3))
        );

        setComponent(mainPanel);
        setMenuBar(menuBar);
        setStatusBar(statusPanel);
    }// </editor-fold>//GEN-END:initComponents

    private void jradAnnuityItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jradAnnuityItemStateChanged
        if(jradAnnuity.isSelected()){
            //values should stay in Class."text" should not be here.
            jLabel2.setText(Annuity.AMTDESC);
            jLabel5.setText(Annuity.RESULTDESC);
                    
        }
    }//GEN-LAST:event_jradAnnuityItemStateChanged

    private void jradLoanItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jradLoanItemStateChanged
        if(jradLoan.isSelected()){
            jLabel2.setText(Loan.AMTDESC);
            jLabel5.setText(Loan.RESULTDESC);
        }
    }//GEN-LAST:event_jradLoanItemStateChanged

    private void jBtnCalcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnCalcActionPerformed
        double a,r;
        int t;
        NumberFormat curr = NumberFormat.getCurrencyInstance();
        statusMessageLabel.setText("");
        try{
            a = Double.parseDouble(jtxtAmount.getText());
        }catch(NumberFormatException e){
            statusMessageLabel.setText("Amount error: "+ e.getMessage());
            jtxtAmount.requestFocusInWindow();
            return;
        }
        try{
            r = Double.parseDouble(jtxtRate.getText());
        }catch(NumberFormatException e){
            statusMessageLabel.setText("Rate error: " + e.getMessage());
            jtxtRate.requestFocusInWindow();
            return;
        }
        try{
            t = Integer.parseInt(jtxtTerm.getText());
         
        }catch(NumberFormatException e ){
            statusMessageLabel.setText("Term error: " + e.getMessage());
            jtxtTerm.requestFocusInWindow();
            return;
        }
        if(jradAnnuity.isSelected()){
            //send values to Annuity.java Class via instantiation
            an = new Annuity(a,r,t);
            if(!an.getErrorMsg().isEmpty()){
                statusMessageLabel.setText(an.getErrorMsg());
            }else{
                jtxtResult.setText(
                        curr.format(an.getResult()));
                jbtnSched.setEnabled(true);
            }
        }else if(jradLoan.isSelected()){
           //loan
            ln = new Loan(a,r,t);  
            if(!ln.getErrorMsg().isEmpty()){
                statusMessageLabel.setText(ln.getErrorMsg());
            }else{
                jtxtResult.setText(
                curr.format(ln.getResult()));
                jbtnSched.setEnabled(true);
            }
        }else{
            statusMessageLabel.setText("No financial operation selected.");
        }
        
    }//GEN-LAST:event_jBtnCalcActionPerformed

    private void jBtnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnClearActionPerformed
        statusMessageLabel.setText("");
        jtxtAmount.setText("");
        jtxtRate.setText("");
        jtxtTerm.setText("");
        jtxtResult.setText("");
        buttonGroup1.clearSelection();
        jLabel2.setText("Amount:");
        jLabel5.setText("Result:");
        jbtnSched.setEnabled(false);
        
                
           

    }//GEN-LAST:event_jBtnClearActionPerformed

    private void jbtnSchedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnSchedActionPerformed
        statusMessageLabel.setText("");
        NumberFormat curr = NumberFormat.getCurrencyInstance();
        NumberFormat pct = NumberFormat.getPercentInstance();
        pct.setMaximumFractionDigits(3);
        pct.setMinimumFractionDigits(3);
        
        JTable sched = null;
        DefaultTableModel mod;
        
        String[]cols;
        //row column
        String[][] t;//t = upper left
        String title;
        
        if(jradAnnuity.isSelected()){
            //build annuity popup
            title = "Annuity Schedule";
            cols = new String[] {"Month","Beg Bal", "Deposit","Rate","Int.Earned","End.Bal"};
            t = new String[an.getTerm()][6];
            mod = new DefaultTableModel(t,cols);
            sched = new JTable(mod);
            for(int i = 0 ; i< an.getTerm(); i++){
                //fill all columns for row = i
                sched.setValueAt(i+1 , i, 0);
                sched.setValueAt(curr.format(an.getBegBal(i+1)), i, 1);
                sched.setValueAt( curr.format(an.getAmt()),i,2);
                sched.setValueAt(pct.format(an.getRate()), i, 3);
                sched.setValueAt(curr.format(an.getIntEarn(i+1)),i,4);
                sched.setValueAt(curr.format(an.getEndBal(i+1)),i,5);
                //cell renderer?
            }
            
        }else if(jradLoan.isSelected()){
            //build loan popup
            title = "Loan Schedule";
            cols = new String[]  { "Month","Beg Bal","Payment",
            "Int.Chg","End Bal"};
            t = new String[ln.getTerm()][5];
            mod = new DefaultTableModel(t,cols);
            sched = new JTable(mod);
            //fill all cells of sched using get from loan.java
            //for loop to fill cells[
                    
        }else{
            statusMessageLabel.setText("No Financial Operation Selected");
            return;
        }
                
       //display table sched
       JScrollPane sp = new JScrollPane(sched);
       JDialog dg = new JDialog();
       dg.add(sp);
       dg.setTitle(title);
       dg.setBounds(150,400,600,300);
       dg.setVisible(true);
               
    }//GEN-LAST:event_jbtnSchedActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jBtnCalc;
    private javax.swing.JButton jBtnClear;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JButton jbtnSched;
    private javax.swing.JRadioButton jradAnnuity;
    private javax.swing.JRadioButton jradLoan;
    private javax.swing.JTextField jtxtAmount;
    private javax.swing.JTextField jtxtRate;
    private javax.swing.JTextField jtxtResult;
    private javax.swing.JTextField jtxtTerm;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JLabel statusAnimationLabel;
    private javax.swing.JLabel statusMessageLabel;
    private javax.swing.JPanel statusPanel;
    // End of variables declaration//GEN-END:variables

    private final Timer messageTimer;
    private final Timer busyIconTimer;
    private final Icon idleIcon;
    private final Icon[] busyIcons = new Icon[15];
    private int busyIconIndex = 0;

    private JDialog aboutBox;
}
