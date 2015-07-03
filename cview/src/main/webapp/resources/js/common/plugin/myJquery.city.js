var CityRIA = new Object;
CityRIA.currentPrivenceID = null;//当前展示开省ID，做缓存，一样的话下次可以不用重新读取
CityRIA.isProvienceWidgetShow = false;
CityRIA.data = null;
CityRIA.documentID = null;
CityRIA.inputID = null;

/**城市级联的层级，默认二级，如果是三级需要修改此参数为3**/
//CityRIA.maxGrade = 2;
/**可以动态设置选择的城市个数**/
CityRIA.maxSelLength = 6;
CityRIA.selectItems = new Array();
CityRIA.currentLength = 0;

//内部属性
CityRIA.top = 0;
CityRIA.left = 0;
CityRIA.height = 21;
CityRIA.width = 149;

CityRIA.provincename = "";
CityRIA.provinceid = "";

CityRIA.values={};
CityRIA.valueIds={};

//内部事件
CityRIA._onItemClick = null;

/**构造对象**/
CityRIA.CreateWidget = function(documentID,inputID,data){
	CityRIA.top = $('#'+documentID).offset().top + CityRIA.height-13;
	CityRIA.left = $('#'+documentID).offset().left-2;
	CityRIA.data = data;
	CityRIA.documentID = documentID;
	CityRIA.inputID = inputID;
	CityRIA._onItemClick = $('#'+documentID).attr("onItemClick");
	_initZIndexDIV($('#'+documentID).offset().top,$('#'+documentID).offset().left,documentID);
	$("#label"+CityRIA.inputID).css("height",(CityRIA.height-5)+"px").css("line-height",(CityRIA.height-3)+"px");
	_initProvinceWidget(documentID,data);
	_InitWidgetEvent();
};

/**
 * 外部方法
 * 
 **/
CityRIA.setUserValue=function(inputID,provincename,provinceid){
	$("#"+inputID).val(provinceid);
	$("#"+inputID+"_label").text(provincename);
	CityRIA.values[CityRIA.documentID] = CityRIA.provincename = provincename;
	CityRIA.valueIds[CityRIA.documentID] = CityRIA.provinceid = provinceid;
	_hiddenProvinceWidget();
};

CityRIA.getId=function(documentID){
	return CityRIA.valueIds[documentID];
};

CityRIA.getValue=function(documentID){
	return CityRIA.values[documentID];
};



/**控件显示和隐藏**/
CityRIA.showWidget = function(){
	_showProvinceWidget();
};
CityRIA.hiddenWidget = function(){
	_hiddenProvinceWidget();
};

CityRIA.deleteCity = function(obj){
	var $obj = $(obj);
	var cid = $obj.parent().attr("cid");
	for(var i=0;i<CityRIA.selectItems.length;i++){
		if(CityRIA.selectItems[i].cityId == cid){
			CityRIA.selectItems.splice(i,1);
			break;
		}
	}
	$obj.parent().remove();
};

CityRIA.closeWidget=function(){
	_hiddenProvinceWidget();
};

CityRIA._selectProvince=function(obj,provinceid){
	var provincename = $(obj).html();
	CityRIA.provincename = provincename;
	CityRIA.provinceid = provinceid;
	CityRIA.setUserValue(CityRIA.inputID,provincename,provinceid);
	CityRIA.values[CityRIA.documentID] = provincename;
	CityRIA.valueIds[CityRIA.documentID] = provinceid;
	var methodName = CityRIA._onItemClick;
	if(methodName != null){
		window[methodName](obj,provinceid);
	}
};

function _initProvinceWidget(documentID,data){
	_createProvinceWidget(documentID,data);
	$("#"+documentID).click(function(){
		$("#"+CityRIA.inputID).focus();
		if(CityRIA.isProvienceWidgetShow){
			_hiddenProvinceWidget();
			CityRIA.isProvienceWidgetShow = false;
		}else{
			_showProvinceWidget();
			CityRIA.isProvienceWidgetShow = true;
		}
	});
}

