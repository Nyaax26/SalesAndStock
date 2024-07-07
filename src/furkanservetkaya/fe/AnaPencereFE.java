package furkanservetkaya.fe;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.toedter.calendar.JDateChooser;

import furkanservetkaya.Utilities.MenulerCom;
import furkanservetkaya.complex.types.SatisContractComplex;
import furkanservetkaya.complex.types.StokContractComplex;
import furkanservetkaya.complex.types.StokContractTotalComplex;
import furkanservetkaya.dal.MusteriDAL;
import furkanservetkaya.dal.SatisDAL;
import furkanservetkaya.dal.StokDAL;
import furkanservetkaya.dal.UrunlerDAL;
import furkanservetkaya.interfaces.FeInterfaces;
import furkanservetkaya.test.LoginFE;
import furkanservetkaya.types.MusteriContract;
import furkanservetkaya.types.PersonelContract;
import furkanservetkaya.types.SatisContract;
import furkanservetkaya.types.StokContract;
import furkanservetkaya.types.UrunlerContract;

public class AnaPencereFE extends JFrame implements FeInterfaces{

	public AnaPencereFE() {
		initPencere();
	}

	@Override
	public void initPencere() {
		JPanel panel = initPanel();
		JMenuBar bar = initBar();
		
		add(panel);
		setJMenuBar(bar);
		setTitle("Satış ve Stok Programı");
		setSize(600,250);//pack();// oto size
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}

	@Override
	public JPanel initPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		
		JTabbedPane pane = initTabs();
		panel.add(pane,BorderLayout.CENTER);
		
