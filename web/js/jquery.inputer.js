/*---------------------------------------------------------------------------*/
/*jquery.inputer.js*/
/*author:xujianbo*/
/*datetiem:2011-05-31*/
/*此控件代码可能有些凌乱，由于公司项目开发紧急，临时写的一个，希望大家可以拿去改进！*/
/*author QQ:350023173*/
/*---------------------------------------------------------------------------*/
$.inputerIndex = 0;
$.fn.inputer = function (idkey, data, defaultvalue, funCallBack) {//data [{text:'aa',value:123},{test:'aa',value:123}]
	var obj = this;
    var objElementValue = idkey + "_";
    var arrayElement = idkey;
    var tempData = '';
    var elementDiv = "inputer_div_" + idkey;
    var elementUL = "inputer_ul_" + idkey;
    var elementLI = "inputer_li_" + idkey + "_";
    var tempText = data[0].text;
    var tempValue = data[0].value;
    var objOffSet = obj.offset();
    var tempKeyUpCount = -1; //计算当前定位ID

    //添加结果隐藏域
    $(document.body).append('<input type="hidden" id="' + objElementValue + '" value="' + defaultvalue + '"/>');
    //初始数据
    //obj.val(tempText); //默认为第一行
    //$("#" + objElementValue).val(tempValue);
    if (tempData == '' || obj.val() == '')
        tempData = data;
    $.each(tempData, function (ovar, opt) {
        if (defaultvalue == opt.value) {//匹配项
            obj.val(_inputer_setContent(opt.text)); //默认用户定义
            $("#" + objElementValue).val(opt.value);
            tempText = _inputer_setContent(opt.text);
            tempValue = opt.value;
        }
    });
    obj.focus(function () {//获取焦点
        if (tempData == '' || obj.val() == '')
          tempData = data;
        _inputer(tempData);
    });
    obj.blur(function () { //失去焦点
    	 if(tempValue!='0'){
    		obj.val(tempText);
            $("#" + objElementValue.substr(0,objElementValue.length-2)).val(tempValue);    		
    	 }
    });

    obj.keyup(function (event) { //模糊搜索
        if (event.keyCode == 38 || event.keyCode == 40 || event.keyCode == 13) {
            KeyUpOrDownOrPress(event.keyCode);
            return;
        }

        var _tempData = '';
        if (obj.val() != null && obj.val() != "") {
             $.each(data, function (ovar, opt) {
                var _tempIndex = opt.text.toLowerCase().indexOf(obj.val().toLowerCase());
                if (_tempIndex > -1) {
                    if (_tempData != '') _tempData += ",";
                    _tempData += "{text:'" + opt.text.replace(opt.text.substr(_tempIndex, obj.val().length), "<font color=\"red\">" + opt.text.substr(_tempIndex, obj.val().length) + "</font>") + "',value:'" + opt.value + "'}";
                }
            }); 
            if (_tempData != '') {
                _tempData = "[" + _tempData + "]";
                _tempData = eval(_tempData);
            }
            else
                _tempData = eval("[{text:'没有搜索到匹配的项!',value:'" + defaultvalue + "'}]");
            tempData = _tempData;
        }
        else {
            tempData = data;
       }
        _inputer(tempData);
    });

    function _inputer(tempData) {
        if (_inputer_exist(elementDiv)) { $("#" + elementDiv).remove(); }

        $(document.body).append('<div id="' + elementDiv + '" class="inputer_div"></div>');
        arrayElement += "|" + elementDiv;

        $("#" + elementDiv).css({
            width: obj.width() + ($.browser.msie ? 2 : 2) + "px",
            height: tempData.length > 10 ? "208px" : "auto",
            "overflow-y": tempData.length > 10 ? "auto" : "hidden",
            left: objOffSet.left,
            top: objOffSet.top + obj.height() + ($.browser.msie ? 6 : 6)
        });

        $("#" + elementDiv).append('<ul id="' + elementUL + '" class="inputer_ul"></ul>');
        arrayElement += "|" + elementUL;

        $.each(tempData, function (ovar, opt) {
            var item = elementLI + ovar;
            $("#" + elementUL).append('<li id=' + item + ' class="inputer_li">' + opt.text + '</li>');
            arrayElement += "|" + item;

            $("#" + item).mouseout(function () { this.className = "inputer_li"; });
            $("#" + item).mouseover(function () { this.className = "inputer_li_hover"; });

            $("#" + item).click(function () {
                $("#" + elementDiv).css("display", "none");
                obj.val(_inputer_setContent(opt.text));

                //临时变量赋值
                tempText = _inputer_setContent(opt.text);
                tempValue = opt.value;
                //隐藏域赋值
                if (_inputer_exist(objElementValue) == false)
                    $(document.body).append('<input type="hidden" id="' + objElementValue + '" class="inputer_div"/>');
                $("#" + objElementValue.substr(0,objElementValue.length-2)).val(opt.value);
                //触发外部事件
                if (funCallBack != null && typeof funCallBack == "function"){
                    funCallBack(opt.value);
                }

            });
        });

        $(document.body).click(function () {
            var e = arguments[0] || window.event;
            var eventSource = e.srcElement || e.target;
            var _arrayElement = arrayElement.split('|');
            var flag = false;
            for (var i = 0; i < _arrayElement.length; i++) {
                if (eventSource.id == _arrayElement[i]) {
                    flag = true;
                    break;
                }
            }
            if (!flag) $("#" + elementDiv).css("display", "none");
        });
    }

    function KeyUpOrDownOrPress(keyCode) {
        //处理事件
        var arrayLIlist = $("#" + elementUL + ">li");
        var arrayLIdata = null;
        switch (keyCode) {
            case 38:
                {
                    if (tempKeyUpCount <= 0)
                        tempKeyUpCount = 0;
                    else
                        tempKeyUpCount -= 1;

                    $.each(tempData, function (ovar, opt) {
                        arrayLIlist[ovar].className = "inputer_li";
                    });
                    arrayLIlist[tempKeyUpCount].className = "inputer_li_hover";

                    if (tempKeyUpCount < 8)
                        $("#" + elementDiv).scrollTop(0);
                    break;
                }
            case 40:
                {
                    if (tempKeyUpCount >= tempData.length - 1)
                        tempKeyUpCount = tempData.length - 1;
                    else
                        tempKeyUpCount += 1;

                    $.each(tempData, function (ovar, opt) {
                        arrayLIlist[ovar].className = "inputer_li";
                    });
                    arrayLIlist[tempKeyUpCount].className = "inputer_li_hover";

                    if (tempKeyUpCount % 8 == 0)
                        $("#" + elementDiv).scrollTop(26 * tempKeyUpCount);
                    break;
                }
            case 13:
                {
            	    if(tempKeyUpCount==-1){
            	    	tempKeyUpCount=0;
            	    }
                    arrayLIdata = tempData[tempKeyUpCount];
                    $("#" + elementDiv).css("display", "none");
                    obj.val(_inputer_setContent(arrayLIdata.text));

                    //临时变量赋值
                    tempText = _inputer_setContent(arrayLIdata.text);
                    tempValue = arrayLIdata.value;

                    //隐藏域赋值
                    if (_inputer_exist(objElementValue) == false)
                        $(document.body).append('<input type="hidden" id="' + objElementValue + '" class="inputer_div"/>');
                    $("#" + objElementValue).val(arrayLIdata.value);

                    //触发外部事件
                    if (funCallBack != null && typeof funCallBack == "function")
                        funCallBack(arrayLIdata.value);
                    //自动失去焦点
                    tempKeyUpCount = 0;
                    obj.blur();
                    break;
                }
        }
    }

    function _inputer_exist(id) {
        var element = $("#" + id);
        if (element.length > 0) { return true }
        else { return false }
    }

    function _inputer_setContent(str) {
        str = str.replace(/<\/?[^>]*>/g, ''); //去除HTML tag
        str.value = str.replace(/[ | ]*\n/g, '\n'); //去除行尾空白
        str = str.replace(/\n[\s| | ]*\r/g, '\n'); //去除多余空行
        return str;
    }
};