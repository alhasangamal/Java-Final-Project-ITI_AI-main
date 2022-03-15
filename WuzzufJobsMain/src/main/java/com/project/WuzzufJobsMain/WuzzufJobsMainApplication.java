package com.project.WuzzufJobsMain;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.knowm.xchart.BitmapEncoder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RequestMapping(path = "api/alhasan")

public class WuzzufJobsMainApplication {

	public static void main(String[] args) 
        {
            SpringApplication.run(WuzzufJobsMainApplication.class, args);
            try {
                Process p =  Runtime.getRuntime().exec("cmd /c WelcomePage.bat", null, new File("src/main/resources/"));
            
           } catch (IOException ex) {
                Logger.getLogger(WuzzufJobsMainApplication.class.getName()).log(Level.SEVERE, null, ex);
            }
	}

    
    private WuzzufJobsDAO pDAO = new WuzzufJobsDAO("src/main/resources/Wuzzuf_Jobs.csv");
    private SparkDataclean SD = new SparkDataclean();
    private Kmeans km = new Kmeans();
    private String BackBotton = "<p><button onclick=\"location.href='http://localhost:8081/api/alhasan/read'\" type=\"button\"> Home Page</button></p>";
    String logo = "/images/java_original_wordmark_logo_icon_146459.png";
    @GetMapping(path="welcome")
    public StringBuilder getWelcom(){
        StringBuilder bulider = new StringBuilder();
        String alhasan_img = "/images/alhasan.png";
        String leena_img = "/images/leena.png";
        String gemmy_img = "/images/gemmy.png";
        String omar_img = "/images/omar.png";
        bulider.append("<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "<link rel=\"shortcut icon\" href="+logo+" />"+
                "<title>Welcome</title>" +
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
                "<style>\n" +
                "body {\n" +
                "  font-family: Arial, Helvetica, sans-serif;\n" +
                "  margin: 0;\n" +
                "}\n" +
                "\n" +
                "html {\n" +
                "  box-sizing: border-box;\n" +
                "}\n" +
                "\n" +
                "*, *:before, *:after {\n" +
                "  box-sizing: inherit;\n" +
                "}\n" +
                "\n" +
                ".column {\n" +
                "  float: left;\n" +
                "  width: 25%;\n" +
                "  margin-bottom: 16px;\n" +
                "  padding: 0 8px;\n" +
                "}\n" +
                "\n" +
                ".card {\n" +
                "  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);\n" +
                "  margin: 8px;\n" +
                "}\n" +
                "\n" +
                ".about-section {\n" +
                "  padding: 50px;\n" +
                "  text-align: center;\n" +
                "  background-color: #474e5d;\n" +
                "  color: white;\n" +
                "}\n" +
                "\n" +
                ".container {\n" +
                "  padding: 0 16px;\n" +
                "}\n" +
                "\n" +
                ".container::after, .row::after {\n" +
                "  content: \"\";\n" +
                "  clear: both;\n" +
                "  display: table;\n" +
                "}\n" +
                "\n" +
                ".title {\n" +
                "  color: grey;\n" +
                "}\n" +
                "\n" +
                ".b{text-align: center;}"
                +
                ".btn {\n" +
                        "  border: none;\n" +
                        "  color: white;\n" +
                        "  padding: 14px 28px;\n" +
                        "width: 20%;"+
                        "  font-size: 16px;\n" +
                        "  cursor: pointer;\n" +
                        "}\n" +
                ".success {background-color: #04AA6D;}" +
                ".success:hover {background-color: #46a049;}" +
                "@media screen and (max-width: 650px) {\n" +
                "  .column {\n" +
                "    width: 100%;\n" +
                "    display: block;\n" +
                "  }\n" +
                "}\n" +
                "</style>\n" +
                "</head>\n" +
                "<body>\n" +
                "\n" +
                "<div class=\"about-section\">");
        bulider.append("<h1>Final Project For Java Course</h1>");
        bulider.append("<h3>ITI AI-Pro Intake 2</h3>");
        bulider.append("</div>");
        bulider.append("<h2 style=\"text-align:center\">Our Team</h2>");
        bulider.append("<div class=\"row\">\n");
        bulider.append("  <div class=\"column\">\n" +
                       "    <div class=\"card\">");
        bulider.append("<img src=").append(alhasan_img).append(" alt=\"Alhasan\" style=\"width:100%\"/>");
        bulider.append("<div class=\"container\">" +
                "<h2>Alhasan Gamal</h2>\n" +
                "      </div>\n" +
                "    </div>\n" +
                "  </div>");
        bulider.append("  <div class=\"column\">\n" +
                "    <div class=\"card\">");
        bulider.append("<img src=").append(leena_img).append(" alt=\"Leena\" style=\"width:100%\"/>");
        bulider.append("<div class=\"container\">" +
                "<h2>Leena Essam</h2>\n" +
                "      </div>\n" +
                "    </div>\n" +
                "  </div>");
        bulider.append("  <div class=\"column\">\n" +
                "    <div class=\"card\">");
        bulider.append("<img src=").append(gemmy_img).append(" alt=\"Gemmy\" style=\"width:100%\"/>");
        bulider.append("<div class=\"container\">" +
                "<h2>Ahmed Gamal</h2>\n" +
                "      </div>\n" +
                "    </div>\n" +
                "  </div>");
        bulider.append("  <div class=\"column\">\n" +
                "    <div class=\"card\">");
        bulider.append("<img src=").append(omar_img).append(" alt=\"Omar\" style=\"width:100%\"/>");
        bulider.append("<div class=\"container\">" +
                "<h2>Omar Ahmed</h2>\n" +
                "      </div>\n" +
                "    </div>\n" +
                "  </div>");
        bulider.append("<div class='b'><button class=\"btn success\" onclick=\"location.href='http://localhost:8081/api/alhasan/read'\">Let's Start</button>");
        bulider.append("</div>");
        bulider.append("</body>\n" +
                "</html>");


        return bulider;
    }