		return panel;
	}

	@Override
	public JMenuBar initBar() {
		JMenuBar bar = MenulerCom.initBar();
		
		return bar;
	}

	@Override
	public JTabbedPane initTabs() {
		JTabbedPane pane = new JTabbedPane();
		ImageIcon Icon = new ImageIcon("icons/icon.png");
		ImageIcon Icon2 = new ImageIcon("icons/icon.png");
		
		
		JPanel StokPanel = new JPanel(new BorderLayout());
		JPanel SatisPanel = new JPanel(new BorderLayout());
		/*STOK İŞLEMLERİ*/
		JPanel StokSolPanel = new JPanel(new BorderLayout());
		JPanel StokSolUstPanel = new JPanel(new GridLayout(5,2));
		JPanel StokSolAltPanel = new JPanel();
		
		StokSolPanel.setBorder(BorderFactory.createTitledBorder("Stok İşlemleri"));
		Object [] stokKolonlar = {"Id","Personel Adı","Ürün Adı","Adeti","Tarihi","Toplam"};
		DefaultTableModel model = new DefaultTableModel(stokKolonlar,0);
		JTable table = new JTable(model);
		JScrollPane stokTablePane= new JScrollPane(table);
		
		for(StokContractComplex contract : new StokDAL().GetAllStok()) {
			model.addRow(contract.getVeriler());
		}
		JLabel StokUrunAdiLabel = new JLabel("Ürün Adı:",JLabel.RIGHT);
		StokSolUstPanel.add(StokUrunAdiLabel);
		JComboBox StokUrunAdiBox = new JComboBox(new UrunlerDAL().GetAll().toArray());
		StokSolUstPanel.add(StokUrunAdiBox);
		JLabel StokAdetLabel = new JLabel("Adet:",JLabel.RIGHT);
		StokSolUstPanel.add(StokAdetLabel);
		JTextField StokAdetField = new JTextField(10);
		StokSolUstPanel.add(StokAdetField);
		JLabel StokTarihLabel = new JLabel("Stok Tarihi:",JLabel.RIGHT);
		StokSolUstPanel.add(StokTarihLabel);
		JDateChooser stokTarihi = new JDateChooser();
		StokSolUstPanel.add(stokTarihi);
		
		JButton StokEkleButton = new JButton("Stok Ekle");
		StokSolUstPanel.add(StokEkleButton);
		JButton StokYenileButton = new JButton("Stok Yenile");
		StokSolUstPanel.add(StokYenileButton);
		JButton stokTotalButton = new JButton("Stok Toplam Ürün");
		StokSolUstPanel.add(stokTotalButton);
		stokTotalButton.addActionListener(new ActionListener() {
			
			@Override
			
			public void actionPerformed(ActionEvent e) {
			
				int satir = model.getRowCount();
				for (int i = 0; i < satir; i++) {
					model.removeRow(0);
				}
				
				for(StokContractTotalComplex total : new StokDAL().GetTotalStok()) {
					
					model.addRow(total.getVeriler());
				}
			}
		});
		StokYenileButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				int satir = model.getRowCount();
				for(int i =0; i < satir; i++) {
					model.removeRow(0);
				}
				//JOptionPane.showMessageDialog(null,"Güncelleniyor...");
				for(StokContractComplex compContract : new StokDAL().GetAllStok()) {
					model.addRow(compContract.getVeriler());
				}
				
			}
		});
		StokEkleButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				StokContract contract = new StokContract();
				UrunlerContract uContract = (UrunlerContract) StokUrunAdiBox.getSelectedItem();
				PersonelContract pContrct = (PersonelContract) LoginFE.emailBox.getSelectedItem();
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				
				String date = format.format(stokTarihi.getDate());
				
				contract.setPersonelId(pContrct.getId());
				contract.setUrunId(uContract.getId());
				contract.setTarih(date.toString());
				contract.setAdet(Integer.parseInt(StokAdetField.getText()));
				new StokDAL().Insert(contract);
				
				JOptionPane.showMessageDialog(null,uContract.getAdi()+ " Adlı Ürün "+ contract.getAdet() + " Adet Eklenmiştir.");
				
				int satir = model.getRowCount();
				for(int i =0; i < satir; i++) {
					model.removeRow(0);
				}
				//JOptionPane.showMessageDialog(null,"Güncelleniyor...");
				for(StokContractComplex complexContract : new StokDAL().GetAllStok()) {
					model.addRow(complexContract.getVeriler());
				}
			}
		});
		
		/*SATIŞ İŞLEMLERİ*/
		JPanel SatisSagPanel = new JPanel(new BorderLayout());
		SatisSagPanel.setBorder(BorderFactory.createTitledBorder("Satış İşlemleri"));
		JPanel SatisSagUstPanel = new JPanel(new GridLayout(6,2));
		JPanel SatisSagAltPanel = new JPanel();
		
		Object [] SatisKolonlar = {"Id","Müşteri Adı","Personel Adı","Ürün Adı","Adet","Tarih"};
		DefaultTableModel SatisModel = new DefaultTableModel(SatisKolonlar,0);
		JTable SatisTable = new JTable(SatisModel);
		JScrollPane SatisTablePane= new JScrollPane(SatisTable);
		
		JLabel musteriLabel = new JLabel("Müşteri Adı:",JLabel.RIGHT);
		SatisSagUstPanel.add(musteriLabel);
		JComboBox musteriAdiBox = new JComboBox(new MusteriDAL().GetAll().toArray());
		SatisSagUstPanel.add(musteriAdiBox);
		
		JLabel SatisUrunAdiLabel = new JLabel("Ürün Adı:",JLabel.RIGHT);
		SatisSagUstPanel.add(SatisUrunAdiLabel);
		JComboBox SatisUrunAdiBox = new JComboBox(new UrunlerDAL().GetAll().toArray());
		SatisSagUstPanel.add(SatisUrunAdiBox);
		JLabel SatisAdetLabel = new JLabel("Adet:",JLabel.RIGHT);
		SatisSagUstPanel.add(SatisAdetLabel);
		JTextField SatisAdetField = new JTextField(10);
		SatisSagUstPanel.add(SatisAdetField);
		JLabel SatisTarihLabel = new JLabel("Satış Tarihi:",JLabel.RIGHT);
		SatisSagUstPanel.add(SatisTarihLabel);
		JDateChooser SatisTarihi = new JDateChooser();
		SatisSagUstPanel.add(SatisTarihi);
		
		JButton SatisEkleButton = new JButton("Satış Yap");
		SatisSagUstPanel.add(SatisEkleButton);
		for(SatisContractComplex yenileContract : new SatisDAL().GetAllSatis()) {
			SatisModel.addRow(yenileContract.getVeriler());
		}
		SatisEkleButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				PersonelContract pContract =(PersonelContract) LoginFE.emailBox.getSelectedItem();
				UrunlerContract uContract = (UrunlerContract) SatisUrunAdiBox.getSelectedItem();
				MusteriContract mContract = (MusteriContract) musteriAdiBox.getSelectedItem();
				SatisContract contract= new SatisContract();
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				
				String date = format.format(SatisTarihi.getDate());
				contract.setMusteriId(mContract.getId());
				contract.setPersonelId(pContract.getId());
				contract.setUrunId(uContract.getId());
				contract.setAdet(Integer.parseInt(SatisAdetField.getText()));
				contract.setTarih(date);
				
				new SatisDAL().Insert(contract);
				StokContract stokContract = new StokContract();
				//StokContract.setMusteriId(mContract.getId());
				stokContract.setPersonelId(pContract.getId());
				stokContract.setUrunId(uContract.getId());
				stokContract.setAdet(-Integer.parseInt(SatisAdetField.getText()));
				stokContract.setTarih(date);
				
				new StokDAL().Insert(stokContract);
				JOptionPane.showMessageDialog(null, mContract.getAdiSoyadi() + " Adlı Müşteriye Satış Gerçekleştirilmiştir ve " + uContract.getAdi() 
				+ " Adlı Üründen Stokta " + contract.getAdet() + " Adet Eksiltilmiştir");
				int satir = SatisModel.getRowCount();
				for(int i =0; i < satir; i++) {
					SatisModel.removeRow(0);
				}
				//JOptionPane.showMessageDialog(null,"Güncelleniyor...");
				for(SatisContractComplex yenileContract : new SatisDAL().GetAllSatis()) {
					SatisModel.addRow(yenileContract.getVeriler());
				}
			}
		});
		
		JButton SatisYenileButton = new JButton("Yenile");
		SatisSagUstPanel.add(SatisYenileButton);
		SatisYenileButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int satir = SatisModel.getRowCount();
				for(int i =0; i < satir; i++) {
					SatisModel.removeRow(0);
				}
				//JOptionPane.showMessageDialog(null,"Güncelleniyor...");
				for(SatisContractComplex complexContract : new SatisDAL().GetAllSatis()) {
					SatisModel.addRow(complexContract.getVeriler());
				}
			}
		});
		
		
		StokPanel.add(StokSolPanel,BorderLayout.WEST);
		StokPanel.add(stokTablePane,BorderLayout.CENTER);
		
		SatisPanel.add(SatisSagPanel,BorderLayout.EAST);
		SatisPanel.add(SatisTablePane,BorderLayout.CENTER);
		
		SatisSagPanel.add(SatisSagUstPanel,BorderLayout.NORTH);
		SatisSagPanel.add(SatisSagAltPanel,BorderLayout.SOUTH);

		
		StokSolPanel.add(StokSolUstPanel,BorderLayout.NORTH);
		StokSolPanel.add(StokSolAltPanel,BorderLayout.SOUTH);
		pane.addTab("Stoklar ", Icon, StokPanel, "Doesn't Nothing");
		pane.addTab("Satışlar ", Icon2, SatisPanel, "yazı");
		return pane;
	}

}
