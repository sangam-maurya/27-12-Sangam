package com.main;

import java.util.*;
import java.util.stream.*;

public class ReverseVowels {
    public static void main(String[] args) {
        String str = "comcast";
        System.out.println(" input is " + str);
        List<Character> vowels = str.chars().filter(c -> "aeiouAEIOU".indexOf(c) != -1).mapToObj(c -> (char) c).collect(Collectors.toList());
        String result = IntStream.range(0, str.length()).mapToObj(i -> "aeiouAEIOU".indexOf(str.charAt(i)) != -1 ? vowels.remove(vowels.size() - 1) : str.charAt(i))
                .map(String::valueOf).collect(Collectors.joining());
        System.out.print(" ");
        System.out.println("this is output " +  result);
    }
}