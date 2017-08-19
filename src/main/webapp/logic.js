var tasknames = new Array('morning','afternoon','evening');


function diagnose(){
	
	//获取用户选择的起始日期
	beginDate = $("input[name='beginDate']").val();
	
	//检查是否为空
	if(isNull(beginDate))
		dangerNotify("Please select a day in calendar!");
	else{ 
		//请求后端获得result
	    //
	    $.ajax({
	        type:"post", 
	        url:"query_activity", 
	        data:{
	            beginDate:beginDate,
	        },
	        success:function(data){
	        	
	        	resultList = eval('('+data+')'); //将json字符串转换成bean对象
	            if(isNull(resultList)) {
	            	dangerNotify("Diagnose failed.");
	            	return;
	            }
	            
	            successNotify("Diagnose success! Please check your report below.");
	            
	            for(var i = 0; i < resultList.length; i++)
		            showStatistics(tasknames[i], resultList[i]);

	        }
	    });	
	}	
}

function showStatistics(time, resultList) {
	
	len = resultList.length;
	
    //构建数据
    var names = new Array();
    var scores = new Array();
    var fullScores = new Array();
	for(i = 0; i < len; i++) {
		names[i] = resultList[i].name;
		scores[i] = resultList[i].score;
		fullScores[i] = resultList[i].fullScore;
	}
    chartdata = {
    		labels: names,
    		datasets: [
       			{
       				label: "your score",
    				backgroundColor: "rgba(151,187,205,0.5)",
    				borderColor: "rgba(151,187,205,0.8)",
    				highlightFill: "rgba(151,187,205,0.75)",
    				highlightStroke: "rgba(151,187,205,1)",
    				data: scores,
    				borderWidth:2
    			},
    			{
    				label: "full score",
    				backgroundColor: "rgba(220,220,220,0.5)",
    				borderColor: "rgba(220,220,220,0.8)",
    				highlightFill: "rgba(220,220,220,0.75)",
    				highlightStroke: "rgba(220,220,220,1)",
    				data: fullScores,
    				borderWidth:2
    			}
    		]
    	};

	//显示隐藏部分
    $("#chart_div_"+time).show();
    
    //生成柱状图
    ctx = $("#chart_"+time).get(0).getContext("2d");
    new Chart(ctx, {
        type: 'bar',
        data: chartdata
    });
    
    //生成最终结果
    $("#score_"+time).text(resultList[len - 1].score);
    $("#achievement_"+time).text(resultList[len - 1].state);
    
    //生成表格
    table = $("#table_"+ time +" tbody");
    table.empty();
    for(i = 0; i < len; i++) {
    	var scene_class = getSceneClass(resultList[i].state); //情景
    	var type_class = getTypeClass(resultList[i].type); //plan/action
    	var finish_class = getFinishClass(resultList[i].state); //完成与否
    	var belief_class = getBeliefClass(resultList[i].state); //情景链接
    	
    	table.append(getTableRow(scene_class, type_class, finish_class, belief_class, resultList[i], i + 1));
    }
    initBootstrapPopovers();
}


//获得表格行的情景类
function getSceneClass(state){
	var scene_class = "danger";
	if(state == "completed")
		scene_class = "success";
	else if(state == "activated")
		scene_class = "warning";
	return scene_class;
}

//获得表格行的type图标
function getTypeClass(type){
	var type_class = "glyphicon glyphicon-tags";
	if(type == "action")
		type_class = "glyphicon glyphicon-tag";

	return type_class;
}

//获得表格行的finish图标
function getFinishClass(finish){
	var finish_class = "glyphicon glyphicon-remove";
	if(finish == "completed")
		finish_class = "glyphicon glyphicon-ok";
	else if(finish == "activated")
		finish_class = "glyphicon glyphicon-repeat";
	
	return finish_class;
}

//获得置信度链接字体的情景类
function getBeliefClass(state){
	var belief_class = "text-error";
	if(state == "completed")
		belief_class = "text-success";
	else if(state == "activated")
		belief_class = "text-warning";
	return belief_class;
}

//加工得到表格行的html代码
function getTableRow(scene_class, type_class, finish_class, belief_class, result, index){
	
	return '<tr class="'+ scene_class +'">'+
				'<th scope="row">'+ index +'</th>' +
				'<td>'+ result.name + '</td>' +
				'<td><span class="'+ type_class +'"></span></td>'+
				'<td>'+ result.score +'</td>'+
				'<td>'+ result.fullScore +'</td>'+
				'<td><span class="'+ finish_class +'"></span></td>' +
				'<td>'+ formatDate(new Date(result.finishTime)) +'</td>'+
				'<td><a class="'+ belief_class + '" href="javascript:void(0);" target="_blank" data-toggle="popover" data-placement="left" data-content="'+ result.reason +'" onclick="hideOtherPopovers();">'+ result.belief * 100 +'%</a></td>' +
			'</tr>';
}

function  formatDate(now)   {     
    var   year=now.getYear();     
    var   month=now.getMonth()+1;     
    var   date=now.getDate();     
    var   hour=now.getHours();     
    var   minute=now.getMinutes();     
    var   second=now.getSeconds();     
    return   "20" + (year - 100) +"-"+month+"-"+date+"   "+hour+":"+minute+":"+second;     
} 

function isNull(str){
	return str == undefined || str == null || str == ""; 
}

function successNotify(message) {
	$.notify({
		message: message,
	},{
		type: 'success',
		placement: {
			from: "top",
			align: "center"
		}
	});
}

function dangerNotify(message) {
	$.notify({
		message: message,
	},{
		type: 'danger',
		placement: {
			from: "top",
			align: "center"
		}
	});
}

function initBootstrapPopovers(){
	$("[data-toggle='popover']").popover({html:true}); //打开换行识别
}

function hideOtherPopovers() {
	$("[data-toggle='popover']").popover('hide')
}