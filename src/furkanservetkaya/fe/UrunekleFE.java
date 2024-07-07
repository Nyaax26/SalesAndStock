package furkanservetkaya.fe;

import java.awt.GridLayout;import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import furkanservetkaya.dal.KategoriDAL;
import furkanservetkaya.dal.UrunlerDAL;
import furkanservetkaya.interfaces.FeInterfaces;
import furkanservetkaya.types.KategoriContract;
import furkanservetkaya.types.UrunlerContract;

public class UrunekleFE extends JDialog implements FeInterfaces {

	public UrunekleFE() {
		initPencere();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initPencere() {

		JPanel panel = initPanel();

		panel.setBorder(BorderFactory.createTitledBorder("Ürün Kayıt Alanı"));
		add(panel);
		setTitle("Ürün Ekleyin");
		pack();
		setModalityType(DEFAULT_MODALITY_TYPE);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(HIDE_ON_CLOSE);

	}

	@Override
	public JPanel initPanel() {
		JPanel panel = new JPanel(new GridLayout(5, 2));
		JLabel adiLabel = new JLabel("Adı:", JLabel.RIGHT);
		panel.add(adiLabel);
		JTextField adiField = new JTextField(10);
		panel.add(adiField);
		JLabel kategoriLabel = new JLabel("Kategori Seçiniz:", JLabel.RIGHT);
		panel.add(kategoriLabel);
		JComboBox kategoriBox = new JComboBox(new KategoriDAL().GetAll().toArray());
        kategoriBox.insertItemAt("Kategori Seçiniz", 0);
        kategoriBox.setSelectedIndex(0);
        panel.add(kategoriBox);
		JLabel tarihLabel = new JLabel("Tarih Seçiniz::", JLabel.RIGHT);
		panel.add(tarihLabel);
		JDateChooser tarihDate = new JDateChooser();
		panel.add(tarihDate);
		JLabel fiyatLabel = new JLabel("Fiyat Giriniz", JLabel.RIGHT);
		panel.add(fiyatLabel);
		JTextField fiyatField = new JTextField(10);
		panel.add(fiyatField);

		JButton kaydetButton = new JButton("Kaydet");
		panel.add(kaydetButton);
		JButton iptalButton = new JButton("İptal");
		panel.add(iptalButton);
		kaydetButton.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        // KategoriBox'tan seçilen öğeyi alın
		        Object selectedObject = kategoriBox.getSelectedItem();
		        
		        // Seçilen öğe KategoriContract türünde mi kontrol edin
		        if (selectedObject instanceof KategoriContract) {
		            KategoriContract castContract = (KategoriContract) selectedObject;
		            
		            // Tarih formatlama işlemi
		            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		            String date = format.format(tarihDate.getDate());
		            
		            // UrunlerContract nesnesi oluşturma ve bilgileri set etme
		            UrunlerContract contract = new UrunlerContract();
		            contract.setAdi(adiField.getText());
		            contract.setKategoriId(castContract.getId());
		            contract.setTarih(date);
		            
		            // Fiyatı alırken hata yönetimi
		            try {
		                float fiyat = Float.parseFloat(fiyatField.getText());
		                contract.setFiyat(fiyat);
		                
		                // Veritabanına ekleme işlemi
		                new UrunlerDAL().Insert(contract);
		                
		                // Başarılı mesajı gösterme
		                JOptionPane.showMessageDialog(null, contract.getAdi() + " adlı ürün başarılı bir şekilde eklenmiştir.");
		                
		                // Alanları temizleme
		                adiField.setText("");
		                fiyatField.setText("");
		                kategoriBox.setSelectedIndex(0);
		                tarihDate.setDate(null);
		            } catch (NumberFormatException ex) {
		                JOptionPane.showMessageDialog(null, "Geçersiz fiyat formatı. Lütfen geçerli bir fiyat girin.");
		            }
		            
		        } else {
		            // Eğer bir KategoriContract seçilmediyse uygun bir mesaj gösterme
		            JOptionPane.showMessageDialog(null, "Lütfen bir kategori seçiniz.");
		        }
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
