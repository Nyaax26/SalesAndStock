package furkanservetkaya.fe;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import furkanservetkaya.dal.MusteriDAL;
import furkanservetkaya.dal.PersonelDAL;
import furkanservetkaya.dal.SehirlerDAL;
import furkanservetkaya.interfaces.FeInterfaces;
import furkanservetkaya.types.MusteriContract;
import furkanservetkaya.types.SehirlerContract;

public class MusteriEkleFE extends JDialog implements FeInterfaces {

	public MusteriEkleFE() {
		initPencere();
	}

	@Override
public void initPencere() {
		
		JPanel panel = initPanel();
		panel.setBorder(BorderFactory.createTitledBorder("Müşteri Ekle"));
		
		add(panel);
		setTitle("Müşteri Ekle");
		pack();
		setLocationRelativeTo(null);
		setModalityType(DEFAULT_MODALITY_TYPE);
		setVisible(true);
		setDefaultCloseOperation(HIDE_ON_CLOSE);	
		
							}

	@Override
	public JPanel initPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		JPanel fieldpanel = new JPanel(new GridLayout(5, 2));
		JPanel buttonPanel = new JPanel(new GridLayout(1, 2));

		JLabel adiSoyadiLabel = new JLabel("Adı Soyadı:",JLabel.RIGHT);
		fieldpanel.add(adiSoyadiLabel);
		JTextField adiSoyadiField = new JTextField(15);
		fieldpanel.add(adiSoyadiField);	
		
		JLabel telefonLabel = new JLabel("Telefon:",JLabel.RIGHT);
		fieldpanel.add(telefonLabel);
		JTextField telefonField = new JTextField(15);
		fieldpanel.add(telefonField);
		
		JLabel sehirSeclabel = new JLabel("Şehir Seç:", JLabel.RIGHT);
	    fieldpanel.add(sehirSeclabel);
	    JComboBox<Object> personelBox = new JComboBox<>();
        personelBox.addItem("Personel Seçiniz");
        for (Object item : new PersonelDAL().GetAll()) {
            personelBox.addItem(item);
        }
        panel.add(personelBox);
	    JComboBox<SehirlerContract> sehirlerBox = new JComboBox<>();
	    List<SehirlerContract> sehirlerList = new SehirlerDAL().GetAll();
	    for (SehirlerContract sehir : sehirlerList) {
	        sehirlerBox.addItem(sehir);
	    }
	    fieldpanel.add(sehirlerBox);
	    
		JLabel adreslabel = new JLabel("");
		fieldpanel.add(adreslabel);
		
		
		JTextArea adresArea = new JTextArea(4,1);
		JScrollPane pane = new JScrollPane(adresArea);
		pane.setBorder(BorderFactory.createTitledBorder("Adres Bilgisi"));
		
		JButton kaydetButton = new JButton("Kaydet");
		buttonPanel.add(kaydetButton);
		JButton iptalButton = new JButton("İptal");
		buttonPanel.add(iptalButton);
		
		panel.add(fieldpanel,BorderLayout.NORTH);
		panel.add(pane,BorderLayout.CENTER);
		panel.add(buttonPanel,BorderLayout.SOUTH);
		
		kaydetButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
			    MusteriContract contract = new MusteriContract();
			    String adiSoyadi = adiSoyadiField.getText();
			    String telefon = telefonField.getText();
			    String adres = adresArea.getText();

			    // SehirlerContract türündeki öğeyi alma
			    SehirlerContract selectedSehir = null;
			    Object selectedItem = sehirlerBox.getSelectedItem();
			    if (selectedItem instanceof SehirlerContract) {
			        selectedSehir = (SehirlerContract) selectedItem;
			    }

			    // SehirlerComboBox'ında seçili bir öğe varsa
			    if (selectedSehir != null) {
			        contract.setSehirId(selectedSehir.getId());
			    }

			    contract.setAdiSoyadi(adiSoyadi);
			    contract.setTelefon(telefon);
			    contract.setAdres(adres);

			    new MusteriDAL().Insert(contract);
			    JOptionPane.showMessageDialog(null, adiSoyadi + " adlı müşteri başarıyla eklenmiştir!");
			    
			    adiSoyadiField.setText("");
				telefonField.setText("");
				sehirlerBox.setSelectedIndex(0);
				adresArea.setText("");
			}
			});
		
		// VEYA
/*
 * kaydetButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				MusteriContract contract = new MusteriContract();
				//SehirlerContract castContract = (SehirlerContract) sehirlerBox.getSelectedItem();
				
				contract.setAdiSoyadi(adiSoyadiField.getText());
				contract.setTelefon(telefonField.getText());
				contract.setAdres(adreslabel.getText());
				
				new MusteriDAL().Insert(contract);
				JOptionPane.showMessageDialog(null, contract.getAdiSoyadi()+" adlı personel eklenmiştir.");	
				
				if (sehirlerBox.getSelectedIndex() != 0) {
					SehirlerContract castContract = (SehirlerContract) sehirlerBox.getSelectedItem();
					contract.setAdiSoyadi(adiSoyadiField.getText());
					contract.setTelefon(telefonField.getText());
					contract.setSehirId(sehirlerBox.getSelectedIndex());
					contract.setAdres(adreslabel.getText());

					new MusteriDAL().Insert(contract);
					JOptionPane.showMessageDialog(null,
							contract.getAdiSoyadi() + " Adlı Müşteri Başarılı bir şekilde kayıt edilmiştir!!");
				} else {
					contract.setAdiSoyadi(adiSoyadiField.getText());
					contract.setSehirId(sehirlerBox.getSelectedIndex());

					new MusteriDAL().Insert(contract);
					JOptionPane.showMessageDialog(null,
							contract.getAdiSoyadi() + " Adlı Kategori Başarılı bir şekilde kayıt edilmiştir!!");
					
				}
			}
		});
 *  2 TÜRDEDE YAPIMI
 */

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
