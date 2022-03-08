<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2022/2/28
  Time: 8:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="../../lib/bootstrap/bootstrap.min.css" media="all">
    <link rel="stylesheet" href="../../lib/layui-v2.6.3/css/layui.css" media="all">
    <style>
        .item{
            background-color: #FFF;
            border-radius: 10px;
        }
        html,body{
            margin: 0px;
        }
    </style>
</head>

<body class="m-3" style="background-color: #F0F0F0;">
<%--添加隐藏域，保存数据问卷id--%>
  <input type="hidden" id="questionId" value="${questionId}">
<%--存放试题的容器--%>
  <div class="d-flex flex-column layui-form paperList">

  </div>
<%--试题的模板--%>
  <div class="template" style="display: none;">
      <%--单选题--%>
      <div id="paper1" class="item d-flex flex-column p-3 mb-3">
          <div class="d-flex align-items-center">
              <span class="paperOrder"></span> <span>、</span><span class="paperTitle"></span>
          </div>
          <hr>
<%--      单选题的容器    --%>
          <div class="radioitem d-flex flex-column">

          </div>
      </div>
<%--单选题的模板--%>
          <input class="radioTemplate" value="" name="choiceText" title="" type="radio"/>
      <%--多选题--%>
      <div id="paper2" class="item d-flex flex-column p-3 mb-3">
          <div class="d-flex align-items-center">
              <span class="paperOrder"></span> <span>、</span><span class="paperTitle"></span>
          </div>
          <hr>