    @GetMapping(path = "read")
    public StringBuilder readData() 
    {

        StringBuilder builder = new StringBuilder();
        builder.append("<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "<link rel=\"shortcut icon\" href="+logo+" />"+
                "<title>Home</title>" +
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
                "<style>\n" +
                "body {\n" +
                "  font-family: Arial, Helvetica, sans-serif;\n" +
                "  margin: 0;\n" +
                "}\n" +
                "\n" +
                "html {\n" +
                "  box-sizing: border-box;\n" +
                "}\n" +
                "\n" +
                "*, *:before, *:after {\n" +
                "  box-sizing: inherit;\n" +
                "}\n" +
                "\n" +
                ".b {margin-left: 34%;}" +
                ".btn {\n" +
                "  border: none;\n" +
                "  color: white;\n" +
                "  padding: 14px 28px;\n" +
                "margin-bottom: 5px;"+
                "width: 50%;"+
                "  font-size: 16px;\n" +
                "  cursor: pointer;\n" +
                "display:block" +
                "}\n" +
                ".info {background-color: #2196F3;}"+
                ".info:hover {background: #0b7dda;}"+
                ".about-section {\n" +
                "  padding: 20px;\n" +
                "  text-align: center;\n" +
                "  background-color: #474e5d;\n" +
                "  color: white;\n" +
                "margin-bottom: 5px" +
                "}\n" +
                "@media screen and (max-width: 650px) {\n" +
                "  .column {\n" +
                "    width: 100%;\n" +
                "    display: block;\n" +
                "  }\n" +
                "}\n" +
                "</style>\n" +
                "</head>\n" +
                "<body>\n" +
                "\n");
        builder.append("<div class=\"about-section\"><h1 style=\"text-align:center\">Welcome To Wuzzaf Project</h1></div>");

       builder.append("<div class= 'b'>\n");
        builder.append("<button class=\"btn info\" onclick=\"location.href='http://localhost:8081/api/alhasan/some'\">Read Sample from Data</button>");
        builder.append("<button class=\"btn info\" onclick=\"location.href='http://localhost:8081/api/alhasan/summary'\">Get Data Summary</button>");
        builder.append("<button class=\"btn info\" onclick=\"location.href='http://localhost:8081/api/alhasan/struct'\">Get Data Structure</button>");
        builder.append("<button class=\"btn info\" onclick=\"location.href='http://localhost:8081/api/alhasan/clean'\">Get Data Cleaned</button>");
        builder.append("<button class=\"btn info\" onclick=\"location.href='http://localhost:8081/api/alhasan/jobVsComp'\">Get the most demanding Companies for Jobs</button>");
        builder.append("<button class=\"btn info\" onclick=\"location.href='http://localhost:8081/api/alhasan/title'\">Get the most popular job Titles</button>");
        builder.append("<button class=\"btn info\" onclick=\"location.href='http://localhost:8081/api/alhasan/area'\">Get the most popular Areas</button>");
        builder.append("<button class=\"btn info\" onclick=\"location.href='http://localhost:8081/api/alhasan/skills'\">Get the most demanding Skills</button>");
        builder.append("<button class=\"btn info\" onclick=\"location.href='http://localhost:8081/api/alhasan/factorize'\">Factorize the YearsExp feature</button>");
        builder.append("<button class=\"btn info\" onclick=\"location.href='http://localhost:8081/api/alhasan/bartitle'\">Bar-Chart for the most popular job Titles</button>");
        builder.append("<button class=\"btn info\" onclick=\"location.href='http://localhost:8081/api/alhasan/bararea'\">Bar-Chart for the most popular Areas</button>");
        builder.append("<button class=\"btn info\" onclick=\"location.href='http://localhost:8081/api/alhasan/piecompany'\">Pie-Chart for the most demanding Companies for Jobs</button>");
        builder.append("<button class=\"btn info\" onclick=\"location.href='http://localhost:8081/api/alhasan/Kmeans'\">Kmeans</button>");
        builder.append("</body>\n" +
                "</html>");
        return builder;
    }
    
