package ru.spb.ipo.generator.base.ui;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;

public class ScaleDialog extends JDialog implements ActionListener {
	private BaseGeneratorUI baseGen;
    private ImageIcon icon = null;
	public ScaleDialog(JFrame parent, String title, String message, final BaseGeneratorUI baseGen) {
		super(parent, title, true);
        this.baseGen = baseGen;
		setResizable(false);
		JPanel mainPanelScale = new JPanel();
		mainPanelScale.setLayout(new BorderLayout());
		JPanel top = new JPanel();
		top.setLayout(new FlowLayout());
		top.add(getImagePanelBigScale());
		top.add(getImagePanelSmallScale());
		values = new JPanel();
		values.setLayout(new FlowLayout());
		wLabel = new JLabel("Ширина");
		width = new JTextField(3);
		width.setText("120");
		hLabel = new JLabel("Высота");
		height = new JTextField(3);
		height.setText("120");
		values.add(wLabel);
		values.add(width);
		values.add(hLabel);
		values.add(height);
		JButton button3 = new JButton("Масштаб");
		button3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int widthS = Integer.valueOf(width.getText());
					int heightS = Integer.valueOf(height.getText());
					icon = new ImageIcon(fileToScale
							.getAbsolutePath());
					icon = new ImageIcon(icon.getImage().getScaledInstance(
							widthS, heightS, Image.SCALE_DEFAULT));
					JLabel label = new JLabel(icon);
					MouseListener listener = new DragMouseAdapter();
					label.addMouseListener(listener);
					label.setTransferHandler(new TransferHandler("icon"));
					try {
						Component c = getImageListPanelSmall().getComponent(0);
						getImageListPanelSmall().remove(c);
					} catch (Exception e2) {
					}
					getImageListPanelSmall().updateUI();
					getImageListPanelSmall().add(label);
					getImageListPanelSmall().revalidate();
				} catch (Exception e8) {
				}
			}
		});
		values.add(button3);
		// top.add(values);

		JPanel buttons = new JPanel();
		JButton button1 = new JButton("Сохранить");
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					JFileChooser fc = getImageChooser();
					int result = fc.showSaveDialog(baseGen);
					if (JFileChooser.APPROVE_OPTION == result) {
						File file = getImageChooser().getSelectedFile();
						JLabel l = (JLabel) imageListPanelSmall.getComponent(0);
						ImageIcon ic = (ImageIcon) l.getIcon();
						Image image = ic.getImage();
						BufferedImage bi1 = new BufferedImage(Integer
								.valueOf(width.getText()), Integer
								.valueOf(height.getText()),
								BufferedImage.TYPE_INT_RGB);
						Graphics2D big = bi1.createGraphics();
						big.drawImage(image, 0, 0, Integer.valueOf(width
								.getText()), Integer.valueOf(height
								.getText()), Color.LIGHT_GRAY, null);
                        big.dispose();
                        String format = file.getName().substring(
								file.getName().lastIndexOf(".") + 1,
								file.getName().length());

                        try {
							if (!ImageIO.write(bi1, format,file)) {
                                JOptionPane.showMessageDialog(
                                            ScaleDialog.this,
                                            "No appropriate writer is found for specified image format",
                                            "Ошибка при сохранении",
                                            JOptionPane.ERROR_MESSAGE);
                            }
                        } catch (IOException e1) {
                            e1.printStackTrace();
                            JOptionPane.showMessageDialog(
										ScaleDialog.this,
										e1.getMessage(),
										"Ошибка при сохранении",
										JOptionPane.ERROR_MESSAGE);
                        }

					}

				} catch (Exception e9) {
				}
			}
		});

		JButton button2 = new JButton("Загрузить...");

		button2.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				int result = getImageChooser().showOpenDialog(baseGen);
				if (JFileChooser.APPROVE_OPTION == result) {
                    icon = null;
                    width.setText("120");
					height.setText("120");
					File file = getImageChooser().getSelectedFile();
					fileToScale = file;
					File fileResizeImage = null;
					ImageIcon icon = new ImageIcon(file.getAbsolutePath());
					JLabel label = new JLabel(icon);
					MouseListener listener = new DragMouseAdapter();
					label.addMouseListener(listener);
					label.setTransferHandler(new TransferHandler("icon"));
					try {
						Component c = getImageListPanelBig().getComponent(0);
						getImageListPanelBig().remove(c);
					} catch (Exception e2) {
					}

					getImageListPanelBig().add(label, BorderLayout.CENTER);
					getImageListPanelBig().revalidate();

					int widthS = 120;
					int heightS = 120;
					ImageIcon icon1 = new ImageIcon(file.getAbsolutePath());
					icon1 = new ImageIcon(icon1.getImage().getScaledInstance(
							widthS, heightS, Image.SCALE_DEFAULT));
					JLabel label1 = new JLabel(icon1);
					MouseListener listener1 = new DragMouseAdapter();
					label1.addMouseListener(listener);
					label1.setTransferHandler(new TransferHandler("icon"));
					try {
						Component c = getImageListPanelSmall().getComponent(0);
						getImageListPanelSmall().remove(c);
						Component c1 = getImageListPanelSmall().getComponent(0);
					} catch (Exception e2) {
					}
					values.updateUI();
					getImageListPanelSmall().updateUI();
					getImageListPanelSmall().add(label1);
					getImageListPanelSmall().revalidate();
				}

			}
		});

		buttons.add(button2);
		buttons.add(button1);
		mainPanelScale.add(values, BorderLayout.NORTH);
		mainPanelScale.add(top, BorderLayout.CENTER);
		mainPanelScale.add(buttons, BorderLayout.SOUTH);
		getContentPane().add(mainPanelScale, BorderLayout.CENTER);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		pack();
        setLocationRelativeTo(parent);
        
		setVisible(true);
	}

	private JPanel values;

	public void actionPerformed(ActionEvent e) {
		setVisible(false);
		dispose();
	}

	private File fileToScale;

	private JPanel rightTop;

	private JLabel wLabel;

	private JLabel hLabel;

	private JTextField width;
	private JTextField height;

	private JPanel getImagePanelBigScale() {
		if (rightTop == null) {
			rightTop = new JPanel();
			rightTop.setLayout(new BorderLayout());
			rightTop.add(getJScrollPaneBigScroll(), BorderLayout.CENTER);
		}
		return rightTop;
	}

	JScrollPane jScrollPaneBig;

	private JScrollPane getJScrollPaneBigScroll() {
		if (jScrollPaneBig == null) {
			jScrollPaneBig = new JScrollPane(getImageListPanelBig());
			jScrollPaneBig.setPreferredSize(new Dimension(230, 190));
		}
		return jScrollPaneBig;
	}

	JPanel imageListPanelBig;

	private JPanel getImageListPanelBig() {
		if (imageListPanelBig == null) {
			imageListPanelBig = new JPanel();
			imageListPanelBig.setLayout(new BorderLayout());
		}
		return imageListPanelBig;
	}

	private JPanel leftTop;

	private JPanel getImagePanelSmallScale() {
		if (leftTop == null) {
			leftTop = new JPanel();
			leftTop.setLayout(new BorderLayout());
			leftTop.add(getJScrollPaneSmallScroll(), BorderLayout.CENTER);
		}
		return leftTop;
	}

	JScrollPane jScrollPaneSmall;

	private JScrollPane getJScrollPaneSmallScroll() {
		if (jScrollPaneSmall == null) {
			jScrollPaneSmall = new JScrollPane();
			jScrollPaneSmall.setViewportView(getImageListPanelSmall());
			jScrollPaneSmall.setPreferredSize(new Dimension(230, 190));
		}
		return jScrollPaneSmall;
	}

	JPanel imageListPanelSmall;

	private JPanel getImageListPanelSmall() {
		if (imageListPanelSmall == null) {
			imageListPanelSmall = new JPanel();
			imageListPanelSmall.setLayout(new BorderLayout());
		}
		return imageListPanelSmall;
	}

	private JFileChooser imageChooser;

	public JFileChooser getImageChooser() {
		if (imageChooser == null) {
			imageChooser = new JFileChooser(new File("." + File.separator
					+ "tasks" + File.separator + "imgs"));
			imageChooser.setDialogTitle("Выберите файл-картинку к задаче...");
			imageChooser.setControlButtonsAreShown(true);
			imageChooser.removeChoosableFileFilter(imageChooser
					.getChoosableFileFilters()[0]);

			// hide(imageChooser.getComponents(), 0);
			imageChooser.setFileFilter(new FileFilter() {
				public boolean accept(File pathname) {
					if (pathname.isDirectory())
						return true;
					if (pathname.isFile()) {
						String name = pathname.getName().toLowerCase();
						if (name.endsWith(".png") || name.endsWith(".gif")
								|| name.endsWith(".jpg")
								|| name.endsWith(".jpeg")) {
							return true;
						}
					}
					return false;
				}

				public String getDescription() {
					return "картинки (*.png, *.gif, *.jpg, *.jpeg)";
				}
			});
			imageChooser.setAccessory(new ImagePreview(imageChooser));
		}
		return imageChooser;
	}

	class ImagePreview extends JLabel {
		public ImagePreview(JFileChooser chooser) {
			setPreferredSize(new Dimension(100, 100));
			setBorder(BorderFactory.createEtchedBorder());
			chooser.addPropertyChangeListener(new PropertyChangeListener() {
				public void propertyChange(PropertyChangeEvent event) {
					if (event.getPropertyName() == JFileChooser.SELECTED_FILE_CHANGED_PROPERTY) {
						File f = (File) event.getNewValue();
						if (f == null) {
							setIcon(null);
							return;
						}
						ImageIcon icon = new ImageIcon(f.getPath());
						if (icon.getIconWidth() > getWidth())
							icon = new ImageIcon(icon.getImage()
									.getScaledInstance(getWidth(), -1,
											Image.SCALE_DEFAULT));
						setVerticalAlignment(CENTER);
						setHorizontalAlignment(CENTER);
						setIcon(icon);
					}
				}
			});
		}
	}

	class DragMouseAdapter extends MouseAdapter {
		public void mousePressed(MouseEvent e) {
			JComponent c = (JComponent) e.getSource();
			TransferHandler handler = c.getTransferHandler();
			handler.exportAsDrag(c, e, TransferHandler.COPY);
		}
	}
}
