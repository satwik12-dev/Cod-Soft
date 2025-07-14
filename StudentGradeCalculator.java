import java.util.Scanner;

public class StudentGradeCalculator {
    int mmarks;
    int hmarks;
    int emarks;
    int smarks;
    int ssmarks;
    
    void takingmarks(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the marks of student in maths:");
        mmarks = sc.nextInt();
        System.out.println("Enter the marks of student in Hindi:");
        hmarks = sc.nextInt();
        System.out.println("Enter the marks of student in English:");
        emarks = sc.nextInt();
        System.out.println("Enter the marks of student in Science:");
        smarks = sc.nextInt();
        System.out.println("Enter the marks of student in Social Studies:");
        ssmarks = sc.nextInt();
        sc.close();
    }
    void totalmarks(){
        int total = mmarks + hmarks + emarks + smarks + ssmarks;
        System.out.println("Total Marks: " + total);
    }

    void avgpercentage(){
        int total = mmarks + hmarks + emarks + smarks + ssmarks;
        double percentage = (total / 5.0);
        System.out.println("Average Percentage: " + percentage + "%");
    }

    void grade(){
        int total = mmarks + hmarks + emarks + smarks + ssmarks;
        double percentage = (total / 5.0);
        
        if (percentage >= 90) {
            System.out.println("Grade: A");
        } else if (percentage >= 80) {
            System.out.println("Grade: B");
        } else if (percentage >= 70) {
            System.out.println("Grade: C");
        } else if (percentage >= 60) {
            System.out.println("Grade: D");
        } else {
            System.out.println("Grade: F");
        }
    }


    
    public static void main(String[] args) {
        StudentGradeCalculator sms = new StudentGradeCalculator();
        sms.takingmarks();
        sms.totalmarks();
        sms.avgpercentage();
        sms.grade();
    }
}