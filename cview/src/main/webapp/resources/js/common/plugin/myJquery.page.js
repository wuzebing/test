/** 分页 */
var pageNo = 1;
var pageSize = 10;
var methodName;
var _divId = "";
window.pageSize = pageSize = 10;
/**  
* 描述：翻页初始方法，只要为了加载每个页面中的查询方法名称
* 创建人：wuzb3
*/ 
function initPaginator(id, method){
	$("#"+id).css("width","100%");
	_divId = id + "_";
	_createPaginator(method,id);
	methodName = method;
	//前一页
	_getJqueryRealId("pageLeft").click(function(){
		if(window.pageNo > 1){
			window.pageNo -= 1;
			method();
		}
	});
	//后一页
	_getJqueryRealId("pageRight").click(function(){
		if(window.pageNo < window.totalPages){
			window.pageNo += 1;
			method();
		}
	});
}
/**  
* 描述：设置分页查询信息后分页信息
* 创建人：wuzb3
*/ 
function setPaginator(pageNo, totalPages,totalCount){
	window.pageNo = parseInt(pageNo);
	window.totalPages = parseInt(totalPages);
	_setPageNoStyle(totalCount);
	_getJqueryRealId("pageNo").val(pageNo);
	_getJqueryRealId("totalPages").text(totalPages);
	
}

