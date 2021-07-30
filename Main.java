package com.company;


import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws Exception {

                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

                System.out.println("Какой магазин будем парсить? ");
                System.out.println("1.Регард, 2. Плеер.ру");
               int line=0;
                try{
               line =Integer.parseInt(reader.readLine());}catch (NumberFormatException e){
                   }
                int result =0;
                if (line>3 || line<=0){
                    System.out.println("Введите значение от 1 до 3!");
                    main(null);
                }
                else result = line;


                if(result==1){
                    regard.csvWriter();
                }
                else if(result==2){
                    Pleer.csvWriter();
                }




        }}















