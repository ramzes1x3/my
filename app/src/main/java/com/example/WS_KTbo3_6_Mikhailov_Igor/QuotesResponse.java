package com.example.WS_KTbo3_6_Mikhailov_Igor;

import java.util.ArrayList;

public class QuotesResponse {
	private Boolean success;
	private ArrayList<QuotesModel> quotes;

	public QuotesResponse(Boolean success, ArrayList<QuotesModel> quotes) {
		this.success = success;
		this.quotes = quotes;
	}

	private static class QuotesModel {
		private String id;
		private String title;
		private String image;
		private String description;

		public QuotesModel(String id, String title, String image, String description) {
			this.id = id;
			this.title = title;
			this.image = image;
			this.description = description;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getImage() {
			return image;
		}

		public void setImage(String image) {
			this.image = image;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public ArrayList<QuotesModel> getQuotes() {
		return quotes;
	}

	public void setQuotes(ArrayList<QuotesModel> quotes) {
		this.quotes = quotes;
	}
}