<%--          多选题选项的容器--%>
          <div class="radioitem d-flex flex-column">

          </div>
      </div>
         <%--多选题选项模板--%>
          <div class="mb-2 checkboxTemplate">
              <input   name="choiceText" title="" lay-skin="primary" type="checkbox"/>
          </div>
      <%--填空题--%>
      <div id="paper3" class="item d-flex flex-column p-3 mb-3">
          <div class="d-flex align-items-center">
              <span class="paperOrder"></span> <span>、</span><span class="paperTitle"></span>
          </div>
          <hr>
          <div class="radioitem d-flex flex-column">
              <input class="form-control-sm form-control"  value="" name="choiceText"  type="text"/>
          </div>
      </div>
      <%--解答题--%>
      <div id="paper4" class="item d-flex flex-column p-3 mb-3">
          <div class="d-flex align-items-center">
              <span class="paperOrder"></span> <span>、</span><span class="paperTitle"></span>
          </div>
          <hr>
          <div class="radioitem d-flex flex-column">
              <textarea class="form-control-sm form-control"  value="" name="choiceText"  type="text"></textarea>
          </div>
      </div>
  </div>

  <script src="../../lib/jquery-3.4.1/jquery-3.4.1.min.js"></script>
  <script src="../../lib/layui-v2.6.3/layui.js" charset="utf-8"></script>
  <script src="../../js/http.js" charset="utf-8"></script>
  <script>
      //页面加载玩调用
      var form =null;
      $(document).ready(function (){
          layui.use(['form'],function (){
              form = layui.form;
          })
          getPaperList();
      })
      //根据问卷id获取试题列表
      function  getPaperList(){
          var parm ={
              questionId:$("#questionId").val()
          }
          http.get("/api/sysPaper/getPaperList",parm,function (data){
              if(data.code == 200) {
                 //动态生成试题
                  console.log(data)
                  if(data.data && data.data.length>0){
                      createPaper(data.data)
                  }
              }
          }
          )
      }
      //生成试题
      //循环，根据试题类型，找到对应的模板
      function createPaper(res){
         //情空容器
          $(".paperList").empty();
          //循环数据
          for(var i = 0 ; i <res.length; i++){
              //数组的每一条数据
             var num = res[i];
              //获取存放试题的容器
              var list =$(".paperList");
              //根据试题的类型，获得对应的模板
              var clonedom = $(".template #paper"+num.paperType).clone();
              //设置试题的序号
              clonedom.find(".paperOrder").text(num.paperOrder);
              //设置试题的标题
              clonedom.find(".paperTitle").text(num.paperTitle);
              //设置试题的id
              clonedom.attr("paperId",num.paperId);
              //设置试题的类型
              clonedom.attr("type",num.paperType);
              //如果是单选是多选题，需要设置选项
              //找到单选题的多选题的容器
              var radioList = clonedom.find(".radioitem");
              if(num.paperType == '1'){
                  if(num.paperChoice && num.paperChoice.length>0){
                      for (var j=0 ; j<num.paperChoice.length;j++){
                          //找到单选题的模板
                          var radioclone= $(".radioTemplate").clone();
                          //设置模板输入框的值
                          radioclone.val(num.paperChoice[j].choiceId);
                          radioclone.attr("title",num.paperChoice[j].choiceText );
                          radioclone.attr("paperId",num.paperChoice[j].paperId );
                          radioclone.attr("name","choiceText-"+num.paperId);
                          radioList.append(radioclone);
                      }
                  }
              }
              //多选题的选项处理
              if(num.paperType == '2'){
                  if(num.paperChoice && num.paperChoice.length>0){
                      for (var j=0 ; j<num.paperChoice.length;j++){
                          //找到单选题的模板
                          var radioclone= $(".checkboxTemplate").clone();
                          //设置模板输入框的值
                          radioclone.find("input[name='choiceText']").val(num.paperChoice[j].choiceId);
                          radioclone.find("input[name='choiceText']").attr("title",num.paperChoice[j].choiceText );
                          radioclone.find("input[name='choiceText']").attr("paperId",num.paperChoice[j].paperId );
                          //radioclone.attr("name","choiceText-"+num.paperChoice[j].choiceId);
                          radioList.append(radioclone);
                      }
                  }
              }
              list.append(clonedom);
              form.render();
          }
      }
      //表单获取页面数据
      function commitData(){
          //定义一个存放试题答案的数组
          var paperData = [];
          //获取所有的试题
          var paperList  = $(".paperList .item");
          if(paperList && paperList.length > 0){
              for (var i =0 ; i<paperList.length ; i++){
                  //定义提交答案的数据格式
                  var obj = {
                      questionId: '',  //问卷Id
                      paperId:'',      //试题Id
                      paperType:'',    //试题类型
                      paperValue:''    //试题答案
                  }
                  //设置问卷Id
                  obj.questionId = $("#questionId").val();
                  //设置问卷类型
                  obj.paperType= $(paperList[i]).attr("type");
                  //设置试卷试题的id
                  obj.paperId =  $(paperList[i]).attr("paperid");
                  //设置选项的值
                  //单选题
                  if(obj.paperType == '1'){
                      var  value  =  $(paperList[i]).find('input[name="choiceText-'+obj.paperId+'"]:checked').val();
                      if(value){
                          obj.paperValue = value;
                      }
                      console.log(value)
                  }
                  else if(obj.paperType == '2') {
                      //获取所有的选中的复选框
                     var boxitem = $(paperList[i]).find(".checkboxTemplate").find("input[name='choiceText']:checked");
                      //复选框会有多个选中的值，需要循环取值
                      if (boxitem && boxitem.length > 0){
                           //用于接受值
                          var vas= "";
                          for(var k=0;k<boxitem.length;k++){
                              //判断是否有值,取到的值，以逗号拼接
                              if($(boxitem[k]).val()){
                                   vas += $(boxitem[k]).val()+",";
                              }
                          }
                          //取到拼接的值后面的逗号 vas = 12,13,14
                          if(vas.length > 0){
                              vas = vas.substr(0,vas.length-1);
                              obj.paperValue=vas;
                          }
                      }
                  }
                  else if(obj.paperType == '3'){  //填空题
                       var textval = $(paperList[i]).find("input[name='choiceText']");
                       if(textval){
                           obj.paperValue = textval;
                       }
                  }
                  else {  //解答题
                      var textareaval = $(paperList[i]).find('textarea').val();
                      if(textareaval){
                          obj.paperValue=textareaval;
                      }
                  }
                  //把每一题的答案放到数组
                  paperData.push(obj)
              }
          }
          //判断是否有空值，如果有系统提示
          if(paperData.length == 0){
                layer.msg('感谢你的参与，请答卷后再提交试题！');
                return;
          }
          for(var j =0 ; j<paperData.length; j++){
                if (!paperData[j].paperValue){   //如果为空，说明题目没做完
                    layer.msg('感谢你的参与，请答卷后再提交试题！');
                    return;
                }
          }
          return paperData;
      }
  </script>
</body>
</html>
