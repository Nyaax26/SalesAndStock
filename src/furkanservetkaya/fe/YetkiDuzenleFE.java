package furkanservetkaya.fe;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import furkanservetkaya.dal.YetkilerDAL;
import furkanservetkaya.interfaces.FeInterfaces;
import furkanservetkaya.types.YetkilerContract;


public class YetkiDuzenleFE extends JDialog implements FeInterfaces{

	public YetkiDuzenleFE() {
		initPencere();
	}

	@Override
	public void initPencere() {
		JPanel panel = initPanel();
	    add(panel);
	    setTitle("Yetki Düzenle");
	    pack();
	    setLocationRelativeTo(null);
	    setModalityType(ModalityType.APPLICATION_MODAL);
	    setDefaultCloseOperation(DISPOSE_ON_CLOSE); // Ana proje kapanmasın, sadece dialog kapanacak
	    setVisible(true);
	}

	@Override
	public JPanel initPanel() {
		
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(BorderFactory.createTitledBorder("Düzenleme İşlemleri"));
		JPanel ustPanel = new JPanel(new GridLayout(4,2));
		ustPanel.setBorder(BorderFactory.createTitledBorder("Yetki Düzenle"));
		JLabel YetkiAdiLabel = new JLabel("Yetki Arama:",JLabel.RIGHT);
		ustPanel.add(YetkiAdiLabel);
		JTextField YetkiAdiField = new JTextField(10);
		ustPanel.add(YetkiAdiField);
		JLabel YetkiGuncelleLabel = new JLabel("Yetkiyi Güncelle:",JLabel.RIGHT);
		ustPanel.add(YetkiGuncelleLabel);
		JTextField YetkiGuncelleField = new JTextField(10);
		ustPanel.add(YetkiGuncelleField);
		
		
		JList YetkiList = new JList();
		YetkiList.setListData(new YetkilerDAL().GetAll().toArray());
		JScrollPane pane = new JScrollPane(YetkiList);
		pane.setBorder(BorderFactory.createTitledBorder("Yetki Listesi"));
		YetkiList.setSelectedIndex(0);
		YetkiAdiField.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				
				YetkiList.setListData(new YetkilerDAL().GetSearchYetkiler(YetkiAdiField.getText()).toArray());
				YetkiList.setSelectedIndex(0);
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		JButton YetkiSilButton = new JButton("Sil");
		ustPanel.add(YetkiSilButton);
		YetkiSilButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				YetkilerContract selecteddYetki = (YetkilerContract) YetkiList.getSelectedValue();
	                if (selecteddYetki != null) {
	                    new YetkilerDAL().Delete(selecteddYetki);
	                    YetkiList.setListData(new YetkilerDAL().GetAll().toArray(new YetkilerContract[0]));
	                    JOptionPane.showMessageDialog(null, selecteddYetki.getAdi() + " adlı Yetki başarıyla silinmiştir.");
	                }
				
			}
		}); 
		JButton YetkiGuncelleButton = new JButton("Güncelle");
		ustPanel.add(YetkiGuncelleButton);
		YetkiGuncelleButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				YetkilerContract selectedYetki = (YetkilerContract) YetkiList.getSelectedValue();
                if (selectedYetki != null) {
                	selectedYetki.setAdi(YetkiAdiField.getText());
                    new YetkilerDAL().Update(selectedYetki);
                    YetkiList.setListData(new YetkilerDAL().GetAll().toArray(new YetkilerContract[0]));
                    JOptionPane.showMessageDialog(null,"Yetki " + selectedYetki.getAdi() + " Adı ile Güncellenmiştir");
                }
			}
		});		
		panel.add(ustPanel,BorderLayout.NORTH);
		panel.add(pane,BorderLayout.CENTER);
		return panel;
	}

	@Override
	public JMenuBar initBar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JTabbedPane initTabs() {
		// TODO Auto-generated method stub
		return null;
	}

}
