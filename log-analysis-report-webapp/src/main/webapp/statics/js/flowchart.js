function render_flow_chart(dataString){
  /***************
   * mail:luxueyan@umeng.com
   * name：flowchart
   ****************/
   if(dataString == "[]" || dataString == "")
   {
     return;
   }
   
    window.Flow = function(prop,canvas){
	 	this.prop ={
	 		lineHeight:110,
	 		recGap : [180,380,135,53,12.5],
	 		width : 600,
	 		recWidth :[240,110,110,110,110],//五种分支的主体框宽度
	 		tipWidth : 44,
	 		tipHeight : 22,
                        paddWidth:10
	 	};
	 	this.canvas = null;
	 	this.canvasContext = null;
	 	this.canvasProp = {
	 		// shadowBlur : 5,   // 阴影模糊等级
			// shadowColor : 'rgb(0,0,0)',  // 阴影颜色  
			width : 600,
			height : 800,        
	   		// globalAlpha : 0.8,
	   		lineWidth : 2,//线宽
	   		mainLineWidth : 3,//线宽
	   		activeColor:'#6987c4',
	   		mainColor:'#1BE106',
	   		inactiveColor :'#C8C8C8'
			// 阴影偏移量
			// shadowOffsetX : 3,  
			// shadowOffsetY :3,
	 	};
	 	 $.extend(this.prop,prop||{});
	 	 $.extend(this.canvasProp,canvas||{});
	 }
        window.Flow.prototype = {
            initUI : function(data){
           		// G_vmlCanvasManager_.init();
           		this.changeCanvasSize();
           		//更改名字，第一级别的名字
               $('div.rec1 .content').text(data[0].name).attr('title',data[0].name);
    		   this.createChart($('div.rec1'),data[0].out_chart);
    		   var level1 = $('.rec[level=1]').filter('.has_child').eq(0);
    		   this.createChart(level1,this.getData(data,level1.find('.content').text()));
    		   var	level2 = $('.rec[level=2]').filter('.has_child').eq(0);
		   	   this.createChart(level2,this.getData(data,level2.find('.content').text()));
    		   var	level3 = $('.rec[level=3]').filter('.has_child').eq(0);
		   	   this.createChart(level3,this.getData(data,level3.find('.content').text()));
    		   
            },
            //生成分支主体框
           createChart : function(parent,data){
            	if(data&&parent){
            		var level = parseInt(parent.attr("level"));
            		
            		var top = (level+0.5)*this.prop.lineHeight,
            		left = parent.position().left+(parent.width()+this.prop.paddWidth)/2,
            		dotData = [],
            		charts = '<div class="clear line">';
            		dotData[0]={x:left,y:top,level:level};
            		dotData[1]=[];
            		//获得主线
            		for (var i = 0,count=data.length;i<count;i++){
            			if (data[i][3]&&parent.hasClass('mainline')){
            				dotData[0].mainLine = i;
            				break;
            			}
            			
            		}
            		//no_active 灰色,charts内容
            		for ( i = 0 ,count = data.length; i<count;i++){
            			charts +='<div class="'+ (data[i][3]?"has_child ":"no_active ") + 'rec' + count+'-'+ (i+1) + ((parent.hasClass('mainline')&&dotData[0].mainLine == i)?' mainline ':'') +  (level==3?' no_active ':'') +' rec  rec'+ count +'" level='+(level+1)+
            			' index='+ i +' title="'+ data[i][0] + '"><div class="content">'+data[i][0]+'</div><span title="设为第一级"></span></div>';
            			dotData[1][i]={};
            			if(count == 1){
            				dotData[1][i].x = this.prop.recGap[count-1]+this.prop.recWidth[count-1]*0.5;
            			}else{
            				dotData[1][i].x = this.prop.recWidth[count-1]*(i+0.5)+i*this.prop.recGap[count-1];
            			}
            			
            			dotData[1][i].y = (level+1.5)*this.prop.lineHeight;
            			dotData[1][i].data = data[i][2];
            			dotData[1][i].active = data[i][3]&&level!=3;
            		}
            		//console.log(charts);
            		parent.parent().nextAll('div.line').remove();
            		$('#flowchart').append(charts+'</div>');
            		this.drawDataBody(parent,dotData);
            		this.height = $('#flowchart').height();
            		
            		this.drawLine(dotData);
        			 if (window.PIE) {//兼容ie8以下
            		 	// alert(123);
				        $('.rec').each(function() {
				            PIE.attach(this);
				        });
				    }
            	}
            	
            	
            },
            //生产数据框
             drawDataBody : function(parent,dotData){
            	var nowLine = parent.parent(),tips='',data,parentIndex,parentData,parentCount;
            	nowLine.nextAll("div.datatip").remove();
            	parentIndex = parent.attr('index');
            	// $('#flowchart_top').text(parentIndex);
            	parentCount = parent.siblings().size();
            		parentData = parseFloat($('#flowchart .level'+(dotData[0].level-1)).eq(parentIndex).text());
            		// parentData = 100;
            		// parentData = parseFloat(parent.parents('.line').prevUntil('.line').eq(parentCount-parentIndex).text());
            	// console.log($('#flowchart .level'+(dotData[0].level+1));
            	for(var i = 0 ,count = dotData[1].length;i<count;i++){
            		y = (dotData[0].y+dotData[1][i].y-this.prop.tipHeight)/2;
            		x = (dotData[0].x+dotData[1][i].x-this.prop.tipWidth)/2;
					data = dotData[1][i].data;
            		if($('#flowchart .line').index(parent.parents('.line')) > 0){
            			var midData = dotData[1][i].data*parentData;
            			// console.log(midData);
            			data = Math.round(midData/(midData>10?10:1))/(midData>10?10:100);
            		}else{
            			data = dotData[1][i].data;
            		}
            		tips +='<div class="datatip level'+ dotData[0].level +(dotData[1][i].active?" ":" no_active") +'" title="'+  data +'%" style="top:'+ y +'px;left:'+ x +'px;">'+ data +'%</div>'
            	}
				//console.log(tips);
            	nowLine.after(tips);
            },
            //画背景线
            drawLine : function(dotData){
            	  startX = dotData[0].x;
            	  startY = dotData[0].y;
            	  this.clearCanvas(0,startY);
            	  for(var i = 0 ,count = dotData[1].length;i<count;i++){
				 	  endY = dotData[1][i].y;
                      endX = dotData[1][i].x;
				 	  if(dotData[0].mainLine!=undefined&&i == dotData[0].mainLine){
					     this.canvasContext.lineWidth = this.canvasProp.mainLineWidth;
				 	  	 this.canvasContext.strokeStyle = this.canvasProp.mainColor;
				 	  }else{
					     this.canvasContext.lineWidth = this.canvasProp.lineWidth;
				 	  	 this.canvasContext.strokeStyle = dotData[1][i].active?this.canvasProp.activeColor:this.canvasProp.inactiveColor;
				 	  }
					  this.canvasContext.beginPath();
					  this.canvasContext.moveTo(startX,startY);
					  this.canvasContext.lineTo(endX,endY);
					  this.canvasContext.stroke();
					 
				  }
            },
            getData : function(data,name){
	    		   for(var i = 0 ,count = data.length;i<count;i++){
	    				if( data[i].name == name){
	    					return data[i].out_chart;
	    				}
	    			}
            		 
            	
            		
            },
            
            clearCanvas : function(x,y){
            	this.canvasContext.clearRect(x,y,this.canvasProp.width,this.canvasProp.height);
            },
            changeCanvasSize : function(){
				this.canvas = document.getElementById('backline');
				// }
				this.canvas.width = this.canvasProp.width;
				this.canvas.height = this.canvasProp.height;
				this.canvasContext=this.canvas.getContext('2d');
				// this.canvasContext.strokeStyle = this.canvasProp.activeColor;
				// this.canvasContext.lineWidth = this.canvasProp.lineWidth;
				// this.canvasContext.shadowBlur = this.canvasProp.shadowBlur;
				// this.canvasContext.shadowColor = this.canvasProp.shadowColor;
				  // // this.canvasContext.lineCap = lineCap;
				  // this.canvasContext.globalAlpha = this.canvasProp.globalAlpha;
			}
        };
        // 应用举例 这里是随机 最大支持5个子分支 --begin
        var myFlow = new Flow(),
        data = $.parseJSON(dataString);
        myFlow.initUI(data);
		//console.log(data);
        $("#flowchart .rec").live("click",function(){
        	if ($(this).hasClass('no_active')){
    			return false;
    		}else{
				myFlow.createChart($(this),myFlow.getData(data,$(this).find('.content').text()));
    			
    		}
        	
        });
        $('#flowchart .rec span').live("click",function(e){
        	$('#flowchart_top').html($(this).parent().html()).attr('title',$(this).parent().attr('title'));
				myFlow.createChart($('#flowchart_top'),myFlow.getData(data,$(this).prev().text()));
        		return false;//阻止冒泡
        });

}
