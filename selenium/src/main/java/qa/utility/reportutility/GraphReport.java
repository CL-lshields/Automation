package qa.utility.reportutility;

import java.util.ArrayList;
import java.util.HashMap;

import qa.SeleniumTest;

public class GraphReport extends ReportHtml{
	ArrayList<int[]> result = new ArrayList<int[]>();
	//HashMap<String,String> data;

	public GraphReport(String fileName,ArrayList<int[]> result ) {
		super(fileName);
		// TODO Auto-generated constructor stub
		this.result =result;
	}
	
	private String graphContent(String id, int i){
		//String temp = "";
		String graph = "<div id='outer' style=\"margin-top:40px; padding:10px\">"+
			    "<div id=\"main\" class=\"row\">"+
		        "<div id=\"div1"+id+"\" class=\"col-sm-6\"></div>"+
		        "<div id=\"div2"+id+"\" class=\"col-sm-6\"></div>"+
		        "<div id=\"div3\"></div>"+
		   "</div>"+
		"</div>"+
				
	"<script language=\"JavaScript\">"+
    "var div1"+id+"=d3.select(document.getElementById('div1'));"+
    "var div2"+id+"=d3.select(document.getElementById('div2'));"+
    "var div3=d3.select(document.getElementById('div3'));"+
    "var div4=d3.select(document.getElementById('div4'));"+
    "start();"+
    "function onClick1() {"+
    "    deselect();"+
    "    div1.attr(\"class\",\"selectedRadial\");}"+
    "function onClick2() {"+
    "    deselect();"+
    "    div2.attr(\"class\",\"selectedRadial\");}"+
    "function onClick3() {"+
    "    deselect();"+
    "    div3.attr(\"class\",\"selectedRadial\");}"+
    "function labelFunction(val,min,max) { }"+
    "function deselect() {"+
    "    div1"+id+".attr(\"class\",\"radial\");"+
    "    div2"+id+".attr(\"class\",\"radial\");"+
    "    div3.attr(\"class\",\"radial\");}"+
    "function start() {"+

    "    var rp1 = radialProgress(document.getElementById('div1"+id+"'))"+
    "            .label(\"Daily\")"+
    "            .onClick(onClick1)"+
    "            .diameter(150)"+
    "            .value("+result.get(i)[0]+")"+
    "            .render();"+    
    "    var rp2 = radialProgress(document.getElementById('div2"+id+"'))"+
    "           .label(\"Monthly\")"+
    "           .onClick(onClick2)"+
    "           .diameter(150)"+
    "           .value("+result.get(i)[1]+")"+
    "           .render();}"+
"</script>";
/*       var rp3 = radialProgress(document.getElementById('div3'))
                .label(\"RADIAL 3\")
                .onClick(onClick3)
                .diameter(150)
                .minValue(100)
                .maxValue(200)
                .value(150)
                .render();
*/
		return graph;
	}
	
	@Override
	protected String generateTabs(ArrayList<Report> reportList) {
		String temp = "";
		
		//Generate market tabs
		for (int i = 0; i < reportList.size(); i++) {
			String pass = "<a href=\"#" + reportList.get(i).getTestName()
					+ "\" class=\"list-group-item list-group-item-success\">" + reportList.get(i).getTestName()
					+ "</a>";
			String fail = "<a href=\"#" + reportList.get(i).getTestName()
					+ "\" class=\"list-group-item list-group-item-danger\">" + reportList.get(i).getTestName() + "</a>";

			if (reportList.get(i).isPass()) {
				temp += pass;
			} else {
				temp += fail;
			}
		}
		return temp;
	}
	
	protected String generateTabContent(ArrayList<Report> reportList, String name) {
		String temp = "";
		
		//Generate screenshots and attach corresponding log reports
		for (int i = 0; i < reportList.size(); i++) {
			if (reportList.get(i).isPass()) {
				temp += "<div class=\"row\">" + "<div id=\"" + reportList.get(i).getTestName()
						+ "\" class=\"panel panel-success\">" + "<div class=\"panel-heading\">"
						+ "<h3 class=\"panel-title\">" + reportList.get(i).getTestName() + "</h3>"
						+ "</div><!-- /.panel-heading -->" + "<div class=\"panel-body\">"
						+ graphContent(reportList.get(i).getTestName(), i)
						+ "</div><!-- /.panel-body -->" +

				"<ul class=\"list-group\">" + readFile(SeleniumTest.staticLogFile, 
						ReportList.testInstance.substring(0,ReportList.testInstance.length()-2)+(i+1),
						ReportList.testInstance.substring(0,ReportList.testInstance.length()-2)+(i+2))
						+ "</ul><!-- /.list-group -->" + "</div><!-- /.panel -->" + "</div><!-- /.row -->";
			} else {
				temp += "<div class=\"row\">" + "<div id=\"" + reportList.get(i).getTestName()
						+ "\" class=\"panel panel-danger\">" + "<div class=\"panel-heading\">"
						+ "<h3 class=\"panel-title\">" + reportList.get(i).getTestName() + "</h3>"
						+ "</div><!-- /.panel-heading -->" + "<div class=\"panel-body\">"
						+ graphContent(reportList.get(i).getTestName(), i)
						+ "</div><!-- /.panel-body -->" +

				"<ul class=\"list-group\">" + "<ul class=\"list-group\">" + readFile(SeleniumTest.staticLogFile,						 
						ReportList.testInstance.substring(0,ReportList.testInstance.length()-2)+(i+1),
						ReportList.testInstance.substring(0,ReportList.testInstance.length()-2)+(i+2))
						+ "</ul><!-- /.list-group -->" + "</div><!-- /.panel -->" + "</div><!-- /.row -->";
			}
		}
		return temp;
	}	

}
