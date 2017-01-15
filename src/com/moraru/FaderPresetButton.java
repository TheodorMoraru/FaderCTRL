package com.moraru;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
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

	public FaderPresetButton(final JPanel panel)
	{
		this.button.setIcon(buttonOff);
		this.led.setIcon(ledOff);
		this.isOn = false;
		this.button.setBorder(null);
		this.button.setContentAreaFilled(false);
		this.button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				refresh(panel);
			}
		});
		panel.add(button);
		//panel.add(led);

	}

	public void refresh(JPanel panel)
	{
		for (Component c : panel.getComponents()) 
			if (c instanceof FaderPresetButton) {
				((FaderPresetButton)c).button.setIcon(buttonOff);
				((FaderPresetButton)c).isOn = false;
			}	

		this.isOn = true;
		this.button.setIcon(buttonOn);
	}



}
