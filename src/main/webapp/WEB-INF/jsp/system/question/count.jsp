<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2022/3/5
  Time: 17:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="../../lib/layui-v2.6.3/css/layui.css" media="all">
    <link rel="stylesheet" href="../../lib/bootstrap/bootstrap.min.css" media="all">
    <style>
        table {
            text-align: center;
            border-collapse: collapse; /* 为table设置这个属性 */
        }
    </style>
    <title>Title</title>
</head>
<body style="padding: 10px">
<input class="questionId" id="questionId" value="${questionId}" style="display: none"/>
<input class="questionTitle" id="questionTitle" value="${questionTitle}" style="display: none" />
    <%--定义表格的模板--%>
    <table id="tables" style="display: none" class="tables">
          <tbody>
          <tr>
              <td class="paperTitle" style="width: 30%;text-align: center"></td>
              <td class="choiceText"></td>
              <td class="total"></td>
          </tr>
          </tbody>
    </table>
<%--  定义容器:存放生成模板--%>
<!--printstart-->
    <table border="1" style="width: 100%;font-size: 0.9rem">
<%-- 问卷标题--%>
        <h3 style="text-align: center">${questionTitle}</h3>
<%--表格的头部--%>
        <thead>
           <th>题目</th>
           <th>选项</th>
           <th>票数</th>
        </thead>
        <tbody class="tableList">

        </tbody>
    </table>
<!--printend-->
    <script src="../../lib/jquery-3.4.1/jquery-3.4.1.min.js" charset="utf-8"></script>
    <script src="../../js/echarts.min.js"></script>
    <script src="../../lib/layui-v2.6.3/layui.js" charset="utf-8"></script>
    <script src="../../js/http.js" charset="utf-8"></script>
    <script>
        $(document).ready(function (){
            getPaperList();
        })


      function getPaperList(){
            var parm = {
                questionId:$("#questionId").val()
            }
          http.get("/api/sysQuestion/getTotalList",parm,function (res){
              if(res.code==200 && res.data){
                     var result  = res.data;
                     //清空容器
                     $(".tableList").empty();
                     //获取容器
                     var list = $(".tableList");
                     for (var i =0;i<result.length;i++){
                            if(result[i].paperChoice && result[i].paperChoice.length>0){
                                for(var j=0;j<result[i].paperChoice.length;j++){
                                    //克隆模板
                                    var clonedom = $("#tables").find("tbody").find("tr").clone();
                                    if(j==0){
                                          clonedom.find(".paperTitle").attr("rowspan", result[i].paperChoice.length);
                                        clonedom.find(".paperTitle").text(result[i].paperTitle);
                                    }else{
                                        //移除空
                                        clonedom.find(".paperTitle").remove();
                                    }
                                    clonedom.find(".choiceText").text(result[i].paperChoice[j].choiceText);
                                    clonedom.find(".total").text(result[i].paperChoice[j].total);
                                    //放到容器
                                    list.append(clonedom)
                                }
                            }
                     }
              }
          })
      }
        function printCount() {
            var bdhtml = window.document.body.innerHTML;
            var printstart = "<!--printstart-->";
            var printend = "<!--printend-->";
            prnhtml = bdhtml.substr(bdhtml.indexOf(printstart), bdhtml.indexOf(printend));
            window.document.body.innerHTML = prnhtml;
            window.print();
            // 还原界面
            window.document.body.innerHTML = bdhtml;
            window.location.reload();
        }
    </script>
</body>
</html>
