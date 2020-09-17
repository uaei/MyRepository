package com.opentext.utils.uuid;

import org.apache.commons.lang3.StringUtils;

/**
 * sgm id 生成
 *
 * @author xhuang
 */
public class SgmIdKit {

    /**
     * 大号
     */
    private String big;
    /**
     * 中间号
     */
    private String mid;
    /**
     * 子号
     */
    private String sub;

    public SgmIdKit() {
        this("00", "000", "000");
    }

    public SgmIdKit(String big, String mid, String sub) {
        validate(big, "big can not be blank.");
        validate(mid, "mid can not be blank.");
        validate(sub, "sub can not be blank.");
        this.big = big;
        this.mid = mid;
        this.sub = sub;
    }

    public SgmIdKit(String sgmId, int bigLength, int midLength, int subLength) {
        validate(sgmId, "sgmId can not be blank.");
        this.big = sgmId.substring(0, bigLength);
        this.mid = sgmId.substring(bigLength, bigLength + midLength);
        this.sub = sgmId.substring(bigLength + midLength, bigLength + midLength + subLength);
    }

    public static SgmIdKit ofBy2_3_3(String value) {
        return of(value, 2, 3, 3);
    }

    public static SgmIdKit of(String big, String mid, String sub) {
        return new SgmIdKit(big, mid, sub);
    }

    public static SgmIdKit of(String sgmId, int bigLength, int midLength, int subLength) {
        return new SgmIdKit(sgmId, bigLength, midLength, subLength);
    }

    /**
     * 更新大号
     * 注：更新大号会使中间号和子号从 0 开始
     *
     * @return
     */
    public SgmIdKit nextBig() {
        this.big = StringUtils.leftPad(next(this.big), this.big.length(), "0");
        this.mid = StringUtils.leftPad("", this.mid.length(), "0");
        this.sub = StringUtils.leftPad("", this.sub.length(), "0");
        return this;
    }

    /**
     * 更新中间号
     * 注：更新中间会使子号从 0 开始
     *
     * @return
     */
    public SgmIdKit nextMid() {
        this.mid = StringUtils.leftPad(next(this.mid), this.mid.length(), "0");
        this.sub = StringUtils.leftPad("", this.sub.length(), "0");
        return this;
    }

    /**
     * 更新子号
     *
     * @return
     */
    public SgmIdKit nextSub() {
        this.sub = StringUtils.leftPad(next(this.sub), this.sub.length(), "0");
        return this;
    }


    /**
     * 重置大号
     *
     * @return
     */
    public SgmIdKit reBig() {
        this.big = StringUtils.leftPad("", this.big.length(), "0");
        return this;
    }

    /**
     * 重置中间号
     *
     * @return
     */
    public SgmIdKit reMid() {
        this.mid = StringUtils.leftPad("", this.mid.length(), "0");
        return this;
    }

    /**
     * 重置子号
     *
     * @return
     */
    public SgmIdKit reSub() {
        this.sub = StringUtils.leftPad("", this.sub.length(), "0");
        return this;
    }

    /**
     * 复制
     *
     * @return
     */
    public SgmIdKit copy() {
        return new SgmIdKit(this.big, this.mid, this.sub);
    }

    @Override
    public String toString() {
        return big + mid + sub;
    }

    protected String next(String value) {
        return String.valueOf(Integer.valueOf(value) + 1);
    }

    protected void validate(String cs, String message) {
        if (cs == null || cs.trim().isEmpty()) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void main(String[] args) {
        SgmIdKit si = new SgmIdKit();
        System.out.println(si);
        System.out.println(si.nextBig());
        System.out.println(si.nextMid());
        System.out.println(si.nextSub());
        System.out.println(si.nextBig().nextMid().nextSub());

        System.out.println("--------------");

        SgmIdKit k = new SgmIdKit("10010100", 2, 3, 3);
        System.out.println(k);
        System.out.println(k.nextSub());
    }

}