//一下都是内部方法
function _createPaginator(method,id){
	var str = "<div id=\""+ _divId +"pageDiv\" class=\"pageDiv\">"
//			+ "<div id=\""+ _divId +"pageBox\" class=\"pageBox\" style=\"font-size: 16px;\"><br />" 
			+ "<div id=\""+ _divId +"pageBox\" class=\"pageBox\"><br />" 
			+ "<a id=\""+ _divId +"pageLeft\" class=\"page_a\">上页</a>" 
			+ "<a id=\""+ _divId +"firstPages\" onclick=\"_exchangePage(1);\" class=\"page_a\" style=\"display:none;\">1</a>" 
			+ "<span id=\""+ _divId +"points_min\" style=\"display:none;\">...</span>" 
			+ "<font id=\""+ _divId +"page_nums\">"
			+ "</font>"
			+ "<span id=\""+ _divId +"points_max\">...</span>" 
			+ "<a id=\""+ _divId +"totalPages\" onclick=\"_exchangePage(-1);\" class=\"page_a\">0</a>" 
			+ "<a id=\""+ _divId +"pageRight\" class=\"page_a\">下页</a>"
			+ "到<input id=\""+ _divId +"pageNo\" class=\"pageselect\" type=\"text\" onkeyup=\"if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\\D/g,'')}\""  
			+ "  onafterpaste=\"if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'0')}else{this.value=this.value.replace(/\\D/g,'')}\"/>页"
			+ "<a id=\""+ _divId +"confimPage\" onclick=\"_jumpPage()\" class=\"page_a confimBut\">确定</a>"
			+ "<font style=\"margin-left: 10px;\" id=\""+ _divId +"showTotalNum\">共0个<font> </div>"
			+ "</div>";
	$("#"+id).html(str);
}
function _exchangePage(value){
	if(value==-1){
		value = window.totalPages;
	}
	if(value<=window.totalPages){
		window.pageNo = value;
		methodName();
	}
}
/** 跳转页数 */
function _jumpPage(){
	var value = _getJqueryRealId("pageNo").val();
	if(value != "" && value<=window.totalPages){
		window.pageNo = value;
		methodName();
	}else{
		alert("页数为空或超出最大页数");
	}
}
/** 设置分页样式 */
function _setPageNoStyle(totalCount){
	if(window.totalPages != 0){
		_getJqueryRealId("showTotalNum").html("共"+totalCount+"个");
		if(window.pageNo==window.totalPages){
			_getJqueryRealId("pageRight").addClass("pageDisabled");
		}else{
			_getJqueryRealId("pageRight").removeClass("pageDisabled");
		}
		if(window.pageNo==1){
			_getJqueryRealId("pageLeft").addClass("pageDisabled");
		}else{
			_getJqueryRealId("pageLeft").removeClass("pageDisabled");
		}
		_getJqueryRealId("pageDiv").css("display","inline");
		_getJqueryRealId("pageBox").find("a").removeClass("pageDown");
		_getJqueryRealId("firstPages").css("display","none");
		_getJqueryRealId("points_min").css("display","none");
		_getJqueryRealId("points_max").css("display","none");
		_getJqueryRealId("totalPages").css("display","none");
		if(window.totalPages>5){
			if(window.pageNo>=4){
				_getJqueryRealId("firstPages").css("display","inline");
				_getJqueryRealId("points_min").css("display","inline");
			}
			if(window.pageNo+2>=window.totalPages){
				_getJqueryRealId("points_max").css("display","none");
				_getJqueryRealId("totalPages").css("display","none");
			}else{
				_getJqueryRealId("points_max").css("display","inline");
				_getJqueryRealId("totalPages").css("display","inline");
			}
		}
		var str = "";
		if(window.totalPages<=5){
			for(var i=1;i<=window.totalPages;i++){
				str = str + "<a id='"+ _divId +"page_"+i+"' onclick='_exchangePage("+i+");' class='page_a'>"+i+"</a>";
			}
		}else{
			if(window.pageNo<4 || window.totalPages<=5){
				str = "<a id='"+ _divId +"page_1' onclick='_exchangePage(1);' class='page_a'>1</a>" 
						+ "<a id='"+ _divId +"page_2' onclick='_exchangePage(2);' class='page_a'>2</a>" 
						+ "<a id='"+ _divId +"page_3' onclick='_exchangePage(3);' class='page_a'>3</a>" 
						+ "<a id='"+ _divId +"page_4' onclick='_exchangePage(4);' class='page_a'>4</a>" 
						+ "<a id='"+ _divId +"page_5' onclick='_exchangePage(5);' class='page_a'>5</a>";
			}else if(window.pageNo<=window.totalPages-2){
				str = "<a id='"+ _divId +"page_" + (window.pageNo-2) + "' onclick='_exchangePage(" + (window.pageNo-2) + ");' class='page_a'>" + (window.pageNo-2) + "</a>"
						+ "<a id='"+ _divId +"page_" + (window.pageNo-1) + "' onclick='_exchangePage(" + (window.pageNo-1) + ");' class='page_a'>" + (window.pageNo-1) + "</a>"
						+ "<a id='"+ _divId +"page_" + (window.pageNo) + "' onclick='_exchangePage(" + (window.pageNo) + ");' class='page_a'>" + (window.pageNo) + "</a>"
						+ "<a id='"+ _divId +"page_" + (window.pageNo+1) + "' onclick='_exchangePage(" + (window.pageNo+1) + ");' class='page_a'>" + (window.pageNo+1) + "</a>"
						+ "<a id='"+ _divId +"page_" + (window.pageNo+2) + "' onclick='_exchangePage(" + (window.pageNo+2) + ");' class='page_a'>" + (window.pageNo+2) + "</a>";
			}else if(window.pageNo > window.totalPages-2){
				str ="<a id='"+ _divId +"page_" + (window.totalPages-4) + "' onclick='_exchangePage(" + (window.totalPages-4) + ");' class='page_a'>" + (window.totalPages-4) + "</a>"
						+ "<a id='"+ _divId +"page_" + (window.totalPages-3) + "' onclick='_exchangePage(" + (window.totalPages-3) + ");' class='page_a'>" + (window.totalPages-3) + "</a>"
						+ "<a id='"+ _divId +"page_" + (window.totalPages-2) + "' onclick='_exchangePage(" + (window.totalPages-2) + ");' class='page_a'>" + (window.totalPages-2) + "</a>"
						+ "<a id='"+ _divId +"page_" + (window.totalPages-1) + "' onclick='_exchangePage(" + (window.totalPages-1) + ");' class='page_a'>" + (window.totalPages-1) + "</a>"
						+ "<a id='"+ _divId +"page_" + (window.totalPages) + "' onclick='_exchangePage(" + (window.totalPages) + ");' class='page_a'>" + (window.totalPages) + "</a>";
			}
		}
		_getJqueryRealId("page_nums").html(str);
		_getJqueryRealId("page_" + window.pageNo).addClass("pageDown");
	}else{
		_getJqueryRealId("pageDiv").css("display","none");
	}
	
}
function _getJqueryRealId(id){
	return $("#"+_divId + id);
}
