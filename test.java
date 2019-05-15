import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
public class test {
	public static void main(String[] args) {
		/*
		"Category","Name","Manufacturer","Scheme","Assurance Level","Protection Profile(s)","Certification Date","Archived Date","Certification Report URL","Security Target URL","Maintenance Date","Maintenance Title","Maintenance Report","Maintenance ST"
		"Access Control Devices and Systems","ISign+ v3.0","PENTA SECURITY SYSTEMS INC.","KR","None","KECS-PP-0822-2017 SSO V1.0","04/10/2019","","http://www.commoncriteriaportal.org:443/files/epfiles/KECS-CISS-0924-2019_CR_EN.pdf","http://www.commoncriteriaportal.org:443/files/epfiles/KECS-CISS-0924-2019_ST_EN.pdf","","","",""*/
		
		
		try {
			String URL = "https://www.commoncriteriaportal.org/products/certified_products.csv";
			URL url = new URL(URL);
			URLConnection con = (URLConnection) url.openConnection();
			InputStreamReader reader = new InputStreamReader(con.getInputStream(), "utf-8");
			BufferedReader buff = new BufferedReader(reader);
			
			String source ="";			 
			 while((source = buff.readLine()) != null)
			 {
				 List<String> list = new ArrayList<String>();
				 String array[] = source.split(",");
				 list = Arrays.asList(array);
				 
				 String Category = list.get(0); //"Access Control Devices and Systems"
				 String Name= list.get(1); //"ISign+ v3.0"
				 String Manufacturer = list.get(2); //"PENTA SECURITY SYSTEMS INC."
				 String Scheme = list.get(3);//"KR"
				 String Assurance_Level= list.get(4);//"None"
				 String Protection_Profile = list.get(5);//"KECS-PP-0822-2017 SSO V1.0"
				 String Certification_Date= list.get(6);//"04/10/2019"
				 String Archived_Date=list.get(7);//""
				 String Certification_Report_URL = list.get(8);//"http://www.commoncriteriaportal.org:443/files/epfiles/KECS-CISS-0924-2019_CR_EN.pdf"
				 String Security_Target_URL=list.get(9);//"http://www.commoncriteriaportal.org:443/files/epfiles/KECS-CISS-0924-2019_ST_EN.pdf"
				 String Maintenance_Date= list.get(10);
				 String Maintenance_Title=list.get(11);
				 String Maintenance_Report=list.get(12);
				 String Maintenance_ST=list.get(13);
				 //System.out.println(source);				 
			 }

			
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
