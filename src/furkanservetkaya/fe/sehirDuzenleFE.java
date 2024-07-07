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

import furkanservetkaya.dal.SehirlerDAL;
import furkanservetkaya.interfaces.FeInterfaces;
import furkanservetkaya.types.SehirlerContract;


public class sehirDuzenleFE extends JDialog implements FeInterfaces{

	public sehirDuzenleFE() {
		initPencere();
	}

	@Override
	public void initPencere() {
		JPanel panel = initPanel();
	    add(panel);
	    setTitle("Şehri Düzenle");
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
		ustPanel.setBorder(BorderFactory.createTitledBorder("Şehri Düzenle"));
		JLabel SehirAdiLabel = new JLabel("Şehir Arama:",JLabel.RIGHT);
		ustPanel.add(SehirAdiLabel);
		JTextField SehirAdiField = new JTextField(10);
		ustPanel.add(SehirAdiField);
		JLabel SehirGuncelleLabel = new JLabel("Şehri Güncelle:",JLabel.RIGHT);
		ustPanel.add(SehirGuncelleLabel);
		JTextField SehirGuncelleField = new JTextField(10);
		ustPanel.add(SehirGuncelleField);
		
		
		JList SehirList = new JList();
		SehirList.setListData(new SehirlerDAL().GetAll().toArray());
		JScrollPane pane = new JScrollPane(SehirList);
		pane.setBorder(BorderFactory.createTitledBorder("Şehir Listesi"));
		SehirList.setSelectedIndex(0);
		SehirGuncelleField.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				
				SehirList.setListData(new SehirlerDAL().GetSearchSehirler(SehirAdiField.getText()).toArray());
				SehirList.setSelectedIndex(0);
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		JButton SehirSilButton = new JButton("Sil");
		ustPanel.add(SehirSilButton);
		SehirSilButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				SehirlerContract selectedSehir = (SehirlerContract) SehirList.getSelectedValue();
	                if (selectedSehir != null) {
	                    new SehirlerDAL().Delete(selectedSehir);
	                    SehirList.setListData(new SehirlerDAL().GetAll().toArray(new SehirlerContract[0]));
	                    JOptionPane.showMessageDialog(null, selectedSehir.getSehirId() + " adlı şehir başarıyla silinmiştir.");
	                }
				
			}
		}); 
		JButton SehirGuncelleButton = new JButton("Güncelle");
		ustPanel.add(SehirGuncelleButton);
		SehirGuncelleButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				SehirlerContract selecteddSehir = (SehirlerContract) SehirList.getSelectedValue();
                if (selecteddSehir != null) {
                	selecteddSehir.setSehirId(SehirGuncelleField.getText());
                    new SehirlerDAL().Update(selecteddSehir);
                    SehirList.setListData(new SehirlerDAL().GetAll().toArray(new SehirlerContract[0]));
                    JOptionPane.showMessageDialog(null, selecteddSehir.getSehirId() + " Adı ile Güncellenmiştir");
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
