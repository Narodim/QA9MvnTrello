package org.example.util;

import org.testng.annotations.DataProvider;
import org.jetbrains.annotations.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;



public class DataProviders {
    @DataProvider
    public static Iterator<Object[]> loginPositive() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(
                DataProviders.class
                        .getResourceAsStream("/loginPositive.data")));

        List<Object[]> userData = new ArrayList<>();
        String line = in.readLine();
        while (line != null) {
            userData.add(line.split(";"));
            line = in.readLine();
        }
        in.close();
        return userData.iterator();
    }

    @DataProvider
    public static Iterator<Object[]> newListCreating() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(
                DataProviders.class
                        .getResourceAsStream("/newListCreating.data")));

        List<Object[]> userData = new ArrayList<>();
        String line = in.readLine();
        while (line != null) {
            userData.add(line.split(";"));
            line = in.readLine();
        }
        in.close();
        return userData.iterator();
    }

    @DataProvider
    public static Iterator<Object[]> newBoardCreating() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(
                DataProviders.class
                        .getResourceAsStream("/newBoardCreating.data")));

        List<Object[]> userData = new ArrayList<>();
        String line = in.readLine();
        while (line != null) {
            userData.add(line.split(";"));
            line = in.readLine();
        }
        in.close();
        return userData.iterator();
    }




    @DataProvider
    public static Iterator<Object[]> newCardCreating() {
        List<Object[]> data = new ArrayList<>();
        data.add(new Object[]{"first"});
        data.add(new Object[]{"second"});
        data.add(new Object[]{"third"});

        return data.iterator();
    }

    @DataProvider
    public static Iterator<Object[]> negativeLoginWithoutRandom() {
        List<Object[]> data = new ArrayList<>();
        data.add(new Object[]{"","iuasgdfsudf77","Missing email"});
        data.add(new Object[]{"secondmail.ru","77489thhh","There isn't an account for this username"});
        data.add(new Object[]{"third3980@yahoo.com","sdfgsdfg4545", "There isn't an account for this email"});

        return data.iterator();
    }



    @DataProvider
    public Iterator<Object[]> negativeLoginWithRandom1() {
        List<Object[]> data = new ArrayList();

        for (int i = 0; i < 2; ++i) {
            data.add(new Object[]{this.generateRandomLogin(), this.generateRandomPassword()});
        }
        return data.iterator();
    }

    private Object generateRandomLogin() {
        String login = new Random().ints(12,33,122).collect(StringBuilder::new,
                StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        return login;
    }

    private Object generateRandomPassword() {
        String login = new Random().ints(12,33,122).collect(StringBuilder::new,
                StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        return login;
    }


    @DataProvider
    public Iterator<Object[]> negativeLoginWithRandom2() {
        List<Object[]> data = new ArrayList();

        for (int i = 0; i < 3; ++i) {
            data.add(new Object[]{this.genRandomString(6,10), this.generateRandomString2(3,8)});
        }

        return data.iterator();
    }

    public String genRandomString(int min,int max) {
        String str = "";
        int length = 0;
        int i = 0;
        int number;
        if (min > max) return "";
        Random gen = new Random();
        length = min + gen.nextInt(max - min +1);
        do {
            number = '0' + gen.nextInt('z' - '0' + 1);
            if ((number < 58 || number > 96 || (number > 64 && number < 91)))
            {
                str = str + (char) number;
                i++;
            }
        }
        while (i < length);

        return str;
    }

    private String generateRandomString2(int minSymbolsCount, int maxSymbolsCount) {
        String symbol = "0123456789zxcvbnmasdfghjklqwertyuiopZXCVBNMASDFGHJKLQWERTYUIOP";
        char[] arr = symbol.toCharArray();
        Random gen = new Random();
        int countOfSimbols = minSymbolsCount + gen.nextInt(maxSymbolsCount - minSymbolsCount);
        String pass = "";
        for (int i = 0; i < countOfSimbols; i++) {
            int num = gen.nextInt(62);
            pass += arr[num];
        }
        return pass;
    }



   }


