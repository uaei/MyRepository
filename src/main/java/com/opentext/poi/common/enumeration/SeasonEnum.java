package com.opentext.poi.common.enumeration;

public enum SeasonEnum {

    MON_DAY(1,"星期一"),
    TUES_DAY(2,"星期二"),
    WEDNES_DAY(3,"星期三"),
    THURS_DAY(4,"星期四"),
    FRI_DAY(5,"星期五"),
    SATUR_DAY(6,"星期六"),
    SUN_DAY(7,"星期天");

    private int code;
    private String msg;

    SeasonEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public static SeasonEnum of(){

        for (SeasonEnum seasonEnum : SeasonEnum.values()) {

            int code1 = seasonEnum.getCode();
            String msg1 = seasonEnum.getMsg();
            System.out.println("msg1====="+msg1+"---------------"+"code1====="+code1);


            String msg = seasonEnum.msg;
            int code = seasonEnum.code;
            System.out.println("msg====="+msg+"-------------------"+"code====="+code);
        }
        return null;
    }


    public static SeasonEnum getCode(String value){

        if (null == value){
            return null;
        }

        for (SeasonEnum seasonEnum : SeasonEnum.values()) {
            if (value.equals(seasonEnum.getMsg())){
                return seasonEnum;
            }
        }
        return null;
    }


}
