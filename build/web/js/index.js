    /* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function check1(form)
{



    var a = document.getElementById("checkPwd").value;
    if (a == "")
    {
        alert("请输入内容！！！")
        return false;
    } else {
        document.getElementById("checkForm").submit();
        return true;
    }
}



function busSearch()
{
    var page1 = document.getElementById("text0");
    var page2 = document.getElementById("text1");
    var page3 = document.getElementById("text2");
    page1.style.backgroundColor='#CCCCCC';
    page2.style.backgroundColor='#ffffff';
    page3.style.backgroundColor='#ffffff';
    loadXMLDoc("bus.xml");

}
function stopSearch()
{
    var page1 = document.getElementById("text0");
var page2 = document.getElementById("text1");
var page3 = document.getElementById("text2");
    page1.style.backgroundColor='#FFFFFF';
    page2.style.backgroundColor='#CCCCCC';
    page3.style.backgroundColor='#ffffff';
    loadXMLDoc("place.xml");
}
function lineSearch()
{
    var page1 = document.getElementById("text0");
var page2 = document.getElementById("text1");
var page3 = document.getElementById("text2");
    page1.style.backgroundColor='#ffffff';
    page2.style.backgroundColor='#ffffff';
    page3.style.backgroundColor='#CCCCCC';
    loadXMLDoc("line.xml");
}


var xmlhttp;
function loadXMLDoc(url)
{
    xmlhttp = null;
    if (window.XMLHttpRequest)
    {// code for Firefox, Opera, IE7, etc.
        xmlhttp = new XMLHttpRequest();
    }
    else if (window.ActiveXObject)
    {// code for IE6, IE5
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    if (xmlhttp != null)
    {
        xmlhttp.onreadystatechange = state_Change;
        xmlhttp.open("GET", url, true);
        xmlhttp.send(null);
    }
    else
    {
        alert("Your browser does not support XMLHTTP.");
    }
}

function state_Change()
{
    if (xmlhttp.readyState == 4)
    {// 4 = "loaded"
        if (xmlhttp.status == 200)
        {// 200 = "OK"
            document.getElementById('input').innerHTML = xmlhttp.responseText;
        }
        else
        {
            alert("Problem retrieving data:" + xmlhttp.statusText);
        }
    }
}

function search1()
{
    document.getElementById("mask").style.display = "block";
    document.getElementById("opendiv").style.display = "block";
    document.getElementById("openbtn").style.display = "block";
    if (Start == "" || End == "")
    {
        alert("请选择起点和终点！！！");
        return false;
    }
    else
    {
        transit.search(Start, End);
    }


}

function hide() {
    document.getElementById("mask").style.display = "none";
    document.getElementById("opendiv").style.display = "none";
    document.getElementById("openbtn").style.display = "none";

}
function load()
{



}
function setPlace() {
    map.clearOverlays();    //清除地图上所有覆盖物
    function myFun() {
        var pp = local.getResults().getPoi(0).point;    //获取第一个智能搜索的结果
        map.centerAndZoom(pp, 18);
        map.addOverlay(new BMap.Marker(pp));    //添加标注
    }
    var local = new BMap.LocalSearch(map, {//智能搜索
        onSearchComplete: myFun
    });
    local.search(myValue);
}


function ShowRouteByStop(stop)
{
    var url = "../ShowRoute?stop=" + stop;
    var str = "<p>\"" + stop + "\"站所经过的公交线路：</p><ul>";

    var data = {};
    $.get(url, data, function(res) {


        if (res == "[]")
        {
            alert("该站点尚无公交线路！");
        } else
        {
            var jsonObj = eval("(" + res + ")");
            for (var i = 0; i < jsonObj.length; i++)
            {
                str += "<li>线路：" + jsonObj[i]['route'] + "路" + "，所在站点：第" + jsonObj[i]['position'] + "站</li>";
                
                
            }
            str += "</ul>";
            $("#opendiv").html(str);

                document.getElementById("opendiv").style.display = "block";
                document.getElementById("openbtn").style.display = "block";
        }

    });
}
function hidediv() {

    document.getElementById("opendiv").style.display = "none";
    document.getElementById("openbtn").style.display = "none";
    map.clearOverlays();
}
function hidechangediv() {
    document.getElementById("mask").style.display = "none";
    document.getElementById("changediv").style.display = "none";
    document.getElementById("openchange").style.display = "none";

}
function hidePlaceDiv()
{
    document.getElementById("mask").style.display = "none";
    document.getElementById("opendiv1").style.display = "none";
    document.getElementById("placedivbtn").style.display = "none";

}

function ShowAddStopPage()
{
    document.getElementById("mask").style.display = "block";
    document.getElementById("changediv").style.display = "block";
    document.getElementById("openchange").style.display = "block";
}
function ShowChangePage(stopname)
{
    var url = "../LoadStopMsg?stop=" + stopname;

    var data = {};
    $.get(url, data, function(res) {
        var jsonObj = eval("(" + res + ")");
        if (jsonObj == null)
        {
            alert("获取信息出错！！！");
        } else
        {
            for (var i = 0; i < jsonObj.length; i++)
            {
                document.getElementById("stopname").value = jsonObj[i]['stop'];
                document.getElementById("oldstopname").value = jsonObj[i]['stop'];
                //document.getElementById("stopname").readOnly=false;
                document.getElementById("submit").value = "修改";
                document.getElementById("longi").value = jsonObj[i]['longitude'];
                document.getElementById("lat").value = jsonObj[i]['latitude'];
                document.getElementById("changediv").style.display = "block";
                document.getElementById("openchange").style.display = "block";
                document.getElementById("mask").style.display = "block";
                document.getElementById("longi").focus();
            }
        }
    });


}

function hideLineDiv(){
    map1.clearOverlays();
    document.getElementById("mask").style.display = "none";
    document.getElementById("linedivbtn").style.display = "none";
    document.getElementById("linediv").style.display = "none";
    document.getElementById("StopMsg").innerHTML="";
    
}

function hideroutediv()
{
    document.getElementById("mask").style.display = "none";
    document.getElementById("routediv").style.display = "none";
    document.getElementById("opennewroute").style.display = "none";
}


function ShowAddPage()
{
    document.getElementById("mask").style.display = "block";
    document.getElementById("routediv").style.display = "block";
    document.getElementById("opennewroute").style.display = "block";
}

function addStop(txt)
{
    var s = document.getElementById("routestop");
    var newli = document.createElement("li")
    newli.onclick = function() {
        deleteMyself(this)
    };
    newli.innerHTML = txt;
    s.appendChild(newli);
}

function deleteMyself(li)
{
    var s = document.getElementById("routestop");
    s.removeChild(li);
}

function AddNewRoute() {
    var a = document.getElementById("routeline").value;

    if (a == "" || a == null)
    {
        alert("请填写新建的线路名称！");
        $("#routeline").focus();
        return false;
    }
    else
    {
        var url = "../CheckRoute?route=" + a;
        var data = {};
        $.get(url, data, function(res) {
            if (res == "ok")
            {
                var olArray = $("#routestop li");
                console.log(olArray.length);
                var t = olArray.length;
                for (var i = 0; i < t; i++)
                {
                    var url1 = "../SaveRoute?stop=" + olArray[i].innerHTML + "&route=" + a + "&position=" + (i+1);
                    var data = {};
                    $.get(url1, data, function(res) {
                        if (res == "notok")
                        {
                            alert("存储数据出错！");
                            return false;
                        }

                    });

                }
                alert("新建线路成功！");
                document.getElementById("routeline").innerHTML="";
                document.getElementById("routestop").innerHTML="";
                document.getElementById("mask").style.display = "none";
                document.getElementById("routediv").style.display = "none";
                document.getElementById("opennewroute").style.display = "none";
                 location.reload(true);
            } else {
                alert("该线路已经存在！");
                $("#routeline").focus();
                return false;
            }

        });
    }
}

function ShowChangeRoutePage(route)
{
    var url = "../LoadRouteMsg?route=" + route;
    var data = {};
    $.get(url, data, function(res) {
      var jsonObj = eval("(" + res + ")");
      var a = document.getElementById("routestop");
      for (var i = 0; i < jsonObj.length; i++)
      { 
          var newli = document.createElement("li");
          newli.onclick = function() {
              deleteMyself(this)
          };
          newli.innerHTML=jsonObj[i]["stopname"];
           a.appendChild(newli);
       }
    });
    document.getElementById("routeline").value=route;
    document.getElementById("routeline").readOnly=true;
    var btn = document.getElementById("addroutebtn");
    btn.value="确定修改";
    btn.onclick = function() {
              ChangeTheRoute(route);
          };
    document.getElementById("mask").style.display = "block";
    document.getElementById("routediv").style.display = "block";
    document.getElementById("opennewroute").style.display = "block";
}
function ChangeTheRoute(route){
     var url = "../DeleteRoute?route=" + route;
    var data = {};
    $.get(url, data, function(res) {
    
    });

    AddNewRoute() ;
    
}

function SearchLine()
{
    var chartData = [];
    var line = document.getElementById("line").value;
    if(line == ""||line==null)
    {
        alert("请输入要查询的线路！");
    }else
    {
        var url ="LoadRouteStopMsg?route="+line;
        var data = {};
        
        $.get(url,data,function(res){   //get获取一个数据，交给function处理
            var jsonObj = eval("(" + res + ")");
           
            var ol = document.getElementById("StopMsg");
            
            for (var i = 0; i < jsonObj.length; i++)
            { 
                var newli = document.createElement("li");
                newli.innerHTML = "站点："+jsonObj[i]["stop"]+"  |  经度："+jsonObj[i]["longitude"] +"  |   纬度："+jsonObj[i]["latitude"];
                ol.appendChild(newli);      
            }
            
            $.each(jsonObj, function (item, value) {
                        chartData.push(new BMap.Point(value.longitude, value.latitude));//存储地图上的目标点
             });
             for (var i = 0; i < chartData.length-1; i++) {
                        var startPoint = chartData[i];
                        var endPoint = chartData[i + 1];
                        showPath(startPoint, endPoint);//连线
              }//公交路径划线
              
              $.each(jsonObj, function (item, value) {
                        var firstPoint = new BMap.Point(value.longitude, value.latitude);
                        var m1 = new BMap.Marker(firstPoint);
                        map1.addOverlay(m1);
                        var lab1 = new BMap.Label((item+1)+":"+value.stop, { position: firstPoint });
                        map1.addOverlay(lab1);
                       // console.log(value.stop+value.longitude+value.latitude);
                    });//对每个点进行标注
                    map1.centerAndZoom(new BMap.Point(jsonObj[0]["longitude"], jsonObj[0]["latitude"]), 18);
                    

        });
        
         
        document.getElementById("linediv").style.display="block";
        document.getElementById("mask").style.display="block";
        document.getElementById("linedivbtn").style.display="block";
    }
}

function SearchPlace()
{
    var stop = document.getElementById("stopname").value;
    console.log(stop);
    var url = "ShowRoute?stop=" + stop;
    

    var data = {};
    $.get(url, data, function(res) {

       
        if (res == "[]")
        {
            alert("该站点不存在或尚无公交线路！");
        } else
        {
            var jsonObj = eval("(" + res + ")");
             var str = "<p>\"" + jsonObj[0]['stop'] + "\"站所经过的公交线路：</p><ul>";
            for (var i = 0; i < jsonObj.length; i++)
            {
                str += "<li>线路：" + jsonObj[i]['route'] + "路" + "，所在站点：第" + jsonObj[i]['position'] + "站</li>";
                
                
            }
            str += "</ul>";
            $("#opendiv1").html(str);
            
                document.getElementById("mask").style.display = "block";
                document.getElementById("opendiv1").style.display = "block";
                document.getElementById("placedivbtn").style.display = "block";
        }

    });
}

function showPath(startPoint, EndPoint)//画两点之间的线
        {
            var walking = new BMap.DrivingRoute(map, { renderOptions: { map: map, autoViewport: true } });
            walking.search(startPoint, EndPoint);

            walking.setSearchCompleteCallback(function (rs) {
                var abc = rs;
                var pts = walking.getResults().getPlan(0).getRoute(0).getPath();
                var line = new BMap.Polyline(pts, { strokeColor: "green", strokeWeight: 5, strokeOpacity: 1 });
                map1.addOverlay(line);
            });
        }

