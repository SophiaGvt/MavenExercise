package maven;

import java.util.*;
import java.io.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 * This class creates a diagram referring to a grades file.
 * 
 * @author SophiaGvt
 *
 */
public class HistogramGenerator {
	
	/**
	 * This method takes as input from the user a file path and
	 * returns an array with the frequencies of each grade the file contains.
	 */	
	public static ArrayList<Integer> countFrequencies() {
		
		ArrayList<Integer> freq = new ArrayList<Integer>();
		for (int i = 0; i < 11; i++) {
			freq.add(0);
		}
		System.out.print("Enter a file path: ");
		Scanner reader = new Scanner(System.in);
		String filepath = reader.nextLine();
		File file = new File(filepath);
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			  
			String line;
			
			while ((line = br.readLine()) != null) {
				int intLine = Integer.parseInt(line);
			    int newValue = freq.get(intLine) + 1;
				freq.set(intLine, newValue);
			}
		} catch (FileNotFoundException ex1){
			System.out.print(ex1.getMessage());
		} catch (IOException ex2) {
			System.out.print(ex2.getMessage());
		} finally {
			if(reader != null) {
				reader.close();
			}
		}
		
		return freq;
	}
	
	
	
	/**
	 * This method takes as input the frequency array and
	 * generates a diagram presenting the frequencies of each grade the file contained.
	 */	
	public static void main(String args[]) {
		createDiagram(countFrequencies());
	}
	
	
	
	/***
	 * This method takes as input the frequency array and
	 * generates a diagram presenting the frequencies of each grade the file contained.
	 * 
	 * @param freq arrayList containing the frequencies
	 */
	public static void createDiagram(ArrayList<Integer> freq) {
		
		XYSeriesCollection dataset = new XYSeriesCollection();
		XYSeries data = new XYSeries("File data");
		for (int i = 0; i < freq.size(); i++) {
			data.add(i, freq.get(i));
		}
		dataset.addSeries(data);

		boolean legend = false; // do not visualize a legend
		boolean tooltips = false; // do not visualize tooltips
		boolean urls = false; // do not visualize urls
		JFreeChart chart = ChartFactory.createXYLineChart("Grades Frequency Diagram", "Grade", "Value", dataset,
				PlotOrientation.VERTICAL, legend, tooltips, urls);
		ChartFrame frame = new ChartFrame("Grades frequency histogram", chart);
		frame.pack();
		frame.setVisible(true);
	}
	
}
