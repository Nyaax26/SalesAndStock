package furkanservetkaya.test;

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

import furkanservetkaya.Utilities.MenulerCom;
import furkanservetkaya.dal.AccountsDAL;
import furkanservetkaya.dal.PersonelDAL;
import furkanservetkaya.fe.AnaPencereFE;
import furkanservetkaya.interfaces.FeInterfaces;
import furkanservetkaya.types.PersonelContract;

public class LoginFE extends JDialog implements FeInterfaces{

	public static JComboBox<PersonelContract> emailBox;
	public LoginFE() {
		initPencere();
	}

	@Override
	public void initPencere() {

		JPanel panel = initPanel();
		panel.setBorder(BorderFactory.createTitledBorder("Sisteme Giriş Yapmak İçin Bilgilerinizi Giriniz."));
		
		add(panel);
		setTitle("Lütfen Giriş Yapınız");
		pack();
		setLocationRelativeTo(null);
		//setModalityType(DEFAULT_MODALITY_TYPE);
		setVisible(true);
		setDefaultCloseOperation(HIDE_ON_CLOSE);	
		
	}

	@Override
	public JPanel initPanel() {
		JPanel panel = new JPanel(new GridLayout(3,2));
		JLabel EmailLabel = new JLabel("Email:",JLabel.RIGHT);
		panel.add(EmailLabel);
		
		 emailBox = new JComboBox(new PersonelDAL().GetAll().toArray(new PersonelContract[0]));
		//JComboBox emailBox = new JComboBox(new PersonelDAL().GetAll().toArray());
		panel.add(emailBox);
		
		JLabel passwordLabel = new JLabel("Şifreniz:",JLabel.RIGHT);
		panel.add(passwordLabel);
		JPasswordField passwordField = new JPasswordField(15);
		panel.add(passwordField);
		
		JButton loginButton = new JButton("Giriş Yap");
		panel.add(loginButton);
		loginButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				PersonelContract contract = (PersonelContract) emailBox.getSelectedItem();
				String sifre = passwordField.getText();				
				//String sifre = new String(passwordField.getPassword());
				if(new AccountsDAL().GetPersonelIdVeSifre(contract.getId(),sifre).getId()!=0) {
				//(contract != null && new AccountsDAL().GetPersonelIdVeSifre(contract.getId(), sifre).getId() != 0)
					new AnaPencereFE();
					dispose();
				}
				else {
					JOptionPane.showMessageDialog(null,"Şifre Yanlış Lütfen Tekrar Deneyiniz!!");
				}
				
			}
		});
		
		JButton iptalButton = new JButton("İptal");		
		panel.add(iptalButton);
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
		return null;
	}

	@Override
	public JTabbedPane initTabs() {
		// TODO Auto-generated method stub
		return null;
	}

}
