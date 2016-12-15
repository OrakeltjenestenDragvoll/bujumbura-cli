package org.orakel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

public class Gui extends JFrame
{
	private static final long serialVersionUID = -6587937094938003031L;
	JPanel pane = new JPanel();
	JButton settings = new JButton("Settings");
	JButton test = new JButton("Test api connection");
	JTextArea text = new JTextArea(13,25);
    ArrayList<String> strlist = new ArrayList<String>();
	
    Gui()
	{
		super("Bujumbura");
		setBounds(100,100,300,300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container con = this.getContentPane();
		con.add(pane);
		setVisible(true);
		
		pane.add(settings);
		pane.add(test);
		pane.add(text);
		
	}
    public void addController(ActionListener controller)
    {
		settings.addActionListener(controller);
		test.addActionListener(controller);
    	
    }
	public void addText(String newLine)
	{
		int listSize = 13;
		strlist.add(newLine);
		String textBuilder ="";
		if(strlist.size() > listSize)
		{
			strlist.remove(0);
		}else
		{
			for(int i = strlist.size(); i<listSize; i++)
				textBuilder += "\n";
		}
		for(String s :strlist)
		{
			textBuilder += s + "\n";
		}
			
		text.setText(textBuilder);
		
	}
}
