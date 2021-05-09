package com.nanum.util;

public class Paging {
	
	 private final static int pageCount = 5;
	    private static int blockStartNum = 0;
	    private static int blockLastNum = 0;
	    private static int lastPageNum = 0;
	    private static int total = 0;
	    
	    public static int getBlockStartNum() {
	        return blockStartNum;
	    }
	    public void setBlockStartNum(int blockStartNum) {
	        Paging.blockStartNum = blockStartNum;
	    }
	    public static int getBlockLastNum() {
	        return blockLastNum;
	    }
	    public void setBlockLastNum(int blockLastNum) {
	        Paging.blockLastNum = blockLastNum;
	    }
	    public static int getLastPageNum() {
	        return lastPageNum;
	    }
	    public void setLastPageNum(int lastPageNum) {
	        Paging.lastPageNum = lastPageNum;
	    }

	    // block을 생성
	    // 현재 페이지가 속한 block의 시작 번호, 끝 번호를 계산
	    public static void makeBlock(int curPage){
	        int blockNum = 0;

	        blockNum = (int)Math.floor((curPage-1)/ pageCount);
	        blockStartNum = (pageCount * blockNum) + 1;
	        blockLastNum = blockStartNum + (pageCount-1);
	    }

	    // 총 페이지의 마지막 번호
	    public static void makeLastPageNum() {
	        if( total % pageCount == 0 ) {
	            lastPageNum = (int)Math.floor(total/pageCount);
	        }
	        else {
	            lastPageNum = (int)Math.floor(total/pageCount) + 1;
	        }
	    }
	
	    // 검색을 했을 때 총 페이지의 마지막 번호
	   

}
