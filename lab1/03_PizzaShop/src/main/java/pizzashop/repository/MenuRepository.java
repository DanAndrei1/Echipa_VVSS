package pizzashop.repository;

import pizzashop.model.MenuDataModel;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class MenuRepository {
    private List<MenuDataModel> listMenu;

    public MenuRepository(){
    }

    private void readMenu() throws IOException {
        String filename = "data/menu.txt";
        File file = new File(filename);
        this.listMenu= new ArrayList<>();
        BufferedReader br = null;
        try
        {
            br = new BufferedReader(new FileReader(file));
            String line = null;
            while((line=br.readLine())!=null){
                MenuDataModel menuItem=getMenuItem(line);
                listMenu.add(menuItem);
            }
            br.close();
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File Not Found");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        catch (IOException e)
        {
            System.out.println("Error reading file");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        finally
        {
            assert br != null;
            br.close();
        }
    }

    private MenuDataModel getMenuItem(String line){
        MenuDataModel item=null;
        if (line==null|| line.isEmpty()) return null;
        StringTokenizer st=new StringTokenizer(line, ",");
        String name= st.nextToken();
        double price = Double.parseDouble(st.nextToken());
        item = new MenuDataModel(name, 0, price);
        return item;
    }

    public List<MenuDataModel> getMenu(){
        try {
            readMenu();//create a new menu for each table, on request
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
        return listMenu;
    }
}
