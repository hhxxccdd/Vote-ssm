<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2022/2/11
  Time: 14:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="../../lib/bootstrap/bootstrap.min.css" media="all">
    <link rel="stylesheet" href="../../lib/bootstrap/bootstrap.min.js" media="all">
</head>
<body class="m-4">
   <h3>bootstrap弹性盒子模型测试</h3>
   <br>
   <h5>div默认垂直排列</h5>
   <div style="background: #d0d0d0;height: 320px">
       <div class="bg-primary" style="color: #FFF;height: 50px;width: 50px;line-height: 50px;text-align: center">1</div>
       <div class="bg-success" style="color: #FFF;height: 50px;width: 50px;line-height: 50px;text-align: center">2</div>
       <div class="bg-dark" style="color: #FFF;height: 50px;width: 50px;line-height: 50px;text-align: center">3</div>
       <div class="bg-white" style="color: #FFF;height: 50px;width: 50px;line-height: 50px;text-align: center">4</div>
       <div class="bg-secondary" style="color: #FFF;height: 50px;width: 50px;line-height: 50px;text-align: center">5</div>
   </div>

   <h5>div变成弹性盒子模型(class="d-flex")</h5>
   <div class="d-flex" style="background: #d0d0d0;height: 320px">
       <div class="bg-primary" style="color: #FFF;height: 50px;width: 50px;line-height: 50px;text-align: center">1</div>
       <div class="bg-success" style="color: #FFF;height: 50px;width: 50px;line-height: 50px;text-align: center">2</div>
       <div class="bg-dark" style="color: #FFF;height: 50px;width: 50px;line-height: 50px;text-align: center">3</div>
       <div class="bg-white" style="color: #FFF;height: 50px;width: 50px;line-height: 50px;text-align: center">4</div>
       <div class="bg-secondary" style="color: #FFF;height: 50px;width: 50px;line-height: 50px;text-align: center">5</div>
   </div>
