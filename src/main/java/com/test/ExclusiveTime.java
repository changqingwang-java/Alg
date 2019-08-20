package com.test;

import java.util.*;

public class ExclusiveTime {
    public static int[] exclusiveTime(int n, List<String> logs) {
        int[] result = new int[n];

        for(int i = 0; i < result.length; i++){
            result[i] = 0;
        }

        int maxEnd = Integer.valueOf(logs.get(logs.size()-1).split(":")[2]);

        Stack<String> stringStack = new Stack<String>();

        TreeSet<String> treeSet = new TreeSet<String>(new Comparator<String>() {
            public int compare(String o1, String o2) {
                String[] sarr1 = o1.split(":");
                String[] sarr2 = o2.split(":");

                if(Integer.valueOf(sarr1[0]).intValue() < Integer.valueOf(sarr2[0]).intValue()){
                    return -1;
                }
                else if(Integer.valueOf(sarr1[0]).intValue() == Integer.valueOf(sarr2[0]).intValue()){
                    if(Integer.valueOf(sarr1[1]).intValue() < Integer.valueOf(sarr2[1]).intValue()){
                        return -1;
                    }
                    else if(Integer.valueOf(sarr1[1]).intValue() == Integer.valueOf(sarr2[1]).intValue()){
                        return 0;
                    }
                    else{
                        return 1;
                    }
                }
                else{
                    return 1;
                }
            }
        });


        for(int i = 0; i < logs.size(); i++){
            String s = logs.get(i);

            if(s.contains("start")){
                stringStack.push(s);
            }
            else{
                String[] sPop = stringStack.pop().split(":");
                String[] sArr = s.split(":");

                int start = Integer.valueOf(sPop[2]);
                int end = Integer.valueOf(sArr[2]);

                int sum = end - start + 1;

                SortedSet<String> times = treeSet.subSet(start + ":0", end + ":" + maxEnd);

                int lastEnd = -1;

                List<String> timeArr = new ArrayList<String>();

                for(String time: times){
                    String[] tempArr = time.split(":");

                    int tempTime = -1;

                    if(lastEnd == Integer.valueOf(tempArr[0]).intValue()){
                        tempTime = Integer.valueOf(tempArr[1]).intValue() - Integer.valueOf(tempArr[0]).intValue();
                    }
                    else {
                        tempTime = Integer.valueOf(tempArr[1]).intValue() - Integer.valueOf(tempArr[0]).intValue() + 1;
                    }

                    sum -= tempTime;

                    lastEnd = Integer.valueOf(tempArr[1]).intValue();

                    timeArr.add(time);
                }

                if(sum > 0 ){
                    result[Integer.valueOf(sArr[0])] += sum;
                }

                if(!timeArr.isEmpty()) {
                    for(String sss:timeArr) {
                        treeSet.remove(sss);
                    }
                }

                treeSet.add(start + ":" + end);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();

        String[] ss = {"0:start:0","1:start:5","2:start:8","3:start:12","4:start:15","5:start:19","6:start:22","7:start:24","8:start:27","6:start:32","6:end:35","8:end:39","2:start:40","5:start:42","5:end:46","2:end:49","7:end:52","7:start:54","7:end:55","0:start:59","1:start:64","1:end:65","0:end:68","2:start:71","7:start:75","6:start:79","6:start:81","6:end:85","6:end:86","7:start:88","7:end:92","2:start:97","2:end:102","1:start:105","7:start:107","8:start:111","8:end:115","7:end:118","1:end:121","7:end:122","2:end:126","6:end:129","5:end:130","4:start:132","4:end:133","4:end:135","3:end:140","1:start:145","1:end:147","2:end:148","1:end:151","2:start:156","1:start:160","4:start:164","2:start:165","7:start:169","7:end:172","2:end:175","4:end:179","1:start:182","4:start:183","4:end:184","1:end:186","1:end:187","2:end:190","0:end:192"};

        for(String s : ss){
            list.add(s);
        }

        int[] exclusiveTime = exclusiveTime(9, list);

        for(int i = 0; i < exclusiveTime.length; i++){
            System.out.println(exclusiveTime[i]);
        }
    }
}
