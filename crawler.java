import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class crawler {
	protected void CCRA_PORTAL() {
		try {
			ArrayList<String> arry_product = new ArrayList<String>();

//			String fileName = "C:\\Users\\ko_pc\\Desktop\\CCRA.txt";
//			File file = new File(fileName);
//			FileReader fr = new FileReader(file);
//			BufferedReader buff = new BufferedReader(fr);

			 String CCRA_URL = "https://www.commoncriteriaportal.org/products/";
			 URL url = new URL(CCRA_URL);
			 URLConnection con = (URLConnection) url.openConnection();
			 InputStreamReader reader = new	 InputStreamReader(con.getInputStream(), "utf-8");
			 BufferedReader buff = new BufferedReader(reader);			
			
			 StringBuilder sb = new StringBuilder();			 
			 
			 String source ="";			 
			 while((source = buff.readLine()) != null)
			 {
				 sb.append(source);				 
			 }
			 
			 source = sb.toString();			 
			 buff.close();			
			
			//제품단위로 별로 해당 소스코드 자르기
			slice_product(source,arry_product);
			
			for(int i=0;i < arry_product.size();i++)
			{				
				get_category(arry_product.get(i),i);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void slice_product(String source, ArrayList<String> arry_product) 
	{
		if (source.length() >= 0) 
		{			 
			int index=0 , index_s, index_e;
			do {
				
				//제품별 소스코드 자르기
				index_s = source.indexOf("<tr class=", index);
				index_e = source.indexOf("</tr>", index_s)+5;
				
				if(index_s >= 0 && index_e >= 0)
				{
					//Array list에 제품별로 소스코드 넣기					
					arry_product.add(source.substring(index_s, index_e));
					index = index_e;
				}
			} while (index + 1 < source.length() && index_s != -1 );
		}

	}

	private void get_category(String product_info,int cnt) 
	{		
		String product =get_product(product_info);
		String vender = get_vender(product_info);
		String issued_date=get_issued_date(product_info);
		String compliance="";
		String scheme=get_scheme(product_info);
		String file_st=get_file_ST(product_info);
		String file_cr=get_file_CR(product_info);
		 
		System.out.format("%d %s %s %s %s %s %s\n",cnt, product, vender,issued_date,scheme,file_st,file_cr);
		
		
		
	}

	private String get_product(String product_info) {		
		
		int p_s,p_e;		
		
		p_s = product_info.indexOf("<td class=\"b\">");
		p_e = product_info.indexOf("<",p_s+14);
		
		if(p_s >=0 && p_e >= 0)
			return product_info.substring(p_s+14, p_e).trim();
		else
			return "NO Product name";
	}

	private String get_vender(String product_info) {
		
		int v_search,v_s,v_e;		
		
		v_search = product_info.indexOf("\"Vendor\'s web site\"");
		v_s = product_info.indexOf(">",v_search);		
		v_e = product_info.indexOf("</a>",v_s);
		
		if(v_s >=0 && v_e >= 0)
			return product_info.substring(v_s+1, v_e).trim();
		else
			return "NO vender";
	}

	private String get_issued_date(String product_info) {
		
		int d_search,d_s,d_e;		
		
		d_search = product_info.indexOf("span title");
		d_s = product_info.indexOf(">",d_search);		
		d_e = product_info.indexOf("</span>",d_s);
		
		if(d_s >=0 && d_e >= 0)
			return product_info.substring(d_s+1, d_e).trim();
		else
			return "NO Date";
	}

	private void get_compliance() {
	}

	private String get_scheme(String product_info) {

		int s_search;		
		
		s_search = product_info.indexOf("#");
		
		if(s_search >=0 )
			return product_info.substring(s_search+1, s_search+3).trim();
		else
			return "NO scheme";
		
	}

	private String get_file_CR(String product_info) {
		int c_search,c_s,c_e;		
		
		c_search = product_info.indexOf("Certification Report:");
		c_s = c_search+21;		
		c_e = product_info.indexOf("\"",c_s);
		
		if(c_search >=0 && c_e >= 0)
			return product_info.substring(c_s, c_e).trim();
		else
			return "NO CR";		
	}

	private String get_file_ST(String product_info) {
		int s_search,s_s,s_e;		
		
		s_search = product_info.indexOf("Security Target:");
		s_s = s_search+16;		
		s_e = product_info.indexOf("\"",s_s);
		
		if(s_search >=0 && s_e >= 0)
			return product_info.substring(s_s, s_e).trim();
		else
			return "NO ST";
	}

}
