<%-- 
    Document   : admin
    Created on : 2017-6-28, 12:50:39
    Author     : yongcheng
--%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.etc.dao.*"%>
<%@page import="com.etc.vo.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" src="../js/jquery-1.9.1.min.js"></script>
        <script type="text/javascript" src="../js/index.js"></script>
         <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=GGpsGS7GWzT3B3Gs9bB8zGRU1YmSAjHI">
        </script>   
        <title>公交信息管理</title>
        <link rel="stylesheet" href="admin.css">
    </head>
    <body>
        <p class="t1">公交线路：</p>
        <%
            RelationDAO rdao = new RelationDAO();
            List<String> a = rdao.SelectAllRoute();

        %>
        <div class="right1">
            <table width="100%"border='3px'>
                <tr>
                    <td nowrap align="left" style="word-wrap:break-word;width:80px;">公交线路</td>
                    <td nowrap align="left" style="word-wrap:break-word;text-align: center;">经过站点</td>
                    <td nowrap align='left' colspan="2" style='word-wrap:break-word;text-align: center;'><a href="javacript:void(0);" onclick="ShowAddPage()">添加线路</a></td>
                    
                </tr>
                <%                    for (int i = 0; i < a.size(); i++) {
                        String s = a.get(i);
                        out.println("<tr><td nowrap align='left' style='word-wrap:break-word;text-align: center;'>" + s + "</td>");
                        List<String> stops = rdao.SelectRoute(s);
                        out.println("<td nowrap align='left' style='word-wrap:break-word;font-size: 20px;width:1050px;overflow:auto;'>");
                        for (int j = 0; j < stops.size(); j++) {
                            String stop = stops.get(j);
                            if (j == (stops.size() - 1)) {
                                out.println("<a href=\"javacript:void(0);\" id=" + stop + " onclick=\"ShowRouteByStop(this.id)\">" + stop + "</a>");
                            } else {
                                out.println("<a href=\"javacript:void(0);\" id=" + stop + " onclick=\"ShowRouteByStop(this.id)\">" + stop + "</a>-->");
                            }
                        }
                        out.println("</td>");
                        out.println("<td nowrap align='left' style='word-wrap:break-word;text-align: center;'><a href=\"../DeleteRoute?route=" + s + "\">删除线路</a></td>");
                        out.println("<td nowrap align='left' style='word-wrap:break-word;text-align: center;'><a href=\"javacript:void(0);\" onclick=\"ShowChangeRoutePage('"+s+"')\">修改线路</a></td>");
                         
                        out.println("</tr>");
                    }
                %>
            </table>
        </div>
        <br><br>


        <p class="t1">站点管理：</p>
        <%
            StopDAO sdao = new StopDAO();
            List<Stop> stops = sdao.SelectAll();
        %>
        <div class="right1">
            <table width="100%"border='3px'>
                <tr>
                    <td nowrap align="left" style="word-wrap:break-word;text-align: center;">站点名称</td>
                    <td nowrap align="left" style="word-wrap:break-word;text-align: center;">所在经度</td>
                    <td nowrap align="left" style="word-wrap:break-word;text-align: center;">所在纬度</td>
                    <td nowrap align='left' colspan="2" style='word-wrap:break-word;text-align: center;'><a href="javacript:void(0);" onclick="ShowAddStopPage()">增加站点</a></td></tr>
                   
                </tr>
                       <% for (int i = 0; i < stops.size(); i++) {
                        Stop s =stops.get(i);
                        out.println("<tr><td nowrap align='left' style='word-wrap:break-word;text-align: center;'><a href=\"javacript:void(0);\" id=\"" + s.getStop() + "\" onclick=\"ShowRouteByStop(this.id)\">" + s.getStop() + "</a></td>");
                        out.println("<td nowrap align='left' style='word-wrap:break-word;text-align: center;'>"+s.getLongitude()+ "</td>");
                        out.println("<td nowrap align='left' style='word-wrap:break-word;text-align: center;'>"+s.getLatitude()+ "</td>");
                        out.println("<td nowrap align='left' style='word-wrap:break-word;text-align: center;'><a href=\"../DeleteStop?stop=" + s.getStop() + "\">删除站点</a></td>");
                        out.println("<td nowrap align='left' style='word-wrap:break-word;text-align: center;'><a href=\"javacript:void(0);\" onclick=\"ShowChangePage('"+s.getStop()+"')\">修改站点</a></td>");
                        
                       
                    }
                %>
                </table>
        </div>
        <div id="mask" class="mask"></div>  
        <div id="opendiv" class="opendiv">
            
        </div>
        <div id="changediv" class="changediv">
            <div id="login" align="center">
            </br>
                    <form action="../AddStop"  method="post">
                        <p id="stop"> 增加站点</p>
                            <input type="text"id="oldstopname" name="oldstopname" value=" " style="display: none;"/>
                            <div id="long"><input type="text" id="stopname" name="stop"placeholder="站点名"></div><br>
                            <input type="button" value="获得站名经纬度"style="background:#cccccc;"onclick="getpoint()"/>
                            <p id="long"><input type="text" id="longi" name="long" placeholder="经度"></p>
                            <p id="long"><input type="text" id="lat" name="lat" placeholder="纬度"></p>
                            <p><input type="submit" id="submit"  value="增加"></p>
                    </form>
            </div>
        </div> 

        <div id="routediv" class="newroutediv">
            <div class="route-title">公交线路：<input type="text" id="routeline" /></div>
            <div id="left" class="left-area">
                <div class="title1">新建线路站点(点击取消)：</div>
                <ol id="routestop" class="list">
                   
                </ol>
            </div>
            <div id="right" class="right-area">
                <div class="title1">候选站点(点击选择)：</div>
                <ol class="list">
                   
                     
                  <% for (int i = 0; i < stops.size(); i++) {
                        Stop s =stops.get(i);
                        out.println("<li onclick=\"addStop('"+s.getStop()+"')\">"+s.getStop()+"</li>");
                                       
                    }
                %>
                </ol>
            </div>
            <input type="button" id="addroutebtn" class="newroutebtn" onclick="AddNewRoute()" value="新建线路">

        </div>
        <div id="opennewroute" class="closebtn2" onmouseover="style.backgroundImage = 'url(../image/cbtn1.png)';"  
             onmouseout= "style.backgroundImage = 'url(../image/cbtn.png)';" onclick="hideroutediv()">  </div>
        <div id="routediv" class="changediv"></div>
       <div id="openchange" class="closebtn1" onmouseover="style.backgroundImage = 'url(../image/cbtn1.png)';"  
             onmouseout= "style.backgroundImage = 'url(../image/cbtn.png)';" onclick="hidechangediv()">  </div>     
        <div id="openbtn" class="closebtn" onmouseover="style.backgroundImage = 'url(../image/cbtn1.png)';"  
             onmouseout= "style.backgroundImage = 'url(../image/cbtn.png)';" onclick="hidediv()">  </div>

    </body>
</html>



<script type="text/javascript">
    var ac = new BMap.Autocomplete(//建立一个自动完成的对象
            {"input": "stopname"
                , "location": "滁州"
            });
          var myValue="";  
    ac.addEventListener("onconfirm", function(e) {    //鼠标点击下拉列表后的事件
        var _value = e.item.value;
        myValue = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
        console.log(_value.business);
        document.getElementById("stopname").value=_value.business;
        
    });
    var myGeo = new BMap.Geocoder();
    function getpoint()
    {
        
        var long,lat;
        if(myValue=="")
        {
            alert("请输入地名!!!");
            return false;
        }
        else
        {
            myGeo.getPoint(myValue,function(point){
                
                long = point.lng;
                lat = point.lat;   
                document.getElementById("longi").value = long
                document.getElementById("lat").value = lat

            });              
    }
}
</script>
