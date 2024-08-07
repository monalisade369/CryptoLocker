import javax.swing.*;
import java.awt.FlowLayout;
import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
public class Decryption{

    public static void operate(int key)
    {

        JFileChooser fileChooser=new JFileChooser();
        fileChooser.showOpenDialog(null);
        File file=fileChooser.getSelectedFile();
        //file FileInputStream
        try
        {

            FileInputStream fis=new FileInputStream(file);

            byte []data=new byte[fis.available()];
            fis.read(data);
            int i=0;
	   for(byte e:data)
            {
                data[i]=(byte)(e^72);
                i++;
            }
	   i=0;
           for(byte d:data)
           {
                data[i]=(byte)(~d);
                i++;
            }
	    i=0;
            for(byte c:data)
            {
                data[i]=(byte)(c-12);
                i++;
            }
            i=0;
            for(byte b:data)
            {
                data[i]=(byte)(b^key);
                i++;
            }

            FileOutputStream fos=new FileOutputStream(file);
            fos.write(data);
            fos.close();
            fis.close();
            JOptionPane.showMessageDialog(null, "Done");

        }catch(Exception e)
        {
            //e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        
        System.out.println("Decrypt your file:");

        JFrame f=new JFrame();
        f.setTitle("IFile Decryption");
        f.setSize(400,250);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel l1,l2,l3;
        l1=new JLabel();
	l2=new JLabel();  
	l3=new JLabel();
 	l1.setText("1.First enter the password before file selection");
	l2.setText("2.The password is the same as encrypted password");
	l3.setText("3.You will get the key from the document owner");   

        Font font=new Font("Roboto",Font.BOLD,20);
        //creating button
        JButton button=new JButton();
        button.setText("Decrypt File");
        button.setFont(font);

        //creating text field

        JTextField textField=new JTextField(10);
        textField.setFont(font);

        
        button.addActionListener(e->{
            System.out.println("Selecting file for Symmetric Key Decryption.....");
            String text=textField.getText();
            try{
            int temp=Integer.parseInt(text);
            operate(temp);
            f.dispose();
            }
            catch (NumberFormatException e2){
            f.dispose();
            System.out.println("Please use provided password only\nRun the code to decrypt again ");
            }
        });

        f.setLayout(new FlowLayout());

        f.add(textField);
        f.add(button);
        f.add(l1);
	f.add(l2);
	f.add(l3);
        f.setVisible(true);

    }
}
