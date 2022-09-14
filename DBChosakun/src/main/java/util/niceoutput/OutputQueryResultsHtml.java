package util.niceoutput;

import java.time.format.DateTimeFormatter;

import queries.Queries;

public class OutputQueryResultsHtml extends NiceQueryResultOutput {
	
	public  OutputQueryResultsHtml(Queries q) {
		setQueries(q);
	}

	@Override
	protected String getSuffix() {
		DateTimeFormatter timeStampPattern = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String suffix = timeStampPattern.format(java.time.LocalDateTime.now());
		return suffix+".html";
	}

	@Override
	protected String getTableHeaderSeparator() {
		return null;
	}

	@Override
	protected String getColSeparatorBegin() {
		return "<td>";
	}

	@Override
	protected String getColSeparatorEnd() {
		return "</td>";
	}

	@Override
	protected String getQueryEndSeparator() {
		return "<tr><td bgcolor=\"grey\"> ----------- End of query results.------------</td></tr>";
	}

	@Override
	protected String getHeader() {
		return "<table>";
	}

	@Override
	protected String getFooter() {
		return "</table>";
	}

	@Override
	protected String getRowSeparatorBegin() {
		return "<tr>";
	}

	@Override
	protected String getRowSeparatorEnd() {
		return "</tr>";
	}

	

}
