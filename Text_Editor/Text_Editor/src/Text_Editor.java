import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Text_Editor implements ActionListener {
    //declaring a frame and properties of text editor
    JFrame frame;
    JMenuBar menuBar;
    JMenu file;
    JMenu edit;

    JTextArea textArea;

    //file menu items
    JMenuItem newfile , openfile , savefile;
    //edit menu items
    JMenuItem cut , copy , paste , selectall , close;
    Text_Editor(){
        //initialising frame
        frame = new JFrame("Text Editor");

        //initialising menubar
        menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        //initialising textarea
        textArea = new JTextArea();

        //initialising menus
        file = new JMenu("File");
        edit = new JMenu("Edit");

        //initialising menu items
        newfile = new JMenuItem("New Window");
        openfile = new JMenuItem("Open File");
        savefile = new JMenuItem("Save File");
        cut = new JMenuItem("Cut");
        copy = new JMenuItem("Copy");
        paste = new JMenuItem("Paste");
        selectall = new JMenuItem("Select All");
        close = new JMenuItem("Close");

        //add action listener to menu items
        newfile.addActionListener(this);
        openfile.addActionListener(this);
        savefile.addActionListener(this);
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectall.addActionListener(this);
        close.addActionListener(this);

        //add menu items to menus
        file.add(newfile);
        file.add(openfile);
        file.add(savefile);
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectall);
        edit.add(close);


        //add menus to menubar
        menuBar.add(file);
        menuBar.add(edit);
        frame.add(textArea);


        frame.setBounds(50,50,400,400);
        frame.setVisible(true);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == cut){
            textArea.cut();
        }
        if(e.getSource() == copy){
            textArea.copy();
        }
        if(e.getSource() == selectall){
            textArea.selectAll();
        }
        if(e.getSource() == paste){
            textArea.paste();
        }
        if(e.getSource() == close){
            System.exit(0);
        }
        if(e.getSource() == openfile){

            JFileChooser fileChooser = new JFileChooser("C:");
            int chooseOption = fileChooser.showOpenDialog(null);

            if(chooseOption == JFileChooser.APPROVE_OPTION){
                //getting selected file
                File file = fileChooser.getSelectedFile();
                //select file path
                String filePath =  file.getPath();
                try{
                    //initialise fileReader
                    FileReader fileReader = new FileReader(filePath);
                    //initialise buffered reader
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    String intermediate = "";
                    String output = "";
                    while((intermediate = bufferedReader.readLine()) != null ){
                        output += intermediate + "\n";
                    }
                    textArea.setText(output);
                }
                catch(IOException ex){
                    ex.printStackTrace();
                }
            }
        }
        if(e.getSource() == savefile){
            JFileChooser fileChooser = new JFileChooser("C:");
            int chooseOption = fileChooser.showSaveDialog(null);
            if(chooseOption == JFileChooser.APPROVE_OPTION){
                //create new file at destination
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath()+".txt");
                try{
                    FileWriter fileWriter = new FileWriter(file);
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    textArea.write(bufferedWriter);
                    bufferedWriter.close();
                }
                catch(Exception ex){
                    ex.printStackTrace();
                }
            }
        }
        if(e.getSource() == newfile){
            Text_Editor new_text_editor = new Text_Editor();
        }
    }
    public static void main(String[] args) {
        Text_Editor t1 = new Text_Editor();
    }
}