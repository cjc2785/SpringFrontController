package com.ss.lms.secret;

public class  GenerateID {


    public int randomID()
    {
        String randomID0 = numberConcat(randomNum1(),randomNum2(),randomNum3());
        String randomID1 = numberConcat(randomNum1(),randomNum2(),randomNum3());
        String randomID2 = numberConcat(randomNum1(),randomNum2(),randomNum3());
        String randomID3 = numberConcat(randomNum1(),randomNum2(),randomNum3());
        String randomID4 = numberConcat(randomNum1(),randomNum2(),randomNum3());
        String randomID5 = numberConcat(randomNum1(),randomNum2(),randomNum3());
        String[] myArr  = {randomID0,randomID1,randomID2,randomID3,randomID4,randomID5};

        int numberToReturn =(int) (Math.random() * 6 -1);
        return Integer.parseInt(myArr[numberToReturn]);
    }



    private int randomNum1()
    {
        int firstNum = (int) (Math.random() * 9999);
        if(firstNum < 9000)
        {
            firstNum = (int) (Math.random() * 9999);
        }
        if (firstNum > 1000)
        {
            firstNum = (int) (Math.random() * 9999);
        }
        return  firstNum;
    }

    private String numberConcat(int num1, int num2, int num3)
    {
        return (num1 + String.valueOf(num2) + num3);
    }
    private int randomNum2()
    {
        return (int) (Math.random() * 99);
    }
    private int randomNum3()
    {
        return (int) (Math.random() * 9);
    }

}
