<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>答题助手</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0"/>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="shortcut icon" href="resources/images/bitbug_favicon.ico" type="image/x-icon" />
    <link rel="stylesheet" href="resources/css/layui.css"  media="all">
    <link rel="stylesheet" href="resources/css/user.css"  media="all">
</head>
<body>
<div class="layui-tab" lay-filter="filter">
    <ul class="layui-tab-title">
        <li class="layui-this" lay-id="xigua">西瓜视频</li>
        <li lay-id="hj">花椒直播</li>
        <li lay-id="cd">冲顶大会</li>
        <li lay-id="zs">芝士超人</li>
        <li lay-id="hjsm">黄金十秒</li>
    </ul>
    <div class="layui-tab-content" id="titleContent">
        <div class="layui-tab-item layui-show" id = "xg">
           <!-- <p id ="datetime"><label>下场开始日期：</label>01月24日20:00场</p>-->
            <div class="answerDiv">

            </div>
        </div>
        <div class="layui-tab-item">
            <div class="answerDiv">

            </div>
        </div>
        <div class="layui-tab-item">
            <div class="answerDiv">

            </div>
        </div>
        <div class="layui-tab-item">
            <div class="answerDiv">

            </div>
        </div>
        <div class="layui-tab-item">
            <div class="answerDiv">

            </div>
        </div>
        <div class="layui-tab-item">
            <div class="answerDiv">

            </div>
        </div>
    </div>
</div>
<script src="resources/layui.js" charset="utf-8"></script>
<script>
    //注意：选项卡 依赖 element 模块，否则无法进行功能性操作
    layui.use(['element',"jquery","layer"], function(){
        var element = layui.element;
        var $ = layui.jquery,layer = layui.layer;

        //var dateStr = $("#datetime").text();
       // dateStr = dateStr.substring(7,18);
       // var date = new Date(2018,parseInt(dateStr.substring(0,2))-1,parseInt(dateStr.substring(3,5)),parseInt(dateStr.substring(6,8)),parseInt(dateStr.substring(9,11)),00);
        layer.alert('本页面在答题开始时会自动显示答案，答案来自网络，无法保证正确性');

        var interval;
        interval = setInterval(function () {
                getAnswer("xigua");
        },200);

        element.on('tab(filter)', function(elem){
            var appName =  $(this).attr('lay-id');
            clearInterval(interval);
            interval = setInterval(function () {
                    getAnswer(appName);
            },200);
        });

        function getAnswer (appName) {
            $.ajax({
                type: "GET",
                url:"xigua/getAnswer?app="+appName,
                async:true,
                contentType: "application/json",
                dataType: "json",
                success : function (data) {
                    if(data.code > 0){
                        setDiv(data.answer ,appName);
                    }
                }
            });



        }
        function setDiv(list,appName) {
            var stringAnswer = "提示区1:<hr>";
            for (var i = 0,y = list.length; i< y; i++ ){
                if(list[i].a == "uc"){
                    stringAnswer = stringAnswer + "<br><br><br>提示区2:<hr><h3>"+list[i].title+"</h3><h1 class = \"answer\">"+list[i].result+"</h1><hr>"
                }else {
                    stringAnswer = stringAnswer + "<h3>"+list[i].title+"</h3><h1 class = \"answer\">"+list[i].result+"</h1><hr>"
                }

            }
            var arrayid;
            if(appName == "xigua"){
                arrayid = 0;
            }else if(appName == "hj"){
                arrayid = 1;
            }else if(appName == "cd"){
                arrayid = 2;
            }else if(appName == "zs"){
                arrayid = 3;
            }else if(appName == "hjsm"){
                arrayid = 4;
            }
            var divArray = $(".answerDiv");
            divArray[arrayid].innerHTML = stringAnswer;
        }

    });
</script>
</body>
</html>