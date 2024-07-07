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

import furkanservetkaya.dal.MusteriDAL;
import furkanservetkaya.interfaces.FeInterfaces;
import furkanservetkaya.types.MusteriContract;

public class musteriDuzenleFE extends JDialog implements FeInterfaces{

	public musteriDuzenleFE() {
		initPencere();
	}

	@Override
	public void initPencere() {
		JPanel panel = initPanel();
	    add(panel);
	    setTitle("Müşteri Düzenle");
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
		ustPanel.setBorder(BorderFactory.createTitledBorder("Müşteri Düzenle"));
		JLabel MusteriAdiLabel = new JLabel("Müşteri Arama:",JLabel.RIGHT);
		ustPanel.add(MusteriAdiLabel);
		JTextField MusteriAdiField = new JTextField(10);
		ustPanel.add(MusteriAdiField);
		JLabel MusteriGuncelleLabel = new JLabel("Müşteri Güncelle:",JLabel.RIGHT);
		ustPanel.add(MusteriGuncelleLabel);
		JTextField MusteriGuncelleField = new JTextField(10);
		ustPanel.add(MusteriGuncelleField);
		
		
		JList MusteriList = new JList();
		MusteriList.setListData(new MusteriDAL().GetAll().toArray());
		JScrollPane pane = new JScrollPane(MusteriList);
		pane.setBorder(BorderFactory.createTitledBorder("Müşteri Listesi"));
		MusteriList.setSelectedIndex(0);
		MusteriAdiField.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				
				MusteriList.setListData(new MusteriDAL().GetSearchMusteri(MusteriAdiField.getText()).toArray());
				MusteriList.setSelectedIndex(0);
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		JButton MusteriSilButton = new JButton("Sil");
		ustPanel.add(MusteriSilButton);
		MusteriSilButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MusteriContract selecteddMusteri = (MusteriContract) MusteriList.getSelectedValue();
	                if (selecteddMusteri != null) {
	                    new MusteriDAL().Delete(selecteddMusteri);
	                    MusteriList.setListData(new MusteriDAL().GetAll().toArray(new MusteriContract[0]));
	                    JOptionPane.showMessageDialog(null, selecteddMusteri.getAdiSoyadi() + " adlı Müşteri başarıyla silinmiştir.");
	                }
				
			}
		}); 
		JButton MusteriGuncelleButton = new JButton("Güncelle");
		ustPanel.add(MusteriGuncelleButton);
		MusteriGuncelleButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MusteriContract selectedMusteri = (MusteriContract) MusteriList.getSelectedValue();
                if (selectedMusteri != null) {
                	selectedMusteri.setAdiSoyadi(MusteriAdiField.getText());
                    new MusteriDAL().Update(selectedMusteri);
                    MusteriList.setListData(new MusteriDAL().GetAll().toArray(new MusteriContract[0]));
                    JOptionPane.showMessageDialog(null,"Müşteriniz " + selectedMusteri.getAdiSoyadi() + " Adı ile Güncellenmiştir");
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