    @GetMapping(path = "struct")
    public String getDataStructure() 
    {
        //return pDAO.getDataStructure()+BackBotton;
        return getHtmlTable(pDAO.getDataStructure(),"Structure of Dataset","Data Struct",BackBotton);
    }
    
    @GetMapping(path = "some")
    public String getSomeData() 
    {
        return getHtmlTable(pDAO.getSomeData(),"Some Data From Dataset","Show Data",BackBotton);
    }
    
    @GetMapping(path = "summary")
    public String getDataSummary() 
    {
        return getHtmlTable(pDAO.getSummary(),"Summary of Dataset","Data Summary",BackBotton);
    }
    
    @GetMapping(path = "clean")
    public String getDataClean() 
    {
        StringBuilder builder = new StringBuilder();
        builder.append("<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "<link rel=\"shortcut icon\" href="+logo+" />"+
                "<title>Display Clean Data</title>"+
                "</head>\n" +
                "<body>\n" +
                "<h1 style=\"text-align:center\"> Display Data Clean Summary</h1>"
        );
        builder.append(BackBotton);
        return  builder + SD.GetDataCleanNumeric();
    }

    
    @GetMapping(path = "jobVsComp")
    public String getJobForEachComp() 
    {
        return BackBotton+ pDAO.getJobsForEachCompany();
    }
    
    @GetMapping(path = "title")
    public String getJobForEachTitle() 
    {
        return BackBotton+pDAO.getJobsForEachTitle();
    }
    
    @GetMapping(path = "area")
    public String getJobForEachLocation() 
    {
        return BackBotton+pDAO.getJobsForEachArea();
    }
    
    @GetMapping(path = "skills")
    public String getDemandingSkills() 
    {
        return BackBotton+pDAO.getDemandingSkills();
    }
    
    @GetMapping(path = "bartitle")
    public StringBuilder getBarChartForTitle() throws IOException 
    {
        String imageNameAndPath = "/images/TitleBarChart.png";
        try{
            BitmapEncoder.saveBitmap(pDAO.getBarChartForTitlesVsJobs(8),"src/main/resources/static"+ imageNameAndPath, BitmapEncoder.BitmapFormat.PNG);
        }catch (IOException e)
        {
            e.printStackTrace();
        }
        StringBuilder builder = new StringBuilder();
        builder.append("<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
                "<link rel=\"shortcut icon\" href="+logo+" />"+
                "<title>Titles Bar Chart</title>"+
                "<style>\n" +
                "img {\n" +
                "  display: block;\n" +
                "  margin-left: auto;\n" +
                "  margin-right: auto;\n" +
                "}\n" +
                "</style>\n" +
                "</head>\n" +
                "<body>");
        builder.append("<h1 style=\"text-align:center\">Titles Bar Chart</h1>");
        builder.append("<img src='").append(imageNameAndPath).append("'/>");
        builder.append(BackBotton);
        return builder ;

    }
    
