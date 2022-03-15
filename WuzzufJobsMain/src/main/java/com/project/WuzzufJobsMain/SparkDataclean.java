package com.project.WuzzufJobsMain;


import org.apache.spark.sql.DataFrameReader;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public class SparkDataclean {
    private Dataset<Row> csvDataFrame;
    private Dataset<Row> Data;
    public SparkDataclean(){
        System.setProperty("hadoop.home.dir", "C:\\winutils");
        final SparkSession sparkSession = SparkSession.builder ().appName ("Spark CSV Analysis Demo").master ("local[2]")
                .getOrCreate ();
        DataFrameReader dataFrameReader = sparkSession.read ();
        dataFrameReader.option ("header", "true");
        csvDataFrame = dataFrameReader.csv ("src/main/resources/Wuzzuf_Jobs1.csv");
        Data = csvDataFrame.distinct().filter("YearsExp NOT LIKE 'null%'").drop();
    }
    public String GetDataCleanNumeric(){
        StringBuilder result = new StringBuilder();
        result.append("<p style=\"text-align:center\">Total Records Before Cleaning = " + csvDataFrame.count() + "</p>");
        result.append("<p style=\"text-align:center\">Number Of Duplicate Records   = " + (csvDataFrame.count() - csvDataFrame.distinct().count()) + "</p>");
        result.append("<p style=\"text-align:center\">Number Of Records Have Null Values = " + (csvDataFrame.count() - csvDataFrame.filter("YearsExp NOT LIKE 'null%'").count()) + "</p>");
        result.append("<p style=\"text-align:center\">Total Records After Cleaning = " + Data.count() + "</p>");

        return result.toString();
    }
    public void GetDataCleanSummary(){
         Data.describe().show();
    }
    public Dataset<Row> GetDataSummary(){
        return csvDataFrame.describe();

    }

//    public static void main(String[] args){

//
////        System.out.println("****************************************************************************************************");
//        disData.describe().show();
////        System.out.println("****************************************************************************************************");
//
////        System.out.println (csvDataFrame.count() - csvDataFrame.distinct().count());
////        System.out.println(csvDataFrame.count() - csvDataFrame.filter("YearsExp NOT LIKE 'null%'").count());
//    }
}
