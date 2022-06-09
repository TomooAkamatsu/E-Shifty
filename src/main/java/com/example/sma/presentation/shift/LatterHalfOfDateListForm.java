package com.example.sma.presentation.shift;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class LatterHalfOfDateListForm {

    private String date16st;
    private String date17nd;
    private String date18rd;
    private String date19th;
    private String date20th;
    private String date21th;
    private String date22th;
    private String date23th;
    private String date24th;
    private String date25th;
    private String date26th;
    private String date27th;
    private String date28th;
    private String date29th;
    private String date30th;
    private String date31th;

    public LatterHalfOfDateListForm(List<String> shiftPatternNameList, int flag) {
        this.date16st = shiftPatternNameList.get(0);
        this.date17nd = shiftPatternNameList.get(1);
        this.date18rd = shiftPatternNameList.get(2);
        this.date19th = shiftPatternNameList.get(3);
        this.date20th = shiftPatternNameList.get(4);
        this.date21th = shiftPatternNameList.get(5);
        this.date22th = shiftPatternNameList.get(6);
        this.date23th = shiftPatternNameList.get(7);
        this.date24th = shiftPatternNameList.get(8);
        this.date25th = shiftPatternNameList.get(9);
        this.date26th = shiftPatternNameList.get(10);
        this.date27th = shiftPatternNameList.get(11);
        this.date28th = shiftPatternNameList.get(12);

        if(flag==28) {
            this.date29th = "";
            this.date30th = "";
            this.date31th = "";
        }

        if(flag==29){
            this.date29th = shiftPatternNameList.get(13);
            this.date30th = "";
            this.date31th = "";
        }

        if(flag==30){
            this.date29th = shiftPatternNameList.get(13);
            this.date30th = shiftPatternNameList.get(14);
            this.date31th = "";
        }

        if(flag==31){
            this.date29th = shiftPatternNameList.get(13);
            this.date30th = shiftPatternNameList.get(14);
            this.date31th = shiftPatternNameList.get(15);
        }

    }


}
