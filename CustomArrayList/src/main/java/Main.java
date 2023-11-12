package main.java;

import java.util.ArrayList;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        CustomArrayList<Integer> list = new CustomArrayList<>();

        list.add(125);
        list.add(34);
        list.add(235);
        list.add(564);
        list.add(-232);
        list.add(98);
        list.add(89);
        list.add(123);
        System.out.println(list + " " + list.size());

        list.add(2, 3457);
        System.out.println(list + " " + list.size());

        list.remove(3);
        System.out.println(list + " " + list.size());

        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(9348);
        arrayList.add(89);
        arrayList.add(-9348);
        arrayList.add(-89);
        arrayList.add(13423);

        list.addAll(arrayList);
        System.out.println(list + " " + list.size());

        list.sort(Comparator.naturalOrder());
        System.out.println(list + " " + list.size());

        list.remove(Integer.valueOf(89));
        System.out.println(list + " " + list.size());


        CustomArrayList<String> listOfStrings = new CustomArrayList<>();

        listOfStrings.add("gfsd125");
        listOfStrings.add("34sadf");
        listOfStrings.add("jfasdf235");
        listOfStrings.add("asdfg564");
        System.out.println(listOfStrings + " " + listOfStrings.size());

        listOfStrings.sort(Comparator.naturalOrder());
        System.out.println(listOfStrings + " " + listOfStrings.size());

        listOfStrings.remove("34sadf");
        System.out.println(listOfStrings + " " + listOfStrings.size());
    }
}
