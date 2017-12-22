package data.analysis.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BittrexResponse {
	
	private String success;
	
	private String message;
	
	private Result result;
	
	public String getSuccess() {
		return success;
	}
	public void setSuccess(String success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	public Result getResult() {
		return result;
	}
	public void setResult(Result result) {
		this.result = result;
	}

	public class Result {
		@JsonProperty("Bid")
		private String bid;
		@JsonProperty("Ask")
		private String ask;
		@JsonProperty("Last")
		private String last;
		public String getBid() {
			return bid;
		}
		public void setBid(String bid) {
			this.bid = bid;
		}
		public String getAsk() {
			return ask;
		}
		public void setAsk(String ask) {
			this.ask = ask;
		}
		public String getLast() {
			return last;
		}
		public void setLast(String last) {
			this.last = last;
		}
		
		
	}
}
