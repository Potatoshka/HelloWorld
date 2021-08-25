package com.company;

import com.opencsv.CSVWriter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class Pleer {
    static void csvWriter() throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Введите ссылку на товар в Плеере: ");
        Document doc = null;
        try {
            String line = reader.readLine();
            doc = Jsoup.connect(line).userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6").get();}
        catch (IllegalArgumentException e){System.out.println("Товар не найден");
            Main.main(null);}

        Properties prop = new Properties();

        try{
            prop.load(new FileInputStream(new File("Settings.ini")));
        }catch (FileNotFoundException e){
            OutputStream str = new FileOutputStream("Settings.ini");
            prop.setProperty("Price_tag","forCalc_nal");
            prop.setProperty("Header_tag","goods_head");
            prop.store(str,null);
        }



        String price_id = doc.getElementsByClass(prop.getProperty("Price tag","price")).text();
     char [] price_char = price_id.toCharArray();
     char f ='р';
     int count = 0;
     int a = price_char.length;
     char [] filter = new char[a];
     for(int i=0;i<price_char.length;i++){
         if(price_char[i]!=f){
             filter[i]=price_char[i];
             System.out.println(price_char[i]);
         }
         else {
            count =i;
             System.out.println(count);
           break;
             }
     }
     char [] noEmpty = new char[count];
     for(int j=count-1;j>=0;j--){
       noEmpty[j]=filter[j];
         System.out.println(noEmpty[j]);
     }

     String price_f=new String(noEmpty);
     String superFilteredPrice = price_f.replaceAll("\\s","");


        System.out.println(superFilteredPrice);
       float price = Integer.parseInt(superFilteredPrice);


        String title_id = doc.getElementsByClass(prop.getProperty("Header tag","product_title")).text();

        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("E yyyy.MM.dd 'и время' hh:mm:ss a zzz");
        String currency = "RUR";
        String text = title_id+" стоимость: " + (int) price +" "+currency+ " дата: "+format.format(date);

        String csv ="parts.csv";
        CSVWriter writer = new CSVWriter(new FileWriter(csv,true));
        String [] recorder = new String[]{text};
        writer.writeNext(recorder);
        System.out.println("Найдено: "+title_id);
        System.out.println("Файл csv готов");
        writer.close();

}}
