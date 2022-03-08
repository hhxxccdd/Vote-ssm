<%--
  Created by IntelliJ IDEA.
  User: lcy
  Date: 2021-08-25
  Time: 12:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="../../lib/layui-v2.6.3/css/layui.css" media="all">
    <link rel="stylesheet" href="../../lib/bootstrap/bootstrap.min.css" media="all">
    <style>
        .layui-table-cell {
            text-align: center;
            height: auto;
            white-space: normal;
        }
    </style>
</head>
<body style="font-size: 0.8rem;">
<%--    搜索框--%>
<div class="d-flex m-3 align-items-center">
    <div class="d-flex">
        <label style="width: 100px;margin: 0px;" class="d-flex align-items-center">问卷标题:</label>
        <input id="questionTitle" class="form-control form-control-sm" value="" placeholder="请输入问卷标题"/>
    </div>
    <button id="searchBtn" style="margin-left: 0.6rem;" class="layui-btn layui-btn-sm layui-btn-normal"><i
            class="layui-icon layui-icon-search"></i>
        搜索
    </button>
    <button id="resetBtn" style="border-color: #FF7670;color: #FF7670;" type="button"
            class="layui-btn layui-btn-primary layui-btn-sm"><i
            class="layui-icon layui-icon-close"></i>重置
    </button>
</div>
<%--      表格布局--%>
<table id="questiontable" lay-filter="questiontable"></table>
<script type="text/html" id="headertool">

    </script>
<script type="text/html" id="linetool">
    <a lay-event="look" class="layui-btn layui-btn-sm layui-btn-normal"><i class="layui-icon layui-icon-edit"></i>统计分析</a>
    <a lay-event="edit" class="layui-btn layui-btn-sm"><i class="layui-icon layui-icon-edit"></i>打印投票</a>
</script>
<script src="../../lib/jquery-3.4.1/jquery-3.4.1.min.js" charset="utf-8"></script>
<script src="../../lib/layui-v2.6.3/layui.js" charset="utf-8"></script>
<script src="../../js/http.js" charset="utf-8"></script>
<script>

    layui.use(['table', 'layer'], function () {
        var table = layui.table;
        var layer = layui.layer;
        var $ = layui.jquery;
        //表格渲染
        table.render({
            elem: '#questiontable',
            url: '/api/sysQuestion/list',
            height: 'full-100',
            toolbar: '#headertool',//表格头部的工具栏
            cols: [[
                {
                    field: 'questionImg', title: '问卷图片', width: 200, align: 'center', templet: function (d) {
                        // return "<img src=" + d.questionImg + "width='80px' height='80px' class='layui-circle' />";
                        // 注意：结束符之前，不能有空格
                        return "<img src=" + d.questionImg + " width='80px' height='80px' class='layui-circle'/>";
                    }
                },
                {field: 'questionTitle', title: '问卷标题', align: 'center', width: 180},
                {field: 'questionDesc', title: '问卷简介', align: 'center'},
                {field: 'questionOrder', title: '问卷序号', align: 'center', width: 180},
                {
                    field: 'questionStatus', title: '问卷状态', align: 'center', width: 180, templet: function (d) {
                        if (d.questionStatus == '0') {
                            return '停用'
                        } else {
                            return '启用'
                        }
                    }
                },
                {field: 'username', title: '操作', align: 'center', width: 230, toolbar: '#linetool'},

            ]],
            page: true,
            limit: 10,
            limits: [10, 20, 30, 50]
        })
        //编辑、删除点击事件
        table.on('tool(questiontable)', function (obj) {
            console.log(obj)
            if (obj.event == 'look') {
                // layer.msg('编辑')
                layer.open({
                    skin: 'layui-layer-molv', //皮肤
                    offset: '0px',
                    title: '【'+obj.data.questionTitle+'】---统计图分析',
                    type: 2,
                    area: ['700px', '500px'],
                    content: '/api/sysQuestion/lookList?questionId=' + obj.data.questionId + "&questionTitle=" + obj.data.questionTitle,
                    // btn: ['打印', '取消'],
                    // btn1: function (index) {
                    //     window["layui-layer-iframe" + index].printCount();
                    // },
                    btn2: function (index) {

                    }
                })
            }
            if (obj.event == 'edit') {
                // layer.msg('编辑')
                layer.open({
                    skin: 'layui-layer-molv', //皮肤
                    offset: '0px',
                    title: obj.data.questionTitle,
                    type: 2,
                    area: ['700px', '500px'],
                    content: '/api/sysQuestion/count?questionId=' + obj.data.questionId + "&questionTitle=" + obj.data.questionTitle,
                    btn: ['打印', '取消'],
                    btn1: function (index) {
                        window["layui-layer-iframe" + index].printCount();
                    },
                    btn2: function (index) {

                    }
                })
            }
        });

        //刷新表格
        function reload() {
            table.reload('questiontable', {
                page: {
                    curr: 1
                },
                where: {
                    questionTitle: $("#questionTitle").val()
                }
            })
        };
        //搜索按钮
        $("#searchBtn").click(function () {
            reload();
        });
        //重置按钮
        $("#resetBtn").click(function () {
            //清空搜索框数据
            $("#questionTitle").val('');
            //重新加载表格
            reload();
        })
    })
</script>
</body>
</html>
