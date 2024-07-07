/**
 * 
 */
package furkanservetkaya.test;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;


/**
 * 
 */
public class Run {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			for(LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {       //GÖRÜNÜM TEMASI
				if("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		}catch(Exception e) {
			//IF Nimbus is not abailable you can set the GUI to another look and feel.
		}
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				
				new LoginFE();
			}
		});
			}
		}