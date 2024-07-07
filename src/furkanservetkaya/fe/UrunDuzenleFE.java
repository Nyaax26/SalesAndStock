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
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import furkanservetkaya.dal.UrunlerDAL;
import furkanservetkaya.interfaces.FeInterfaces;
import furkanservetkaya.types.UrunlerContract;

public class UrunDuzenleFE extends JDialog implements FeInterfaces{

	public UrunDuzenleFE() {
		initPencere();
	}

	@Override
	public void initPencere() {
		
		JPanel panel = initPanel();
	    add(panel);
	    setTitle("Kategori Düzenle");
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
		ustPanel.setBorder(BorderFactory.createTitledBorder("Ürünü Düzenle"));
		JLabel UrunAdiLabel = new JLabel("Ürün Arama:",JLabel.RIGHT);
		ustPanel.add(UrunAdiLabel);
		JTextField UrunAdiField = new JTextField(10);
		ustPanel.add(UrunAdiField);
		JLabel UrunGuncelleLabel = new JLabel("Ürünü Güncelle:",JLabel.RIGHT);
		ustPanel.add(UrunGuncelleLabel);
		JTextField UrunGuncelleField = new JTextField(10);
		ustPanel.add(UrunGuncelleField);
		
		
		JList UrunList = new JList();
		UrunList.setListData(new UrunlerDAL().GetAll().toArray());
		JScrollPane pane = new JScrollPane(UrunList);
		pane.setBorder(BorderFactory.createTitledBorder("Ürün Listesi"));
		UrunList.setSelectedIndex(0);
		UrunAdiField.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {

				UrunList.setListData(new UrunlerDAL().GetSearchUrunler(UrunAdiField.getText()).toArray());
				UrunList.setSelectedIndex(0);
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		JButton UrunSilButton = new JButton("Sil");
		ustPanel.add(UrunSilButton);
		UrunSilButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				UrunlerContract selecteddUrun = (UrunlerContract) UrunList.getSelectedValue();
	                if (selecteddUrun != null) {
	                    new UrunlerDAL().Delete(selecteddUrun);
	                    UrunList.setListData(new UrunlerDAL().GetAll().toArray(new UrunlerContract[0]));
	                    JOptionPane.showMessageDialog(null, selecteddUrun.getAdi() + " adlı Ürün başarıyla silinmiştir.");
	                }
				
			}
		}); 
		JButton UrunGuncelleButton = new JButton("Güncelle");
		ustPanel.add(UrunGuncelleButton);
		UrunGuncelleButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				UrunlerContract selectedUrun = (UrunlerContract) UrunList.getSelectedValue();
                if (selectedUrun != null) {
                	selectedUrun.setAdi(UrunGuncelleField.getText());
                    new UrunlerDAL().Update(selectedUrun);
                    UrunList.setListData(new UrunlerDAL().GetAll().toArray(new UrunlerContract[0]));
                    JOptionPane.showMessageDialog(null,"Ürününüz " + selectedUrun.getAdi() + " Adı ile Güncellenmiştir");
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
