package com.moraru;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;


public class FaderGUI {
	private JFrame mainFrame;
	private JLabel titleLabel;
	private JPanel fadersPanel;
	private JPanel presetButtonsPanel;
	private JPanel presetLEDsPanel;
	private JPanel presetEditPanel;
	private JLabel [] faderLabels;
	private JButton [] faderButtons;
	private FaderPresetButton [] presetButtons;
	
	public FaderGUI(){
		prepareGUI();
	}

	public static void main(String[] args){
		FaderGUI FaderGUI = new FaderGUI();  
		FaderGUI.showGridLayout();       
	}

	private void prepareGUI(){
		mainFrame = new JFrame("FaderCTRL");
		mainFrame.setSize(1120,720);
		mainFrame.getContentPane().setBackground(Color.decode("#505050"));
		mainFrame.setLayout(new GridLayout(8, 1, 0, 0));
		mainFrame.setResizable(false);

		titleLabel = new JLabel("",JLabel.CENTER);
		titleLabel.setBackground(Color.decode("#505050"));
		
		mainFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent){
				System.exit(0);
			}        
		});    

		fadersPanel = new JPanel();
		presetButtonsPanel = new JPanel();
		presetLEDsPanel = new JPanel();
		faderLabels = new JLabel [8];
		presetButtons = new FaderPresetButton[5];
		presetEditPanel = new JPanel();
		
		mainFrame.add(titleLabel);
		mainFrame.add(fadersPanel);
		mainFrame.add(new JLabel(new ImageIcon("images/preset/selection.png")));
		mainFrame.add(presetButtonsPanel);
		mainFrame.add(presetLEDsPanel);
		mainFrame.add(new JLabel(new ImageIcon("images/preset/management.png")));
		mainFrame.add(presetEditPanel);
		
		mainFrame.setVisible(true);  
	}

	private void showGridLayout(){   
		titleLabel.setIcon(new ImageIcon("images/title.png"));

		createFaderSection();
		createPresetSection();
		
		fadersPanel.setBackground(Color.decode("#505050"));
		fadersPanel.setLayout(new GridLayout(2, 7, 0, 0));
		presetLEDsPanel.setBackground(Color.decode("#505050"));
		presetButtonsPanel.setBackground(Color.decode("#505050"));
		presetEditPanel.setBackground(Color.decode("#505050"));
		mainFrame.setVisible(true);  
	}

	private void createFaderSection()
	{
		faderButtons = new JButton[8];
		faderLabels = new JLabel[8];

		for (int i = 0; i < faderButtons.length; i++) {
			faderLabels[i] = new JLabel(new ImageIcon("images/faders/fader" + (i+1) + ".png"));
			fadersPanel.add(faderLabels[i]);
		}

		for (int i = 0; i < faderButtons.length; i++) {
			faderButtons[i] = new JButton(new ImageIcon("images/numbers/1.png")); 
			faderButtons[i].setBorder(null);
			faderButtons[i].setContentAreaFilled(false);
			fadersPanel.add(faderButtons[i]);
		}

	}

	private void createPresetSection(){
		
		for (int i = 0; i < presetButtons.length; i++) {
			presetButtons[i] = new FaderPresetButton(presetButtonsPanel); 
			presetButtonsPanel.add(presetButtons[i]);
		}
		
		presetEditPanel.add(new JLabel(new ImageIcon("images/preset/store.png")));
		presetEditPanel.add(new JLabel(new ImageIcon("images/preset/delete.png")));
	}
}