<br>
   <h5>div变成弹性盒子模型(class="d-flex"),水平排列</h5>
   <div class="d-flex flex-row" style="background: #d0d0d0;height: 320px">
       <div class="bg-primary" style="color: #FFF;height: 50px;width: 50px;line-height: 50px;text-align: center">1</div>
       <div class="bg-success" style="color: #FFF;height: 50px;width: 50px;line-height: 50px;text-align: center">2</div>
       <div class="bg-dark" style="color: #FFF;height: 50px;width: 50px;line-height: 50px;text-align: center">3</div>
       <div class="bg-white" style="color: #FFF;height: 50px;width: 50px;line-height: 50px;text-align: center">4</div>
       <div class="bg-secondary" style="color: #FFF;height: 50px;width: 50px;line-height: 50px;text-align: center">5</div>
   </div>

   <br>
   <h5>div变成弹性盒子模型(class="d-flex"),垂直排列</h5>
   <div class="d-flex flex-column" style="background: #d0d0d0;height: 320px">
       <div class="bg-primary" style="color: #FFF;height: 50px;width: 50px;line-height: 50px;text-align: center">1</div>
       <div class="bg-success" style="color: #FFF;height: 50px;width: 50px;line-height: 50px;text-align: center">2</div>
       <div class="bg-dark" style="color: #FFF;height: 50px;width: 50px;line-height: 50px;text-align: center">3</div>
       <div class="bg-white" style="color: #FFF;height: 50px;width: 50px;line-height: 50px;text-align: center">4</div>
       <div class="bg-secondary" style="color: #FFF;height: 50px;width: 50px;line-height: 50px;text-align: center">5</div>
   </div>

   <br>
   <h3>主轴的对齐方式：左对齐、居中对齐、右对齐</h3>
   <br>

   <h5>div变成弹性盒子模型(class="d-flex"),左对齐</h5>
   <div class="d-flex justify-content-start" style="background: #d0d0d0;height: 320px">
       <div class="bg-primary" style="color: #FFF;height: 50px;width: 50px;line-height: 50px;text-align: center">1</div>
       <div class="bg-success" style="color: #FFF;height: 50px;width: 50px;line-height: 50px;text-align: center">2</div>
       <div class="bg-dark" style="color: #FFF;height: 50px;width: 50px;line-height: 50px;text-align: center">3</div>
       <div class="bg-white" style="color: #FFF;height: 50px;width: 50px;line-height: 50px;text-align: center">4</div>
       <div class="bg-secondary" style="color: #FFF;height: 50px;width: 50px;line-height: 50px;text-align: center">5</div>
   </div>
   <h5>div变成弹性盒子模型(class="d-flex"),居中对齐</h5>
   <div class="d-flex justify-content-center" style="background: #d0d0d0;height: 320px">
       <div class="bg-primary" style="color: #FFF;height: 50px;width: 50px;line-height: 50px;text-align: center">1</div>
       <div class="bg-success" style="color: #FFF;height: 50px;width: 50px;line-height: 50px;text-align: center">2</div>
       <div class="bg-dark" style="color: #FFF;height: 50px;width: 50px;line-height: 50px;text-align: center">3</div>
       <div class="bg-white" style="color: #FFF;height: 50px;width: 50px;line-height: 50px;text-align: center">4</div>
       <div class="bg-secondary" style="color: #FFF;height: 50px;width: 50px;line-height: 50px;text-align: center">5</div>
   </div>
   <h5>div变成弹性盒子模型(class="d-flex"),右对齐</h5>
   <div class="d-flex justify-content-end" style="background: #d0d0d0;height: 320px">
       <div class="bg-primary" style="color: #FFF;height: 50px;width: 50px;line-height: 50px;text-align: center">1</div>
       <div class="bg-success" style="color: #FFF;height: 50px;width: 50px;line-height: 50px;text-align: center">2</div>
       <div class="bg-dark" style="color: #FFF;height: 50px;width: 50px;line-height: 50px;text-align: center">3</div>
       <div class="bg-white" style="color: #FFF;height: 50px;width: 50px;line-height: 50px;text-align: center">4</div>
       <div class="bg-secondary" style="color: #FFF;height: 50px;width: 50px;line-height: 50px;text-align: center">5</div>
   </div>
   <h5>div变成弹性盒子模型(class="d-flex"),环绕对齐</h5>
   <div class="d-flex justify-content-around" style="background: #d0d0d0;height: 320px">
       <div class="bg-primary" style="color: #FFF;height: 50px;width: 50px;line-height: 50px;text-align: center">1</div>
       <div class="bg-success" style="color: #FFF;height: 50px;width: 50px;line-height: 50px;text-align: center">2</div>
       <div class="bg-dark" style="color: #FFF;height: 50px;width: 50px;line-height: 50px;text-align: center">3</div>
       <div class="bg-white" style="color: #FFF;height: 50px;width: 50px;line-height: 50px;text-align: center">4</div>
       <div class="bg-secondary" style="color: #FFF;height: 50px;width: 50px;line-height: 50px;text-align: center">5</div>
   </div>
   <h5>div变成弹性盒子模型(class="d-flex"),两端对齐</h5>
   <div class="d-flex justify-content-between" style="background: #d0d0d0;height: 320px">
       <div class="bg-primary" style="color: #FFF;height: 50px;width: 50px;line-height: 50px;text-align: center">1</div>
       <div class="bg-success" style="color: #FFF;height: 50px;width: 50px;line-height: 50px;text-align: center">2</div>
       <div class="bg-dark" style="color: #FFF;height: 50px;width: 50px;line-height: 50px;text-align: center">3</div>
       <div class="bg-white" style="color: #FFF;height: 50px;width: 50px;line-height: 50px;text-align: center">4</div>
       <div class="bg-secondary" style="color: #FFF;height: 50px;width: 50px;line-height: 50px;text-align: center">5</div>
   </div>

   <br>
   <h3>交叉轴的对齐方式：上对齐、居中对齐、下对齐</h3>
   <br>

   <h5>div变成弹性盒子模型(class="d-flex"),上对齐</h5>
   <div class="d-flex align-items-start" style="background: #d0d0d0;height: 320px">
       <div class="bg-primary" style="color: #FFF;height: 50px;width: 50px;line-height: 50px;text-align: center">1</div>
       <div class="bg-success" style="color: #FFF;height: 50px;width: 50px;line-height: 50px;text-align: center">2</div>
       <div class="bg-dark" style="color: #FFF;height: 50px;width: 50px;line-height: 50px;text-align: center">3</div>
       <div class="bg-white" style="color: #FFF;height: 50px;width: 50px;line-height: 50px;text-align: center">4</div>
       <div class="bg-secondary" style="color: #FFF;height: 50px;width: 50px;line-height: 50px;text-align: center">5</div>
   </div>
   <h5>div变成弹性盒子模型(class="d-flex"),居中对齐</h5>
   <div class="d-flex align-items-center" style="background: #d0d0d0;height: 320px">
       <div class="bg-primary" style="color: #FFF;height: 50px;width: 50px;line-height: 50px;text-align: center">1</div>
       <div class="bg-success" style="color: #FFF;height: 50px;width: 50px;line-height: 50px;text-align: center">2</div>
       <div class="bg-dark" style="color: #FFF;height: 50px;width: 50px;line-height: 50px;text-align: center">3</div>
       <div class="bg-white" style="color: #FFF;height: 50px;width: 50px;line-height: 50px;text-align: center">4</div>
       <div class="bg-secondary" style="color: #FFF;height: 50px;width: 50px;line-height: 50px;text-align: center">5</div>
   </div>
   <h5>div变成弹性盒子模型(class="d-flex")下对齐</h5>
   <div class="d-flex align-items-end" style="background: #d0d0d0;height: 320px">
       <div class="bg-primary" style="color: #FFF;height: 50px;width: 50px;line-height: 50px;text-align: center">1</div>
       <div class="bg-success" style="color: #FFF;height: 50px;width: 50px;line-height: 50px;text-align: center">2</div>
       <div class="bg-dark" style="color: #FFF;height: 50px;width: 50px;line-height: 50px;text-align: center">3</div>
       <div class="bg-white" style="color: #FFF;height: 50px;width: 50px;line-height: 50px;text-align: center">4</div>
       <div class="bg-secondary" style="color: #FFF;height: 50px;width: 50px;line-height: 50px;text-align: center">5</div>
   </div>

   <br>
   <h3>水平垂直居中</h3>
   <br>
   <h5>div变成弹性盒子模型(class="d-flex") 水平垂直居中</h5>
   <div class="d-flex align-items-center justify-content-center" style="background: #d0d0d0;height: 320px">
       <div class="bg-primary" style="color: #FFF;height: 50px;width: 50px;line-height: 50px;text-align: center">1</div>
       <div class="bg-success" style="color: #FFF;height: 50px;width: 50px;line-height: 50px;text-align: center">2</div>
       <div class="bg-dark" style="color: #FFF;height: 50px;width: 50px;line-height: 50px;text-align: center">3</div>
       <div class="bg-white" style="color: #FFF;height: 50px;width: 50px;line-height: 50px;text-align: center">4</div>
       <div class="bg-secondary" style="color: #FFF;height: 50px;width: 50px;line-height: 50px;text-align: center">5</div>
   </div>


</body>
</html>
