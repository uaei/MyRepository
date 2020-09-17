package com.opentext.lambda.demo_1;

import java.util.ArrayList;
import java.util.List;
 
public class Demo1 {
 
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
		lis.add(user5);
		lis.add(user6);
		lis.add(user7);
		lis.add(user8);
		lis.add(user9);
		
		//List<User> lis1 =lis.stream().filter(a->a.getAdd().equals("shenyang")).collect(Collectors.toList());
		//lis1.forEach(a->System.out.println(UserOp.getInfo(a)));
		
		int numcount =lis.stream().mapToInt(a->a.getNum()).sum();
		System.out.println(numcount);
		
//		List<String> aa=lis.stream().map(a->UserOp.getname(a)).collect(Collectors.toList());
//		aa.forEach(a->System.out.println(a));
	}
 
}