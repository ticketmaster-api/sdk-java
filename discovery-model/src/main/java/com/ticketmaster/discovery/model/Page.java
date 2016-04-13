package com.ticketmaster.discovery.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@Getter
@NoArgsConstructor
public class Page<T> {

	@JsonProperty("_embedded")
	private T embedded;

	@JsonProperty("_links")
	private PageLinks links;

	@JsonProperty("page")
	private PageInfo info;

	@ToString
	@Getter
	@Setter
	@EqualsAndHashCode
	@NoArgsConstructor
	@AllArgsConstructor
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	public static class PageLinks {

		private Link self;
		private Link next;
		@JsonProperty("prev")
		private Link previous;
	}

	@ToString
	@Getter
	@Setter
	@EqualsAndHashCode
	@NoArgsConstructor
	@AllArgsConstructor
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	public static class PageInfo {

		@JsonProperty("size")
		private Integer pageSize;
		private Integer totalElements;
		private Integer totalPages;
		@JsonProperty("number")
		private Integer currentPage;
	}
	
	@ToString
	@Getter
	@Setter
	@EqualsAndHashCode
	@NoArgsConstructor
	@AllArgsConstructor
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	public static class Link {

	    private static final String TEMPLATE_PATTERN = "\\{(.*?)\\}";

	    private String href;
	    private String rel;
	    private Boolean templated;

	    public String getRelativeHref() {
	        if (templated) {
	            return href.replaceAll(TEMPLATE_PATTERN, "");
	        } else {
	            return href;
	        }
	    }
	}

}
