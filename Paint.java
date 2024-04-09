
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;



/**
 * A program Project4
 *
 * <p>Purdue University -- CS18000 -- Spring 2024 -- Project4-- </p>
 *
 * @version Mar 27, 2024
 */
public class Paint extends JFrame {

    private JButton clrButton, fillButton, eraseButton, randomButton, hexButton, rgbButton;
    private JTextField hexText, rText, gText, bText;
    private JPanel canvas;
    private Color penColor = Color.BLACK;

    public Paint() {
        setTitle("Paint Application");
        setSize(800, 600);
        setLocationRelativeTo(null); // Center on screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Top panel
        JPanel topPanel = new JPanel();
        clrButton = new JButton("Clear");
        fillButton = new JButton("Fill");
        eraseButton = new JButton("Erase");
        randomButton = new JButton("Random");
        topPanel.add(clrButton);
        topPanel.add(fillButton);
        topPanel.add(eraseButton);
        topPanel.add(randomButton);

        // Canvas
        canvas = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                setBackground(Color.WHITE);
            }
        };
        canvas.setPreferredSize(new Dimension(800, 400));
        MouseAdapter mouseListener = new MouseAdapter() {
            Point start, end;

            @Override
            public void mousePressed(MouseEvent e) {
                start = e.getPoint();
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                end = e.getPoint();
                Graphics2D g2 = (Graphics2D) canvas.getGraphics();
                g2.setStroke(new BasicStroke(5));
                g2.setColor(penColor);
                g2.drawLine(start.x, start.y, end.x, end.y);
                start = end;
            }
        };
        canvas.addMouseListener(mouseListener);
        canvas.addMouseMotionListener(mouseListener);

        // Bottom panel
        JPanel bottomPanel = new JPanel();
        hexText = new JTextField("#", 10);
        rText = new JTextField("", 5);
        gText = new JTextField("", 5);
        bText = new JTextField("", 5);
        hexButton = new JButton("Set Hex");
        rgbButton = new JButton("Set RGB");
        bottomPanel.add(hexText);
        bottomPanel.add(hexButton);
        bottomPanel.add(rText);
        bottomPanel.add(gText);
        bottomPanel.add(bText);
        bottomPanel.add(rgbButton);

        // Adding panels to frame
        add(topPanel, BorderLayout.NORTH);
        add(canvas, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        // Button listeners
        clrButton.addActionListener(e -> clearCanvas());
        fillButton.addActionListener(e -> fillCanvas());
        eraseButton.addActionListener(e -> eraseCanvas());
        randomButton.addActionListener(e -> randomColor());
        hexButton.addActionListener(e -> setColorFromHex());
        rgbButton.addActionListener(e -> setColorFromRGB());
    }

    private void clearCanvas() {
        canvas.repaint();
        penColor = Color.BLACK;
        resetTextFields();
    }

    private void fillCanvas() {
       Graphics2D g2 = (Graphics2D) canvas.getGraphics();
       g2.setColor(penColor);
       g2.fillRect(0,0,canvas.getWidth(),getHeight());
    }

    private void eraseCanvas() {
        penColor = canvas.getBackground();
    }

    private void randomColor() {
        Random random = new Random();
        int r = random.nextInt(256);
        int g = random.nextInt(256);
        int b = random.nextInt(256);
        penColor = new Color(r, g, b);
        updateTextFields(penColor);
    }

    private void setColorFromHex() {
        try {
            String hexValue = hexText.getText();
            if (!hexValue.matches("#[0-9a-fA-F]{6}")) {
                throw new NumberFormatException("Invalid Hex format");
            }
            Color color = Color.decode(hexValue);
            penColor = color;
            updateTextFields(color);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Not a valid Hex Value", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void setColorFromRGB() {
        try {
            int r = rText.getText().isEmpty() ? 0 : Integer.parseInt(rText.getText());
            int g = gText.getText().isEmpty() ? 0 : Integer.parseInt(gText.getText());
            int b = bText.getText().isEmpty() ? 0 : Integer.parseInt(bText.getText());
            if (r < 0 || r > 255 || g < 0 || g > 255 || b < 0 || b > 255) {
                throw new IllegalArgumentException("RGB values must be between 0 and 255");
            }
            Color color = new Color(r, g, b);
            penColor = color;
            hexText.setText(String.format("#%02x%02x%02x", r, g, b));
            updateTextFields(color);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, "Not a valid RGB Value", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void resetTextFields() {
        hexText.setText("#");
        rText.setText("");
        gText.setText("");
        bText.setText("");
    }

    private void updateTextFields(Color color) {
        hexText.setText(String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue()));
        rText.setText(String.valueOf(color.getRed()));
        gText.setText(String.valueOf(color.getGreen()));
        bText.setText(String.valueOf(color.getBlue()));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Paint().setVisible(true);
        });
    }

    // Getters for buttons and text fields if needed

    public JButton getClearButton() {
        return clrButton;
    }

    public JButton getFillButton() {
        return fillButton;
    }

    public JButton getEraseButton() {
        return eraseButton;
    }

    public JButton getRandomButton() {
        return randomButton;
    }

    public JTextField getHexTextField() {
        return hexText;
    }

    public JTextField getRTextField() {
        return rText;
    }

    public JTextField getGTextField() {
        return gText;
    }

    public JTextField getBTextField() {
        return bText;
    }
}