//显示或隐藏省的div
function _showProvinceWidget(){
	$(".cityRIA").css("visibility","visible");
}
function _hiddenProvinceWidget(){
	$(".cityRIA").css("visibility","hidden");
}

function _initZIndexDIV(top,left,documentID){
	var _html = '<div id="label'+ CityRIA.inputID +'" onclick="_changeIuput()" class="CityRIAlabelReplace" style="display: block;"><label id="'+ CityRIA.inputID +'_label" for="'+ CityRIA.inputID +'">全部</label></div>'
		+ '<input name="'+ CityRIA.inputID +'" class="CityRIAInput" id="'+ CityRIA.inputID +'"/>';
	$('#' + documentID).append(_html);
}

function _changeIuput(){
	$("#label"+CityRIA.inputID).css("display","none");
	_showProvinceWidget();
	CityRIA.isProvienceWidgetShow = true;
}

//创建省
function _createProvinceWidget(documentID,data){
	var china = data.china;
	var _html = '<div class="cityRIA"'
		+'style="width: 300px; left: '+CityRIA.left+'px; top: '+CityRIA.top+'px; visibility: hidden;">'
	+'<div class="widgetBlock_city">'
	+'	<div style="height: 5px;" class="clear"></div>'
	+'	<div class="pCityTitB_city">'
	+'		<span></span>'
	+'		<span id="currentprovincename"' 
	+'			onclick="">请选择</span>'
	+'	</div>'
	+'	<div style="display: block;" class="pCityItemB_city">'
	+'		<div class="widgetTabCB_city">'
	+'			<table class="widgetTabC_city" id="widgetTabC_city" border="0" cellpadding="0"';
	var privenceTable = '<tr><td class="blurItem_city" width="20%"><span class="availItem_city" onclick="CityRIA._selectProvince(this,\'\')">全部</span><td></tr><tr>';
	var totalNum = 1;
	for(var i in china){
		var province = china[i];
		privenceTable = privenceTable +'<td class="blurItem_city"'
		+' width="20%">'
		+ '<span class="availItem_city"'
		+' onclick="CityRIA._selectProvince(this,\''+province.provinceid+'\');">'+province.provincename+'</span></td>';
		if(totalNum%5 == 0){
			privenceTable = privenceTable + '</tr><tr>';
		}
		totalNum++;
	}
	_html = _html + privenceTable +'</tr></table>'
	+'		</div>'
	+'	</div>'
	+'	<div style="height: 5px;" class="clear"></div>'
	
	+'</div>'
	+'</div>';
	$('body').append(_html);
//	$("#" + documentID).after(_html);
}

//初始化所有的事件（测试city中的span部分还没有生成，他的事件不在此定义）
function _InitWidgetEvent(){
	$(".cityRIA").mouseleave(function(event){
		event.stopPropagation();
		CityRIA.hiddenWidget();
	});
	$(".cityRIA").mouseenter(function(){
		CityRIA.showWidget();
	});
	$("#"+CityRIA.inputID).change(function(){
		CityRIA.values[CityRIA.documentID] = CityRIA.provincename = "";
		CityRIA.valueIds[CityRIA.documentID] = CityRIA.provinceid = "";
		$("#"+CityRIA.inputID).val("");
		$("#"+CityRIA.inputID +"_label").text("");
	});
	$("#"+CityRIA.inputID).click(function(){
		$("#"+CityRIA.inputID).focus();
	});
	$("#"+CityRIA.inputID).focus(function(){
		$("#label"+CityRIA.inputID).css("display","none");
		$("#"+CityRIA.inputID).val(CityRIA.provincename);
	});
	$("#"+CityRIA.inputID).blur(function(){
		$("#label"+CityRIA.inputID).css("display","inline");
		$("#"+CityRIA.inputID).val(CityRIA.provinceid);
	});
}
