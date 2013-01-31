function DrapDropBar(tabid){
	var resizing = false;
	
	var pbid = "#"+ tabid + "Bar";
	var tabledivid = "#"+ tabid + "TableDiv";
	var tableid = "#"+ tabid + "Table";
	var tabsdivid = "#"+ tabid + "TabsDiv";
	
	$(pbid).mouseup(function(e) {
	    resizing = false;
	});
	
	$(pbid).mousedown(function(e) {
	    resizing = true;
	});
	
	$(".pageContent").mousemove(function(e) {
		if(resizing) {
	       var tablediv = $(tabledivid);
	       var table = $(tableid);
			var scroller = $(tableid).children('.gridScroller');
			
	       var tabsdiv = $(tabsdivid);
	        				
		    var origHeightDiv = tablediv.height();
	       var origHeightTable = table.height();
	       var origHeightScroll = scroller.height();
	       var origHeightTabs = tabsdiv.height();
	
		        
	       var origPosYGrip = $(pbid).offset().top;
	       var gripHeight = $(pbid).height();
	        
	       tablediv.height(e.pageY - origPosYGrip + origHeightDiv - gripHeight/2);
	       table.height(e.pageY - origPosYGrip + origHeightTable - gripHeight/2);
	       scroller.height(e.pageY - origPosYGrip + origHeightScroll - gripHeight/2);
	       tabsdiv.height(e.pageY + origPosYGrip - origHeightTable + gripHeight/2);
       
		}
	});  	
}

function myGetIds(selectedIds, targetType){
	var ids = "";
	var $box = targetType == "dialog" ? $.pdialog.getCurrent() : navTab.getCurrentPanel();
	$box.find("input:checked").filter("[name='"+selectedIds+"']").each(function(i){
		var val = $(this).val();
		ids += i==0 ? val : ","+val;
	});
	return ids;
}