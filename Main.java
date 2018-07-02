package hamming;

import java.util.*;
import java.lang.*;

import java.util.Scanner;


public class Main {

    public static void main(String[] args){

        System.out.println("If generates press 1 or if correction press 2.........");

        Scanner s = new Scanner(System.in);

        int select = s.nextInt();

        if(select == 1) {

            Generator ob = new Generator();
            ob.generates();
        }
        else{
            Correction obj = new Correction();
            obj.check();
        }
    }

}