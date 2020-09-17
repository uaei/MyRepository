package com.opentext.mybatis;

import com.opentext.lambda.demo.*;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static com.opentext.lambda.demo.Streams.Status;
import static com.opentext.lambda.demo.Streams.Task;

/**
 * @Author GuYaWei
 * @created 2020/7/2 16:00
 * @description
 */

//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = MybatisPulsGeneratorApplication.class)
public class LambdaTest {


    @Test
    public void jdk8(){

        List<Integer> list = Arrays.asList(10, 5, 25, -15, 20);

        Runnable run = () -> {}; //无参数，无返回值

        //Supplier系列，英文翻译就是“供应者”，顾名思义：只产出，不收取。所以不接受任何参数，返回T类型结果。
        Supplier<String> supplier = () -> ""; //无参数，有返回值--->返回一个string类型的参数

        //Consumer系列与Function系列一样，有各种衍生接口，这里不一一列出了。不过都具备类似的特征：那就是不返回任何结果。
        Consumer<String> consumer = (t) -> {}; //有参数，无返回值--->接受一个string类型的参数

        //Function代表的是有参数，有返回值的函数。
        Function<String, Integer> function = (t) -> 1; //有参数，有返回值--->接受一个string类型的参数，返回一个string类型的参数

        //Predicate系列参数不固定，但是返回的一定是boolean类型。
        Predicate<String> predicate = (t) -> true; //有参数，有返回值--->接受一个string类型的参数，返回一个Boolean值

        Optional<List<Integer>> optionalIntegerList = Optional.ofNullable(list);
        Optional<String> optional = Optional.ofNullable(null);

        List<Integer> collect = list.stream().filter(i -> i > 5).collect(Collectors.toList());
        System.out.println(collect);

    }

