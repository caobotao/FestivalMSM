package com.example.caobotao.festivalmsm.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by caobotao on 15/12/25.
 */
public class FestivalLab {
    private static FestivalLab mInstance;
    private List<Festival> mFestivals = new ArrayList<Festival>();
    private List<Msg> mMsgs = new ArrayList<Msg>();

    public FestivalLab() {
        mFestivals.add(new Festival(1,"国庆节"));
        mFestivals.add(new Festival(2,"中秋节"));
        mFestivals.add(new Festival(3,"元旦"));
        mFestivals.add(new Festival(4,"春节"));
        mFestivals.add(new Festival(5,"端午节"));
        mFestivals.add(new Festival(6,"七夕节"));
        mFestivals.add(new Festival(7,"圣诞节"));
        mFestivals.add(new Festival(8,"儿童节"));

        mMsgs.add(new Msg(1,1,"天蓝蓝，草青青，国庆长假振人心。山秀秀，水清清，携手爱人去旅行。"));
        mMsgs.add(new Msg(2,1,"水在流，鱼在游，爱你不需要理由；风在吹，雨在下，很想抱你亲一下；天有情，地有情，国庆浪漫一下行不行？   "));
        mMsgs.add(new Msg(3,1,"九州日丽，国趋昌盛人趋富；四化春新，花爱阳春果爱秋！"));
        mMsgs.add(new Msg(4,1,"后面看你，像蔡国庆。侧面看你，像庾澄庆。正面看你，像西门庆，国庆快乐！"));
        mMsgs.add(new Msg(5,1,"天一片晴朗，花一朵芬芳；风悠悠飘荡，水幽幽流淌。唱一首好歌，送一份关怀，愿你愉快；写一条短信，送一句祝福，“10.1”快乐！"));
        mMsgs.add(new Msg(6,1,"亲爱的客户，刚有一笔10万美元的汇款按要求转入你的账户，请在国庆放假的第八日确认，否则转账自动作废。"));
        mMsgs.add(new Msg(7,1,"夏日悄悄的走了，秋日渐渐的近了，中秋慢慢的退了，国庆默默的来了，短信静静的发了，铃声响响的叫了，祝福真真的到了，问候浅浅的笑了：国庆快乐！"));
        mMsgs.add(new Msg(8,1,"国庆节之际，不想给你太多，只给你五千万：千万要快乐，千万要健康，千万要平安，千万要知足，千万莫忘记我！"));
        mMsgs.add(new Msg(9,1,"今天是个特殊的日子，干杯吧，朋友，让我们庆祝起来，载歌载舞，歌颂伟大的祖国，歌唱幸福的生活。衷心祝福你，国庆节快乐，幸福到永远！"));

    }

    public List<Festival> getFestivals() {
        return new ArrayList<>(mFestivals);
    }

    public Festival getFestivalById(int id) {
        for (Festival festival : mFestivals) {
            if (festival.getId() == id) {
                return festival;
            }
        }
        return null;
    }

    public List<Msg> getMsgsByFestivalId(int festivalId) {
        List<Msg> list = new ArrayList<Msg>();
        for (Msg msg : mMsgs) {
            if (msg.getFestivalId() == festivalId) {
                list.add(msg);
            }
        }
        return list;
    }

    public Msg getMsgByMsgId(int id) {
        for (Msg msg : mMsgs) {
            if (msg.getId() == id) {
                return new Msg(msg.getId(),msg.getFestivalId(),msg.getContent());
            }
        }
        return null;
    }

    public static FestivalLab getInstance() {
        if (mInstance == null) {
            synchronized (FestivalLab.class) {
                if (mInstance == null) {
                    mInstance = new FestivalLab();
                }
            }
        }
        return mInstance;
    }
}