/*
public class Paint extends JFrame {

    private JButton clrButton, fillButton, eraseButton, randomButton, hexButton, rgbButton;
    private JTextField hexText, rText, gText, bText;
    private JPanel canvas;
    private Color penColor = Color.BLACK;

    public Paint() {
        setTitle("Paint Application");
        setSize(800, 600);
        setLocationRelativeTo(null); // Center on screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Top panel
        JPanel topPanel = new JPanel();
        clrButton = new JButton("Clear");
        fillButton = new JButton("Fill");
        eraseButton = new JButton("Erase");
        randomButton = new JButton("Random");
        topPanel.add(clrButton);
        topPanel.add(fillButton);
        topPanel.add(eraseButton);
        topPanel.add(randomButton);

        // Canvas
        canvas = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                setBackground(Color.WHITE);
            }
        };
        canvas.setPreferredSize(new Dimension(800, 400));
        canvas.setBackground(Color.WHITE);
        MouseAdapter mouseListener = new MouseAdapter() {
            Point start, end;

            @Override
            public void mousePressed(MouseEvent e) {
                start = e.getPoint();
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                end = e.getPoint();
                Graphics2D g2 = (Graphics2D) canvas.getGraphics();
                g2.setStroke(new BasicStroke(5));
                g2.setColor(penColor);
                g2.drawLine(start.x, start.y, end.x, end.y);
                start = end;
            }
        };
        canvas.addMouseListener(mouseListener);
        canvas.addMouseMotionListener(mouseListener);

        // Bottom panel
        JPanel bottomPanel = new JPanel();
        hexText = new JTextField("#", 10);
        rText = new JTextField("", 5);
        gText = new JTextField("", 5);
        bText = new JTextField("", 5);
        hexButton = new JButton("Set Hex");
        rgbButton = new JButton("Set RGB");
        bottomPanel.add(hexText);
        bottomPanel.add(hexButton);
        bottomPanel.add(rText);
        bottomPanel.add(gText);
        bottomPanel.add(bText);
        bottomPanel.add(rgbButton);

        // Adding panels to frame
        add(topPanel, BorderLayout.NORTH);
        add(canvas, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        // Button listeners
        clrButton.addActionListener(e -> clearCanvas());
        fillButton.addActionListener(e -> fillCanvas());
        eraseButton.addActionListener(e -> eraseCanvas());
        randomButton.addActionListener(e -> randomColor());
        hexButton.addActionListener(e -> setColorFromHex());
        rgbButton.addActionListener(e -> setColorFromRGB());
    }

    private void clearCanvas() {
        canvas.setBackground(Color.WHITE);
        canvas.repaint();
        penColor = Color.BLACK;
        resetTextFields();
    }

    private void fillCanvas() {
        canvas.setBackground(penColor);
        penColor = Color.BLACK;
        resetTextFields();
    }

    private void eraseCanvas() {
        penColor = canvas.getBackground();
    }

    private void randomColor() {
        Random random = new Random();
        int r = random.nextInt(256);
        int g = random.nextInt(256);
        int b = random.nextInt(256);
        penColor = new Color(r, g, b);
        updateTextFields(penColor);
    }

    private void setColorFromHex() {
        try {
            String hexValue = hexText.getText();
            if (!hexValue.matches("#[0-9a-fA-F]{6}")) {
                throw new NumberFormatException("Invalid Hex format");
            }
            Color color = Color.decode(hexValue);
            penColor = color;
            updateTextFields(color);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Not a valid Hex Value", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void setColorFromRGB() {
        try {
            int r = rText.getText().isEmpty() ? 0 : Integer.parseInt(rText.getText());
            int g = gText.getText().isEmpty() ? 0 : Integer.parseInt(gText.getText());
            int b = bText.getText().isEmpty() ? 0 : Integer.parseInt(bText.getText());
            if (r < 0 || r > 255 || g < 0 || g > 255 || b < 0 || b > 255) {
                throw new IllegalArgumentException("RGB values must be between 0 and 255");
            }
            Color color = new Color(r, g, b);
            penColor = color;
            hexText.setText(String.format("#%02x%02x%02x", r, g, b));
            updateTextFields(color);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, "Not a valid RGB Value", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void resetTextFields() {
        hexText.setText("#");
        rText.setText("");
        gText.setText("");
        bText.setText("");
    }

    private void updateTextFields(Color color) {
        hexText.setText(String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue()));
        rText.setText(String.valueOf(color.getRed()));
        gText.setText(String.valueOf(color.getGreen()));
        bText.setText(String.valueOf(color.getBlue()));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Paint().setVisible(true);
        });
    }

    public JButton getClearButton() {
        return clrButton;
    }

    public JButton getFillButton() {
        return fillButton;
    }

    public JButton getEraseButton() {
        return eraseButton;
    }

    public JButton getRandomButton() {
        return randomButton;
    }

    public JTextField getHexTextField() {
        return hexText;
    }

    public JTextField getRTextField() {
        return rText;
    }

    public JTextField getGTextField() {
        return gText;
    }

    public JTextField getBTextField() {
        return bText;
    }
}



 */