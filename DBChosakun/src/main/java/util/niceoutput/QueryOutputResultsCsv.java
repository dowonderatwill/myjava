package util.niceoutput;

import java.time.format.DateTimeFormatter;

import queries.Queries;

public class QueryOutputResultsCsv extends NiceQueryResultOutput {
	
	public QueryOutputResultsCsv(Queries q) {
		setQueries(q);
	}

	@Override
	protected String getSuffix() {
		DateTimeFormatter timeStampPattern = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String suffix = timeStampPattern.format(java.time.LocalDateTime.now());
		return suffix+".csv";
	}

	@Override
	protected String getTableHeaderSeparator() {
		// TODO Auto-generated method stub
		return "-----";
	}

	@Override
	protected String getColSeparatorBegin() {
		// TODO Auto-generated method stub
		return " \t ";
	}

	@Override
	protected String getColSeparatorEnd() {
		// TODO Auto-generated method stub
		return ", ";
	}

	@Override
	protected String getRowSeparatorBegin() {
		// TODO Auto-generated method stub
		return " ";
	}

	@Override
	protected String getRowSeparatorEnd() {
		// TODO Auto-generated method stub
		return " ";
	}

	@Override
	protected String getQueryEndSeparator() {
		// TODO Auto-generated method stub
		return "=====";
	}

	@Override
	protected String getHeader() {
		// TODO Auto-generated method stub
		return "";
	}

	@Override
	protected String getFooter() {
		// TODO Auto-generated method stub
		return "~~ END~~~";
	}

	
}
