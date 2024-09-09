import java.awt.BorderLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MainFrame extends JFrame {
	JSlider slider;
	DrawPanel p;
	public MainFrame() {
		JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);
        JMenuItem selectFileItem = new JMenuItem("Select File");
        fileMenu.add(selectFileItem);
        selectFileItem.addActionListener(new FileListener());
        setJMenuBar(menuBar);
        
        slider = new JSlider(JSlider.HORIZONTAL, 0, 1, 0); // min: 1, max: n, initial: 1
        slider.setMajorTickSpacing(1 / 10); // Set major tick spacing
        slider.setMinorTickSpacing(1 / 20); // Set minor tick spacing
        slider.setPaintTicks(true);          // Show tick marks
        slider.setPaintLabels(true);         // Show labels for ticks
        
        setLayout(new BorderLayout());
        add(slider, BorderLayout.SOUTH);
		
		setTitle("SOP Instance Viewer");
		setSize(1400, 1400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null); 
		setVisible(true);
		
		p = new DrawPanel();
		p.setVisible(true);
		add(p, BorderLayout.CENTER);
		
		addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                // This method is called when the component is resized
                Main.screenWidth =  getWidth();
                Main.screenHeight = getHeight();
            }
        });
		slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
            	if(Main.currentSample != slider.getValue()) {
            		Main.currentSample = slider.getValue();
                	p.repaint();
            	}
            }
        });
	}
	
	public void updateSlider(int size) {
		slider.setMaximum(size - 1);
		slider.setMajorTickSpacing(5); // Set major tick spacing
		
	    slider.setMinorTickSpacing(1); // Set minor tick spacing
	    Main.currentSample = 0;
	}
	
	class FileListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			 // Create a file chooser
            JFileChooser fileChooser = new JFileChooser();

            // Show the file chooser dialog
            int result = fileChooser.showOpenDialog(null);

            // Check if a file was selected
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                // Display the file path in a dialog or process the file
                JOptionPane.showMessageDialog(null, "Selected file: " + selectedFile.getAbsolutePath());
                setTitle(selectedFile.getAbsolutePath());
                Main.createPaths(selectedFile);
                updateSlider(Main.sampleCount);
                p.repaint();
            }
		}
	}


	
}
