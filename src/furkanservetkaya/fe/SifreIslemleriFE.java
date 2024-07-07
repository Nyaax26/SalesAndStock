package furkanservetkaya.fe;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;

import furkanservetkaya.dal.AccountsDAL;
import furkanservetkaya.dal.PersonelDAL;
import furkanservetkaya.dal.YetkilerDAL;
import furkanservetkaya.interfaces.FeInterfaces;
import furkanservetkaya.types.AccountsContract;
import furkanservetkaya.types.PersonelContract;
import furkanservetkaya.types.YetkilerContract;

public class SifreIslemleriFE extends JDialog implements FeInterfaces {

	public SifreIslemleriFE() {
		initPencere();
	}

	@Override
	public void initPencere() {
		
		JPanel panel = initPanel();
		panel.setBorder(BorderFactory.createTitledBorder("Yetki Ekle"));
		add(panel);
		setTitle("Şifre Belirleme İşlemleri");
		pack();
		setModalityType(DEFAULT_MODALITY_TYPE);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		
	}

	@Override
	public JPanel initPanel() {
		JPanel panel = new JPanel(new GridLayout(5,2));
		JLabel personelLabel = new JLabel("Personel Seçiniz:",JLabel.RIGHT);
		panel.add(personelLabel);
		JComboBox<Object> personelBox = new JComboBox<>();
        personelBox.addItem("Personel Seçiniz");
        for (Object item : new PersonelDAL().GetAll()) {
            personelBox.addItem(item);
        }
        panel.add(personelBox);
		//JComboBox personelBox = new JComboBox(new PersonelDAL().GetAll().toArray());
		//panel.add(personelBox);
		JLabel yetkiLabel = new JLabel("Yetki Seçiniz",JLabel.RIGHT);
		panel.add(yetkiLabel);
        JComboBox<Object> yetkiBox = new JComboBox<>();
        yetkiBox.addItem("Yetki Seçiniz");
        for (Object item : new YetkilerDAL().GetAll()) {
            yetkiBox.addItem(item);
        }
        panel.add(yetkiBox);
		//JComboBox yetkiBox = new JComboBox(new YetkilerDAL().GetAll().toArray());
		//panel.add(yetkiBox);
		JLabel passwordlabel = new JLabel("Şifre Belirleyiniz:",JLabel.RIGHT);
		panel.add(passwordlabel);
		JPasswordField passField = new JPasswordField(10);
		panel.add(passField);
		JLabel passTekrarLabel = new JLabel("Şifreyi Tekrar Giriniz:",JLabel.RIGHT);
		panel.add(passTekrarLabel);
		JPasswordField passTekrar = new JPasswordField(10);
		panel.add(passTekrar);
		
		JButton kaydetButton = new JButton("Kaydet");
		panel.add(kaydetButton);
		JButton iptalButton = new JButton("İptal");
		panel.add(iptalButton);
		
		kaydetButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (personelBox.getSelectedIndex() == 0 || yetkiBox.getSelectedIndex() == 0) {
                    JOptionPane.showMessageDialog(null, "Lütfen geçerli bir personel ve yetki seçiniz.");
                    return;
				}
				AccountsContract contract = new AccountsContract();
				PersonelContract pContract = (PersonelContract)personelBox.getSelectedItem();
				YetkilerContract yContract = (YetkilerContract)yetkiBox.getSelectedItem();
				
				if(passField.getText().equals(passTekrar.getText()))
				{
					contract.setPersonelId(pContract.getId());
					contract.setYetkiId(yContract.getId());
					contract.setSifre(passField.getText());
					
					new AccountsDAL().Insert(contract);
					JOptionPane.showMessageDialog(null, pContract.getAdiSoyadi()+" Adlı kişiye "+yContract.getAdi()+" adlı yetki başarılı bir şekilde  eklenmiştir.");
					
					// Bileşenleri sıfırla
                    personelBox.setSelectedIndex(0);
                    yetkiBox.setSelectedIndex(0);
                    passField.setText("");
                    passTekrar.setText("");
					
				}else
				JOptionPane.showMessageDialog(null,"Şifre Uyuşmadı!! Lütfen Aynı şifreyi giriniz.");
			}
		});
		
		 iptalButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                dispose();
	            }
	        });
		 
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
