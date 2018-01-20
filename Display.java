package msd;

import java.util.Scanner;

class ComplexCalculation {

    private double real;
    private double imaginary;
    //set default complex number as 0+0i
    public ComplexCalculation() {
        this.real = 0;
        this.imaginary = 0;
    }
    //set the complex number need for calulations
    public ComplexCalculation(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }
    //add two numbers that passes through two objects
    public ComplexCalculation addComplex(ComplexCalculation z1, ComplexCalculation z2) {
        this.real = z1.real + z2.real;
        this.imaginary = z1.imaginary + z2.imaginary;
        return new ComplexCalculation(this.real, this.imaginary);
    }
    //subtract two numbers that passes through two objects
    public ComplexCalculation subtractComplex(ComplexCalculation z1, ComplexCalculation z2) {
        this.real = z1.real - z2.real;
        this.imaginary = z1.imaginary - z2.imaginary;
        return new ComplexCalculation(this.real, this.imaginary);
    }
    //multiply two numbers that passes through two objects
    public ComplexCalculation multiplyComplex(ComplexCalculation z1, ComplexCalculation z2) {
        this.real = z1.real * z2.real - z1.imaginary * z2.imaginary;
        this.imaginary = z1.real * z2.imaginary + z1.imaginary * z2.real;
        return new ComplexCalculation(this.real, this.imaginary);
    }
    //divide two numbers that passes through two objects
    public ComplexCalculation divideComplex(ComplexCalculation z1, ComplexCalculation z2) throws Exception {
        ComplexCalculation numerator = multiplyComplex(z1, z2.conjugate());
        double denominator = Math.pow(z2.real, 2) + Math.pow(z2.imaginary, 2);
        if (denominator == 0) {
            throw new ArithmeticException("Cannot divide by zero");
        } else {
            return new ComplexCalculation(numerator.real / denominator, numerator.imaginary / denominator);
        }
    }
    //get the conjugate for a complex number
    public ComplexCalculation conjugate() {
        return new ComplexCalculation(this.real, -this.imaginary);
    }
    //passes real part of complex number
    public float getReal() {
       
        return (float)this.real;
    }
    //passes imaginary part of complex number
    public float getImaginary() {
        return (float)this.imaginary;
    }
}

class Display {

    private static ComplexCalculation z0;

    public static void main(String[] args) {
        GetComplex testing = new GetComplex();
        try {
            //set input complex numbers
            testing.getNumber();
            //cbeck the input function and call to relevent method
            if (testing.getFunction() == '+') {
                z0 = new ComplexCalculation().addComplex(testing.getFirstNum(), testing.getSecondNum());
                System.out.println("Answer is: "+z0.getReal() + "+" + z0.getImaginary() + "i");
            } else if (testing.getFunction() == '-') {
                z0 = new ComplexCalculation().subtractComplex(testing.getFirstNum(), testing.getSecondNum());
                System.out.println("Answer is :"+z0.getReal() + "+" + z0.getImaginary() + "i");
            } else if (testing.getFunction() == '*') {
                z0 = new ComplexCalculation().multiplyComplex(testing.getFirstNum(), testing.getSecondNum());
                System.out.println("Answer is :"+z0.getReal() + "+" + z0.getImaginary() + "i");
            } else {
                z0 = new ComplexCalculation().divideComplex(testing.getFirstNum(), testing.getSecondNum());
                System.out.println("Answer is :"+String.format("%.3f", z0.getReal()) + "+" + String.format("%.3f", z0.getImaginary()) + "i");
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }
}

class GetComplex {

    private ComplexCalculation z1;
    private ComplexCalculation z2;
    private char function;
    private double real;
    private double imaginary;
    
    //get complex number as a input
    public void getNumber() throws Exception {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the first number in format 4 + -5i");
        String a1 = scan.nextLine();

        checkValidation(a1);
        z1 = new ComplexCalculation(real, imaginary);

        System.out.println("Enter the second number in same format");
        String a2 = scan.nextLine();
        checkValidation(a2);
        z2 = new ComplexCalculation(real, imaginary);

        System.out.println("Enter the function you need. +,/,-,* ");
        String a3 = scan.next();
        if (a3.charAt(0) == '+') {
            function = '+';
        } else if (a3.charAt(0) == '-') {
            function = '-';
        } else if (a3.charAt(0) == '/') {
            function = '/';
        } else if (a3.charAt(0) == '*') {
            function = '*';
        } else {
            throw new Exception("enter a correct function");
        }
    }
    //check whether complex number is in correct format
    public void checkValidation(String num) throws Exception {

        if (num.charAt(num.length() - 1) == 'i') {
            num = num.substring(0, num.length() - 1);

            String[] b = num.split("\\+");
            if (b.length == 2) {
                real = Double.parseDouble(b[0]);
                imaginary = Double.parseDouble(b[1]);
            } else {
                throw new Exception("give a correct number ex. 4+ -5i");
            }

        } else {
            throw new Exception("wrong format. enter in correct format ex. 4+ -5i");

        }

    }

    public ComplexCalculation getFirstNum() {
        return z1;
    }

    public ComplexCalculation getSecondNum() {
        return z2;
    }

    public char getFunction() {
        return function;
    }
}