    @GetMapping(path = "bararea")
    public StringBuilder getBarChartForAreas() throws IOException 
    {
        String imageNameAndPath = "/images/AreasBarChart.png";
        try{
            BitmapEncoder.saveBitmap(pDAO.getBarChartForAreasVsJobs(8),"src/main/resources/static"+ imageNameAndPath, BitmapEncoder.BitmapFormat.PNG);
        }catch (IOException e)
        {
            e.printStackTrace();
        }
        StringBuilder builder = new StringBuilder();
        builder.append("<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
                "<link rel=\"shortcut icon\" href="+logo+" />"+
                "<title>Areas Bar Chart</title>"+
                "<style>\n" +
                "img {\n" +
                "  display: block;\n" +
                "  margin-left: auto;\n" +
                "  margin-right: auto;\n" +
                "}\n" +
                "</style>\n" +
                "</head>\n" +
                "<body>");
        builder.append("<h1 style=\"text-align:center\">Areas Bar Chart</h1>");
        builder.append("<img src='").append(imageNameAndPath).append("'/>");
        builder.append(BackBotton);
        return builder;
    }
    
    @GetMapping(path = "piecompany")
    public StringBuilder getPieChartForCompany() throws IOException 
    {
        String imageNameAndPath = "/images/CompanyPieChart.png";
        try{
            BitmapEncoder.saveBitmap(pDAO.getPieChartForJobsVsCompanies(8),"src/main/resources/static"+ imageNameAndPath, BitmapEncoder.BitmapFormat.PNG);
        }catch (IOException e)
        {
            e.printStackTrace();
        }
        StringBuilder builder = new StringBuilder();
        builder.append("<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
                "<link rel=\"shortcut icon\" href="+logo+" />"+
                "<title>Companies Pie Chart</title>"+
                "<style>\n" +
                "img {\n" +
                "  display: block;\n" +
                "  margin-left: auto;\n" +
                "  margin-right: auto;\n" +
                "}\n" +
                "</style>\n" +
                "</head>\n" +
                "<body>");
        builder.append("<h1 style=\"text-align:center\">Companies Pie Chart</h1>");
        builder.append("<img src='").append(imageNameAndPath).append("'/>");
        builder.append(BackBotton);
        return builder;
        }
    
    @GetMapping(path = "factorize")
    public String factorizeYrsExp() throws IOException 
    {
        return getHtmlTable(pDAO.factorizeYrsOfExp(),"Somedata of Dataset after factorizing","Factorize YrsOfExp",BackBotton);
    }
    @GetMapping(path = "Kmeans")
    public String kmeans(){
        return km.calculateKMeans();
    }
    
    private String getHtmlTable(String datatext,String tableTitle,String title,String button_ref)
    {
        StringBuilder builder = new StringBuilder();
        builder.append("<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "<link rel=\"shortcut icon\" href="+logo+" />"+
                        "<title>"+title+"</title>"+

                "<style>\n" +
                "table {\n" +
                "  font-family: arial, sans-serif;\n" +
                "  border-collapse: collapse;\n" +
                "  width: 100%;\n" +
                "}\n" +
                "\n" +
                "td, th {\n" +
                "  border: 1px solid #dddddd;\n" +
                "  text-align: left;\n" +
                "  padding: 8px;\n" +
                "}\n" +
                "\n" +
                "tr:nth-child(even) {\n" +
                "  background-color: #dddddd;\n" +
                "}\n" +
                "</style>\n" +
                "</head>\n" +
                "<body>\n");
        builder.append(button_ref);
        builder.append("<h1 style=\"text-align:center\">"+tableTitle+"</h1>");
        builder.append("<table>");
        String[] dataArray = datatext.split("\n");
        for (String x : dataArray )
        {
          if(!(x.contains("--")||x.contains("[")))
          {
              builder.append("<tr><td>" + x.replace("|", "</td><td>") + "</td></tr>");
          }
        }
        builder.append("</table>\n" +
                "\n" +
                "</body>\n" +
                "</html>");
        return builder.toString();
    }

}
