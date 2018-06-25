function select_ok(){
	var tree = $('#treeview').data('tree');
	var output = '';
	var ids = '';
	var items = tree.selectedItems();
	for(var i in items) if (items.hasOwnProperty(i)) {
		var item = items[i];
		var id = item.additionalParameters['id'];
		var name = item.name;
		ids += id + ",";
		output+= name + "，";
	}
	ids = ids.substring(0,ids.length-1);
	output = output.substring(0,output.length-1);
	$("#select_people").html(output);
	$("#controlPeople").val(ids);
}

function selectType(obj){
	var val = obj.value;
	if(val == '0'){
		$("#type2").hide();
		$("#type1").show();
	}else{
		$("#type1").hide();
		$("#type2").show();
	}
}

function double_select(id,selectid){
	$.ajax({
		url: "approve/double_select.htm",
		data: 'id='+id,
		type: 'POST',
		dataType: 'json' ,
		success : function(data) {
			$("#"+selectid).empty();
			var html = "<option value=''>请选择</option>";
			for(var i = 0;i<data.length;i++){
				html+="<option value='"+data[i].id+"'>"+data[i].userName+"</option>";
			}
			$("#"+selectid).html(html);
		},
		error: function(data) {
			//console.log(this.url);
		}
	});
}

jQuery(function($){
	
	var DataSourceTree = function(options){
		this.url = options.url;
	};
	
	
	DataSourceTree.prototype.data = function(options, callback) {
		var self = this;
		var $data = null;

		var param = null;
		if(!("name" in options) && !("type" in options)){
			param = "";//load the first level data
		}
		else if("type" in options && options.type == "folder") {
			if("additionalParameters" in options && "children" in options.additionalParameters)
				param = options.additionalParameters["id"];
		}
		if(param != null) {
			$.ajax({
				url: this.url,
				data: 'id='+param,
				type: 'POST',
				dataType: 'json' ,
				success : function(response) {
					//if(response.status == "OK")
					callback({ data: response.data });
				},
				error: function(response) {
					//console.log(this.url);
				}
			});
		}
	};
	
	
	$('#treeview').ace_tree({
		dataSource: new DataSourceTree({url: 'approve/users.htm'}),
		multiSelect:true,
		loadingHTML:'<div class="tree-loading"><i class="icon-refresh icon-spin blue"></i></div>',
		'open-icon' : 'icon-minus',
		'close-icon' : 'icon-plus',
		'selectable' : true,
		'selected-icon' : 'icon-ok',
		'unselected-icon' : 'icon-remove'
	});
	
});