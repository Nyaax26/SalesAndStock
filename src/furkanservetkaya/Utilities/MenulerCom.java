package furkanservetkaya.Utilities;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.CloseAction;

import furkanservetkaya.dal.AccountsDAL;
import furkanservetkaya.fe.KategoriDuzenleFE;
import furkanservetkaya.fe.KategoriEkleFE;
import furkanservetkaya.fe.MusteriEkleFE;
import furkanservetkaya.fe.PersonelDuzenleFE;
import furkanservetkaya.fe.PersonelEkleFE;
import furkanservetkaya.fe.SehirEkleFE;
import furkanservetkaya.fe.SifreIslemleriFE;
import furkanservetkaya.fe.UrunDuzenleFE;
import furkanservetkaya.fe.UrunekleFE;
import furkanservetkaya.fe.YetkiDuzenleFE;
import furkanservetkaya.fe.YetkiEkleFE;
import furkanservetkaya.fe.musteriDuzenleFE;
import furkanservetkaya.fe.sehirDuzenleFE;
import furkanservetkaya.test.LoginFE;
import furkanservetkaya.types.PersonelContract;

public class MenulerCom {
	public static JMenuBar initBar() {

		JMenuBar bar = new JMenuBar();

		// Ürünler

		JMenu urunlerMenu = new JMenu("Ürünler");
		bar.add(urunlerMenu);

		JMenuItem urunEkleItem = new JMenuItem("Ürün Ekle");
		urunlerMenu.add(urunEkleItem);

		JMenuItem kategoriEkleItem = new JMenuItem("Kategori Ekle");
		urunlerMenu.add(kategoriEkleItem);
		urunlerMenu.addSeparator();

		JMenuItem urunDuzenleItem = new JMenuItem("Ürünü Düzenle");
		urunlerMenu.add(urunDuzenleItem);
		urunDuzenleItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
					
					@Override
					public void run() {
						new UrunDuzenleFE();
					}
				});
			}
		});		
		
		JMenuItem kategoriDuzenleItem = new JMenuItem("Kategoriyi Düzenle");
		urunlerMenu.add(kategoriDuzenleItem);
		kategoriDuzenleItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
					
					@Override
					public void run() {
						new KategoriDuzenleFE();
					}
				});
			}
		});
		
		// Personel Menüsü

		JMenu personellerMenu = new JMenu("Personel İşlemleri");
		bar.add(personellerMenu);

		JMenuItem personelEkleItem = new JMenuItem("Personel Ekle");
		personellerMenu.add(personelEkleItem);

		JMenuItem yetkiEkleItem = new JMenuItem("Yetki Ekle");
		personellerMenu.add(yetkiEkleItem);

		JMenuItem sifreBelirlemeItem = new JMenuItem("Şifre Belirleme");
		personellerMenu.add(sifreBelirlemeItem);
		personellerMenu.addSeparator();

		JMenuItem personelDuzenleItem = new JMenuItem("Personel Düzenle");
		personellerMenu.add(personelDuzenleItem);
		personelDuzenleItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
					
					@Override
					public void run() {
						new PersonelDuzenleFE();
					}
				});
			}
		});
		
		JMenuItem yetkiDuzenleItem = new JMenuItem("Yetki Düzenle");
		personellerMenu.add(yetkiDuzenleItem);
		yetkiDuzenleItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
					
					@Override
					public void run() {
						new YetkiDuzenleFE();
					}
				});
			}
		});
		
		
		
		/*Musteri Menüsü*/
		
		JMenu musterilerMenu = new JMenu("Müşteriler");
		bar.add(musterilerMenu);
		JMenuItem musteriEkleItem = new JMenuItem("Müşteri Ekle");
		musterilerMenu.add(musteriEkleItem);
		JMenuItem sehirEkleItem = new JMenuItem("Şehir Ekle");
		musterilerMenu.add(sehirEkleItem);
		musterilerMenu.addSeparator();
		
		JMenuItem musteriDuzenleItem = new JMenuItem("Müşteri Düzenle");
		musterilerMenu.add(musteriDuzenleItem);
		musteriDuzenleItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
					
					@Override
					public void run() {
						new musteriDuzenleFE();
					}
				});
			}
		});
		
		JMenuItem sehirDuzenleItem = new JMenuItem("Şehir Düzenle");
		musterilerMenu.add(sehirDuzenleItem);
		sehirDuzenleItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
					
					@Override
					public void run() {
						new sehirDuzenleFE();
					}
				});
			}
		});

		PersonelContract contract = (PersonelContract) LoginFE.emailBox.getSelectedItem();
		
		if(new AccountsDAL().GetYetkiId(contract.getId()).getYetkiId()==2)
		{
			personellerMenu.hide();
		}else if(new AccountsDAL().GetYetkiId(contract.getId()).getYetkiId()==3){
		musterilerMenu.hide();
			personellerMenu.hide();
			urunlerMenu.hide();
		}
		
	
		urunEkleItem.addActionListener(new ActionListener() {
		
			
			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {

					@Override
					public void run() {

						new UrunekleFE();

					}
				});

			}
		});

		
		kategoriEkleItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new KategoriEkleFE();

			}
		});

	
		personelEkleItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				SwingUtilities.invokeLater(new Runnable() {

					@Override
					public void run() {

						new PersonelEkleFE();

					}
				});

			}
		});

	
		
		yetkiEkleItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				SwingUtilities.invokeLater(new Runnable() {
					
					@Override
					public void run() {
						new YetkiEkleFE();
						
					}
				});
				
			}
		});
		
		
		
		sifreBelirlemeItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				new SifreIslemleriFE();
				
			}
		});
		
		
		
		musteriEkleItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				SwingUtilities.invokeLater(new Runnable() {
					
					@Override
					public void run() {
						
						new MusteriEkleFE();
						
					}
				});
				
			}
		});
		
		sehirEkleItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				SwingUtilities.invokeLater(new Runnable() {
					
					@Override
					public void run() {
						
						new SehirEkleFE();
						
					}
				});
				
			}
		});
		
		return bar;
	}
}
