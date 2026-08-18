import java.awt.*;
import javax.swing.*;

public class PackerUnpackerGUI
{
    public static void main(String args[])
    {
        // Create main JFrame
        JFrame frame = new JFrame("Packer - Unpacker");

        // Set size of main window
        frame.setSize(850, 450);

        // Use manual positioning
        frame.setLayout(null);

        // Close application
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // =====================================================
        // PACKER PANEL
        // =====================================================

        // Create Packer panel
        JPanel packerPanel = new JPanel();

        // Use null layout inside panel
        packerPanel.setLayout(null);

        // Set panel position and size
        packerPanel.setBounds(40, 70, 350, 280);

        // Add panel to JFrame
        frame.add(packerPanel);

        // Packer heading
        JLabel lblPacker = new JLabel("Packer");

        lblPacker.setFont(new Font("Arial", Font.BOLD, 22));

        lblPacker.setBounds(130, 15, 100, 30);

        packerPanel.add(lblPacker);

        // Folder label
        JLabel lblFolder = new JLabel("Folder:");

        lblFolder.setBounds(30, 80, 80, 30);

        packerPanel.add(lblFolder);

        // Folder text field
        JTextField txtFolder = new JTextField();

        txtFolder.setBounds(110, 80, 180, 30);

        packerPanel.add(txtFolder);

        // File Name label
        JLabel lblFileName = new JLabel("File Name:");

        lblFileName.setBounds(30, 130, 80, 30);

        packerPanel.add(lblFileName);

        // File Name text field
        JTextField txtFileName = new JTextField();

        txtFileName.setBounds(110, 130, 180, 30);

        packerPanel.add(txtFileName);

        // Pack button
        JButton btnPack = new JButton("PACK");

        btnPack.setBounds(125, 195, 100, 40);

        packerPanel.add(btnPack);

        // =====================================================
        // UNPACK PANEL
        // =====================================================

        // Create Unpack panel
        JPanel unpackPanel = new JPanel();

        // Use null layout
        unpackPanel.setLayout(null);

        // Set panel position and size
        unpackPanel.setBounds(450, 70, 350, 280);

        // Add panel to JFrame
        frame.add(unpackPanel);

        // Unpack heading
        JLabel lblUnpack = new JLabel("Unpack");

        lblUnpack.setFont(new Font("Arial", Font.BOLD, 22));

        lblUnpack.setBounds(125, 15, 120, 30);

        unpackPanel.add(lblUnpack);

        // Packed File label
        JLabel lblPackedFile = new JLabel("Packed File:");

        lblPackedFile.setBounds( 30, 80, 90, 30);

        unpackPanel.add(lblPackedFile);

        // Packed File text field
        JTextField txtPackedFile = new JTextField();

        txtPackedFile.setBounds(125, 80, 180, 30);

        unpackPanel.add(txtPackedFile);

        // Unpack button
        JButton btnUnpack = new JButton("UNPACK");

        btnUnpack.setBounds( 120, 140, 110, 40);

        unpackPanel.add(btnUnpack);

        // =====================================================
        // PACK EVENT
        // =====================================================

        btnPack.addActionListener(e ->
        {
            // Get folder path from text field
            String folder = txtFolder.getText().trim();

            // Get packed file name
            String fileName = txtFileName.getText().trim();

            // Check folder
            if(folder.isEmpty())
            {
                JOptionPane.showMessageDialog(frame,"Please enter folder path.");

                return;
            }

            // Check file name
            if(fileName.isEmpty())
            {
                JOptionPane.showMessageDialog(frame,"Please enter file name.");

                return;
            }

            try
            {
                // Create Packer object
                Packer pobj = new Packer();

                // Call Packer.java
                pobj.PackFiles(folder,fileName);

                // Show success message
                JOptionPane.showMessageDialog(frame,"Packing completed successfully.");
            }
            catch(Exception ex)
            {
                // Show error message
                JOptionPane.showMessageDialog(frame,"Packing failed:\n" + ex.getMessage());
            }
        });

        // =====================================================
        // UNPACK EVENT
        // =====================================================

        btnUnpack.addActionListener(e ->
        {
            // Get packed file path from text field
            String packedFile = txtPackedFile.getText().trim();

            // Check packed file path
            if(packedFile.isEmpty())
            {
                JOptionPane.showMessageDialog(frame,"Please enter packed file path.");

                return;
            }

            try
            {
                // Create Unpacker object
                Unpacker uobj = new Unpacker();

                // Call Unpacker.java
                uobj.UnpackFile(packedFile);

                // Show success message
                JOptionPane.showMessageDialog(frame,"Unpacking completed successfully.");
            }
            catch(Exception ex)
            {
                // Show error message
                JOptionPane.showMessageDialog(frame,"Unpacking failed:\n" + ex.getMessage());
            }
        });

        // =====================================================
        // SHOW FRAME
        // =====================================================

        frame.setVisible(true);
    }
}