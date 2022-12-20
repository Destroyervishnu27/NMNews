package com.nmnews.nmnewsagency.adapter;

import com.nmnews.nmnewsagency.model.ChildPerformance;
import com.intrusoft.sectionedrecyclerview.Section;

import java.util.List;

public class SectionHeaderPerformnce1 implements Section<ChildPerformance>, Comparable<SectionHeaderPerformnce1> {

    List<ChildPerformance> childList;
    String sectionText;
    int index;

    public SectionHeaderPerformnce1(List<ChildPerformance> childList, String sectionText, int index) {
        this.childList = childList;
        this.sectionText = sectionText;
        this.index = index;
    }

    @Override
    public List<ChildPerformance> getChildItems() {
        return childList;
    }

    public String getSectionText() {
        return sectionText;
    }

    @Override
    public int compareTo(SectionHeaderPerformnce1 another) {
        if (this.index > another.index) {
            return -1;
        } else {
            return 1;
        }
    }
}