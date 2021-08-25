package com.company;

import com.opencsv.CSVWriter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class regard   {
     static void csvWriter() throws Exception {
         BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
         System.out.println("Введите ссылку на товар в Регарде: ");
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



        String price_id = doc.getElementById(prop.getProperty("Price tag","forCalc_nal")).text();
        float price = Integer.parseInt(price_id);


        String title_id = doc.getElementById(prop.getProperty("Header tag","goods_head")).text();

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

