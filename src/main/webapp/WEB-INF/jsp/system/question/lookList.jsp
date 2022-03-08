<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2022/3/5
  Time: 17:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="../../lib/bootstrap/bootstrap.min.css" media="all">
    <link rel="stylesheet" href="../../lib/layui-v2.6.3/css/layui.css" media="all">
    <title>Title</title>
</head>
<body>
<input id="questionId" type="hidden" value="${questionId}" />
<input id="questionTitle" type="hidden" value="${questionTitle}" />
<%--存放试题的容器--%>
  <div id="chartList" style="margin-top: 20px;" class="d-flex justify-content-center align-items-center flex-column">
      <!-- 为 ECharts 准备一个定义了宽高的 DOM -->

  </div>
<%--定义模板 需要隐藏--%>
<div id="template" style="display: none">
    <div id="main" class="mb-5" style="width: 600px;height:400px;background-color: #EEEEEE;border: 1px solid #e5e5e5"></div>
</div>
  <script src="../../lib/jquery-3.4.1/jquery-3.4.1.min.js" charset="utf-8"></script>
  <script src="../../js/echarts.min.js"></script>
  <script src="../../lib/layui-v2.6.3/layui.js" charset="utf-8"></script>
  <script src="../../js/http.js" charset="utf-8"></script>
  <script type="text/javascript">
      $(document).ready(function (){
          getPaperList();
      })
      //获取数据
      function getPaperList(){
          var parm ={
              questionId :$("#questionId").val(),
          }
          http.get("/api/sysQuestion/getTotalList",parm,function (data){
              if(data.code == 200){
                  console.log(data)
                  //返回的试题数据
                  var result = data.data;
                  if(result && result.length>0){
                      //清空容器
                      $("#chartList").empty();
                      //获取存放试题的容器
                      var list = $("#chartList");
                      //循环试题
                      for(var i=0;i<result.length;i++){
                          //找到试题的模板
                          var clonechat = $("#template #main").clone();
                          clonechat.attr("id","main"+i);
                          //生成echart
                          list.append(clonechat);
                          //初始化
                          var myChart = echarts.init(document.getElementById('main'+i));
                          //配置option属性
                          var option = null;
                          //单选题
                          if(result[i].paperType ==1 ){

                              option = {
                                  title: {
                                      text: 'Referer of a Website',
                                      subtext: 'Fake Data',
                                      left: 'center'
                                  },
                                  tooltip: {
                                      trigger: 'item'
                                  },
                                  legend: {
                                      orient: 'vertical',
                                      left: 'left'
                                  },
                                  series: [
                                      {
                                          name: 'Access From',
                                          type: 'pie',
                                          radius: '50%',
                                          data: [

                                          ],
                                          emphasis: {
                                              itemStyle: {
                                                  shadowBlur: 10,
                                                  shadowOffsetX: 0,
                                                  shadowColor: 'rgba(0, 0, 0, 0.5)'
                                              }
                                          }
                                      }
                                  ]
                              };
                              //设置试题的标题
                              option.title.text =i+1+'、'+ result[i].paperTitle;
                              //生成选项
                            if(result[i].paperChoice && result[i].paperChoice.length>0){
                                  for (var j=0;j<result[i].paperChoice.length;j++){
                                      var obj = {value:0,name:''};
                                      obj.value = result[i].paperChoice[j].total;
                                      obj.name = result[i].paperChoice[j].choiceText;
                                      option.series[0].data.push(obj);
                                  }
                              }
                          }
                          //多选题
                          else if(result[i].paperType == 2){
                              option = {
                                  title: {
                                      text: 'Referer of a Website',
                                      subtext: 'Fake Data',
                                      left: 'center'
                                  },
                                  xAxis: {
                                      type: 'category',
                                      data: []
                                  },
                                  yAxis: {
                                      type: 'value'
                                  },
                                  series: [
                                      {
                                          data: [],
                                          type: 'bar'
                                      }
                                  ]
                              };
                              //设置标题
                             option.title.text =i+1+'、'+result[i].paperTitle;
                              if(result[i].paperChoice && result[i].paperChoice.length>0){
                                  for (var j=0;j<result[i].paperChoice.length;j++){
                                      option.xAxis.data.push(result[i].paperChoice[j].choiceText)
                                      option.series[0].data.push(result[i].paperChoice[j].total)
                                  }
                              }
                          }
                          myChart.setOption(option);
                      }
                  }
              }
          })
      }
      // 基于准备好的dom，初始化echarts实例
      // var myChart = echarts.init(document.getElementById('main'));
      //
      // // 指定图表的配置项和数据
      // var option = {
      //     title: {
      //         text: 'Referer of a Website',
      //         left: 'left',
      //         top:'10%'
      //     },
      //     tooltip: {
      //         trigger: 'item'
      //     },
      //     legend: {
      //         orient: 'vertical',
      //         left: 'left',
      //         top:'20%'
      //     },
      //     series: [
      //         {
      //             name: 'Access From',
      //             type: 'pie',
      //             radius: '50%',
      //             data: [
      //                 { value: 1048, name: 'Search Engine' },
      //                 { value: 735, name: 'Direct' },
      //                 { value: 580, name: 'Email' },
      //                 { value: 484, name: 'Union Ads' },
      //                 { value: 300, name: 'Video Ads' }
      //             ],
      //             emphasis: {
      //                 itemStyle: {
      //                     shadowBlur: 10,
      //                     shadowOffsetX: 0,
      //                     shadowColor: 'rgba(0, 0, 0, 0.5)'
      //                 }
      //             }
      //         }
      //     ]
      // };
      //
      // // 使用刚指定的配置项和数据显示图表。
      // myChart.setOption(option);
  </script>
</body>
</html>
