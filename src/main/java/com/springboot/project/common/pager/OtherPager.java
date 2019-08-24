package com.springboot.project.common.pager;

public class OtherPager extends Pager {

	public OtherPager() {
		super();
	}

	public OtherPager(int printArticle, int printPage) {
		super(printArticle, printPage);
	}

	@Override
	protected void computeArticleNumbers() {
		this.startArticleNumber = (this.pageNo * this.printArticle);
		this.endArticleNumber = this.printArticle;
	}

	@Override
	public void setEndArticleNumber(int endArticleNumber) {
		this.endArticleNumber = printArticle;
	}

	@Override
	public int getEndArticleNumber() {
		return this.printArticle;
	}

}
