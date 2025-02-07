import java.sql.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class hw3 {

    // this method inserts my data into the students table in my database
    public static void insertStudents(String[] first_names, String[] last_names, int[] ids){
        Connection conn = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/HW3","root","nalgenesql");
            Statement s = conn.createStatement();
            // input this data to the database
            PreparedStatement ps = conn.prepareStatement("INSERT INTO Students VALUES(?,?,?)");
            for(int i = 0; i < 100; i++){
                ps.setString(1,first_names[i]);
                ps.setString(2,last_names[i]);
                ps.setInt(3,ids[i]);
                ps.executeUpdate();
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    // this method inserts my data into the classes table in my database
    public static void insertClasses(String[] classnames, int[] numcredits){
        Connection conn = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/HW3","root","nalgenesql");
            Statement s = conn.createStatement();
            // input data to the database
            PreparedStatement ps = conn.prepareStatement("INSERT INTO Classes VALUES(?,?)");
            for(int i = 0; i < 34; i++){
                ps.setString(1,classnames[i]);
                ps.setInt(2,numcredits[i]);
                ps.executeUpdate();
            }
            String q1 = "SELECT * FROM Classes";
            ResultSet rs = s.executeQuery(q1);
            while(rs.next()){
                System.out.println(rs.getString(1));
                System.out.println(rs.getInt(2));
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    // this method inserts my data into the major and minor tables in my database
    public static void insertMajorsMinors(int[] ids){
        int num_major;
        int num_minor;
        ArrayList<String> departments = new ArrayList<String>(Arrays.asList("Bio","Chem","CS","Eng","Math","Phys"));
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/HW3","root","nalgenesql");
            for(int i = 0; i < 100; i++){
                int[] probs = {0,70,25,5};
                num_major = genRandomFromDist(3, probs) + 1; // add one because this returns a value between 0 and 2 and we want one between 1 and 3.
                num_minor = genRandomFromDist(3,probs) + 1;
                Collections.shuffle(departments); // randomize selection of depts
                for(int j = 0; j < num_major; j++){
                    PreparedStatement ps = conn.prepareStatement("INSERT INTO Majors VALUES(?,?)");
                    ps.setInt(1,ids[i]);
                    ps.setString(2,departments.get(j));
                    ps.executeUpdate();
                }
                for(int j = 0; j < num_minor; j++){
                    PreparedStatement ps = conn.prepareStatement("INSERT INTO Minors VALUES(?,?)");
                    ps.setInt(1,ids[i]);
                    ps.setString(2,departments.get(num_major + j));
                    ps.executeUpdate();
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    // this method inserts my data into the isTaking table in my database
    public static void insertIsTaking(int[] ids, String[] classnames){
        ArrayList<Integer> idxs = new ArrayList<Integer>();
        for(int i = 0; i < 34; i++){
            idxs.add(i);
        }
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/HW3","root","nalgenesql");
            for(int i = 0; i < 100; i++){
                Collections.shuffle(idxs);
                PreparedStatement ps = conn.prepareStatement("INSERT INTO IsTaking VALUES(?,?)");
                for(int j = 0; j < 5; j++){
                    ps.setInt(1,ids[i]);
                    ps.setString(2,classnames[idxs.get(j)]);
                    ps.executeUpdate();
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    // this method inserts my data into the hasTaken table in my database
    public static void insertHasTaken(int[] ids, String[] classnames){
        Random rand = new Random();
        String[] grades = {"A","B","C","D","F"};
        ArrayList<Integer> idxs = new ArrayList<Integer>();
        for(int i = 0; i < 34; i++){
            idxs.add(i);
        }
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/HW3","root","nalgenesql");
            for(int i = 0; i < 100; i++){
                int num = rand.nextInt(0,35);
                Collections.shuffle(idxs);
                PreparedStatement ps = conn.prepareStatement("INSERT INTO HasTaken VALUES(?,?,?)");
                for(int j = 0; j < num; j++){
                    ps.setInt(1,ids[i]);
                    ps.setString(2,classnames[idxs.get(j)]);
                    ps.setString(3,grades[rand.nextInt(0,5)]);
                    ps.executeUpdate();
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    // I wrote this method to randomly generate a number from a given distribution
    // cases = length(probs) - 1
    // probs[0] = 0
    // probs[i] corresponds to the probability that case i occurs
    // 0 <= probs[i] <= 100
    // The sum of all elements of probs must equal 100
    // returns a randomly generated number from the distribution given by probs
    public static int genRandomFromDist(int cases, int[] probs){
        Random rand = new Random();
        int num = rand.nextInt(0,100);
        int result = -1;
        int probsum = 0;
        for(int i = 0; i < probs.length - 1; i++){
            if(num >= probsum && num < probsum+probs[i+1]){
                result = i;
            }
            probsum+=probs[i+1];
        }
        return result;
    }
    
    // given a list of student ids, print the necessary info about them
    // this involves printing their name, major(s), minor(s), GPA, and total number of credits
    public static String printStudents(ArrayList<Integer> ids){
        String result = "" + ids.size() + " students found.\n";
        try{
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/HW3","root","nalgenesql");
            Statement s = conn.createStatement();
            for(int i = 0; i < ids.size(); i++){
                // get student name
                String q = "SELECT * FROM Students WHERE id = " + ids.get(i);
                ResultSet rs = s.executeQuery(q);
                rs.next();
                result += rs.getString(2) + ", " + rs.getString(1) + "\n";
                result += "ID: " + ids.get(i) + "\n";
                // get student major(s)
                q = "SELECT dname FROM Majors WHERE sid = " + ids.get(i);
                rs = s.executeQuery(q);
                rs.next();
                result += "Major: " + rs.getString(1);
                while(rs.next()){
                    result+= ", " + rs.getString(1);
                }
                // get student minor(s)
                q = "SELECT dname FROM Minors WHERE sid = " + ids.get(i);
                rs = s.executeQuery(q);
                rs.next();
                result += "\nMinor: " + rs.getString(1);
                while(rs.next()){
                    result+= ", " + rs.getString(1);
                }
                // get GPA and total number of credits (using a convoluted query)
                q = "SELECT SUM(Y.tempcol)/SUM(Y.credits) AS GPA, SUM(Y.credits) AS totcredits " +
                "FROM ( " +
                "SELECT X.sid, X.grade, X.credits, X.credits * (CASE WHEN X.grade = \"A\" THEN 4 WHEN X.grade = \"B\" THEN 3 WHEN X.grade = \"C\" THEN 2 WHEN X.grade = \"D\" THEN 1 ELSE 0 END) AS tempcol " +
                "FROM (SELECT HasTaken.sid, Classes.name, Classes.credits, HasTaken.grade FROM HasTaken LEFT JOIN Classes ON HasTaken.name = Classes.name) X) Y " +
                "WHERE Y.sid = " + ids.get(i);
                rs = s.executeQuery(q);
                rs.next();
                result += "\nGPA: " + rs.getDouble(1) + "\n"; // the GPA is the (sum of (grade number * credit)) / total credits
                result += "Credits: " + rs.getInt(2) + "\n\n"; // total number of credits
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args){
        // this code inputs the data into my database; it is commented out because it should only be run once
        /*try{
            File file = new File("/Users/eva/Downloads/names.csv");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            String temp[] = new String[3];
            String first_names[] = new String[100];
            String last_names[] = new String[100];
            int ids[] = new int[100];
            int count = 0;
            while((line = br.readLine()) != null){
                temp = line.split(",");
                first_names[count] = temp[0];
                last_names[count] = temp[1];
                ids[count] = Integer.parseInt(temp[2]);
                count++;
            } 
            String classnames[] = {"How to Crimp", "How to Jug", "How to Slope", "Intro to layback", "Gaston", "Mastering Leading","Intro to Free Solo",
            "Advanced Free Soloing Technique","History of Climbing","Psychology of Falling","Alex Honnold Case Study","Women and Climbing","Physics of Pulley Injuries",
            "Advanced belaying","Intro to Choss","Intermediate Choss Analysis", "Advanced Choss Identification","Overcoming Fear in Lead Climbing",
            "Overcoming Fear in Lead Belaying","Climbing in Film and Literature","Ethics of Outdoor Climbing","Climbing Theory","Intro to Trad",
            "Intermediate Trad","Advanced Trad","The Future of Climbing","Climbing During Wartime","Anatomy of Climbing",
            "Exercise Science and Climbing","Intro to Clipping","Intro to Crack Climbing","Multipitch for Beginners",
            "Geology and Climbing","Geography and Climbing"};
            int numcredits[] = {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4}; //length=34
            //insertStudents(first_names,last_names,ids);
            //insertClasses(classnames,numcredits);
            //insertMajorsMinors(ids);
            //insertHasTaken(ids,classnames);
            //insertIsTaking(ids,classnames);
        }
        catch(Exception e){
            e.printStackTrace();
        }*/  

        // Question 3
        Scanner scan = new Scanner(System.in);
        System.out.println("Welcome to the university database. Queries available:");
        System.out.println("1. Search students by name.");
        System.out.println("2. Search students by year.");
        System.out.println("3. Search for students with a GPA >= threshold.");
        System.out.println("4. Search for students with a GPA <= threshold.");
        System.out.println("5. Get department statistics.");
        System.out.println("6. Get class statistics.");
        System.out.println("7. Execute an arbitrary SQL query.");
        System.out.println("8. Exit the application.");
        System.out.println("Which query would you like to run (1-8)?");
        int query = scan.nextInt();
        scan.nextLine();
        String q;
        ResultSet rs;
        try{
            while(query!=8){
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:mysql://" + args[0],args[1],args[2]);
                Statement s = conn.createStatement();
                ArrayList<Integer> ret_ids = new ArrayList<Integer>();
                if(query == 1){
                    System.out.println("Please enter the name.");
                    String name = scan.nextLine();
                    q = "SELECT * FROM Students WHERE first_name LIKE \"%" + name + "%\" OR last_name LIKE \"%" + name + "%\"";
                    rs = s.executeQuery(q);
                    while(rs.next()){
                        ret_ids.add(rs.getInt(3));
                    }
                    System.out.println(printStudents(ret_ids));
                }
                if(query == 2){
                    System.out.println("Please enter the year.");
                    String year = scan.nextLine();
                    q = "SELECT X.sid, SUM(CASE WHEN X.grade = \"F\" THEN 0 ELSE X.credits END) as total_credits " + 
                    "FROM (SELECT HasTaken.sid, HasTaken.grade, Classes.name, Classes.credits FROM HasTaken LEFT JOIN Classes ON HasTaken.name = Classes.name) X " +
                    "GROUP BY X.sid;";
                    rs = s.executeQuery(q);
                    ArrayList<Integer> ids = new ArrayList<Integer>();
                    while(rs.next()){
                        int credits = rs.getInt(2);
                        if(year.equals("Fr") && credits >= 0 && credits <= 29) ids.add(rs.getInt(1));
                        else if(year.equals("So") && credits >= 30 && credits <= 59) ids.add(rs.getInt(1));
                        else if(year.equals("Ju") && credits >= 60 && credits <= 89) ids.add(rs.getInt(1));
                        else if(year.equals("Sr") && credits >= 90) ids.add(rs.getInt(1));
                    }
                    System.out.println(printStudents(ids));
                }
                if(query == 3){
                    System.out.println("Please enter the threshold.");
                    double cutoff = scan.nextDouble();
                    scan.nextLine();
                    q = "SELECT Y.sid " +
                    "FROM ( " +
                    "SELECT X.sid, X.grade, X.credits, X.credits * (CASE WHEN X.grade = \"A\" THEN 4 WHEN X.grade = \"B\" THEN 3 WHEN X.grade = \"C\" THEN 2 WHEN X.grade = \"D\" THEN 1 ELSE 0 END) AS tempcol " +
                    "FROM (SELECT HasTaken.sid, Classes.name, Classes.credits, HasTaken.grade FROM HasTaken LEFT JOIN Classes ON HasTaken.name = Classes.name) X) Y " +
                    "GROUP BY Y.sid " +
                    "HAVING SUM(Y.tempcol)/SUM(Y.credits) >= " + cutoff;
                    rs = s.executeQuery(q);
                    ArrayList<Integer> ids = new ArrayList<Integer>();
                    while(rs.next()){
                        ids.add(rs.getInt(1));
                    }
                    System.out.println(printStudents(ids));
                }
                if(query == 4){
                    System.out.println("Please enter the threshold.");
                    double cutoff = scan.nextDouble();
                    scan.nextLine();
                    q = "SELECT Y.sid " +
                    "FROM ( " +
                    "SELECT X.sid, X.grade, X.credits, X.credits * (CASE WHEN X.grade = \"A\" THEN 4 WHEN X.grade = \"B\" THEN 3 WHEN X.grade = \"C\" THEN 2 WHEN X.grade = \"D\" THEN 1 ELSE 0 END) AS tempcol " +
                    "FROM (SELECT HasTaken.sid, Classes.name, Classes.credits, HasTaken.grade FROM HasTaken LEFT JOIN Classes ON HasTaken.name = Classes.name) X) Y " +
                    "GROUP BY Y.sid " +
                    "HAVING SUM(Y.tempcol)/SUM(Y.credits) <= " + cutoff;
                    rs = s.executeQuery(q);
                    ArrayList<Integer> ids = new ArrayList<Integer>();
                    while(rs.next()){
                        ids.add(rs.getInt(1));
                    }
                    System.out.println(printStudents(ids));
                }
                if(query == 5){
                    System.out.println("Please enter the department.");
                    String dept = scan.nextLine();
                    q = "SELECT Z.dname, COUNT(*) AS num_students, AVG(Z.GPA) AS avg_gpa " +
                    "FROM (SELECT Majors.sid, Majors.dname, G.GPA FROM Majors LEFT JOIN ( " +
                    "SELECT Y.sid, SUM(Y.tempcol)/SUM(Y.credits) AS GPA " +
                    "FROM ( " +
                    "SELECT X.sid, X.grade, X.credits, X.credits * (CASE WHEN X.grade = \"A\" THEN 4 WHEN X.grade = \"B\" THEN 3 WHEN X.grade = \"C\" THEN 2 WHEN X.grade = \"D\" THEN 1 ELSE 0 END) AS tempcol " +
                    "FROM (SELECT HasTaken.sid, Classes.name, Classes.credits, HasTaken.grade FROM HasTaken LEFT JOIN Classes ON HasTaken.name = Classes.name) X) Y " +
                    "GROUP BY Y.sid) G ON Majors.sid = G.sid) Z " +
                    "WHERE Z.dname = \"" + dept +  "\"";
                    rs = s.executeQuery(q);
                    rs.next();
                    System.out.println("Num students: " + rs.getInt(2));
                    System.out.println("Average GPA: " + rs.getDouble(3));
                }
                if(query == 6){
                    System.out.println("Please enter the class name.");
                    String classname = scan.nextLine();
                    q = "SELECT COUNT(*) as num_students FROM IsTaking WHERE IsTaking.name = \"" + classname + "\"";
                    rs = s.executeQuery(q);
                    rs.next();
                    System.out.println(rs.getInt(1) + " students currently enrolled");
                    System.out.println("Grades of previous enrollees:");
                    q = "SELECT X.grade, COUNT(X.grade) AS counts FROM (SELECT * FROM HasTaken WHERE name = \"" + classname + "\") X GROUP BY X.grade ORDER BY grade ASC";
                    rs = s.executeQuery(q);
                    while(rs.next()){
                        System.out.println(rs.getString(1) + " " + rs.getInt(2));
                    }
                }
                if(query == 7){
                    System.out.println("Please enter the query.");
                    String inputquery = scan.nextLine();
                    rs = s.executeQuery(inputquery);
                    ResultSetMetaData rsmd = rs.getMetaData();
                    int numcols = rsmd.getColumnCount();
                    for(int i = 1; i <= numcols; i++){
                        System.out.print(rsmd.getColumnName(i) + "\t");
                    }
                    System.out.print("\n");
                    int type;
                    while(rs.next()){
                        for(int i = 1; i <= numcols; i++){
                            type = rsmd.getColumnType(i);
                            if(type == 8) System.out.print(rs.getDouble(i) + "\t");
                            if(type == 4) System.out.print(rs.getInt(i) + "\t");
                            if(type == 12) System.out.print(rs.getString(i) + "\t");
                        }
                        System.out.print("\n");
                    }
                }
                System.out.println("Which query would you like to run (1-8)?\n");
                query = scan.nextInt();
                scan.nextLine();
            }
            System.out.println("Goodbye.");
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
