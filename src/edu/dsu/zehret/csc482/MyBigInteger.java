package edu.dsu.zehret.csc482;

public class MyBigInteger {
    private String value = new String();

    public MyBigInteger() {
        this.value = new String("0");
    }

    public MyBigInteger(String value) {
        this.value = new String(value);
    }

    public String Value() {
        return value;
    }

    public String AbbreviatedValue() {
        if (value.length() < 12)
            return Value();
        else {
            return value.substring(0, 5) + ".." + value.substring(value.length() - 5, value.length());
        }
    }

    /**
     * @see https://leetcode.com/problems/add-strings/discuss/878119/Simple-Java-solution
     * @param x
     * @return MyBigInteger sum
     */
    public MyBigInteger Plus(MyBigInteger x) {
        String num1 = x.value;
        String num2 = this.value;

        int i = num1.length() - 1, j = num2.length() - 1;
        int sum = 0, carry = 0;
        StringBuilder sb = new StringBuilder();
        while (i >= 0 || j >= 0 || carry != 0) {
            sum = carry;
            if (i >= 0) {
                sum += num1.charAt(i) - '0';
                i--;
            }
            if (j >= 0) {
                sum += num2.charAt(j) - '0';
                j--;
            }
            sb.append(sum % 10);
            carry = sum / 10;
        }
        return new MyBigInteger(sb.reverse().toString());
    }

    /***
     * @see https://leetcode.com/problems/multiply-strings/discuss/841005/Straightforward-Java-Solution-Simulation
     * @param x
     * @return
     */
    public MyBigInteger Times(MyBigInteger x) {
        String num1 = x.value;
        String num2 = this.value;

        int n = num1.length();
        int m = num2.length();
        int[] ans = new int[n + m];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                int subres = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
                int p1 = i + j;
                int p2 = i + j + 1;
                subres += ans[p2];
                ans[p1] += subres / 10;
                ans[p2] = subres % 10;
            }
        }
        StringBuilder sb = new StringBuilder();
        boolean started = false;
        for (int i = 0; i < ans.length; i++) {
            if (ans[i] != 0) {
                started = true;
            }
            if (started) {
                sb.append(ans[i]);
            }
        }
        return new MyBigInteger(sb.length() == 0 ? "0" : sb.toString());
    }
    // y.greaterThan(x)
    public boolean GreaterThan(MyBigInteger x) {
        if(x.value.equals(this.value)) return true;
        if(x.value.length() > this.value.length()) {
            return false;
        } else if(x.value.length() < this.value.length()) {
            return true;
        } else {
            for(int i = 0; i < x.value.length(); i++) {
                int xVal = x.value.charAt(i) - '0';
                int yVal = this.value.charAt(i) - '0';
                if(xVal == yVal) {
                    continue;
                } else {
                    if(xVal < yVal) {
                        return true;
                    } else if(xVal > yVal) {
                        return false;
                    }
                }
            }
        }
        //Will not be reached but required to make the compiler happy.
        return false;
    }
    public boolean LessThan(MyBigInteger x) {
        if(x.value.equals(this.value)) return true;
        if(x.value.length() > this.value.length()) {
            return true;
        } else if(x.value.length() < this.value.length()) {
            return false;
        } else {
            for(int i = 0; i < x.value.length(); i++) {
                int xVal = x.value.charAt(i) - '0';
                int yVal = this.value.charAt(i) - '0';
                if(xVal == yVal) {
                    continue;
                } else {
                    if(xVal < yVal) {
                        return false;
                    } else if(xVal > yVal) {
                        return true;
                    }
                }
            }
        }
        //Will not be reached but required to make the compiler happy.
        return false;
    }
    public boolean Equals(MyBigInteger x) {
        return this.value.equals(x.value);
    }
}