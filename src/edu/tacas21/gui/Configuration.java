package edu.tacas21.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSeparator;

import edu.tacas21.ta.ModelGenerator;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import javax.swing.JTextField;

public class Configuration {

	private JFrame frmExperimentConfiguration;
	private Parameters setting;
	private String settingPath = "out/declaration";
	private String queryPath = "out/query";
	private JTextField txboxSave;
	private JTextField txBoxCompact;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Configuration window = new Configuration();
					window.frmExperimentConfiguration.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Configuration() {
		setting = new Parameters();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmExperimentConfiguration = new JFrame();
		frmExperimentConfiguration.setTitle("Experiment Configuration");
		frmExperimentConfiguration.setResizable(false);
		frmExperimentConfiguration.setBounds(100, 100, 617, 962);
		frmExperimentConfiguration.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmExperimentConfiguration.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("- Wheel Loader Number:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(45, 56, 188, 19);
		frmExperimentConfiguration.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Please configure the parameters of the agents. Virtual time unit: second.");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(24, 24, 581, 19);
		frmExperimentConfiguration.getContentPane().add(lblNewLabel_1);

		JLabel lblTruckNumber = new JLabel("- Truck Number:");
		lblTruckNumber.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTruckNumber.setBounds(45, 85, 188, 19);
		frmExperimentConfiguration.getContentPane().add(lblTruckNumber);

		JLabel lblWheelLoader = new JLabel("- Wheel Loader Travelling Time:");
		lblWheelLoader.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblWheelLoader.setBounds(45, 114, 276, 19);
		frmExperimentConfiguration.getContentPane().add(lblWheelLoader);

		JLabel lblWheelLoader_2 = new JLabel("- Wheel Loader Task Time:");
		lblWheelLoader_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblWheelLoader_2.setBounds(45, 146, 227, 19);
		frmExperimentConfiguration.getContentPane().add(lblWheelLoader_2);

		JLabel lblTruckTravelling = new JLabel("- Truck Travelling Time:");
		lblTruckTravelling.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTruckTravelling.setBounds(45, 175, 227, 19);
		frmExperimentConfiguration.getContentPane().add(lblTruckTravelling);

		JLabel lblTruckTask = new JLabel("- Truck Task Time:");
		lblTruckTask.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTruckTask.setBounds(45, 204, 227, 19);
		frmExperimentConfiguration.getContentPane().add(lblTruckTask);

		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "1", "2", "3", "4" }));
		comboBox.setToolTipText("");
		comboBox.setBounds(352, 50, 163, 21);
		frmExperimentConfiguration.getContentPane().add(comboBox);

		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8" }));
		comboBox_1.setToolTipText("");
		comboBox_1.setBounds(352, 83, 163, 21);
		frmExperimentConfiguration.getContentPane().add(comboBox_1);

		JComboBox comboBox_5 = new JComboBox();
		comboBox_5.setModel(new DefaultComboBoxModel(new String[] { "60", "90", "120", "150", "180" }));
		comboBox_5.setToolTipText("");
		comboBox_5.setBounds(352, 112, 81, 21);
		frmExperimentConfiguration.getContentPane().add(comboBox_5);

		JComboBox comboBox_6 = new JComboBox();
		comboBox_6.setModel(new DefaultComboBoxModel(new String[] { "30", "60", "90", "120", "150", "180" }));
		comboBox_6.setToolTipText("");
		comboBox_6.setBounds(352, 140, 81, 21);
		frmExperimentConfiguration.getContentPane().add(comboBox_6);

		JComboBox comboBox_7 = new JComboBox();
		comboBox_7.setModel(new DefaultComboBoxModel(new String[] { "60", "90", "120", "150", "180" }));
		comboBox_7.setToolTipText("");
		comboBox_7.setBounds(352, 172, 81, 21);
		frmExperimentConfiguration.getContentPane().add(comboBox_7);

		JComboBox comboBox_8 = new JComboBox();
		comboBox_8.setModel(new DefaultComboBoxModel(new String[] { "30", "60", "90", "120", "150", "180" }));
		comboBox_8.setToolTipText("");
		comboBox_8.setBounds(352, 201, 81, 21);
		frmExperimentConfiguration.getContentPane().add(comboBox_8);

		JLabel lblTo_1 = new JLabel("to");
		lblTo_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTo_1.setBounds(443, 143, 25, 19);
		frmExperimentConfiguration.getContentPane().add(lblTo_1);

		JLabel lblTo_2 = new JLabel("to");
		lblTo_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTo_2.setBounds(443, 172, 25, 19);
		frmExperimentConfiguration.getContentPane().add(lblTo_2);

		JLabel lblTo_3 = new JLabel("to");
		lblTo_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTo_3.setBounds(443, 201, 25, 19);
		frmExperimentConfiguration.getContentPane().add(lblTo_3);

		JLabel lblTo = new JLabel("to");
		lblTo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTo.setBounds(443, 116, 25, 19);
		frmExperimentConfiguration.getContentPane().add(lblTo);

		JComboBox comboBox_5_1 = new JComboBox();
		comboBox_5_1.setModel(new DefaultComboBoxModel(new String[] { "60", "90", "120", "150", "180" }));
		comboBox_5_1.setToolTipText("");
		comboBox_5_1.setBounds(465, 112, 81, 21);
		frmExperimentConfiguration.getContentPane().add(comboBox_5_1);

		JComboBox comboBox_6_1 = new JComboBox();
		comboBox_6_1.setModel(new DefaultComboBoxModel(new String[] { "60", "90", "120", "150", "180" }));
		comboBox_6_1.setToolTipText("");
		comboBox_6_1.setBounds(465, 144, 81, 21);
		frmExperimentConfiguration.getContentPane().add(comboBox_6_1);

		JComboBox comboBox_7_1 = new JComboBox();
		comboBox_7_1.setModel(new DefaultComboBoxModel(new String[] { "60", "90", "120", "150", "180" }));
		comboBox_7_1.setToolTipText("");
		comboBox_7_1.setBounds(465, 173, 81, 21);
		frmExperimentConfiguration.getContentPane().add(comboBox_7_1);

		JComboBox comboBox_8_1 = new JComboBox();
		comboBox_8_1.setModel(new DefaultComboBoxModel(new String[] { "60", "90", "120", "150", "180" }));
		comboBox_8_1.setToolTipText("");
		comboBox_8_1.setBounds(465, 202, 81, 21);
		frmExperimentConfiguration.getContentPane().add(comboBox_8_1);

		JLabel lblNewLabel_1_1 = new JLabel("Please configure the parameters of the environment");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_1.setBounds(24, 275, 412, 19);
		frmExperimentConfiguration.getContentPane().add(lblNewLabel_1_1);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 253, 595, 2);
		frmExperimentConfiguration.getContentPane().add(separator);

		JLabel lblPrimaryCrusher = new JLabel("- Primary Crusher Number:");
		lblPrimaryCrusher.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPrimaryCrusher.setBounds(45, 307, 276, 19);
		frmExperimentConfiguration.getContentPane().add(lblPrimaryCrusher);

		JLabel lblSecondaryCrusher = new JLabel("- Secondary Crusher Number:");
		lblSecondaryCrusher.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSecondaryCrusher.setBounds(45, 340, 276, 19);
		frmExperimentConfiguration.getContentPane().add(lblSecondaryCrusher);

		JLabel lblChargerNumber = new JLabel("- Charger Number:");
		lblChargerNumber.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblChargerNumber.setBounds(45, 369, 276, 19);
		frmExperimentConfiguration.getContentPane().add(lblChargerNumber);

		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] { "1", "2", "3", "4" }));
		comboBox_2.setToolTipText("");
		comboBox_2.setBounds(352, 306, 163, 21);
		frmExperimentConfiguration.getContentPane().add(comboBox_2);

		JComboBox comboBox_3 = new JComboBox();
		comboBox_3.setModel(new DefaultComboBoxModel(new String[] { "1", "2" }));
		comboBox_3.setToolTipText("");
		comboBox_3.setBounds(352, 339, 163, 21);
		frmExperimentConfiguration.getContentPane().add(comboBox_3);

		JComboBox comboBox_4 = new JComboBox();
		comboBox_4.setModel(new DefaultComboBoxModel(new String[] { "0", "1", "2" }));
		comboBox_4.setToolTipText("");
		comboBox_4.setBounds(352, 368, 163, 21);
		frmExperimentConfiguration.getContentPane().add(comboBox_4);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 406, 595, 0);
		frmExperimentConfiguration.getContentPane().add(separator_1);

		JLabel lblNewLabel_1_1_1 = new JLabel("- How many configurations do you want to generate?");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_1_1.setBounds(24, 679, 412, 19);
		frmExperimentConfiguration.getContentPane().add(lblNewLabel_1_1_1);

		JLabel lblConfigurationNumber = new JLabel("- Configuration Number:");
		lblConfigurationNumber.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblConfigurationNumber.setBounds(45, 707, 188, 19);
		frmExperimentConfiguration.getContentPane().add(lblConfigurationNumber);

		JComboBox comboBox_9 = new JComboBox();
		comboBox_9
				.setModel(new DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }));
		comboBox_9.setToolTipText("");
		comboBox_9.setBounds(273, 708, 163, 21);
		frmExperimentConfiguration.getContentPane().add(comboBox_9);

		JSeparator separator_1_1 = new JSeparator();
		separator_1_1.setBounds(10, 597, 595, 2);
		frmExperimentConfiguration.getContentPane().add(separator_1_1);

		JLabel lblNewLabel_1_1_2 = new JLabel("Please configure the parameters of the mission");
		lblNewLabel_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_1_2.setBounds(24, 499, 412, 19);
		frmExperimentConfiguration.getContentPane().add(lblNewLabel_1_1_2);

		JLabel lblStoneAmount = new JLabel("- Stone Amount:");
		lblStoneAmount.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblStoneAmount.setBounds(45, 530, 188, 19);
		frmExperimentConfiguration.getContentPane().add(lblStoneAmount);

		JComboBox comboBox_10 = new JComboBox();
		comboBox_10.setModel(new DefaultComboBoxModel(new String[] { "100", "150", "200" }));
		comboBox_10.setToolTipText("");
		comboBox_10.setBounds(273, 531, 163, 21);
		frmExperimentConfiguration.getContentPane().add(comboBox_10);

		JLabel lblCapacityOf = new JLabel("- Capacity of a Truck:");
		lblCapacityOf.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCapacityOf.setBounds(45, 563, 227, 19);
		frmExperimentConfiguration.getContentPane().add(lblCapacityOf);

		JComboBox comboBox_11 = new JComboBox();
		comboBox_11.setModel(new DefaultComboBoxModel(new String[] { "10", "20", "30", "40", "50", "100" }));
		comboBox_11.setToolTipText("");
		comboBox_11.setBounds(273, 564, 163, 21);
		frmExperimentConfiguration.getContentPane().add(comboBox_11);

		JButton btnNewButton = new JButton("Reset");
		btnNewButton.setBounds(299, 881, 85, 21);
		frmExperimentConfiguration.getContentPane().add(btnNewButton);

		JLabel lblNewLabel_1_1_1_1 = new JLabel("- What is the total learning time per episode?");
		lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_1_1_1.setBounds(24, 611, 412, 19);
		frmExperimentConfiguration.getContentPane().add(lblNewLabel_1_1_1_1);

		JLabel lblLearningTime = new JLabel("- Virtual learning time (minute):");
		lblLearningTime.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblLearningTime.setBounds(45, 643, 249, 19);
		frmExperimentConfiguration.getContentPane().add(lblLearningTime);

		JComboBox comboBox_12 = new JComboBox();
		comboBox_12.setModel(new DefaultComboBoxModel(new String[] { "30", "60", "120", "180", "240", "300" }));
		comboBox_12.setToolTipText("");
		comboBox_12.setBounds(273, 641, 163, 21);
		frmExperimentConfiguration.getContentPane().add(comboBox_12);

		txboxSave = new JTextField();
		txboxSave.setText("/home/ron/uppaal/strategies/new_complete_path.out");
		txboxSave.setBounds(126, 782, 387, 19);
		frmExperimentConfiguration.getContentPane().add(txboxSave);
		txboxSave.setColumns(10);

		JLabel lblDirectory = new JLabel("- Directory:");
		lblDirectory.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDirectory.setBounds(45, 780, 85, 19);
		frmExperimentConfiguration.getContentPane().add(lblDirectory);

		txBoxCompact = new JTextField();
		txBoxCompact.setText("/home/ron/uppaal/strategies/new_comprise_path.out");
		txBoxCompact.setEnabled(false);
		txBoxCompact.setColumns(10);
		txBoxCompact.setBounds(126, 836, 387, 19);
		frmExperimentConfiguration.getContentPane().add(txBoxCompact);

		JLabel lblDirectory_1 = new JLabel("- Directory:");
		lblDirectory_1.setEnabled(false);
		lblDirectory_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDirectory_1.setBounds(45, 834, 85, 19);

		JCheckBox chckbxDoYouWant = new JCheckBox("Do you want to save the strategies?");
		chckbxDoYouWant.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txboxSave.setEnabled(chckbxDoYouWant.isSelected());
				lblDirectory.setEnabled(chckbxDoYouWant.isSelected());
			}
		});
		chckbxDoYouWant.setSelected(true);
		chckbxDoYouWant.setFont(new Font("Tahoma", Font.PLAIN, 15));
		chckbxDoYouWant.setBounds(24, 745, 391, 21);
		frmExperimentConfiguration.getContentPane().add(chckbxDoYouWant);

		JCheckBox chckbxNewCheckBox = new JCheckBox("Do you want to compact the strategies?");
		chckbxNewCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txBoxCompact.setEnabled(chckbxNewCheckBox.isSelected());
				lblDirectory_1.setEnabled(chckbxDoYouWant.isSelected());
			}
		});
		chckbxNewCheckBox.setFont(new Font("Tahoma", Font.PLAIN, 15));
		chckbxNewCheckBox.setBounds(24, 807, 391, 21);
		frmExperimentConfiguration.getContentPane().add(chckbxNewCheckBox);
		frmExperimentConfiguration.getContentPane().add(lblDirectory_1);

		JLabel lblNewLabel_1_1_2_1 = new JLabel("Please configure the time of battery exhaustion (time unit: minute):");
		lblNewLabel_1_1_2_1.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblNewLabel_1_1_2_1.setBounds(21, 400, 525, 19);
		frmExperimentConfiguration.getContentPane().add(lblNewLabel_1_1_2_1);

		JLabel lblTruckTask_1 = new JLabel("- Wheel Loader:");
		lblTruckTask_1.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblTruckTask_1.setBounds(45, 434, 227, 19);
		frmExperimentConfiguration.getContentPane().add(lblTruckTask_1);

		JComboBox comboBox_wl_charging_time_lower = new JComboBox();
		comboBox_wl_charging_time_lower.setModel(new DefaultComboBoxModel(new String[] {"5", "10", "15", "30", "45", "60"}));
		comboBox_wl_charging_time_lower.setToolTipText("");
		comboBox_wl_charging_time_lower.setBounds(352, 431, 81, 21);
		frmExperimentConfiguration.getContentPane().add(comboBox_wl_charging_time_lower);

		JComboBox comboBox_wl_charging_time_upper = new JComboBox();
		comboBox_wl_charging_time_upper.setModel(new DefaultComboBoxModel(new String[] {"5", "10", "15", "30", "45", "60"}));
		comboBox_wl_charging_time_upper.setToolTipText("");
		comboBox_wl_charging_time_upper.setBounds(465, 432, 81, 21);
		frmExperimentConfiguration.getContentPane().add(comboBox_wl_charging_time_upper);

		JLabel lblTruckTask_1_1 = new JLabel("- Truck:");
		lblTruckTask_1_1.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblTruckTask_1_1.setBounds(45, 468, 227, 19);
		frmExperimentConfiguration.getContentPane().add(lblTruckTask_1_1);

		JComboBox comboBox_tk_charging_time_lower = new JComboBox();
		comboBox_tk_charging_time_lower.setModel(new DefaultComboBoxModel(new String[] {"5", "10", "15", "30", "45", "60"}));
		comboBox_tk_charging_time_lower.setToolTipText("");
		comboBox_tk_charging_time_lower.setBounds(352, 465, 81, 21);
		frmExperimentConfiguration.getContentPane().add(comboBox_tk_charging_time_lower);

		JComboBox comboBox_tk_charging_time_upper = new JComboBox();
		comboBox_tk_charging_time_upper.setModel(new DefaultComboBoxModel(new String[] {"5", "10", "15", "30", "45", "60"}));
		comboBox_tk_charging_time_upper.setToolTipText("");
		comboBox_tk_charging_time_upper.setBounds(465, 466, 81, 21);
		frmExperimentConfiguration.getContentPane().add(comboBox_tk_charging_time_upper);

		JLabel lblTo_4 = new JLabel("to");
		lblTo_4.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblTo_4.setBounds(443, 436, 25, 19);
		frmExperimentConfiguration.getContentPane().add(lblTo_4);

		JLabel lblTo_5 = new JLabel("to");
		lblTo_5.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblTo_5.setBounds(443, 468, 25, 19);

		frmExperimentConfiguration.getContentPane().add(lblTo_5);
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setting.wlNum = Integer.parseInt(comboBox.getSelectedItem().toString());
				setting.tkNum = Integer.parseInt(comboBox_1.getSelectedItem().toString());
				setting.pcNum = Integer.parseInt(comboBox_2.getSelectedItem().toString());
				setting.scNum = Integer.parseInt(comboBox_3.getSelectedItem().toString());
				setting.chargerNum = Integer.parseInt(comboBox_4.getSelectedItem().toString());
				
				setting.wlTravelingTimeLower = Integer.parseInt(comboBox_5.getSelectedItem().toString());
				setting.wlTravelingTimeUpper = Integer.parseInt(comboBox_5_1.getSelectedItem().toString());
				setting.wlTaskTimeLower = Integer.parseInt(comboBox_6.getSelectedItem().toString());
				setting.wlTaskTimeUpper = Integer.parseInt(comboBox_6_1.getSelectedItem().toString());
				setting.setWlChTime(Integer.parseInt(comboBox_wl_charging_time_lower.getSelectedItem().toString()),
						Integer.parseInt(comboBox_wl_charging_time_upper.getSelectedItem().toString()));
				
				setting.tkTravelingTimeLower = Integer.parseInt(comboBox_7.getSelectedItem().toString());
				setting.tkTravelingTimeUpper = Integer.parseInt(comboBox_7_1.getSelectedItem().toString());
				setting.tkTaskTimeLower = Integer.parseInt(comboBox_8.getSelectedItem().toString());
				setting.tkTaskTimeUpper = Integer.parseInt(comboBox_8_1.getSelectedItem().toString());
				setting.setTkChTime(Integer.parseInt(comboBox_tk_charging_time_lower.getSelectedItem().toString()),
						Integer.parseInt(comboBox_tk_charging_time_upper.getSelectedItem().toString()));
				
				setting.goal = Integer.parseInt(comboBox_10.getSelectedItem().toString());
				setting.iterations = Integer.parseInt(comboBox_10.getSelectedItem().toString())
						/ Integer.parseInt(comboBox_11.getSelectedItem().toString());
				setting.setTotalTime(Integer.parseInt(comboBox_12.getSelectedItem().toString()));
				setting.save = chckbxDoYouWant.isSelected();
				if (setting.save) {
					setting.path_save = txboxSave.getText();
				}
				setting.compact = chckbxNewCheckBox.isSelected();
				if (setting.compact) {
					setting.path_compact = txBoxCompact.getText();
				}

				int totalNumber = Integer.parseInt(comboBox_9.getSelectedItem().toString());

				// String path1 = "";
				// String path2 = "";
				Parameters temp;
				for (int i = 0; i < totalNumber; i++) {
					// path1 = settingPath + i + ".config";
					// path2 = queryPath + i + ".config";
					temp = setting.random();

					ModelGenerator.run(temp, ModelGenerator.prefix + i + ".xml");
					/*
					 * logtoText(path1); System.out.println(temp.getDelarations()); closeText();
					 * logtoText(path2); System.out.println(temp.getQuery()); closeText();
					 */
				}
			}
		});
		btnOk.setBounds(423, 881, 85, 21);
		frmExperimentConfiguration.getContentPane().add(btnOk);
	}
}
