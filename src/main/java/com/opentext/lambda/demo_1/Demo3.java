package com.opentext.lambda.demo_1;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
 
public class Demo3 {
 
	public static void main(String[] args) {
		User user1 = new User(1, "u1", "shenyang", "hahaha");
		User user2 = new User(2, "u2", "shenyang", "gaga");
		User user3 = new User(3, "u3", "shanghai", "lala");
		User user4 = new User(4, "u4", "shanghai", "hahaha");

		User user5 = new User(5, "u5", "shenyang", "gaga");
		User user6 = new User(6, "u6", "shanghai", "hahaha");
		User user7 = new User(7, "u7", "beijing", "gaga");
		User user8 = new User(8, "u8", "beijing", "hahaha");
		User user9 = new User(9, "u9", "shenyang", "lala");

		List<User> lis = new ArrayList<>();
		lis.add(user1);
		lis.add(user2);
		lis.add(user3);
		lis.add(user4);

		List<User> lis2 = new ArrayList<>();
		lis2.add(user5);
		lis2.add(user6);
		lis2.add(user7);
		lis2.add(user8);
		lis2.add(user9);

		List<User> figures = Stream.of(lis, lis2).flatMap(u -> u.subList(0, 1).stream()).collect(Collectors.toList());
		figures.forEach(f -> System.out.println(f.toString()));
	}
 
}