var menuController = {
		
	queryHeadMenu:function(){
		$.ajax({
			url: cview.path+"/admin/manage/menu/queryAllMenus",
			type: "post",
			cache: false,
			async: false,
			dataType: "json",
			data: {
				parentId:"ROOT"
			},
			traditional: true,
			success: function(data, textStatus){
				if(data.statusCode == 200){
					var obj = data.returnObj;
					addHeadMenu(obj);
				}else{
					alert(data.returnObj);
				}
			},
			error: function(XMLHttpRequest, textStatus, errorThrown){
				alert(errorThrown);
			}
		});
	},
	
	_addHeadMenu:function(array){
		for(var i in array){
			var obj = array[i];
			var newHtml = "<li><a data-id='"+ obj.id +"' href='" + cview.path + obj.url + "'>"+ obj.name +"</a></li>";
			$(selector).append(newHtml);
		}
	}
};
