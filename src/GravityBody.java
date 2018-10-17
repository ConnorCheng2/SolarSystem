package com.company;

/*
 *
 * @Author Chenliang Tian
 * @ date Oct 16 2018
 *
 */

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class GravityBody {
    private String name;
    private double mass;
    private double diam;
    private GravityBody orbits;
    private double meanDistance;

    public GravityBody(String name, double mass, double diam, double peri, double aphe){
        this.name = name;
        this.mass = mass;
        this.diam = diam;
        this.orbits = null;
        this.meanDistance = (peri + aphe)/2;
    }

    public boolean isOrbits(String s){
        return s.equals(name);
    }
    public void setOrbits(GravityBody orbits){
        this.orbits = orbits;
    }

    public String toString(){
        if(orbits == null) {
            return "Name: " + name + ", Mass(kg):" + mass + ", Diam(m):" + diam + ", Orbits:" + "NaN" + ", MeanDistance(m):" + meanDistance + "\n";
        }else {
            return "Name: " + name + ", Mass(kg):" + mass + ", Diam(m):" + diam + ", Orbits:" + orbits.name + ", MeanDistance(m):" + meanDistance+ "\n";
        }
    }
}

class readData {
    static ArrayList<GravityBody> List;

    public void findOrbits(GravityBody gb, String orbits){
        // find and set the orbits
        int size = List.size();
        if(orbits.equals("NaN")) {
            return;
        }
        for(int i = 0; i < size; i++) {
            if (List.get(i).isOrbits(orbits)) {
                gb.setOrbits(List.get(i));
            }
        }
    }

    public readData() throws IOException {
        Scanner c = new Scanner(new BufferedReader(new FileReader("src/resource/solarsystem.dat")));

        String name = null, orbits = null;
        double[] info = new double[4];
        c.nextLine();
        while (c.hasNextLine()) {    // traverse
            for (int i = 0; i < 6; i++) {
                if (i == 0) {
                    name = c.next();
                } else if (i == 1) {
                    orbits = c.next();
                } else {
                    info[i - 2] = c.nextDouble();
                }
            }
            GravityBody gb = new GravityBody(name, info[0], info[1], info[2], info[3]);
            List.add(gb);
            findOrbits(gb, orbits);
            c.nextLine();
        }
    }

    public static void main(String[] args)throws IOException {
        List = new ArrayList<>();
        readData a = new readData();
        System.out.println(List);
    }
}