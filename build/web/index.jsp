<%-- 
    Document   : index
    Created on : 2017-6-27, 14:43:16
    Author     : yongcheng
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>基于百度地图的公交查询系统</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width">
        <link type="text/css" rel="stylesheet" href="css/index.css" />
        <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=GGpsGS7GWzT3B3Gs9bB8zGRU1YmSAjHI">
        </script>
        <script type="text/javascript" src="js/jquery-1.9.1.min.js"></script>
        <script type="text/javascript" src = "js/index.js"></script>
    </head>
    <body onload="load()">

        <div id="main_body"> 

            <img id="background"  src="image/background.png" />
            <div id="search-frame">
                <br>
                <p id="title">小花公交查询系统</p>
                <hr></hr>
                <div id="search-method">
                    <div id="text">
                        <div id="text0" onclick="busSearch()">公交路径 </div>
                        <div id="text1" onclick="stopSearch()">站名查询 </div>
                        <div id="text2" onclick="lineSearch()">线路查询</div>
                    </div>
                </div>
                <div id="input">
                    <div id="place">

                        <input type="text" id="start" name="StartId" size="20" placeholder="请输入起点" value="百度">
                        <input type="text" id="end" name="EndId" size="20" placeholder="请输入终点"  value="百度">

                    </div>
                    <div style="width:100px;margin: 0 auto;">
                        <input type="button" id="btn" name="submit" onclick="search1()" value="查询">

                    </div>
                </div>
                <div id="goAdmin"><a  href="admin/admin.jsp">前往管理员页面--></a></div>
            </div>

        </div>
        <div id="mask" class="mask"></div>  
        <div id="opendiv" class="opendiv">
            <div id="l-map"></div>
            <div id="r-result"></div>
        </div>
        
        
        <div id="opendiv1" class="opendiv1">
            
        </div>
        <div id="placedivbtn" class="closebtn1" onmouseover="style.backgroundImage = 'url(image/cbtn1.png)';"  
             onmouseout= "style.backgroundImage = 'url(image/cbtn.png)';" onclick="hidePlaceDiv()">  </div>
        </div>
    
    
        <div id="linedivbtn" class="closebtn" onmouseover="style.backgroundImage = 'url(image/cbtn1.png)';"  
             onmouseout= "style.backgroundImage = 'url(image/cbtn.png)';" onclick="hideLineDiv()">  </div>
    </div>
        <div id="linediv" class="opendiv">
            <div id="l-map1"></div>
            <ol style="margin-left:80px;margin-top: 50px;" id="StopMsg">
                
            </ol>
        </div>

        <div id="openbtn" class="closebtn" onmouseover="style.backgroundImage = 'url(image/cbtn1.png)';"  
             onmouseout= "style.backgroundImage = 'url(image/cbtn.png)';" onclick="hide()">  </div>
    </div>
</body>
</html>
<script  type="text/javascript">
    var map = new BMap.Map("l-map");
    var myCity = new BMap.LocalCity();   //通过ip定位所在城市
 
    map.centerAndZoom("滁州", 12);
    map.enableScrollWheelZoom();   //启用滚轮放大缩小，默认禁用
    map.enableContinuousZoom();    //启用地图惯性拖拽，默认禁用
    
    var map1 = new BMap.Map("l-map1");
      

    map1.centerAndZoom("滁州", 12);
    map1.enableScrollWheelZoom();   
    map1.enableContinuousZoom();    
    var ac = new BMap.Autocomplete(//建立一个自动完成的对象
            {"input": "start"
                , "location": "滁州"
            });
    var bc = new BMap.Autocomplete(//建立一个自动完成的对象
            {"input": "end"
                , "location": map
            });
    var Strat = "";
    var End = "";
    ac.addEventListener("onconfirm", function(e) {    //选择起点鼠标点击下拉列表后的事件
        var _value = e.item.value;
        Start = _value.province + _value.city + _value.district + _value.street + _value.business;
    });
    bc.addEventListener("onconfirm", function(e) {    //选择终点鼠标点击下拉列表后的事件
        var _value = e.item.value;
        End = _value.province + _value.city + _value.district + _value.street + _value.business;
    });
    var transit = new BMap.TransitRoute(map, {//查询公交换乘的对象
        renderOptions: {map: map, panel: "r-result"}
    });


</script>