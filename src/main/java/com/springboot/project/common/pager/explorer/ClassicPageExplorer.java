package com.springboot.project.common.pager.explorer;

import com.springboot.project.common.pager.Pager;
import com.springboot.project.common.pager.decorator.Decorator;

public class ClassicPageExplorer extends PageExplorer {

	public ClassicPageExplorer(Pager pager) {
		this.pager = pager;
		this.decorator = new Decorator();
	}
	
	@Override
	protected String generate(StringBuffer pagenation) {
		if ( pager.nowGroupNumber > 0 ) {
			pagenation.append(makePrevGroup(pager.prevGroupPageNumber));
		}
		
		int nextPrintPage = pager.groupStartPage + pager.printPage;
		if ( nextPrintPage > pager.totalPage) {
			nextPrintPage = pager.totalPage + 1;
		}
		
		String pageNumber = "";
		
		for ( int i = pager.groupStartPage; i < nextPrintPage; i++ ) {
			pageNumber = decorator.makePageNumber(pageNumber, i);
			
			if ( (i-1) == pager.pageNo ) {
				pageNumber = makeHighlightNowPageNumber(pageNumber);
			}
			
			pagenation.append(makePageNumbers(i-1, pageNumber));
		}
		
		if ( pager.nowGroupNumber < (pager.totalGroup -1 )) {
			pagenation.append(makeNextGroup(pager.nextGroupPageNumber));
		}
		return pagenation.toString();
	}

}