    @Test
    public void testSortJdk8() {

        List<Integer> list = Arrays.asList(10, 5, 25, -15, 20);

        //jdk1.7写法
        Collections.sort(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        System.out.println(list);

        //jdk1.8写法
//       list.sort((o1,o2) -> {return o1 - o2;});
        list.sort((o1, o2) -> o1 - o2);
        System.out.println(list);
    }


    @Test
    public void testForEachJdk8() {
        List<Integer> list = Arrays.asList(10, 5, 25, -15, 20);
//        list.forEach((i) -> {
//            System.out.println(i);
//        });

//        list.forEach(i -> System.out.println(i));
        List<Integer> collect = list.stream().filter(e -> e > 15).collect(Collectors.toList());
        for (Integer integer : collect) {
            System.out.println(integer);
        }
//        list.forEach(System.out::println);
//
//        Optional<List<Integer>> optionalIntegerList = Optional.ofNullable(list);
//        System.out.println(optionalIntegerList.get());
//
//        Optional.ofNullable(list).get().forEach(System.out::println);
    }

    @Test
    public void testJdk8_01() {

        List<Map<String, Object>> list = Arrays.asList(
                Collections.singletonMap("bb", "李四"),
                Collections.singletonMap("bb", "李六"),
                Collections.singletonMap("aa", "张三")
        );
        System.out.println("list：：："+list);
        Object aa = Optional.ofNullable(list)
                .map(Collection::stream)
                .flatMap(Stream::findFirst)
                .map(m -> m.get("bb"))
                .orElse("王五");
        System.out.println(aa);
    }

    @Test
    public void testJdk8_02() {

       Runnable runnable = ()->{
           System.out.println("哇哇哇，好难啊！！！");
       };
//       runnable.run();
        new Thread(runnable).start();
    }


    @Test
    public void testJdk8_03() {

        // 定义一个局部变量
        int num = -1;
        Runnable r = () -> {
            // 在Lambda表达式中使用局部变量num，num会被隐式声明为final
            System.out.println(num);
        };
        new Thread(r).start();// -1

//        // 定义一个局部变量
//        int num = -1;
//        Runnable r = () -> {
//            // 在Lambda表达式中使用局部变量num，num会被隐式声明为final，不能进行任何修改操作
//            System.out.println(num++);
//        };
//        new Thread(r).start();//报错
    }

    @Test
    public void testJdk8_04() {

        List<Integer> list = Arrays.asList(1000, 2000, 3000);

        List<String> convert = CollectionUtil.convert(list, i -> Integer.toHexString(i));
        System.out.println("convert："+convert);

        List<String> convert2 = CollectionUtil.convert(convert, i -> i.toUpperCase());
        System.out.println("convert2："+convert2);

        //类的静态方法引用
        List<String> convert1 = CollectionUtil.convert(list, Integer::toHexString);
        System.out.println("convert1："+convert1);


        List<String> convert3 = CollectionUtil.convert(convert1, String::toUpperCase);
        System.out.println("convert3："+convert3);
    }

    @Test
    public void testJdk8_05() {

        List<Integer> list = Arrays.asList(1000, 2000, 3000);
        Integer num = 2000;
        List<Integer> convert = CollectionUtil.convert(list, i -> num.compareTo(i));
        System.out.println(convert);

        List<Integer> convert1 = CollectionUtil.convert(list, num::compareTo);
        System.out.println(convert1);
    }

    @Test
    public void testJdk8_06() {
        List<Integer> list = Arrays.asList(1000, 2000, 3000);
        List<Date> convert = CollectionUtil.convert(list, i -> new Date(i));
        convert.forEach(System.out::println);
        System.out.println("----------------------------------------------------");
        List<Date> convert1 = CollectionUtil.convert(list, Date::new);
        convert1.forEach(System.out::println);
    }

    @Test
    public void testJdk8_07() {
        Defaulable defaulable = DefaulableFactory.create(DefaultableImpl::new);
        System.out.println(defaulable.notRequired());

        Defaulable defaulable1 = DefaulableFactory.create(OverridableImpl::new);
        System.out.println(defaulable1.notRequired());
    }

    @Test
    public void testJdk8_08() {

//        Optional<String> optional = Optional.ofNullable(null);

        Optional<String> optional = Optional.ofNullable("我不是空的");
        System.out.println(optional);

        System.out.println("是否不为空?  "+optional.isPresent());
        System.out.println("你是不是空的? "+ optional.orElseGet(() -> "你是空的，我上场了"));
        System.out.println(optional.map(s -> s).orElse("你是空的，我上场了"));

    }

    @Test
    public void testJdk8_09(){

//        Task task = new Task(Status.OPEN, 5);
        List<Task> tasks = Arrays.asList(
                new Task(Status.OPEN, 5),
                new Task(Status.OPEN, 13),
                new Task(Status.CLOSED, 8));

        IntStream intStream = tasks.parallelStream().filter(task -> task.getStatus() == Status.OPEN).mapToInt(Task::getPoints);
        System.out.println(intStream.sum());

        Integer reduce = tasks.stream().parallel().map(task -> task.getPoints()).reduce(0, Integer::sum);
        System.out.println(reduce);

        Map<Status, List<Task>> map = tasks.stream().parallel().collect(Collectors.groupingBy(Task::getStatus));
        System.out.println(map);

        double totalPoints = 26.0;
        List<String> collect = tasks
                .stream()
                .mapToInt(Task::getPoints)
                .asLongStream()
                .mapToDouble(points -> points / totalPoints)
                .boxed()
                .mapToLong(weight -> (long) (weight * 100))
                .mapToObj(percentage -> percentage + "%")
                .collect(Collectors.toList());
        System.out.println(collect);
    }

    @Test
    public void testJdk8_10(){

        Path path = new File("C:/Users/opentext/Desktop/jdk1.8新特性.java").toPath();
        try {
            Stream<String> lines = Files.lines(path, StandardCharsets.UTF_8);
            lines.onClose(() -> {}).forEach((consumer)->{
                System.out.println(consumer);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testJdk8_11(){
//        Function<Integer,Integer> test = (i) -> {return i+1;};
        Function<Integer,Integer> test = i->i+1;
        Integer apply = test.apply(5);
        System.out.println(apply);
    }

    @Test
    public void testJdk8_12(){
        List<String> list = Arrays.asList("haha", "hehe", "lala", "huahua");
        Function<List, List> identity = Function.identity();
        identity.apply(list).stream().forEach(System.out::println);

        Runnable runnable = () -> {
        };
    }


    @Test
    public void testJdk8_13(){


    }

}
