<!DOCTYPE html>
<html>
<head>
    <#include "common.ftl">
</head>
<body>

<div class="layui-carousel" id="carousel1">
    <div carousel-item>
        <div style="background-color: #009688">
            <div style="position: relative;
                        top: 50%; /*偏移*/
                        transform: translateY(-50%);" align="center">
                <div align="left" style="position: relative;left:40%">
                    <h1>此CRM客户关系管理系统采用： </h1>
                    <h2>0.后端框架 springboot  <a class="layui-btn layui-btn-xs layui-btn-danger" target="_blank" href="http://https://docs.spring.io/spring-boot/docs/current/reference/html/features.html#features.spring-application">SpringBoot文档</a></h2>
                    <h2>1.前端模板 layui  <a class="layui-btn layui-btn-xs layui-btn-danger" target="_blank" href="http://www.layui.com/doc">layui文档</a></h2>
                    <h2>2.持久层 mybatis  <a class="layui-btn layui-btn-xs layui-btn-danger" target="_blank" href="https://mybatis.org/mybatis-3/zh/index.html">mybatis文档</a></h2>
                    <h2>3.模板引擎 freemarker  <a class="layui-btn layui-btn-xs layui-btn-danger" target="_blank" href="http://freemarker.foofun.cn/">freemarker文档</a></h2>
                    <h2>4.生成目录树插件 Z-tree  <a class="layui-btn layui-btn-xs layui-btn-danger" target="_blank" href="http://www.treejs.cn/v3/faq.php#_206">Z-tree文档</a></h2>
                    <h2>5.数据连接池 Druid  <a class="layui-btn layui-btn-xs layui-btn-danger" target="_blank" href="https://github.com/alibaba/druid/">Druid文档</a></h2>
                    <h2>6.分页插件 pageHelper<a class="layui-btn layui-btn-xs layui-btn-danger" target="_blank" href="https://pagehelper.github.io/">PageHelper文档</a></h2>
                </div>
            </div>
        </div>
        <div style="background-color: #5FB878"><img src="images/2.jpg" width="100%" height="100%"></div>
        <div style="background-color: #0B61A4"><img src="images/3.jpg" width="100%" height="100%"></div>
        <div style="background-color: mediumpurple"><img src="images/4.jpg" width="100%" height="100%"></div>
        <div style="background-color: palevioletred"><img src="images/5.jpg" width="100%" height="100%"></div>
    </div>
</div>


<script>
    layui.use('carousel', function(){
        let carousel = layui.carousel;
        //建造实例
        carousel.render({
            elem: '#carousel1'
            ,full:true
            ,interval:5000
        });
    });
</script>
</body>
</html>