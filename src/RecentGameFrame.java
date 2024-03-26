import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class RecentGameFrame extends JFrame {

    JScrollPane scroll;
    JTable table;
    String [] info = {"player","score","date","time"};
    String[][] data = new String[100][8];
    public RecentGameFrame() throws FileNotFoundException {

        this.setTitle("Brick Breaker");
        this.setSize(700,600);

        table = new JTable(data,info);
        scroll = new JScrollPane(table);
        scroll.setBounds(650,0,30,700);
        table.setBounds(0,0,700,700);
        table.setRowHeight(30);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);


        File file = new File("data.txt");
        Scanner sc = new Scanner(file);
        int line = 0;
        int row = 0;
        while(sc.hasNextLine()) {
            String s = sc.next();
            String[] paerson = s.split("/");

            for (String string : paerson) {
                table.setValueAt(string, row, line);
                if (line < 3) {
                    line++;
                }
            }
            line = 0;
            row++;
            if(sc.hasNextLine()) {

                sc.nextLine();
            }



        }

        scroll.setLocation(10, 10);
        this.setResizable(false);
        this.add(scroll);
        this.setVisible(true);
    }
}
