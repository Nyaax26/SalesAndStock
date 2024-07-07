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

import furkanservetkaya.dal.KategoriDAL;
import furkanservetkaya.interfaces.FeInterfaces;
import furkanservetkaya.types.KategoriContract;

public class KategoriDuzenleFE extends JDialog implements FeInterfaces{

	public KategoriDuzenleFE() {
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
		ustPanel.setBorder(BorderFactory.createTitledBorder("Kategori Düzenle"));
		JLabel kategorliAdiLabel = new JLabel("Kategori Arama:",JLabel.RIGHT);
		ustPanel.add(kategorliAdiLabel);
		JTextField kategoriAdiField = new JTextField(10);
		ustPanel.add(kategoriAdiField);
		JLabel kategoriGuncelleLabel = new JLabel("Kateogiri Güncelle:",JLabel.RIGHT);
		ustPanel.add(kategoriGuncelleLabel);
		JTextField kategoriGuncelleField = new JTextField(10);
		ustPanel.add(kategoriGuncelleField);
		//JLabel ustKategoriLabel = new JLabel("Üst Kategorisi:",JLabel.RIGHT);
		//ustPanel.add(ustKategoriLabel);
		//JComboBox ustKategoriBox = new JComboBox(new KategoriDAL().GetAll().toArray());
		//ustPanel.add(ustKategoriBox);
		
		JList kategoriList = new JList();
		kategoriList.setListData(new KategoriDAL().GetAll().toArray());
		JScrollPane pane = new JScrollPane(kategoriList);
		pane.setBorder(BorderFactory.createTitledBorder("Kategori Listesi"));
		kategoriList.setSelectedIndex(0);
		kategoriAdiField.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				
				kategoriList.setListData(new KategoriDAL().GetSearchKategori(kategoriAdiField.getText()).toArray());
				kategoriList.setSelectedIndex(0);
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		JButton KategoriSilButton = new JButton("Sil");
		ustPanel.add(KategoriSilButton);
		KategoriSilButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				KategoriContract selectedKategori = (KategoriContract) kategoriList.getSelectedValue();
	                if (selectedKategori != null) {
	                    new KategoriDAL().Delete(selectedKategori);
	                    kategoriList.setListData(new KategoriDAL().GetAll().toArray(new KategoriContract[0]));
	                    JOptionPane.showMessageDialog(null, selectedKategori.getAdi() + " adlı kategori başarıyla silinmiştir.");
	                }
				
			}
		}); 
		JButton KategoriGuncelleButton = new JButton("Güncelle");
		ustPanel.add(KategoriGuncelleButton);
		KategoriGuncelleButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				KategoriContract selectedKategori = (KategoriContract) kategoriList.getSelectedValue();
                if (selectedKategori != null) {
                	selectedKategori.setAdi(kategoriGuncelleField.getText());
                    new KategoriDAL().Update(selectedKategori);
                    kategoriList.setListData(new KategoriDAL().GetAll().toArray(new KategoriContract[0]));
                    JOptionPane.showMessageDialog(null,"Kategoriniz " + selectedKategori.getAdi() + " Adı ile Güncellenmiştir");
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
