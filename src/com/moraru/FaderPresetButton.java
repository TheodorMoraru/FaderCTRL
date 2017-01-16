package com.moraru;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

@SuppressWarnings("serial")
public class FaderPresetButton extends Component{

	JToggleButton button = new JToggleButton();
	JLabel led = new JLabel();
	ImageIcon buttonOn = new ImageIcon("images/preset/on.png");
	ImageIcon buttonOff = new ImageIcon("images/preset/off.png");
	ImageIcon ledOn = new ImageIcon("images/leds/on.png");
	ImageIcon ledOff = new ImageIcon("images/leds/off.png");
	boolean isOn = false;
	private int index;
	String [][] presetStoredValues;
	private static final String COMMA_DELIMITER = ",";
	private static final String NEW_LINE_SEPARATOR = "\n";


	public FaderPresetButton(final JPanel presetButtonsPanel, final JPanel fadersPanel, final String [][] presetStoredValues){
		this.button.setIcon(buttonOff);
		this.led.setIcon(ledOff);
		this.isOn = false;
		this.button.setBorder(null);
		this.button.setContentAreaFilled(false);
		this.presetStoredValues = presetStoredValues;
		this.button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				refresh(presetButtonsPanel); //updates the icons to pushed or not
				
				try {
					loadPreset(presetStoredValues, index, fadersPanel); //gets the stored values from csv file and updates the faders according to index of preset button
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				loadPresetAtIndex(presetStoredValues, index, fadersPanel);
			}

		});
		presetButtonsPanel.add(button);
		//panel.add(led);

	}

	public void refresh(JPanel panel){
		for (Component c : panel.getComponents()) 
			if (c instanceof FaderPresetButton) {
				((FaderPresetButton)c).button.setIcon(buttonOff);
				((FaderPresetButton)c).isOn = false;
			}	

		this.isOn = true;
		this.button.setIcon(buttonOn);
	}

	public void savePreset(JPanel panel){
		try{
			FileWriter writer = new FileWriter("presetOptions.csv");
			for (Component c : panel.getComponents()) 
				if (c instanceof FaderPresetButton) {
					writer.append(c.getName());
					writer.append(COMMA_DELIMITER);
					writer.append(NEW_LINE_SEPARATOR);

				}	
			writer.close();
		} catch (IOException e) {

		}
	}

	public void loadPreset(String[][] values, int index, JPanel panel) throws IOException {
		@SuppressWarnings("resource")
		BufferedReader fileReader = new BufferedReader(new FileReader("presetOptions.csv"));
		String line = fileReader.readLine();
		int i = 0;
		while (line != null) {
			//Get all tokens available in line
			String[] tokens = line.split(COMMA_DELIMITER);
			
			if (tokens.length > 0) {
				values[i][0] = tokens[0].toString();
				values[i][1] = tokens[1].toString();
				values[i][2] = tokens[2].toString();
				values[i][3] = tokens[3].toString();
				values[i][4] = tokens[4].toString();
				values[i][5] = tokens[5].toString();
				values[i][6] = tokens[6].toString();
				values[i][7] = tokens[7].toString();
				
				i++;
			}
			line = fileReader.readLine();
		}
	}
	
	private void loadPresetAtIndex(String[][] values, int index, JPanel panel) {
		int j = 0;
		System.out.println(index);
		for (Component c : panel.getComponents()) {
			
			if (c instanceof JButton) {
			((JButton) c).setText(values[index][j++]);
			}	
		}
		
	}
	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
}
