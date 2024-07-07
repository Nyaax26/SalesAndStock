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

import furkanservetkaya.dal.PersonelDAL;
import furkanservetkaya.interfaces.FeInterfaces;
import furkanservetkaya.types.PersonelContract;

public class PersonelDuzenleFE extends JDialog implements FeInterfaces{

	public PersonelDuzenleFE() {
		initPencere();
	}

	@Override
	public void initPencere() {
		JPanel panel = initPanel();
	    add(panel);
	    setTitle("Personel Düzenle");
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
		ustPanel.setBorder(BorderFactory.createTitledBorder("Personel Düzenle"));
		JLabel PersonelAdiLabel = new JLabel("Personel Email Arama:",JLabel.RIGHT);
		ustPanel.add(PersonelAdiLabel);
		JTextField PersonelAdiField = new JTextField(10);
		ustPanel.add(PersonelAdiField);
		JLabel PersonelGuncelleLabel = new JLabel("Personel Email Güncelle:",JLabel.RIGHT);
		ustPanel.add(PersonelGuncelleLabel);
		JTextField PersonelGuncelleField = new JTextField(10);
		ustPanel.add(PersonelGuncelleField);
		
		
		JList PersonelList = new JList();
		PersonelList.setListData(new PersonelDAL().GetAll().toArray());
		JScrollPane pane = new JScrollPane(PersonelList);
		pane.setBorder(BorderFactory.createTitledBorder("Personel Email Listesi"));
		PersonelList.setSelectedIndex(0);
		PersonelAdiField.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				
				PersonelList.setListData(new PersonelDAL().GetSearchPersonel(PersonelAdiField.getText()).toArray());
				PersonelList.setSelectedIndex(0);
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		JButton PersonelSilButton = new JButton("Sil");
		ustPanel.add(PersonelSilButton);
		PersonelSilButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				PersonelContract selectedPersonel = (PersonelContract) PersonelList.getSelectedValue();
	                if (selectedPersonel != null) {
	                    new PersonelDAL().Delete(selectedPersonel);
	                    PersonelList.setListData(new PersonelDAL().GetAll().toArray(new PersonelContract[0]));
	                    JOptionPane.showMessageDialog(null, selectedPersonel.getEmail() + " adlı Email başarıyla silinmiştir.");
	                }
				
			}
		}); 
		JButton PersonelGuncelleButton = new JButton("Güncelle");
		ustPanel.add(PersonelGuncelleButton);
		PersonelGuncelleButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				PersonelContract selecteddPersonel = (PersonelContract) PersonelList.getSelectedValue();
                if (selecteddPersonel != null) {
                	selecteddPersonel.setEmail(PersonelAdiField.getText());
                    new PersonelDAL().Update(selecteddPersonel);
                    PersonelList.setListData(new PersonelDAL().GetAll().toArray(new PersonelContract[0]));
                    JOptionPane.showMessageDialog(null,"Personel Emailiniz " + selecteddPersonel.getEmail() + "  ile Güncellenmiştir");
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
