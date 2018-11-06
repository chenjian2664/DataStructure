package streamtest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by chenjian
 * 2018/8/11 16:26
 */
public class StreamTest {
    public static void main(String[] args) {
        Stream<String> s = Stream.of("i", "love", "programing");
//        String str = s.collect(Collectors.joining(" ", " {", "}"));
//        System.out.println(str);
//        ArrayList<String> list = s.distinct().map(s1 -> s1 + 3).
//                collect(Collectors.toCollection(ArrayList::new));
//        list.forEach(s1 -> System.out.print(s1 + " "));
//        Map<Integer, String> map = s.map(s1 -> s1 + 1).
//                collect(Collectors.toMap(String::length, Function.identity() ));
        ArrayList<String> list = s.collect(Collectors.toCollection(ArrayList::new));
        for (String string : list) {
            System.out.println(string);
        }

    }
}
