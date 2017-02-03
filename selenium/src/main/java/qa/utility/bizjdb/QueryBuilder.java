package qa.utility.bizjdb;

public class QueryBuilder {

	private String query="";
	private String select="";
	private String where="";
	private String from="";
	private String[] columns;
	
	public QueryBuilder(){
		
	}
	
	
	
	public String getQuery() {
		return query;
	}



	public void setQuery(String query) {
		this.query = query;
	}



	public String getSelect() {
		return select;
	}



	@SuppressWarnings("unused")
	private void setSelect(String select) {
		this.select = select;
	}



	public String getWhere() {
		return where;
	}



	@SuppressWarnings("unused")
	private void setWhere(String where) {
		this.where = where;
	}



	public String getFrom() {
		return from;
	}



	@SuppressWarnings("unused")
	private void setFrom(String from) {
		this.from = from;
	}



	public String[] getColumns() {
		return columns;
	}



	public void setColumns(String[] columns) {
		this.columns = columns;
	}



	/**
	 * Builds the select portion of an sql query
	 * @param columns array representation of the colums to select from a database	 
	 */
	public void sqlBuildSelect(String[] columns){
		this.columns = columns;
		
		this.select = "Select ";
		for(int i=0;i< columns.length;i++){			
			if(i==0){
				select+=columns[i];
			}else{
			select += ", "+columns[i];
			}
		}		
	}
	
	/**
	 * Builds the 'from' portion of an sql query
	 */
	public void sqlBuildFrom(String table){
		this.from = " FROM " + table;		
	}
	
	/**
	 * Builds the 'where' portion of an sql query
	 */
	public void sqlBuildWhere(String condition){
		this.where = " WHERE " + condition;		
	}
	
	/**
	 * Builds the 'from' portion of an sql query
	 */
	public String sqlBuildQuery(){
		if(!this.select.isEmpty()){
			this.query+=this.select;
		}
		if(!this.from.isEmpty()){
			this.query+=this.from;
		}
		if(!this.where.isEmpty()){
			this.query+= this.where;
		}
		return this.query;
	}
}
