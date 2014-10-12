
$.tools.validator.addEffect('integerated_validator_errors', function(errors, event){
    var wall = $(this.getConf().container).fadeOut(200, function(){
        $(this).fadeIn()
        });
    wall.empty();
    $.each(errors, function(index, error) {
        error_tag = ''
        if( error.input.attr("localize_name") != undefined ){
            error_tag = error.input.attr("localize_name");
        }
        wall.append( "<p><strong>" + error_tag + "</strong> " +error.messages[0] + "</p>" );
    });
},function(){});


// supply the language
$.tools.validator.localize("zh_cn", {
    ':email'  	: '邮件地址格式不正确!',
    ':url' 		: '无效的 URL',
    '[max]'	 	: '最大长度为 $1',
    '[min]'		: '最小长度为$1',
    '[required]'	: '为必填项!'
});

$.tools.validator.fn("[data-equals]", "Value not equal with the $1 field", function(input) {
    var name = input.attr("data-equals"),
    field = this.getInputs().filter("[name=user[" + name + "]]");
    return input.val() == field.val() ? true :{
        zh_cn: "两次密码不匹配!"
    };

});


$.tools.validator.fn("[minlength]", function(input, value) {
    var min = input.attr("minlength");

    return value.length >= min ? true : {
        zh_cn: "请输入至少 " +min+ " 个字符!" + (min > 1 ? "" : "")
    };
});

$.tools.validator.fn("[maxlength]", function(input, value) {
    var max = input.attr("maxlength");
    return value.length <= max ? true : {
        zh_cn: "请输入至多 " +max+ " 个字符!"
    };
});

$.tools.validator.fn("[type=qq]", "QQ格式为5位及以上数字!", function(input, value) {
    return /^\d{5+}$/.test(value) ? true : {
        zh_cn: "QQ格式为5位及以上数字!"
    };
});

$.tools.validator.fn("[type=msn]", "Msn格式为邮件地址!", function(input, value) {
    return /^\d{5+}$/.test(value) ? true : {
        zh_cn: "Msn格式为邮件地址!"
    };
});

$.tools.validator.fn("[type=gtalk]", "Gtalk格式为邮件地址!", function(input, value) {
    return /^\d{5,15}$/.test(value) ? true : {
        zh_cn: "Gtalk格式为邮件地址!"
    };
});

$.tools.dateinput.localize("zh_cn",  {
    months:         '一月,二月,三月,四月, 五月,六月,七月,八月,九月,十月,十一月,十二月',
    shortMonths:   '一,二,三,四,五,六,七,八,九,十,十一,十二',
    days:          '星期一,星期二,星期三,星期四,星期五,星期六,星期日',
    shortDays:     '一,二,三,四,五,六,日'
 });