package com.rimberse;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.RoundingMode;
import java.text.DecimalFormat;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class TemperatureConverter implements ActionListener {
	
	JFrame frame;
	JRadioButton FButton;
	JRadioButton CButton;
	JTextField temperature;
	JTextField result;
	JLabel t;
	JLabel r;
	
	public static void main(String[] args) {
		
		new TemperatureConverter().run();
	}
	
	public void run() {
		
		frame = new JFrame("Temperature Conventor");
		JPanel panel = new JPanel();
		JPanel north = new JPanel();
		JPanel center = new JPanel();
		JLabel label = new JLabel("Select temperature scale:");
		label.setHorizontalAlignment(JLabel.CENTER);
		
		FButton = new JRadioButton("Fahrenheit");
		FButton.setHorizontalAlignment(JRadioButton.CENTER);
		FButton.setActionCommand("Fahrenheit");
		FButton.setSelected(false);
		CButton = new JRadioButton("Celsius");
		CButton.setHorizontalAlignment(JRadioButton.CENTER);
		CButton.setActionCommand("Celsius");
		CButton.setSelected(false);
		ButtonGroup group = new ButtonGroup();
		group.add(FButton);
		group.add(CButton);
		
		temperature = new JTextField(5);
		temperature.setHorizontalAlignment(JTextField.CENTER);
		t = new JLabel("Temperature:");
		t.setHorizontalAlignment(JLabel.CENTER);
		JButton convert = new JButton("Convert");
		result = new JTextField(5);
		result.setHorizontalAlignment(JTextField.CENTER);
		r = new JLabel("Result:");
		r.setHorizontalAlignment(JLabel.CENTER);
		result.setEditable(false);
		
		panel.setLayout(new BorderLayout());
		north.setLayout(new GridLayout(3, 2));
		center.setLayout(new GridLayout(4, 2, 15, 0));
		convert.addActionListener(this);
		Font f = new Font("Times", Font.PLAIN, 18);
		Font f2 = new Font("serif", Font.BOLD + Font.ITALIC, 20);
		Font f3 = new Font("Courier", Font.PLAIN, 12);
		label.setFont(f);
		r.setFont(f2);
		t.setFont(f2);
		FButton.setFont(f3);
		CButton.setFont(f3);
		
		north.add(label);
		north.add(new JLabel());
		north.add(new JLabel());
		north.add(new JLabel());
		north.add(FButton);
		north.add(CButton);
		center.add(t);
		center.add(r);
		center.add(temperature);
		center.add(result);
		for (int i = 0; i < 4; i ++) {
			center.add(new JLabel());
		}
		panel.add(BorderLayout.NORTH, north);
		panel.add(BorderLayout.CENTER, center);
		panel.add(BorderLayout.SOUTH, convert);
		frame.getContentPane().add(panel);
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SwingUtilities.updateComponentTreeUI(frame);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(420, 370));
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
	}
	
	public double convertToFahrenheit(double celsius) {
		double fahrenheit;
		fahrenheit = (celsius * 1.8) + 32;
		return fahrenheit;
	}
	
	public double convertToCelsius(double fahrenheit) {
		double celsius;
		celsius = (fahrenheit - 32) / 1.8;
		return celsius;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		DecimalFormat df = new DecimalFormat("#.#");
		df.setRoundingMode(RoundingMode.HALF_UP);
		
		if (FButton.isSelected() == true) {
			
			if (temperature.getText().contains("°C") || temperature.getText().contains("°F")) {
				StringBuilder sb = new StringBuilder(temperature.getText());
				sb.deleteCharAt(sb.length() - 1);
				sb.deleteCharAt(sb.length() - 1);
				sb.deleteCharAt(sb.length() - 1);	
				temperature.setText(sb.toString());
			}
			
			double fahrenheit = convertToFahrenheit(Double.parseDouble(temperature.getText()));
			t.setText("Celsius:");
			temperature.setText(temperature.getText() + " °C");
			r.setText("Fahrenheit:");
			result.setText(df.format(fahrenheit) + " °F");		
			
		} else if (CButton.isSelected() == true) {
			
			if (temperature.getText().contains("°C") || temperature.getText().contains("°F")) {
				StringBuilder sb = new StringBuilder(temperature.getText());
				sb.deleteCharAt(sb.length() - 1);
				sb.deleteCharAt(sb.length() - 1);
				sb.deleteCharAt(sb.length() - 1);	
				temperature.setText(sb.toString());
			}
			
			double celsius = convertToCelsius(Double.parseDouble(temperature.getText()));
			t.setText("Fahrenheit:");
			temperature.setText(temperature.getText() + " °F");
			r.setText("Celsius:");
			result.setText(df.format(celsius) + " °C");		
		}	
	}
}