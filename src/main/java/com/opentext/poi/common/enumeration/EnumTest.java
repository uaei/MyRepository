package com.opentext.poi.common.enumeration;

public class EnumTest {

    public static void main(String[] args) {

//        String s = SeasonEnum.MON_DAY.toString();
//        System.out.println("sssssssss::::::"+s);

        SeasonEnum seasonEnum = SeasonEnum.valueOf("MON_DAY");

        System.out.println("seasonEnum: "+seasonEnum.toString());
        System.out.println("code: "+seasonEnum.getCode()+"---"+"message: "+seasonEnum.getMsg());

        final SeasonEnum[] values = SeasonEnum.values();
        for (SeasonEnum value : values) {

            final int code = value.getCode();
            final String msg = value.getMsg();
//            System.out.println("code:"+code+"----------------------"+"msg:"+msg);
        }

//        SeasonEnum.of();

        final SeasonEnum monday = SeasonEnum.getCode("星期一");

//        System.out.println(monday.getCode()+monday.getMsg());
    }

}
