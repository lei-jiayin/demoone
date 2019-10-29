<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/10/29
  Time: 11:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>接收用户信息</title>
    <%-- 表情显示样式库 必备 --%>
    <link href="//cdn.bootcss.com/emojione/2.2.2/assets/css/emojione.min.css" rel="stylesheet">
    <link href="//cdn.bootcss.com/emojione/2.2.2/assets/sprites/emojione.sprites.css" rel="stylesheet">
    <script src="//cdn.bootcss.com/emojione/2.2.2/lib/js/emojione.min.js"></script>
</head>
<body>
    <p onclick="uncodeUtf16('${nn}','a')" id="a">${nn}</p>
    <p onclick="uncodeUtf16('${nickName1}','ala')" id="ala">${nickName1}</p>
    <p onclick="uncodeUtf16('${nickName2}'),'b'" id="b">${nickName2}</p>
    <p onclick="uncodeUtf16('${nickName3}'),'c'" id="c">${nickName3}</p>
<script>
    function uncodeUtf16(str,id){
        /*var reg = /\&#.*?;/g;
        var result = str.replace(reg,function(char){
            var H,L,code;
            if(char.length == 9 ){
                code = parseInt(char.match(/[0-9]+/g));
                H = Math.floor((code-0x10000) / 0x400)+0xD800;
                L = (code - 0x10000) % 0x400 + 0xDC00;
                return unescape("%u"+H.toString(16)+"%u"+L.toString(16));
            }else{
                return char;
            }
        });*/
        var result = emojione.toImage(str);
        // var result = emojione.shortnameToImage(str);
        console.log(result);
        document.getElementById(id).innerHTML = result;
        // return result;
    }
</script>

</body>
</html>
