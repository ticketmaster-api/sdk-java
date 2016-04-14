package com.ticketmaster.discovery.v2.operation;

import java.util.ArrayList;
import java.util.Collection;

import com.google.common.base.Joiner;
import com.google.common.base.Strings;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode(callSuper = true)
public class SearchVenuesOperation extends Operation {

	private SearchVenuesOperation(SearchVenuesOperationBuilder builder) {
		// TODO: That boilerplate code could be improved using annotations
		if (! Strings.isNullOrEmpty(builder.keyword)) {
			this.queryParams.put("keyword", builder.keyword);
		}
		if (! Strings.isNullOrEmpty(builder.locale)) {
			this.queryParams.put("locale", builder.locale);
		}
		if (builder.venueIds != null && builder.venueIds.size() > 0) {
			this.queryParams.put("venueId",
					Joiner.on(",").skipNulls().join(builder.venueIds));
		}
		if (! Strings.isNullOrEmpty(builder.sort)) {
			this.queryParams.put("sort", builder.sort);
		}
		if (builder.pageSize != null) {
			this.queryParams.put("size", Integer.toString(builder.pageSize));
		}
		if (builder.pageNumber != null) {
			this.queryParams.put("page", Integer.toString(builder.pageNumber));
		}
	}

	public static SearchVenuesOperationBuilder builder() {
		return new SearchVenuesOperationBuilder();
	}

	public static class SearchVenuesOperationBuilder {
		private String keyword;
		private ArrayList<String> venueIds;
		private Integer pageSize;
		private Integer pageNumber;
		private String sort;
		private String locale;

		SearchVenuesOperationBuilder() {
		}

		public SearchVenuesOperation.SearchVenuesOperationBuilder keyword(
				String keyword) {
			this.keyword = keyword;
			return this;
		}

		public SearchVenuesOperation.SearchVenuesOperationBuilder venueId(
				String venueId) {
			if (this.venueIds == null)
				this.venueIds = new ArrayList<String>();
			this.venueIds.add(venueId);
			return this;
		}

		public SearchVenuesOperation.SearchVenuesOperationBuilder venueIds(
				Collection<String> venueIds) {
			if (this.venueIds == null)
				this.venueIds = new ArrayList<String>();
			this.venueIds.addAll(venueIds);
			return this;
		}

		public SearchVenuesOperation.SearchVenuesOperationBuilder pageSize(
				Integer pageSize) {
			this.pageSize = pageSize;
			return this;
		}

		public SearchVenuesOperation.SearchVenuesOperationBuilder pageNumber(
				Integer pageNumber) {
			this.pageNumber = pageNumber;
			return this;
		}

		public SearchVenuesOperation.SearchVenuesOperationBuilder sort(
				String sort) {
			this.sort = sort;
			return this;
		}

		public SearchVenuesOperation.SearchVenuesOperationBuilder locale(
				String locale) {
			this.locale = locale;
			return this;
		}

		public SearchVenuesOperation build() {
			return new SearchVenuesOperation(this);
		}
	}
}