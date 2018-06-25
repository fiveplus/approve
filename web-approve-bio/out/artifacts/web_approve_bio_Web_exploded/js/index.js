function checkAll(obj){
	var t = document.getElementsByName("checkbox");
	if(!obj.checked){
		for(var i = 0;i<t.length;i++){
			t[i].checked = false;
		}
	}else{
		for(var i = 0;i<t.length;i++){
			t[i].checked = true;
		}
	}
}

function go_back(){
	window.history.go(-1);
}

function printPage(){
	var body = document.body.innerHTML;
	$("#deltr").remove();
	//document.body.innerHTML = str;
	window.print();
	document.body.innerHTML = body;
	return false;
}

function check_user_approve(){
	var title = $("input[name='userApprove.title']").val();
	var remark = $("input[name='userApprove.remark']").val();
	var content = $("textarea[name='userApprove.content']").val();
	if(title == ''){
		alert("	请输入审批标题!");
		return false;
	}
	if(remark == ''){
		alert("请确认选择审批人!");
		return false;
	}
	if(content == ''){
		alert("清输入详细内容!");
		return false;
	}
	return true;
}

function del(url,id){
	if(confirm("确认删除?")){
		$.ajax({
			url:url,
			data:"id="+id,
			type:"POST",
			success:function(data){
				alert(data);
				location.reload();
			}
		});
	}
}

function post_url(url){
	window.location.href=url;
}

function showPrintDialog(id){
	var result = window.open('userapprove/printpage.htm?id='+id+"&temp="+Math.random(),'','width=800,toolbar=yes,modal=yes,status=no,scrollbars=yes');
	window.onfocus=function (){result.focus();};
    window.onclick=function (){result.focus();};
}
/*
function del(url,id){
	if(confirm("确认删除?")){
		window.location.href = url+"?id="+id;
	}
}
*/
function recover(url,id){
	if(confirm("确认恢复?")){
		window.location.href = url+"?id="+id;
	}
}

function cancel(url,id){
	if(confirm("确认作废?")){
		$.ajax({
			url:url,
			data:"id="+id,
			type:"POST",
			success:function(data){
				alert(data);
				location.reload();
			}
		});
	}
}


function to_page(url,formid){
	var form = $("#"+formid);
	var action = form.attr("action",url);
	form.submit();
}

function check_submit(){
	var submit = document.getElementById("submit");
	$("#submit").attr("value","已提交");
	submit.disabled = true;
	return true;
}

