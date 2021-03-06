import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;

/**
 *
 * @author swong
 */
public class BlackJackGUI extends JFrame {
	
	private HumanInteraction blackjack;
	
	public BlackJackGUI() {
		new BlackJackGUI(blackjack);
	}
	
    public BlackJackGUI(HumanInteraction blackjack) {
    	this.blackjack = blackjack;
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        hitButton = new JButton();
        standButton = new JButton();
        doubleDownButton = new JButton();
        logoLabel = new JLabel();
        dealerLabel = new JLabel();
        chipsLabel = new JLabel();
        chips = new JLabel();
        playerLabel = new JLabel();
        betAmountLabel = new JLabel();
        betAmount = new JLabel();
        redealButton = new JButton();
        dealerCards = new JLabel[5];
	        for(int i=0;i<dealerCards.length;i++) {
	        	dealerCards[i] = new JLabel();
	        	dealerCards[i].setBorder(BorderFactory.createLineBorder(Color.black));
	        }
        playerCards = new JLabel[5];
	        for(int i=0;i<playerCards.length;i++) {
	        	playerCards[i] = new JLabel();
	        	playerCards[i].setBorder(BorderFactory.createLineBorder(Color.black));
	        }
        
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        hitButton.setText("Hit");
        hitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hitButtonActionPerformed(evt);
            }
        });

        standButton.setText("Stand");
        standButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                standButtonActionPerformed(evt);
            }
        });

        doubleDownButton.setText("DoubleDown");
        doubleDownButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	doubleDownButtonActionPerformed(evt);
            }
        });
        
        logoLabel.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        logoLabel.setForeground(new java.awt.Color(51, 51, 255));
        logoLabel.setText("BlackJack");

        dealerLabel.setText("Dealer Hand");
      	playerLabel.setText("Player Hand");

        chipsLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        chipsLabel.setText("Chips:");

        chips.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        chips.setHorizontalAlignment(SwingConstants.TRAILING);
        chips.setText("0");

        betAmountLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        betAmountLabel.setText("Bet Amount:");

        betAmount.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        betAmount.setHorizontalAlignment(SwingConstants.TRAILING);
        betAmount.setText("-");
        
        redealButton.setText("Re-deal");
        redealButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	redealButtonActionPerformed(evt);
            }
        });
        
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        
        layout.setHorizontalGroup(
        	layout.createSequentialGroup()
        	.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
        		.addComponent(logoLabel)
        		.addGroup(layout.createSequentialGroup()
        			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
        				.addComponent(betAmountLabel)
        				.addComponent(chipsLabel))
        			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
        				.addComponent(betAmount,javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
        				.addComponent(chips,javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
        		.addComponent(redealButton))
        	.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
        		.addComponent(dealerLabel)
        		.addGroup(layout.createSequentialGroup()
        			.addComponent(dealerCards[0],javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
        			.addComponent(dealerCards[1],javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
        			.addComponent(dealerCards[2],javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
        			.addComponent(dealerCards[3],javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
        			.addComponent(dealerCards[4],javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
	        	.addGroup(layout.createSequentialGroup()
	        			.addGap(50,50,50)
	        			.addComponent(hitButton)
	        			.addComponent(doubleDownButton)
	        			.addComponent(standButton))
	        	.addComponent(playerLabel)
	        	.addGroup(layout.createSequentialGroup()
	        			.addComponent(playerCards[0],javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
	        			.addComponent(playerCards[1],javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
	        			.addComponent(playerCards[2],javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
	        			.addComponent(playerCards[3],javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
	        			.addComponent(playerCards[4],javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
        	.addContainerGap(133, Short.MAX_VALUE)
        );
        
        layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] {doubleDownButton, hitButton, standButton});
        
        layout.setVerticalGroup(
        	layout.createParallelGroup(GroupLayout.Alignment.LEADING)
        	.addGroup(layout.createSequentialGroup()
	    		.addComponent(logoLabel)
	    		.addComponent(dealerLabel)
	    		.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
					.addComponent(dealerCards[0],javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
        			.addComponent(dealerCards[1],javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
        			.addComponent(dealerCards[2],javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
        			.addComponent(dealerCards[3],javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
        			.addComponent(dealerCards[4],javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
        		.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
					.addComponent(betAmountLabel)
					.addComponent(betAmount)
					.addComponent(hitButton)
        			.addComponent(doubleDownButton)
        			.addComponent(standButton))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
					.addComponent(chipsLabel)
					.addComponent(chips))
				.addGap(20)
				.addComponent(playerLabel)
	    		.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
					.addComponent(playerCards[0],javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
        			.addComponent(playerCards[1],javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
        			.addComponent(playerCards[2],javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
        			.addComponent(playerCards[3],javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
        			.addComponent(playerCards[4],javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
        		.addComponent(redealButton)
	    		.addContainerGap(133, Short.MAX_VALUE))
        );
        
        layout.linkSize(SwingConstants.VERTICAL, new java.awt.Component[] {doubleDownButton, hitButton, standButton});
        
        pack();
    }// </editor-fold>//GEN-END:initComponents

	public JLabel getChips() {
		return chips;
	}

	public JLabel getPlayerLabel() {
		return playerLabel;
	}
	
    public JLabel[] getDealerCards() {
		return dealerCards;
	}

	public JLabel[] getPlayerCards() {
		return playerCards;
	}
	
    public JLabel getBetAmount() {
		return betAmount;
	}
    
    private void doubleDownButtonActionPerformed(ActionEvent evt) {
    		blackjack.performDoubleDown();
	}

	private void hitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hitButtonActionPerformed
			blackjack.performHit();
    }//GEN-LAST:event_hitButtonActionPerformed

    private void standButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_standButtonActionPerformed
    		blackjack.performStand();
    }//GEN-LAST:event_standButtonActionPerformed

    private void redealButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_redealButtonActionPerformed
    	blackjack.redeal();
    }//GEN-LAST:event_redealButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(BlackJackGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BlackJackGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BlackJackGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BlackJackGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new BlackJackGUI().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JButton doubleDownButton;
    private JButton hitButton;
    private JButton redealButton;
    private JLabel dealerLabel;
    private JLabel logoLabel;
    private JLabel chipsLabel;
    private JLabel chips;
    private JLabel playerLabel;
    private JLabel betAmountLabel;
    private JLabel betAmount;
    private JLabel[] dealerCards;
	private JLabel[] playerCards;
	private JButton standButton;
    // End of variables declaration//GEN-END:variables
}
