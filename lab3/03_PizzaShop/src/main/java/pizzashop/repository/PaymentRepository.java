package pizzashop.repository;

import pizzashop.model.Payment;
import pizzashop.model.PaymentType;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class PaymentRepository {
    private static String filename = "data/payments.txt";
    private List<Payment> paymentList;

    public PaymentRepository(){
        this.paymentList = new ArrayList<>();
        readPayments();
    }

    private void readPayments(){
        File file = new File(filename);
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            String line = null;
            while((line=br.readLine())!=null){
                Payment payment=getPayment(line);
                paymentList.add(payment);
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Payment getPayment(String line){
        Payment item=null;
        if (line==null|| line.equals("")) return null;
        StringTokenizer st=new StringTokenizer(line, ",");
        int tableNumber= Integer.parseInt(st.nextToken());
        String type= st.nextToken();
        double amount = Double.parseDouble(st.nextToken());
        item = new Payment(tableNumber, PaymentType.valueOf(type), amount);
        return item;
    }

    public void add(Payment payment){

        try
        {
            if (payment.getAmount() < 0 || payment.getAmount() > 1000)
            {
                throw new Exception("Amount must be between 0 and 1000.");
            }
            if (payment.getTableNumber() < 1 || payment.getTableNumber() > 8)
            {
                throw new Exception("Table number must be between 1 and 8.");
            }
            paymentList.add(payment);
            System.out.println("ADDED");
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

        try {
            writeAll();
        }
        catch (IOException e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    public List<Payment> getAll(){
        return paymentList;
    }

    public void writeAll() throws IOException {
        File file = new File(filename);

        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(file));
            for (Payment p:paymentList) {
                System.out.println(p.toString());
                bw.write(p.toString());
                bw.newLine();
            }
            bw.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
            System.out.println("File exception");
            System.out.println(e.getMessage());
        }
        finally
        {
            assert bw != null;
            bw.close();
        }
    }

}