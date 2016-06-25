package ru.spb.ipo.taskgenerator.ui;

import javax.swing.JSplitPane;

public class SmartSplitPane extends JSplitPane {

//	private double myDividerLocation;
//	private boolean inChange = false;
//	
//	public SmartSplitPane() {
//		if (getWidth() != 0) {
//			myDividerLocation = getDividerLocation() / getWidth();
//		} else {
//			myDividerLocation = -1.0;
//		}
//		this.addComponentListener(new ComponentAdapter() {
//			public void componentResized(ComponentEvent e) {
//				if (myDividerLocation != -1){
//					inChange = true;
//					setDividerLocation(myDividerLocation);
//					inChange = false;
//				}
//			}
//		});
//		this.addPropertyChangeListener(JSplitPane.DIVIDER_LOCATION_PROPERTY, new PropertyChangeListener() {
//
//			public void propertyChange(PropertyChangeEvent evt) {
//				if (!inChange) {
//					if (getWidth() != 0) {
//						myDividerLocation = ((double)getDividerLocation()) / getWidth();
//					} else {
//						myDividerLocation = -1;
//					}				
//				}
//			}
//			
//		});
//		
//	}
	
}
