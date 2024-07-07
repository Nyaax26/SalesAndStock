package furkanservetkaya.fe;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import furkanservetkaya.dal.SehirlerDAL;
import furkanservetkaya.interfaces.FeInterfaces;
import furkanservetkaya.types.SehirlerContract;

public class SehirEkleFE extends JDialog implements FeInterfaces{

	public SehirEkleFE() {
		initPencere();
	}

	@Override
	public void initPencere() {
		
		JPanel panel = initPanel();
		panel.setBorder(BorderFactory.createTitledBorder("Şehir Ekle"));
		
		add(panel);
		setTitle("Şehir Ekle");
		pack();
		setLocationRelativeTo(null);
		setModalityType(DEFAULT_MODALITY_TYPE);
		setVisible(true);
		setDefaultCloseOperation(HIDE_ON_CLOSE);	
		
	}

	@Override
	public JPanel initPanel() {
		
		JPanel panel = new JPanel(new GridLayout(2,2));
		JLabel sehirLabel = new JLabel("Şehir Adı:",JLabel.RIGHT);
		panel.add(sehirLabel);
		JTextField sehirField = new JTextField(10);
		panel.add(sehirField);      
		JButton kaydetButton = new JButton("Kaydet");
		panel.add(kaydetButton);
		JButton iptalButton = new JButton("İptal");
		panel.add(iptalButton);
		
		kaydetButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				SehirlerContract contract = new SehirlerContract();
				contract.setSehirId(sehirField.getText());
				
				new SehirlerDAL().Insert(contract);
				JOptionPane.showMessageDialog(null, contract.getSehirId()+" adlı şehir başarıyla eklenmiştir.");	
				
				sehirField.setText("");
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
