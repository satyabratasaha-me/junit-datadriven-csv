package mypackage;

import com.opencsv.CSVWriter;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;


import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

//@RunWith(value= Parameterized.class)
public class DataDrivenTest {

    static CSVWriter writer = null;


    private String artifact;
    private String query1;
    private String query2;



    @ParameterizedTest
    @CsvFileSource(resources = "/test-data.csv", numLinesToSkip = 1)
    public  void TestA(String artifact,String query1, String query2){
        System.out.println(artifact + query1 + query2);

        String data = "artifact"+","+"5"+","+"4"+","+"1";
        String [] record = data.split(",");
        writer.writeNext(record);

    }

    @BeforeAll
    public  static void beforeTest() throws IOException {
        System.out.println("Before");
        String pattern = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        String date = simpleDateFormat.format(new Date());
        String fileName = date.replace(" ","_").replace(":","_");
         writer = new CSVWriter(new FileWriter(fileName+".csv", true));
        String [] record = "artifact,facetsCount,mptsCount,variance".split(",");
        writer.writeNext(record);
    }

    @AfterAll
    public static  void afterTest() throws IOException {
        System.out.println("After");
        writer.close();
    }
}
