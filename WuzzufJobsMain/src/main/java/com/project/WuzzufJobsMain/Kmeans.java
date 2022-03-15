package com.project.WuzzufJobsMain;

import javassist.expr.Cast;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.ml.evaluation.ClusteringEvaluator;
import org.apache.spark.ml.feature.StringIndexer;
import org.apache.spark.ml.feature.StringIndexerModel;
import org.apache.spark.mllib.clustering.KMeans;
import org.apache.spark.mllib.clustering.KMeansModel;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.linalg.Vectors;
import org.apache.spark.rdd.RDD;
import org.apache.spark.sql.DataFrameReader;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

    public class Kmeans {
        public Kmeans(){}
        public static String  calculateKMeans() {
            // Loads data.


            // Create Spark Session to create connection to Spark
            final SparkSession sparkSession = SparkSession.builder().appName("Wuzzuf Spark Kmeans Demo")
                    .master("local[6]").getOrCreate();

            // Get DataFrameReader using SparkSession and set header option to true
            // to specify that first row in file contains name of columns
            final DataFrameReader dataFrameReader = sparkSession.read().option("header", true);
            final Dataset<Row> trainingData = dataFrameReader.csv("src/main/resources/Wuzzuf_Jobs.csv");
            StringIndexerModel indexer = new StringIndexer()
                    .setInputCols(new String[]{"Title", "Company"})
                    .setOutputCols(new String[]{"TitleConv", "CompanyConv"})
                    .fit(trainingData);

            Dataset<Row> indexed = indexer.transform(trainingData);
            indexed.show();
            indexed.printSchema();
            Dataset<Row> factorized = indexed.select("TitleConv", "CompanyConv");
            factorized.show();

            System.out.println("+++++========++++++++kmeans");

            JavaRDD<Vector> parsedData = factorized.javaRDD().map((Function<Row, Vector>) s -> {
                double[] values = new double[2];

                values[0] = Double.parseDouble(s.get(0).toString());
                values[1] = Double.parseDouble(s.get(1).toString());

                return Vectors.dense(values);
            });

            parsedData.cache();
            int numClusters = 2;
            int numIterations = 30;
            KMeansModel clusters = KMeans.train(parsedData.rdd(), numClusters, numIterations);
            double WithinError = clusters.computeCost(parsedData.rdd());
            String output = "<html><body>";
            output += "<h1>"+"Calculate KMeans Clustering:"+"</h1><br><p>";

            for (Vector str : clusters.clusterCenters()){
                output += str.toString();
                output += "<br>";
            }

            output += "<br>" + "Within Set Sum of Square Error: " + WithinError+"<br>";
            output += "</p></body></html>";
            return output;
        }

        }


